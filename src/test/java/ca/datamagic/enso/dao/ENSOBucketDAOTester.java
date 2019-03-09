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

import ca.datamagic.enso.dto.ENSOBucketDTO;
import ca.datamagic.enso.dto.ENSORangeDTO;

import com.google.gson.Gson;

/**
 * @author Greg
 *
 */
public class ENSOBucketDAOTester {
	private static Logger _logger = LogManager.getLogger(ENSOBucketDAOTester.class);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DOMConfigurator.configure("src/test/resources/log4j.cfg.xml");
		String dataPath = (new File("src/main/resources/data")).getCanonicalPath();
		BaseDAO.setDataPath(dataPath);
	}

	@Test
	public void allENSOBucketTest() throws Exception {
		ENSOBucketDAO dao = new ENSOBucketDAO();
		List<ENSOBucketDTO> all = dao.getAll();
		Gson gson = new Gson();
		String json = gson.toJson(all);
		_logger.debug(json);
	}

	@Test
	public void ENSOBucket2016Test() throws Exception {
		ENSOBucketDAO dao = new ENSOBucketDAO();
		List<ENSOBucketDTO> all = dao.getAll(2016);
		Gson gson = new Gson();
		String json = gson.toJson(all);
		_logger.debug(json);
	}
	
	@Test
	public void ENSORangeTest() throws Exception {
		ENSOBucketDAO dao = new ENSOBucketDAO();
		List<ENSORangeDTO> ranges = dao.getRanges();
		Gson gson = new Gson();
		String json = gson.toJson(ranges);
		_logger.debug(json);
	}
}
