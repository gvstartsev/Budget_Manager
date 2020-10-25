package budget.menu;

public abstract class Menu {

    private final MenuListener listener;

    protected Menu(MenuListener listener) {
        this.listener = listener;
    }

    public MenuListener getListener() {
        return listener;
    }

    public abstract void show();
}