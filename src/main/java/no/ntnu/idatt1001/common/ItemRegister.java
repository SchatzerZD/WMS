package no.ntnu.idatt1001.common;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import no.ntnu.idatt1001.util.Category;
import no.ntnu.idatt1001.util.Color;
import no.ntnu.idatt1001.util.IllegalNumberException;
import no.ntnu.idatt1001.util.item.Item;
import no.ntnu.idatt1001.util.item.ItemBuilder;


/**
 * A class representing a register for instances of the {@link Item} class.
 * The register provides basic functionality for modifying a list containing
 * {@code Item} objects. The register doesn't allow items with duplicate
 * item numbers, and item numbers are not case-sensitive.
 *
 * @author 10124
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
   * @throws NullPointerException       If the specified item is {@code null}
   */
  public void addItem(Item item) {
    if (item == null) {
      throw new NullPointerException("Item specified cannot be null");
    }

    boolean itemNumberExists = itemList.stream().anyMatch(itemInList ->
            itemInList.getItemNumber().equalsIgnoreCase(item.getItemNumber()));

    if (itemNumberExists) {
      throw new IllegalArgumentException("Item number already exists in the register");
    }

    itemList.add(item);
  }

  /**
   * Searches the {@link ItemRegister#itemList} of this instance for the
   * specified item number. Creates a deep-copy of the item that was found,
   * and returns the copy. Only uses one matching item if any matches were
   * found. Utilizes the {@link ItemBuilder#deepCopy(Item)} method for
   * deep copying the item.
   *
   * @param itemNumberInput Item number which is used to find any matches
   * @return                Copy of an {@link Item} object if any matches
   *                        were found,otherwise returns {@code null}
   */
  public Item searchByItemNumber(String itemNumberInput) {
    if (itemNumberInput == null) {
      throw new NullPointerException("Item number specified cannot be null");
    }

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
   * found. Utilizes the {@link ItemBuilder#deepCopy(Item)} method for
   * deep copying the item.
   *
   * @param itemDescInput   Item description which is used to find any matches
   * @return                Copy of an {@link Item} object if any matches
   *                        were found,otherwise returns {@code null}
   */
  public Item searchByItemDesc(String itemDescInput) {
    if (itemDescInput == null) {
      throw new NullPointerException("Description cannot be null");
    }

    Item optionalItem = optionalItemFromList(item ->
            item.getDescription().toLowerCase().trim().equals(itemDescInput.toLowerCase().trim()))
            .orElse(null);

    if (optionalItem != null) {
      return ItemBuilder.deepCopy(optionalItem);
    }

    return null;
  }

  /**
   * Increases the stock of the specified {@link Item} by the specified amount of stock,
   * utilizing the {@link Item#setWarehouseStock(int)} method.
   * This method searches the list for the specified item number and adds the specified amount
   * to the existing amount. If the item is not found in this register's list, a
   * {@link NoSuchElementException} is thrown. A negative amount is not allowed because that
   * would cause the item stock to decrease. Use the
   * {@link ItemRegister#decreaseItemStock(Item, int)} method instead.
   *
   * @param itemInput               The {@link Item} which the stock
   *                                of should be changed
   * @param stockIncrease           The amount of stock that should be added to the
   *                                specified item's stock. Cannot be below 0
   * @throws IllegalNumberException If the specified amount is below 0
   * @throws NoSuchElementException If the specified item doesn't exist in this register's list
   * @throws NullPointerException   If the specified item is {@code null}
   */
  public void increaseItemStock(Item itemInput, int stockIncrease) {
    if (itemInput == null) {
      throw new NullPointerException("Item specified cannot be null");
    }

    if (stockIncrease < 0) {
      throw new IllegalNumberException("Stock amount specified cannot be below 0");
    }

    Item optionalItem = optionalItemFromList(item ->
            item.getItemNumber().equals(itemInput.getItemNumber()))
            .orElseThrow(NoSuchElementException::new);
    optionalItem.setWarehouseStock(optionalItem.getWarehouseStock() + stockIncrease);

  }

  /**
   * Decreases the stock of the specified {@link Item} by the specified amount of stock,
   * utilizing the {@link Item#setWarehouseStock(int)} method.
   * This method searches the list for the specified item number and subtracts the specified amount
   * from the existing amount. If the item is not found in this register's list, a
   * {@link NoSuchElementException} is thrown. A negative amount is not allowed because that
   * would cause the item stock to increase. Use the
   * {@link ItemRegister#increaseItemStock(Item, int)} method instead.
   *
   * @param itemInput               The {@link Item} which the stock
   *                                of should be changed
   * @param stockDecrease           The amount of stock that should be subtraced from the
   *                                specified item's stock. Cannot be below 0
   * @throws IllegalNumberException If the specified amount is below 0
   * @throws NoSuchElementException If the specified item doesn't exist in this register's list
   * @throws NullPointerException   If the specified item is {@code null}
   */
  public void decreaseItemStock(Item itemInput, int stockDecrease) {
    if (itemInput == null) {
      throw new NullPointerException("Item specified cannot be null");
    }

    if (stockDecrease < 0) {
      throw new IllegalNumberException("Stock amount specified cannot be below 0");
    }

    Item optionalItem = optionalItemFromList(item ->
            item.getItemNumber().equals(itemInput.getItemNumber()))
            .orElseThrow(NoSuchElementException::new);
    optionalItem.setWarehouseStock(optionalItem.getWarehouseStock() - stockDecrease);
  }

  /**
   * Changes the price of the specified {@link Item} to the specified amount,
   * utilizing the {@link Item#setPrice(int)} method.
   * This method searches the list for the specified item number and sets the price of the
   * item to the specified amount. If the item is not found in this register's list,
   * a {@link NoSuchElementException} is thrown. A negative amount is not allowed.
   *
   * @param itemInput               The {@link Item} which the price
   *                                of should be changed
   * @param price                   The new price which the specified item will have. Cannot
   *                                be below 0
   * @throws IllegalNumberException If the specified price is below 0
   * @throws NoSuchElementException If the specified item doesn't exist in this register's list
   * @throws NullPointerException   If the specified item is {@code null}
   */
  public void changePriceOfItem(Item itemInput, int price) {
    if (itemInput == null) {
      throw new NullPointerException("Item specified cannot be null");
    }

    if (price < 0) {
      throw new IllegalNumberException("Price amount specified cannot be below 0");
    }

    Item optionalItem = optionalItemFromList(item ->
            item.getItemNumber().equals(itemInput.getItemNumber()))
            .orElseThrow(NoSuchElementException::new);
    optionalItem.setPrice(price);
  }

  /**
   * Changes the discount of the specified {@link Item} to the specified amount,
   * utilizing the {@link Item#setDiscount(double)} method.
   * This method searches the list for the specified item number and sets the discount of the
   * item to the specified amount. If the item is not found in this register's list,
   * a {@link NoSuchElementException} is thrown.
   *
   * @param itemInput               The {@link Item} which the discount
   *                                of should be changed.
   * @param discount                The new discount which the specified item will have.
   *                                Accept numbers between 0 and 100
   * @throws IllegalNumberException If the specified discount is below 0 or above 100
   * @throws NoSuchElementException If the specified item doesn't exist in this register's list
   * @throws NullPointerException   If the specified item is {@code null}
   */
  public void changeDiscountOfItem(Item itemInput, double discount) {
    if (itemInput == null) {
      throw new NullPointerException("Item specified cannot be null");
    }

    if (discount < 0 || discount > 100) {
      throw new IllegalNumberException("Discount amount specified cannot be below 0 or above 100");
    }

    Item optionalItem = optionalItemFromList(item ->
            item.getItemNumber().equals(itemInput.getItemNumber()))
            .orElseThrow(NoSuchElementException::new);

    optionalItem.setDiscount(discount);
  }

  /**
   * Changes the description of the specified {@link Item} to the specified string,
   * utilizing the {@link Item#setDescription(String)} method.
   * This method searches the list for the specified item and sets the description of the
   * item to the specified string. If the item is not found in this register's list,
   * a {@link NoSuchElementException} is thrown.
   *
   * @param itemInput               The {@link Item} which the description
   *                                of should be changed
   * @param description             The new description which the specified item will have
   * @throws NoSuchElementException If the specified item doesn't exist in this register's list
   * @throws NullPointerException   If the specified item is {@code null}
   */
  public void changeDescriptionOfItem(Item itemInput, String description) {
    if (itemInput == null) {
      throw new NullPointerException("Item specified cannot be null");
    }

    Item optionalItem = optionalItemFromList(item ->
            item.getItemNumber().equals(itemInput.getItemNumber()))
            .orElseThrow(NoSuchElementException::new);
    optionalItem.setDescription(description);
  }

  /**
   * Gets the index of the specified {@code itemNumber} in this register's list by
   * utilizing the {@link IntStream} class. The stream
   * uses the {@link java.util.stream.Stream#filter(Predicate)} method to find the
   * item with the specified {@code itemNumber} and returns the index.
   *
   * @param itemInput               The {@link Item} which the index
   *                                of should be returned
   * @return                        The index number of the specified {@link Item} in this
   *                                register's list, otherwise returns
   *                                {@code -1} if the item is not found in the list
   * @throws NullPointerException   If the specified item is {@code null}
   */
  public int getIndexOfItem(Item itemInput) {
    if (itemInput == null) {
      throw new NullPointerException("The specified item cannot be null");
    }

    return IntStream.range(0, itemList.size())
            .filter(i -> itemInput.getItemNumber().equals(itemList.get(i).getItemNumber()))
            .findFirst()
            .orElse(-1);
  }

  /**
   * Removes the specified {@link Item} from this register's list by
   * utilizing the {@link List#remove(Object)} method.
   *
   * @param itemInput               The {@link Item} that should be removed from this
   *                                register's item list
   * @return                        {@code true} if the specified item is in the list,
   *                                otherwise returns {@code false} if the item wasn't found
   *                                in the list
   * @throws NullPointerException   If the specified item is {@code null}
   */
  public boolean removeItem(Item itemInput) {
    if (itemInput == null) {
      throw new NullPointerException("The specified item cannot be null");
    }

    Item optionalItem = optionalItemFromList(item ->
            item.getItemNumber().equals(itemInput.getItemNumber()))
            .orElse(null);

    if (optionalItem == null) {
      return false;
    }

    return itemList.remove(optionalItem);
  }

  /**
   * Sorts this register's list by item number by utilizing
   * the {@link List#sort(Comparator)} method. The specified
   * boolean parameter determines if the list should be sorted
   * ascendingly. In this case the list gets sorted alphabetically
   * with the item number
   *
   * @param ascending If the specified value is {@code true}, the list
   *                  gets sorted ascendingly, otherwise the list gets
   *                  sorted descendingly
   */
  public void sortListByItemnumber(boolean ascending) {
    itemList.sort(((o1, o2) -> {
      if (o1.getItemNumber().equals(o2.getItemNumber())) {
        return 0;
      }
      if (ascending) {
        return (o1.getItemNumber().compareTo(o2.getItemNumber()) > 0) ? 1 : -1;
      } else {
        return (o1.getItemNumber().compareTo(o2.getItemNumber()) > 0) ? -1 : 1;
      }
    }));
  }

  /**
   * Sorts this register's list by brand name by utilizing
   * the {@link List#sort(Comparator)} method. The specified
   * boolean parameter determines if the list should be sorted
   * ascendingly. In this case the list gets sorted alphabetically
   * with the brand name.
   *
   * @param ascending If the specified value is {@code true}, the list
   *                  gets sorted ascendingly, otherwise the list gets
   *                  sorted descendingly
   */
  public void sortListByBrandname(boolean ascending) {
    itemList.sort(((o1, o2) -> {
      if (o1.getBrandName().equals(o2.getBrandName())) {
        return 0;
      }
      if (ascending) {
        return (o1.getBrandName().compareTo(o2.getBrandName()) > 0) ? 1 : -1;
      } else {
        return (o1.getBrandName().compareTo(o2.getBrandName()) > 0) ? -1 : 1;
      }
    }));
  }

  /**
   * Sorts this register's list by price by utilizing
   * the {@link List#sort(Comparator)} method. The specified
   * boolean parameter determines if the list should be sorted
   * ascendingly. In this case the list gets sorted by the number
   * value of the items' price.
   *
   * @param ascending If the specified value is {@code true}, the list
   *                  gets sorted ascendingly, otherwise the list gets
   *                  sorted descendingly
   */
  public void sortListByPrice(boolean ascending) {
    itemList.sort(((o1, o2) -> {
      if (o1.getPrice() == o2.getPrice()) {
        return 0;
      }
      if (ascending) {
        return (o1.getPrice() > o2.getPrice()) ? 1 : -1;
      } else {
        return (o1.getPrice() > o2.getPrice()) ? -1 : 1;
      }
    }));
  }

  /**
   * Sorts this register's list by warehouse stock by utilizing
   * the {@link List#sort(Comparator)} method. The specified
   * boolean parameter determines if the list should be sorted
   * ascendingly. In this case the list gets sorted by the number
   * value of the items' warehouse stock.
   *
   * @param ascending If the specified value is {@code true}, the list
   *                  gets sorted ascendingly, otherwise the list gets
   *                  sorted descendingly
   */
  public void sortListByWarehousestock(boolean ascending) {
    itemList.sort(((o1, o2) -> {
      if (o1.getWarehouseStock() == o2.getWarehouseStock()) {
        return 0;
      }
      if (ascending) {
        return (o1.getWarehouseStock() > o2.getWarehouseStock()) ? 1 : -1;
      } else {
        return (o1.getWarehouseStock() > o2.getWarehouseStock()) ? -1 : 1;
      }
    }));
  }

  /**
   * Sorts this register's list by {@link Color} by utilizing
   * the {@link List#sort(Comparator)} method. The specified
   * boolean parameter determines if the list should be sorted
   * ascendingly. In this case the list gets sorted alphabetically
   * with the {@code Color.toString()} value.
   *
   * @param ascending If the specified value is {@code true}, the list
   *                  gets sorted ascendingly, otherwise the list gets
   *                  sorted descendingly
   */
  public void sortListByColor(boolean ascending) {
    itemList.sort(((o1, o2) -> {
      if (o1.getColor().equals(o2.getColor())) {
        return 0;
      }
      if (ascending) {
        return (o1.getColor().name().compareTo(o2.getColor().name()) > 0) ? 1 : -1;
      } else {
        return (o1.getColor().name().compareTo(o2.getColor().name()) > 0) ? -1 : 1;
      }
    }));
  }

  /**
   * Sorts this register's list by {@link Category} by utilizing
   * the {@link List#sort(Comparator)} method. The specified
   * boolean parameter determines if the list should be sorted
   * ascendingly. In this case the list gets sorted alphabetically
   * with the {@code Category.toString()} value.
   *
   * @param ascending If the specified value is {@code true}, the list
   *                  gets sorted ascendingly, otherwise the list gets
   *                  sorted descendingly
   */
  public void sortListByCategory(boolean ascending) {
    itemList.sort(((o1, o2) -> {
      if (o1.getCategory().equals(o2.getCategory())) {
        return 0;
      }
      if (ascending) {
        return (o1.getCategory().name().compareTo(o2.getCategory().name()) > 0) ? 1 : -1;
      } else {
        return (o1.getCategory().name().compareTo(o2.getCategory().name()) > 0) ? -1 : 1;
      }
    }));
  }

  /**
   * Gets a deep-copy of this register's item list by utilizing
   * the {@link ItemBuilder#deepCopy(Item)} method. This method
   * creates a stream from the item list, and uses the
   * {@link java.util.stream.Stream#map(Function)} method to map
   * a deep-copy of every item into a new list which will be returned.
   *
   * @return A {@link List} containing a deep-copy of all the items
   *         in this register's item list
   */
  public List<Item> getCopyOfList() {
    return itemList.stream().map(ItemBuilder::deepCopy).toList();
  }

  /**
   * Extracted method for retrieving an {@link Optional} object of
   * an {@link Item} object. Utilizes the {@link java.util.stream.Stream} class
   * for creating a stream out of this register's list, and using the
   * {@link java.util.stream.Stream#filter(Predicate)} method for filtering
   * the stream with the specified predicate. Retrieves the first object in the stream
   *
   * @param predicate The predicate for filtering the stream
   * @return          An {@link Optional} object of the {@link Item} class.
   */
  private Optional<Item> optionalItemFromList(Predicate<Item> predicate) {
    return itemList.stream()
            .filter(predicate)
            .findFirst();
  }


  /**
   * Fills this register's item list with pre-made items for testing purposes.
   * This method utilizes the {@link ItemBuilder} class for creating {@link Item}
   * objects
   */
  public void fillListWithDefaultItems() {
    addItem(new ItemBuilder()
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
            .build());

    addItem(new ItemBuilder()
            .setItemNumber("M5788B")
            .setDescription("Cherry Lumber")
            .setBrandName("Barrington Hardwoods")
            .setPrice(120)
            .setWarehouseStock(8)
            .setWeight(2.43)
            .setLength(30.48)
            .setHeight(3.2)
            .setColor(Color.BROWN)
            .setCategory(Category.LUMBER)
            .setWidth(20)
            .build());

    addItem(new ItemBuilder()
            .setItemNumber("C1007B")
            .setDescription("Magnetic Thermal Insulated")
            .setBrandName("Mpmedo")
            .setPrice(298)
            .setWarehouseStock(31)
            .setWeight(52.4)
            .setLength(57.3)
            .setHeight(210.47)
            .setColor(Color.GRAY)
            .setCategory(Category.DOORS)
            .setWidth(20)
            .build());

    addItem(new ItemBuilder()
            .setItemNumber("A1008B")
            .setDescription("Basement Hopper Window")
            .setBrandName("Park products")
            .setPrice(977)
            .setWarehouseStock(76)
            .setWeight(8.6)
            .setLength(96.3)
            .setHeight(45.2)
            .setColor(Color.GRAY)
            .setCategory(Category.WINDOWS)
            .setWidth(20)
            .build());

    addItem(new ItemBuilder()
            .setItemNumber("F4020G")
            .setDescription("Self Adhesive Vinyl Floor Tile")
            .setBrandName("Achim")
            .setPrice(173)
            .setWarehouseStock(211)
            .setWeight(4.08)
            .setLength(30.48)
            .setHeight(30.48)
            .setColor(Color.BLACK)
            .setCategory(Category.FLOOR_LAMINATES)
            .setWidth(20)
            .build());
  }

  /**
   * Gets a deep-copy of the {@link Item} object in the
   * specified {@code index} location in this register's list.
   * Utilizes the {@link ItemBuilder#deepCopy(Item)} method for
   * deep copying the item.
   *
   * @param index                       The {@code index} of the item which should be deep-copied
   *                                    and retrieved. Cannot be below 0 or greater
   *                                    than {@link ItemRegister#size()} - 1
   * @return                            A deep-copy of the {@link Item} in the
   *                                    specified {@code index} location
   * @throws IndexOutOfBoundsException  If the specified index is below 0 or greater than
   *                                    this register's list
   */
  public Item getItem(int index) {
    if (index < 0 || index > (size() - 1)) {
      throw new IndexOutOfBoundsException("Specified index is out of bounds");
    }

    return ItemBuilder.deepCopy(itemList.get(index));
  }

  /**
   * Gets the size of this register's item list by utilizing
   * the {@link List#size()} method.
   *
   * @return The size of this register's list
   */
  public int size() {
    return itemList.size();
  }

  /**
   * A standard to-string method with all the relevant information from this
   * item register in a specific format. Displays all the items in {@code itemList}.
   *
   * @return A string formatted with all the relevant information about this item register
   */
  @Override
  public String toString() {
    StringBuilder returnString = new StringBuilder(String.format(
            "| %-15s | %-22s | %-17s | %-6s | %-10s | %-10s | %-10s | %-10s | %-8s | %-18s | %s\n",
            "ITEM NUMBER", "BRAND NAME", "PRICE (DISCOUNT)", "STOCK", "WEIGHT",
            "LENGTH", "HEIGHT", "WIDTH" , "COLOR", "CATEGORY", "DESCRIPTION"));

    returnString.append("+ ").append("-".repeat(15)).append(" + ")
            .append("-".repeat(22)).append(" + ")
            .append("-".repeat(17)).append(" + ").append("-".repeat(6)).append(" + ")
            .append("-".repeat(10)).append(" + ").append("-".repeat(10)).append(" + ")
            .append("-".repeat(10)).append(" + ").append("-".repeat(10)).append("+")
            .append("-".repeat(8)).append(" + ").append("-".repeat(18)).append(" + ")
            .append("-".repeat(64)).append("\n");

    for (Item item : itemList) {
      returnString.append(item.toString()).append("\n");
    }

    return returnString.toString();
  }


}
