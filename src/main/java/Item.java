/**
 * A class representing an item stored inside
 * the warehouse. An item contains information accessed
 * by the warehouse for simple warehouse functionality
 *
 * @author Daniel Ireneo Neri Saren
 * @version 1.0.0
 */
public class Item {
  private String itemNumber;
  private String desc;
  private String brandName;

  private int price;
  private int warehouseStock;

  private double weight;
  private double length;
  private double height;

  private Color color;
  private Category category;

  /**
   * Constructor for the Item class. Used for creating an object instance of this class
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
   * @throws IllegalArgumentException   If the specified price, warehouse stock, weight,
   *                                    length or height is below 0
   */
  public Item(String itemNumber, String desc, String brandName, int price, int warehouseStock,
              double weight, double length, double height, Color color, Category category) {

    if (price < 0 || warehouseStock < 0 || weight < 0 || length < 0 || height < 0) {
      throw new IllegalArgumentException();
    } else {
      this.price = price;
      this.warehouseStock = warehouseStock;
      this.weight = weight;
      this.length = length;
      this.height = height;
    }

    this.itemNumber = itemNumber;
    this.desc = desc;
    this.brandName = brandName;

    this.color = color;
    this.category = category;

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

  public int getPrice() {
    return price;
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

  public void setPrice(int price) {
    this.price = price;
  }

  public void setWarehouseStock(int warehouseStock) {
    this.warehouseStock = warehouseStock;
  }

  @Override
  public String toString() {
    return String.format("| %-15s | %-22s | %-6d | %-6d | %-10.2f | %-10.2f | %-10.2f | %-8s | %-18s | %s\n",
            this.getItemNumber(), this.getBrandName(),
            this.getPrice(), this.getWarehouseStock(),
            this.getWeight(), this.getLength(), this.getHeight(),
            this.getColor(), this.getCategory(),this.getDesc());
  }
}
