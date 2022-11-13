import java.util.Scanner;

public class Menu {
  private final ItemRegister itemRegister;
  private final Scanner scanner;

  public Menu(ItemRegister itemRegister){
    this.itemRegister = itemRegister;
    this.scanner = new Scanner(System.in);
  }

  public void selectMenu(int menuSelection){

    final int PRINT_OUT_ITEMS = 1;
    final int SEARCH_FOR_ITEM = 2;
    final int ADD_NEW_ITEM = 3;
    final int INCREASE_ITEM_STOCK = 4;
    final int DECREASE_ITEM_STOCK = 5;
    final int REMOVE_ITEM = 6;
    final int CHANGE_ITEM_PRICE = 7;

    switch (menuSelection) {
      case PRINT_OUT_ITEMS -> printOutItems();
      case SEARCH_FOR_ITEM -> searchForItem();
      case ADD_NEW_ITEM -> addNewItem();
      case INCREASE_ITEM_STOCK -> increaseItemStock();
      case DECREASE_ITEM_STOCK -> decreaseItemStock();
      case REMOVE_ITEM -> removeItem();
      case CHANGE_ITEM_PRICE -> changeItemPrice();
      default -> noMenuWasSelected();
    }

  }

  private void printOutItems() {
    System.out.println(itemRegister);
  }

  private void searchForItem() {
    System.out.println("Search for item by number or description");
    String userInput = scanner.nextLine();

    if(itemRegister.searchByItemNumber(userInput) != null) System.out.println(itemRegister.searchByItemNumber(userInput));
    else if(itemRegister.searchByItemDesc(userInput) != null) System.out.println(itemRegister.searchByItemDesc(userInput));
    else System.out.println("Item not found");
  }

  private void addNewItem() {

  }

  private void increaseItemStock(){

  }

  private void decreaseItemStock(){

  }

  private void removeItem(){

  }

  private void changeItemPrice(){

  }

  private void noMenuWasSelected(){
    scanner.close();
  }

}
