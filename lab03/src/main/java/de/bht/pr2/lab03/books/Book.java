package de.bht.pr2.lab03.books;

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

    public double getPrice(){
        return price;
    }

    public String getType(){
        return type;
    }
}
