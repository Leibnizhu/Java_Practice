package leibniz.hu.category;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import leibniz.hu.utils.DataSourceUtil;

public class CategoryDAO {

	public List<Category> queryAll() {
		String sql = "select * from category";
		QueryRunner qRun = new QueryRunner(DataSourceUtil.getDataSource());
		List<Category> lCategory = null;
		try {
			lCategory = qRun.query(sql, new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lCategory;
	}

}
