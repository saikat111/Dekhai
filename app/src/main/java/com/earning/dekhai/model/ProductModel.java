package com.earning.dekhai.model;

public class ProductModel {
    String category;
    String description;
    String id;
    String price;
    String name;
    String image;
    String tag;

    public ProductModel() {
    }

    public ProductModel(String category, String description, String id, String price, String name, String image, String tag) {
        this.category = category;
        this.description = description;
        this.id = id;
        this.price = price;
        this.name = name;
        this.image = image;
        this.tag = tag;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
