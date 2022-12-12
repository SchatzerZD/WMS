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

class ItemRegisterTest {

  ItemRegister itemRegister;

  @BeforeEach
  void setUp() {
    itemRegister = new ItemRegister();
    itemRegister.fillListWithDefaultItems();

  }

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

  @Test
  void searchByItemNumber() {
    Item item = itemRegister.searchByItemNumber("a1205B");

    assertEquals(item.getItemNumber(),"A1205B");
    assertEquals(item.getDescription(),"Large Christmas Window");
    assertEquals(item.getPrice(),130);
    assertEquals(item.getWeight(),0.45);
  }

  @Test
  void searchByItemDesc() {
    List<Item> itemListFromSearch = itemRegister.searchByItemDesc("  LARGE ChristmAs Window");
    Item item = itemListFromSearch.get(0);

    assertEquals(item.getItemNumber(),"A1205B");
    assertEquals(item.getDescription(),"Large Christmas Window");
    assertEquals(item.getPrice(),130);
    assertEquals(item.getWeight(),0.45);

  }

  @Test
  void increaseItemStock() {
    itemRegister.increaseItemStock(itemRegister.searchByItemNumber("A1205B"),3);

    assertEquals(5,itemRegister.searchByItemNumber("A1205B").getWarehouseStock());

  }

  @Test
  void decreaseItemStock() {

    itemRegister.decreaseItemStock(itemRegister.searchByItemNumber("A1205B"),1);

    assertEquals(1,itemRegister.searchByItemNumber("A1205B").getWarehouseStock());

  }

  @Test
  void changePriceOfItem() {

    itemRegister.changePriceOfItem(itemRegister.searchByItemNumber("A1205B"),50);

    assertEquals(50,itemRegister.searchByItemNumber("A1205B").getPrice());

  }

  @Test
  void changeDiscountOfItem() {

    itemRegister.changeDiscountOfItem(itemRegister.searchByItemNumber("A1205B"),20);

    Item item = itemRegister.searchByItemNumber("A1205B");

    assertEquals(20,item.getDiscount());
    assertEquals(104,item.getPrice());
  }

  @Test
  void changeDescriptionOfItem() {

    itemRegister.changeDescriptionOfItem(itemRegister.searchByItemNumber("A1205B"),"Test");

    assertEquals("Test",itemRegister.searchByItemNumber("A1205B").getDescription());
  }

  @Test
  void getIndexOfItem() {

    assertEquals(0,itemRegister.getIndexOfItem(itemRegister.searchByItemNumber("A1205B")));

  }

  @Test
  void removeItem() {

    assertTrue(itemRegister.removeItem(itemRegister.searchByItemNumber("A1205B")));
    assertEquals(4,itemRegister.size());

  }

  @Test
  void sortListByItemnumber() {

    itemRegister.sortListByItemnumber(true);
    assertEquals("A1008B",itemRegister.getItem(0).getItemNumber());

    itemRegister.sortListByItemnumber(false);
    assertEquals("M5788B",itemRegister.getItem(0).getItemNumber());

  }

  @Test
  void sortListByBrandname() {

    itemRegister.sortListByBrandname(true);
    assertEquals("Achim",itemRegister.getItem(0).getBrandName());

    itemRegister.sortListByBrandname(false);
    assertEquals("SULOLI",itemRegister.getItem(0).getBrandName());

  }

  @Test
  void sortListByPrice() {

    itemRegister.sortListByPrice(true);
    assertEquals(120,itemRegister.getItem(0).getPrice());

    itemRegister.sortListByPrice(false);
    assertEquals(977,itemRegister.getItem(0).getPrice());

  }

  @Test
  void sortListByWarehousestock() {

    itemRegister.sortListByWarehousestock(true);
    assertEquals(2,itemRegister.getItem(0).getWarehouseStock());

    itemRegister.sortListByWarehousestock(false);
    assertEquals(211,itemRegister.getItem(0).getWarehouseStock());

  }

  @Test
  void sortListByColor() {

    itemRegister.sortListByColor(true);
    assertEquals(Color.BLACK,itemRegister.getItem(0).getColor());

    itemRegister.sortListByColor(false);
    assertEquals(Color.WHITE,itemRegister.getItem(0).getColor());

  }

  @Test
  void sortListByCategory() {

    itemRegister.sortListByCategory(true);
    assertEquals(Category.DOORS,itemRegister.getItem(0).getCategory());

    itemRegister.sortListByCategory(false);
    assertEquals(Category.WINDOWS,itemRegister.getItem(0).getCategory());

  }

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

  @Test
  void getItem() {

    Item deepCopy = itemRegister.getItem(0);

    assertNotEquals(deepCopy, itemRegister.getItem(0));
    assertEquals(deepCopy.getItemNumber(), itemRegister.getItem(0).getItemNumber());
    assertEquals(deepCopy.getBrandName(), itemRegister.getItem(0).getBrandName());
    assertEquals(deepCopy.getPrice(), itemRegister.getItem(0).getPrice());
    assertEquals(deepCopy.getColor(), itemRegister.getItem(0).getColor());

  }

  @Test
  void size() {

    assertEquals(5,itemRegister.size());

  }

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