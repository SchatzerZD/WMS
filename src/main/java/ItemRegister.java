import java.util.ArrayList;
import java.util.List;

public class ItemRegister {

  private final List<Item> itemList;

  public ItemRegister(){
    itemList = new ArrayList<>();
  }


  public Item searchByItemNumber(String itemNumberInput){
    for (Item item: itemList) {
      if(item.getItemNumber().equals(itemNumberInput))return item;
    }
    return null;
  }

  public Item searchByItemDesc(String itemDescInput){
    for (Item item: itemList) {
      if(item.getDesc().equals(itemDescInput))return item;
    }
    return null;
  }

  public void addItem(Item itemInput){
    itemList.add(itemInput);
    sortByItemNumber();
  }

  public void addStockToItem(Item itemInput, int stock){
    itemInput.setWarehouseStock(itemInput.getWarehouseStock() + stock);
  }

  public void sortByItemNumber(){
    itemList.sort((o1,o2) -> {
      if(o1.getItemNumber().equals(o2.getItemNumber()))return 0;
      return o1.getItemNumber().compareTo(o2.getItemNumber()) > 0 ? 1 : -1;
    });
  }

  public List<Item> getItemList(){
    return itemList;
  }

  @Override
  public String toString() {
    StringBuilder returnString = new StringBuilder(String.format(
            "| %-15s | %-22s | %-6s | %-6s | %-10s | %-10s | %-10s | %-8s | %-18s | %s\n",
            "ITEM NUMBER", "BRAND NAME", "PRICE", "STOCK", "WEIGHT",
            "LENGTH", "HEIGHT", "COLOR", "CATEGORY", "DESCRIPTION"));

    returnString.append("+ ").append("-".repeat(15)).append(" + ").append("-".repeat(22)).append(" + ")
            .append("-".repeat(6)).append(" + ").append("-".repeat(6)).append(" + ")
            .append("-".repeat(10)).append(" + ").append("-".repeat(10)).append(" + ")
            .append("-".repeat(10)).append(" + ").append("-".repeat(8)).append(" + ")
            .append("-".repeat(18)).append(" + ").append("-".repeat(64)).append("\n");

    for (Item item: itemList) {
      returnString.append(item.toString());
    }

    return returnString.toString();
  }


}
