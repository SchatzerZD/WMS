package no.ntnu.idatt1001.util;
/**
 * A class representing a builder for constructing instances
 * of an {@link Item} object.
 *
 * @author Daniel Ireneo Neri Saren
 * @version 1.0.0
 */
public class ItemBuilder {

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

  public ItemBuilder setItemNumber(String itemNumber) {

    if(itemNumber == null){
      throw new NullPointerException("Item number must be specified");
    }

    this.itemNumber = itemNumber;
    return this;
  }

  public ItemBuilder setDesc(String desc) {

    if(desc == null){
      this.desc = "";
    }else{
      this.desc = desc;
    }

    return this;
  }

  public ItemBuilder setBrandName(String brandName) {

    if(brandName == null){
      throw new NullPointerException("Brand name must be specified");
    }

    this.brandName = brandName;
    return this;
  }

  public ItemBuilder setPrice(int price) {

    if(price < 0){
      throw new IllegalNumberException("Price cannot be negative");
    }

    this.price = price;
    return this;
  }


  public ItemBuilder setWarehouseStock(int warehouseStock) {
    if(warehouseStock < 0){
      throw new IllegalNumberException("Warehouse stock cannot be negative");
    }

    this.warehouseStock = warehouseStock;
    return this;
  }

  public ItemBuilder setWeight(double weight) {

    if(weight <= 0){
      throw new IllegalNumberException("Weight cannot be 0 or below");
    }

    this.weight = weight;
    return this;
  }

  public ItemBuilder setLength(double length) {

    if(length <= 0){
      throw new IllegalNumberException("Length cannot be 0 or below");
    }

    this.length = length;
    return this;
  }

  public ItemBuilder setHeight(double height) {

    if(height <= 0){
      throw new IllegalNumberException("Height cannot be 0 or below");
    }

    this.height = height;
    return this;
  }

  public ItemBuilder setColor(Color color) {

    if(color == null){
      throw new NullPointerException("Color must be specified");
    }

    this.color = color;
    return this;
  }

  public ItemBuilder setCategory(Category category) {

    if(category == null){
      throw new NullPointerException("Category must be specified");
    }

    this.category = category;
    return this;
  }

  public Item build(){

    this.setItemNumber(itemNumber);
    this.setDesc(desc);
    this.setBrandName(brandName);
    this.setPrice(price);
    this.setWarehouseStock(warehouseStock);
    this.setWeight(weight);
    this.setLength(length);
    this.setHeight(height);
    this.setColor(color);
    this.setCategory(category);

    return new Item(itemNumber,desc,brandName,price,warehouseStock,weight,length,height,color,category);
  }
}
