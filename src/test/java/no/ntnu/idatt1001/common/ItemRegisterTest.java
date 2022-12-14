package no.ntnu.idatt1001.common;

import no.ntnu.idatt1001.util.Category;
import no.ntnu.idatt1001.util.Color;
import no.ntnu.idatt1001.util.IllegalNumberException;
import no.ntnu.idatt1001.util.item.Item;
import no.ntnu.idatt1001.util.item.ItemBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A JUnit class for testing the {@link ItemRegister} class.
 */
class ItemRegisterTest {

  ItemRegister itemRegister;

  /**
   * The code that gets executed before each test operation.
   * Assigns a new {@link ItemRegister} to the {@code itemRegister} value and fills it with default
   * items with the {@link ItemRegister#fillListWithDefaultItems()} method for
   * further testing purposes.
   */
  @BeforeEach
  void setUp() {
    itemRegister = new ItemRegister();
    itemRegister.fillListWithDefaultItems();

  }

  /**
   * Test for the {@link ItemRegister#addItem(Item)} method.
   * Test will <code>PASS</code> if the item was added successfully
   * Test will <code>FAIL</code> if the item wasn't added successfully
   */
  @Test
  void addItem() {
    itemRegister.addItem(new ItemBuilder()
            .setItemNumber("A1215C")
            .setDescription("Large Christmas Window")
            .setBrandName("SULOLI")
            .setPrice(130)
            .setWarehouseStock(2)
            .setWeight(0.45)
            .setLength(15.8)
            .setHeight(27.5)
            .setColor(Color.WHITE)
            .setCategory(Category.WINDOWS)
            .setWidth(20)
            .build());

    Assertions.assertEquals(6,itemRegister.size());
    Item item = itemRegister.getItem(itemRegister.size() -1);

    assertEquals(item.getItemNumber(),"A1215C");
    assertEquals(item.getDescription(),"Large Christmas Window");
    assertEquals(item.getPrice(),130);
    assertEquals(item.getWeight(),0.45);

  }

  /**
   * Test for the {@link ItemRegister#searchByItemNumber(String)} method.
   * Test will <code>PASS</code> if the item was found successfully
   * Test will <code>FAIL</code> if the item wasn't found successfully
   */
  @Test
  void searchByItemNumber() {
    Item item = itemRegister.searchByItemNumber("a1205B");

    assertEquals(item.getItemNumber(),"A1205B");
    assertEquals(item.getDescription(),"Large Christmas Window");
    assertEquals(item.getPrice(),130);
    assertEquals(item.getWeight(),0.45);
  }

  /**
   * Test for the {@link ItemRegister#searchByItemDesc(String)} method.
   * Test will <code>PASS</code> if the items was found successfully
   * Test will <code>FAIL</code> if the items were not found successfully
   */
  @Test
  void searchByItemDesc() {
    List<Item> itemListFromSearch = itemRegister.searchByItemDesc("  LARGE ChristmAs Window");
    Item item = itemListFromSearch.get(0);

    assertEquals(item.getItemNumber(),"A1205B");
    assertEquals(item.getDescription(),"Large Christmas Window");
    assertEquals(item.getPrice(),130);
    assertEquals(item.getWeight(),0.45);

  }

  /**
   * Test for the {@link ItemRegister#searchByCategory(Category)} method.
   * Test will <code>PASS</code> if the items were found successfully
   * Test will <code>FAIL</code> if the items were not found successfully
   */
  @Test
  void searchByCategory(){
    List<Item> itemListFromSearch = itemRegister.searchByCategory(Category.WINDOWS);
    Item item = itemListFromSearch.get(0);

    assertEquals(item.getItemNumber(),"A1205B");
    assertEquals(item.getDescription(),"Large Christmas Window");
    assertEquals(item.getPrice(),130);
    assertEquals(item.getWeight(),0.45);
  }

  /**
   * Test for the {@link ItemRegister#increaseItemStock(Item, int)} method.
   * Test will <code>PASS</code> if the item stock was increased successfully
   * Test will <code>FAIL</code> if the item stock wasn't increased successfully
   */
  @Test
  void increaseItemStock() {
    itemRegister.increaseItemStock(itemRegister.searchByItemNumber("A1205B"),3);

    assertEquals(5,itemRegister.searchByItemNumber("A1205B").getWarehouseStock());

  }

  /**
   * Test for the {@link ItemRegister#decreaseItemStock(Item, int)} method.
   * Test will <code>PASS</code> if the item stock was decreased successfully
   * Test will <code>FAIL</code> if the item stock wasn't decreased successfully
   */
  @Test
  void decreaseItemStock() {

    itemRegister.decreaseItemStock(itemRegister.searchByItemNumber("A1205B"),1);

    assertEquals(1,itemRegister.searchByItemNumber("A1205B").getWarehouseStock());

  }

  /**
   * Test for the {@link ItemRegister#changePriceOfItem(Item, int)} method.
   * Test will <code>PASS</code> if the item price was changed successfully
   * Test will <code>FAIL</code> if the item price wasn't changed successfully
   */
  @Test
  void changePriceOfItem() {

    itemRegister.changePriceOfItem(itemRegister.searchByItemNumber("A1205B"),50);

    assertEquals(50,itemRegister.searchByItemNumber("A1205B").getPrice());

  }

  /**
   * Test for the {@link ItemRegister#changeDiscountOfItem(Item, double)} method.
   * Test will <code>PASS</code> if the item discount was changed successfully
   * Test will <code>FAIL</code> if the item discount wasn't changed successfully
   */
  @Test
  void changeDiscountOfItem() {

    itemRegister.changeDiscountOfItem(itemRegister.searchByItemNumber("A1205B"),20);

    Item item = itemRegister.searchByItemNumber("A1205B");

    assertEquals(20,item.getDiscount());
    assertEquals(104,item.getPrice());
  }

  /**
   * Test for the {@link ItemRegister#changeDescriptionOfItem(Item, String)} method.
   * Test will <code>PASS</code> if the item description was changed successfully
   * Test will <code>FAIL</code> if the item description wasn't changed successfully
   */
  @Test
  void changeDescriptionOfItem() {

    itemRegister.changeDescriptionOfItem(itemRegister.searchByItemNumber("A1205B"),"Test");

    assertEquals("Test",itemRegister.searchByItemNumber("A1205B").getDescription());
  }

  /**
   * Test for the {@link ItemRegister#getIndexOfItem(Item)} method.
   * Test will <code>PASS</code> if the item index was returned successfully
   * Test will <code>FAIL</code> if the item index wasn't returned successfully
   */
  @Test
  void getIndexOfItem() {

    assertEquals(0,itemRegister.getIndexOfItem(itemRegister.searchByItemNumber("A1205B")));

  }

  /**
   * Test for the {@link ItemRegister#removeItem(Item)} method.
   * Test will <code>PASS</code> if the item was removed successfully
   * Test will <code>FAIL</code> if the item wasn't removed successfully
   */
  @Test
  void removeItem() {

    assertTrue(itemRegister.removeItem(itemRegister.searchByItemNumber("A1205B")));
    assertEquals(4,itemRegister.size());

  }

  /**
   * Test for the {@link ItemRegister#sortListByItemnumber(boolean)} method.
   * Test will <code>PASS</code> if the list was sorted successfully
   * Test will <code>FAIL</code> if the list wasn't sorted successfully
   */
  @Test
  void sortListByItemnumber() {

    itemRegister.sortListByItemnumber(true);
    assertEquals("A1008B",itemRegister.getItem(0).getItemNumber());

    itemRegister.sortListByItemnumber(false);
    assertEquals("M5788B",itemRegister.getItem(0).getItemNumber());

  }

  /**
   * Test for the {@link ItemRegister#sortListByBrandname(boolean)} method.
   * Test will <code>PASS</code> if the list was sorted successfully
   * Test will <code>FAIL</code> if the list wasn't sorted successfully
   */
  @Test
  void sortListByBrandname() {

    itemRegister.sortListByBrandname(true);
    assertEquals("Achim",itemRegister.getItem(0).getBrandName());

    itemRegister.sortListByBrandname(false);
    assertEquals("SULOLI",itemRegister.getItem(0).getBrandName());

  }

  /**
   * Test for the {@link ItemRegister#sortListByPrice(boolean)} method.
   * Test will <code>PASS</code> if the list was sorted successfully
   * Test will <code>FAIL</code> if the list wasn't sorted successfully
   */
  @Test
  void sortListByPrice() {

    itemRegister.sortListByPrice(true);
    assertEquals(120,itemRegister.getItem(0).getPrice());

    itemRegister.sortListByPrice(false);
    assertEquals(977,itemRegister.getItem(0).getPrice());

  }

  /**
   * Test for the {@link ItemRegister#sortListByWarehousestock(boolean)} method.
   * Test will <code>PASS</code> if the list was sorted successfully
   * Test will <code>FAIL</code> if the list wasn't sorted successfully
   */
  @Test
  void sortListByWarehousestock() {

    itemRegister.sortListByWarehousestock(true);
    assertEquals(2,itemRegister.getItem(0).getWarehouseStock());

    itemRegister.sortListByWarehousestock(false);
    assertEquals(211,itemRegister.getItem(0).getWarehouseStock());

  }

  /**
   * Test for the {@link ItemRegister#sortListByColor(boolean)} method.
   * Test will <code>PASS</code> if the list was sorted successfully
   * Test will <code>FAIL</code> if the list wasn't sorted successfully
   */
  @Test
  void sortListByColor() {

    itemRegister.sortListByColor(true);
    assertEquals(Color.BLACK,itemRegister.getItem(0).getColor());

    itemRegister.sortListByColor(false);
    assertEquals(Color.WHITE,itemRegister.getItem(0).getColor());

  }

  /**
   * Test for the {@link ItemRegister#sortListByCategory(boolean)} method.
   * Test will <code>PASS</code> if the list was sorted successfully
   * Test will <code>FAIL</code> if the list wasn't sorted successfully
   */
  @Test
  void sortListByCategory() {

    itemRegister.sortListByCategory(true);
    assertEquals(Category.DOORS,itemRegister.getItem(0).getCategory());

    itemRegister.sortListByCategory(false);
    assertEquals(Category.WINDOWS,itemRegister.getItem(0).getCategory());

  }

  /**
   * Test for the {@link ItemRegister#getCopyOfList()} method.
   * Test will <code>PASS</code> if the list was deep-copied successfully
   * Test will <code>FAIL</code> if the list wasn't deep-copied successfully
   */
  @Test
  void getCopyOfList() {

    List<Item> itemList = itemRegister.getCopyOfList();

    Item deepCopy = itemList.get(0);
    Item item = itemRegister.getItem(0);

    assertNotEquals(deepCopy, item);
    assertEquals(deepCopy.getItemNumber(), item.getItemNumber());
    assertEquals(deepCopy.getBrandName(), item.getBrandName());
    assertEquals(deepCopy.getPrice(), item.getPrice());
    assertEquals(deepCopy.getColor(), item.getColor());

  }

  /**
   * Test for the {@link ItemRegister#getItem(int)} method.
   * Test will <code>PASS</code> if the item was returned successfully
   * Test will <code>FAIL</code> if the item wasn't returned successfully
   */
  @Test
  void getItem() {

    Item deepCopy = itemRegister.getItem(0);

    assertNotEquals(deepCopy, itemRegister.getItem(0));
    assertEquals(deepCopy.getItemNumber(), itemRegister.getItem(0).getItemNumber());
    assertEquals(deepCopy.getBrandName(), itemRegister.getItem(0).getBrandName());
    assertEquals(deepCopy.getPrice(), itemRegister.getItem(0).getPrice());
    assertEquals(deepCopy.getColor(), itemRegister.getItem(0).getColor());

  }

  /**
   * Test for the {@link ItemRegister#size()} method.
   * Test will <code>PASS</code> if the list size was returned successfully
   * Test will <code>FAIL</code> if the list size wasn't returned successfully
   */
  @Test
  void size() {

    assertEquals(5,itemRegister.size());

  }

  /**
   * Test for all the exceptions that can be thrown
   * in the methods.
   * Test will <code>PASS</code> if all exceptions were
   * thrown as expected
   * Test will <code>FAIL</code> if any exceptions were
   * thrown as unexpectedly
   */
  @Test
  void testExceptionsInMethods(){

    Item itemNotInList = new ItemBuilder()
            .setItemNumber("A1215C")
            .setDescription("Large Christmas Window")
            .setBrandName("SULOLI")
            .setPrice(130)
            .setWarehouseStock(2)
            .setWeight(0.45)
            .setLength(15.8)
            .setHeight(27.5)
            .setColor(Color.WHITE)
            .setCategory(Category.WINDOWS)
            .setWidth(20)
            .build();

    assertThrows(NullPointerException.class, () -> itemRegister.addItem(null));
    assertThrows(IllegalArgumentException.class, () ->
            itemRegister.addItem(new ItemBuilder()
            .setItemNumber("A1205B")
            .setDescription("Large Christmas Window")
            .setBrandName("SULOLI")
            .setPrice(130)
            .setWarehouseStock(2)
            .setWeight(0.45)
            .setLength(15.8)
            .setHeight(27.5)
            .setColor(Color.WHITE)
            .setCategory(Category.WINDOWS)
            .setWidth(20)
            .build()));


    assertThrows(NullPointerException.class, () -> itemRegister.increaseItemStock(null, 50));
    assertThrows(IllegalNumberException.class, () -> itemRegister.increaseItemStock(itemRegister.getItem(0), -2));
    assertThrows(NoSuchElementException.class, () -> itemRegister.increaseItemStock(itemNotInList,5));

    assertThrows(NullPointerException.class, () -> itemRegister.decreaseItemStock(null, 50));
    assertThrows(IllegalNumberException.class, () -> itemRegister.decreaseItemStock(itemRegister.getItem(0), -2));
    assertThrows(NoSuchElementException.class, () -> itemRegister.decreaseItemStock(itemNotInList,5));

    assertThrows(NullPointerException.class, () -> itemRegister.changePriceOfItem(null, 50));
    assertThrows(IllegalNumberException.class, () -> itemRegister.changePriceOfItem(itemRegister.getItem(0), -2));
    assertThrows(NoSuchElementException.class, () -> itemRegister.changePriceOfItem(itemNotInList,5));

    assertThrows(NullPointerException.class, () -> itemRegister.changeDiscountOfItem(null, 50));
    assertThrows(IllegalNumberException.class, () -> itemRegister.changeDiscountOfItem(itemRegister.getItem(0), -2));
    assertThrows(NoSuchElementException.class, () -> itemRegister.changeDiscountOfItem(itemNotInList,5));


    assertThrows(NullPointerException.class, () -> itemRegister.changeDescriptionOfItem(null,"Test"));
    assertThrows(NoSuchElementException.class, () -> itemRegister.changeDescriptionOfItem(itemNotInList,"Test"));


    assertThrows(NullPointerException.class, () -> itemRegister.getIndexOfItem(null));
    assertEquals(-1, itemRegister.getIndexOfItem(itemNotInList));


    assertThrows(NullPointerException.class, () -> itemRegister.removeItem(null));
    assertFalse(itemRegister.removeItem(itemNotInList));


    assertThrows(IndexOutOfBoundsException.class, () -> itemRegister.getItem(7));

  }
}