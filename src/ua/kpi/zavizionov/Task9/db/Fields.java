package ua.kpi.zavizionov.Task9.db;

public interface Fields {
	
	String ID = "id";
	//users
	String USER_LOGIN = "login";
	String USER_PASSWORD = "password";
	String USER_EMAIL = "email";
	String USER_ROLE_ID = "role_id";
	
	//client
	String CLIENT_NAME = "name";
	String CLIENT_ADDRESS = "address";
	String CLIENT_NUMBER = "number";
	String CLIENT_USER_ID = "user_id";

	//roles
	String ROLE_NAME = "name";
	
	//users
	String ACCOUNT_BANK = "bank";
	String ACCOUNT_OPEN_DATE = "open_date";
	String ACCOUNT_CLOSE_DATE = "close_date";
	String ACCOUNT_BALANCE = "balance";
	String ACCOUNT_CURRENCY = "currency";
	String ACCOUNT_CLIENT_ID = "client_id";
	
	
}
