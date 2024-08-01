/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.databaseexample;

/**
 *
 * @author Jordan
 */
public class Game_Library {
   private int acc_id;
   private int game_id;
   private int hours_played;
   public Game_Library(int acc_id, int game_id, int hours_played){
   this.acc_id=acc_id;
   this.game_id=game_id;
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
     * @return the game_id
     */
    public int getGame_id() {
        return game_id;
    }

    /**
     * @param game_id the game_id to set
     */
    public void setGame_id(int game_id) {
        this.game_id = game_id;
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
