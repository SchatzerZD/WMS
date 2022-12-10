package no.ntnu.idatt1001.common;

import no.ntnu.idatt1001.util.Category;
import no.ntnu.idatt1001.util.Color;
import no.ntnu.idatt1001.util.item.Item;
import no.ntnu.idatt1001.util.item.ItemBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
            .setCategory(Category.WINDOWS).build());

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
    Item item = itemRegister.searchByItemDesc("  LARGE ChristmAs Window");

    assertEquals(item.getItemNumber(),"A1205B");
    assertEquals(item.getDescription(),"Large Christmas Window");
    assertEquals(item.getPrice(),130);
    assertEquals(item.getWeight(),0.45);

  }

  @Test
  void increaseItemStock() {
    itemRegister.increaseItemStock("A1205B",3);

    assertEquals(5,itemRegister.searchByItemNumber("A1205B").getWarehouseStock());

  }

  @Test
  void decreaseItemStock() {

    itemRegister.decreaseItemStock("A1205B",1);

    assertEquals(1,itemRegister.searchByItemNumber("A1205B").getWarehouseStock());

  }

  @Test
  void changePriceOfItem() {

    itemRegister.changePriceOfItem("A1205B",50);

    assertEquals(50,itemRegister.searchByItemNumber("A1205B").getPrice());

  }

  @Test
  void changeDiscountOfItem() {

    itemRegister.changeDiscountOfItem("A1205B",20);

    Item item = itemRegister.searchByItemNumber("A1205B");

    assertEquals(20,item.getDiscount());
    assertEquals(104,item.getPrice());
  }

  @Test
  void changeDescriptionOfItem() {

    itemRegister.changeDescriptionOfItem("A1205B","Test");

    assertEquals("Test",itemRegister.searchByItemNumber("A1205B").getDescription());
  }

  @Test
  void getIndexOfItem() {

    assertEquals(0,itemRegister.getIndexOfItem("A1205B"));

  }

  @Test
  void removeItem() {
  }

  @Test
  void sortListByItemnumber() {
  }

  @Test
  void sortListByBrandname() {
  }

  @Test
  void sortListByPrice() {
  }

  @Test
  void sortListByWarehousestock() {
  }

  @Test
  void sortListByColor() {
  }

  @Test
  void sortListByCategory() {
  }

  @Test
  void getCopyOfList() {
  }

  @Test
  void getItem() {
  }

  @Test
  void size() {
  }
}