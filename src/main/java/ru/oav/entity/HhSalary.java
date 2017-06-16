package ru.oav.entity;

/**
 * Created by antonorlov on 16/06/2017.
 */
public class HhSalary {

    private int to;
    private int from;
    private String currency;


    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
