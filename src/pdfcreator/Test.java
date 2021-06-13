package pdfcreator;

import java.io.FileNotFoundException;

import dto.Tenant;

public class Test {

	public static void main(String[] args) {
		
		Tenant tenant = new Tenant(1, 2, 1, null, 12, "");
		StatementCreator sc = new StatementCreator(tenant);
		try {
			sc.generateStatement();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
