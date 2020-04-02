package fi.tuni.game;

public class Menut {
    Toilet toilet;
    Menu menu;
    BackButton backButton;

    public Menut(Toilet x) {
        toilet = x;
        menu = new Menu();
        backButton = new BackButton();
    }

    public Toilet getToilet() {
        return toilet;
    }
    public Menu getMenu() {
        return menu;
    }
    public BackButton getBackButton() {
        return backButton;
    }
}
