package leibniz.hu.book;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import leibniz.hu.utils.DataSourceUtil;

public class BookDAO {

	public List<Book> queryByCategory(String categoryId) {
		QueryRunner  qRun = new QueryRunner(DataSourceUtil.getDataSource());
		String sql = null;
		List<Book> lBooks = null;
		
		try {
			if(null == categoryId){
				//���û����category ID������Ϊnull�������ѯ����ͼ�飬���ܷ������
				sql = "select * from books";
				lBooks = qRun.query(sql, new BeanListHandler<Book>(Book.class));
			} else {
				//���������category ID���򰴴�ID���в�ѯ
				sql = "select * from books b "+ 
						"inner join bookcategory bc on b.id = bc.bookid " +
						"where bc.categoryid = ?";
				lBooks = qRun.query(sql, new BeanListHandler<Book>(Book.class), categoryId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lBooks;
	}

}
