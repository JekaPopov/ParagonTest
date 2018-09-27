package ru.dravn.paragontest.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;


/**
 * Created by Jeka on 24.09.2018.
 */

@Entity
public class DataModel {

    @NonNull
    @PrimaryKey
    private int id;
    private String version;
    private String name;
    private Date released;
    private int api;
    private Double distribution;
    private String description;
    private Boolean favorite;

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleased() {
        return released;
    }

    public void setReleased(Date released) {
        this.released = released;
    }

    public Integer getApi() {
        return api;
    }

    public void setApi(int api) {
        this.api = api;
    }

    public Double getDistribution() {
        return distribution;
    }

    public void setDistribution(Double distribution) {
        this.distribution = distribution;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }
}