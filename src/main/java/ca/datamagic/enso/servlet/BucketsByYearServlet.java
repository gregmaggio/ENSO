/**
 * 
 */
package ca.datamagic.enso.servlet;

import java.io.IOError;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.Gson;

import ca.datamagic.enso.dto.ENSOBucketDTO;

/**
 * @author Greg
 *
 */
public class BucketsByYearServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(BucketsByYearServlet.class);

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			///api/{year}
			List<ENSOBucketDTO> buckets = ENSOContextListener.getENSOBucketDAO().getAll();
			String json = (new Gson()).toJson(buckets);
			response.setContentType("application/json");
			response.getWriter().println(json);
		} catch (Throwable t) {
			logger.error("Exception", t);
			throw new IOError(t);
		}
	}
}
