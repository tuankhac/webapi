package com.neo.api.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.CallableStatement;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.neo.api.model.ObjectId;
import com.neo.api.utils.ConstantParams;
import com.neo.api.utils.TrippleDes;

import oracle.jdbc.OracleTypes;

import com.neo.api.utils.ImageResizer;

@Service
// @CacheConfig(cacheNames={"users"}) // tells Spring where to store
public class ObjectService {
	private DataSource dataSource;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ObjectId login(String username, String password, String sql) {
		ObjectId objectId = null;
		Connection conn = null;
		CallableStatement ps = null;
		ResultSet rs = null;
		String encodePassword = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareCall(sql);
			ps.setObject(1, username);

			rs = ps.executeQuery();
			rs.next();
			String pass = rs.getString("PASSWORD");
			objectId = new ObjectId(username, pass);

			// encodePassword = MD5.encrypt(password);
			encodePassword = password;
			System.out.println(
					"encodePassword====>" + encodePassword + "|username ===>" + username + "|pass:=====>" + pass);
			if (objectId.getPassword().equals(encodePassword)) {
				System.out.println("chay vao day");
				return objectId;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public synchronized List<Map<Object, Object>> qry(Map<Object, Object> params, String sql, String... order) {
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		Connection conn = null;
		CallableStatement ps = null;
		Map<Object, Object> item = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareCall(sql);
			int i = 1;
			for (String s : order) {
				ps.setObject(i, params.get(s));
				i++;
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				item = new HashMap<>();
				for (i = 0; i < rs.getMetaData().getColumnCount(); i++) {
					item.put(rs.getMetaData().getColumnLabel(i + 1),
							rs.getObject(rs.getMetaData().getColumnLabel(i + 1)));
				}
				list.add(item);
			}
			rs.close();
			return list;
		} catch (SQLException e) {
			Logger.getLogger("ws-error")
					.error(params.toString() + "|SQL: ========>" + sql + "| Exception: ===========>" + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public synchronized List<Map<Object, Object>> ref(Map<Object, Object> params, String sql, String... order) {
		// simulateSlowService();
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		Connection conn = null;
		CallableStatement ps = null;
		Map<Object, Object> item = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareCall(sql);
			ps.registerOutParameter(1, OracleTypes.CURSOR);
			int i = 2;
			for (String s : order) {
				ps.setObject(i, params.get(s));
				i++;
			}
			ps.execute();
			rs = (ResultSet) ps.getObject(1);
			while (rs.next()) {
				item = new HashMap<>();
				for (i = 0; i < rs.getMetaData().getColumnCount(); i++) {
					item.put(rs.getMetaData().getColumnLabel(i + 1),
							rs.getObject(rs.getMetaData().getColumnLabel(i + 1)));
				}
				list.add(item);
			}
			rs.close();
			return list;
		} catch (SQLException e) {
			Logger.getLogger("ws-error")
					.error(params.toString() + "|SQL: ========>" + sql + "| Exception: ===========>" + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public synchronized Object rej(Map<Object, Object> params, String sql, String... order) {
		// simulateSlowService();
		Object result = null;
		Connection conn = null;
		CallableStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareCall(sql);
			int i = 1;
			for (String s : order) {
				ps.setObject(i, params.get(s));
				i++;
			}
			ps.execute();
			rs = ps.getResultSet();
			rs.next();
			result = rs.getObject(1);
			rs.close();
			return result;
		} catch (SQLException e) {
			Logger.getLogger("ws-error")
					.error(params.toString() + "|SQL: ========>" + sql + "| Exception: ===========>" + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	// private void simulateSlowService() {
	// try {
	// Thread.sleep(3000L);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// }

	public synchronized Object val(Map<Object, Object> params, String sql, String... order) {
		Object result = null;
		Connection conn = null;
		CallableStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareCall(sql);
			int i = 1;
			for (String s : order) {
				ps.setObject(i, params.get(s));
				i++;
			}
			ps.execute();
			rs = ps.getResultSet();
			rs.next();
			result = rs.getObject(1);
			rs.close();
			return result;
		} catch (SQLException e) {
			Logger.getLogger("ws-error")
					.error(params.toString() + "|SQL: ========>" + sql + "| Exception: ===========>" + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public synchronized Object update(Map<Object, Object> params, String sql, String... order) {
		Object result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			int i = 1;
			for (String s : order) {
				ps.setObject(i, params.get(s));
				i++;
			}
			result = ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			Logger.getLogger("ws-error")
					.error(params.toString() + "|SQL: ========>" + sql + "| Exception: ===========>" + e.getMessage());
			e.printStackTrace();
			result = e.getMessage();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
		}
		return result;
	}

	public synchronized void uploads(HttpServletRequest request, MultipartFile[] photos, Map<Object, Object> params) {
		// decode duong dan
		String path = TrippleDes.decrypt(params.get(ConstantParams.SECRET_KEY_PATH).toString());

		// Tạo file tại Server.
		String uploadPath = request.getServletContext().getRealPath(path);
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		for (int i = 0; i < photos.length; i++) {
			String[] fileName = photos[i].getOriginalFilename().split(ConstantParams.SPLIT_FILE_CHARACTER, -1);
			String fileNameFinal = fileName[0] + params.get("suffix_file") + "." + fileName[1];
			try {
				File file = new File(uploadDir + File.separator + fileNameFinal);
				String mimetype = new MimetypesFileTypeMap().getContentType(file);
				String type = mimetype.split("/")[0];

				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
				byte[] data = null;
				if (type.equals("image")) {
					data = ImageResizer.scale(photos[i].getBytes(), (int) params.get("resize"), 0);
				} else {
					data = photos[i].getBytes();
				}
				stream.write(data);
				stream.close();

			} catch (IllegalStateException e) {
				Logger.getLogger("ws-error").error(params.toString() + "| Exception: ===========>" + e.getMessage());
				e.printStackTrace();
			} catch (IOException ie) {
				Logger.getLogger("sql").info(params.toString());
				ie.printStackTrace();
			}
		}
	}

}