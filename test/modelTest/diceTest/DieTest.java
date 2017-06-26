package modelTest.diceTest;

import org.junit.Before;
import org.junit.Test;

import model.dice.Die;

public class DieTest {

	private Die theDie;

	@Before
	public void SetUp() {
		this.theDie = new Die();
	}

	private int[] popuateIntArray() {

		int[] values = new int[1000];

		for (int i = 0; i < 1000; i++) {
			values[i] = this.theDie.roll();
		}
		return values;
	}
	
	@Test
	public void checkUnrolledDieIsNegativeOne() {
		assert(this.theDie.getLastRoll() == -1);
	}

	@Test
	public void testIfValueBetweenOneAndSix() {
		
		int[] values = this.popuateIntArray();
		
		for (int i = 0; i < values.length; i++) {
			assert(values[i] >= 1 && values[i] <= 6);
		}
	}
}
