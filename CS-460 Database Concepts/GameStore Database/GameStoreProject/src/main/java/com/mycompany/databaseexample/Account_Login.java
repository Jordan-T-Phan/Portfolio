/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.databaseexample;

/**
 *
 * @author Matthew O'Donnell
 * Account Login class
 */
public class Account_Login {
    private int id;
    private String username;
    private String password;
    private int admin_access;
    // Constructor
    Account_Login(int id, String username, String password, int admin_access){
    this.id = id;
    this.username = username;
    this.password = password;
    this.admin_access = admin_access;
    
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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param name the username to set
     */
    public void setUsername(String name) {
        this.username = name;
    }
    
        /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param name the password to set
     */
    public void setPassword(String name) {
        this.password = name;
    }
    
     /**
     * @return the admin access
     */
    public int getAccess() {
        return getAdmin_access();
    }

    /**
     * Gives admin access
     */
    public void giveAccess() {
        this.setAdmin_access(1);
    }

    /**
     * Revokes admin access
     */
    public void revokeAccess() {
        this.setAdmin_access(0);
    }

    /**
     * @param admin_access the access to set
     */
    public void setAccess(int admin_access) {
        this.setAdmin_access(admin_access);
    }

    /**
     * @return the admin_access
     */
    public int getAdmin_access() {
        return admin_access;
    }

    /**
     * @param admin_access the admin_access to set
     */
    public void setAdmin_access(int admin_access) {
        this.admin_access = admin_access;
    }
    
}
