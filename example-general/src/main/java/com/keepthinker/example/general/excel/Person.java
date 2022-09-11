package com.keepthinker.example.general.excel;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;

public class Person {
    @ExcelProperty({"Person", "name"})
    private String name;
    @ExcelProperty({"Person", "sex"})
    private String sex;
    @ExcelProperty({"Person", "address"})
    private String address;
    @ExcelProperty({"Person", "phone"})
    private String phone;
    @ExcelProperty({"Person", "height"})
    private int height;
    @ExcelProperty({"Person", "weight"})
    private int weight;
    @ExcelProperty({"Person", "age"})
    private int age;
    @ExcelProperty({"Person", "profession"})
    private String profession;
    @ExcelProperty({"Person", "salary"})
    private int salary;
    @ExcelProperty({"Person", "race"})
    private String race;
    @DateTimeFormat(value = "yyyy-MM-dd")
    @ExcelProperty({"Person", "birthday"})
    private Date birthday;
    @ExcelIgnore
    private int id;
    @ExcelProperty(value = {"Person", "country"}, converter = RegionConvertor.class)
    private String region;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
