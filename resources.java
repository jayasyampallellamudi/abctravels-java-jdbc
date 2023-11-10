import java.util.Scanner;

public class resources {
	Scanner scan =  new Scanner(System.in);
	public  String fromstation() {
		String fromstastion = null;
		System.out.println("select your from station \npress 1 for hyderabad : \npress 2 for rajahmundry: \npress 3 for vizag : \npress 4 for vijayawada :");
		int frominput = scan.nextInt();
		if(frominput == 1) {
			fromstastion = "hyderabad";
		}
		else if(frominput == 2) {      
			fromstastion = "rajahmundry";
		}
		else if(frominput == 3) {
			fromstastion = "vizag";
		}
		else if(frominput == 4) {
			fromstastion = "vijayawada";
		}
		return fromstastion;
	}
	public  String tostation() {
		System.out.println("select your to station \npress 1 for hyderabad : \npress 2 for rajahmundry: \npress 3 for vizag : \npress 4 for vijayawada :");
		int toinput = scan.nextInt();
		String tostastion = null;
		if(toinput == 1) {
			tostastion = "hyderabad";
		}
		else if(toinput == 2) {
			tostastion = "rajahmundry";
		}
		else if(toinput == 3) {
			tostastion = "vizag";
		}
		else if(toinput == 4) {
			tostastion = "vijayawada";
		}
		return tostastion;
	}
}
