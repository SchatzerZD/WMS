import java.util.Scanner;

public class Main{
  public static void main(String[] args) {

    ItemRegister itemRegister = new ItemRegister();
    itemRegister.fillListWithDefaultItems();

    Menu menu = new Menu(itemRegister);


    while(true){
      System.out.println(menu);
      menu.selectMenu(Integer.parseInt(menu.getUserInput("")));
    }

  }
}
