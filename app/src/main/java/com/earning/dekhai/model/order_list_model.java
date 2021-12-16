package com.earning.dekhai.model;

public class order_list_model {
    String order_no;
    String amount;
    String order_date;
    String deliver_date;
    String status;

    public order_list_model(String order_no, String amount, String order_date, String deliver_date, String status) {
        this.order_no = order_no;
        this.amount = amount;
        this.order_date = order_date;
        this.deliver_date = deliver_date;
        this.status = status;
    }

    public String getOrder_no() {
        return order_no;
    }

    public String getAmount() {
        return amount;
    }

    public String getOrder_date() {
        return order_date;
    }

    public String getDeliver_date() {
        return deliver_date;
    }

    public String getStatus() {
        return status;
    }
}
