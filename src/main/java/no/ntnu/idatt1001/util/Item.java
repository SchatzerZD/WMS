package no.ntnu.idatt1001.util;

/**
 * A class representing an item stored inside
 * the warehouse. An item contains information accessed
 * by the warehouse for simple warehouse functionality
 *
 * @author Daniel Ireneo Neri Saren
 * @version 1.1.0
 */
public class Item {
  private final String itemNumber;
  private String desc;
  private final String brandName;

  private int price;
  private int warehouseStock;
  private double discount;

  private final double weight;
  private final double length;
  private final double height;

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
   * @param desc                        A text which describes the item
   * @param brandName                   A text containing the brand which the item was created by
   * @param price                       Integer representing the price of the item
   * @param warehouseStock              Integer representing how many of
   *                                    this item is left in the warehouse
   * @param weight                      The weight of the item
   * @param length                      The length of the item
   * @param height                      The height of the item
   * @param color                       The color of the item
   * @param category                    Which warehouse category the item belongs to
   * @throws IllegalNumberException     If the specified price is below 0. Also throws
   *                                    if weight, length or height is 0 or below 0.
   */
  protected Item(String itemNumber, String desc, String brandName, int price, int warehouseStock,
       double weight, double length, double height, Color color, Category category) {

    if (price < 0 || warehouseStock < 0 || weight <= 0 || length <= 0 || height <= 0) {
      throw new IllegalNumberException();
    }

    this.price = price;
    this.warehouseStock = warehouseStock;
    this.weight = weight;
    this.length = length;
    this.height = height;

    this.itemNumber = itemNumber;
    this.desc = desc;
    this.brandName = brandName;

    this.color = color;
    this.category = category;
    this.discount = 0;

  }

  public String getItemNumber() {
    return itemNumber;
  }

  public String getDesc() {
    return desc;
  }

  public String getBrandName() {
    return brandName;
  }

  /**
   * Gets the price of the item after calculation. The calculation
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

  public double getDiscount() {
    return discount;
  }

  public int getWarehouseStock() {
    return warehouseStock;
  }

  public double getWeight() {
    return weight;
  }

  public double getLength() {
    return length;
  }

  public double getHeight() {
    return height;
  }

  public Color getColor() {
    return color;
  }

  public Category getCategory() {
    return category;
  }


  public void setDesc(String desc) {
    this.desc = desc;
  }

  /**
   * Sets the price of the item to the specified integer.
   *
   * @param price                     The price which the item should be set to
   * @throws IllegalNumberException   If the price specified is negative
   */
  public void setPrice(int price) {
    if (price < 0) {
      throw new IllegalNumberException();
    }
    this.price = price;
  }

  /**
   * Sets the discount of the item. The discount is taken into account when
   * the {@link Item#getPrice()} method is called.
   *
   * @param discount                    The amount of discount specified
   *                                    in a percentage between 0 and 100
   * @throws IllegalArgumentException   If the specified discount amount is
   *                                    below 0 or above 100
   */
  public void setDiscount(double discount) {
    if (discount < 0 || discount > 100) {
      throw new IllegalArgumentException();
    }
    this.discount = discount;
  }

  /**
   * Sets the warehouse stock of the item to the specified amount.
   *
   * @param warehouseStock            The amount representing the number of items in stock
   * @throws IllegalNumberException   If the specified amount is below 0
   */
  public void setWarehouseStock(int warehouseStock) {
    if (warehouseStock < 0) {
      throw new IllegalNumberException();
    }
    this.warehouseStock = warehouseStock;
  }

  @Override
  public String toString() {
    return String.format(
            "| %-15s | %-22s | %6d (%8.2f%%)| %6d | %10.2f | %10.2f | %10.2f | %-8s | %-18s | %s",
            this.getItemNumber(), this.getBrandName(),
            this.getPrice(), this.getDiscount(), this.getWarehouseStock(),
            this.getWeight(), this.getLength(), this.getHeight(),
            this.getColor(), this.getCategory(), this.getDesc());
  }
}
