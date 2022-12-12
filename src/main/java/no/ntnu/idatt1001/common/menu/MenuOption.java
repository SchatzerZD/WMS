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

  PRINT_OUT_ITEMS(1),
  SEARCH_FOR_ITEM(2),
  ADD_NEW_ITEM(3),
  INCREASE_ITEM_STOCK(4),
  DECREASE_ITEM_STOCK(5),
  REMOVE_ITEM(6),
  CHANGE_ITEM_PRICE(7),
  CHANGE_ITEM_DISCOUNT(8),
  CHANGE_ITEM_DESCRIPTION(9),
  SORT_LIST(10),
  EXIT(15);

  private final int menuIndex;

  MenuOption(int menuIndex) {
    this.menuIndex = menuIndex;
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
