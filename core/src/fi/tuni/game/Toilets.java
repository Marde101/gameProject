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
    private Texture contractTe = new Texture(Gdx.files.internal("plainButton.png"));
    private Texture upgradeTe = new Texture(Gdx.files.internal("euroNappi.png"));
    private String price0 = "100";
    private String price1 = "500";
    private String price2 = "3000";
    private String price3 = "10000";
    private Texture tier0 = new Texture(Gdx.files.internal("puuHuusSilu.png"));
    private Texture tier1 = new Texture(Gdx.files.internal("sinihus.png"));
    private Texture tier2 = new Texture(Gdx.files.internal("huussi.png"));
    private Texture tier3 = new Texture(Gdx.files.internal("huussi.png"));

    public Toilets(Toilet x, String k) {
        toilet = x;
        key = k;
        menu = new Menu();
        backButton = new BackButton();
        contract = new ButtonBackground(8f,6f, contractTe);
        contract2 = new ButtonBackground(8f,5f, contractTe);
        upgrade = new ButtonBackground(8f,4f, upgradeTe);
        getTier();
        toilet.setToiletTexture(setTexturebyTier());
    }

    public int getTier() {
        MemoryReader.readToilet(this);
        return tier;
    }

    private void resetTiers() {
        MemoryWriter.writeToilet(key, 0);
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

    public String getKey() {
        return this.key;
    }

    public void upgrade() {
        tier++;
        MemoryWriter.writeToilet(key, tier);
        toilet.setToiletTexture(setTexturebyTier());
    }



    public void setTier(int x) {
        tier = x;
    }

    public String getPriceString() {
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

    public int getPriceInt() {
        int i = 0;
        if (tier==0) {
            return i = Integer.parseInt(price0);
        } else if (tier==1) {
            return i = Integer.parseInt(price1);
        } else if (tier==2) {
            return i = Integer.parseInt(price2);
        } else if (tier==3){
            return i = Integer.parseInt(price3);
        } else {
            return 0;
        }
    }

    private Texture setTexturebyTier() {
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


}
