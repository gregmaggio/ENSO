/**
 * 
 */
package ca.datamagic.enso.dao;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ca.datamagic.enso.dto.ONIDTO;

/**
 * @author Greg
 *
 */
public class ONIDAOTester {
	private static Logger _logger = LogManager.getLogger(ONIDAOTester.class);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DOMConfigurator.configure("src/test/resources/META-INF/log4j.cfg.xml");
		String dataPath = (new File("src/main/resources/META-INF/data")).getCanonicalPath();
		BaseDAO.setDataPath(dataPath);
	}

	@Test
	public void allONITest() throws JsonProcessingException, SQLException {
		ONIDAO dao = new ONIDAO();
		List<ONIDTO> all = dao.getAll();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(all);
		_logger.debug(json);
	}

}
