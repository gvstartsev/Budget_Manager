package budget.item;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ItemList {

    @Expose
    private final ItemCategory category;
    @Expose
    private final List<Item> list;
    private double sum;

    public ItemList(ItemCategory category) {
        this.category = category;
        this.list = new ArrayList<>();
    }

    public List<Item> getList() {
        return list;
    }

    public double getSum() {
        return sum;
    }

    public void add(Item item) {
        list.add(item);
        sum += item.getValue();
    }

    public void recalculateSum() {
        sum = 0;
        for(Item i : list) {
            sum += i.getValue();
        }
    }

    public void print() {
        System.out.println(category + ":");
        if (list.size() > 0)
            for (Item item : list)
                item.print();
        else System.out.println("Purchase list is empty!");
    }

    public void printAll() {
        for (Item item : list) {
            item.print();
        }
    }

    public void printByType() {
        System.out.println(category + " - $" + String.format(Locale.US, "%.2f", getSum()));
    }

    public void printSum() {
        System.out.println("Total sum: $" + String.format(Locale.US, "%.2f", sum));
    }
}