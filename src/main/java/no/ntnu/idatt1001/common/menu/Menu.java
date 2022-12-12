package no.ntnu.idatt1001.common.menu;


import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import no.ntnu.idatt1001.common.ItemRegister;
import no.ntnu.idatt1001.common.UserInput;
import no.ntnu.idatt1001.util.Category;
import no.ntnu.idatt1001.util.Color;
import no.ntnu.idatt1001.util.item.Item;
import no.ntnu.idatt1001.util.item.ItemBuilder;


/**
 * A class representing a menu for user interface
 * functionality. The menu class is able to navigate
 * through an {@link ItemRegister} object provides the
 * user the ability to modify the register's item list.
 *
 * @author 10124
 * @version 1.0.0
 */
public class Menu {

  private final ItemRegister itemRegister;
  private final Scanner scanner;

  /**
   * A constructor for the {@link Menu} class. Creating an instance
   * requires an {@link ItemRegister} object which will be used
   * through the menu object. An object of the {@link Scanner} class
   * is also initialized for getting user input.
   *
   * @param itemRegister  The {@link ItemRegister} which the menu will
   *                      be navigating through
   */
  public Menu(ItemRegister itemRegister) {
    this.itemRegister = itemRegister;
    this.scanner = new Scanner(System.in);
  }

  /**
   * The start method which is used to start the whole
   * menu user interface. The program enters an indefinite
   * while loop while the menu is running. The user is prompted
   * to input an index which corresponds to the index number of
   * the specific menu option in {@link MenuOption}. This user input
   * is achieved by using the {@link Scanner} class and the implementation
   * of the {@link UserInput} interface. The {@link Menu#selectMenu(MenuOption)}
   * method is called inside the loop as long as the application is running.
   */
  public void start() {
    while (true) {
      if (itemRegister == null) {
        System.out.println("Item register is null\nProgram closing...");
        break;
      }

      System.out.println(this);

      try {
        selectMenu(MenuOption.getMenuOption(
                Integer.parseInt(getUserInput("Input", scanner::nextLine))));
      } catch (NoSuchElementException nse) {
        System.out.println("\n\nMenu option not found, try again");
      } catch (NumberFormatException nfe) {
        System.out.println("\n\nPlease input a number");
      }

    }
  }

  /**
   * A method for selecting a function which will be performed on
   * the register by the user.
   *
   * @param menuSelection The menu option which should be selected
   */
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
      case CHANGE_ITEM_DESCRIPTION -> changeItemDescription();
      case SORT_LIST -> sortList();
      case EXIT -> exit();
      default -> System.out.println("Something went wrong with the menu selection");
    }
  }

  /**
   * Utilizes the {@link ItemRegister#toString()} method for printing out
   * the whole item list in the register.
   */
  private void printOutItems() {
    System.out.println(itemRegister);
    halt();
  }

  /**
   * Searches for an item in the register by utilizing the
   * {@link ItemRegister#searchByItemDesc(String)} and
   * {@link ItemRegister#searchByItemNumber(String)} methods.
   */
  private void searchForItem() {
    System.out.println("Search for item by item number or description");
    String userInput = scannerNextLine();

    Item itemSearchedFor = itemRegister.searchByItemNumber(userInput);
    boolean found = false;

    if (itemSearchedFor != null) {
      System.out.println(itemSearchedFor);
      found = true;
    } else {
      itemSearchedFor = itemRegister.searchByItemDesc(userInput);
    }

    if (!found && itemSearchedFor != null) {
      System.out.println(itemSearchedFor);
      found = true;
    }

    if (!found) {
      System.out.println("Item not found");
    }

    halt();

  }

  /**
   * Adds an item to the list by constructing an {@link Item} object with
   * the {@link ItemBuilder} class, and using the {@link ItemRegister#addItem(Item)}
   * method to add the constructed item into the list.
   */
  private void addNewItem() {

    Item newItem = new ItemBuilder()
        .setItemNumber(getUserInput("Input item number", () -> {
          boolean itemNumberExists;
          boolean isNullString;
          String itemNumberInput;
          do {
            itemNumberExists = false;
            isNullString = false;
            itemNumberInput = scannerNextLine();
            if (itemNumberInput.equals("")) {
              System.out.print("Cant input empty string: ");
              isNullString = true;
              continue;
            }
            List<Item> itemArrayList = itemRegister.getCopyOfList();
            String finalItemNumberInput = itemNumberInput;
            itemNumberExists = itemArrayList
                    .stream()
                    .anyMatch(item -> item
                            .getItemNumber()
                            .equalsIgnoreCase(finalItemNumberInput));

            if (itemNumberExists) {
              System.out.print("Item number already exists, try again: ");
            }
          } while (itemNumberExists || isNullString);

          return itemNumberInput;
        }))

        .setDescription(getUserInput("Input item description", () -> {
          boolean isNullString;
          String itemDescInput;

          do {
            isNullString = false;
            itemDescInput = scannerNextLine();
            if (itemDescInput.equals("")) {
              isNullString = true;
              System.out.print("Cant input empty string: ");
            }
          } while (isNullString);

          return itemDescInput;
        }))

        .setBrandName(getUserInput("Input item brand", () -> {
          boolean isNullString;
          String itemBrandInput;

          do {
            isNullString = false;
            itemBrandInput = scannerNextLine();
            if (itemBrandInput.equals("")) {
              isNullString = true;
              System.out.print("Cant input empty string: ");
            }
          } while (isNullString);

          return itemBrandInput;
        }))

        .setPrice(Integer.parseInt(getUserInput("Input item price", intUserInput())))
        .setWarehouseStock(Integer.parseInt(getUserInput("Input item stock", intUserInput())))
        .setWeight(Double.parseDouble(getUserInput("Specify item weight", doubleUserInput(true))))
        .setLength(Double.parseDouble(getUserInput("Specify item length", doubleUserInput(true))))
        .setHeight(Double.parseDouble(getUserInput("Specify item height", doubleUserInput(true))))
        .setColor(Color.valueOf(
                getUserInput("Specify item color " + Arrays.toString(Color.values()), () -> {
                  boolean isColor;
                  String colorInput;
                  do {
                    isColor = false;
                    colorInput = scannerNextLine().toUpperCase();
                    try {
                      Color.valueOf(colorInput);
                      isColor = true;
                    } catch (IllegalArgumentException iae) {
                      System.out.print("Could not find color, try again: ");
                    }
                  } while (!isColor);


                  return colorInput;
                })))

        .setCategory(Category.valueOf(
                getUserInput("Input item category " + Arrays.toString(Category.values()), () -> {
                  boolean isCategory;
                  String categoryInput;
                  do {
                    isCategory = false;
                    categoryInput = scannerNextLine().toUpperCase();
                    try {
                      Category.valueOf(categoryInput);
                      isCategory = true;
                    } catch (IllegalArgumentException iae) {
                      System.out.print("Could not find category, try again: ");
                    }
                  } while (!isCategory);


                  return categoryInput;
                })))

        .build();


    itemRegister.addItem(newItem);

    System.out.println("Item successfully added");
    halt();
  }

  /**
   * Increases the stock of an {@link Item} in the register by utilizing
   * the {@link ItemRegister#increaseItemStock(Item, int)} method.
   */
  private void increaseItemStock() {
    Item selectedItem = itemSelection();

    int stockIncrease = Integer.parseInt(
            getUserInput("Input stock amount which should be added", () -> {
              boolean isGreaterThanMax;
              String numberInput;
              do {
                numberInput = intUserInput().input();
                isGreaterThanMax = false;

                int stringToInt = Integer.parseInt(numberInput);
                if (selectedItem.getWarehouseStock() + stringToInt < 0) {
                  isGreaterThanMax = true;
                  System.out.print("Stock amount exceeds maximum amount, try again: ");
                }
              } while (isGreaterThanMax);

              return numberInput;
            }));

    try {
      itemRegister.increaseItemStock(selectedItem, stockIncrease);
      System.out.printf("Item updated:\n %4d %s\n", itemRegister.getIndexOfItem(selectedItem)
              + 1, itemRegister.searchByItemNumber(selectedItem.getItemNumber()));
      halt();
    } catch (NoSuchElementException nee) {
      System.out.print("Item was not found in the item register");
    }

  }

  /**
   * Decreases the stock of an {@link Item} in the register by utilizing
   * the {@link ItemRegister#decreaseItemStock(Item, int)} method.
   */
  private void decreaseItemStock() {
    Item selectedItem = itemSelection();

    int stockDecrease = Integer.parseInt(
            getUserInput("Input stock amount which should be removed", () -> {
              boolean isGreaterThanStock;
              String numberInput;
              do {
                numberInput = intUserInput().input();
                isGreaterThanStock = false;

                int stringToInt = Integer.parseInt(numberInput);
                if (selectedItem.getWarehouseStock() < stringToInt) {
                  isGreaterThanStock = true;
                  System.out.print("Number inputted is greater than item stock, try again: ");
                }
              } while (isGreaterThanStock);

              return numberInput;
            }));

    try {
      itemRegister.decreaseItemStock(selectedItem, stockDecrease);
      System.out.printf("Item updated:\n %4d %s\n", itemRegister.getIndexOfItem(selectedItem)
              + 1, itemRegister.searchByItemNumber(selectedItem.getItemNumber()));
      halt();
    } catch (NoSuchElementException nee) {
      System.out.print("Item was not found in the item register");
    }

  }

  /**
   * Removes an {@link Item} from the register by utilizing
   * the {@link ItemRegister#removeItem(Item)} method.
   */
  private void removeItem() {
    Item selectedItem = itemSelection();

    if (itemRegister.removeItem(selectedItem)) {
      System.out.print("Item successfully removed\n");
      halt();
    }

  }

  /**
   * Changes the price of an {@link Item} in the register
   * by utilizing the {@link ItemRegister#changePriceOfItem(Item, int)} method.
   */
  private void changeItemPrice() {
    Item selectedItem = itemSelection();

    int newPrice = Integer.parseInt(getUserInput("Input new price for the item", intUserInput()));
    try {
      itemRegister.changePriceOfItem(selectedItem, newPrice);
      System.out.printf("Item updated:\n %4d %s\n", itemRegister.getIndexOfItem(selectedItem)
              + 1, itemRegister.searchByItemNumber(selectedItem.getItemNumber()));
      halt();
    } catch (NoSuchElementException nee) {
      System.out.println("Item was not found in the item register");
    }
  }

  /**
   * Changes the discount of an {@link Item} in the register
   * by utilizing the {@link ItemRegister#changeDiscountOfItem(Item, double)} method.
   */
  private void changeItemDiscount() {
    Item selectedItem = itemSelection();
    double newDiscount;

    do {
      newDiscount = Double.parseDouble(
              getUserInput("Input new discount for the item (0-100)", doubleUserInput(false)));
      if (newDiscount > 100) {
        System.out.println("Discount cannot be greater than 100");
      }

    } while (newDiscount > 100);

    try {
      itemRegister.changeDiscountOfItem(selectedItem, newDiscount);
      System.out.printf("Item updated:\n %4d %s\n", itemRegister.getIndexOfItem(selectedItem)
              + 1, itemRegister.searchByItemNumber(selectedItem.getItemNumber()));
      halt();
    } catch (IllegalArgumentException iae) {
      System.out.println("Something went wrong with inputted number");
    } catch (NoSuchElementException nee) {
      System.out.println("Item was not found in the item register");
    }
  }

  /**
   * Changes the description of an {@link Item} in the register
   * by utilizing the {@link ItemRegister#changeDescriptionOfItem(Item, String)} method.
   */
  private void changeItemDescription() {
    Item selectedItem = itemSelection();

    System.out.print("Input new item description:");
    String newDescription = scannerNextLine();
    try {
      itemRegister.changeDescriptionOfItem(selectedItem, newDescription);
      System.out.printf("Item updated:\n %4d %s\n", itemRegister.getIndexOfItem(selectedItem)
              + 1, itemRegister.searchByItemNumber(selectedItem.getItemNumber()));

      halt();
    } catch (NoSuchElementException nee) {
      System.out.println("Item was not found in the item register");
    }

  }

  /**
   * Sorts the item list in the register by the specified method by the user.
   * For example, if the user wishes to sort by item number, this method
   * would utilize the {@link ItemRegister#sortListByItemnumber(boolean)} method.
   */
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
      sortMenuSelection = Integer.parseInt(getUserInput("Choose sort option", intUserInput()));
      if (sortMenuSelection < 1 || sortMenuSelection > 6) {
        System.out.println("Input a valid number, try again");
      }
    } while (sortMenuSelection < 1 || sortMenuSelection > 6);

    boolean ascending = Boolean.parseBoolean(getUserInput("Ascending? (y/n)", () -> {
      String booleanStringInput = scannerNextLine().toLowerCase();
      if (booleanStringInput.equals("y") || booleanStringInput.equals("yes")) {
        return "True";
      } else {
        return "False";
      }
    }));

    switch (sortMenuSelection) {
      case 1 -> {
        itemRegister.sortListByItemnumber(ascending);
        System.out.println("List sorted successfully by item number");
      }
      case 2 -> {
        itemRegister.sortListByBrandname(ascending);
        System.out.println("List sorted successfully by brand name");
      }
      case 3 -> {
        itemRegister.sortListByPrice(ascending);
        System.out.println("List sorted successfully by price");
      }
      case 4 -> {
        itemRegister.sortListByWarehousestock(ascending);
        System.out.println("List sorted successfully by warehouse stock");
      }
      case 5 -> {
        itemRegister.sortListByColor(ascending);
        System.out.println("List sorted successfully by color");
      }
      case 6 -> {
        itemRegister.sortListByCategory(ascending);
        System.out.println("List sorted successfully by category");
      }
      default -> System.out.println("Something went wrong");
    }

    halt();

  }

  /**
   * Exits the current running application.
   */
  private void exit() {
    System.out.println("Thank you for using WMS");
    System.out.println("Exiting application...");
    scanner.close();
    System.exit(0);
  }


  /**
   * A standard method for receiving input from the user. Utilizes the
   * {@link UserInput} interface for functional programming.
   *
   * @param message     The message that will be displayed to the user
   *                    when prompted with the ability to input
   * @param userInput   {@link UserInput} function for receiving user
   *                    input. {@code () -> {}} can be used for easy
   *                    functional programming
   * @return            The string returned from the {@link UserInput#input()}
   *                    method which is specified in the parameter for {@code userInput}
   */
  private String getUserInput(String message, UserInput userInput) {
    System.out.print(message + ": ");
    return userInput.input();
  }

  /**
   * A simple method for pausing the program temporarily and
   * where the user input doesn't matter.
   */
  private void halt() {
    System.out.println("Press ENTER to continue");
    scanner.nextLine();
  }

  /**
   * A standard {@link UserInput} method for retrieving
   * an integer from the user and ensuring the number
   * isn't negative or anything other than a number.
   *
   * @return  A {@link UserInput} object which executes code
   *          for retrieving a valid integer
   */
  private UserInput intUserInput() {
    return () -> {
      boolean isNumber;
      boolean isNegative;
      String numberStringInput;

      do {
        isNumber = false;
        isNegative = false;
        numberStringInput = scannerNextLine();
        try {
          if (numberStringInput.equals("")) {
            throw new NullPointerException();
          }
          int stringToInt = Integer.parseInt(numberStringInput);
          isNumber = true;
          if (stringToInt < 0) {
            isNegative = true;
            System.out.print("Can't input negative numbers, try again: ");
          }
        } catch (NumberFormatException | NullPointerException e) {
          System.out.print("Input numbers, try again: ");
        }
      } while (!isNumber || isNegative);

      return numberStringInput;
    };
  }

  /**
   * A standard {@link UserInput} method for retrieving
   * a double from the user and ensuring the number
   * isn't negative or anything other than a number.
   *
   * @param zeroIsInclusive   {@code true} if the double value received from
   *                          user input should exclude the value {@code 0}.
   *                          {@code false} if the double value only should
   *                          be checked for being negative.
   * @return                  A {@link UserInput} object which executes code
   *                          for retrieving a valid double
   */
  private UserInput doubleUserInput(boolean zeroIsInclusive) {
    return () -> {
      boolean isNumber;
      boolean isNegative;
      String numberStringInput;

      do {
        isNumber = false;
        isNegative = false;
        numberStringInput = scannerNextLine();

        try {
          double stringToDouble = Double.parseDouble(numberStringInput);
          isNumber = true;
          if (!zeroIsInclusive && stringToDouble < 0) {
            isNegative = true;
            System.out.print("Can't input negative numbers, try again: ");
          } else if (zeroIsInclusive && stringToDouble <= 0) {
            isNegative = true;
            System.out.print("Can't input 0 or negative numbers, try again: ");
          }
        } catch (NumberFormatException | NullPointerException e) {
          System.out.print("Input numbers. try again: ");
        }
      } while (!isNumber || isNegative);

      return numberStringInput;
    };
  }

  /**
   * A method for selecting an item from the register list, and creating a deep-copy
   * of the item by utilizing the {@link ItemRegister#getItem(int)} method.
   *
   * @return A deep-copy of the selected {@link Item}
   */
  private Item itemSelection() {
    System.out.printf("%-5s | %-15s | %-22s | %-17s | %-6s | %-10s | %-10s | %-10s "
                    + "| %-8s | %-18s | %s\n",
            "index", "ITEM NUMBER", "BRAND NAME", "PRICE (DISCOUNT)", "STOCK", "WEIGHT",
            "LENGTH", "HEIGHT", "COLOR", "CATEGORY", "DESCRIPTION");
    for (int i = 0; i < itemRegister.size(); i++) {
      System.out.printf("%5d %s\n", i + 1, itemRegister.getItem(i));
    }

    int listIndex = Integer.parseInt(
            getUserInput("Input the index number of the item you wish to edit", () -> {
              boolean isListIndex;
              String indexInput;

              do {
                isListIndex = false;
                indexInput = scannerNextLine();

                try {
                  int stringToInt = Integer.parseInt(indexInput);
                  if (stringToInt > 0 && stringToInt <= itemRegister.size()) {
                    isListIndex = true;
                  } else {
                    System.out.print("Input a number in the index list, try again: ");
                  }
                } catch (NumberFormatException nfe) {
                  System.out.print("Input numbers, try again: ");
                }

              } while (!isListIndex);

              return indexInput;
            })) - 1;

    Item selectedItem = itemRegister.getItem(listIndex);
    System.out.printf("Selected item:\n%5d %s\n", listIndex + 1, selectedItem);

    return selectedItem;
  }

  /**
   * A standard method for retrieving the next line the user inputs by utilizing
   * the {@link Scanner#nextLine()} method. This method checks if the user inputted
   * "/BACK", and reverts to the main menu selection if "/BACK" is inputted
   *
   * @return  String which the user inputted
   */
  private String scannerNextLine() {
    String userInput = scanner.nextLine();
    if (userInput.equals("/BACK")) {
      start();
    }
    return userInput;

  }

  /**
   * A standard to-string method for displaying all the specified menu options.
   *
   * @return A string formatted with all the menu options specified
   */
  @Override
  public String toString() {
    return """
            -------------- MENU --------------
            1.  Print out items in List
            2.  Search for item
            3.  Add new item
            4.  Increase stock of an item
            5.  Decrease stock of an item
            6.  Remove an item
            7.  Change price of an item
            8.  Change discount of an item
            9.  Change description of an item
            10. Sort List
            15. EXIT
            
            Select option by typing the index number
            Type "/BACK" to return to this menu at any point
            Press any other key to exit program""";
  }
}
