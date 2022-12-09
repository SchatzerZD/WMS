package no.ntnu.idatt1001.common.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import no.ntnu.idatt1001.util.Category;
import no.ntnu.idatt1001.util.Color;
import no.ntnu.idatt1001.util.item.Item;
import no.ntnu.idatt1001.util.item.ItemBuilder;


/**
 * A class representing a register for instances of the {@link Item} class.
 * The register provides basic functionality for modifying a list containing
 * {@code Item} objects. The register doesn't allow items with duplicate
 * item numbers, and item numbers are not case-sensitive.
 *
 * @author Daniel Ireneo Neri Saren
 * @version 1.0.0
 */
public class ItemRegister {

  private final List<Item> itemList;

  /**
   * A constructor for the {@link ItemRegister} class. Creates a new
   * instance of an {@link ArrayList} for containing {@code Item} objects.
   */
  public ItemRegister() {
    itemList = new ArrayList<>();
  }

  /**
   * Adds the specified {@link Item} to this register's {@code itemList}. A check
   * is performed before the item is added to the list, which checks if list
   * already contains the specified item's item number. If the check returns
   * true, an {@link IllegalArgumentException} is thrown, otherwise the item
   * gets added to the list. Since this method is the only way of adding new items,
   * this ensures that every item in the list has a unique item number.
   *
   * @param item                        The {@link Item} which should be
   *                                    added to this register's list
   * @throws IllegalArgumentException   If the specified item's item number
   *                                    already exists in the register's list
   */
  public void addItem(Item item) {
    boolean itemNumberExists = itemList.stream().anyMatch(itemInList ->
            itemInList.getItemNumber().equalsIgnoreCase(item.getItemNumber()));

    if (itemNumberExists) {
      throw new IllegalArgumentException();
    }

    itemList.add(item);
  }

  /**
   * Searches the {@link ItemRegister#itemList} of this instance for the
   * specified item number. Creates a deep-copy of the item that was found,
   * and returns the copy. Only uses one matching item if any matches were
   * found.
   *
   * @param itemNumberInput Item number which is used to find any matches
   * @return                Copy of an {@link Item} object if any matches
   *                        were found,otherwise returns {@code null}
   */
  public Item searchByItemNumber(String itemNumberInput) {
    Item optionalItem = optionalItemFromList(item ->
            item.getItemNumber().toLowerCase().trim().equals(itemNumberInput.toLowerCase().trim()))
            .orElse(null);

    if (optionalItem != null) {
      return ItemBuilder.deepCopy(optionalItem);
    }

    return null;
  }

  /**
   * Searches the {@link ItemRegister#itemList} of this instance for the
   * specified description. Creates a deep-copy of the item that was found,
   * and returns the copy. Only uses one matching item if any matches were
   * found.
   *
   * @param itemDescInput   Item description which is used to find any matches
   * @return                Copy of an {@link Item} object if any matches
   *                        were found,otherwise returns {@code null}
   */
  public Item searchByItemDesc(String itemDescInput) {
    Item optionalItem = optionalItemFromList(item ->
            item.getDesc().toLowerCase().trim().equals(itemDescInput.toLowerCase().trim()))
            .orElse(null);

    if (optionalItem != null) {
      return ItemBuilder.deepCopy(optionalItem);
    }

    return null;
  }

  /**
   * Increases the stock of the specified {@link Item} by the specified amount of stock,
   * utilizing the {@link Item#setWarehouseStock(int)} method.
   * The method searches the list for the specified item and adds the specified amount
   * to the existing amount. If the item is not found in this register's list, a
   * {@link NoSuchElementException} is thrown.
   *
   * @param itemInput               The {@link Item} which the stock of should be changed
   * @param stockIncrease           The amount of stock that should be added to the
   *                                specified item's stock
   * @throws NoSuchElementException If the specified item doesn't exist in this register's list
   */
  public void increaseItemStock(Item itemInput, int stockIncrease) {
    optionalItemFromList(item -> item.equals(itemInput))
            .orElseThrow(NoSuchElementException::new)
            .setWarehouseStock(itemInput.getWarehouseStock() + stockIncrease);
  }

  /**
   * Decreases the stock of the specified {@link Item} by the specified amount of stock,
   * utilizing the {@link Item#setWarehouseStock(int)} method.
   * The method searches the list for the specified item and subtracts the specified amount
   * from the existing amount. If the item is not found in this register's list, a
   * {@link NoSuchElementException} is thrown.
   *
   * @param itemInput               The {@link Item} which the stock of should be changed
   * @param stockDecrease           The amount of stock that should be subtraced from the
   *                                specified item's stock
   * @throws NoSuchElementException If the specified item doesn't exist in this register's list
   */
  public void decreaseItemStock(Item itemInput, int stockDecrease) {
    optionalItemFromList(item -> item.equals(itemInput))
            .orElseThrow(NoSuchElementException::new)
            .setWarehouseStock(itemInput.getWarehouseStock() - stockDecrease);
  }

  public void changePriceOfItem(Item itemInput, int price) {
    optionalItemFromList(item -> item.equals(itemInput))
            .orElseThrow(NoSuchElementException::new)
            .setPrice(price);
  }

  public void changeDiscountOfItem(Item itemInput, double discount) {
    optionalItemFromList(item -> item.equals(itemInput))
            .orElseThrow(NoSuchElementException::new)
            .setDiscount(discount);
  }

  public int getIndexOfItem(Item itemInput){
    return itemList.indexOf(itemInput);
  }

  public boolean removeItem(Item item){
    return itemList.remove(item);
  }

  public void sortListByItemnumber(boolean ascending){
    itemList.sort(((o1, o2) -> {
      if(o1.getItemNumber().equals(o2.getItemNumber()))return 0;
      if(ascending)
        return (o1.getItemNumber().compareTo(o2.getItemNumber()) > 0) ? 1 : -1;
      else
        return (o1.getItemNumber().compareTo(o2.getItemNumber()) > 0) ? -1 : 1;
    }));
  }

  public void sortListByBrandname(boolean ascending){
    itemList.sort(((o1, o2) -> {
      if(o1.getBrandName().equals(o2.getBrandName()))return 0;
      if(ascending)
        return (o1.getBrandName().compareTo(o2.getBrandName()) > 0) ? 1 : -1;
      else
        return (o1.getBrandName().compareTo(o2.getBrandName()) > 0) ? -1 : 1;
    }));
  }

  public void sortListByPrice(boolean ascending){
    itemList.sort(((o1, o2) -> {
      if(o1.getPrice() == o2.getPrice())return 0;
      if(ascending)
        return (o1.getPrice() > o2.getPrice()) ? 1 : -1;
      else
        return (o1.getPrice() > o2.getPrice()) ? -1 : 1;
    }));
  }

  public void sortListByWarehousestock(boolean ascending){
    itemList.sort(((o1, o2) -> {
      if(o1.getWarehouseStock() == o2.getWarehouseStock())return 0;
      if(ascending)
        return (o1.getWarehouseStock() > o2.getWarehouseStock()) ? 1 : -1;
      else
        return (o1.getWarehouseStock() > o2.getWarehouseStock()) ? -1 : 1;
    }));
  }

  public void sortListByColor(boolean ascending){
    itemList.sort(((o1, o2) -> {
      if(o1.getColor().equals(o2.getColor()))return 0;
      if(ascending)
        return (o1.getColor().name().compareTo(o2.getColor().name()) > 0) ? 1 : -1;
      else
        return (o1.getColor().name().compareTo(o2.getColor().name()) > 0) ? -1 : 1;
    }));
  }

  public void sortListByCategory(boolean ascending){
    itemList.sort(((o1, o2) -> {
      if(o1.getCategory().equals(o2.getCategory()))return 0;
      if(ascending)
        return (o1.getCategory().name().compareTo(o2.getCategory().name()) > 0) ? 1 : -1;
      else
        return (o1.getCategory().name().compareTo(o2.getCategory().name()) > 0) ? -1 : 1;
    }));
  }

  public List<Item> getCopyOfList(){
    return itemList.stream().map(ItemBuilder::deepCopy).toList();
  }

  private Optional<Item> optionalItemFromList(Predicate<Item> predicate){
    return itemList.stream()
            .filter(predicate)
            .findFirst();
  }



  public void fillListWithDefaultItems(){
    addItem(new ItemBuilder()
            .setItemNumber("A1205B")
            .setDesc("Large Christmas Window")
            .setBrandName("SULOLI")
            .setPrice(130)
            .setWarehouseStock(2)
            .setWeight(0.45)
            .setLength(15.8)
            .setHeight(27.5)
            .setColor(Color.WHITE)
            .setCategory(Category.WINDOWS).build());

    addItem(new ItemBuilder()
            .setItemNumber("M5788B")
            .setDesc("Cherry Lumber")
            .setBrandName("Barrington Hardwoods")
            .setPrice(120)
            .setWarehouseStock(8)
            .setWeight(2.43)
            .setLength(30.48)
            .setHeight(3.2)
            .setColor(Color.BROWN)
            .setCategory(Category.LUMBER).build());

    addItem(new ItemBuilder()
            .setItemNumber("C1007B")
            .setDesc("Magnetic Thermal Insulated")
            .setBrandName("Mpmedo")
            .setPrice(298)
            .setWarehouseStock(31)
            .setWeight(52.4)
            .setLength(57.3)
            .setHeight(210.47)
            .setColor(Color.GRAY)
            .setCategory(Category.DOORS).build());

    addItem(new ItemBuilder()
            .setItemNumber("A1008B")
            .setDesc("Basement Hopper Window")
            .setBrandName("Park products")
            .setPrice(977)
            .setWarehouseStock(76)
            .setWeight(8.6)
            .setLength(96.3)
            .setHeight(45.2)
            .setColor(Color.GRAY)
            .setCategory(Category.WINDOWS).build());

    addItem(new ItemBuilder()
            .setItemNumber("F4020G")
            .setDesc("Self Adhesive Vinyl Floor Tile")
            .setBrandName("Achim")
            .setPrice(173)
            .setWarehouseStock(211)
            .setWeight(4.08)
            .setLength(30.48)
            .setHeight(30.48)
            .setColor(Color.BLACK)
            .setCategory(Category.FLOOR_LAMINATES).build());
  }
  public Item getItem(int index){
    return itemList.get(index);
  }

  public int size(){
    return itemList.size();
  }

  @Override
  public String toString() {
    StringBuilder returnString = new StringBuilder(String.format(
            "| %-15s | %-22s | %-17s | %-6s | %-10s | %-10s | %-10s | %-8s | %-18s | %s\n",
            "ITEM NUMBER", "BRAND NAME", "PRICE (DISCOUNT)", "STOCK", "WEIGHT",
            "LENGTH", "HEIGHT", "COLOR", "CATEGORY", "DESCRIPTION"));

    returnString.append("+ ").append("-".repeat(15)).append(" + ").append("-".repeat(22)).append(" + ")
            .append("-".repeat(17)).append(" + ").append("-".repeat(6)).append(" + ")
            .append("-".repeat(10)).append(" + ").append("-".repeat(10)).append(" + ")
            .append("-".repeat(10)).append(" + ").append("-".repeat(8)).append(" + ")
            .append("-".repeat(18)).append(" + ").append("-".repeat(64)).append("\n");

    for (Item item: itemList) {
      returnString.append(item.toString()).append("\n");
    }

    return returnString.toString();
  }


}
