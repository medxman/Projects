
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class CSVReader {
	
	String [] countries;
	String [] years;
	double [][] dataTable;
	
	public CSVReader(String filename){
		
		File file = new File(filename);
		
		try
		{
			Scanner myscanner = new Scanner(file);
			myscanner.nextLine(); //ignores the title line
			
			int countryCounter = 0;
			
			while(myscanner.hasNextLine()){
				String data = myscanner.nextLine();
				String [] values = data.split(",");
				
				if(values[0].equals("Number of countries")){ //this sets the number of countries
					// System.out.println("number of countries = " +values[1]);
					int numberOfCountries = Integer.parseInt(values[1]);
					countries = new String[numberOfCountries];
				}else if(values[0].equals("Country Name")){ // this sets the years array
					years = new String[((values.length)-1)];
					for (int i = 0; i<years.length;i++){
						years[i]=values[i+1];
					}
					dataTable = new double [countries.length][years.length];
				}else{ //now the actual data will be read into array
					   // values[0] is the country name and they rest are the data
					countries[countryCounter] = values[0];
				
					// this takes in the data and sets it to dataTable
					for(int i=0;i<years.length;i++){	
						dataTable[countryCounter][i] = Double.parseDouble(values[(i+1)]);
					}
					
					countryCounter++;
				}
			}
			myscanner.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File " + filename + " not found!");
		}
		
	}
	
	public String[] getCountryNames(){		
		return countries;
	}
	
	public int[] getYearLabels(){
		int [] intYearLabels = new int [years.length];
		for(int i =0; i<intYearLabels.length;i++){
			intYearLabels[i] = Integer.parseInt(years[i]);
		}
		return intYearLabels;
	}
	
	public double [][] getParsedTable(){
		return dataTable;
	}
	
	public int getNumberOfYears(){
		return years.length;
	}
	
}