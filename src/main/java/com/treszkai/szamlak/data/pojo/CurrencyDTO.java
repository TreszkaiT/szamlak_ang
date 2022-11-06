package com.treszkai.szamlak.data.pojo;


public class CurrencyDTO {

    private Long Id;

    private String shortName;

    private String longName;

    public CurrencyDTO() {
    }

    public Long getId() {
        return Id;
    }

    public CurrencyDTO setId(Long id) {
        Id = id;
        return this;
    }

    public String getShortName() {
        return shortName;
    }

    public CurrencyDTO setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public String getLongName() {
        return longName;
    }

    public CurrencyDTO setLongName(String longName) {
        this.longName = longName;
        return this;
    }
}
