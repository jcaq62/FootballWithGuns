package com.example.footballwithguns;

public class Model {
    String company;
    String type;
    int move;
    int attack;
    int react;
    int toughness;
    int wounds;
    int nerve;
    int experience;
    int cost;
    int rebate;
    int save;

    //TODO: Traits

    public Model(){
        this.company = "Humans";
        this.type = "Lineman";
        this.move = 3;
        this.attack = 3;
        this.react = 3;
        this.toughness = 4;
        this.wounds = 2;
        this.nerve = 10;
        this.experience = 0;
        this.cost = 21;
        this.rebate = 0;
        this.save = 19;
    }

    public Model(String company, String type, int move, int attack, int react, int toughness, int wounds, int nerve, int experience, int cost, int rebate, int save) {
        this.company = company;
        this.type = type;
        this.move = move;
        this.attack = attack;
        this.react = react;
        this.toughness = toughness;
        this.wounds = wounds;
        this.nerve = nerve;
        this.experience = experience;
        this.cost = cost;
        this.rebate = rebate;
        this.save = save;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getReact() {
        return react;
    }

    public void setReact(int react) {
        this.react = react;
    }

    public int getToughness() {
        return toughness;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    public int getWounds() {
        return wounds;
    }

    public void setWounds(int wounds) {
        this.wounds = wounds;
    }

    public int getNerve() {
        return nerve;
    }

    public void setNerve(int nerve) {
        this.nerve = nerve;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getRebate() {
        return rebate;
    }

    public void setRebate(int rebate) {
        this.rebate = rebate;
    }

    public int getSave() {
        return save;
    }

    public void setSave(int save) {
        this.save = save;
    }
}
