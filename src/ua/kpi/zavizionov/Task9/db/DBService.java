package ua.kpi.zavizionov.Task9.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ua.kpi.zavizionov.Task9.db.entity.Account;
import ua.kpi.zavizionov.Task9.db.entity.Client;
import ua.kpi.zavizionov.Task9.db.entity.Role;
import ua.kpi.zavizionov.Task9.db.entity.User;
public class DBService {
	
	private static final String SQL_SELECT_USER_BY_LOGIN = "select * from users where login = ?";
	private static final String SQL_SELECT_ROLE = "select * from roles where id = ?";
	private static final String SQL_SELECT_ACCOUNTS  = "select * from account";
	private static final String SQL_SELECT_CLIENT = "select * from client where id = ?";
	//Singleton
	
		private static DBService instance;
		
		public static synchronized DBService getInstance() {
			if (instance == null) {
				instance = new DBService();
			}
			return instance;
		}
		
		public Connection getConnection() throws SQLException {
			Connection con = null;
			try {
				Context initContext = new InitialContext();
				Context envContext  = (Context)initContext.lookup("java:/comp/env");
				DataSource ds = (DataSource)envContext.lookup("jdbc/mysql");
				con = ds.getConnection();
			} catch (NamingException ex) {
						
			}
			return con;
		}
		
		public Role getRoleByPK(int id){
			DBService service = DBService.getInstance();
			Connection con = null;
			PreparedStatement statement = null;
			ResultSet rs = null;
			Role role = null;
			try {
				con = service.getConnection();
				statement = con.prepareStatement(SQL_SELECT_ROLE);
				statement.setInt(1, id);
				rs = statement.executeQuery();
				role = parseRoleResultSet(rs).iterator().next();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return role;
		}
		
		public Client getClientByPK(int id){
			DBService service = DBService.getInstance();
			Connection con = null;
			PreparedStatement statement = null;
			ResultSet rs = null;
			Client client = null;
			try {
				con = service.getConnection();
				statement = con.prepareStatement(SQL_SELECT_CLIENT);
				statement.setInt(1, id);
				rs = statement.executeQuery();
				client = parseClientResultSet(rs).iterator().next();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return client;
		}
		
		
		
		public User findUserByLogin(String login){
			DBService service = DBService.getInstance();
			Connection con = null;
			PreparedStatement statement = null;
			ResultSet rs = null;
			User user = null;
			try {
				con = service.getConnection();
				statement = con.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
				statement.setString(1, login);
				rs = statement.executeQuery();
				user = parseUserResultSet(rs).iterator().next();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return user;
		}
		
		public List<Account> getAllAccounts(){
			DBService service = DBService.getInstance();
			Connection con = null;
			PreparedStatement statement = null;
			ResultSet rs = null;
			List<Account> result = null;
			try {
				con = service.getConnection();
				statement = con.prepareStatement(SQL_SELECT_ACCOUNTS);
				rs = statement.executeQuery();
				result = parseAccountResultSet(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return result;
		}
		
		public List<User> parseUserResultSet(ResultSet rs){
			List<User> result = new ArrayList<User>();
			
			try {
				while (rs.next()){
					User user = new User();
					user.setId(rs.getInt(Fields.ID));
					user.setLogin(rs.getString(Fields.USER_LOGIN));
					user.setPassword(rs.getString(Fields.USER_PASSWORD));
					user.setRole(getRoleByPK(rs.getInt(Fields.USER_ROLE_ID)));
					result.add(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
		
		public List<Role> parseRoleResultSet(ResultSet rs){
			List<Role> result = new ArrayList<Role>();
			
			try {
				while (rs.next()){
					Role role = new Role();
					role.setId(rs.getInt(Fields.ID));
					role.setName(rs.getString(Fields.ROLE_NAME));
					result.add(role);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
		
		public List<Account> parseAccountResultSet(ResultSet rs){
			List<Account> result = new ArrayList<Account>();
			try {
				while (rs.next()){
					Account account = new Account();
					account.setId(rs.getInt(Fields.ID));
					account.setBank(rs.getString(Fields.ACCOUNT_BANK));
					account.setOpenDate(rs.getDate(Fields.ACCOUNT_OPEN_DATE));
					account.setCloseDate(rs.getDate(Fields.ACCOUNT_CLOSE_DATE));
					account.setCurrency(rs.getString(Fields.ACCOUNT_CURRENCY));
					account.setBalance(rs.getFloat(Fields.ACCOUNT_BALANCE));
					account.setClient(this.getClientByPK(rs.getInt(Fields.ACCOUNT_CLIENT_ID)));
					result.add(account);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
		
		public List<Client> parseClientResultSet(ResultSet rs){
			List<Client> result = new ArrayList<Client>();
			try {
				while (rs.next()){
					Client c = new Client();
					c.setId(rs.getInt(Fields.ID));
					c.setName(rs.getString(Fields.CLIENT_NAME));
					c.setAddress(rs.getString(Fields.CLIENT_ADDRESS));
					c.setNumber(rs.getInt(Fields.CLIENT_NUMBER));
					//user
					result.add(c);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}

		
		private DBService() {
			
		}

}
