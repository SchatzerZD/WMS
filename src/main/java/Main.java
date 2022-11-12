import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    ItemRegister itemRegister = new ItemRegister();

    itemRegister.addItem(new Item("A1205B","Large Christmas Window","SULOLI",
            130,2,0.45,15.8,27.5,Color.WHITE,Category.WINDOWS));

    itemRegister.addItem(new Item("M5788B","Cherry Lumber","Barrington Hardwoods",
            120,8,2.43,30.48,3.2,Color.BROWN,Category.LUMBER));

    itemRegister.addItem(new Item("C1007B","Magnetic Thermal Insulated","Mpmedo",
            298,31,52.4,57.3,210.47,Color.GRAY,Category.DOORS));

    itemRegister.addItem(new Item("A1008B","Basement Hopper Window","Park products",
            977,76,8.6,96.3,45.2,Color.GRAY,Category.WINDOWS));

    itemRegister.addItem(new Item("F4020G","Self Adhesive Vinyl Floor Tile","Achim",
            173,211,4.08,30.48,30.48,Color.BLACK,Category.FLOOR_LAMINATES));


    System.out.println(itemRegister + "\n\n\n");

    Scanner scanner = new Scanner(System.in);
    System.out.println("Search for item by number or description");
    String userInput = scanner.nextLine();

    if(itemRegister.searchByItemNumber(userInput) != null) System.out.println(itemRegister.searchByItemNumber(userInput));
    else if(itemRegister.searchByItemDesc(userInput) != null) System.out.println(itemRegister.searchByItemDesc(userInput));
    else System.out.println("Item not found");

  }

}
