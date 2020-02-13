package MortagePlan;

public class Customer{
	private double interest, year, loan;
	private String name;
	public Customer(String name, double loan, double interest, double year) {
		this.name = name;
		this.interest = interest;
		this.year = year;
		this.loan = loan;
	}
	
	public String getName() {
		return name;
	}
	
	public double getInterest() {
		return interest;
	}
	
	public double getYear() {
		return year;
	}
	
	public double getLoan() {
		return loan;
	}
	
	/**
	 * The calculation is split into several parts to make it
	 * easier to identify errors
	 * @return monthly payment
	 */
	public double getPayment() {
		double first = square((1+ interestPerMonth()), numberOfPayments());
		double second = square((1+interestPerMonth()), numberOfPayments());
		second -= 1;
		first*= interestPerMonth();
		first /= second;
		double third = loan * first;
		return third;
	}
	
	private double interestPerMonth(){
		return interest/100/12;
	}
	private double numberOfPayments() {
		return year*12;
	}
	private double square(double value, double squareValue) {
		double returnValue = value;
		for(int i = 1; i < squareValue; i++) {
			returnValue *= value;
		}
		return returnValue;
	}
}