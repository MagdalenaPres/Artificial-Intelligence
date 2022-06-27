package com.company;

import java.util.ArrayList;

public class Variable
{
    private ArrayList<Integer> domain;
    private ArrayList<Integer> currentDomain;
    private int value;
    private int row;
    private int col;

    public Variable(ArrayList<Integer> domain, ArrayList<Integer> currentDomain, int value) {
        this.domain = domain;
        this.currentDomain = currentDomain;
        this.value = value;
    }

    public ArrayList<Integer> getDomain() {
        return domain;
    }

    public void setDomain(ArrayList<Integer> domain) {
        this.domain = domain;
    }

    public ArrayList<Integer> getCurrentDomain() {
        return currentDomain;
    }

    public void setCurrentDomain(ArrayList<Integer> currentDomain) {
        this.currentDomain = currentDomain;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
