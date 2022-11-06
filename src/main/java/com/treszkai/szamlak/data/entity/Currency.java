package com.treszkai.szamlak.data.entity;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
@Table(name = "SZML_CURR")
public class Currency {

    @Id
    @GeneratedValue
    @Column(name = "CURR_ID")
    private Long Id;

    @Column(name = "CURR_SHORT_NAME")
    private String shortName;

    @Column(name = "CURR_LONG_NAME")
    private String longName;

    public Currency() {
    }

    public Long getId() {
        return Id;
    }

    public Currency setId(Long id) {
        Id = id;
        return this;
    }

    public String getShortName() {
        return shortName;
    }

    public Currency setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public String getLongName() {
        return longName;
    }

    public Currency setLongName(String longName) {
        this.longName = longName;
        return this;
    }
}
