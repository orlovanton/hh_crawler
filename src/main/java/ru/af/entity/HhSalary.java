package ru.af.entity;

/**
 * з/п вакансии
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


    @Override
    public String toString() {
        return "HhSalary{" +
                "to=" + to +
                ", from=" + from +
                ", currency='" + currency + '\'' +
                '}';
    }
}
