package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient;


    @Test
    public void setBunsTest() {
        Burger burger = new Burger();

        burger.setBuns(bun);
        Bun actual = burger.bun;
        Bun expected = bun;

        assertEquals("bun was successfully returned", expected, actual);
    }


    @Test
    public void addIngredientTest() {
        Burger burger = new Burger();

        burger.addIngredient(ingredient);
        List<Ingredient> actual = burger.ingredients;
        List<Ingredient> expected = new ArrayList<>();
        expected.add(ingredient);

        assertEquals("ingredient was successfully added", expected, actual);
    }


    @Test
    public void removeIngredientTest() {
        Burger burger = new Burger();

        burger.addIngredient(new Ingredient(SAUCE, "chili sauce", 122));
        burger.addIngredient(new Ingredient(FILLING, "cutlet", 346));
        burger.removeIngredient(0);
        List<Ingredient> actual = burger.ingredients;

        List<Ingredient> expected = new ArrayList<>();
        expected.add(new Ingredient(SAUCE, "chili sauce", 122));
        expected.add(new Ingredient(FILLING, "cutlet", 346));
        expected.remove(0);

        assertEquals("ingredient was removed from ingredients set", expected.size(), actual.size());
        assertEquals("right ingredient was removed, ingredient price is correct", expected.get(0).price, actual.get(0).price, 0);
        assertEquals("right ingredient was removed, ingredient name is correct", expected.get(0).name, actual.get(0).name);
        assertEquals("right ingredient was removed, ingredient type is correct", expected.get(0).type, actual.get(0).type);
    }


    @Test
    public void moveIngredient() {
        Burger burger = new Burger();

        burger.addIngredient(new Ingredient(SAUCE, "dinosaur", 888));
        burger.addIngredient(new Ingredient(FILLING, "sausage", 777));
        burger.moveIngredient(0, 1);
        List<Ingredient> actual = burger.ingredients;

        List<Ingredient> expected = new ArrayList<>();
        expected.add(new Ingredient(SAUCE, "dinosaur", 888));
        expected.add(new Ingredient(FILLING, "sausage", 777));
        expected.add(1, expected.remove(0));

        assertEquals("ingredients was successfully moved in ingredients set", expected.size(), expected.size());
        assertEquals("ingredients was moved right, ingredient price is correct", expected.get(0).price, actual.get(0).price, 0);
        assertEquals("ingredients was moved right, ingredient name is correct", expected.get(0).name, actual.get(0).name);
        assertEquals("ingredients was moved right, ingredient type is correct", expected.get(0).type, actual.get(0).type);
    }

    @Test
    public void getPriceTest() {

        Burger burger = new Burger();

        List<Bun> buns = new ArrayList<>();
        buns.add(bun);
        buns.add(bun);
        burger.setBuns(buns.get(0));
        burger.setBuns(buns.get(1));

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        ingredients.add(ingredient);
        burger.addIngredient(ingredients.get(0));
        burger.addIngredient(ingredients.get(1));

        float bunPrice = 100;
        float ingredientPrice = 200;
        Mockito.when(bun.getPrice()).thenReturn(ingredientPrice);
        Mockito.when(ingredient.getPrice()).thenReturn(bunPrice);

        float actual = burger.getPrice();
        float expected = 600;
        assertEquals("price of burger is correct", expected, actual, 0);
    }

    @Test
    public void getReceiptTest() {

        Burger burger = new Burger();

        List<Bun> buns = new ArrayList<>();
        buns.add(bun);
        buns.add(bun);
        burger.setBuns(buns.get(0));
        burger.setBuns(buns.get(1));

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        ingredients.add(ingredient);
        burger.addIngredient(ingredients.get(0));
        burger.addIngredient(ingredients.get(1));

        float price = 600;
        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(ingredient.getName()).thenReturn("hot sauce");
        Mockito.when(ingredient.getType()).thenReturn(SAUCE);
        Mockito.when(burger.getPrice()).thenReturn(price);

        String actual = burger.getReceipt();
        String expected = String.format("(==== black bun ====)%n") +
                String.format("= sauce hot sauce =%n") +
                String.format("= sauce hot sauce =%n") +
                String.format("(==== black bun ====)%n") +
                String.format("%n") +
                String.format("Price: 1200,000000%n");
        assertEquals("price of burger is correct", expected, actual);
    }
}
