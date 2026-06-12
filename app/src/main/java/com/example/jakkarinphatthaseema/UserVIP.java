package com.example.jakkarinphatthaseema;

public class UserVIP extends User {
    //attribute
    String vipLevel;
    Double discountRate;

    //method
    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    public void Print() {
        System.out.println(this.name + " " + this.sex + " " + this.age + " " + this.vipLevel + " " + this.discountRate);
    }
}