package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Fields {
    private Field field;
    private Menu menu;
    private BackButton backButton;
    private String key;
    private String keyS;

    private ButtonBackground contract;
    private ButtonBackground contract2;
    private ButtonBackground contract3;
    private Texture contractPee = new Texture(Gdx.files.internal("peeButton.png"));
    private Texture contractPoo = new Texture(Gdx.files.internal("pooButton.png"));

    private ButtonBackground contractX;
    private ButtonBackground contract2X;
    private ButtonBackground contract3X;
    private Texture contractPeeX = new Texture(Gdx.files.internal("peeLocked.png"));
    private Texture contractPooX = new Texture(Gdx.files.internal("pooLocked.png"));

    private Texture cont0 = new Texture(Gdx.files.internal("textureField.png"));
    private Texture cont1 = new Texture(Gdx.files.internal("cabbageField.png"));
    private Texture cont2 = new Texture(Gdx.files.internal("onionField.png"));
    private Texture none = new Texture(Gdx.files.internal("plainField.png"));

    private String price0 = "1500";
    private String price1 = "15000";
    private String price2 = "35000";
    private int reward1 = 2500;
    private int reward2 = 12000;
    private int reward3 = 24500;
    private long time1 = 15000;
    private long time2 = 30000;
    private long time3 = 45000;
    private int cont = 0;
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

        contractX = new ButtonBackground(7.3f,6f, contractPeeX);
        contract2X = new ButtonBackground(7.3f,5f, contractPeeX);
        contract3X = new ButtonBackground(7.3f,4f, contractPooX);

        getCont();
        getStartedTime();
        field.setFieldTexture(setTextureByCont());
    }

    public void startProduction(int which) {
        // 1 = vilja, 2 = kaali, 3 = sipuli
        setCont(which);
        if (cont==1) {
            startedTime = time1 + MemoryReader.readCurrentTimestamp();
        } else if (cont==2) {
            startedTime = time2 + MemoryReader.readCurrentTimestamp();
        } else if (cont==3) {
            startedTime = time3 + MemoryReader.readCurrentTimestamp();
        }
        MemoryWriter.writeTimer(keyS, startedTime);
        field.setFieldTexture(setTextureByCont());
    }

    private void getStartedTime() {
        startedTime = MemoryReader.readTimer(keyS);
    }

    public void checkProduction(Balance cash) {
        if (startedTime < MemoryReader.readCurrentTimestamp()) {
            if (cont==1) {
                RequestSound.playBalanceSound();
                cash.addValue(reward1);
            } else if (cont==2) {
                RequestSound.playBalanceSound();
                cash.addValue(reward2);
            } else if (cont==3) {
                RequestSound.playBalanceSound();
                cash.addValue(reward3);
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
        } else if (x==2) {
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
    public ButtonBackground getContractX() {
        return contractX;
    }

    public ButtonBackground getContract2X() {
        return contract2X;
    }

    public ButtonBackground getContract3X() {
        return contract3X;
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
