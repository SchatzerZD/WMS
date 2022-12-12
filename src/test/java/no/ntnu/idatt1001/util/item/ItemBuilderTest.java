package no.ntnu.idatt1001.util.item;

import no.ntnu.idatt1001.util.Category;
import no.ntnu.idatt1001.util.Color;
import no.ntnu.idatt1001.util.IllegalNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemBuilderTest {

  ItemBuilder itemBuilder;

  @BeforeEach
  void setUp() {
    itemBuilder = new ItemBuilder();
    itemBuilder.setItemNumber("A1205B");
    itemBuilder.setDescription("Large Christmas Window");
    itemBuilder.setBrandName("SULOLI");
    itemBuilder.setPrice(130);
    itemBuilder.setWarehouseStock(2);
    itemBuilder.setWeight(0.45);
    itemBuilder.setLength(15.8);
    itemBuilder.setHeight(27.5);
    itemBuilder.setColor(Color.WHITE);
    itemBuilder.setCategory(Category.WINDOWS);
    itemBuilder.setWidth(20);
  }

  @Test
  void build() {
    Item item = itemBuilder.build();
    assertEquals(item.getItemNumber(),"A1205B");
    assertEquals(item.getDescription(),"Large Christmas Window");
    assertEquals(item.getPrice(),130);
    assertEquals(item.getWeight(),0.45);
    assertEquals(Color.WHITE,item.getColor());
  }

  @Test
  void deepCopy() {

    Item item = itemBuilder.build();
    Item deepCopy = ItemBuilder.deepCopy(item);

    assertNotEquals(deepCopy, item);
    assertEquals(deepCopy.getItemNumber(), item.getItemNumber());
    assertEquals(deepCopy.getBrandName(), item.getBrandName());
    assertEquals(deepCopy.getPrice(), item.getPrice());
    assertEquals(deepCopy.getColor(), item.getColor());

  }

  @Test
  void testExceptionsInMethods(){
    assertThrows(NullPointerException.class, () -> itemBuilder.setItemNumber(null));

    assertThrows(NullPointerException.class, () -> itemBuilder.setBrandName(null));

    assertThrows(NullPointerException.class, () -> itemBuilder.setColor(null));

    assertThrows(NullPointerException.class, () -> itemBuilder.setCategory(null));


    assertThrows(IllegalNumberException.class, () -> itemBuilder.setPrice(-5));

    assertThrows(IllegalNumberException.class, () -> itemBuilder.setWarehouseStock(-2));

    assertThrows(IllegalNumberException.class, () -> itemBuilder.setWeight(0));

    assertThrows(IllegalNumberException.class, () -> itemBuilder.setLength(-12));

    assertThrows(IllegalNumberException.class, () -> itemBuilder.setHeight(-20));
  }

}