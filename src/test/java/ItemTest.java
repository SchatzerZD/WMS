import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ItemTest {

  Item testItem;

  @BeforeEach
  void setUp() {
    testItem = new Item("A1005B","An item for testing","Dan AS",
            16,2,81.4,32.7,180.4,Color.YELLOW,Category.WINDOWS);
  }

  @Test
  void getItemNumber() {
    Assertions.assertEquals("A1005B",testItem.getItemNumber());
  }

  @Test
  void getDesc() {
    assertEquals("An item for testing",testItem.getDesc());
  }

  @Test
  void getBrandName() {
    assertEquals("Dan AS",testItem.getBrandName());
  }

  @Test
  void getPrice() {
    assertEquals(16,testItem.getPrice());
  }

  @Test
  void getWarehouseStock() {
    assertEquals(2,testItem.getWarehouseStock());
  }

  @Test
  void getWeight() {
    assertEquals(81.4,testItem.getWeight());
  }

  @Test
  void getLength() {
    assertEquals(32.7,testItem.getLength());
  }

  @Test
  void getHeight() {
    assertEquals(180.4,testItem.getHeight());
  }

  @Test
  void getColor() {
    assertEquals(Color.YELLOW,testItem.getColor());
  }

  @Test
  void getCategory() {
    assertEquals(Category.WINDOWS,testItem.getCategory());
  }

  @Test
  void setDesc() {
    testItem.setDesc("Testing setDesc() method");
    assertEquals("Testing setDesc() method",testItem.getDesc());
  }

  @Test
  void setPrice() {
    testItem.setPrice(24);
    assertEquals(24,testItem.getPrice());
  }

  @Test
  void setWarehouseStock() {
    testItem.setWarehouseStock(5);
    assertEquals(5,testItem.getWarehouseStock());
  }


  @Test
  void testIfIllegalArgumentExceptionIsThrown(){
    //Tests negative price value
    assertThrows(IllegalArgumentException.class, () ->
            new Item("A1005B","An item for testing","Dan AS",
            -16,2,81.4,32.7,180.4,Color.YELLOW,Category.WINDOWS));

    //Tests negative warehouseStock value
    assertThrows(IllegalArgumentException.class, () ->
            new Item("A1005B","An item for testing","Dan AS",
                    16,-2,81.4,32.7,180.4,Color.YELLOW,Category.WINDOWS));

    //Tests negative weight value
    assertThrows(IllegalArgumentException.class, () ->
            new Item("A1005B","An item for testing","Dan AS",
                    16,2,-81.4,32.7,180.4,Color.YELLOW,Category.WINDOWS));

    //Tests negative length value
    assertThrows(IllegalArgumentException.class, () ->
            new Item("A1005B","An item for testing","Dan AS",
                    16,2,81.4,-32.7,180.4,Color.YELLOW,Category.WINDOWS));

    //Tests negative height value
    assertThrows(IllegalArgumentException.class, () ->
            new Item("A1005B","An item for testing","Dan AS",
                    16,2,81.4,32.7,-180.4,Color.YELLOW,Category.WINDOWS));
  }

}