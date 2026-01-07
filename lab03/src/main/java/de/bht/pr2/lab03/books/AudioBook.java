package de.bht.pr2.lab03.books;

public class AudioBook extends Book{
    private String playMode;

    public AudioBook(String title, String type, double price, int edition, String playMode) {
        super(title, type, price, edition);
        this.playMode = playMode;
    }

    public String getPlayMode(){
        return playMode;
    }
}
