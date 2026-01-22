package de.bht.pr2.lab03.parser;

import de.bht.pr2.lab03.books.*;

public class BookParser {

    public static Book parse(String line){
        String[] parts =line.split(";");
        String title = parts[0].trim();
        String type = parts[1].trim();
        double price = Double.parseDouble(parts[2].trim());
        int edition = Integer.parseInt(parts[3].trim());
        // try - catch einbauen

        // "Der Heimweg;Buch;22.99;2016"
        return switch (type.toLowerCase()) {
            case "buch" -> new PrintedBook(title, type, price, edition);
            case "ebuch" -> new EBook(title, type, price, edition, parts[4].trim());
            case "hoerbuch" -> new AudioBook(title, type, price, edition, parts[4].trim());
            default -> throw new IllegalArgumentException("unbekannter Buchtyp " + type);
        };
    }
}
