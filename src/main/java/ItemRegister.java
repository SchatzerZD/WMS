import java.util.ArrayList;
import java.util.List;

public class ItemRegister {

  private List<Item> itemList;

  public ItemRegister(){
    itemList = new ArrayList<>();
  }


  public void addItem(Item itemInput){
    itemList.add(itemInput);
  }

  @Override
  public String toString() {
    String returnString = String.format("%-10s %-10s %-6s %-6s %-6s %-6s %-6s %-8s %-12s \n","Item number","Brand Name","Item Price","Stock","Weight","Length","Height","Color","Category");
    for (Item item: itemList) {
      returnString +=
              String.format("%-10s %-10s %-6d %-6d %-6.2f %-6.2f %-6.2f %-8s %-12s \n",
                            item.getItemNumber(), item.getBrandName(),
                            item.getPrice(), item.getWarehouseStock(),
                            item.getWeight(), item.getLength(), item.getHeight(),
                            item.getColor(), item.getCategory());
    }
    return returnString;
  }
}
