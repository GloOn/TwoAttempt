import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
/**
 * This BookDatabase class is for create book database to list
 * all the books.
 * It is obvious include a ArrayList class and some functions
 * of the ArrayList like 
 * 
 * @author Jing Guo 
 * @version FIT5131 assignment2 (Due Date:24th Oct 2014)
 */
public class BookDatabase
{
    private ArrayList<Book> bookList;
    
    /**
     * Constructor for objects of class BookDatabase
     */
    public BookDatabase()
    {
        bookList = new ArrayList<Book>();
        //validator = new Validation();
    }
    
    /**
     * adding a book in book list by inserting some values 
     */
    public void addBook(String newTitle, ArrayList<String> newAuthor, int newCost, int newRating)
    {
        Book newBook = new Book(newTitle, newAuthor, newCost, newRating);
        bookList.add(newBook);
    }
    
    /**
     * adding book in book list by inserting one line 
     */
    public void addBook(String bookDetailLine)
    {
        Book newBook = new Book(bookDetailLine);
        bookList.add(newBook);
    }
    
    /**
     * adding a book in book list
     */
    public void addBook(Book newBook)
    {
        bookList.add(newBook);
    }
    
    /**
     * clear all the books in the list 
     */
    public void clearList()
    {
        bookList.clear();
    }
    
    /**
     * delete a book from the database by matching the title inputed by user 
    */
    public void deleteBook(int index)
    {
        bookList.remove(index);
    }
    
    /**
     * get the book from the list by inputing the index 
     */
    public Book getBook(int index)
    {
        return bookList.get(index);
    }
    
    /**
     * get the book list
     */
    public ArrayList<Book> getBookList()
    {
        return bookList;
    }
    
    /**
     * get the index of the book
     */
    public int getIndex(Book book)
    {
        return bookList.indexOf(book);
    }
    
    /**
     * get the size of the book list 
     */
    public int getSize()
    {
        return bookList.size();
    }
    
    /**
     * set the book list
     */
    public void setBookList(ArrayList<Book> newBookList) 
    {
        bookList = newBookList;
    }
}   
    
