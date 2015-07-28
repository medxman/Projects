
public class Country {
	
	private String name;
	private LinkedList<SubscriptionYear> subscriptions;
	private int counter;
	
	private int minYear;
	private int maxYear;
	
	private double maxData;
	private double minData;
	
	//constructor that takes in name => now creates a linkedList to hold the subscriptionyear
	public Country(String name){
		this.name = name;
		subscriptions = new LinkedList<SubscriptionYear>();
		counter = 0;
		minYear = 0;
		maxYear = 0;
		minData = 0;
		maxData = 0;
	}
	
	//adds SubscriptionYear to LinkedList
	public void addSubscriptionYear(int year, double singleSubscription){
		SubscriptionYear newSub = new SubscriptionYear(year, singleSubscription);
		subscriptions.add(newSub);
		counter++;
		
		// Sets variables minYear, maxYear, minData, maxData while the list is being set up
		//sets min year
		if (minYear == 0){
			minYear = year;
		} else if (minYear > year){
			minYear = year;
		}
		//sets max year
		if (maxYear == 0){
			maxYear = year;
		} else if (maxYear < year){
			maxYear = year;
		}
		//sets min data
		
		//sets maxData
		if (maxData == 0){
			maxData = singleSubscription;
		} else if (maxData < singleSubscription){
			maxData = singleSubscription;
		}
		
	}
	
	public String getName(){
		return name;
	}
	
	public double getMinData(){
		return minData;
	}
	
	public double getMaxData(){
		return maxData;
	}
	
	public int getMinYear(){
		return minYear;
	}
	
	public int getMaxYear(){
		return maxYear;
	}
	
	
	public 	LinkedList<SubscriptionYear> getSubs(){
		return subscriptions;
	}
	
	public String toString(){
		String message = "";
		message += name;
		message += "	";
		message += subscriptions.toString();
		return message;
	}
}
