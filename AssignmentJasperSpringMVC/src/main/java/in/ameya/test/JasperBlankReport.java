package in.ameya.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class JasperBlankReport {
	static void blankReport() {
		try {
			String filePath = "C:\\Users\\Devendra Haldankar\\JaspersoftWorkspace\\MyReports\\Blank_A4.jrxml";
			JasperReport compileReport = JasperCompileManager.compileReport(filePath);
			//JasperPrint fillReport = JasperFillManager.fillReport(compileReport, null);
			
			JasperPrint fillReport=JasperFillManager.fillReport(compileReport, null, new JREmptyDataSource());
			
			File file=new File("C:\\JasperReports\\MyTestReport.pdf");
			OutputStream outputStream=new FileOutputStream(file);
			JasperExportManager.exportReportToPdfStream(fillReport, outputStream);
			
			

//			byte[] exportReportToPdf = JasperExportManager.exportReportToPdf(fillReport);
//			HttpHeaders headers = new HttpHeaders();
//			// headers.setContentType(MediaType.application_);
//			ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(exportReportToPdf, HttpStatus.OK);
			//return entity;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Report Create Error" + e.getMessage());
		}
	}

}
