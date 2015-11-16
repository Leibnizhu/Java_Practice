package leibniz.hu.addr;

import java.util.List;

import leibniz.hu.utils.DataSourceUtil;

import org.apache.commons.dbutils.QueryRunner;
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

}
