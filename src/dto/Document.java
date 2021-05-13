package dto;

import java.sql.Clob;

public class Document {

	private int documentId;
	private int tenantId;
	private int documentType;
	private Clob doc;

	public Document() {

	}
	
	public Document(int documentId, int tenantId, int documentType, Clob doc) {
		this.documentId = documentId;
		this.tenantId = tenantId;
		this.documentType = documentType;
		this.doc = doc;
	}

	public int getDocumentId() {
		return documentId;
	}

	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public int getDocumentType() {
		return documentType;
	}

	public void setDocumentType(int documentType) {
		this.documentType = documentType;
	}

	public Clob getDoc() {
		return doc;
	}

	public void setDoc(Clob doc) {
		this.doc = doc;
	}

}
