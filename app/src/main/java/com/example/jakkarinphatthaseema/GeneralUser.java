package com.example.jakkarinphatthaseema;

public class GeneralUser extends User {
    public GeneralUser(String name, String sex, String age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public void Print() {
        System.out.println("User: " + name + ", Sex: " + sex + ", Age: " + age);
    }
}
