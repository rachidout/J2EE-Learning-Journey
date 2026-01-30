package metier;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestcalculerM {
        private ICreditMetier metier;
	@Before
	public void setUp() throws Exception {
		metier = new CreditMetierIMP();
	}

	@Test
	public void testCalculerM() {
		double c =200000;
		int d=240;
		double t= 4.5;
		
		double ResAtt = 1265.2987;
		double resCall = metier.calculerM(c, t, d); 

		assertEquals(ResAtt , resCall , 0.0001); // resAtt - resCall
		
	}

}
 