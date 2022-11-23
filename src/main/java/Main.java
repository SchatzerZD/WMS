
public class Main{
  public static void main(String[] args) {
    ItemRegister itemRegister = new ItemRegister();
    itemRegister.fillListWithDefaultItems();

    Menu menu = new Menu(itemRegister);

    menu.start();



  }
}
