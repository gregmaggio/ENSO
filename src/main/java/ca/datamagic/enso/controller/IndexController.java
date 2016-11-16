/**
 * 
 */
package ca.datamagic.enso.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Injector;

import ca.datamagic.enso.dao.BaseDAO;
import ca.datamagic.enso.dao.ENSOBucketDAO;
import ca.datamagic.enso.dto.CachedItemDTO;
import ca.datamagic.enso.dto.ENSOBucketDTO;
import ca.datamagic.enso.dto.ENSORangeDTO;
import ca.datamagic.enso.dto.SwaggerConfigurationDTO;
import ca.datamagic.enso.dto.SwaggerResourceDTO;
import ca.datamagic.enso.inject.DAOModule;
import ca.datamagic.enso.inject.MemoryCacheInterceptor;

/**
 * @author Greg
 *
 */
@Controller
@RequestMapping(path = "")
public class IndexController {
	private static Logger _logger = LogManager.getLogger(IndexController.class);
	private static Injector _injector = null;
	private static ENSOBucketDAO _dao = null;
	private static SwaggerConfigurationDTO _swaggerConfiguration = null;
	private static SwaggerResourceDTO[] _swaggerResources = null;
	private static String _swagger = null;
	
	static {
		FileInputStream swaggerStream = null;
		try {
			DefaultResourceLoader loader = new DefaultResourceLoader();       
		    Resource dataResource = loader.getResource("classpath:data");
		    Resource metaInfResource = loader.getResource("META-INF");
		    String dataPath = dataResource.getFile().getAbsolutePath();
		    String metaInfPath = metaInfResource.getFile().getAbsolutePath();
		    _logger.debug("dataPath: " + dataPath);
		    _logger.debug("metaInfPath: " + metaInfPath);
		    
		    String swaggerFileName = MessageFormat.format("{0}/swagger.json", metaInfPath);
		    swaggerStream = new FileInputStream(swaggerFileName);
		    _swagger = IOUtils.toString(swaggerStream, "UTF-8");
		    
		    BaseDAO.setDataPath(dataPath);
			_injector = Guice.createInjector(new DAOModule());
			_dao = _injector.getInstance(ENSOBucketDAO.class);
			_swaggerConfiguration = new SwaggerConfigurationDTO();
			_swaggerResources = new SwaggerResourceDTO[] { new SwaggerResourceDTO() };
		} catch (Throwable t) {
			_logger.error("Exception", t);
		}
		if (swaggerStream != null) {
			IOUtils.closeQuietly(swaggerStream);
		}
	}
	
	public IndexController() {
	}
	
	@RequestMapping(value="/api/ranges", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<ENSORangeDTO> ranges(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException {
		try {
			return _dao.getRanges();
		} catch (SQLException ex) {
			_logger.error("Exception", ex);
			throw ex;
		}
	}
	
	@RequestMapping(value="/api/years", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<Integer> years(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException {
		try {
			return _dao.getYears();
		} catch (SQLException ex) {
			_logger.error("Exception", ex);
			throw ex;
		}
	}
	
	@RequestMapping(value="/api/{year}", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<ENSOBucketDTO> bucketsByYear(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable Integer year) throws SQLException {
		try {
			return _dao.getAll(year);
		} catch (SQLException ex) {
			_logger.error("Exception", ex);
			throw ex;
		}
	}
	
	@RequestMapping(value="/api/buckets", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<ENSOBucketDTO> buckets(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException {
		try {
			return _dao.getAll();
		} catch (SQLException ex) {
			_logger.error("Exception", ex);
			throw ex;
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/api/cache", produces="application/json")
	@ResponseBody
	public List<CachedItemDTO> getCachedItems() throws JsonProcessingException {
		List<CachedItemDTO> items = new ArrayList<CachedItemDTO>();
		ObjectMapper mapper = new ObjectMapper();
		Enumeration<String> keys = MemoryCacheInterceptor.getKeys();
		if (keys != null) {
			while (keys.hasMoreElements()) {
				String key = keys.nextElement();
				Object value = MemoryCacheInterceptor.getValue(key);
				String json = mapper.writeValueAsString(value);
				CachedItemDTO dto = new CachedItemDTO();
				dto.setKey(key);
				dto.setJson(json);
				items.add(dto);
			}
		}
		return items;
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/api/cache")
	public void clearCachedItems() {
		MemoryCacheInterceptor.clearCache();
	}
	
	@RequestMapping(value="/swagger-resources/configuration/ui", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public SwaggerConfigurationDTO getSwaggerConfigurationUI() {
		return _swaggerConfiguration;
	}
	
	@RequestMapping(value="/swagger-resources", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public SwaggerResourceDTO[] getSwaggerResources() {
		return _swaggerResources;
	}
	
	@RequestMapping(value="/v2/api-docs", method=RequestMethod.GET, produces="application/json")
	public void getSwagger(Writer responseWriter) throws IOException {
		responseWriter.write(_swagger);
	}
}
