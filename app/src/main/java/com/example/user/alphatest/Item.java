package com.example.user.alphatest;

public class Item {
    public int num;
    public String tNum;
    public String ability;

    public Item(String tNum, String ability, int num) {
        this.tNum = tNum;
        this.num = num;
        this.ability = ability;
    }

    public int getNum() {
        return num;
    }

    public String getTNum() {
        return tNum;
    }

    public String getAbility() {
        return ability;
    }
}
