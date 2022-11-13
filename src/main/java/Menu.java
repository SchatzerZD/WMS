import java.util.Arrays;
import java.util.Scanner;

public class Menu {
  private final ItemRegister itemRegister;
  private final Scanner scanner;

  public Menu(ItemRegister itemRegister) {
    this.itemRegister = itemRegister;
    this.scanner = new Scanner(System.in);
  }

  public void selectMenu(int menuSelection) {

    final int printOutItems = 1;
    final int searchForItem = 2;
    final int addNewItem = 3;
    final int increaseItemStock = 4;
    final int decreaseItemStock = 5;
    final int removeItem = 6;
    final int changeItemPrice = 7;

    switch (menuSelection) {
      case printOutItems -> printOutItems();
      case searchForItem -> searchForItem();
      case addNewItem -> addNewItem();
      case increaseItemStock -> increaseItemStock();
      case decreaseItemStock -> decreaseItemStock();
      case removeItem -> removeItem();
      case changeItemPrice -> changeItemPrice();
      default -> noMenuWasSelected();
    }

  }

  private void printOutItems() {
    System.out.println(itemRegister);
    getUserInput("Press ENTER to return to main menu");
  }

  private void searchForItem() {
    System.out.println("Search for item by number or description");
    String userInput = scanner.nextLine();

    if (itemRegister.searchByItemNumber(userInput) != null) {
      System.out.println(itemRegister.searchByItemNumber(userInput));
    } else if (itemRegister.searchByItemDesc(userInput) != null) {
      System.out.println(itemRegister.searchByItemDesc(userInput));
    } else {
      System.out.println("Item not found");
    }

  }

  private void addNewItem() {
    itemRegister.addItem(getUserInput("Input item number"),getUserInput("Input item description"),
            getUserInput("Input item brand"),Integer.parseInt(getUserInput("Input item price")),
            Integer.parseInt(getUserInput("Input item stock")), Double.parseDouble(getUserInput("Specify item weight")),
            Double.parseDouble(getUserInput("Specify item length")),Double.parseDouble(getUserInput("Specify item height")),
            Color.valueOf(getUserInput("Specify item color " + Arrays.toString(Color.values())).toUpperCase()),
            Category.valueOf(getUserInput("Input item category " + Arrays.toString(Category.values())).toUpperCase()));
  }

  private void increaseItemStock(){

  }

  private void decreaseItemStock(){

  }

  private void removeItem(){

  }

  private void changeItemPrice(){

  }

  private void noMenuWasSelected() {
    scanner.close();
  }

  public String getUserInput(String message){
    System.out.println(message);
    return scanner.nextLine();
  }


  @Override
  public String toString() {
    return """
            -------------- MENU --------------
            1. Print out items in List
            2. Search for item
            3. Add new item
            4. Increase stock of an item
            5. Decrease stock of an item
            6. Remove an item
            7. Change price of an item
            
            Select option by typing the index number
            Press any other key to exit program
            """;
  }
}
