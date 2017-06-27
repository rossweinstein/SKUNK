package modelTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Kitty;

public class KittyTest {
	
	private Kitty theKitty;
	
	@Before
	public void SetUp() {
		this.theKitty = new Kitty();
	}

	@Test
	public void kittyStartsAtZero() {
		assertTrue(this.theKitty.getAmount() == 0);
	}
	
	@Test
	public void kittyAddsChipsCorrectly() {
		this.theKitty.addToKitty(10);
		this.theKitty.addToKitty(5);
		this.theKitty.addToKitty(2);
		assertTrue(this.theKitty.getAmount() == 17);
	}
	
	@Test
	public void kittyClearsChipsCorrectly() {
		this.theKitty.addToKitty(10);
		this.theKitty.addToKitty(5);
		this.theKitty.addToKitty(2);
		this.theKitty.paidOutKitty();
		assertTrue(this.theKitty.getAmount() == 0);
	}

}
