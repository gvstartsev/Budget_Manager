package budget.menu;

public class TypePurchaseMenu extends Menu {

    public TypePurchaseMenu(MenuListener listener) {
        super(listener);
    }

    @Override
    public void show() {
        System.out.println("\nChoose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) Back");
    }
}