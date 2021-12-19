package com.earning.dekhai.model;

public class payment_history_cashout_model {

    String tvDekhaiID;
    String tvWalletIdP;
    String tvAmountP;
    String tvReqDate;
    String tvConfDate;
    String tvStatusP;
    public payment_history_cashout_model(String tvDekhaiID, String tvWalletIdP, String tvAmountP, String tvReqDate, String tvConfDate, String tvStatusP) {
        this.tvDekhaiID = tvDekhaiID;
        this.tvWalletIdP = tvWalletIdP;
        this.tvAmountP = tvAmountP;
        this.tvReqDate = tvReqDate;
        this.tvConfDate = tvConfDate;
        this.tvStatusP = tvStatusP;
    }

    public String getTvDekhaiID() {
        return tvDekhaiID;
    }

    public String getTvWalletIdP() {
        return tvWalletIdP;
    }

    public String getTvAmountP() {
        return tvAmountP;
    }

    public String getTvReqDate() {
        return tvReqDate;
    }

    public String getTvConfDate() {
        return tvConfDate;
    }

    public String getTvStatusP() {
        return tvStatusP;
    }
}
