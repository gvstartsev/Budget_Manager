package budget;

public class Purchase {
    private String name;
    private double price;
    private TypePurchase typePurchase;

    public Purchase(String name, double price, TypePurchase typePurchase) {
        this.name = name;
        this.price = price;
        this.typePurchase = typePurchase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TypePurchase getTypePurchase() {
        return typePurchase;
    }

    public void setTypePurchase(TypePurchase typePurchase) {
        this.typePurchase = typePurchase;
    }
}
