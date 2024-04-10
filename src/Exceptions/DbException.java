package Exceptions;

import java.sql.SQLException;

public class DbException extends SQLException {
	
	public DbException(String message) {
		super(message);
	}

}
