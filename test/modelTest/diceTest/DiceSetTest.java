package modelTest.diceTest;

import org.junit.Before;
import org.junit.Test;

import model.dice.DiceSet;

public class DiceSetTest {

	private DiceSet dice;

	@Before
	public void SetUp() {
		this.dice = new DiceSet();
	}

	private int[] popuateIntArray() {

		int[] values = new int[1000];

		for (int i = 0; i < 1000; i++) {
			
			int[] rollValue = this.dice.roll();
			values[i] = rollValue[0] + rollValue[1];
		}
		return values;
	}

	@Test
	public void testIfValueBetweenTwoAndTwelve() {
		
		int[] values = this.popuateIntArray();
		
		for (int i = 0; i < values.length; i++) {
			assert(values[i] >= 2 && values[i] <= 12);
		}
	}
}