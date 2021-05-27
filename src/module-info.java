module TenantManager {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	
	opens utils;
	opens dto;
	opens application;
	opens signUp;
	opens owner.home;
}
