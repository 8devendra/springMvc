package in.ameya.printer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.event.PrintJobAttributeEvent;
import javax.print.event.PrintJobAttributeListener;
import javax.print.event.PrintJobEvent;
import javax.print.event.PrintJobListener;

public class Printer {
	int count = 0;
	DocFlavor createDocFlavor(){
		DocFlavor flavor = null;
		//flavor = DocFlavor.INPUT_STREAM.PDF;
		flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
		return flavor;
	}

	public PrintRequestAttributeSet setRequestAttribute() {
		PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
		//attributes.add(Sides.ONE_SIDED);
		//attributes.add(Sides.DUPLEX);
		attributes.add(MediaSizeName.ISO_A4);
		//attributes.add(OrientationRequested.PORTRAIT);
		attributes.add(new JobName("Passbook printing", null));
		attributes.add(new MediaPrintableArea((float) 0.0, (float) 0.0, 210, 297, MediaPrintableArea.MM));
		attributes.add(new Copies(2));		
		return attributes;
	}

	public PrintService getDefaultPrinter(){
		PrintService service =  PrintServiceLookup.lookupDefaultPrintService();
		System.out.println(service);		
		getPrinterAttributes(service);				
		return service;
	}

	public List<PrintService> getAllAvailablePrinters(){
		List<PrintService> services = Arrays.asList(PrintServiceLookup.lookupPrintServices(null, null));
		services.forEach(System.out::println);
		
		return services;
		
	}	

	List<PrintService> findDocFlavorSupportedPrinters(DocFlavor flavour, PrintRequestAttributeSet attributes){
		List<PrintService> services = Arrays.asList(PrintServiceLookup.lookupPrintServices(flavour, attributes));
		services.forEach(System.out::println);
		return services;
	}

	void getPrinterAttributes(PrintService service){		
		AttributeSet attributes = service.getAttributes();
		for (Attribute attr : attributes.toArray()) {
			String attrName = attr.getName();
			String attrValue = attributes.get(attr.getClass()).toString();
			System.out.println(attrName + " : " + attrValue);
		}
	}

	public PrintService printWithDialog(){
		DocFlavor flavor = createDocFlavor();		
		PrintRequestAttributeSet attributes = setRequestAttribute();
		
		return ServiceUI.printDialog(null, 10, 10,
				PrintServiceLookup.lookupPrintServices(flavor, null), 
				PrintServiceLookup.lookupDefaultPrintService(), 
				DocFlavor.SERVICE_FORMATTED.PRINTABLE,
				attributes
				);
	}

	boolean isDocFlavorSupported(PrintService service, String docType){
		Stream.of(service.getSupportedDocFlavors()).forEach(docFlavor -> {
			System.out.println(docFlavor.toString());
			if (docFlavor.toString().contains(docType)) {
				count++;
			}
		});
		if (count == 0) {
			System.err.println(docType + " not supported by printer: " + service.getName());
			return false;
		} else {
			System.out.println(docType + " is supported by printer: " + service.getName());
			return true;
		}		
	}



	public static class PrintJobMonitor implements PrintJobListener {

		@Override
		public void printDataTransferCompleted(PrintJobEvent pje) {
			System.out.println("########## printDataTransferCompleted");
			// Called to notify the client that data has been successfully
			// transferred to the print service, and the client may free
			// local resources allocated for that data.
		}

		@Override
		public void printJobCanceled(PrintJobEvent pje) {
			System.out.println("########## printJobCanceled");
			// Called to notify the client that the job was canceled
			// by a user or a program.
		}

		@Override
		public void printJobCompleted(PrintJobEvent pje) {
			System.out.println("########## printJobCanceled");
			// Called to notify the client that the job completed successfully.
		}

		@Override
		public void printJobFailed(PrintJobEvent pje) {
			System.out.println("########## printJobFailed");
			// Called to notify the client that the job failed to complete
			// successfully and will have to be resubmitted.
		}

		@Override
		public void printJobNoMoreEvents(PrintJobEvent pje) {
			System.out.println("########## printJobNoMoreEvents");
			// Called to notify the client that no more events will be delivered.
		}

		@Override
		public void printJobRequiresAttention(PrintJobEvent pje) {
			System.out.println("########## printJobRequiresAttention");
			// Called to notify the client that an error has occurred that the
			// user might be able to fix.
		}

	}


	private static class PrintJobAttributeFinder implements PrintJobAttributeListener {
		public void attributeUpdate(PrintJobAttributeEvent event) {
			Attribute[] attrs = event.getAttributes().toArray();	        
			for (int i = 0; i < attrs.length; i++) {
				String attrName = attrs[i].getName();
				String attrValue = attrs[i].toString();
				System.out.println(attrName + " : " + attrValue);
			}
		}
	}

}
