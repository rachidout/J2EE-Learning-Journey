package metier;

public class CreditMetierIMP implements ICreditMetier{
	
	@Override
	public double calculerM(double c , double t , int d) {
		   double tt = t/100;
		   double t1 = c * tt/12;
		   double t2 =  1- Math.pow(1+tt/12, -d);
		return t1 / t2;
	}
	
  
}
