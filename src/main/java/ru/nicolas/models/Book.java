package ru.nicolas.models;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name="Book")
public class Book {
   @Id
   @Column(name = "book_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @ManyToOne
   @JoinColumn(name="person_id",referencedColumnName = "person_id")

    private Person owner;

  // @Pattern(regexp = "\\d{4}", message = "Year should contain 4 digits!")
    @Column(name="year")
    private int year;

@Column(name = "title")
    private String title;
@Column(name="author")
    private String author;

    @Column(name="ordered_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderedAt;

    public Book() {

    }

    public Book(  int year, String title, String author) {


        this.year = year;
        this.title = title;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }



    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }



    public void setYear(int year) {
        this.year = year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Date getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(Date orderedAt) {
        this.orderedAt = orderedAt;
    }
}
