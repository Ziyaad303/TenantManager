package pdfcreator;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.VerticalAlignment;

public class StatementElements {
	
	public Cell createMetaCell(String content) {
		Cell cell = new Cell()
				.add(new Paragraph(content))
				.setVerticalAlignment(VerticalAlignment.TOP)
				.setFontSize(8)
				.setBorder(Border.NO_BORDER)
				.setHeight(15F);
		return cell;
	}
	
	protected Cell addTable(Table table) {
		Cell cell = new Cell()
				.add(table)
				.setBorder(Border.NO_BORDER);
		return cell;
	}

}
