package pdfcreator;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import account.AccountDatabaseHandler;
import dto.Account;
import dto.ElectricityReading;
import dto.Person;
import dto.Property;
import dto.Tenant;
import dto.WaterReading;
import person.PersonDatabaseHandler;
import property.PropertyDatabaseHandler;
import readings.ReadingsDatabaseHandler;
import tenant.TenantDatabaseHandler;
import utils.BillingDetailsLine;

public class StatementCreator extends StatementElements{

	//fakeAmount to be changed to sliding scale as per rates
	private int fakeAmount = 10;
	private int balanceTotal = 0;
	private Tenant container;
	private Person tenant;
	private Property property;
	private Person owner;
	private Account ownersAccount;
	
	private TenantDatabaseHandler tenantDbHandler;
	private PersonDatabaseHandler personDbHandler;
	private PropertyDatabaseHandler propertyDbHandler;
	private AccountDatabaseHandler accountDbHandler;
	private ReadingsDatabaseHandler readingsDbHandler;
	private Document document;
	
	String dest = "C:\\Users\\Ziyaad\\OCDev\\Eclipse workspace\\misc\\documentTest\\document_exhibits\\statement_2.pdf";

	public StatementCreator(Tenant container){
		this.container = container;
		
		tenantDbHandler = new TenantDatabaseHandler();
		personDbHandler = new PersonDatabaseHandler();
		propertyDbHandler = new PropertyDatabaseHandler();
		accountDbHandler = new AccountDatabaseHandler();
		readingsDbHandler = new ReadingsDatabaseHandler();
		
		tenant = personDbHandler.retrievePerson(container.getPersonId());
		property = propertyDbHandler.retrieveProperty(container.getPropertyId());
		owner = personDbHandler.retrievePerson(property.getPersonId());
		ownersAccount = accountDbHandler.retrieveActiveAccount(owner.getPersonId());
	}
	
	public void generateStatement()  throws FileNotFoundException {
		if (container != null) {
			init();
			
			createHeader();
			document.add(new Paragraph("\n"));
			createStatementMetadata();
			document.add(new Paragraph("\n"));
			createStatementDetails();
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			createFooter();
			document.close();
			System.out.println("Document Created");
		} else {
			System.out.println("No Tenant Found");
		}
	}
	
	private void init() throws FileNotFoundException {
		PdfWriter writer = new PdfWriter(dest);

		// Creating a PdfDocument object
		PdfDocument pdfDoc = new PdfDocument(writer);

		// Creating a Document object
		document = new Document(pdfDoc);
		System.out.println("Document initialized");
	}

	private void createHeader() {
		float[] columnWidths = {350F, 150F};
		Table table = new Table(columnWidths);
		
		Cell statementCell = new Cell()
				.add(new Paragraph("Statement"))
				.setTextAlignment(TextAlignment.CENTER)
				.setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setHeight(100F)
				.setFontSize(36);
		
		String info = owner.getFirstName() + " " + owner.getLastName() + "\nPhone: " + owner.getPhone() + "\nEmail: " + owner.getEmail();
		Cell infoCell = new Cell()
				.add(new Paragraph(info))
				.setPaddingLeft(50F)
				.setTextAlignment(TextAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFontSize(6);
		
		table.addCell(statementCell);
		table.addCell(infoCell);
		document.add(table);
		System.out.println("Header Created");
	}

	private void createStatementMetadata() {
		String[] metaHeaders = {"Statement #: ","Date: ","Statement Period: ", "Bill To: "};
		String billTo = tenant.getFirstName() + " " + tenant.getLastName() + "\n" 
				+ property.getComplexName() + "\n" + property.getStreetAddress() + "\n" + 
				property.getSuburb() + "\n" + property.getAreaCode();

		String[] metaInfo = {"A001", String.valueOf(LocalDate.now()), String.valueOf(property.getPurchaseDate()), billTo};
		
		float width = 125F;
		float[] columnWidth = {width};
		
		Table leftHeaderTable = new Table(columnWidth);
		Table leftInfoTable = new Table(columnWidth);
		
		for(int i = 0; i< 3; i++) {
			leftHeaderTable.addCell(createMetaCell(metaHeaders[i])
					.setTextAlignment(TextAlignment.RIGHT));
			leftInfoTable.addCell(createMetaCell(metaInfo[i])
					.setTextAlignment(TextAlignment.LEFT));
		}
		
		Table rightHeaderTable = new Table(columnWidth);
		Table rightInfoTable = new Table(columnWidth);
		
		rightHeaderTable.addCell(createMetaCell(metaHeaders[3])
				.setTextAlignment(TextAlignment.RIGHT));
		rightInfoTable.addCell(createMetaCell(metaInfo[3])
				.setTextAlignment(TextAlignment.LEFT)
				.setHeight(75F)
				);
		
		float[] metaColumnWidths = {width, width, width, width};
		Table metaTable = new Table(metaColumnWidths);
		metaTable.addCell(addTable(leftHeaderTable));
		metaTable.addCell(addTable(leftInfoTable));
		metaTable.addCell(addTable(rightHeaderTable));
		metaTable.addCell(addTable(rightInfoTable));
		
		document.add(metaTable);
		System.out.println("MetaData created");
	}

	private void createStatementDetails() {
		float[] detailsColumnWidths = {70F, 70F, 150F, 75F, 75F, 75F};
		Table table = new Table(detailsColumnWidths);
		
		String[] detailsHeaders = {"Date", "Reference", "Description", "Debit", "Credit", "Balance"};
		for(String header: detailsHeaders) {
			Cell c = new Cell()
					.add(new Paragraph(header))
					.setBackgroundColor(ColorConstants.DARK_GRAY)
					.setFontColor(ColorConstants.WHITE)
					.setTextAlignment(TextAlignment.CENTER)
					.setVerticalAlignment(VerticalAlignment.MIDDLE)
					.setFontSize(8);
			table.addCell(c);
		}
		
		List<BillingDetailsLine> details = new ArrayList<>();
		details.add(calculateWaterDetail(false));
		details.add(calculateElecDetail(false));
		details.add(addRent(false));
		details.add(processPayment("01-04-2021", 8520));
		details.add(calculateWaterDetail(true));
		details.add(calculateElecDetail(true));
		details.add(addRent(true));
		details.add(new BillingDetailsLine());
		
		BillingDetailsLine totalLine = new BillingDetailsLine();
		totalLine.setBalance(balanceTotal);
		details.add(totalLine);
		
		for(BillingDetailsLine line : details) {
			Cell dateCell = new Cell()
					.add(new Paragraph(line.getDate()))
					.setTextAlignment(TextAlignment.CENTER)
					.setVerticalAlignment(VerticalAlignment.MIDDLE)
					.setFontSize(8);
			Cell refCell = new Cell()
					.add(new Paragraph(line.getReference()))
					.setTextAlignment(TextAlignment.CENTER)
					.setVerticalAlignment(VerticalAlignment.MIDDLE)
					.setFontSize(8);
			Cell descCell = new Cell()
					.add(new Paragraph(line.getDescription()))
					.setTextAlignment(TextAlignment.CENTER)
					.setVerticalAlignment(VerticalAlignment.MIDDLE)
					.setFontSize(8);
			Cell debitCell = new Cell()
					.add(new Paragraph(String.valueOf(line.getDebit() == 0.0 ? "" : line.getDebit())))
					.setTextAlignment(TextAlignment.CENTER)
					.setVerticalAlignment(VerticalAlignment.MIDDLE)
					.setFontSize(8);
			Cell creditCell = new Cell()
					.add(new Paragraph(String.valueOf(line.getCredit() == 0.0 ? "" : line.getCredit())))
					.setTextAlignment(TextAlignment.CENTER)
					.setVerticalAlignment(VerticalAlignment.MIDDLE)
					.setFontSize(8);
			Cell balanceCell = new Cell()
					.add(new Paragraph(String.valueOf(line.getBalance() == 0.0 ? "" : line.getBalance())))
					.setTextAlignment(TextAlignment.CENTER)
					.setVerticalAlignment(VerticalAlignment.MIDDLE)
					.setFontSize(8);
			table.addCell(dateCell);
			table.addCell(refCell);
			table.addCell(descCell);
			table.addCell(debitCell);
			table.addCell(creditCell);
			table.addCell(balanceCell);
		}
		
		document.add(table);

	}

	private void createFooter() {
		float[] columnWidths = {100F, 100F, 150F, 150F};
		Table mainTable = new Table(columnWidths);
		
		String[] footerHeaders = {"Banking Details", "Bank: ", "Account Holder: ", "Account Type: ", "Account Number: ", "Branch Code: "};
		String[] footerDetails = {"", ownersAccount.getBank(), ownersAccount.getAccountHolder(), ownersAccount.getAccountType(), ownersAccount.getAccountNumber(), ownersAccount.getBranchCode()};
		
		float[] bankingHeaderWidths = {175F};
		Table headerTable = new Table(bankingHeaderWidths);
		float[] bankingDetailsWidths = {175F};
		Table detailsTable = new Table(bankingDetailsWidths);
		
		for(int i = 0; i< footerHeaders.length; i++) {
			Cell headerCell = createMetaCell(footerHeaders[i]);
			headerTable.addCell(headerCell);
			Cell detailCell = createMetaCell(footerDetails[i]);
			detailsTable.addCell(detailCell);
		}
		
		mainTable.addCell(addTable(headerTable));
		mainTable.addCell(addTable(detailsTable));
		
		document.add(mainTable);

	}

	public Tenant getContainer() {
		return container;
	}

	public void setContainer(Tenant container) {
		this.container = container;
	}

	private BillingDetailsLine calculateWaterDetail(boolean isCurrent) {
		BillingDetailsLine line = new BillingDetailsLine();
		
		List<WaterReading> readings = readingsDbHandler.retrieveWaterReadings(property.getPropertyId(), container.getTenantId());
		//=========================================
		//	LOWER TOTAL
		//=========================================
		int lowerTotal = property.getFirstWaterReading();
		if(isCurrent) {
			for(int i = 0; i < readings.size() - 1; i++) {
				lowerTotal += readings.get(i).getUnitsUsed();
			}
		} else {
			for(int i = 0; i < readings.size() - 2; i++) {
				lowerTotal += readings.get(i).getUnitsUsed();
			}
		}
		
		//=========================================
		//	USAGE
		//=========================================
		int usage;
		if(isCurrent) {
			usage = readings.get(readings.size() - 1).getUnitsUsed();
		} else {
			usage = readings.get((readings.size() - 2)).getUnitsUsed();
		}
		
		String description = lowerTotal + " - " + (lowerTotal + usage) + " = " + usage + " units" ;
		
		if(isCurrent) {
			line.setDate(readings.get(readings.size() - 1).getReadingDate());
		} else {
			line.setDate(readings.get(readings.size() - 2).getReadingDate());
		}
		line.setReference("Water");
		line.setDescription(description);
		line.setDebit(fakeAmount);
		line.setBalance(balanceTotal += fakeAmount);
		return line;
	}
	
	private BillingDetailsLine calculateElecDetail(boolean isCurrent) {
		BillingDetailsLine line = new BillingDetailsLine();

		List<ElectricityReading> readings = readingsDbHandler.retrieveElectricityReadings(property.getPropertyId(), container.getTenantId());
		// =========================================
		// LOWER TOTAL
		// =========================================
		int lowerTotal = property.getFirstElecReading();
		if (isCurrent) {
			for (int i = 0; i < readings.size() - 1; i++) {
				lowerTotal += readings.get(i).getUnitsUsed();
			}
		} else {
			for (int i = 0; i < readings.size() - 2; i++) {
				lowerTotal += readings.get(i).getUnitsUsed();
			}
		}

		// =========================================
		// USAGE
		// =========================================
		int usage;
		if (isCurrent) {
			usage = readings.get(readings.size() - 1).getUnitsUsed();
		} else {
			usage = readings.get((readings.size() - 2)).getUnitsUsed();
		}

		String description = lowerTotal + " - " + (lowerTotal + usage) + " = " + usage + " units";

		if (isCurrent) {
			line.setDate(readings.get(readings.size() - 1).getReadingDate());
		} else {
			line.setDate(readings.get(readings.size() - 2).getReadingDate());
		}
		line.setReference("Electricity");
		line.setDescription(description);
		line.setDebit(fakeAmount);
		line.setBalance(balanceTotal += fakeAmount);
		return line;
	}
	
	private BillingDetailsLine addRent(boolean isCurrent) {
		BillingDetailsLine line = new BillingDetailsLine();

		List<ElectricityReading> elecReadings = readingsDbHandler.retrieveElectricityReadings(property.getPropertyId(), container.getTenantId());
		
		String date;
		if (isCurrent) {
			date = elecReadings.get(elecReadings.size() - 1).getReadingDate();
		} else {
			date = elecReadings.get(elecReadings.size() - 2).getReadingDate();
		}

		String[] dateArray = date.split("-");
		String monthString = convertToMonth(Integer.parseInt(dateArray[1]) + 1);
		String referenceString = monthString + " " + dateArray[2];

		line.setDate(date);
		line.setReference(referenceString);
		line.setDescription("Rent " + monthString);
		line.setDebit(8500);
		line.setBalance(balanceTotal += 8500);
		return line;
	}
	
	private String convertToMonth(int month) {
		switch(month) {
		case 1: return "January";
		case 2: return "February";
		case 3: return "March";
		case 4: return "April";
		case 5: return "May";
		case 6: return "June";
		case 7: return "July";
		case 8: return "August";
		case 9: return "September";
		case 10: return "October";
		case 11: return "November";
		case 12: return "December";
		default: return "";
		}
	}
	
	private BillingDetailsLine processPayment(String dateReceived, double amountReceived) {
		BillingDetailsLine line = new BillingDetailsLine();
		
		line.setDate(dateReceived);
		line.setReference("");
		line.setDescription("Payment Received - Thank You");
		line.setCredit(amountReceived);
		line.setBalance(balanceTotal -= amountReceived); 
		return line;
	}
}
