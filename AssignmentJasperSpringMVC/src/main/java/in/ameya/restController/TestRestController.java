package in.ameya.restController;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ameya.model.Account;
import in.ameya.service.AccountService;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@RestController
public class TestRestController {	
	@RequestMapping("/welcome")
	public String welcome() {
		return "Welcome";
	}

	@RequestMapping("/report")
	public ResponseEntity<byte[]> getReport() {
		try {
			String filePath = "C:\\Users\\Devendra Haldankar\\JaspersoftWorkspace\\MyReports\\Blank_A4.jrxml";
			JasperReport compileReport = JasperCompileManager.compileReport(filePath);
			JasperPrint fillReport = JasperFillManager.fillReport(compileReport, null);

			byte[] exportReportToPdf = JasperExportManager.exportReportToPdf(fillReport);
			HttpHeaders headers = new HttpHeaders();
			// headers.setContentType(MediaType.application_);
			ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(exportReportToPdf, HttpStatus.OK);
			return entity;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Report Create Error" + e.getMessage());
		}

		return null;
	}
	
//	@RequestMapping("/all")
//	public List<Account> getAllAccount(){
//		System.out.println("All Accounts:Controller");
//		List<Account> ac=service.getAllAccount();
//		return ac;
//	}
//
//	@RequestMapping("/a")
//	public String getAc(){
//		System.out.println("All Accounts:Controller");
//		List<Account> ac=service.getAllAccount();
//		return "av";
//	}

	

}
