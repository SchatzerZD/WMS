package no.ntnu.idatt1001.common;

import java.util.*;

import no.ntnu.idatt1001.common.lib.ItemRegister;
import no.ntnu.idatt1001.common.lib.UserInput;
import no.ntnu.idatt1001.util.Category;
import no.ntnu.idatt1001.util.Color;
import no.ntnu.idatt1001.util.item.Item;
import no.ntnu.idatt1001.util.item.ItemBuilder;

public class Menu {

  private final ItemRegister itemRegister;
  private final Scanner scanner;

  public Menu(ItemRegister itemRegister) {
    this.itemRegister = itemRegister;
    this.scanner = new Scanner(System.in);
  }

  public void start(){
    while(true){
      if(itemRegister == null){
        System.out.println("Item register is null\nProgram closing...");
        break;
      }

      System.out.println(this);

      try{
        selectMenu(MenuOption.getMenuOption(Integer.parseInt(getUserInput("Input", scanner::nextLine))));
      }catch (NoSuchElementException nse){
        System.out.println("Menu option not found\nProgram closing...");
        noMenuWasSelected();
      }catch (NumberFormatException nfe){
        System.out.println("No numbers were inputted\nProgram closing...");
        noMenuWasSelected();
      }

    }
  }

  private void selectMenu(MenuOption menuSelection) {

    switch (menuSelection) {
      case PRINT_OUT_ITEMS -> printOutItems();
      case SEARCH_FOR_ITEM -> searchForItem();
      case ADD_NEW_ITEM -> addNewItem();
      case INCREASE_ITEM_STOCK -> increaseItemStock();
      case DECREASE_ITEM_STOCK -> decreaseItemStock();
      case REMOVE_ITEM -> removeItem();
      case CHANGE_ITEM_PRICE -> changeItemPrice();
      case CHANGE_ITEM_DISCOUNT -> changeItemDiscount();
      case SORT_LIST -> sortList();
      default -> noMenuWasSelected();
    }
  }

  private void printOutItems() {
    System.out.println(itemRegister);
    halt();
  }

  private void searchForItem() {
    System.out.println("Search for item by item number or description");
    String userInput = scannerNextLine();

    Item itemSearchedFor = itemRegister.searchByItemNumber(userInput);
    boolean found = false;

    if (itemSearchedFor != null) {
      System.out.println(itemSearchedFor);
      found = true;
    } else{
      itemSearchedFor = itemRegister.searchByItemDesc(userInput);
    }

    if(!found && itemSearchedFor != null){
      System.out.println(itemSearchedFor);
      found = true;
    }

    if(!found){
      System.out.println("Item not found");
    }

    halt();

  }

  private void addNewItem() {

    Item newItem = new ItemBuilder()
            .setItemNumber(getUserInput("Input item number",() -> {
              boolean itemNumberExists;
              boolean isNullString;
              String itemNumberInput;
              do{
                itemNumberExists = false;
                isNullString = false;
                itemNumberInput = scannerNextLine();
                if(itemNumberInput.equals("")){
                  System.out.print("Cant input empty string: ");
                  isNullString = true;
                  continue;
                }
                List<Item> itemArrayList = itemRegister.getCopyOfList();
                String finalItemNumberInput = itemNumberInput;
                itemNumberExists = itemArrayList
                        .stream()
                        .anyMatch(item -> item.getItemNumber().equalsIgnoreCase(finalItemNumberInput));

                if(itemNumberExists){
                  System.out.print("Item number already exists, try again: ");
                }
              }while(itemNumberExists || isNullString);

              return itemNumberInput;
            }))

            .setDesc(getUserInput("Input item description", () -> {
              boolean isNullString;
              String itemDescInput;

              do{
                isNullString = false;
                itemDescInput = scannerNextLine();
                if(itemDescInput.equals("")){
                  isNullString = true;
                  System.out.print("Cant input empty string: ");
                }
              }while(isNullString);

              return itemDescInput;
            }))

            .setBrandName(getUserInput("Input item brand", () -> {
              boolean isNullString;
              String itemBrandInput;

              do{
                isNullString = false;
                itemBrandInput = scannerNextLine();
                if(itemBrandInput.equals("")){
                  isNullString = true;
                  System.out.print("Cant input empty string: ");
                }
              }while(isNullString);

              return itemBrandInput;
            }))

            .setPrice(Integer.parseInt(getUserInput("Input item price", intUserInput())))
            .setWarehouseStock(Integer.parseInt(getUserInput("Input item stock", intUserInput())))
            .setWeight(Double.parseDouble(getUserInput("Specify item weight", doubleUserInput(true))))
            .setLength(Double.parseDouble(getUserInput("Specify item length", doubleUserInput(true))))
            .setHeight(Double.parseDouble(getUserInput("Specify item height", doubleUserInput(true))))
            .setColor(Color.valueOf(getUserInput("Specify item color " + Arrays.toString(Color.values()),() -> {
              boolean isColor;
              String colorInput;
              do{
                isColor = false;
                colorInput = scannerNextLine().toUpperCase();
                try{
                  Color.valueOf(colorInput);
                  isColor = true;
                }catch (IllegalArgumentException iae){
                  System.out.print("Could not find color, try again: ");
                }
              }while(!isColor);


              return colorInput;
            })))

            .setCategory(Category.valueOf(getUserInput("Input item category " + Arrays.toString(Category.values()), () -> {
              boolean isCategory;
              String categoryInput;
              do{
                isCategory = false;
                categoryInput = scannerNextLine().toUpperCase();
                try{
                  Category.valueOf(categoryInput);
                  isCategory = true;
                }catch (IllegalArgumentException iae){
                  System.out.print("Could not find category, try again: ");
                }
              }while(!isCategory);


              return categoryInput;
            })))

            .build();


    itemRegister.addItem(newItem);

    System.out.println("Item successfully added");
    halt();
  }

  private void increaseItemStock(){
    Item selectedItem = itemSelection();

    int stockIncrease = Integer.parseInt(getUserInput("Input stock amount which should be added", () -> {
      boolean isGreaterThanMax;
      String numberInput;
      do {
        numberInput = intUserInput().input();
        isGreaterThanMax = false;

        int stringToInt = Integer.parseInt(numberInput);
        if(selectedItem.getWarehouseStock() + stringToInt < 0){
          isGreaterThanMax = true;
          System.out.print("Stock amount exceeds maximum amount, try again: ");
        }
      }while (isGreaterThanMax);

      return numberInput;
    }));

    try{
      itemRegister.increaseItemStock(selectedItem, stockIncrease);
      System.out.printf("Item updated:\n %4d %s\n", itemRegister.getIndexOfItem(selectedItem) + 1,selectedItem);
      halt();
    }catch (NoSuchElementException nee){
      System.out.print("Item was not found in the item register");
    }

  }

  private void decreaseItemStock(){
    Item selectedItem = itemSelection();

    int stockDecrease = Integer.parseInt(getUserInput("Input stock amount which should be removed", () -> {
      boolean isGreaterThanStock;
      String numberInput;
      do {
        numberInput = intUserInput().input();
        isGreaterThanStock = false;

        int stringToInt = Integer.parseInt(numberInput);
        if(selectedItem.getWarehouseStock() < stringToInt){
          isGreaterThanStock = true;
          System.out.print("Number inputted is greater than item stock, try again: ");
        }
      }while (isGreaterThanStock);

      return numberInput;
    }));

    try{
      itemRegister.decreaseItemStock(selectedItem, stockDecrease);
      System.out.printf("Item updated:\n %4d %s\n", itemRegister.getIndexOfItem(selectedItem) + 1,selectedItem);
      halt();
    }catch (NoSuchElementException nee){
      System.out.print("Item was not found in the item register");
    }

  }

  private void removeItem(){
    Item selectedItem = itemSelection();

    if(itemRegister.removeItem(selectedItem)){
      System.out.print("Item successfully removed\n");
      halt();
    }

  }

  private void changeItemPrice(){
    Item selectedItem = itemSelection();

    int newPrice = Integer.parseInt(getUserInput("Input new price for the item", intUserInput()));
    try{
      itemRegister.changePriceOfItem(selectedItem,newPrice);
      System.out.printf("Item updated:\n %4d %s\n", itemRegister.getIndexOfItem(selectedItem) + 1,selectedItem);
      halt();
    }catch (NoSuchElementException nee){
      System.out.println("Item was not found in the item register");
    }
  }

  private void changeItemDiscount() {
    Item selectedItem = itemSelection();
    double newDiscount;

    do{
      newDiscount = Double.parseDouble(getUserInput("Input new discount for the item (0-100)", doubleUserInput(false)));
      if(newDiscount > 100){
        System.out.println("Discount cannot be greater than 100");
      }

    }while (newDiscount > 100);

    try{
      itemRegister.changeDiscountOfItem(selectedItem,newDiscount);
      System.out.printf("Item updated:\n %4d %s\n", itemRegister.getIndexOfItem(selectedItem) + 1,selectedItem);
      halt();
    }catch (IllegalArgumentException iae){
      System.out.println("Something went wrong with inputted number");
    }catch (NoSuchElementException nee){
      System.out.println("Item was not found in the item register");
    }
  }

  private void sortList() {
    String sortMenu =
            """
            
            1. Sort by item number
            2. Sort by brand name
            3. Sort by price
            4. Sort by warehouse stock
            5. Sort by color
            6. Sort by category
            """;
    System.out.println(sortMenu);

    int sortMenuSelection;

    do {
      sortMenuSelection = Integer.parseInt(getUserInput("Choose sort option",intUserInput()));
      if(sortMenuSelection < 1 || sortMenuSelection > 6){
        System.out.println("Input a valid number, try again");
      }
    }while(sortMenuSelection < 1 || sortMenuSelection > 6);

    boolean ascending = Boolean.parseBoolean(getUserInput("Ascending? (y/n)",() ->{
      String booleanStringInput = scannerNextLine().toLowerCase();
      if(booleanStringInput.equals("y") || booleanStringInput.equals("yes")){
        return "True";
      }else{
        return "False";
      }
    }));

    switch (sortMenuSelection){
      case 1 -> itemRegister.sortListByItemnumber(ascending);
      case 2 -> itemRegister.sortListByBrandname(ascending);
      case 3 -> itemRegister.sortListByPrice(ascending);
      case 4 -> itemRegister.sortListByWarehousestock(ascending);
      case 5 -> itemRegister.sortListByColor(ascending);
      case 6 -> itemRegister.sortListByCategory(ascending);
    }
    System.out.println("List sorted successfully");
    halt();
  }

  private void noMenuWasSelected() {
    System.out.println("Application closed");
    scanner.close();
    System.exit(0);
  }

  public String getUserInput(String message, UserInput userInput){
    System.out.print(message + ": ");
    return userInput.input();
  }

  public void halt(){
    System.out.println("Press ENTER to continue");
    scanner.nextLine();
  }


  private UserInput intUserInput(){
    return () -> {
      boolean isNumber;
      boolean isNegative;
      String numberStringInput;

      do{
        isNumber = false;
        isNegative = false;
        numberStringInput = scannerNextLine();
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
  private UserInput doubleUserInput(boolean zeroIsInclusive){
    return () -> {
      boolean isNumber;
      boolean isNegative;
      String numberStringInput;

      do{
        isNumber = false;
        isNegative = false;
        numberStringInput = scannerNextLine();

        try{
          double stringToDouble = Double.parseDouble(numberStringInput);
          isNumber = true;
          if(!zeroIsInclusive && stringToDouble < 0){
            isNegative = true;
            System.out.print("Can't input negative numbers, try again: ");
          }else if(zeroIsInclusive && stringToDouble <= 0){
            isNegative = true;
            System.out.print("Can't input 0 or negative numbers, try again: ");
          }
        }catch (NumberFormatException | NullPointerException e){
          System.out.print("Input numbers. try again: ");
        }
      }while (!isNumber || isNegative);

      return numberStringInput;
    };
  }

  private Item itemSelection(){
    System.out.printf("%-5s | %-15s | %-22s | %-17s | %-6s | %-10s | %-10s | %-10s | %-8s | %-18s | %s\n",
            "index","ITEM NUMBER", "BRAND NAME", "PRICE (DISCOUNT)", "STOCK", "WEIGHT",
            "LENGTH", "HEIGHT", "COLOR", "CATEGORY", "DESCRIPTION");
    for (int i = 0; i < itemRegister.size(); i++) {
      System.out.printf("%5d %s\n",i+1,itemRegister.getItem(i));
    }

    int listIndex = Integer.parseInt(getUserInput("Input the index number of the item you wish to edit", () -> {
      boolean isListIndex;
      String indexInput;

      do {
        isListIndex = false;
        indexInput = scannerNextLine();

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
    System.out.printf("Selected item:\n%5d %s\n",listIndex +1,selectedItem);

    return selectedItem;
  }

  private String scannerNextLine(){
    String userInput = scanner.nextLine();
    if(userInput.equals("/BACK")){
      start();
    }
    return userInput;

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
            8. Change discount of an item
            9. Sort List
            
            Select option by typing the index number
            Type "/BACK" to return to this menu at any point
            Press any other key to exit program""";
  }
}
