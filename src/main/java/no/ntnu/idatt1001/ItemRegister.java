package no.ntnu.idatt1001;

import no.ntnu.idatt1001.util.Category;
import no.ntnu.idatt1001.util.Color;
import no.ntnu.idatt1001.util.Item;
import no.ntnu.idatt1001.util.ItemBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

public class ItemRegister {

  private final List<Item> itemList;

  public ItemRegister(){
    itemList = new ArrayList<>();
  }

  public Item searchByItemNumber(String itemNumberInput){
    return optionalItemFromList(item -> item.getItemNumber().equals(itemNumberInput))
            .orElse(null);
  }

  public Item searchByItemDesc(String itemDescInput){
    return optionalItemFromList(item -> item.getDesc().equals(itemDescInput))
            .orElse(null);
  }

  public void addItem(Item item){

    itemList.add(item);
    sortByItemNumber();
  }

  public void increaseItemStock(Item itemInput, int stockIncrease){
    optionalItemFromList(item -> item.equals(itemInput))
            .orElseThrow(NoSuchElementException::new)
            .setWarehouseStock(itemInput.getWarehouseStock() + stockIncrease);
  }

  public void decreaseItemStock(Item itemInput, int stockDecrease){
    optionalItemFromList(item -> item.equals(itemInput))
            .orElseThrow(NoSuchElementException::new)
            .setWarehouseStock(itemInput.getWarehouseStock() - stockDecrease);
  }

  public void changePriceOfItem(Item itemInput, int price){
    optionalItemFromList(item -> item.equals(itemInput))
            .orElseThrow(NoSuchElementException::new)
            .setPrice(price);
  }

  public void changeDiscountOfItem(Item itemInput, double discount){
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

  public void sortByItemNumber(){
    itemList.sort((o1,o2) -> {
      if(o1.getItemNumber().equals(o2.getItemNumber()))return 0;
      return o1.getItemNumber().compareTo(o2.getItemNumber()) > 0 ? 1 : -1;
    });
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
