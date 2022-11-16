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
    getUserInput("Press ENTER to continue");
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

    getUserInput("Press ENTER to continue");

  }

  private void addNewItem() {
    itemRegister.addItem(getUserInput("Input item number",() -> {
              boolean itemNumberExists;
              boolean isNullString;
              String itemNumberInput;
              do{
                itemNumberExists = false;
                isNullString = false;
                itemNumberInput = scanner.nextLine();
                if(itemNumberInput.equals("")){
                  System.out.print("Cant input empty string: ");
                  isNullString = true;
                  continue;
                }
                for (Item item: itemRegister.getItemList()) {
                  if (item.getItemNumber().equals(itemNumberInput)) {
                    itemNumberExists = true;
                    break;
                  }
                }
                if(itemNumberExists){
                  System.out.print("Item number already exists, try again: ");
                }
              }while(itemNumberExists || isNullString);

              return itemNumberInput;
            }),getUserInput("Input item description", () -> {
              boolean isNullString;
              String itemDescInput;

              do{
                isNullString = false;
                itemDescInput = scanner.nextLine();
                if(itemDescInput.equals("")){
                  isNullString = true;
                  System.out.print("Cant input empty string: ");
                }
              }while(isNullString);

              return itemDescInput;
            }),
            getUserInput("Input item brand", () -> {
              boolean isNullString;
              String itemBrandInput;

              do{
                isNullString = false;
                itemBrandInput = scanner.nextLine();
                if(itemBrandInput.equals("")){
                  isNullString = true;
                  System.out.print("Cant input empty string: ");
                }
              }while(isNullString);

              return itemBrandInput;
            }),Integer.parseInt(getUserInput("Input item price", intUserInput())),
            Integer.parseInt(getUserInput("Input item stock", intUserInput())), Double.parseDouble(getUserInput("Specify item weight", doubleUserInput())),
            Double.parseDouble(getUserInput("Specify item length", doubleUserInput())),Double.parseDouble(getUserInput("Specify item height", doubleUserInput())),
            Color.valueOf(getUserInput("Specify item color " + Arrays.toString(Color.values()),() -> {
              boolean isColor;
              String colorInput;
              do{
                isColor = false;
                colorInput = scanner.nextLine().toUpperCase();
                try{
                  Color.valueOf(colorInput);
                  isColor = true;
                }catch (IllegalArgumentException iae){
                  System.out.print("Could not find color, try again: ");
                }
              }while(!isColor);


              return colorInput;
            })),
            Category.valueOf(getUserInput("Input item category " + Arrays.toString(Category.values()), () -> {
              boolean isCategory;
              String categoryInput;
              do{
                isCategory = false;
                categoryInput = scanner.nextLine().toUpperCase();
                try{
                  Category.valueOf(categoryInput);
                  isCategory = true;
                }catch (IllegalArgumentException iae){
                  System.out.print("Could not find category, try again: ");
                }
              }while(!isCategory);


              return categoryInput;
            })));

    System.out.println("Item successfully added");
    getUserInput("Press ENTER to continue");
  }

  private void increaseItemStock(){

    System.out.printf("%-5s | %-15s | %-22s | %-6s | %-6s | %-10s | %-10s | %-10s | %-8s | %-18s | %s\n",
                      "index","ITEM NUMBER", "BRAND NAME", "PRICE", "STOCK", "WEIGHT",
                      "LENGTH", "HEIGHT", "COLOR", "CATEGORY", "DESCRIPTION");
    for (int i = 0; i < itemRegister.size(); i++) {
      System.out.printf("%5d %s\n",i+1,itemRegister.getItem(i));
    }

    int listIndex = Integer.parseInt(getUserInput("Input the index number of the item you wish to edit", () -> {
      boolean isListIndex;
      String indexInput;

      do {
        isListIndex = false;
        indexInput = scanner.nextLine();

        try{
          int stringToInt = Integer.parseInt(indexInput);
          if(stringToInt > 0 && stringToInt <= itemRegister.size()){
            isListIndex = true;
          }else{
            System.out.print("Input a number in the index list, try again: ");
          }
        }catch (NumberFormatException nfe){
          System.out.print("Input numbers, try again: ");
        }

      }while(!isListIndex);

      return indexInput;
    })) - 1;

    Item selectedItem = itemRegister.getItem(listIndex);


  }

  private void decreaseItemStock(){

  }

  private void removeItem(){

  }

  private void changeItemPrice(){

  }

  private void noMenuWasSelected() {
    System.out.println("Application closed");
    scanner.close();
    System.exit(0);
  }

  public String getUserInput(String message,UserInput userInput){
    System.out.print(message + ": ");
    return userInput.input();
  }

  public String getUserInput(String message){
    System.out.print(message + ": ");
    return scanner.nextLine();
  }


  private UserInput intUserInput(){
    return () -> {
      boolean isNumber;
      boolean isNegative;
      String numberStringInput;

      do{
        isNumber = false;
        isNegative = false;
        numberStringInput = scanner.nextLine();
        try{
          if(numberStringInput.equals("")){
            throw new NullPointerException();
          }
          int stringToInt = Integer.parseInt(numberStringInput);
          isNumber = true;
          if(stringToInt < 0){
            isNegative = true;
            System.out.print("Can't input negative numbers, try again: ");
          }
        }catch (NumberFormatException | NullPointerException e){
          System.out.print("Input numbers, try again: ");
        }
      }while (!isNumber || isNegative);

      return numberStringInput;
    };
  }
  private UserInput doubleUserInput(){
    return () -> {
      boolean isNumber;
      boolean isNegative;
      String numberStringInput;

      do{
        isNumber = false;
        isNegative = false;
        numberStringInput = scanner.nextLine();
        try{
          double stringToDouble = Double.parseDouble(numberStringInput);
          isNumber = true;
          if(stringToDouble < 0){
            isNegative = true;
            System.out.print("Can't input negative numbers, try again: ");
          }
        }catch (NumberFormatException | NullPointerException e){
          System.out.print("Input numbers. try again: ");
        }
      }while (!isNumber || isNegative);

      return numberStringInput;
    };
  }

  public Scanner getScanner() {
    return scanner;
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
            Press any other key to exit program""";
  }
}
