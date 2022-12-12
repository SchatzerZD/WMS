package no.ntnu.idatt1001.common.menu;


import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import no.ntnu.idatt1001.common.ItemRegister;
import no.ntnu.idatt1001.common.UserInput;
import no.ntnu.idatt1001.util.Category;
import no.ntnu.idatt1001.util.Color;
import no.ntnu.idatt1001.util.IllegalNumberException;
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
  private boolean running;

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
    running = true;
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
    if(!running){
      System.out.println("Something went wrong with the initialization of this object");
    }

    while (running) {
      if (itemRegister == null) {
        System.out.println("Item register is null\nProgram closing...");
        break;
      }

      System.out.println(this);

      try {
        selectMenu(MenuOption.getMenuOption(
                Integer.parseInt(getUserInput("Input", scanner::nextLine))));
      } catch (NoSuchElementException nse) {
        System.out.println("\n\n\u001B[31mMenu option not found, try again\u001B[0m");
      } catch (NumberFormatException nfe) {
        System.out.println("\n\n\u001B[31mPlease input a number\u001B[0m");
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
      default -> System.out.println("\u001B[31m"
              + "Something went wrong with the menu selection\u001B[0m");
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

    Item itemFoundByItemNumber = itemRegister.searchByItemNumber(userInput);
    List<Item> itemsFoundByDescription = itemRegister.searchByItemDesc(userInput);

    boolean isItemNumber = false;
    boolean isDescription = false;

    if (itemFoundByItemNumber != null) {
      isItemNumber = true;
    }

    if (itemsFoundByDescription != null) {
      isDescription = true;
    }

    if (isItemNumber) {
      System.out.println(itemFoundByItemNumber);
    }

    if (isDescription) {
      itemsFoundByDescription.forEach(item -> {
        if (!item.getItemNumber().toLowerCase().trim().equals(userInput)) {
          System.out.println(item);
        }
      });
    }

    if (!isItemNumber && !isDescription) {
      System.out.println("\u001B[31mItem not found\u001B[0m");
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
              System.out.print("\u001B[31mCant input empty string:\u001B[0m");
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
              System.out.print("\u001B[31mItem number already exists, try again:\u001B[0m");
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
              System.out.print("\u001B[31mCant input empty string:\u001B[0m");
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
              System.out.print("\u001B[31mCant input empty string:\u001B[0m");
            }
          } while (isNullString);

          return itemBrandInput;
        }))

        .setPrice(Integer.parseInt(getUserInput("Input item price", intUserInput())))
        .setWarehouseStock(Integer.parseInt(getUserInput("Input item stock", intUserInput())))
        .setWeight(Double.parseDouble(getUserInput("Specify item weight (kg)", doubleUserInput(true))))
        .setLength(Double.parseDouble(getUserInput("Specify item length (m)", doubleUserInput(true))))
        .setHeight(Double.parseDouble(getUserInput("Specify item height (m)", doubleUserInput(true))))
        .setWidth(Double.parseDouble(getUserInput("Specify item width (m)", doubleUserInput(true))))
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
                      System.out.print("\u001B[31mCould not find color, try again:\u001B[0m");
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
                      System.out.print("\u001B[31mCould not find category, try again:\u001B[0m");
                    }
                  } while (!isCategory);


                  return categoryInput;
                })))

        .build();


    itemRegister.addItem(newItem);

    System.out.println("\u001B[32mItem successfully added\u001B[0m");
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
                  System.out.print("\u001B[31m"
                          + "Stock amount exceeds maximum amount, try again:\u001B[0m");
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
      System.out.print("\u001B[31mItem was not found in the item register\u001B[0m");
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
                  System.out.print("\u001B[31m"
                          + "Number inputted is greater than item stock, try again:\u001B[0m");
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
      System.out.print("\u001B[31mItem was not found in the item register\u001B[0m");
    }

  }

  /**
   * Removes an {@link Item} from the register by utilizing
   * the {@link ItemRegister#removeItem(Item)} method.
   */
  private void removeItem() {
    Item selectedItem = itemSelection();
    boolean confirmation = Boolean.parseBoolean(getUserInput(
            "Are you sure you want to delete this item? (y/n)", () -> {
        String booleanStringInput = scannerNextLine().toLowerCase();
        if (booleanStringInput.equals("y") || booleanStringInput.equals("yes")) {
          return "True";
        } else {
          return "False";
        }
      }));

    if (confirmation && itemRegister.removeItem(selectedItem)) {
      System.out.print("\u001B[32mItem successfully removed\u001B[0m\n");
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
      System.out.println("\u001B[31mItem was not found in the item register\u001B[0m");
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
        System.out.println("\u001B[31mDiscount cannot be greater than 100\u001B[0m");
      }

    } while (newDiscount > 100);

    try {
      itemRegister.changeDiscountOfItem(selectedItem, newDiscount);
      System.out.printf("Item updated:\n %4d %s\n", itemRegister.getIndexOfItem(selectedItem)
              + 1, itemRegister.searchByItemNumber(selectedItem.getItemNumber()));
      halt();
    } catch (IllegalArgumentException iae) {
      System.out.println("\u001B[31mSomething went wrong with inputted number\u001B[0m");
    } catch (NoSuchElementException nee) {
      System.out.println("\u001B[31mItem was not found in the item register\u001B[0m");
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
      System.out.println("\u001B[31mItem was not found in the item register\u001B[0m");
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
        System.out.println("\u001B[31mInput a valid number, try again\u001B[0m");
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
        System.out.println("\u001B[32mList sorted successfully by item number\u001B[0m");
      }
      case 2 -> {
        itemRegister.sortListByBrandname(ascending);
        System.out.println("\u001B[32mList sorted successfully by brand name\u001B[0m");
      }
      case 3 -> {
        itemRegister.sortListByPrice(ascending);
        System.out.println("\u001B[32mList sorted successfully by price\u001B[0m");
      }
      case 4 -> {
        itemRegister.sortListByWarehousestock(ascending);
        System.out.println("\u001B[32mList sorted successfully by warehouse stock\u001B[0m");
      }
      case 5 -> {
        itemRegister.sortListByColor(ascending);
        System.out.println("\u001B[32mList sorted successfully by color\u001B[0m");
      }
      case 6 -> {
        itemRegister.sortListByCategory(ascending);
        System.out.println("\u001B[32mList sorted successfully by category\u001B[0m");
      }
      default -> System.out.println("\u001B[31mSomething went wrong\u001B[0m");
    }

    halt();

  }

  /**
   * Exits the current running application.
   */
  private void exit() {
    System.out.println("Thank you for using WMS");
    System.out.println("Exiting application...");
    running = false;
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
    System.out.println("\u001B[32mPress ENTER to continue\u001B[0m");
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
            System.out.print("\u001B[31mCan't input negative numbers, try again:\u001B[0m");
          }
        } catch (NumberFormatException | NullPointerException e) {
          System.out.print("\u001B[31mInput numbers, try again:\u001B[0m");
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
            System.out.print("\u001B[31mCan't input negative numbers, try again:\u001B[0m");
          } else if (zeroIsInclusive && stringToDouble <= 0) {
            isNegative = true;
            System.out.print("\u001B[31mCan't input 0 or negative numbers, try again:\u001B[0m");
          }
        } catch (NumberFormatException | NullPointerException e) {
          System.out.print("\u001B[31mInput numbers. try again:\u001B[0m");
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
    System.out.printf("%-5s | %-15s | %-22s | %-17s | %-6s | %-12s | %-11s | %-11s "
                    + "| %-11s | %-8s | %-18s | %s\n",
            "index", "ITEM NUMBER", "BRAND NAME", "PRICE (DISCOUNT)", "STOCK", "WEIGHT(kg)",
            "LENGTH(m)", "HEIGHT(m)", "WIDTH(m)", "COLOR", "CATEGORY", "DESCRIPTION");
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
                    System.out.print("\u001B[31m"
                            + "Input a number in the index list, try again:\u001B[0m");
                  }
                } catch (NumberFormatException nfe) {
                  System.out.print("\u001B[31mInput numbers, try again:\u001B[0m");
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
    if (userInput.equalsIgnoreCase("/back")) {
      scanner.reset();
      start();
    }
    return userInput;
  }

  /**
   * A standard to-string method for displaying all the specified menu options.
   * Builds the string by using the {@link MenuOption} class. Any new options
   * added to this class will get added.
   *
   * @return A string formatted with all the menu options specified
   */
  @Override
  public String toString() {

    StringBuilder returnString = new StringBuilder("\u001B[36m"
           + "-------------- MENU --------------\u001B[0m\n");

    for (int i = 0; i < MenuOption.values().length; i++) {
      returnString.append(MenuOption.values()[i].getMenuIndex()).append(".\t")
              .append(MenuOption.values()[i].getOptionDescription())
              .append("\n");
    }

    returnString.append("\n\u001B[36mSelect option by typing the index number\u001B[0m\n");
    returnString.append("\u001B[36mType \u001B[35m\"/BACK\"\u001B[36m "
            + "to return to this menu at any point\u001B[0m\n");

    return returnString.toString();

  }
}
