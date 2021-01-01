package packtsamples.di;

public class RentCalculator{

    private float rentRate;

    private TaxCalculator tCalc;

    public  RentCalculator(float rate, TaxCalculator tCalc){
        rentRate = rate;
        this.tCalc = tCalc;        
    }
    
    public void calculateRent(int noOfDays){
        float totalRent = noOfDays * rentRate;
        float tax = tCalc.calculateTax(totalRent);
        totalRent = totalRent + tax;
        System.out.println("Rent is :"+totalRent);
    }

}
