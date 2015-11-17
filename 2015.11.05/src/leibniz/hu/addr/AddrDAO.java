package leibniz.hu.addr;

import java.util.List;

import leibniz.hu.utils.DataSourceUtil;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class AddrDAO {

	public List<Address> queryByUserid(String uid) {
		String sql = "select * from address where uid = ?";
		QueryRunner qRun = new QueryRunner(DataSourceUtil.getDataSource());
		List<Address> addrList = null;
		try{
			addrList = qRun.query(sql, new BeanListHandler<Address>(Address.class), uid);
		} catch(Exception e){
		}
		return addrList;
	}

	public Address queryByAddrid(String addrid) {
		String sql = "select * from address where id = ?";
		QueryRunner qRun = new QueryRunner(DataSourceUtil.getDataSource());
		Address addr = null;
		try{
			addr = qRun.query(sql, new BeanHandler<Address>(Address.class), addrid);
		} catch(Exception e){
		}
		return addr;
	}

}
