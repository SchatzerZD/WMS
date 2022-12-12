package no.ntnu.idatt1001.util.item;

import no.ntnu.idatt1001.util.Category;
import no.ntnu.idatt1001.util.Color;
import no.ntnu.idatt1001.util.IllegalNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A JUnit test class for the {@link ItemBuilder} class.
 */
class ItemBuilderTest {

  ItemBuilder itemBuilder;

  /**
   * The code that gets executed before each test operation.
   * Assigns a new {@link ItemBuilder} to the {@code itemBuilder} value for
   * further testing purposes.
   */
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

  /**
   * Test for the {@link ItemBuilder#build()} method.
   * Test will <code>PASS</code> if the field variables matches
   * Test will <code>FAIL</code> if the field variables doesn't match
   */
  @Test
  void build() {
    Item item = itemBuilder.build();
    assertEquals(item.getItemNumber(),"A1205B");
    assertEquals(item.getDescription(),"Large Christmas Window");
    assertEquals(item.getPrice(),130);
    assertEquals(item.getWeight(),0.45);
    assertEquals(Color.WHITE,item.getColor());
  }

  /**
   * Test for the {@link ItemBuilder#deepCopy(Item)} method for deep-copying
   * an item.
   * Test will <code>PASS</code> if the new item doesn't equal the
   * previous item, and if all field values are the same
   * Test will <code>FAIL</code> if the new item equals the
   * previous item or if all field values are not the same
   */
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