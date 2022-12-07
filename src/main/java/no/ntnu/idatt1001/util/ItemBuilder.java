package no.ntnu.idatt1001.util;

public class ItemBuilder {

  private String itemNumber;
  private String desc;
  private String brandName;

  private int price;
  private int warehouseStock;
  private double discount;

  private double weight;
  private double length;
  private double height;

  private Color color;
  private Category category;

  public ItemBuilder setItemNumber(String itemNumber) {
    this.itemNumber = itemNumber;
    return this;
  }

  public ItemBuilder setDesc(String desc) {
    this.desc = desc;
    return this;
  }

  public ItemBuilder setBrandName(String brandName) {
    this.brandName = brandName;
    return this;
  }

  public ItemBuilder setPrice(int price) {
    this.price = price;
    return this;
  }


  public ItemBuilder setWarehouseStock(int warehouseStock) {
    this.warehouseStock = warehouseStock;
    return this;
  }

  public ItemBuilder setDiscount(double discount) {
    this.discount = discount;
    return this;
  }

  public ItemBuilder setWeight(double weight) {
    this.weight = weight;
    return this;
  }

  public ItemBuilder setLength(double length) {
    this.length = length;
    return this;
  }

  public ItemBuilder setHeight(double height) {
    this.height = height;
    return this;
  }

  public ItemBuilder setColor(Color color) {
    this.color = color;
    return this;
  }

  public ItemBuilder setCategory(Category category) {
    this.category = category;
    return this;
  }

  public Item build(){
    return new Item(itemNumber,desc,brandName,price,warehouseStock,weight,length,height,color,category);
  }
}
