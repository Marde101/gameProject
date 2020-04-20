package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Fields {
    private Field field;
    private Menu menu;
    private BackButton backButton;
    private ButtonBackground contract;
    private ButtonBackground contract2;
    private ButtonBackground contract3;
    private String key;
    private String keyS;
    private Texture contractPee = new Texture(Gdx.files.internal("peeButton.png"));
    private Texture contractPoo = new Texture(Gdx.files.internal("pooButton.png"));
    private String price0 = "1000";
    private String price1 = "5600";
    private String price2 = "8000";
    private Texture cont0 = new Texture(Gdx.files.internal("textureField.png"));
    private Texture cont1 = new Texture(Gdx.files.internal("cabbageField.png"));
    private Texture cont2 = new Texture(Gdx.files.internal("onionField.png"));
    private Texture none = new Texture(Gdx.files.internal("plainField.png"));

    private int cont = 0;
    private long example = 20000;
    private boolean state;
    private long startedTime;

    public Fields(Field x, String k) {
        field = x;
        key = k;
        keyS = k+"S";
        menu = new Menu();
        backButton = new BackButton();
        contract = new ButtonBackground(7.3f,6f, contractPee);
        contract2 = new ButtonBackground(7.3f,5f, contractPee);
        contract3 = new ButtonBackground(7.3f,4f, contractPoo);
        getCont();
        getStartedTime();
        field.setFieldTexture(setTextureByCont());
    }

    public void startProduction(int which) {
        // 0 = vilja, 1 = kaali, 2 = sipuli
        setCont(which);
        startedTime = example + MemoryReader.readCurrentTimestamp();
        MemoryWriter.writeTimer(keyS, startedTime);
        field.setFieldTexture(setTextureByCont());
    }

    private void getStartedTime() {
        startedTime = MemoryReader.readTimer(keyS);
    }

    public void checkProduction(Balance cash) {
        if (startedTime < MemoryReader.readCurrentTimestamp()) {
            if (cont==1) {
                cash.addValue(1500);
            } else if (cont==2) {
                cash.addValue(2500);
            } else if (cont==3) {
                cash.addValue(10000);
            }
            setCont(0);
            field.setFieldTexture(setTextureByCont());
        } else {
            state = true;
        }
    }

    public String getTimeLeftString() {
        long timeLeft = (startedTime - MemoryReader.readCurrentTimestamp())/1000;
        String time = timeLeft+"s";
        return time;
    }

    private Texture setTextureByCont() {
        state = true;
        if (cont==1) {
            return cont0;
        } else if (cont==2) {
            return cont1;
        } else if (cont==3){
            return cont2;
        } else {
            setCont(0);
            state = false;
            return none;
        }
    }

    public String getPrice(int x) {
        if (x==1) {
            return price0;
        } else if (x==1) {
            return price1;
        } else {
            return price2;
        }
    }

    public int getCont() {
        MemoryReader.readField(this);
        return cont;
    }

    public void setCont(int cunt) {
        cont = cunt;
        MemoryWriter.writeField(key, cont);
    }

    public String getKey() {
        return this.key;
    }

    public boolean getState() {
        if (cont != 0) {
            state = true;
        }
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
