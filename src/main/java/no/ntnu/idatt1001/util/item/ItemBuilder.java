package no.ntnu.idatt1001.util.item;

import java.util.Objects;
import no.ntnu.idatt1001.util.Category;
import no.ntnu.idatt1001.util.Color;
import no.ntnu.idatt1001.util.IllegalNumberException;


/**
 * A class representing a builder for constructing instances
 * of an {@link Item} object.
 *
 * @author 10124
 * @version 1.0.0
 */
public class ItemBuilder {

  private String itemNumber;
  private String description;
  private String brandName;
  private int price;
  private int warehouseStock;
  private double weight;
  private double length;
  private double height;
  private double width;

  private Color color;
  private Category category;

  /**
   * Sets the {@link ItemBuilder#itemNumber} to the specified string.
   *
   * @param itemNumber              The item number for an {@link Item} object
   * @return                        This builder object
   * @throws NullPointerException   If the specified parameter is null
   */
  public ItemBuilder setItemNumber(String itemNumber) {

    if (itemNumber == null) {
      throw new NullPointerException("Item number must be specified");
    }

    this.itemNumber = itemNumber;
    return this;
  }

  /**
   * Sets the {@link ItemBuilder#description} to the specified string.
   * If the specified string is null, the {@link ItemBuilder#description} is
   * set to an empty string.
   *
   * @param description    The description for an {@link Item} object
   * @return        This builder object
   */
  public ItemBuilder setDescription(String description) {

    this.description = Objects.requireNonNullElse(description, "");

    return this;
  }

  /**
   * Sets the {@link ItemBuilder#brandName} to the specified string.
   *
   * @param brandName              The brandName for an {@link Item} object
   * @return                       This builder object
   * @throws NullPointerException  If the specified parameter is null
   */
  public ItemBuilder setBrandName(String brandName) {

    if (brandName == null) {
      throw new NullPointerException("Brand name must be specified");
    }

    this.brandName = brandName;
    return this;
  }

  /**
   * Sets the {@link ItemBuilder#price} to the specified amount.
   *
   * @param price                  The price for an {@link Item} object
   * @return                       This builder object
   * @throws IllegalNumberException  If the specified parameter is negative
   */
  public ItemBuilder setPrice(int price) {

    if (price < 0) {
      throw new IllegalNumberException("Price cannot be negative");
    }

    this.price = price;
    return this;
  }


  /**
   * Sets the {@link ItemBuilder#warehouseStock} to the specified amount.
   *
   * @param warehouseStock           The warehouse stock for an {@link Item} object
   * @return                         This builder object
   * @throws IllegalNumberException  If the specified parameter is negative
   */
  public ItemBuilder setWarehouseStock(int warehouseStock) {
    if (warehouseStock < 0) {
      throw new IllegalNumberException("Warehouse stock cannot be negative");
    }

    this.warehouseStock = warehouseStock;
    return this;
  }

  /**
   * Sets the {@link ItemBuilder#weight} to the specified amount.
   *
   * @param weight                   The weight for an {@link Item} object
   * @return                         This builder object
   * @throws IllegalNumberException  If the specified parameter is negative or 0
   */
  public ItemBuilder setWeight(double weight) {

    if (weight <= 0) {
      throw new IllegalNumberException("Weight cannot be 0 or below");
    }

    this.weight = weight;
    return this;
  }

  /**
   * Sets the {@link ItemBuilder#length} to the specified amount.
   *
   * @param length                   The length for an {@link Item} object
   * @return                         This builder object
   * @throws IllegalNumberException  If the specified parameter is negative or 0
   */
  public ItemBuilder setLength(double length) {

    if (length <= 0) {
      throw new IllegalNumberException("Length cannot be 0 or below");
    }

    this.length = length;
    return this;
  }

  /**
   * Sets the {@link ItemBuilder#height} to the specified amount.
   *
   * @param height                   The height for an {@link Item} object
   * @return                         This builder object
   * @throws IllegalNumberException  If the specified parameter is negative or 0
   */
  public ItemBuilder setHeight(double height) {

    if (height <= 0) {
      throw new IllegalNumberException("Height cannot be 0 or below");
    }

    this.height = height;
    return this;
  }

  /**
   * Sets the {@link ItemBuilder#width} to the specified amount.
   *
   * @param width                    The width for an {@link Item} object
   * @return                         This builder object
   * @throws IllegalNumberException  If the specified parameter is negative or 0
   */
  public ItemBuilder setWidth(double width) {

    if (width <= 0) {
      throw new IllegalNumberException("Width cannot be 0 or below");
    }

    this.width = width;
    return this;
  }

  /**
   * Sets the {@link ItemBuilder#color} to the specified color.
   *
   * @param color                   The {@link Color} for an {@link Item} object
   * @return                        This builder object
   * @throws NullPointerException   If the specified parameter is null
   */
  public ItemBuilder setColor(Color color) {

    if (color == null) {
      throw new NullPointerException("Color must be specified");
    }

    this.color = color;
    return this;
  }

  /**
   * Sets the {@link ItemBuilder#category} to the specified category.
   *
   * @param category                The {@link Category} for an {@link Item} object
   * @return                        This builder object
   * @throws NullPointerException   If the specified parameter is null
   */
  public ItemBuilder setCategory(Category category) {

    if (category == null) {
      throw new NullPointerException("Category must be specified");
    }

    this.category = category;
    return this;
  }

  /**
   * Builds an {@link Item} object with all the specified variables. Since
   * this method calls all the set-methods in the {@link ItemBuilder} class, there
   * are some mandatory variables which need to be set before calling this method.
   *
   * @return  An instance of an {@link Item} object with
   *          the values set to this {@link ItemBuilder} object.
   */
  public Item build() {

    this.setItemNumber(itemNumber);
    this.setDescription(description);
    this.setBrandName(brandName);
    this.setPrice(price);
    this.setWarehouseStock(warehouseStock);
    this.setWeight(weight);
    this.setLength(length);
    this.setHeight(height);
    this.setWidth(width);
    this.setColor(color);
    this.setCategory(category);

    return new Item(itemNumber, description, brandName, price,
            warehouseStock, weight, length, height, width, color, category);
  }

  /**
   * A static method for creating a deep-copy of an {@link Item} object.
   * Utilizes the {@link Item#Item(Item)} constructor for creating a
   * new item with all the same field values as the specified item.
   *
   * @param item                  The {@link Item} which should be copied from
   * @return                      An instance of a new {@link Item} object which has
   *                              been deep-copied from the specified item
   * @throws NullPointerException If the parameter specified is {@code null}
   */
  public static Item deepCopy(Item item) {
    if (item == null) {
      throw new NullPointerException("Item cannot be null");
    }

    return new Item(item);
  }
}
