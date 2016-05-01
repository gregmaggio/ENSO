/**
 * 
 */
package ca.datamagic.enso.importer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.xml.DOMConfigurator;

import ca.datamagic.enso.dao.ONIDAO;
import ca.datamagic.enso.dto.ONIDTO;

/**
 * @author Greg
 *
 */
public class Importer {
	public static List<ONI> parse(String fileName, boolean header) throws IOException {
		FileInputStream inputStream = null;		
		try {
			inputStream = new FileInputStream(fileName);
			return parse(inputStream, header);
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}
	
	public static List<ONI> parse(InputStream inputStream, boolean header) {
		Scanner inputScanner = null;
		try {
			List<ONI> onis = new ArrayList<ONI>();
			inputScanner = new Scanner(inputStream);
			int lineNo = 0;
			while (inputScanner.hasNextLine()) {
				if ((lineNo++ == 0) && !header) {
					continue;
				}
				String currentLine = inputScanner.nextLine();
				ONI oni = ONI.getONI(currentLine);
				if (oni != null) {
					onis.add(oni);
				}
			}
			return onis;
		} finally {
			if (inputScanner != null) {
				inputScanner.close();
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			DOMConfigurator.configure("C:/Dev/Applications/ENSO/src/main/resources/META-INF/log4j.importer.cfg.xml");
			String fileName = "C:/Data/ENSO/detrend.nino34.ascii.txt";
			List<ONI> onis = parse(fileName, true);
			ONIDAO dao = new ONIDAO();
			dao.clear();
			for (ONI oni : onis) {
				ONIDTO dto = new ONIDTO();
				dto.setYear(oni.getYear());
				dto.setMonth(oni.getMonth());
				dto.setAnom(oni.getAnom());
				dao.save(dto);
			}
		} catch (Throwable t){
			System.out.println("Exception: " + t.getMessage());
			t.printStackTrace();
		}
	}
}
