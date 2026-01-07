package de.bht.pr2.lab03.part1;

import de.bht.pr2.lab03.books.AudioBook;
import de.bht.pr2.lab03.books.Book;
import de.bht.pr2.lab03.books.EBook;
import de.bht.pr2.lab03.books.PrintedBook;
import de.bht.pr2.lab03.parser.BookParser;
import de.bht.pr2.lab03.store.BookStore;

public class SumPrices {

  public static void main(String[] args) {
    /* Das Ergebnis der Berechnungen sollte sein:

    Summe Buch:     € 381.88
    Summe Ebuch:    € 255.82
    Summe Hoerbuch: € 344.58
    Summe Alle:     € 982.28
   */

    double sumBook = 0;
    double sumEbook = 0;
    double sumAudio = 0;

    for (String s : BookStore.getSoldBooks()) {
      Book b = BookParser.parse(s);

      if (b instanceof PrintedBook) {
        sumBook += b.getPrice();
      } else if (b instanceof EBook) {
        sumEbook += b.getPrice();
      } else if (b instanceof AudioBook) {
        sumAudio += b.getPrice();
      }
    }

    double sumAll = sumBook + sumEbook + sumAudio;

    System.out.printf("Summe Buch:     € %.2f%n", sumBook);
    System.out.printf("Summe Ebuch:    € %.2f%n", sumEbook);
    System.out.printf("Summe Hoerbuch: € %.2f%n", sumAudio);
    System.out.printf("Summe Alle:     € %.2f%n", sumAll);

  }
}
