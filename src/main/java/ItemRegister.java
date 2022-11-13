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

  public void addItem(String itemNumber, String desc, String brandName, int price, int warehouseStock,
                      double weight, double length, double height, Color color, Category category){

    itemList.add(new Item(itemNumber,desc,brandName,price,warehouseStock,weight,length,height,color,category));
    sortByItemNumber();

  }

  public boolean increaseItemStock(Item itemInput, int stockIncrease){
    if (!itemList.contains(itemInput)){
      return false;
    }
    itemInput.setWarehouseStock(itemInput.getWarehouseStock() + stockIncrease);
    return true;
  }

  public boolean decreaseItemStock(Item itemInput, int stockDecrease){
    if (!itemList.contains(itemInput)){
      return false;
    }
    itemInput.setWarehouseStock(itemInput.getWarehouseStock() - stockDecrease);
    return true;
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

  public void fillListWithDefaultItems(){
    itemList.add(new Item("A1205B","Large Christmas Window","SULOLI",
            130,2,0.45,15.8,27.5,Color.WHITE,Category.WINDOWS));

    itemList.add(new Item("M5788B","Cherry Lumber","Barrington Hardwoods",
            120,8,2.43,30.48,3.2,Color.BROWN,Category.LUMBER));

    itemList.add(new Item("C1007B","Magnetic Thermal Insulated","Mpmedo",
            298,31,52.4,57.3,210.47,Color.GRAY,Category.DOORS));

    itemList.add(new Item("A1008B","Basement Hopper Window","Park products",
            977,76,8.6,96.3,45.2,Color.GRAY,Category.WINDOWS));

    itemList.add(new Item("F4020G","Self Adhesive Vinyl Floor Tile","Achim",
            173,211,4.08,30.48,30.48,Color.BLACK,Category.FLOOR_LAMINATES));
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
