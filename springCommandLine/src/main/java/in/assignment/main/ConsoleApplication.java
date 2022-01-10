package in.assignment.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import in.assignment.configuration.ApplicationConfiguration;
import in.assignment.model.Account;
import in.assignment.services.AccountServices;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@ComponentScan("in.assignment")
@SpringBootApplication
@Component
public class ConsoleApplication implements CommandLineRunner {


	@Autowired
	private AccountServices accountServices;
	
	
	public static void main(String[] args)  throws JAXBException {
		// TODO Auto-generated method stub
		System.out.println("INSIDE MAIN");
		SpringApplication.run(ConsoleApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("INSIDE RUN");
		List<Account> accounts= accountServices.getAllAccount();
		 
		String jrxmlFilePath="C:\\Users\\Devendra Haldankar\\JaspersoftWorkspace\\MyReports\\AA.jrxml";
		String outputFile="C:\\Users\\Devendra Haldankar\\eclipse-workspace-new\\springCommandLine\\src\\main\\resources\\AccountReport.pdf";
		for (Account account : accounts) {
			System.out.println("Ac No"+account.getAccountNo());	
		}
		if(accounts.size()==0) {
			System.out.println("Empty DS");
		}else { 
			JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(accounts);
			
			Map<String , Object> parameters =new HashMap<String,Object>();
			
			parameters.put("TableData",dataSource);
			
			if(!parameters.isEmpty()) {
			//parameters.put("company","aa");
			JasperReport compileReport = JasperCompileManager.compileReport(jrxmlFilePath);
			JasperPrint fillReport = JasperFillManager.fillReport(compileReport, parameters,dataSource);
			JasperExportManager.exportReportToPdfFile(fillReport,outputFile);
			}else {
				System.out.println("Empty Empty");
			}
		}		
		
	}

}
