package fi.tuni.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;



public class Toilets {
    private Toilet toilet;
    private Menu menu;
    private BackButton backButton;
    private ButtonBackground contract;
    private ButtonBackground contract2;
    private ButtonBackground upgrade;
    private int tier;
    private String key;
    private String keyS;
    private String keyC;
    private Texture contractTe = new Texture(Gdx.files.internal("plainButton.png"));
    private Texture upgradeTe = new Texture(Gdx.files.internal("euroNappi.png"));

    private String price0 = "1000";
    private String price1 = "15600";
    private String price2 = "64400";
    private String price3 = "609000";
    private String price4 = "2500000";
    private Texture tier0 = new Texture(Gdx.files.internal("puuHuusSilu.png"));
    private Texture tier1 = new Texture(Gdx.files.internal("sinihus.png"));
    private Texture tier2 = new Texture(Gdx.files.internal("sinihuus.png"));
    private Texture tier3 = new Texture(Gdx.files.internal("huussi.png"));
    private Texture tier4 = new Texture(Gdx.files.internal("huussi.png"));
    private Texture tier5 = new Texture(Gdx.files.internal("huussi.png"));

    private int cont;
    private long peeTimeBase = 20000;
    private long pooTimeBase = 30000;
    private int perSecond = 75;
    private long multiplierTime = 4;
    private double multiplierValue = 1.1;
    private boolean state;
    private long startedTime;

    public Toilets(Toilet x, String k) {
        toilet = x;
        key = k;
        keyS = k+"_S";
        keyC = k+"_C";
        menu = new Menu();
        backButton = new BackButton();
        contract = new ButtonBackground(7.3f,6f, contractTe);
        contract2 = new ButtonBackground(7.3f,5f, contractTe);
        upgrade = new ButtonBackground(7.3f,4f, upgradeTe);
        getTier();
        getCont();
        getStartedTime();
        if (cont!=0) {
            state = true;
        } else {
            state = false;
        }
        toilet.setToiletTexture(setTextureByTier());
    }

    public void startProduction(int which) {
        // 0 = pee, 1 = poo
        setCont(which);
        if (cont == 0) {
            startedTime = (peeTimeBase + tier * multiplierTime) + MemoryReader.readCurrentTimestamp();
        } else {
            startedTime = (pooTimeBase + tier * multiplierTime) + MemoryReader.readCurrentTimestamp();
        }
        state = true;
        MemoryWriter.writeTimer(keyS, startedTime);
    }

    private void getStartedTime() {
        startedTime = MemoryReader.readTimer(keyS);
    }

    public void checkProduction(Balance pee, Balance poo) {
        if (startedTime < MemoryReader.readCurrentTimestamp()) {
            state = false;
            if (cont==0) {
                pee.addValue((int)(
                        (peeTimeBase/1000 + tier * multiplierTime)
                                *(perSecond*tier*multiplierValue)));
            } else if (cont==1) {
                poo.addValue((int)(
                        (pooTimeBase/1000 + tier * multiplierTime)
                                *(perSecond*tier*multiplierValue)));
            }
            setCont(0);
        } else {
            state = true;
        }
    }

    public String getTimeLeftString() {
        long timeLeft = (startedTime - MemoryReader.readCurrentTimestamp())/1000;
        String time = timeLeft+"s";
        return time;
    }

    public boolean getState() {
        return state;
    }

    public int getTier() {
        MemoryReader.readToilet(this);
        return tier;
    }
    public int getCont() {
        MemoryReader.readToiletCont(this);
        return cont;
    }

    public void setCont(int cunt) {
        cont = cunt;
        MemoryWriter.writeToiletCont(keyC, cont);
    }

    private void resetTiers() {
        MemoryWriter.writeToilet(key, 0);
    }

    public String getKey() {
        return this.key;
    }

    public void setTier(int x) {
        tier = x;
    }

    public void upgrade() {
        tier++;
        MemoryWriter.writeToilet(key, tier);
        toilet.setToiletTexture(setTextureByTier());
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
        } else if (tier==4) {
            return price4;
        } else {
            return "";
        }
    }

    private Texture setTextureByTier() {
        if (tier==0) {
            return tier0;
        } else if (tier==1) {
            return tier1;
        } else if (tier==2) {
            return tier2;
        } else if (tier==3){
            return tier3;
        } else if (tier==4) {
            return tier4;
        } else {
            return tier5;
        }
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
    public ButtonBackground getContractButton() {
        return contract;
    }
    public ButtonBackground getContractButton2() {
        return contract2;
    }
    public ButtonBackground getUpgradeButton() {
        return upgrade;
    }
}
