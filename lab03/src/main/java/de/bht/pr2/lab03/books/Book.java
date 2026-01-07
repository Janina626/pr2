package de.bht.pr2.lab03.books;

import java.util.Objects;

public abstract class Book {
    protected String title;
    protected String type;
    protected double price;
    protected int edition;

    public Book(String title, String type, double price, int edition){
        this.title = title;
        this.type = type;
        this.price = price;
        this.edition = edition;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice(){
        return price;
    }

    public String getType(){
        return type;
    }

    public int getEdition() {
        return edition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Book)) return false;
        Book book = (Book) o;
        if (edition != book.edition) return false;
        if (!title.equals(book.title)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, edition);
    }
}
