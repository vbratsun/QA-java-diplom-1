package praktikum;

import org.junit.Before;
import org.junit.Test;
import praktikum.constants.TestData;

import static org.junit.Assert.*;

public class BunTest {

    private Bun bun;

    @Before
    public void setUp() {
        bun = new Bun(TestData.BUN_NAME, TestData.BUN_MAX_PRICE);
    }

    @Test
    public void testGetName() {
        assertEquals(TestData.BUN_NAME, bun.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(TestData.BUN_MAX_PRICE, bun.getPrice(), TestData.DELTA);
    }
}
