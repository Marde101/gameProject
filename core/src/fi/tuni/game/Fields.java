package fi.tuni.game;

public class Fields {
    Field field;
    Menu menu;
    BackButton backButton;

    public Fields(Field x) {
        field = x;
        menu = new Menu();
        backButton = new BackButton();
    }

    public Field getField() {
        return field;
    }
    public Menu getMenu() {
        return menu;
    }
    public BackButton getBackButton() {
        return backButton;
    }
}
