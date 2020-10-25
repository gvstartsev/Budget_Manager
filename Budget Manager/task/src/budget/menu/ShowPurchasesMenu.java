package budget.menu;

public class ShowPurchasesMenu extends Menu {

    public ShowPurchasesMenu(MenuListener listener) {
        super(listener);
    }

    @Override
    public void show() {
        System.out.println("\nChoose the type of purchases\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) All\n" +
                "6) Back");
    }
}