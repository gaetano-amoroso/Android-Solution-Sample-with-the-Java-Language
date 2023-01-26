package com.gaetanoamoroso.fragmenttofragmentcomunicationdinamic;

import android.graphics.drawable.Drawable;

public class Item {
    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    private  String initial;
    private String title;
    private String author;
    private Drawable drawable;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public Item(String title, String author, Drawable drawable) {
        this.title = title;
        this.author = author;
        this.drawable = drawable;
        this.initial = String.valueOf(this.author.charAt(0));
    }
}
