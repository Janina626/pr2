package de.bht.pr2.lab03.part3;

import de.bht.pr2.lab03.books.AudioBook;
import de.bht.pr2.lab03.books.Book;
import de.bht.pr2.lab03.parser.BookParser;
import de.bht.pr2.lab03.store.BookStore;

public class StreamingAudioBookCount {

  public static void main(String[] args) {
    /* Das Ergebnis der Berechnungen sollte sein:

    Anzahl Hoerbuecher per Streaming: 5

     */
    int count = 0;

    for (String s : BookStore.getSoldBooks()) {
      Book b = BookParser.parse(s);

      if (b instanceof AudioBook) {
        AudioBook a = (AudioBook) b;
        if (a.getPlayMode().equalsIgnoreCase("Streaming")) {
          count++;
        }
      }
    }
    System.out.println("Anzahl Hoerbuecher per Streaming: " + count);
  }
}
