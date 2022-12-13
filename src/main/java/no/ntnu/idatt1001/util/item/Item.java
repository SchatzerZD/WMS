package no.ntnu.idatt1001.util.item;

import no.ntnu.idatt1001.util.Category;
import no.ntnu.idatt1001.util.Color;
import no.ntnu.idatt1001.util.IllegalNumberException;

/**
 * A class representing an item stored inside
 * the warehouse. An item contains information accessed
 * by the warehouse for simple warehouse functionality
 *
 * @author 10124
 * @version 1.1.0
 */
public class Item {
  private final String itemNumber;
  private String description;
  private final String brandName;

  private int price;
  private int warehouseStock;
  private double discount;

  private final double weight;
  private final double length;
  private final double height;
  private final double width;

  private final Color color;
  private final Category category;

  /**
   * Constructor for the {@link Item} class. Used for creating an object instance of this class.
   * This constructor can only be accessed by the {@link ItemBuilder} class. Please use
   * the {@link ItemBuilder} class for specifying the parameters and use
   * the {@link ItemBuilder#build()} method for creating an {@link Item} object.
   *
   * @param itemNumber                  The item number of the item,
   *                                    contatins both numbers and letters
   * @param description                        A text which describes the item
   * @param brandName                   A text containing the brand which the item was created by
   * @param price                       Integer representing the price of the item
   * @param warehouseStock              Integer representing how many of
   *                                    this item is left in the warehouse
   * @param weight                      The weight of the item
   * @param length                      The length of the item
   * @param height                      The height of the item
   * @param width                       The width of the item
   * @param color                       The color of the item
   * @param category                    Which warehouse category the item belongs to
   * @throws IllegalNumberException     If the specified price is below 0. Also throws
   *                                    if weight, length or height is 0 or below 0.
   * @throws NullPointerException       If the specified {@code itemNumber},
   *                                    {@code color} or {@code category} is {@code null}
   */
  protected Item(String itemNumber, String description, String brandName, int price,
                 int warehouseStock, double weight, double length, double height, double width,
                 Color color, Category category) {

    if (itemNumber == null) {
      throw new NullPointerException("Item number cannot be null");
    }

    if (color == null) {
      throw new NullPointerException("Color cannot be null");
    }

    if (category == null) {
      throw new NullPointerException("Category cannot be null");
    }

    if (price < 0) {
      throw new IllegalNumberException("Price cannot be below 0");
    }

    if (warehouseStock < 0) {
      throw new IllegalNumberException("Warehouse stock cannot be below 0");
    }

    if (weight <= 0) {
      throw new IllegalNumberException("Weight cannot be 0 or below 0");
    }

    if (length <= 0) {
      throw new IllegalNumberException("Length cannot be 0 or below 0");
    }

    if (height <= 0) {
      throw new IllegalNumberException("Height cannot be 0 or below 0");
    }

    if (width <= 0) {
      throw new IllegalNumberException("Width cannot be 0 or below 0");
    }

    this.price = price;
    this.warehouseStock = warehouseStock;
    this.weight = weight;
    this.length = length;
    this.height = height;
    this.width = width;

    this.itemNumber = itemNumber;
    this.description = description;
    this.brandName = brandName;

    this.color = color;
    this.category = category;
    this.discount = 0;

  }

  /**
   * Constructor for creating a deep copy of an {@link Item} object.
   * The constructor takes an item as a parameter, and assigns the value
   * of each field to this object's fields.
   *
   * @param item                  The {@link Item} which should be copied from
   * @throws NullPointerException If the specified item is {@code null}
   */
  protected Item(Item item) {
    if (item == null) {
      throw new NullPointerException("Item cannot be null");
    }

    this.itemNumber = item.getItemNumber();
    this.description = item.getDescription();
    this.brandName = item.getBrandName();
    this.price = item.price;
    this.warehouseStock = item.getWarehouseStock();
    this.weight = item.getWeight();
    this.length = item.getLength();
    this.height = item.getHeight();
    this.width = item.getWidth();
    this.color = item.getColor();
    this.category = item.getCategory();
    this.discount = item.getDiscount();
  }

  /**
   * Gets the {@code itemNumber} of this item.
   *
   * @return The item number of this item
   */
  public String getItemNumber() {
    return itemNumber;
  }

  /**
   * Gets the {@code description} of this item.
   *
   * @return The description of this item
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gets the {@code brandName} of this item.
   *
   * @return The brand name of this item
   */
  public String getBrandName() {
    return brandName;
  }

  /**
   * Gets the {@link Item#price} of the item after calculation. The calculation
   * takes into account the {@link Item#discount} value.
   * The price is calculated by taking the {@link Item#discount} value,
   * dividing it by 100, subtracting 1 from the result,
   * and multiplying the result with the {@link Item#price} value.
   *
   * @return  The price of the item calculated with the discount
   */
  public int getPrice() {
    return (int) (price * (1 - (discount / 100)));
  }

  /**
   * Gets the {@code discount} of this item.
   *
   * @return The discount of this item
   */
  public double getDiscount() {
    return discount;
  }

  /**
   * Gets the {@code warehouseStock} of this item.
   *
   * @return The warehouse stock of this item
   */
  public int getWarehouseStock() {
    return warehouseStock;
  }

  /**
   * Gets the {@code weight} of this item.
   *
   * @return The weight of this item
   */
  public double getWeight() {
    return weight;
  }

  /**
   * Gets the {@code length} of this item.
   *
   * @return The length of this item
   */
  public double getLength() {
    return length;
  }

  /**
   * Gets the {@code height} of this item.
   *
   * @return The height of this item
   */
  public double getHeight() {
    return height;
  }

  /**
   * Gets the {@code width} of this item.
   *
   * @return The width of this item
   */
  public double getWidth() {
    return width;
  }

  /**
   * Gets the {@link Color} of this item.
   *
   * @return The color of this item
   */
  public Color getColor() {
    return color;
  }

  /**
   * Gets the {@link Category} of this item.
   *
   * @return The category of this item
   */
  public Category getCategory() {
    return category;
  }


  /**
   * Sets the {@link Item#description} of the item to the specified string.
   *
   * @param description  The description which the item should be set to
   */
  public void setDescription(String description) {
    if (description == null) {
      this.description = "";
    } else {
      this.description = description;
    }
  }

  /**
   * Sets the {@link Item#price} of the item to the specified integer.
   *
   * @param price                     The price which the item should be set to
   * @throws IllegalNumberException   If the price specified is negative
   */
  public void setPrice(int price) {
    if (price < 0) {
      throw new IllegalNumberException("Price cannot be set to a negative integer");
    }
    this.price = price;
  }

  /**
   * Sets the {@link Item#discount} of the item. The discount is taken into account when
   * the {@link Item#getPrice()} method is called.
   *
   * @param discount                    The amount of discount specified
   *                                    in a percentage between 0 and 100
   * @throws IllegalNumberException     If the specified discount amount is
   *                                    below 0 or above 100
   */
  public void setDiscount(double discount) {
    if (discount < 0 || discount > 100) {
      throw new IllegalNumberException("Discount cannot "
              + "be set to a negative integer or an integer above 100");
    }
    this.discount = discount;
  }

  /**
   * Sets the {@link Item#warehouseStock} of the item to the specified amount.
   *
   * @param warehouseStock            The amount representing the number of items in stock
   * @throws IllegalNumberException   If the specified amount is below 0
   */
  public void setWarehouseStock(int warehouseStock) {
    if (warehouseStock < 0) {
      throw new IllegalNumberException("Warehouse stock cannot be set to a negative integer");
    }
    this.warehouseStock = warehouseStock;
  }

  /**
   * A standard to-string method with all the relevant information from this
   * item in a specific format.
   *
   * @return A string formatted with all the relevant information about this item
   */
  @Override
  public String toString() {
    return String.format(
            "| %-15s | %-22s | %6d (%8.2f%%)| %6d | %10.2fkg | "
                    + "%10.2fm | %10.2fm | %10.2fm | %-8s | %-18s | %s",
            this.getItemNumber(), this.getBrandName(),
            this.getPrice(), this.getDiscount(), this.getWarehouseStock(),
            this.getWeight(), this.getLength(), this.getHeight(),
            this.getWidth(), this.getColor(), this.getCategory(), this.getDescription());
  }
}
