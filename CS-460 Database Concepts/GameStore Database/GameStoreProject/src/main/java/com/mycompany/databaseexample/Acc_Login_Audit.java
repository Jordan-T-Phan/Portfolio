/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.databaseexample;

/**
 *
 * @author William Horton
 */
public class Acc_Login_Audit {
    private int id;
    private int acc_id;
    private String usernameBefore;
    private String usernameAfter;
    private String passwordBefore;
    private String passwordAfter;
    private String modified_user;
    private String modified_date;
    private char action;
    public Acc_Login_Audit(int id, int acc_id, String usernameBefore, String usernameAfter, String passwordBefore, String passwordAfter, String modified_user, String modified_date, char action) {
        this.id = id;
        this.acc_id = acc_id;
        this.usernameBefore = usernameBefore;
        this.usernameAfter = usernameAfter;
        this.passwordBefore = passwordBefore;
        this.passwordAfter = passwordAfter;
        this.modified_user = modified_user;
        this.modified_date = modified_date;
        this.action = action;
        
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
     * @return the acc_id
     */
    public int getAcc_id() {
        return acc_id;
    }

    /**
     * @param acc_id the acc_id to set
     */
    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
    }

    /**
     * @return the username
     */

    /**
     * @return the modified_user
     */
    public String getModified_user() {
        return modified_user;
    }

    /**
     * @param modified_user the modified_user to set
     */
    public void setModified_user(String modified_user) {
        this.modified_user = modified_user;
    }

    /**
     * @return the modified_date
     */
    public String getModified_date() {
        return modified_date;
    }

    /**
     * @param modified_date the modified_date to set
     */
    public void setModified_date(String modified_date) {
        this.modified_date = modified_date;
    }

    /**
     * @return the action
     */
    public char getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(char action) {
        this.action = action;
    }

    /**
     * @return the usernameBefore
     */
    public String getUsernameBefore() {
        return usernameBefore;
    }

    /**
     * @param usernameBefore the usernameBefore to set
     */
    public void setUsernameBefore(String usernameBefore) {
        this.usernameBefore = usernameBefore;
    }

    /**
     * @return the usernameAfter
     */
    public String getUsernameAfter() {
        return usernameAfter;
    }

    /**
     * @param usernameAfter the usernameAfter to set
     */
    public void setUsernameAfter(String usernameAfter) {
        this.usernameAfter = usernameAfter;
    }

    /**
     * @return the passwordBefore
     */
    public String getPasswordBefore() {
        return passwordBefore;
    }

    /**
     * @param passwordBefore the passwordBefore to set
     */
    public void setPasswordBefore(String passwordBefore) {
        this.passwordBefore = passwordBefore;
    }

    /**
     * @return the passwordAfter
     */
    public String getPasswordAfter() {
        return passwordAfter;
    }

    /**
     * @param passwordAfter the passwordAfter to set
     */
    public void setPasswordAfter(String passwordAfter) {
        this.passwordAfter = passwordAfter;
    }
    
}
