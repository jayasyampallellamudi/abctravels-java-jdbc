import java.util.regex.Matcher;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.ResultSet;
public class USERLOGIN {
	resources d = new resources();
    String url = "jdbc:mysql://localhost:3306/abc_travels";
    String user = "root";
    String password = "root";
    ResultSet resultset = null;
	public void userdetails() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO user_details (first_name, last_name, mobile_number, e_mail, user_password) VALUES (? ,? ,?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // Set parameter values
            preparedStatement.setString(1, firstname());
            preparedStatement.setString(2, Lastname());
            preparedStatement.setString(3, phonenumber());
            preparedStatement.setString(4, emailaddress());
            preparedStatement.setString(5, password());
            // Execute the INSERT statement
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("completed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
		}
	Scanner scan = new Scanner(System.in);
	private String firstname() {
	System.out.println("enter your First name : ");
	String firstname = scan.nextLine();
	return firstname;}
	private String Lastname() {
	System.out.println("enter your last name : ");
	String Lastname = scan.nextLine();
	return Lastname;}
	private String phonenumber(){
	System.out.println("enter your mobile number : ");
	String phonenuber = scan.nextLine();
	int count = 0;
	char[] num1 = phonenuber.toCharArray();
	for(char ch : num1) {
		if(ch/1==ch) {
			++count;
		}
	}
	if(count == 10) {
		System.out.println("your number is valid");
	}
	else {
		System.out.println("your number is not valid");
		phonenumber();
	}
	return phonenuber;
	}
	private static boolean isValidEmail(String emails) {
    	final String EMAIL_REGEX = "^[A-Za-z0-9]+@[A-Za-z]+\\.[A-Za-z]+$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(emails);
        return matcher.matches();
    }
	public String emailaddress() {
	System.out.println("enter your e-mail address : ");
	String emails = scan.nextLine();
	if(isValidEmail(emails) && emails.length()<320) {
		System.out.println("your e-mail address is valid");
	}
	else {
		System.out.println("your e-mail address is not valid");
		emailaddress();
	}
	return emails;
	}
	public String password() {
		System.out.println("enter your password : "
				+"(Note:your password contains lower,Upper case letters, numbers and special charecters)");
		String password = scan.nextLine();
        boolean containsCapital = false;
        boolean containsNumber = false;
        boolean containsSpecialChar = false;
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                containsCapital = true;
            } else if (Character.isDigit(ch)) {
                containsNumber = true;
            } else if ("@#$%^&*!".contains(String.valueOf(ch))) {
                containsSpecialChar = true;
            }
        }if(containsCapital && containsNumber && containsSpecialChar && password.length()>8 ) {
        	System.out.println("your password is strong");
        }else {
        	System.out.println("your password is week");
        	password();
        }
		return password;
	}
	String userloginemail;
	String userlogipassword;
	private boolean dummy =  false;
	public void userlogin() {
        Connection connection;
		try {
		connection = DriverManager.getConnection(url, user, password);
        String sqlQuery = "SELECT * FROM user_details";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        resultset = preparedStatement.executeQuery();
        System.out.println("enter your email adress : ");
		userloginemail = scan.nextLine();
		System.out.println("enter your password : ");
		userlogipassword = scan.nextLine();
		boolean condition = false;
			while (resultset.next()) {
			    // Retrieve data from the result set
			    String email = resultset.getString("e_mail");;
				String password = resultset.getString("user_password"); 
				if(userloginemail.equalsIgnoreCase(email) && userlogipassword.contains(password)) {
					dummy = true;
					condition = true;
				}}
			if (condition) {
				System.out.println("your E-mail & Passwords are valid");
			}
			else {
				System.out.println("your E-mail & Passwords are not valid");
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	public boolean dummy() {
		return dummy;
	}
	public void Enquiry() throws SQLException {
        try (Connection connection1 = DriverManager.getConnection(url, user, password)) {
            String sqlQuery = "SELECT start_time, reach_time, price, seat_availabulity, bus_number " +
                    "FROM bus_details " +
                    "WHERE from_station = ? AND to_station = ?";
		try (PreparedStatement preparedStatement = connection1.prepareStatement(sqlQuery)){
			preparedStatement.setString(1,  d.fromstation());
	        preparedStatement.setString(2, d.tostation());
            ResultSet resultset = preparedStatement.executeQuery();
        while (resultset.next()) {
            String startTime = resultset.getString("start_time");
            String reachTime = resultset.getString("reach_time");
            int ticketPrice = resultset.getInt("price");
            int seatAvailability = resultset.getInt("seat_availabulity");
            String busNumber = resultset.getString("bus_number");
            System.out.println("Start Time: " + startTime);
            System.out.println("Reach Time: " + reachTime);
            System.out.println("Ticket Price: " + ticketPrice);
            System.out.println("Seat Availability: " + seatAvailability);
            System.out.println("Bus Number: " + busNumber);
		}}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	}
	int balance;
	public int balancefetch() throws SQLException {
		try (Connection connection2 = DriverManager.getConnection(url, user, password)) {
			String balancefetch = "SELECT wallet_amount " +
                    "FROM user_details " +
                    "WHERE e_mail = ? AND user_password = ?";
		try (PreparedStatement preparedStatement = connection2.prepareStatement(balancefetch)){
			preparedStatement.setString(1, userloginemail);
	        preparedStatement.setString(2, userlogipassword);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
            	balance = resultset.getInt("wallet_amount");
            	System.out.println(balance + "rupees only/-" );
            	}
            }
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		return balance;}
	public int balanceintake() {
		System.out.println("Enter your amount to add wallet : ");
		int balanceintake = scan.nextInt();
		return balanceintake;
	}
	public void balanceadding() throws SQLException {
	    try (Connection connection2 = DriverManager.getConnection(url, user, password)) {
	    	String balanceadding1 = "UPDATE user_details " +
                    "SET wallet_amount = ? " +
                    "WHERE e_mail = ? AND user_password = ?";
			String balancefetch = "SELECT wallet_amount " +
                    "FROM user_details " +
                    "WHERE e_mail = ? AND user_password = ?";
		try (PreparedStatement preparedStatement = connection2.prepareStatement(balancefetch)){
			preparedStatement.setString(1, userloginemail);
	        preparedStatement.setString(2, userlogipassword);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
            	balance = resultset.getInt("wallet_amount");
            	System.out.println(balance + "present balance" );
            	}
            }
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int newbalance = balance + balanceintake();
	    	try (PreparedStatement preparedStatement2 = connection2.prepareStatement(balanceadding1)){
	            preparedStatement2.setInt(1, newbalance);
	            preparedStatement2.setString(2, userloginemail);
	            preparedStatement2.setString(3, userlogipassword);
	            System.out.println("newbalance: " + newbalance);
	            System.out.println("email: " + userloginemail);
	            System.out.println("password1: " + userlogipassword);
	            int resultset1 = preparedStatement2.executeUpdate();
	            System.out.println(resultset1);
	        } catch (SQLException e) {
	            e.printStackTrace(); // Handle the exception appropriately
	        }
	}}
	public void bookinghistory() throws SQLException {
        try (Connection connection1 = DriverManager.getConnection(url, user, password)) {
            String sqlQuery = "SELECT from_station, to_station, price, passengers_count, bus_number " +
                    "FROM booking_details " +
                    "WHERE e_mail = ? ";
       try (PreparedStatement preparedStatement1 = connection1.prepareStatement(sqlQuery)){
    	   preparedStatement1.setString(1, userloginemail);
    	   ResultSet resultset = preparedStatement1.executeQuery();
           while (resultset.next()) {
        	   String from = resultset.getString("from_station");
        	   String to = resultset.getString("to_station");
               int ticketPrice = resultset.getInt("price");
               int count = resultset.getInt("passengers_count");
               String Number = resultset.getString("bus_number");
               System.out.println("from station : " + from);
               System.out.println("to station : " + to);
               System.out.println("booking price : " + ticketPrice);
               System.out.println("number of passengers : " + count);
               System.out.println("bus number : " + Number);
               }
       }
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}

}	
}