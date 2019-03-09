/**
 * 
 */
package ca.datamagic.enso.dao;

import java.io.File;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

import ca.datamagic.enso.dto.ONIDTO;

/**
 * @author Greg
 *
 */
public class ONIDAOTester {
	private static Logger _logger = LogManager.getLogger(ONIDAOTester.class);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DOMConfigurator.configure("src/test/resources/log4j.cfg.xml");
		String dataPath = (new File("src/main/resources/data")).getCanonicalPath();
		BaseDAO.setDataPath(dataPath);
	}

	@Test
	public void allONITest() throws Exception {
		ONIDAO dao = new ONIDAO();
		List<ONIDTO> all = dao.getAll();
		Gson gson = new Gson();
		String json = gson.toJson(all);
		_logger.debug(json);
	}

}
