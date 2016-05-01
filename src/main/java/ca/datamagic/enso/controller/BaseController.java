/**
 * 
 */
package ca.datamagic.enso.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import ca.datamagic.enso.dao.BaseDAO;

/**
 * @author Greg
 *
 */
public abstract class BaseController {
	private static Logger _logger = LogManager.getLogger(BaseController.class);
	
	static {
		try {
			DefaultResourceLoader loader = new DefaultResourceLoader();       
		    Resource resource = loader.getResource("classpath:META-INF/data");
		    String dataPath = resource.getFile().getAbsolutePath();
		    _logger.debug("dataPath: " + dataPath);
		    BaseDAO.setDataPath(dataPath);
		} catch (Throwable t) {
			_logger.error("Exception", t);
		}
	}

}
