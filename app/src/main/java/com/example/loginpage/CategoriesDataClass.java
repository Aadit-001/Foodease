package com.example.loginpage;

public class CategoriesDataClass {

     private String name ;
     private String image_url ;
     private String restaurant_id;

     private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public CategoriesDataClass(String name, String image_url,String restaurant_id) {
        this.name = name;
        this.image_url = image_url;
        this.restaurant_id = restaurant_id;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public String getName() {
        return name;
    }

    public String getImage_url() {
        return image_url;
    }

    public CategoriesDataClass() {
    }
}
