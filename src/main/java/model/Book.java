package model;


import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book
{
    @Id
    @Column(name = "book_id")
    @GeneratedValue
    private Integer id;

    @Column
    private String author;

    @Column
    private String name;

    public Book(){}

    public Book(String author, String name)
    {
        this.author = author;
        this.name = name;
    }

    public Integer getId()
    {
        return id;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return author + " \"" + name + "\"";
    }
}
