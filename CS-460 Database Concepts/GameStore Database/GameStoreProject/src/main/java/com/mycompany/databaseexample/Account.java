package com.mycompany.databaseexample;

public class Account {

    private int id;
    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private String displayName;
    private int creditNumber;
    

    Account(int id, String first_name, String last_name, String username, String password, String displayName, int creditNumber) {
        
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.creditNumber = creditNumber;
    }

    /**
     * @return the account_id
     */
    public int getId() {
        return id;
    }

    /**
     * @param account_id the account_id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the fName
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * @param fName the fName to set
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * @return the lName
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * @param lName the lName to set
     */
    public void setLast_name(String last_name) {
        this.last_name=last_name;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return the creditNumber
     */
    public int getCreditNumber() {
        return creditNumber;
    }

    /**
     * @param creditNumber the creditNumber to set
     */
    public void setCreditNumber(int creditNumber) {
        this.creditNumber = creditNumber;
    }
}
