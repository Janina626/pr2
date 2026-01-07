package de.bht.pr2.lab03.part1;

import de.bht.pr2.lab03.books.AudioBook;
import de.bht.pr2.lab03.books.Book;
import de.bht.pr2.lab03.books.EBook;
import de.bht.pr2.lab03.books.PrintedBook;
import de.bht.pr2.lab03.parser.BookParser;
import de.bht.pr2.lab03.store.BookStore;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyTest {

  @Test
  void testSomething() {

  }

  @Test
  void testSumPrices() {
    double sumBook = 0;
    double sumEbook = 0;
    double sumAudio = 0;

    for (String s : BookStore.getSoldBooks()) {
      Book b = BookParser.parse(s);
      if (b instanceof PrintedBook) sumBook += b.getPrice();
      else if (b instanceof EBook) sumEbook += b.getPrice();
      else if (b instanceof AudioBook) sumAudio += b.getPrice();
    }

    double sumAll = sumBook + sumEbook + sumAudio;

    assertEquals(381.88, sumBook, 0.01); //damit Rundungsfehler akzeptiert werden
    assertEquals(255.82, sumEbook, 0.01);
    assertEquals(344.58, sumAudio, 0.01);
    assertEquals(982.28, sumAll, 0.01);
  }

  @Test
  void testTolino3Count() {
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
    assertEquals(9, count);
  }

  @Test
  void testStreamingAudioCount() {
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
    assertEquals(5, count);
  }

  @Test
  void testGroupByBookTypeCounts() {
    Set<Book> books = new HashSet<>();
    Set<Book> ebooks = new HashSet<>();
    Set<Book> audioBooks = new HashSet<>();

    for (String s : BookStore.getSoldBooks()) {
      Book b = BookParser.parse(s);
      if (b instanceof PrintedBook) books.add(b);
      else if (b instanceof EBook) ebooks.add(b);
      else if (b instanceof AudioBook) audioBooks.add(b);
    }

    assertEquals(6, books.size());
    assertEquals(4, ebooks.size());
    assertEquals(5, audioBooks.size());
  }
}
