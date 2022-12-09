package no.ntnu.idatt1001.common;

import java.util.Arrays;
import java.util.NoSuchElementException;

enum MenuOption {

  PRINT_OUT_ITEMS(1),
  SEARCH_FOR_ITEM(2),
  ADD_NEW_ITEM(3),
  INCREASE_ITEM_STOCK(4),
  DECREASE_ITEM_STOCK(5),
  REMOVE_ITEM(6),
  CHANGE_ITEM_PRICE(7),
  CHANGE_ITEM_DISCOUNT(8);

  private final int menuIndex;

  MenuOption(int menuIndex) {
    this.menuIndex = menuIndex;
  }

  public int getMenuIndex() {
    return menuIndex;
  }

  public static MenuOption getMenuOption(int menuIndex) {
    return Arrays.stream(MenuOption.values())
            .filter(mo -> mo.getMenuIndex() == menuIndex)
            .findFirst()
            .orElseThrow(NoSuchElementException::new);

  }

}
