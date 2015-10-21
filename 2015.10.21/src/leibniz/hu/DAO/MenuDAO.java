package leibniz.hu.DAO;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import leibniz.hu.domain.Menu;
import leibniz.hu.utils.DataSourceUtil;

public class MenuDAO {

	/**
	 * @param uid
	 * @return
	 * ��ָ����User ID��ͨ��innner join �����Ӳ�ѯ���ݣ���ö�Ӧ������Menu��Ϣ
	 * ����BeanListHandler��װ��List<Menu>����
	 */
	public List<Menu> query(String uid) {
		String sql = "SELECT m.* FROM userrole ru " + 
					" INNER JOIN roles r ON ru.rid=r.id" + 
					" INNER JOIN rolemenu rm ON r.id=rm.rid" +
					" INNER JOIN menus m ON rm.mid=m.id" +
					" WHERE ru.uid=?";
		QueryRunner qRun = new QueryRunner(DataSourceUtil.getDataSource());
		try {
			List<Menu> lsMenu = qRun.query(sql, new BeanListHandler<Menu>(Menu.class), uid);
			return lsMenu;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
