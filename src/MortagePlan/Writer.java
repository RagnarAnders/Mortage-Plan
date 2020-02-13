package MortagePlan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Writer {

	private ArrayList<Customer> customers;
	/**
	 * This method reads the file named in new File("Mortage Plan.txt");
	 * then returns a list of the correct added customers from the file.
	 * @return A list of Strings representing the customers.
	 */
	private ArrayList<String> readFile() {
		ArrayList<String> values = new ArrayList<>();
		try {
			File myObj = new File("prospects.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				values.add(myReader.nextLine());
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return values;
	}

	/**
	 * Creates a file if it does not already exist.
	 */
	private void createFile() {
		try {
			File myObj = new File("Mortage Plan.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	/**
	 * Reads and create customers from the parameter.
	 * Breaks the list of strings into four parts, first the name 
	 * then the number of loan, interest and year. Finally adding the
	 * customer to the list.
	 * @param values A list of strings
	 */
	public void saveCustomers() {
		ArrayList<String> values = readFile();
		Scanner scan = new Scanner(System.in);
		String[] customerInfo;
		createFile();
		if(customers == null) {
			customers = new ArrayList<Customer>();
		}
		try {
			for (int i = 1; i < values.size(); i++) {
				customerInfo = values.get(i).toString().split(",");
				String name = customerInfo[0];
				if (name.equals("")) {
					break;
				}
				try {
					double loan = Double.parseDouble(customerInfo[1]);
					double interest = Double.parseDouble(customerInfo[2]);
					double year = Double.parseDouble(customerInfo[3]);
					customers.add(new Customer(name, loan, interest, year));
				} catch (NumberFormatException e) {
					System.out.println(e);
				}
			}
			scan.close();
		}catch(NumberFormatException e) {
			System.out.println(e);
		}
	}

	public void addCustomer(String name, double loan, double interest, double year) {
		customers.add(new Customer(name,loan,interest,year));
	}

	/**
	 * This method goes throw the list of customers, put them in a string and
	 * writes them into a document.
	 * 
	 * @author Anders Ragnar
	 *
	 */
	public void writeCustomers() {
		String customer = "";
		int i = 0;
		if(customers == null) {
			System.out.println("No customers");
			return;
		}
			try {
				FileWriter myWriter = new FileWriter("Mortage Plan.txt");
				
				DecimalFormat df = new DecimalFormat ("#.##");
				
				for(Customer c: customers) {
					i++;
					customer += "Prospect " +i+ ": " + c.getName() + " wants to borrow " + c.getLoan() + "€ for a period of " 
							+ c.getYear() + " years and pay " + df.format(c.getPayment()) + "€ each mont\n";					
				}
				myWriter.write(customer);
				myWriter.close();
				System.out.println("File written");
			}catch(NumberFormatException e) {
				System.out.println(e);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
