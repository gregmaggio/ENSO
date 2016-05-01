/**
 * 
 */
package ca.datamagic.enso.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author Greg
 *
 */
public class BaseDAO {
	private static Logger _logger = LogManager.getLogger(BaseDAO.class);
	private static String _dataPath = "C:/Dev/Applications/ENSO/src/main/resources/META-INF/data";

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Throwable t) {
			_logger.error("Exception", t);
		}
	}
	
	public BaseDAO() {
	}
	
	public static String getDataPath() {
		return _dataPath;
	}
	
	public static void setDataPath(String newVal) {
		_dataPath = newVal;
	}
	
	public static Connection openConnection(String fileName) throws SQLException {
		String connnectionString = MessageFormat.format("jdbc:sqlite:{0}/{1}", _dataPath, fileName);
		return DriverManager.getConnection(connnectionString);
	}
}
