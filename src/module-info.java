module TenantManager {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires layout;
	requires kernel;
	
	opens utils;
	opens dto;
	opens application;
	opens signUp;
}
