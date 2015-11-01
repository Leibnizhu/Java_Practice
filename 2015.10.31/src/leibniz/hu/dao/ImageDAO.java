package leibniz.hu.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import leibniz.hu.domain.Image;
import leibniz.hu.utils.DataSourceUtil;

public class ImageDAO {

	public Image save(Image img) {
		QueryRunner qRun = new QueryRunner(DataSourceUtil.getDataSource());
		String sql = "insert into images(id, oldname, newname, ip, crdate, descript, tnwidth, tnheight) values(?,?,?,?,?,?,?,?)";
		try {
			qRun.update(sql, img.getId(), img.getOldname(), img.getNewname(), img.getIp(), img.getCrdate(), img.getDescript(), img.getTnwidth(), img.getTnheight());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return img;
	}

	public List<Image> queryAll() {
		QueryRunner qRun = new QueryRunner(DataSourceUtil.getDataSource());
		String sql = "select * from images";
		List<Image> lImg = null;
		try {
			lImg = qRun.query(sql, new BeanListHandler<Image>(Image.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lImg;
	}

	public Image load(String id) {
		QueryRunner qRun = new QueryRunner(DataSourceUtil.getDataSource());
		String sql = "select * from images where id=?";
		Image img = null;
		try {
			img = qRun.query(sql, new BeanHandler<Image>(Image.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return img;
	}

	public int delete(Image img) {
		QueryRunner qRun = new QueryRunner(DataSourceUtil.getDataSource());
		String sql = "delete from images where id=?";
		int count=0;
		try {
			 count = qRun.update(sql, img.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}
