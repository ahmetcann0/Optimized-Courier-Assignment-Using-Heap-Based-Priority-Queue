//-----------------------------------------------------
// Title: Main (Driver) Class 
// Author: Ahmet Can Öztürk
// Assignment: 3
// Description: This is our driver class, it provides for creating objects and reading text files
// also called the findAvg function
//-----------------------------------------------------
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {

		MaxPQ<Customer> pq = new MaxPQ<Customer>(200); // Creating max priority queue with 200 capacity

		ArrayList<Customer> arr = new ArrayList<Customer>(); // Customer [] arr = new Customer [200]; // Create
																// arrayList for hold the textFile informations
		
		//Taking file name from user
		System.out.println("Enter input filename:");
		Scanner keyboard = new Scanner(System.in);
		String file = keyboard.nextLine();
		
		//Taking avg time input from user
		System.out.println("Enter the maximum average waiting time:");
		int max_time = keyboard.nextInt();
		
		
		// We read a text file with BufferedReader and created an array that holds 
				// the text file's elements separately. The while loop's supply 
				// the necessary parameters to the create customer object with these informations.
		String nextLine = "";
		String[] info;
		String firstItem;	// Holds number of customer (first line of textfile)

		BufferedReader inFile = new BufferedReader(new FileReader(file));
		firstItem = inFile.readLine().trim(); // Taking the number of customer (read only first line)
		

		while ((nextLine = inFile.readLine()) != null) {
			info = nextLine.trim().split("\\s+"); // Splitting string on multiple spaces
			Customer customer = new Customer(Integer.parseInt(info[0]), 2022 - Integer.parseInt(info[1]), // taking year
																											// by after
																											// subtraction
																											// (2022 -
																											// year)
					Integer.parseInt(info[2]), Integer.parseInt(info[3])); // Creating customer objects considering in
																			// textFile values.
			arr.add(customer);
			//System.out.println(customer.year); provide to check creating customer objects correctly
		}

		inFile.close(); // close BufferedReader
		

		Customer c = new Customer(); // creating customer object

		c.findAvg(arr, pq, max_time);   // Call the important function which is calculate and print the avg time and informations.

	}

}
