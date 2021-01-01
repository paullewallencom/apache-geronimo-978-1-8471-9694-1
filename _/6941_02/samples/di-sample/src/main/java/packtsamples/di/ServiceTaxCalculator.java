package packtsamples.di;

public class ServiceTaxCalculator implements TaxCalculator{

    private float taxRate;

    public ServiceTaxCalculator(float rate){
        taxRate = rate;
    }

    public float calculateTax(float amount){
        return (amount * taxRate/100);
    }
}
