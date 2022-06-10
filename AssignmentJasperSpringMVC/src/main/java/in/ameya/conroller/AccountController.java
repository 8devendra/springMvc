package in.ameya.conroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.PrintService;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ameya.model.Account;
import in.ameya.model.PrinterModal;
import in.ameya.model.WithBalance;
import in.ameya.service.AccountService;
import in.ameya.printer.Printer;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintJobAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintJobAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobMediaSheetsCompleted;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrinterName;
import javax.print.attribute.standard.Sides;
import javax.print.event.PrintJobAttributeEvent;
import javax.print.event.PrintJobAttributeListener;
import javax.print.event.PrintJobEvent;
import javax.print.event.PrintJobListener;
import javax.validation.Valid;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;

@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	public AccountService service;

	@GetMapping("/welcome")
	public String home(Model m) {
		m.addAttribute("client","hrcu");
		return "account";
	}

	@GetMapping("/all")
	public ResponseEntity<byte[]> findAllCustomer(Model m) throws JRException {
		List<Account> findAll = service.getAllAccount();
		Account newObject=new Account();
		newObject.setAccountNo(11114);
		newObject.setBalance(4320.32F);
		findAll.add(0,newObject);
		
		
		//String jrxmlFilePath = "C:\\Users\\Devendra Haldankar\\JaspersoftWorkspace\\MyReports\\AA.jrxml";
		String jrxmlFilePath = "C:\\Users\\Devendra Haldankar\\JaspersoftWorkspace\\MyReports\\tblTest.jrxml";
		String outputFile = "AccountReport.pdf";
		JasperReport compileReport = JasperCompileManager.compileReport(jrxmlFilePath);
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(findAll);
		System.out.println("TEST");
		for (Account account : findAll) {
			System.out.println(account.getAccountNo());
			
		}

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("TableData", dataSource);

		

		JasperPrint fillReport = JasperFillManager.fillReport(compileReport, parameters, dataSource);
		JasperExportManager.exportReportToPdfFile(fillReport, outputFile);

		byte[] exportTOPdf = JasperExportManager.exportReportToPdf(fillReport);
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=Report.pdf");
		 //headers.set(HttpHeaders.CONTENT_DISPOSITION, "inlune;filename=Report.pdf");
		//headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=ReportNew.pdf"); // Download The file
		
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(exportTOPdf);
		// ResponseEntity<byte[]> entity=new ResponseEntity<byte[]>()

		// B//yteArrayOutputStream byteArray;
		// JasperRunManager.runReportToPdfStream(compileReport, byteArray,
		// parameters,dataSource );

		// m.addAttribute("accounts",findAll);
		// return "account";

	}
	
	@GetMapping("/type")
	public ResponseEntity<byte[]> getAccountType(Model m) throws JRException{
		List<Account> ac=service.getAllAccount();
		Account acc=new Account();
		acc.setAccountNo(0);
		acc.setBalance(0.0F);
		acc.setAccountType("");
		ac.add(0, acc);
		
		
		String jrxmlFilePath = "C:\\Users\\Devendra Haldankar\\JaspersoftWorkspace\\MyReports\\GroupReport.jrxml";
		String jrxmlFilePathSub = "C:\\Users\\Devendra Haldankar\\JaspersoftWorkspace\\MyReports\\grpSub1.jrxml";
		String outputFile = "AccountTypeReport.pdf";
		JasperReport compileReport = JasperCompileManager.compileReport(jrxmlFilePath);
		JasperReport compileSubReport = JasperCompileManager.compileReport(jrxmlFilePathSub);
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(ac);
			
		
		for (Account account : ac) {
			System.out.print(account.getAccountNo()+"\t");
			System.out.print(account.getBalance()+"\t");
			System.out.print(account.getAccountType()+"\n");
		}
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("TableDataGroup", dataSource);

		parameters.put("jasperSub", compileSubReport);
		JasperPrint fillReport = JasperFillManager.fillReport(compileReport, parameters, dataSource);
		JasperExportManager.exportReportToPdfFile(fillReport, outputFile);

		byte[] exportTOPdf = JasperExportManager.exportReportToPdf(fillReport);
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=Report.pdf");	
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(exportTOPdf);

	}
	
	@GetMapping("j")
	public  ResponseEntity<byte[]> getTest(Model m) throws JRException, SQLException, IOException{
		
		//String jrxmlFilePathJR="C:\\Users\\Devendra Haldankar\\JaspersoftWorkspace\\GroupJasper\\Main.jrxml";
		File jrxmlFilePathJR=ResourceUtils.getFile("classpath:Main.jrxml");
		
		//String jrxmlFilePathJR="C:\\Users\\Devendra Haldankar\\JaspersoftWorkspace\\GroupJasper\\Main.jrxml";
		//String jrxmlFilePathJR="C:\\Users\\Devendra Haldankar\\JaspersoftWorkspace\\GroupJasper\\dtlReport.jrxml";
		
		JasperReport compileReport = JasperCompileManager.compileReport(jrxmlFilePathJR.getAbsolutePath());
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("accountType", "FIX");
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankfair", "root", "");
		
		
		
		JasperPrint fillReport = JasperFillManager.fillReport(compileReport, parameters, con);
		JasperExportManager.exportReportToPdfFile(fillReport, "Applicaion.pdf");

		byte[] exportTOPdf = JasperExportManager.exportReportToPdf(fillReport);
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=Report.pdf");	
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(exportTOPdf);

		
		
		
		
		
	}
	
	
	@GetMapping("/showpdf")
	@ResponseBody
	public String welcome(Model m)throws Exception
	{
		File file = ResourceUtils.getFile("C:\\Users\\Devendra Haldankar\\JaspersoftWorkspace\\MyReports\\Simple_Blue.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		//JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource();

		Map<String, Object> parameters  = new HashMap<>();
		parameters.put("createdBy", "Sandeep");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

		//Create for PDF
		byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
		
		String str = new String(Base64.getEncoder().encode(b)).trim();
		System.out.println(str);
		m.addAttribute("image", str);
		return str;
	}

	@GetMapping("/addAccount")
	public String addAccount(Model m, @ModelAttribute("account") Account account) throws IOException {
		m.addAttribute("account", account);

		//// PRINTER CODE

		String pdf = "JVBERi0xLjUKJeLjz9MKMyAwIG9iago8PC9GaWx0ZXIvRmxhdGVEZWNvZGUvTGVuZ3RoIDMxMD4+c3RyZWFtCnicrVLLTsMwELz7K/ZYDhi/HV+RCiLiwMM3xCEiTihKimgTAX/POmkhhDaqANmOLWd2Zna9L+TUE2kgYQZ8TuaeXBMBabzlwHDEb6IE+JqcnHHgDHxBZkf+KWIRQBUuA6uS9DjtNBw7AatAigEFg4d6SCoY2MRQy7fEUkViFMCBZDN/4S/nUYZBGaVGVHf3uOed1f0iynKqOQopquxnBiOh5nGxBpwZNOGtgSIsqvwg3W8WXhGfkr4a/bo53yIwVW6ohprE4vTnitzuJOh0vkKxTMZtYq3enH/E7svfuC7CjF6vF4m5L9uq+k2NGXWMOwPjHTnRsZSgOXYBl30XDF/ECcodCDFh6iorA0Kfi392pqWO1hTb6Sz+nbQF/I/diJWZFPBtWOfZOzALabsMB6l9AGpcw0YKZW5kc3RyZWFtCmVuZG9iagoxIDAgb2JqCjw8L1RhYnMvUy9Hcm91cDw8L1MvVHJhbnNwYXJlbmN5L1R5cGUvR3JvdXAvQ1MvRGV2aWNlUkdCPj4vQ29udGVudHMgMyAwIFIvVHlwZS9QYWdlL1Jlc291cmNlczw8L0NvbG9yU3BhY2U8PC9DUy9EZXZpY2VSR0I+Pi9Qcm9jU2V0IFsvUERGIC9UZXh0IC9JbWFnZUIgL0ltYWdlQyAvSW1hZ2VJXS9Gb250PDwvRjEgMiAwIFI+Pj4+L1BhcmVudCA0IDAgUi9NZWRpYUJveFswIDAgNTk1IDg0Ml0+PgplbmRvYmoKNSAwIG9iagpbMSAwIFIvWFlaIDAgODUyIDBdCmVuZG9iagoyIDAgb2JqCjw8L1N1YnR5cGUvVHlwZTEvVHlwZS9Gb250L0Jhc2VGb250L0hlbHZldGljYS9FbmNvZGluZy9XaW5BbnNpRW5jb2Rpbmc+PgplbmRvYmoKNCAwIG9iago8PC9LaWRzWzEgMCBSXS9UeXBlL1BhZ2VzL0NvdW50IDEvSVRYVCgyLjEuNyk+PgplbmRvYmoKNiAwIG9iago8PC9OYW1lc1soSlJfUEFHRV9BTkNIT1JfMF8xKSA1IDAgUl0+PgplbmRvYmoKNyAwIG9iago8PC9EZXN0cyA2IDAgUj4+CmVuZG9iago4IDAgb2JqCjw8L05hbWVzIDcgMCBSL1R5cGUvQ2F0YWxvZy9QYWdlcyA0IDAgUi9WaWV3ZXJQcmVmZXJlbmNlczw8L1ByaW50U2NhbGluZy9BcHBEZWZhdWx0Pj4+PgplbmRvYmoKOSAwIG9iago8PC9Nb2REYXRlKEQ6MjAyMjA2MDcxMzMyNDMrMDUnMzAnKS9DcmVhdG9yKEphc3BlclJlcG9ydHMgTGlicmFyeSB2ZXJzaW9uIDYuMTguMC1hNzE1MjFiOTU2OWRiMTQ3YjcxMGUyMTg3NzMyNGVhZWJlMjM2OTI2KS9DcmVhdGlvbkRhdGUoRDoyMDIyMDYwNzEzMzI0MyswNSczMCcpL1Byb2R1Y2VyKGlUZXh0IDIuMS43IGJ5IDFUM1hUKT4+CmVuZG9iagp4cmVmCjAgMTAKMDAwMDAwMDAwMCA2NTUzNSBmIAowMDAwMDAwMzkyIDAwMDAwIG4gCjAwMDAwMDA2NjggMDAwMDAgbiAKMDAwMDAwMDAxNSAwMDAwMCBuIAowMDAwMDAwNzU2IDAwMDAwIG4gCjAwMDAwMDA2MzMgMDAwMDAgbiAKMDAwMDAwMDgxOSAwMDAwMCBuIAowMDAwMDAwODczIDAwMDAwIG4gCjAwMDAwMDA5MDUgMDAwMDAgbiAKMDAwMDAwMTAwOCAwMDAwMCBuIAp0cmFpbGVyCjw8L0luZm8gOSAwIFIvSUQgWzw4YjU5YzdhM2ZlMzE3MzZkMDI2ZmNhMmUwZDRkMWEwMT48Zjc1ZjM2Y2IzZjhjNmQ1NTVhNDA3ZmU0M2NiYmRjMWM+XS9Sb290IDggMCBSL1NpemUgMTA+PgpzdGFydHhyZWYKMTIxNwolJUVPRgo=";
//InputStream fis = new ByteArrayInputStream(pdf.getBytes(Charset.forName("UTF-8")));
		InputStream fis = new ByteArrayInputStream(Base64.getDecoder().decode(pdf));
		// FileInputStream fis = new FileInputStream("C:\\Users\\Devendra
		// Haldankar\\Desktop\\ICMR\\abc.txt");

		File file = new File("c:\\Users\\Devendra Haldankar\\Desktop\\Ameya\\google.pdf");

//copyInputStreamToFile(fis, file);

		int i = 0;
		System.out.println();
		while ((i = fis.read()) != -1) {
			// System.out.print((char)i);
		}

		Printer printer = new Printer();
		List<PrinterModal> printerModalList=new ArrayList<PrinterModal>();
		
		
		///PrintService service = printer.getDefaultPrinter();
		//printer.getAllAvailablePrinters();
		List<String> printerList=new ArrayList<String>();
		for (PrintService printService : printer.getAllAvailablePrinters()) {
			PrinterModal printerModal=new PrinterModal();
			printerList.add(printService.getName());
			printerModal.setPrinterName(printService.getName());
			printerModalList.add(printerModal);

		}
		
		PrintService service = printer.getDefaultPrinter();
		printer.getAllAvailablePrinters();
		PrintRequestAttributeSet attributes = printer.setRequestAttribute();
		
		//service = printer.printWithDialog();
		//if(service != null) {}
		m.addAttribute("printerList", printerList);
		m.addAttribute("printerModalList", printerModalList);
		m.addAttribute("printerListSize", printerList.size());
		//// END PRINTER

		return "form";
	}
	
	
	@GetMapping("/makePrint")
	public String makePrint(Model m, @ModelAttribute("printerModal") PrinterModal printerModal) {
		
		Printer printer = new Printer();
		List<PrinterModal> printerModalList=new ArrayList<PrinterModal>();
		
		
		///PrintService service = printer.getDefaultPrinter();
		//printer.getAllAvailablePrinters();
		List<String> printerList=new ArrayList<String>();
		for (PrintService printService : printer.getAllAvailablePrinters()) {
			PrinterModal printerM=new PrinterModal();
			printerList.add(printService.getName());
			printerM.setPrinterName(printService.getName());
			printerModalList.add(printerM);

		}
		
		PrintService service = printer.getDefaultPrinter();
		printer.getAllAvailablePrinters();
		PrintRequestAttributeSet attributes = printer.setRequestAttribute();
		
		//service = printer.printWithDialog();
		//if(service != null) {}
	
		m.addAttribute("printerModalList", printerModalList);
	

		
		return "makePrint";
	}
	
	@GetMapping("/printDirect")
	public void printDirect(Model m, @ModelAttribute("printerModal") PrinterModal printerModal) throws FileNotFoundException, JRException {
		
		
		File file = ResourceUtils.getFile("C:\\Users\\Devendra Haldankar\\JaspersoftWorkspace\\MyReports\\Simple_Blue.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		Map<String, Object> parameters  = new HashMap<>();
		parameters.put("createdBy", "Ameya");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
		

		//Create for PDF
		byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
		
		
		
		PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
		  //printRequestAttributeSet.add(MediaSizeName.ISO_A4);

		printRequestAttributeSet.add(MediaSizeName.ISO_A5);
		if (jasperPrint.getOrientationValue() == net.sf.jasperreports.engine.type.OrientationEnum.LANDSCAPE) {
		   printRequestAttributeSet.add(OrientationRequested.LANDSCAPE);
		  } else {
		   printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
		  }
		
		PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
		printServiceAttributeSet.add(new PrinterName(printerModal.getPrinterName(), null));
		
		JRPrintServiceExporter exporter = new JRPrintServiceExporter(); 
		
		SimplePrintServiceExporterConfiguration configuration = new SimplePrintServiceExporterConfiguration();
		  configuration.setPrintRequestAttributeSet(printRequestAttributeSet);
		  configuration.setPrintServiceAttributeSet(printServiceAttributeSet);
		  configuration.setDisplayPageDialog(false);
		  configuration.setDisplayPrintDialog(false);
		  
		  exporter.setExporterInput(new SimpleExporterInput(jasperPrint));  ///WARNING
		  exporter.setConfiguration(configuration);
		  
		  PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
		  PrintService selectedService = null;
		  
		  if (services.length != 0 || services != null) {
			   for (PrintService service : services) {
			    String existingPrinter = service.getName();
			    if (existingPrinter.equals(printerModal.getPrinterName())) {
			     selectedService = service;
			     break;
			    }
			   }
			  }
		  
		  
		  if (selectedService != null) {
			   exporter.exportReport();
			  } else {
			   System.out.println("You did not set the printer!");
			  }

		
		
		
		
	}
	
	
	
	@GetMapping("/addAccountBal")
	public String addAccountByBalance(Model m, @ModelAttribute("account") Account account)  {
		return "formBal";
		
	}
	
	@GetMapping("/addAccountBalCall")
	public String addAccountByBalanceCall(Model m, @ModelAttribute("account") Account account)  {
		return "formBal";
		
	}
	
	@GetMapping("/addAccount1")
	public String addAccountOnly(Model m, @ModelAttribute("account") Account account) {
		
		return "Acct";
		
	}
	
	@PostMapping("/addAccount1Call")
	public String addAccountCall(@Validated(value = WithBalance.class) @Valid @ModelAttribute("account") Account account,Errors e ) {
		System.out.println(e.hasErrors());
		
		return "Acct";
		
	}
	
	@GetMapping("/header")
	public String header() {
		
		return "header";
	}
	

}
