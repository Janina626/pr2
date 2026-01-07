package de.bht.pr2.lab03.part2;

import de.bht.pr2.lab03.books.Book;
import de.bht.pr2.lab03.books.EBook;
import de.bht.pr2.lab03.parser.BookParser;
import de.bht.pr2.lab03.store.BookStore;

public class Tolino3BookCount {

  public static void main(String[] args) {
    /* Das Ergebnis der Berechnungen sollte sein:

    Anzahl Ebuecher bei Tolino 3: 9

     */
    int count = 0;

    for (String s : BookStore.getSoldBooks()) {
      Book b = BookParser.parse(s);

      if (b instanceof EBook) {
        EBook e = (EBook) b; // b vom Typ Buch wird zum Typ EBook umgewandelt
        if (e.getTolinoVersion().equals("3")) {
          count++;
        }
      }
    }
    System.out.println("Anzahl Ebuecher bei Tolino 3: " + count);
  }
}
