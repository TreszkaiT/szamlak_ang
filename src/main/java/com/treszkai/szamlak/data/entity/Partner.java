package com.treszkai.szamlak.data.entity;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Audited
@Table(name = "SZML_PTNR")
@Entity
public class Partner {

    @Id
    @GeneratedValue
    @Column(name = "PRTN_ID")
    private Long id;
    @Column(name = "PRTN_LAST_NAME")
    private String lastName;
    @Column(name = "PRTN_FIRST_NAME")
    private String firstName;
    @Column(name = "PRTN_COUNTRY")
    private String country;
    @Column(name = "PRTN_ZIP")
    private Integer zip;
    @Column(name = "PRTN_CITY")
    private String city;
    @Column(name = "PRTN_DISTRICT")
    private Integer district;            // kerület
    @Column(name = "PRTN_HOUSE_NUMBER")
    private Integer houseNumber;
    @Column(name = "PRTN_HOUSE_BUILDING")
    private Integer houseBuilding;
    @Column(name = "PRTN_HOUSE_STAIRCASE")
    private Integer houseStaircase;          // lépcsőház
    @Column(name = "PRTN_HOUSE_FLOOR")
    private Integer houseFloor;              // szint
    @Column(name = "PRTN_HOUSE_DOOR")
    private Integer houseDoor;
    @Column(name = "PRTN_BANK_NUMBER1")
    private Integer bankAccNum1;
    @Column(name = "PRTN_BANK_NUMBER2")
    private Integer bankAccNum2;
    @Column(name = "PRTN_BANK_NUMBER3")
    private Integer bankAccNum3;
    @Column(name = "PRTN_TAX_NUMBER1")
    private Integer taxNum1;
    @Column(name = "PRTN_TAX_NUMBER2")
    private Integer taxNum2;
    @Column(name = "PRTN_TAX_NUMBER3")
    private Integer taxNum3;
    @Column(name = "PRTN_CONTACT1_NAME")
    private String contact1Name;
    @Column(name = "PRTN_CONTACT1_TEL1")
    private Integer contact1Tel1;
    @Column(name = "PRTN_CONTACT1_TEL2")
    private Integer contact1Tel2;
    @Column(name = "PRTN_CONTACT1_EMAIL")
    private String contact1Email;
    @Column(name = "PRTN_CONTACT2_NAME")
    private String contact2Name;
    @Column(name = "PRTN_CONTACT2_TEL1")
    private Integer contact2Tel1;
    @Column(name = "PRTN_CONTACT2_TEL2")
    private Integer contact2Tel2;
    @Column(name = "PRTN_CONTACT2_EMAIL")
    private String contact2Email;

    public Partner() {
    }

    public Long getId() {
        return id;
    }

    public Partner setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Partner setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Partner setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Partner setCountry(String country) {
        this.country = country;
        return this;
    }

    public Integer getZip() {
        return zip;
    }

    public Partner setZip(Integer zip) {
        this.zip = zip;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Partner setCity(String city) {
        this.city = city;
        return this;
    }

    public Integer getDistrict() {
        return district;
    }

    public Partner setDistrict(Integer district) {
        this.district = district;
        return this;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public Partner setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
        return this;
    }

    public Integer getHouseBuilding() {
        return houseBuilding;
    }

    public Partner setHouseBuilding(Integer houseBuilding) {
        this.houseBuilding = houseBuilding;
        return this;
    }

    public Integer getHouseStaircase() {
        return houseStaircase;
    }

    public Partner setHouseStaircase(Integer houseStaircase) {
        this.houseStaircase = houseStaircase;
        return this;
    }

    public Integer getHouseFloor() {
        return houseFloor;
    }

    public Partner setHouseFloor(Integer houseFloor) {
        this.houseFloor = houseFloor;
        return this;
    }

    public Integer getHouseDoor() {
        return houseDoor;
    }

    public Partner setHouseDoor(Integer houseDoor) {
        this.houseDoor = houseDoor;
        return this;
    }

    public Integer getBankAccNum1() {
        return bankAccNum1;
    }

    public Partner setBankAccNum1(Integer bankAccNum1) {
        this.bankAccNum1 = bankAccNum1;
        return this;
    }

    public Integer getBankAccNum2() {
        return bankAccNum2;
    }

    public Partner setBankAccNum2(Integer bankAccNum2) {
        this.bankAccNum2 = bankAccNum2;
        return this;
    }

    public Integer getBankAccNum3() {
        return bankAccNum3;
    }

    public Partner setBankAccNum3(Integer bankAccNum3) {
        this.bankAccNum3 = bankAccNum3;
        return this;
    }

    public Integer getTaxNum1() {
        return taxNum1;
    }

    public Partner setTaxNum1(Integer taxNum1) {
        this.taxNum1 = taxNum1;
        return this;
    }

    public Integer getTaxNum2() {
        return taxNum2;
    }

    public Partner setTaxNum2(Integer taxNum2) {
        this.taxNum2 = taxNum2;
        return this;
    }

    public Integer getTaxNum3() {
        return taxNum3;
    }

    public Partner setTaxNum3(Integer taxNum3) {
        this.taxNum3 = taxNum3;
        return this;
    }

    public String getContact1Name() {
        return contact1Name;
    }

    public Partner setContact1Name(String contact1Name) {
        this.contact1Name = contact1Name;
        return this;
    }

    public Integer getContact1Tel1() {
        return contact1Tel1;
    }

    public Partner setContact1Tel1(Integer contact1Tel1) {
        this.contact1Tel1 = contact1Tel1;
        return this;
    }

    public Integer getContact1Tel2() {
        return contact1Tel2;
    }

    public Partner setContact1Tel2(Integer contact1Tel2) {
        this.contact1Tel2 = contact1Tel2;
        return this;
    }

    public String getContact1Email() {
        return contact1Email;
    }

    public Partner setContact1Email(String contact1Email) {
        this.contact1Email = contact1Email;
        return this;
    }

    public String getContact2Name() {
        return contact2Name;
    }

    public Partner setContact2Name(String contact2Name) {
        this.contact2Name = contact2Name;
        return this;
    }

    public Integer getContact2Tel1() {
        return contact2Tel1;
    }

    public Partner setContact2Tel1(Integer contact2Tel1) {
        this.contact2Tel1 = contact2Tel1;
        return this;
    }

    public Integer getContact2Tel2() {
        return contact2Tel2;
    }

    public Partner setContact2Tel2(Integer contact2Tel2) {
        this.contact2Tel2 = contact2Tel2;
        return this;
    }

    public String getContact2Email() {
        return contact2Email;
    }

    public Partner setContact2Email(String contact2Email) {
        this.contact2Email = contact2Email;
        return this;
    }
}
