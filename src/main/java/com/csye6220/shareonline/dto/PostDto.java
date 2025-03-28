package com.csye6220.shareonline.dto;


public class PostDto {

    private String title;
    private String description;
    private String category;
    private String imageUrl;

    public PostDto() {}

    public PostDto(String title, String description, String category, String imageUrl) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
