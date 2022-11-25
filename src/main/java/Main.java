
public class Main{
  public static void main(String[] args) {

    Item item = new Item("yo","yo","yo",1,1,1,1,1,Color.BLACK,Category.DOORS);
    System.out.println(item);
    ItemRegister itemRegister = new ItemRegister();
    itemRegister.fillListWithDefaultItems();

    Menu menu = new Menu(itemRegister);

    menu.start();



  }
}
