package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Fields {
    Field field;
    Menu menu;
    BackButton backButton;
    private ButtonBackground contract;
    private ButtonBackground contract2;
    private ButtonBackground contract3;
    private int tier;
    private String key;
    private Texture contractTe = new Texture(Gdx.files.internal("plainButton.png"));

    private String price0 = "1000";
    private String price1 = "5600";
    private String price2 = "23400";
    private String price3 = "99000";

    public Fields(Field x) {
        field = x;
        menu = new Menu();
        backButton = new BackButton();
        contract = new ButtonBackground(7.3f,6f, contractTe);
        contract2 = new ButtonBackground(7.3f,5f, contractTe);
        contract3 = new ButtonBackground(7.3f,4f, contractTe);
    }

    public String getPrice() {
        if (tier==0) {
            return price0;
        } else if (tier==1) {
            return price1;
        } else if (tier==2) {
            return price2;
        } else if (tier==3){
            return price3;
        } else {
            return "";
        }
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
    public ButtonBackground getContractButton() {
        return contract;
    }
    public ButtonBackground getContractButton2() {
        return contract2;
    }
    public ButtonBackground getContractButton3() {
        return contract3;
    }
}
