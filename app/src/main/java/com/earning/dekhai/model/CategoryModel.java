package com.earning.dekhai.model;

public class CategoryModel {
    String name;
    String key;
    String image;
    String order;

    public CategoryModel() {
    }

    public CategoryModel(String name, String key, String image, String order) {
        this.name = name;
        this.key = key;
        this.image = image;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
