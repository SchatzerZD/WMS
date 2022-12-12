package no.ntnu.idatt1001;

import no.ntnu.idatt1001.common.ItemRegister;
import no.ntnu.idatt1001.common.menu.Menu;
import no.ntnu.idatt1001.util.item.Item;

/**
 * The main class which has the {@link Main#main(String[])} method
 * which gets executed at the start of the program. The program
 * represents a Warehouse Management System (WMS) that manages
 * a list of items of the {@link Item} class through a register,
 * which utilizes the {@link ItemRegister} class. The main method
 * creates an instance of the {@link Menu} class for easy navigation
 * through the item register.
 *
 * @author 10124
 * @version 1.0.0
 */
public class Main {
  /**
   * The main method which gets executed at the start of the program.
   * This method creates an instance of an {@link ItemRegister} class
   * for usage in a {@link Menu} object.
   *
   * @param args  Arguments passed in the command line when executing this java class
   */
  public static void main(String[] args) {

    ItemRegister itemRegister = new ItemRegister();
    itemRegister.fillListWithDefaultItems();

    Menu menu = new Menu(itemRegister);


    menu.start();
  }
}
