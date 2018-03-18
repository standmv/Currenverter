package com.demoyageek.currenverter;

import java.util.Map;

/**
 * Created by StanDeMoya on 3/16/18.
 */

public class Currency {
    private String name;
    private String abbreviation;
    private int flagImgId;

    public Currency(String name, String abbreviation, int flagImgId){
        this.name = name;
        this.abbreviation = abbreviation;
        this.flagImgId = flagImgId;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public int getFlagImgId() {
        return flagImgId;
    }

    private Double calculateResult(Double quantity, Double rate){
        return quantity * rate;
    }
}
