package budget;

import budget.item.Item;
import budget.item.ItemCategory;
import budget.item.ItemList;
import budget.menu.*;
import com.google.gson.annotations.Expose;

import java.util.Scanner;

public class BudgetManager implements PurchaseFileManager {

    private final Scanner scanner;
    private final Menu mainMenu;
    private final Menu addTypePurchaseMenu;
    private final Menu typePurchaseMenu;
    private final Menu showPurchasesMenu;
    private final Menu menuSort;
    @Expose
    private ItemList[] purchases;
    @Expose
    private double balance;

    public BudgetManager() {
        scanner = new Scanner(System.in);
        balance = 0.0d;
        mainMenu = new MainMenu(this::handleMainMenu);
        addTypePurchaseMenu = new TypePurchaseMenu(this::handleAddPurchaseMenu);
        typePurchaseMenu = new TypePurchaseMenu(this::handleMenuSort);
        showPurchasesMenu = new ShowPurchasesMenu(this::handleShowPurchaseMenu);
        menuSort = new MenuSort(this::handleMenuSort);
        initPurchaseLists();
    }

    private void initPurchaseLists() {
        purchases = new ItemList[ItemCategory.values().length];
        for (int i = 0; i < purchases.length; i++)
            purchases[i] = new ItemList(ItemCategory.values()[i]);
    }

    public void run() {
        mainMenu.getListener().handleInput();
    }

    private void handleMainMenu() {
        mainMenu.show();
        String option = scanner.nextLine().trim();
        System.out.println();
        switch (option) {
            case "1":
                addIncome();
                break;
            case "2":
                addTypePurchaseMenu.getListener().handleInput();
                break;
            case "3":
                showPurchasesMenu.getListener().handleInput();
                break;
            case "4":
                showBalance();
                break;
            case "5":
                write(this);
                break;
            case "6":
                handleLoadedData();
                break;
            case "7":
                menuSort.getListener().handleInput();
                break;
            case "0":
                System.out.println("Bye!");
                return;
            default:
                System.out.println("Unknown operation.");
        }
        mainMenu.getListener().handleInput();
    }

    private void handleLoadedData() {
        BudgetManager loaded = read();
        if (loaded != null) {
            balance = loaded.balance;
            purchases = loaded.purchases;
            for (ItemList list : purchases)
                list.recalculateSum();
        }
    }

    private void handleAddPurchaseMenu() {
        addTypePurchaseMenu.show();
        String option = scanner.nextLine().trim();
        System.out.println();
        ItemCategory category = ItemCategory.get(option);
        if (category != null)
            addPurchase(category);
        else if (option.equals("5"))
            return;
        else System.out.println("Unknown operation.");
        addTypePurchaseMenu.getListener().handleInput();
    }

    private void handleShowPurchaseMenu() {
        if (!isPurchaseEmpty()) {
            showPurchasesMenu.show();
            String option = scanner.nextLine().trim();
            System.out.println();
            ItemCategory category = ItemCategory.get(option);
            if (category != null) {
                purchases[category.ordinal()].print();
                purchases[category.ordinal()].printSum();
            } else if (option.equals("5"))
                printAllPurchases();
            else if (option.equals("6"))
                return;
            else System.out.println("Unknown operation.");
            showPurchasesMenu.getListener().handleInput();
        } else System.out.println("Purchase list is empty!");
    }

    private void handleMenuSort() {

        menuSort.show();
        String option = scanner.nextLine().trim();
        System.out.println();
        switch (option) {
            case "1":
                System.out.println("All:");
                double sum = 0;
                for (ItemList list : purchases) {
//                Comparator.comparing(list::getValue).reversed();
                    list.printAll();
                    sum += list.getSum();
                }
                System.out.println("Total sum: $" + sum);
                break;
            case "2":
                System.out.println("Types:");
                double sumByType = 0;
                for (ItemList list : purchases) {
                    list.printByType();
                    sumByType += list.getSum();
                }
                System.out.println("Total sum: $" + sumByType);
                break;
            case "3":
                typePurchaseMenu.show();
                String param = scanner.nextLine().trim();
                ItemCategory category = ItemCategory.get(param);
                if (category != null) {
                    System.out.println(category + ":");
                    purchases[category.ordinal()].print();
                    purchases[category.ordinal()].printSum();
                }
                typePurchaseMenu.getListener().handleInput();
                break;
            case "4":
                break;
        }
    }

    private boolean isPurchaseEmpty() {
        for (ItemList list : purchases)
            if (list.getList().size() != 0)
                return false;
        return true;
    }

    private void printAllPurchases() {
        double sum = 0;
        for (ItemList list : purchases) {
            if (list.getList().size() > 0) {
                list.print();
                sum += list.getSum();
            }
        }
        System.out.println("Total sum: $" + sum);
    }

    private void addPurchase(ItemCategory category) {
        System.out.println("Enter purchase name:");
        String name = scanner.nextLine().replaceAll("\\s+", " ");
        System.out.println("Enter its price:");
        try {
            double value = parseAmount(scanner.nextLine());
            balance -= value;
            purchases[category.ordinal()].add(new Item(name, value));
            System.out.println("Purchase was added!");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private void addIncome() {
        System.out.println("Enter income:");
        try {
            String valueString = scanner.nextLine();
            if (!valueString.contains("-")) {
                double value = parseAmount(valueString);
                balance += value;
                System.out.println("Income was added!");
            } else System.out.println("Income cannot be negative!");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private double parseAmount(String value) throws NullPointerException, NumberFormatException {
        return Double.parseDouble(value.trim().replace(',', '.').replaceAll("[-+]", ""));
    }

    private void showBalance() {
        System.out.println("Balance: $" + String.format("%.2f", balance));
    }
}