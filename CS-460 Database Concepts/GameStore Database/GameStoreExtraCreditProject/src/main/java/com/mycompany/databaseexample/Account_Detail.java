/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.databaseexample;

/**
 *
 * @author Matthew O'Donnell
 * Account Detail class
 */
public class Account_Detail {
    private int id;
    private String first_name;
    private String last_name;
    private String display_name;
    private double balance;
    
    Account_Detail(int id, String first_name, String last_name, String display_name, double balance){
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.display_name = display_name;
        this.balance = balance;
    
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the first name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * @param name the first name to set
     */
    public void setFirst_name(String name) {
        this.first_name = name;
    }
    
        /**
     * @return the last_name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * @param last_name the last name to set
     */
    public void setLast_name(String name) {
        this.last_name = name;
    }
    
     /**
     * @return the display name
     */
    public String getDisplay_name() {
        return display_name;
    }

    /**
     * @param name the display name to set
     */
    public void setDisplay_name(String name) {
        this.display_name = name;
    }

    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
     /**
     * @param balance the balance to add
     */
    public void addbalance(double balance) {
        this.balance = this.balance + balance;
    }
    
     /**
     * @param balance the balance to deduct
     */
    public void deductbalance(double balance) {
        this.balance = this.balance - balance;
    }
}
