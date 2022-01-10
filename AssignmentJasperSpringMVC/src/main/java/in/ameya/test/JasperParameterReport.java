package in.ameya.test;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class JasperParameterReport {

	//C:\Users\Devendra Haldankar\JaspersoftWorkspace\MyReports\Simple_Blue.jrxml
	public static void parRep() throws JRException {
		String filePath = "C:\\Users\\Devendra Haldankar\\JaspersoftWorkspace\\MyReports\\Simple_Blue.jrxml";

///		String filePath = "C:\\Users\\Devendra Haldankar\\JaspersoftWorkspace\\MyReports\\Employees.jrxml";
		Map<String , Object> parameters=new HashMap<String,Object>();
		parameters.put("CompanyName","Ameya");


		JasperReport compileReport=JasperCompileManager.compileReport(filePath);
		JRElement elementByKey = compileReport.getTitle().getElementByKey("myTitle");
		//elementByKey.setForecolor(Color.magenta);
		
		
		JasperPrint filRep= JasperFillManager.fillReport(compileReport, parameters,new JREmptyDataSource());
		JasperExportManager.exportReportToPdfFile(filRep,"C:\\JasperReports\\MyTestReport.pdf");
	}

}
