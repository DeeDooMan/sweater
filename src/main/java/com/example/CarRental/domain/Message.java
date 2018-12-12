package com.example.CarRental.domain;

import javax.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String text;
    private String tag;
    private String price;
    private String aviable;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String filename;

    public Message() {
    }

    public Message(String text, String tag, String price, String aviable, User user) {
        this.author = user;
        this.text = text;
        this.tag = tag;
        this.price = price;
        this.aviable = aviable;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public String getAviable() {
        return aviable;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setAviable(String aviable) {
        this.aviable = aviable;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}