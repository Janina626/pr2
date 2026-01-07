package de.bht.pr2.lab03.part4;

import de.bht.pr2.lab03.books.AudioBook;
import de.bht.pr2.lab03.books.Book;
import de.bht.pr2.lab03.books.EBook;
import de.bht.pr2.lab03.books.PrintedBook;
import de.bht.pr2.lab03.parser.BookParser;
import de.bht.pr2.lab03.store.BookStore;

import java.util.HashSet;
import java.util.Set;

public class GroupByBookType {

  public static void main(String[] args) {
    /* Das Ergebnis der Berechnungen sollte sein:

Buecher Anzahl: 6
	Buch: 7 Minuten am Tag (2016)
	Buch: Der Heimweg (2016)
	Buch: Ohne Schuld (2020)
	Buch: Escape Room (2020)
	Buch: Der Heimweg (2020)
	Buch: Ohne Schuld (2016)
Ebuecher Anzahl: 4
	Ebuch: Ohne Schuld (2020)
	Ebuch: Der Heimweg (2020)
	Ebuch: Zwei Handvoll Leben (2020)
	Ebuch: Kingsbridge - Der Morgen einer neuen Zeit (2020)
Hoerbuecher Anzahl: 5
	Hoerbuch: QualityLand 2.0 (2019)
	Hoerbuch: Funken Mord (2018)
	Hoerbuch: QualityLand 2.0 (2018)
	Hoerbuch: Funken Mord (2019)
	Hoerbuch: Harry Potter - alle 7 Baende (2018)

     */
    Set<Book> books = new HashSet<>();
    Set<Book> ebooks = new HashSet<>();
    Set<Book> audioBooks = new HashSet<>();

    for (String s : BookStore.getSoldBooks()) {
      Book b = BookParser.parse(s);

      if (b instanceof PrintedBook) {
        books.add(b);
      } else if (b instanceof EBook) {
        ebooks.add(b);
      } else if (b instanceof AudioBook) {
        audioBooks.add(b);
      }
    }
    System.out.println("Buecher Anzahl: " + books.size());
    for (Book b : books) {
      System.out.println(" Buch: " + b.getTitle() + " (" + b.getEdition() + ")");
    }

    System.out.println("Ebuecher Anzahl: " + ebooks.size());
    for (Book b : ebooks) {
      System.out.println(" Ebuch: " + b.getTitle() + " (" + b.getEdition() + ")");
    }

    System.out.println("Hoerbuecher Anzahl: " + audioBooks.size());
    for (Book b : audioBooks) {
      System.out.println(" Hoerbuch: " + b.getTitle() + " (" + b.getEdition() + ")");
    }

  }
}
