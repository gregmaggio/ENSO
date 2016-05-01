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

import ca.datamagic.enso.dto.ENSOBucketDTO;
import ca.datamagic.enso.dto.ENSORangeDTO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Greg
 *
 */
public class ENSOBucketDAOTester {
	private static Logger _logger = LogManager.getLogger(ENSOBucketDAOTester.class);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DOMConfigurator.configure("src/test/resources/META-INF/log4j.cfg.xml");
		String dataPath = (new File("src/main/resources/META-INF/data")).getCanonicalPath();
		BaseDAO.setDataPath(dataPath);
	}

	@Test
	public void allENSOBucketTest() throws JsonProcessingException, SQLException {
		ENSOBucketDAO dao = new ENSOBucketDAO();
		List<ENSOBucketDTO> all = dao.getAll();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(all);
		_logger.debug(json);
	}

	@Test
	public void ENSOBucket2016Test() throws JsonProcessingException, SQLException {
		ENSOBucketDAO dao = new ENSOBucketDAO();
		List<ENSOBucketDTO> all = dao.getAll(2016);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(all);
		_logger.debug(json);
	}
	
	@Test
	public void ENSORangeTest() throws JsonProcessingException, SQLException {
		ENSOBucketDAO dao = new ENSOBucketDAO();
		List<ENSORangeDTO> ranges = dao.getRanges();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(ranges);
		_logger.debug(json);
	}
}
