package com.mycompany.databaseexample;

public class Game {

    private int id; // Primary key
    private String title;
    private String publisher;
    private float orig_price;
    private float salePercent;
    private float userRating;
    private int downloads;

    Game(int id, String title, String publisher, float orig_price , float salePercent, float userRating,int downloads) {

        this.id = id;
        this.title = title;
   this.publisher = publisher;
   this.orig_price = orig_price;
        this.salePercent = salePercent;
        this.userRating = userRating;

        this.downloads = downloads;

    }
    
    public float getSale_Price(){
        return orig_price*salePercent;
    
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * @param publisher the publisher to set
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * @return the orig_price
     */
    public float getOrig_price() {
        return orig_price;
    }

    /**
     * @param orig_price the orig_price to set
     */
    public void setOrig_price(float orig_price) {
        this.orig_price = orig_price;
    }

    /**
     * @return the salePercent
     */
    public float getSalePercent() {
        return salePercent;
    }

    /**
     * @param salePercent the salePercent to set
     */
    public void setSalePercent(float salePercent) {
        this.salePercent = salePercent;
    }

    /**
     * @return the userRating
     */
    public float getUserRating() {
        return userRating;
    }

    /**
     * @param userRating the userRating to set
     */
    public void setUserRating(float userRating) {
        this.userRating = userRating;
    }

    /**
     * @return the downloads
     */
    public int getDownloads() {
        return downloads;
    }

    /**
     * @param downloads the downloads to set
     */
    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    
}