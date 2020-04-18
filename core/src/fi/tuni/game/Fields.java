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
    private String keyS;
    private Texture contractTe = new Texture(Gdx.files.internal("plainButton.png"));

    private Texture state0 = new Texture(Gdx.files.internal("textureField.png"));
    private Texture state1 = new Texture(Gdx.files.internal("cabbageField.png"));
    private Texture state2 = new Texture(Gdx.files.internal("onionField.png"));
    private Texture none = new Texture(Gdx.files.internal("plainField.png"));

    private int cont = -1;
    private long example = 1000;
    private boolean state;
    private long startedTime;

    public Fields(Field x, String k) {
        field = x;
        key = k;
        keyS = k+"S";
        menu = new Menu();
        backButton = new BackButton();
        contract = new ButtonBackground(7.3f,6f, contractTe);
        contract2 = new ButtonBackground(7.3f,5f, contractTe);
        contract3 = new ButtonBackground(7.3f,4f, contractTe);

        field.setFieldTexture(setTextureByState());
    }

    public void startProduction(int which) {
        // 0 = vilja, 1 = kaali, 2 = sipuli
        cont = which;
        startedTime = example + MemoryReader.readCurrentTimestamp();
        state = true;
        field.setFieldTexture(setTextureByState());
        MemoryWriter.writeToiletTime(keyS, startedTime);
    }

    public void checkProduction(Balance cash) {
        if (startedTime < MemoryReader.readCurrentTimestamp()) {
            state = false;
            if (cont==0) {
                cash.addValue(1500);
            } else if (cont==1) {
                cash.addValue(1500);
            } else if (cont==2) {
                cash.addValue(1500);
            }
            cont = -1;
            field.setFieldTexture(setTextureByState());
        }
    }

    private Texture setTextureByState() {
        if (cont==0) {
            return state0;
        } else if (cont==1) {
            return state1;
        } else if (cont==2){
            return state2;
        } else {
            return none;
        }
    }

    public boolean getState() {
        return state;
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
