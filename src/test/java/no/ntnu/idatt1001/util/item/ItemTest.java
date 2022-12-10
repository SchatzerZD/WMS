package no.ntnu.idatt1001.util.item;

import no.ntnu.idatt1001.util.Category;
import no.ntnu.idatt1001.util.Color;
import no.ntnu.idatt1001.util.IllegalNumberException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

  Item item;

  @BeforeEach
  void setUp() {
    item = new Item("A1205B","Large Christmas Window","SULOLI",
            130,2,0.45,15.8,27.5,Color.WHITE,Category.WINDOWS);

  }

  @Test
  void getItemNumber() {
    Assertions.assertEquals("A1205B",item.getItemNumber());
  }

  @Test
  void getDescription() {
    Assertions.assertEquals("Large Christmas Window",item.getDescription());
  }

  @Test
  void getBrandName() {
    Assertions.assertEquals("SULOLI",item.getBrandName());
  }

  @Test
  void getPrice() {
    Assertions.assertEquals(130,item.getPrice());
  }

  @Test
  void getDiscount() {
    Assertions.assertEquals(0,item.getDiscount());
  }

  @Test
  void getWarehouseStock() {
    Assertions.assertEquals(2,item.getWarehouseStock());
  }

  @Test
  void getWeight() {
    Assertions.assertEquals(0.45,item.getWeight());
  }

  @Test
  void getLength() {
    Assertions.assertEquals(15.8,item.getLength());
  }

  @Test
  void getHeight() {
    Assertions.assertEquals(27.5,item.getHeight());
  }

  @Test
  void getColor() {
    Assertions.assertEquals(Color.WHITE,item.getColor());
  }

  @Test
  void getCategory() {
    Assertions.assertEquals(Category.WINDOWS,item.getCategory());
  }

  @Test
  void setDescription() {
    item.setDescription("Test");
    Assertions.assertEquals("Test",item.getDescription());
  }

  @Test
  void setPrice() {
    item.setPrice(50);
    Assertions.assertEquals(50,item.getPrice());
  }

  @Test
  void setDiscount() {
    item.setDiscount(20);
    Assertions.assertEquals(20,item.getDiscount());
    Assertions.assertEquals(104,item.getPrice());
  }

  @Test
  void setWarehouseStock() {
    item.setWarehouseStock(5);
    Assertions.assertEquals(5,item.getWarehouseStock());
  }

  @Test
  void testDeepCopyConstructor(){
    Item deepCopy = new Item(item);

    assertNotEquals(deepCopy, item);
    assertEquals(deepCopy.getItemNumber(), item.getItemNumber());
    assertEquals(deepCopy.getBrandName(), item.getBrandName());
    assertEquals(deepCopy.getPrice(), item.getPrice());
    assertEquals(deepCopy.getColor(), item.getColor());

  }

  @Test
  void testExceptionsInConstructor(){

    assertThrows(NullPointerException.class, () ->
            new Item(null,"Large Christmas Window","SULOLI",
            130,2,0.45,15.8,27.5,Color.WHITE,Category.WINDOWS));

    assertThrows(NullPointerException.class, () ->
            new Item("A1205B","Large Christmas Window","SULOLI",
                    130,2,0.45,15.8,27.5,null,Category.WINDOWS));

    assertThrows(NullPointerException.class, () ->
            new Item("A1205B","Large Christmas Window","SULOLI",
                    130,2,0.45,15.8,27.5,Color.WHITE,null));

    assertThrows(NullPointerException.class, () ->
            new Item(null));


    assertThrows(IllegalNumberException.class, () ->
            new Item("A1205B","Large Christmas Window","SULOLI",
                    -5,2,0.45,15.8,27.5,Color.WHITE,Category.WINDOWS));

    assertThrows(IllegalNumberException.class, () ->
            new Item("A1205B","Large Christmas Window","SULOLI",
                    130,-2,0.45,15.8,27.5,Color.WHITE,Category.WINDOWS));

    assertThrows(IllegalNumberException.class, () ->
            new Item("A1205B","Large Christmas Window","SULOLI",
                    130,2,-0.45,15.8,27.5,Color.WHITE,Category.WINDOWS));

    assertThrows(IllegalNumberException.class, () ->
            new Item("A1205B","Large Christmas Window","SULOLI",
                    130,2,0.45,-15.8,27.5,Color.WHITE,Category.WINDOWS));

    assertThrows(IllegalNumberException.class, () ->
            new Item("A1205B","Large Christmas Window","SULOLI",
                    130,2,0.45,15.8,-27.5,Color.WHITE,Category.WINDOWS));


  }

  @Test
  void testExceptionsInMethods(){

    assertThrows(IllegalNumberException.class, () -> item.setPrice(-5));

    assertThrows(IllegalNumberException.class, () -> item.setDiscount(102));

    assertThrows(IllegalNumberException.class, () -> item.setDiscount(-2));

    assertThrows(IllegalNumberException.class, () -> item.setWarehouseStock(-15));



  }

}