package budget.item;

public enum ItemCategory {

    FOOD("1"), CLOTHES("2"), ENTERTAINMENT("3"), OTHER("4");

    final String value;

    ItemCategory(String value) {
        this.value = value;
    }

    public static ItemCategory get(String value) {
        for (ItemCategory i : values())
            if (i.value.equals(value))
                return i;
        return null;
    }

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}