package no.ntnu.idatt1001.util.item;

import no.ntnu.idatt1001.util.Category;
import no.ntnu.idatt1001.util.Color;
import no.ntnu.idatt1001.util.IllegalNumberException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A JUnit test class for the {@link Item} class.
 */
class ItemTest {

  Item item;

  /**
   * The code that gets executed before each test operation.
   * Assigns a new {@link Item} to the {@code item} value for
   * further testing purposes.
   */
  @BeforeEach
  void setUp() {
    item = new Item("A1205B","Large Christmas Window","SULOLI",
            130,2,0.45,15.8,27.5,20, Color.WHITE,Category.WINDOWS);

  }

  /**
   * Test for the {@link Item#getItemNumber()} method.
   * Test will <code>PASS</code> if the {@code itemNumber} matches
   * Test will <code>FAIL</code> if the {@code itemNumber} doesn't match
   */
  @Test
  void getItemNumber() {
    Assertions.assertEquals("A1205B",item.getItemNumber());
  }

  /**
   * Test for the {@link Item#getDescription()} method.
   * Test will <code>PASS</code> if the {@code description} matches
   * Test will <code>FAIL</code> if the {@code description} doesn't match
   */
  @Test
  void getDescription() {
    Assertions.assertEquals("Large Christmas Window",item.getDescription());
  }

  /**
   * Test for the {@link Item#getBrandName()} method.
   * Test will <code>PASS</code> if the {@code brandName} matches
   * Test will <code>FAIL</code> if the {@code brandName} doesn't match
   */
  @Test
  void getBrandName() {
    Assertions.assertEquals("SULOLI",item.getBrandName());
  }

  /**
   * Test for the {@link Item#getPrice()} method.
   * Test will <code>PASS</code> if the {@code price} matches
   * Test will <code>FAIL</code> if the {@code price} doesn't match
   */
  @Test
  void getPrice() {
    Assertions.assertEquals(130,item.getPrice());
  }

  /**
   * Test for the {@link Item#getDiscount()} method.
   * Test will <code>PASS</code> if the {@code discount} matches
   * Test will <code>FAIL</code> if the {@code discount} doesn't match
   */
  @Test
  void getDiscount() {
    Assertions.assertEquals(0,item.getDiscount());
  }

  /**
   * Test for the {@link Item#getWarehouseStock()} method.
   * Test will <code>PASS</code> if the {@code warehouseStock} matches
   * Test will <code>FAIL</code> if the {@code warehouseStock} doesn't match
   */
  @Test
  void getWarehouseStock() {
    Assertions.assertEquals(2,item.getWarehouseStock());
  }

  /**
   * Test for the {@link Item#getWeight()} method.
   * Test will <code>PASS</code> if the {@code weight} matches
   * Test will <code>FAIL</code> if the {@code weight} doesn't match
   */
  @Test
  void getWeight() {
    Assertions.assertEquals(0.45,item.getWeight());
  }

  /**
   * Test for the {@link Item#getLength()} method.
   * Test will <code>PASS</code> if the {@code length} matches
   * Test will <code>FAIL</code> if the {@code length} doesn't match
   */
  @Test
  void getLength() {
    Assertions.assertEquals(15.8,item.getLength());
  }

  /**
   * Test for the {@link Item#getHeight()} method.
   * Test will <code>PASS</code> if the {@code height} matches
   * Test will <code>FAIL</code> if the {@code height} doesn't match
   */
  @Test
  void getHeight() {
    Assertions.assertEquals(27.5,item.getHeight());
  }

  /**
   * Test for the {@link Item#getWidth()} method.
   * Test will <code>PASS</code> if the {@code width} matches
   * Test will <code>FAIL</code> if the {@code width} doesn't match
   */
  @Test
  void getWidth(){
    assertEquals(20, item.getWidth());
  }

  /**
   * Test for the {@link Item#getColor()} method.
   * Test will <code>PASS</code> if the {@link Color} matches
   * Test will <code>FAIL</code> if the {@link Color} doesn't match
   */
  @Test
  void getColor() {
    Assertions.assertEquals(Color.WHITE,item.getColor());
  }

  /**
   * Test for the {@link Item#getCategory()} method.
   * Test will <code>PASS</code> if the {@link Category} matches
   * Test will <code>FAIL</code> if the {@link Category} doesn't match
   */
  @Test
  void getCategory() {
    Assertions.assertEquals(Category.WINDOWS,item.getCategory());
  }

  /**
   * Test for the {@link Item#setDescription(String)} method.
   * Test will <code>PASS</code> if the set {@code description} matches with
   * the specified {@code description}
   * Test will <code>FAIL</code> if the set {@code description} doesn't match with
   * the specified {@code description}
   */
  @Test
  void setDescription() {
    item.setDescription("Test");
    Assertions.assertEquals("Test",item.getDescription());
  }

  /**
   * Test for the {@link Item#setPrice(int)} method.
   * Test will <code>PASS</code> if the set {@code price} matches with
   * the specified {@code price}
   * Test will <code>FAIL</code> if the set {@code price} doesn't match with
   * the specified {@code price}
   */
  @Test
  void setPrice() {
    item.setPrice(50);
    Assertions.assertEquals(50,item.getPrice());
  }

  /**
   * Test for the {@link Item#setDiscount(double)} method.
   * Test will <code>PASS</code> if the set {@code discount} matches with
   * the specified {@code discount}
   * Test will <code>FAIL</code> if the set {@code discount} doesn't match with
   * the specified {@code discount}
   */
  @Test
  void setDiscount() {
    item.setDiscount(20);
    Assertions.assertEquals(20,item.getDiscount());
    Assertions.assertEquals(104,item.getPrice());
  }

  /**
   * Test for the {@link Item#setWarehouseStock(int)} method.
   * Test will <code>PASS</code> if the set {@code warehouseStock} matches with
   * the specified {@code warehouseStock}
   * Test will <code>FAIL</code> if the set {@code warehouseStock} doesn't match with
   * the specified {@code warehouseStock}
   */
  @Test
  void setWarehouseStock() {
    item.setWarehouseStock(5);
    Assertions.assertEquals(5,item.getWarehouseStock());
  }

  /**
   * Test for the {@link Item} constructor for deep-copying
   * an item.
   * Test will <code>PASS</code> if the new item doesn't equal the
   * previous item, and if all field values are the same
   * Test will <code>FAIL</code> if the new item equals the
   * previous item or if all field values are not the same
   */
  @Test
  void testDeepCopyConstructor(){
    Item deepCopy = new Item(item);

    assertNotEquals(deepCopy, item);
    assertEquals(deepCopy.getItemNumber(), item.getItemNumber());
    assertEquals(deepCopy.getBrandName(), item.getBrandName());
    assertEquals(deepCopy.getPrice(), item.getPrice());
    assertEquals(deepCopy.getColor(), item.getColor());

  }

  /**
   * Test for all the exceptions that can be thrown in
   * the constructor.
   * Test will <code>PASS</code> if all exceptions were
   * thrown as expected
   * Test will <code>FAIL</code> if any exceptions were
   * thrown as unexpectedly
   */
  @Test
  void testExceptionsInConstructor(){

    assertThrows(NullPointerException.class, () ->
            new Item(null,"Large Christmas Window","SULOLI",
            130,2,0.45,15.8,27.5, 20,Color.WHITE,Category.WINDOWS));

    assertThrows(NullPointerException.class, () ->
            new Item("A1205B","Large Christmas Window","SULOLI",
                    130,2,0.45,15.8,27.5, 20,null,Category.WINDOWS));

    assertThrows(NullPointerException.class, () ->
            new Item("A1205B","Large Christmas Window","SULOLI",
                    130,2,0.45,15.8,27.5, 20,Color.WHITE,null));

    assertThrows(NullPointerException.class, () ->
            new Item(null));


    assertThrows(IllegalNumberException.class, () ->
            new Item("A1205B","Large Christmas Window","SULOLI",
                    -5,2,0.45,15.8,27.5, 20,Color.WHITE,Category.WINDOWS));

    assertThrows(IllegalNumberException.class, () ->
            new Item("A1205B","Large Christmas Window","SULOLI",
                    130,-2,0.45,15.8,27.5, 20,Color.WHITE,Category.WINDOWS));

    assertThrows(IllegalNumberException.class, () ->
            new Item("A1205B","Large Christmas Window","SULOLI",
                    130,2,-0.45,15.8,27.5, 20,Color.WHITE,Category.WINDOWS));

    assertThrows(IllegalNumberException.class, () ->
            new Item("A1205B","Large Christmas Window","SULOLI",
                    130,2,0.45,-15.8,27.5, 20,Color.WHITE,Category.WINDOWS));

    assertThrows(IllegalNumberException.class, () ->
            new Item("A1205B","Large Christmas Window","SULOLI",
                    130,2,0.45,15.8,-27.5, 20,Color.WHITE,Category.WINDOWS));


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

    assertThrows(IllegalNumberException.class, () -> item.setPrice(-5));

    assertThrows(IllegalNumberException.class, () -> item.setDiscount(102));

    assertThrows(IllegalNumberException.class, () -> item.setDiscount(-2));

    assertThrows(IllegalNumberException.class, () -> item.setWarehouseStock(-15));

    item.setDescription(null);
    assertEquals("", item.getDescription());



  }

}