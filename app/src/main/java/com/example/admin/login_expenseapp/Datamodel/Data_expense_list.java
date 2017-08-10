package com.example.admin.login_expenseapp.Datamodel;

import android.util.Log;

import java.io.Serializable;

public class Data_expense_list implements Serializable {
    private final String TAG = "Data_expense_list";
    private int id;
    private String expense_category;
    private String expense_date;
    private String amount;
    private String description;
    private String expense_time;
    private String payment_to;
    private String email_id;
private byte[] icon_expense;

    public Data_expense_list(int id,
                             String amount,
                             String email_id,
                             String expense_categories,String description,
                             String expense_date,
                             String expense_time,
                             String payment_to,byte[] icon_expense) {
        Log.d(TAG,"amount = "+amount+"email_id = "+email_id+"expense_categories = "+expense_categories+"expense_date = "+expense_date
                +"expense_time = "+expense_time);
        this.id = id;
        this.expense_category= expense_categories;
        this.amount = amount;
        this.email_id=email_id;
        this.description=description;
        this.expense_time = expense_time;
        this.expense_date = expense_date;
        this.payment_to = payment_to;
        this.icon_expense=icon_expense;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpense_category() {
        return expense_category;
    }

    public void setExpense_category(String expense_category) {
        this.expense_category = expense_category;
    }


    public String getAmount() {
        return amount;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    public byte[] getIcon_expense() {
        return icon_expense;
    }

    public void setIcon_expense(byte[] icon_expense)
    {
        this.icon_expense = icon_expense;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.amount = amount;
    }

    public String getExpense_date() {
        return expense_date;
    }

    public void setExpense_date(String expense_date) {
        this.expense_date = expense_date;
    }

    public String getPayment_to() {
        return payment_to;
    }

    public void setPayment_to(String payment_to) {
        this.payment_to = payment_to;
    }
    public String getExpense_time() {
        return expense_time;
    }

    public void setExpense_time(String expense_time) {
        this.expense_time = expense_time;
    }

}

