package base.zfarming.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import base.zfarming.db.DBConn;

public class BaseDao {

	private Connection connection;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public BaseDao() {
		connection = DBConn.getConn();
	}

	/**
	 * 查询分页条数等
	 * 
	 * @param sql
	 * @return
	 */
	public long getLong(String sql) {
		Long longResult = 0l;
		Map<String, Object> map = this.get(sql);
		Set<Entry<String, Object>> es = map.entrySet();
		Iterator<Entry<String, Object>> iterator = es.iterator();
		if (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			longResult = Long.valueOf(entry.getValue() + "");
		}
		return longResult;
	}

	/**
	 * 查询某一条记录的某一个字段
	 * 
	 * @param sql
	 * @return
	 */
	public Object getObject(String sql) {
		Object objResult = 0l;
		Map<String, Object> map = this.get(sql);
		Set<Entry<String, Object>> es = map.entrySet();
		Iterator<Entry<String, Object>> iterator = es.iterator();
		if (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			objResult = entry.getValue();
		}
		return objResult;
	}

	/**
	 * 取一条记录（通常按主键获取实体）
	 * 
	 * @param sql
	 * @return
	 */
	public Map<String, Object> get(String sql) {
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();

			ResultSetMetaData rsmd = ps.getMetaData();
			// 取得结果集列数
			int columnCount = rsmd.getColumnCount();

			Map<String, Object> data = new HashMap<String, Object>();
			if (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					data.put(rsmd.getColumnLabel(i),
							rs.getObject(rsmd.getColumnLabel(i)));
				}
			}
			return data;
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 取记录列表
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> getList(String sql) {
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();

			ResultSetMetaData rsmd = ps.getMetaData();
			// 取得结果集列数
			int columnCount = rsmd.getColumnCount();

			List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
			Map<String, Object> data = null;
			while (rs.next()) {
				data = new HashMap<String, Object>();
				// 每循环一条将列名和列值存入Map
				for (int i = 1; i <= columnCount; i++) {
					data.put(rsmd.getColumnLabel(i),
							rs.getObject(rsmd.getColumnLabel(i)));
				}
				// 将整条数据的Map存入到List中
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 插入（返回主键）
	 * @param sql
	 * @return
	 */
	public long insert(String sql) {
		try {
			ps = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				long id = rs.getLong(1);
				return id;
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 更新、删除操作（返回受影响的行数）
	 * 
	 * @param sql
	 * @return
	 */
	public int updateOrDelete(String sql) {
		try {
			ps = connection.prepareStatement(sql);
			return ps.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// BaseDao baseDao = new BaseDao();
		/*
		 * baseDao.updateOrDelete(
		 * "update sys_user set username='xiaolee' where id=3");
		 * baseDao.updateOrDelete("delete from sys_user where id=2");
		 */
		/*
		 * //���� long id = baseDao.insert(
		 * "insert into sys_user(username,password) values('xiaoliu','qqqq')");
		 * System.out.println("����ID="+id);
		 */
		/*
		 * baseDao.getObject("select username from sys_user where id=1");
		 */
		/*
		 * baseDao.getLong("select count(*) from sys_user");
		 */
		/*
		 * Map<String, Object> data =
		 * baseDao.get("select username from sys_user"); Set<Entry<String,
		 * Object>> es = data.entrySet(); Iterator<Entry<String, Object>>
		 * iterator = es.iterator(); while(iterator.hasNext()){ Entry<String,
		 * Object> entry = iterator.next(); System.out.println(entry.getKey()
		 * +":"+entry.getValue()); }
		 */
		/*
		 * List<Map<String, Object>> datas =
		 * baseDao.getList("select * from sys_user"); for (int i = 0; i <
		 * datas.size(); i++) { Map<String, Object> map = datas.get(i);
		 * Set<Entry<String, Object>> es = map.entrySet();
		 * Iterator<Entry<String, Object>> iterator = es.iterator();
		 * while(iterator.hasNext()){ Entry<String, Object> entry =
		 * iterator.next(); System.out.println(entry.getKey()
		 * +":"+entry.getValue()); } }
		 */
	}
}
