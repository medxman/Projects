
public class SubscriptionYear {
	
	private int year; // stores year for subscription data
	private double subscriptions; // stores the number of subscriptions for a specific year
	
	// constructor that takes in the year and subscriptions
	public SubscriptionYear(int year, double subscriptions){
		this.year = year;
		this.subscriptions = subscriptions;
	}
	
	// sets year
	public void setYear(int year){
		this.year = year;
	}
	
	// gets year
	public int getYear(){
		return year;
	}
	
	// sets subscriptions
	public void setSubscritpions(double subscritpions){
		this.subscriptions = subscritpions;
	}
	
	// gets subscriptions
	public double getSubscriptions(){
		return subscriptions;
	}
	
	// return only the number of subscriptions
	public String toString(){
		String subscriptions = Double.toString(this.subscriptions);
		
		return subscriptions;
	}

}
