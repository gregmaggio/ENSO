/**
 * 
 */
package ca.datamagic.enso.servlet;

import java.text.MessageFormat;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.google.inject.Guice;
import com.google.inject.Injector;

import ca.datamagic.enso.dao.BaseDAO;
import ca.datamagic.enso.dao.ENSOBucketDAO;
import ca.datamagic.enso.inject.DAOModule;

/**
 * @author Greg
 *
 */
public class ENSOContextListener implements ServletContextListener {
	private static Logger logger = LogManager.getLogger(ENSOContextListener.class);
	private static Injector injector = null;
	private static ENSOBucketDAO ensoBucketDAO = null;
	
	public static ENSOBucketDAO getENSOBucketDAO() {
		return ensoBucketDAO;
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String realPath = sce.getServletContext().getRealPath("/");
		String fileName = MessageFormat.format("{0}/WEB-INF/classes/log4j.cfg.xml", realPath);
		String dataPath = MessageFormat.format("{0}/WEB-INF/classes/data", realPath);
		DOMConfigurator.configure(fileName);
		BaseDAO.setDataPath(dataPath);
		injector = Guice.createInjector(new DAOModule());
		ensoBucketDAO = injector.getInstance(ENSOBucketDAO.class);
		logger.debug("contextInitialized");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.debug("contextDestroyed");
	}
}
