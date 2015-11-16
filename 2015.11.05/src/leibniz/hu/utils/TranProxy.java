package leibniz.hu.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

public class TranProxy implements InvocationHandler {
	
	//����Ķ�̬����д��
	private Object source;
	private TranProxy(Object source){
		this.source = source;
	}
	
	//��̬�����������ṩ�������
	public static Object getProxy(Object source){
		return Proxy.newProxyInstance(TranProxy.class.getClassLoader(),
																				source.getClass().getInterfaces(), 
																				new TranProxy(source));
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		//���жϵ�ǰ�����Ƿ��������ע��Tran
		if(!method.isAnnotationPresent(Tran.class)){
			//���û��Tranע�⣬����Ҫ����ֱ��ִ��ԭ���ķ���
			System.out.println("û��Tranע�⣬����Ҫ����ֱ��ִ��ԭ���ķ���");
			return method.invoke(source, args);
		}
		//��ע�⣬��������֣���ȡһ�����Ӳ���������
		Connection conn = null;
		Object returnVal = null;
		try{
			//�ӹ������л�ȡһ��������������ӣ���֤�߳�Ψһ��
			conn = DataSourceUtil.getConnNonTrans();
			conn.setAutoCommit(false);
			//����ԭ���ķ���ִ��
			returnVal = method.invoke(source, args);
			//�ύ
			System.out.println(">>>���óɹ����ύ����");
			conn.commit();
		} catch(Exception e){
			//�����쳣����ع�
			System.out.println(">>>�����쳣���ع�����");
			conn.rollback();
			throw e;
		} finally{
			//������Σ���Ҫ�ر�����
			conn.close();
			//��ThreadLocal���Ƴ�����
			DataSourceUtil.remove();
		}
		return returnVal;
	}

}
