package no.ntnu.idatt1001.common.menu;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Enum class representing the options available
 * in the menu for the user interface. Each menu
 * option has a menu index specified on the declaration
 * of the menu option. Each menu index should be unique.
 *
 * @author 10124
 * @version 1.0.0
 */
enum MenuOption {

  PRINT_OUT_ITEMS(1, "Print out items in List"),
  SEARCH_FOR_ITEM(2, "Search for item"),
  GET_ALL_ITEMS_BY_CATEGORY(3, "Get item list by category"),
  ADD_NEW_ITEM(4, "Add new item"),
  INCREASE_ITEM_STOCK(5, "Increase stock of an item"),
  DECREASE_ITEM_STOCK(6, "Decrease stock of an item"),
  REMOVE_ITEM(7, "Remove an item"),
  CHANGE_ITEM_PRICE(8, "Change price of an item"),
  CHANGE_ITEM_DISCOUNT(9, "Change discount of an item"),
  CHANGE_ITEM_DESCRIPTION(10, "Change description of an item"),
  SORT_LIST(11, "Sort List"),
  EXIT(15, "EXIT");

  private final int menuIndex;
  private final String optionDescription;

  /**
   * An enum constructor for the {@link MenuOption} class.
   *
   * @param menuIndex           The index of the menu option, should be
   *                            unique
   * @param optionDescription   The description of the menu option
   */
  MenuOption(int menuIndex, String optionDescription) {
    this.menuIndex = menuIndex;
    this.optionDescription = optionDescription;
  }

  /**
   * Gets the menu index of this menu option.
   *
   * @return The {@code menuIndex} of this {@link MenuOption}
   */
  int getMenuIndex() {
    return menuIndex;
  }

  /**
   * Gets the menu option description of this menu option.
   *
   * @return The {@code optionDescription} of this {@link MenuOption}
   */
  String getOptionDescription() {
    return optionDescription;
  }

  /**
   * Gets the {@link MenuOption} of the specified index. This method
   * utilizes the {@link java.util.stream.Stream} class for iterating
   * through all the menu option values and returning the first match.
   * As long as all menu options has unique {@code menuIndex} values,
   * this method works as intended.
   *
   * @param menuIndex The index of the menu option which should be returned
   * @return          The menu option with the specified {@code menuIndex}
   */
  static MenuOption getMenuOption(int menuIndex) {
    return Arrays.stream(MenuOption.values())
            .filter(mo -> mo.getMenuIndex() == menuIndex)
            .findFirst()
            .orElseThrow(NoSuchElementException::new);

  }

}
