import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class booking {
	Scanner scan =  new Scanner(System.in);
	resources r = new resources();
	USERLOGIN u = new USERLOGIN();
	public String date() {
		System.out.print("Enter a date (yyyy-MM-dd): ");
		String date = getCurrentDateFormatted(scan.nextLine());
		return date;
	}
    public String getCurrentDateFormatted(String formatPattern) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
        return currentDate.format(formatter);
    }
    public int numpass() {
    	System.out.println("Enter number of passengers to travel : ");
    	int pass = scan.nextInt();
		return pass;
    }
    String url = "jdbc:mysql://localhost:3306/abc_travels";
    String user = "root";
    String password = "root";
    int pass;
    String a;
    String b;
    String date;
    int newseatavailable;
	public void booking() throws SQLException {
	     a = r.fromstation();
	     b = r.tostation();
	     pass = numpass();
        try (Connection connection1 = DriverManager.getConnection(url, user, password)) {
            String sqlQuery = "SELECT seat_availabulity, price, bus_number " +
                    "FROM bus_details " +
                    "WHERE from_station = ? AND to_station = ?";
            String seatupdateQuery = "UPDATE bus_details " +
                    "SET seat_availabulity = ? " +
                    "WHERE from_station = ? AND to_station = ?";
            String bookingdata = "INSERT INTO booking_details (from_station, to_station, price, passengers_count, bus_number, e_mail) VALUES (?, ?, ?, ?, ?, ?)";      
          if(!a.equals(b)) {
		try (PreparedStatement preparedStatement = connection1.prepareStatement(sqlQuery)){
			preparedStatement.setString(1, a);
	        preparedStatement.setString(2, b);
            ResultSet resultset = preparedStatement.executeQuery();
        while (resultset.next()) {
            int ticketPrice = resultset.getInt("price");
            int seatAvailability = resultset.getInt("seat_availabulity");
            String Number = resultset.getString("bus_number");
            int busNumber = Integer.parseInt(Number);
            newseatavailable = seatAvailability-pass;
    		if(seatAvailability>pass) {
    			try (PreparedStatement preparedStatement1 = connection1.prepareStatement(seatupdateQuery)){
    				preparedStatement1.setInt(1, newseatavailable);
    				preparedStatement1.setString(2, a);
    				preparedStatement1.setString(3, b);
    	            int resultset5 = preparedStatement1.executeUpdate();
    			}
    			catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			int totalprice = ticketPrice*pass;
    			try (PreparedStatement preparedStatement1 = connection1.prepareStatement(bookingdata)){
    				preparedStatement1.setString(1, a);
    				preparedStatement1.setString(2, b);
    				preparedStatement1.setInt(3, totalprice);
    				preparedStatement1.setInt(4, pass);
    				preparedStatement1.setInt(5, busNumber);
    				preparedStatement1.setString(6, u.userloginemail);
    	            int resultset3 = preparedStatement1.executeUpdate();	
    			}
    			catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}}}
		}}
      System.out.println("booking sucesfully completed");
}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
}