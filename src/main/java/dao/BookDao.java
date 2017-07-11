package dao;

import model.Book;

import java.util.List;

public interface BookDao
{
    List<Book> getAll();
    Book get(String author, String name);
    List<Book> get(String author);
    void add(Book book);
    void edit(Book book);
    void delete(Book book);
}
