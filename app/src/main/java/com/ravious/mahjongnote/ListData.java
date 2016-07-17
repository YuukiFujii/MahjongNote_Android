package com.ravious.mahjongnote;

/**
 * Created by yuuki on 7/17/16.
 */

public class ListData {
    private String averageRank;
    private String first;
    private String second;
    private String third;
    private String fourth;
    private String divident;
    private String payment;

    public void setAverageRank(long averageRank) {
        this.averageRank = String.valueOf(averageRank);
    }

    public String getAverageRank(){
        return averageRank;
    }

    public void setFirst(int first) {
        this.first = String.valueOf(first);
    }

    public String getFirst(){
        return first;
    }

    public void setSecond(long second) {
        this.second = String.valueOf(second);
    }

    public String getSecond(){
        return second;
    }

    public void setThird(long third) {
        this.third = String.valueOf(third);
    }

    public String getThird(){
        return third;
    }

    public void setFourth(long fourth) {
        this.fourth = String.valueOf(fourth);
    }

    public String getFourth(){
        return fourth;
    }

    public void setDivident(long divident) {
        this.divident = String.valueOf(divident);
    }

    public String getDivident(){
        return divident;
    }

    public void setPayment(long payment) {
        this.payment = String.valueOf(payment);
    }

    public String getPayment(){
        return payment;
    }



}
