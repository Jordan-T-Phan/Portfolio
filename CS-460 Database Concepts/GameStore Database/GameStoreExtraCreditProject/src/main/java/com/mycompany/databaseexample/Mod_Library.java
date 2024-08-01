/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.databaseexample;

/**
 *
 * @author Jordan
 */
public class Mod_Library {
    private int acc_id;
    private int mod_id;
    private int hours_played;
    public Mod_Library(int acc_id, int mod_id, int hours_played){
    this.acc_id=acc_id;
    this.mod_id=mod_id;
    this.hours_played=hours_played;
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
     * @return the mod_id
     */
    public int getMod_id() {
        return mod_id;
    }

    /**
     * @param mod_id the mod_id to set
     */
    public void setMod_id(int mod_id) {
        this.mod_id = mod_id;
    }

    /**
     * @return the hours_played
     */
    public int getHours_played() {
        return hours_played;
    }

    /**
     * @param hours_played the hours_played to set
     */
    public void setHours_played(int hours_played) {
        this.hours_played = hours_played;
    }
}
