import no.ntnu.idatt1001.util.Category;
import no.ntnu.idatt1001.util.Color;
import no.ntnu.idatt1001.util.Item;
import no.ntnu.idatt1001.util.ItemBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ItemTest {

  Item testItem;

  @BeforeEach
  void setUp() {
    testItem = new ItemBuilder()
            .setItemNumber("A1205B")
            .setDesc("Large Christmas Window")
            .setBrandName("SULOLI")
            .setPrice(130)
            .setWarehouseStock(2)
            .setWeight(0.45)
            .setLength(15.8)
            .setHeight(27.5)
            .setColor(Color.WHITE)
            .setCategory(Category.WINDOWS).build();
  }

  @Test
  void getItemNumber() {
    Assertions.assertEquals("A1205B",testItem.getItemNumber());
  }

  @Test
  void getDesc() {
    assertEquals("Large Christmas Window",testItem.getDesc());
  }

  @Test
  void getBrandName() {
    assertEquals("SULOLI",testItem.getBrandName());
  }

  @Test
  void getPrice() {
    assertEquals(130,testItem.getPrice());
  }

  @Test
  void getWarehouseStock() {
    assertEquals(2,testItem.getWarehouseStock());
  }

  @Test
  void getWeight() {
    assertEquals(0.45,testItem.getWeight());
  }

  @Test
  void getLength() {
    assertEquals(15.8,testItem.getLength());
  }

  @Test
  void getHeight() {
    assertEquals(27.5,testItem.getHeight());
  }

  @Test
  void getColor() {
    assertEquals(Color.WHITE,testItem.getColor());
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
            new ItemBuilder()
                    .setItemNumber("A1205B")
                    .setDesc("Large Christmas Window")
                    .setBrandName("SULOLI")
                    .setPrice(-130)
                    .setWarehouseStock(2)
                    .setWeight(0.45)
                    .setLength(15.8)
                    .setHeight(27.5)
                    .setColor(Color.WHITE)
                    .setCategory(Category.WINDOWS).build());

    //Tests negative warehouseStock value
    assertThrows(IllegalArgumentException.class, () ->
            new ItemBuilder()
                    .setItemNumber("A1205B")
                    .setDesc("Large Christmas Window")
                    .setBrandName("SULOLI")
                    .setPrice(130)
                    .setWarehouseStock(-2)
                    .setWeight(0.45)
                    .setLength(15.8)
                    .setHeight(27.5)
                    .setColor(Color.WHITE)
                    .setCategory(Category.WINDOWS).build());

    //Tests negative weight value
    assertThrows(IllegalArgumentException.class, () ->
            new ItemBuilder()
                    .setItemNumber("A1205B")
                    .setDesc("Large Christmas Window")
                    .setBrandName("SULOLI")
                    .setPrice(130)
                    .setWarehouseStock(2)
                    .setWeight(-0.45)
                    .setLength(15.8)
                    .setHeight(27.5)
                    .setColor(Color.WHITE)
                    .setCategory(Category.WINDOWS).build());

    //Tests negative length value
    assertThrows(IllegalArgumentException.class, () ->
            new ItemBuilder()
                    .setItemNumber("A1205B")
                    .setDesc("Large Christmas Window")
                    .setBrandName("SULOLI")
                    .setPrice(130)
                    .setWarehouseStock(2)
                    .setWeight(0.45)
                    .setLength(-15.8)
                    .setHeight(27.5)
                    .setColor(Color.WHITE)
                    .setCategory(Category.WINDOWS).build());

    //Tests negative height value
    assertThrows(IllegalArgumentException.class, () ->
            new ItemBuilder()
                    .setItemNumber("A1205B")
                    .setDesc("Large Christmas Window")
                    .setBrandName("SULOLI")
                    .setPrice(130)
                    .setWarehouseStock(2)
                    .setWeight(0.45)
                    .setLength(15.8)
                    .setHeight(-27.5)
                    .setColor(Color.WHITE)
                    .setCategory(Category.WINDOWS).build());

    //Tests negative price value in setPrice method
    assertThrows(IllegalArgumentException.class, () ->{
      Item illegalItem = new ItemBuilder()
              .setItemNumber("A1205B")
              .setDesc("Large Christmas Window")
              .setBrandName("SULOLI")
              .setPrice(130)
              .setWarehouseStock(2)
              .setWeight(0.45)
              .setLength(15.8)
              .setHeight(27.5)
              .setColor(Color.WHITE)
              .setCategory(Category.WINDOWS).build();

      illegalItem.setPrice(-5);
    });

    //Tests negative price value in setWarehouseStock method
    assertThrows(IllegalArgumentException.class, () ->{
      Item illegalItem = new ItemBuilder()
              .setItemNumber("A1205B")
              .setDesc("Large Christmas Window")
              .setBrandName("SULOLI")
              .setPrice(130)
              .setWarehouseStock(2)
              .setWeight(0.45)
              .setLength(15.8)
              .setHeight(27.5)
              .setColor(Color.WHITE)
              .setCategory(Category.WINDOWS).build();

      illegalItem.setWarehouseStock(-1);
    });

  }

}