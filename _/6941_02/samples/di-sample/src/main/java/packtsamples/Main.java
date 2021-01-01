package packtsamples;

public class Main {

	/**
	 * @param args. args[0] = taxRate, args[1] = rentRate, args[2] = noOfDays
	 */
	public static void main(String[] args) {
		RentCalculator rc = new RentCalculator(Float.parseFloat(args[1]), Float.parseFloat(args[0]));
		
		rc.calculateRent(Integer.parseInt(args[2]));
	}

}
