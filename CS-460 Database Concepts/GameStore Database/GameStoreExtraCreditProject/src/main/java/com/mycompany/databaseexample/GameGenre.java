package com.mycompany.databaseexample;

public class GameGenre {

    private int game_id; // Primary key
    private int genre_id;
    

    GameGenre(int game_id, int genre_id) {
        this.game_id = game_id;
        this.genre_id = genre_id;
    }

    public int getGame_id() {
      return game_id;
    }

    public void setGame_id(int game_id) {
      this.game_id = game_id;
    }

    public int getGenre_id() {
      return genre_id;
    }

    public void setGenre(int genre_id) {
      this.genre_id = genre_id;
    }
}
