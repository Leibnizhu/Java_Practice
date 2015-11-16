package leibniz.hu.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

public class TranProxy implements InvocationHandler {
	
	//经典的动态代理写法
	private Object source;
	private TranProxy(Object source){
		this.source = source;
	}
	
	//静态工厂类用于提供代理对象
	public static Object getProxy(Object source){
		return Proxy.newProxyInstance(TranProxy.class.getClassLoader(),
																				source.getClass().getInterfaces(), 
																				new TranProxy(source));
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		//先判断当前方法是否有事务的注解Tran
		if(!method.isAnnotationPresent(Tran.class)){
			//如果没有Tran注解，则不需要代理，直接执行原来的方法
			System.out.println("没有Tran注解，则不需要代理，直接执行原来的方法");
			return method.invoke(source, args);
		}
		//有注解，进入代理部分，获取一个连接并开启事务
		Connection conn = null;
		Object returnVal = null;
		try{
			//从工具类中获取一个用于事务的连接（保证线程唯一）
			conn = DataSourceUtil.getConnNonTrans();
			conn.setAutoCommit(false);
			//调用原来的方法执行
			returnVal = method.invoke(source, args);
			//提交
			System.out.println(">>>调用成功，提交事务");
			conn.commit();
		} catch(Exception e){
			//出现异常，则回滚
			System.out.println(">>>出现异常，回滚事务");
			conn.rollback();
			throw e;
		} finally{
			//不管如何，都要关闭连接
			conn.close();
			//从ThreadLocal中移除连接
			DataSourceUtil.remove();
		}
		return returnVal;
	}

}
