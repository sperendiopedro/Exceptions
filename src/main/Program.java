package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Reservation;
import model.exceptions.DomainExceptions;

//tratando exceções. 
public class Program {
	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			System.out.println("Room number: ");
			Integer i = sc.nextInt();
			sc.nextLine();
			System.out.println("Check in date: ");
			Date d1 = sdf.parse(sc.next());
			System.out.println("Check out date: ");
			Date d2 = sdf.parse(sc.next());
	
			Reservation res = new Reservation(i, d1, d2);
			System.out.println("Reservation: " + res);
	
			System.out.println();
			System.out.println("Enter data to update the reservation: ");
			System.out.println("Check in date: ");
			d1 = sdf.parse(sc.next());
			System.out.println("Check out date: ");
			d2 = sdf.parse(sc.next());
			
			res.updateDates(d1, d2);
			System.out.println("Reservation: " + res);
		}
		catch(ParseException e ) {
			System.out.println("Invalid date format: " + e.getMessage());
		}
		catch(DomainExceptions e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}
}
