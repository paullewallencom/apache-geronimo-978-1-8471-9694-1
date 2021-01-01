package packtsamples.di;

import java.lang.reflect.InvocationTargetException;

public class Assembler {
	
    private TaxCalculator createTaxCalculator(String className, float taxRate){
    	TaxCalculator tc = null;
    	try {
			Class cls = Class.forName(className);
			tc = (TaxCalculator)cls.getConstructors()[0].newInstance(taxRate);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return tc;
    }
    
    private RentCalculator createRentCalculator(float rate, TaxCalculator tCalc){
    	return new RentCalculator(rate,tCalc);
    }
    
    private void assembleAndExecute(String className, float taxRate, float rentRate, int noOfDays){
    	TaxCalculator tc = createTaxCalculator(className, taxRate);
    	createRentCalculator(rentRate, tc).calculateRent(noOfDays);    	
    }
    
    /**
     * 
     * @param args args[0] = className, args[1] = taxRate args[2] = rentRate args[3] = noOfDays
     */
    public static void main(String[] args){
    	new Assembler().assembleAndExecute(args[0], Float.parseFloat(args[1]), Float.parseFloat(args[2]), Integer.parseInt(args[3]));
    }
}
