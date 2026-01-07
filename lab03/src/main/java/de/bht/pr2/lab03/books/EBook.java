package de.bht.pr2.lab03.books;

public class EBook extends Book{
    private String tolinoVersion;

    public EBook(String title, String type, double price, int edition, String tolinoVersion) {
        super(title, type, price, edition);
        this.tolinoVersion = tolinoVersion;
    }

    public String getTolinoVersion(){
        return tolinoVersion;
    }
}
