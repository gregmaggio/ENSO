/**
 * 
 */
package ca.datamagic.enso.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import ca.datamagic.enso.dao.ENSOBucketDAO;
import ca.datamagic.enso.dto.CachedItemDTO;
import ca.datamagic.enso.dto.ENSOBucketDTO;
import ca.datamagic.enso.dto.ENSORangeDTO;
import ca.datamagic.enso.inject.DAOModule;
import ca.datamagic.enso.inject.MemoryCacheInterceptor;

/**
 * @author Greg
 *
 */
@Api(value="enso", description="ENSO operations")
@Controller
@RequestMapping("")
public class ENSOController extends BaseController {
	private static Logger _logger = LogManager.getLogger(ENSOController.class);
	private ENSOBucketDAO _dao = null;
	
	public ENSOController() {
		Injector injector = Guice.createInjector(new DAOModule());
		_dao = injector.getInstance(ENSOBucketDAO.class);
	}

	@ApiOperation(value = "Get all the ENSO ranges")
	@RequestMapping(value="/ranges", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<ENSORangeDTO> ranges(@ApiIgnore Model model, @ApiIgnore HttpServletRequest request, @ApiIgnore HttpServletResponse response) throws SQLException {
		try {
			return _dao.getRanges();
		} catch (SQLException ex) {
			_logger.error("Exception", ex);
			throw ex;
		}
	}
	
	@ApiOperation(value = "Get the years in the ENSO database")
	@RequestMapping(value="/years", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<Integer> years(@ApiIgnore Model model, @ApiIgnore HttpServletRequest request, @ApiIgnore HttpServletResponse response) throws SQLException {
		try {
			return _dao.getYears();
		} catch (SQLException ex) {
			_logger.error("Exception", ex);
			throw ex;
		}
	}
	
	@ApiOperation(value = "Get the ENSO buckets for a year")
	@RequestMapping(value="/{year}", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<ENSOBucketDTO> bucketsByYear(@ApiIgnore Model model, @ApiIgnore HttpServletRequest request, @ApiIgnore HttpServletResponse response, @ApiParam(name="year", value="The year", required=true) @PathVariable Integer year) throws SQLException {
		try {
			return _dao.getAll(year);
		} catch (SQLException ex) {
			_logger.error("Exception", ex);
			throw ex;
		}
	}
	
	@ApiOperation(value = "Get the ENSO buckets")
	@RequestMapping(value="/buckets", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<ENSOBucketDTO> buckets(@ApiIgnore Model model, @ApiIgnore HttpServletRequest request, @ApiIgnore HttpServletResponse response) throws SQLException {
		try {
			return _dao.getAll();
		} catch (SQLException ex) {
			_logger.error("Exception", ex);
			throw ex;
		}
	}
	
	@ApiOperation(value = "Returned the cached items")
	@RequestMapping(method=RequestMethod.GET, value="/cache", produces="application/json")
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
	
	@ApiOperation(value = "Clear the cache")
	@RequestMapping(method=RequestMethod.DELETE, value="/cache")
	public void clearCachedItems() {
		MemoryCacheInterceptor.clearCache();
	}
	
	@ApiIgnore
	@RequestMapping(method=RequestMethod.GET, value="")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "index";
	}
}
