package no.ntnu.idatt1001;

import no.ntnu.idatt1001.common.ItemRegister;
import no.ntnu.idatt1001.common.Menu;

public class Main{
  public static void main(String[] args) {

    ItemRegister itemRegister = new ItemRegister();
    itemRegister.fillListWithDefaultItems();

    Menu menu = new Menu(itemRegister);

    menu.start();



  }
}
