import java.sql.SQLException;
import java.util.Scanner;
public class HOMESCREEN {
	public static void main(String[] args) throws SQLException {
		System.out.println("welcome to abc travels\n");
		Scanner scan = new Scanner(System.in);
		System.out.println("press 1 for signup : \npress 2 for login  : \npress 3 for Bus Enquiry  :");
		int userinput = scan.nextInt();
		USERLOGIN UL = new USERLOGIN();
		booking b = new booking();
		if(userinput==1) {
		UL.userdetails();
		}
		else if(userinput==2){
			UL.userlogin();
			if (UL.dummy()) {
				System.out.println("press 1 for booking : \npress 2 for wallet balnce fetch  : \npress 3 for adding amount to wallet: \npress 4 for booking history:");
				int a = scan.nextInt();
				if(a == 1) {
					b.booking();
				}
				else if(a == 2) {
					UL.balancefetch();
				}
				else if(a == 3) {
					UL.balanceadding();
				}
				else if(a == 4) {
					UL.bookinghistory();
				}
			}
		}
		else if(userinput==3){
				try {
					UL.Enquiry();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
}}