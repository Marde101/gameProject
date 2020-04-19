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
    private Texture contractTe = new Texture(Gdx.files.internal("plainButton.png"));
    private Texture upgradeTe = new Texture(Gdx.files.internal("euroNappi.png"));
    private String price0 = "1000";
    private String price1 = "5600";
    private String price2 = "23400";
    private String price3 = "99000";
    private Texture tier0 = new Texture(Gdx.files.internal("puuHuusSilu.png"));
    private Texture tier1 = new Texture(Gdx.files.internal("sinihus.png"));
    private Texture tier2 = new Texture(Gdx.files.internal("sinihuus.png"));
    private Texture tier3 = new Texture(Gdx.files.internal("huussi.png"));

    private int cont;
    private long example = 15000;
    private boolean state;
    private long startedTime;

    public Toilets(Toilet x, String k) {
        toilet = x;
        key = k;
        keyS = k+"_S";
        menu = new Menu();
        backButton = new BackButton();
        contract = new ButtonBackground(7.3f,6f, contractTe);
        contract2 = new ButtonBackground(7.3f,5f, contractTe);
        upgrade = new ButtonBackground(7.3f,4f, upgradeTe);
        getTier();
        toilet.setToiletTexture(setTextureByTier());
    }

    public void startProduction(int which) {
        // 0 = pee, 1 = poo
        cont = which;

        startedTime = example + MemoryReader.readCurrentTimestamp();
        state = true;
        MemoryWriter.writeTimer(keyS, startedTime);
    }

    public void checkProduction(Balance pee, Balance poo) {
        if (startedTime < MemoryReader.readCurrentTimestamp()) {
            state = false;
            if (cont==0) {
                pee.addValue(1500);
            } else if (cont==1) {
                poo.addValue(1500);
            }
        }
    }

    public boolean getState() {
        return state;
    }

    public int getTier() {
        MemoryReader.readToilet(this);
        return tier;
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
        } else {
            return tier3;
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
