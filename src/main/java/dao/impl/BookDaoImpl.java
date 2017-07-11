package dao.impl;

import dao.BookDao;
import model.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class BookDaoImpl implements BookDao
{
    @Autowired
    private SessionFactory session;

    @Override
    public List<Book> getAll()
    {
        return session.getCurrentSession().createQuery("from model.Book").list();
    }

    @Override
    public void add(Book book)
    {
        session.getCurrentSession().save(book);
    }

    @Override
    public void edit(Book book)
    {
        session.getCurrentSession().update(book);
    }

    @Override
    public void delete(Book book)
    {
        session.getCurrentSession().delete(book);
    }

    @Override
    public Book get(String author, String name)
    {
        return (Book) session.getCurrentSession().createQuery("from model.Book where author = :author and  name = :name")
                .setParameter("author", author)
                .setParameter("name", name)
                .uniqueResult();
    }

    @Override
    public List<Book> get(String name)
    {
        return session.getCurrentSession().createQuery("from model.Book where name = :name")
                .setParameter("name", name)
                .list();
    }
}
