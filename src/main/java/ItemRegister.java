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

  public void addItem(String itemNumber, String desc, String brandName, int price, int warehouseStock,
                      double weight, double length, double height, Color color, Category category){

    itemList.add(new Item(itemNumber,desc,brandName,price,warehouseStock,weight,length,height,color,category));
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
    addItem("A1205B","Large Christmas Window","SULOLI",
            130,2,0.45,15.8,27.5,Color.WHITE,Category.WINDOWS);
    addItem("M5788B","Cherry Lumber","Barrington Hardwoods",
            120,8,2.43,30.48,3.2,Color.BROWN,Category.LUMBER);
    addItem("C1007B","Magnetic Thermal Insulated","Mpmedo",
            298,31,52.4,57.3,210.47,Color.GRAY,Category.DOORS);
    addItem("A1008B","Basement Hopper Window","Park products",
            977,76,8.6,96.3,45.2,Color.GRAY,Category.WINDOWS);
    addItem("F4020G","Self Adhesive Vinyl Floor Tile","Achim",
            173,211,4.08,30.48,30.48,Color.BLACK,Category.FLOOR_LAMINATES);
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
            "| %-15s | %-22s | %-6s | %-6s | %-10s | %-10s | %-10s | %-8s | %-18s | %s\n",
            "ITEM NUMBER", "BRAND NAME", "PRICE", "STOCK", "WEIGHT",
            "LENGTH", "HEIGHT", "COLOR", "CATEGORY", "DESCRIPTION"));

    returnString.append("+ ").append("-".repeat(15)).append(" + ").append("-".repeat(22)).append(" + ")
            .append("-".repeat(6)).append(" + ").append("-".repeat(6)).append(" + ")
            .append("-".repeat(10)).append(" + ").append("-".repeat(10)).append(" + ")
            .append("-".repeat(10)).append(" + ").append("-".repeat(8)).append(" + ")
            .append("-".repeat(18)).append(" + ").append("-".repeat(64)).append("\n");

    for (Item item: itemList) {
      returnString.append(item.toString()).append("\n");
    }

    return returnString.toString();
  }


}
