package com.android.josesantos.upcomingmovies.data.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by josesantos on 05/12/17.
 */

public class Genres {

    @SerializedName("genres")
    @Expose
    private List<Genre> genres = new ArrayList<>();

    private HashMap<Integer,String> genresMap = new HashMap<>();

    public List<Genre> getGenresList() {
        return genres;
    }

    public HashMap<Integer,String> getGenresMap(){
        for (Genre genre :
                genres) {
            genresMap.put(genre.getId(), genre.getName());
        }
        return genresMap;
    }

    public String getGenresText(List<Integer> genres){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < genres.size(); i++) {
            builder.append(getGenresMap().get(genres.get(i)));
            if (i != genres.size()-1){
                builder.append(", ");
            }
        }
        return builder.toString();
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public class Genre {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}