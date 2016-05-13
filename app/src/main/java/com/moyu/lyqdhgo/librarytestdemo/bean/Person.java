package com.moyu.lyqdhgo.librarytestdemo.bean;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by lyqdhgo on 2016/5/13.
 */
public class Person extends RealmObject {
    private String name;
    private String imageUrl;
    private RealmList<Dog> dogs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public RealmList<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(RealmList<Dog> dogs) {
        this.dogs = dogs;
    }
}
