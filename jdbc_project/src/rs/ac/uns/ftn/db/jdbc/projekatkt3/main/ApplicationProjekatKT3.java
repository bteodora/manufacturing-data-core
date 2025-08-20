package rs.ac.uns.ftn.db.jdbc.projekatkt3.main;

import rs.ac.uns.ftn.db.jdbc.projekatkt3.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.ui_handler.MainUIHandler;

public class ApplicationProjekatKT3 {

	public static void main(String[] args) {

		// set application log level
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "WARN");

		MainUIHandler mainUIHandler = new MainUIHandler();
		try {
			mainUIHandler.handleMainMenu();
			ConnectionUtil_HikariCP.closeDataSource();
		} catch (Exception e) {
			ConnectionUtil_HikariCP.closeDataSource();
		}
	}

}
