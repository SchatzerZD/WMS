package no.ntnu.idatt1001;

import no.ntnu.idatt1001.common.lib.ItemRegister;
import no.ntnu.idatt1001.common.Menu;
import no.ntnu.idatt1001.util.Category;
import no.ntnu.idatt1001.util.Color;
import no.ntnu.idatt1001.util.item.Item;
import no.ntnu.idatt1001.util.item.ItemBuilder;

public class Main{
  public static void main(String[] args) {

    ItemRegister itemRegister = new ItemRegister();
    itemRegister.fillListWithDefaultItems();

    Item itemBuilder = new ItemBuilder()
            .setItemNumber("yo")
            .setBrandName("yo")
            .setWeight(1)
            .setHeight(1)
            .setLength(1)
            .setColor(Color.BLACK)
            .setCategory(Category.WINDOWS)
            .build();

    Menu menu = new Menu(itemRegister);

    menu.start();



  }
}
