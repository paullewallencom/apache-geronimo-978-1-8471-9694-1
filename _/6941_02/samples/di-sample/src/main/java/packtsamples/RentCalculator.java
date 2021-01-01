package packtsamples;

public class RentCalculator{

    private float rentRate;

    private TaxCalculator tCalc;

    public  RentCalculator(float rate, float taxRate){
        rentRate = rate;
        tCalc = new ServiceTaxCalculator(taxRate);
    }
    
    public void calculateRent(int noOfDays){
        float totalRent = noOfDays * rentRate;
        float tax = tCalc.calculateTax(totalRent);
        totalRent = totalRent + tax;
        System.out.println("Rent is :"+totalRent);
    }

}
