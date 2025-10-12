package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.constants.TestData;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient cream;

    @Mock
    private Ingredient butter;

    @Mock
    private Ingredient jam;

    @Mock
    private Ingredient mango;


    @Before
    public void setUp() {
        burger = new Burger(); //  создание объекта
    }

    @Test
    public void testSetBuns() {
        when(bun.getName()).thenReturn(TestData.BUN_NAME);
        burger.setBuns(bun);
        assertEquals(TestData.BUN_NAME, burger.bun.getName());
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(cream);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(cream);
        burger.addIngredient(butter);
        burger.addIngredient(jam);
        burger.addIngredient(mango);
        assertEquals(4, burger.ingredients.size());
        burger.removeIngredient(2);
        assertEquals(3, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(cream);
        burger.addIngredient(butter);
        burger.addIngredient(jam);
        burger.addIngredient(mango);
        burger.moveIngredient(1, 2);
        assertEquals(butter, burger.ingredients.get(2));
        assertEquals(jam, burger.ingredients.get(1));
    }

    @Test
    public void testGetPrice() {
        burger.setBuns(bun);
        burger.addIngredient(cream);
        burger.addIngredient(butter);
        when(bun.getPrice()).thenReturn(TestData.BUN_PRICE);
        when(cream.getPrice()).thenReturn(TestData.CREAM_PRICE);
        when(butter.getPrice()).thenReturn(TestData.BUTTER_PRICE);
        float expectedPrice = TestData.BUN_PRICE * 2 + TestData.CREAM_PRICE + TestData.BUTTER_PRICE;
        assertEquals(expectedPrice, burger.getPrice(), TestData.DELTA);
    }

    @Test
    public void testGetReceipt() {
        when(bun.getName()).thenReturn(TestData.BUN_NAME);
        when(bun.getPrice()).thenReturn(TestData.BUN_PRICE);
        when(cream.getName()).thenReturn(TestData.CREAM_NAME);
        when(cream.getType()).thenReturn(TestData.CREAM_TYPE);
        when(cream.getPrice()).thenReturn(TestData.CREAM_PRICE);

        burger.setBuns(bun);
        burger.addIngredient(cream);

        String receipt = burger.getReceipt();

        float expectedTotalPrice = TestData.BUN_PRICE * 2 + TestData.CREAM_PRICE;

        String expectedReceipt = String.format(
                "(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n",
                TestData.BUN_NAME,
                TestData.CREAM_TYPE.toString().toLowerCase(),
                TestData.CREAM_NAME,
                TestData.BUN_NAME,
                expectedTotalPrice
        );

        assertEquals(expectedReceipt, receipt);
    }
}