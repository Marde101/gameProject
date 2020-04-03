package fi.tuni.game;

public class Toilets {
    Toilet toilet;
    Menu menu;
    BackButton backButton;

    public Toilets(Toilet x) {
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
