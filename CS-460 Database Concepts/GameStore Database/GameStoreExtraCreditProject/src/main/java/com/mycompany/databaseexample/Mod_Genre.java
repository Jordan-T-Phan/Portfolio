/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.databaseexample;

/**
 *
 * @author Jordan
 */
public class Mod_Genre {
    private int mod_id;
    private int genre_id;

    Mod_Genre(int mod_id, int genre_id){
        this.mod_id=mod_id;
        this.genre_id=genre_id;
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
     * @return the mod_genre
     */
    public int getGenre_id() {
        return genre_id;
    }

    /**
     * @param mod_genre the mod_genre to set
     */
    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }
}
