package com.moyu.lyqdhgo.librarytestdemo.bean;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by lyqdhgo on 2016/5/13.
 */
public class Dog extends RealmObject {
    @Required
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
