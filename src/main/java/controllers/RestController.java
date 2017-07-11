package controllers;

import dao.BookDao;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Controller
public class RestController
{
    private static final String BAD_REQUEST = "book does not exists";

    @Autowired
    private BookDao bookDao;

    public void remove(String name)
    {
        List<Book> list = bookDao.get(name);
        if (list.isEmpty())
            System.out.println(BAD_REQUEST);
        else
        {
            Book book;
            if (list.size() > 1)
            {
                Collections.sort(list, (a, b) -> a.getName().compareTo(b.getName()));
                System.out.println("please select which one you want to delete");
                for (int i = 0; i < list.size(); ++i)
                    System.out.println("\t" + (i + 1) + " " + list.get(i));
                Scanner scanner = new Scanner(System.in);
                int id;
                do
                    id = scanner.nextInt();
                while (id < 1 || id > list.size());
                book = list.get(id - 1);
            }
            else
                book = list.get(0);
            bookDao.delete(book);
            System.out.println("removed " + book);
        }
    }

    public List<Book> all()
    {
        List<Book> list = bookDao.getAll();
        if (list.isEmpty())
            return null;
        else
        {
            Collections.sort(list, (a, b) -> a.getName().compareTo(b.getName()));
            return list;
        }
    }

    public void edit(String name)
    {
        List<Book> list = bookDao.get(name);
        if (list.isEmpty())
            System.out.println(BAD_REQUEST);
        else
        {
            Book book;
            Scanner scanner = new Scanner(System.in);
            if (list.size() > 1)
            {
                Collections.sort(list, (a, b) -> a.getName().compareTo(b.getName()));
                System.out.println("please select which one you want to edit");
                for (int i = 0; i < list.size(); ++i)
                    System.out.println("\t" + (i + 1) + " " + list.get(i));
                int id;
                do
                    id = scanner.nextInt();
                while (id < 1 || id > list.size());
                book = list.get(id - 1);
            }
            else
                book = list.get(0);
            System.out.print("type new book name: ");
            String newName = scanner.nextLine();
            book.setName(newName);
            bookDao.edit(book);
            System.out.println("edited " + book);
        }
    }

    public void add(String author, String name)
    {
        Book b = bookDao.get(author, name);
        if (b != null)
        {
            System.out.println("book already exists");
        }
        else
        {
            Book book = new Book(author, name);
            bookDao.add(book);
            System.out.println("added " + book);
        }
    }
}
