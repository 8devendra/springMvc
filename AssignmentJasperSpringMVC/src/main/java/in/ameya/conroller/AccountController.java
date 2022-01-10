package in.ameya.conroller;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import in.ameya.model.Account;
import in.ameya.service.AccountService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	public AccountService service;
	
	@GetMapping("/welcome")
	public String home() {
		return "account";
	}
	@GetMapping("/all")
	public String findAllCustomer(Model m) throws JRException {
		List<Account> findAll=service.getAllAccount();
		
		String jrxmlFilePath="C:\\Users\\Devendra Haldankar\\JaspersoftWorkspace\\MyReports\\AA.jrxml";
		String outputFile="AccountReport.pdf";
		JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(findAll);
		
		Map<String , Object> parameters =new HashMap<String,Object>();
		
		parameters.put("TableData",dataSource);

		JasperReport compileReport = JasperCompileManager.compileReport(jrxmlFilePath);
		JasperPrint fillReport = JasperFillManager.fillReport(compileReport, parameters,dataSource);
		JasperExportManager.exportReportToPdfFile(fillReport,outputFile);
		byte[] output = JasperExportManager.exportReportToPdf(fillReport);
		
		
		m.addAttribute("accounts",findAll);
		return "account";
		
	}
	
	

}
