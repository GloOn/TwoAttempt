import java.util.Scanner;
import java.io.*;
import java.lang.String;
import java.util.ArrayList;
/**
 * This ManageBookSystem class is to create a system of some function
 * doing with the book database.
 * Including adding, editing, searching and deleting books in
 * database, this system is used by the user directly.
 * And it also call the methods in Book class and BookDatabase class.
 * 
 * @author Jing Guo 
 * @version FIT5131 assignment2 (Due Date:24th Oct 2014)
 */
public class ManageBookSystem
{
    private BookDatabase bookList;
    private Validation validator;
    private Scanner input;
    private ErrorPrint error;
    
    /**
     * Constructor for objects of class ManageBookSystem
     */
    public ManageBookSystem()
    {
        bookList = new BookDatabase();
        validator = new Validation();
        input = new Scanner(System.in);
        error = new ErrorPrint();
    }
    
    /**
     * add one book in database
     */
    private void addOneBook()
    {
        System.out.println("Please add a new book");
        String title = "";
        ArrayList<String> author = new ArrayList<String>();
        int cost = 0;
        int rating = 0;
        boolean addedBook = false;
        Book book = new Book();
        while (!addedBook)
        {
            //enter the title of the book 
            while (true)
            {   
                System.out.print("Title: ");
                title = input.nextLine().trim();
                if (!validator.isNull(title) && validator.isOnlyLetter(title) && validator.existTitle(title, bookList) == -1)
                {
                    book.setTitle(title);
                    break;
                }
                else
                    error.invalidInput("title");
            }
            
            //enter the author of the book
            System.out.print("You can add up to 3 authors of book. How many author do you want to add?");
            boolean addedAuthor = false;
            while (!addedAuthor)
            {
                String countAuthor = input.nextLine().trim();
                switch (countAuthor)
                {
                    case "1": addMultipleAuthor(author, book, 1);
                              addedAuthor = true;break;
                    case "2": addMultipleAuthor(author, book, 2);
                              addedAuthor = true;break;
                    case "3": addMultipleAuthor(author, book, 3);
                              addedAuthor = true;break;
                    default:    
                        System.out.println("Enter 1 to 3 to select the number of the authors you want to add of this book.");
                        System.out.println("Please enter again:");break;
                }
            }
            
            //enter the cost of the book
            while (true)
            {   
                System.out.print("Cost: ");
                String inputCost = input.nextLine().trim();
                if (validator.isInteger(inputCost) && !validator.isNull(inputCost))
                //the cost has to be an integer
                {   
                    cost = Integer.valueOf(inputCost).intValue();
                    if (cost > 0)
                    // the cost should be bigger than 0
                    {
                        book.setCost(cost);
                        break;
                    }    
                    else
                        System.out.println("Please enter a positive number.");
                }
                else
                    System.out.println("Please enter a valid cost.");
            }
            
            // enter the rating of the book 
            while (true)
            {   
                System.out.print("Rating: ");
                String inputRating = input.nextLine().trim();
                if (validator.isInteger(inputRating) && !validator.isNull(inputRating))
                // the rating should be an integer
                {   
                    rating = Integer.valueOf(inputRating).intValue();
                    if (rating >= 0 && rating <= 10)
                    // the rating should be between 0 and 10
                    {
                        book.setRating(rating);
                        break;
                    }
                    else
                        System.out.println("Please enter a rating between 0 to 10.");
                }
                else
                    System.out.println("Please enter a valid rating.");
            }
            bookList.addBook(book);
            addedBook = true;
        }
        System.out.print("You have added a book: " + book.displayBookInFormat());
    }
    
    /**
     * add i ( 1 <= i <= 3) author(s) of one book
     */
     private void addMultipleAuthor(ArrayList<String> newAuthor, Book newBook, int number)
    {
        ArrayList<String> authorList = new ArrayList<String>();
        a:for(int inputTimes = 1; inputTimes <= number; inputTimes++)
        {
            System.out.print("Please enter #" + inputTimes + " author:");
            String inputAuthor = input.nextLine().trim();
            //check input author is a valid author and no re-enter
            if (!validator.isNull(inputAuthor) && validator.isOnlyLetter(inputAuthor) && !validator.existInputAuthor(inputAuthor, authorList))
            {
                authorList.add(inputAuthor);
            }
            else
            {
                System.out.println("You can not enter null, numbers or re-enter the name of the author.");
                inputTimes--;
                continue;
            }
        }
        newBook.setAuthor(authorList);
    }
    
    /**
     * delete one book from database
     */
    private void deleteBookByTitle()
    {
        System.out.println("Please enter a title of the book you want to delete");
        System.out.print("* The title must be a full exact match *\nEnter title: ");
        String searchTitle = input.nextLine().trim();
        //search the book by the input title
        int index = validator.existTitle(searchTitle, bookList);
        if (index < 0)//this title is not a book exist in the database
            System.out.println("Sorry, the book called " + searchTitle + " is not exist.");
        else
        {    
            Book theDeletedBook = bookList.getBook(index);
            System.out.println("The book you want to delete is "+ theDeletedBook.displayBookInFormat());
            System.out.println("Are you sure to delete it? (Y/N)");// comfirm the deletion
            boolean doDelete = false;
            while(!doDelete)
            {
                Scanner del = new Scanner(System.in);
                String deleteConfirm = del.nextLine().trim();
                switch (deleteConfirm.trim().toLowerCase())
                {
                    case "y": bookList.deleteBook(index);// delete the book
                          System.out.println("The book has been deleted.");
                          doDelete = true;break;
                          case "n": System.out.println("Action of deleting this book is not executed");
                          doDelete = true;break;
                          default: System.out.println("Please input only 'y' or 'n' to execute the action.");break;
                }
            }
        }
    }
    
    /**
     * display the book list
     */
    private void displayBookList(ArrayList<Book> bookList)
    {
        for (int i = 0; i < bookList.size(); i++)
        {
            Book bookInList = bookList.get(i);
            System.out.println(bookInList.displayBookInFormat());
        }
    }
    
    /**
     * display the menu
     */
    private void displayMenu()
    {
        String menuStr = "\n==================================\n";
        menuStr += "(1) Add Book\n";
        menuStr += "(2) Delete Book\n";
        menuStr += "(3) List Books By Title/Author\n";
        menuStr += "(4) List My Favorite Book\n";
        menuStr += "(5) List All Books\n";
        menuStr += "(6) Edit Book\n";
        menuStr += "(7) Exit System\n";
        menuStr += "==================================";
        System.out.println(menuStr);
        System.out.print("Please choose an option:");
    }
    
    /**
     * edit one book in database
     */
    private void editBook()
    {   
        System.out.println("Please enter the title of the book you want to edit or press * to exit.");
        boolean doEdit = false;
        while (!doEdit)
        {
            String inputTitle = input.nextLine().trim();
            // if input * then exit
            if (inputTitle.trim().equals("*"))
            {
                doEdit = true;
                System.out.println("Cancel editing book and exit.");
            }
            else
            {
                int index = validator.existTitle(inputTitle, bookList);
                if (index != -1)//check if the book exist in the database
                {
                    Book book = bookList.getBook(index);
                    boolean editedBook = false;
                    while (!editedBook)
                    {
                        System.out.println("Please select the part you want to edit or cancel.");
                        System.out.print("(1) Author; (2) Cost; (3) Rating; (4) Cancel.\n>");
                        String choice = input.nextLine().trim();
                        switch (choice.trim())
                        {
                            case "1": editBookAuthor(book);
                                      editedBook = true; doEdit = true; break;
                            case "2": editBookCost(book);
                                      editedBook = true; doEdit = true; break;
                            case "3": editBookRating(book);
                                      editedBook = true; doEdit = true; break;
                            case "4": System.out.println("Exit editing book.");
                                      editedBook = true; doEdit = true; break;
                            default:
                                  System.out.println("Please choice 1 to 4 to edit book or exit.");
                        }
                    }
                }
                else
                    System.out.print("Please enter an exist book title or press * to exit.\n>");
            }
        }
    }
    
    /**
     * edit the author(s) of the book
     */
    private void editBookAuthor(Book book)
    {
        System.out.print("Current author(s) is/are " + book.displayAuthor());
        {
            ArrayList<String> editAuthorList = new ArrayList<String>();
            boolean addedAuthor = false;
            while (!addedAuthor)
            {
                System.out.print("Please enter the number of the new author(s):");// select how many author of this book
                String countAuthor = input.nextLine().trim();
                switch (countAuthor)
                {
                    case "1": addMultipleAuthor(editAuthorList, book, 1);//edit the book by insert 1 author 
                              addedAuthor = true;break;
                    case "2": addMultipleAuthor(editAuthorList, book, 2);//edit the book by insert 2 author
                              addedAuthor = true;break;
                    case "3": addMultipleAuthor(editAuthorList, book, 3);//edit the book by insert 3 author
                              addedAuthor = true;break;
                    default:    
                        System.out.println("Enter 1 to 3 to select the number of the authors you want to add of this book:");break;
                }
            }
            System.out.println("You have edit the author of " + book.getTitle());
            System.out.println("The book now is: " + book.displayBookInFormat());
        }
    }
    
    /**
     * edit the cost of the book
     */
    private void editBookCost(Book book)
    {
        System.out.println("Current cost is " + book.getCost() + ". Please enter a new cost:");
        boolean editedCost = false;
        while (!editedCost)
        {
            String newCost = input.nextLine().trim();
            if ( validator.isInteger(newCost))
            {
                int cost = Integer.valueOf(newCost).intValue();
                if (cost != book.getCost() && cost > 0)// the cost must not be the same as the original one
                {
                    book.setCost(cost);
                    System.out.println("You have edit the cost of " + book.getTitle());
                    System.out.println("The book now is: " + book.displayBookInFormat());
                    editedCost = true;
                }
                else
                    System.out.println("You have to enter a new positive integer.");
            }
            else
            {
                error.invalidInput("integer");
            }
        }
    }
    
    /**
     * edit the rating of the book
     */
    private void editBookRating(Book book)
    {
        System.out.println("Current rating is " + book.getRating() + ". Please enter a new rating:");
        boolean editedRating = false;
        while (!editedRating)
        {
            String newRating = input.nextLine().trim();
            if ( validator.isInteger(newRating))
            {
                int rating = Integer.valueOf(newRating).intValue();
                if (rating != book.getRating() && rating >= 0 && rating <= 10)// the rating must not be the same as the original one
                {
                    book.setRating(rating);
                    System.out.println("You have edit the rating of " + book.getTitle());
                    System.out.println("The book now is: " + book.displayBookInFormat());
                    editedRating = true;
                }
                else
                    System.out.println("You have to enter a new integer between 0 and 10.");
            }
            else
            {
                error.invalidInput("integer");
            }
        }
    }

    /**
     * list all books in database
     */
    private void listAllBook()
    {
        for (int i = 0; i < bookList.getSize(); i++)
        {
            Book book = bookList.getBook(i);
            System.out.println( (i + 1) + "." + book.displayBookInFormat() );
        }
    }
    
    /**
     * list books by input authors (up to 3 authors)
     */
    private void listBookByAuthor()
    {
        boolean doSearch = true;
        while (doSearch)
        {
            System.out.print("Please enter the number of the author(s)(up to 3) you want to search or press * to exit:");
            String countAuthor = input.nextLine().trim();
            if(validator.isNull(countAuthor))// input must not be null
                continue;
            
            switch (countAuthor)
            {
                case "*": System.out.println("Cancel searching book and exit.");
                          doSearch = false; break;    
                case "1": searchBookByAuthor(1);// search book match 1 author
                          doSearch = false; break;
                case "2": searchBookByAuthor(2);// search book match 2 authors
                          doSearch = false; break;
                case "3": searchBookByAuthor(3);// search book match 3 authors
                          doSearch = false; break;
                default:
                    error.invalidInput("choice");
            }     
        }
    }
    
    /**
     * list books by input rating, the book's rating which is higher than or equals
     * to the input will be listed
     */
    private void listBookByRating()
    {
        System.out.print("Please enter a rating:");
        String inputRating = input.nextLine().trim();
        if (validator.isInteger(inputRating) && !validator.isNull(inputRating))
        // the rating should be an integer
        {   
            int rating = Integer.valueOf(inputRating).intValue();
            if (rating >= 0 && rating <= 10)
            // the rating should be between 0 and 10
            {
                ArrayList<Book> ratingBookList = new ArrayList<Book>();
                for(int i = 0; i < bookList.getSize(); i++)
                {
                    Book eachBook = bookList.getBook(i);
                    if (eachBook.getRating() >= rating)
                    {
                        ratingBookList.add(eachBook);
                    }
                }
                System.out.println("The book(s) is/are: ");
                displayBookList(ratingBookList);
            }
            else
                System.out.println("Please enter a rating between 0 to 10.");
        }
        else
            System.out.println("Please enter a valid rating.");
    }
    
    /**
     * list books by input title
     */
    private void listBookByTitle()
    {
        boolean doSearch = true;
        while (doSearch)
        {
            System.out.print("Please enter a title or press * to exit:");
            String searchTitle = input.nextLine().trim();
            if(validator.isNull(searchTitle))// input title must not be null
                continue;
                
            if (searchTitle.trim().equals("*"))
            {
                doSearch = false;
                System.out.println("Cancel searching book and exit.");
            }
            else
            {
                ArrayList<Book> titleBookList = validator.containTitle(searchTitle, bookList);
                if (titleBookList.size() == 0)
                {    
                    System.out.println("The book is not exist in the database.");
                    doSearch = false;
                }
                else
                {   
                    System.out.println("The book(s) is/are: ");
                    displayBookList(titleBookList);
                    doSearch = false;
                }
            }
        }
    }
    
    /**
     * list books by input title or author
     */
    private void listBookByTitleOrAuthor()
    {
        System.out.println("You can search the books by entering title/author.");
        boolean isEnd = false;
        while (!isEnd)
        {
            System.out.print("(1) By title; (2) By author; (3) Cancel.\nEnter the selection: ");
            String choice = input.nextLine().trim();
            switch (choice.trim())
            {
                case "1": listBookByTitle();
                          isEnd = true;break;
                case "2": listBookByAuthor();
                          isEnd = true;break;
                case "3": System.out.println("Cancel and exit.");
                          isEnd = true;break;
                default:
                    System.out.println("Please enter 1 to 3 for searching or exit.");break;
            }
        }
    }
    
    /**
     * load the book list from a txt file
     */
    public void loadFile()
    {
        String filename = "theBookList.txt";
        FileReader inputFile = null;
        String bookLine = "";
        try
        {
            inputFile = new FileReader(filename);
            Scanner loader = new Scanner(inputFile);
            while(loader.hasNextLine())
            {
                bookLine = loader.nextLine();
                bookList.addBook(bookLine);
            }
            inputFile.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("file not found");
        }
        catch(IOException e)
        {
            System.out.println("I/O error");
        }
    }
    
    public void saveFile(BookDatabase list)
    {
        String filename = "theBookList.txt";
        if (list == null)
            System.out.println("list is not exist.");
            
        try
        {
            PrintWriter outputFile = new PrintWriter(filename);
            for (int i = 0; i < list.getSize(); i++)
            {
                Book book = list.getBook(i);
                outputFile.println(book.displayBook());
            }
            outputFile.close();
        }
        catch(IOException e)
        {
            System.out.println("Unexpected I/O error occurs.");
        }
    }
    
    /**
     * get the list of input author name(s)
     */
    private void searchBookByAuthor(int numberOfAuthor)
    {
        boolean doSearch = true;
        while (doSearch)
        {
            ArrayList<String> inputAuthorList = new ArrayList<String>();// a list contents all the input author name
            for(int i = 1; i <= numberOfAuthor; i++)
            {
                System.out.print("Please enter #" + i + " author:");
                String inputAu = input.nextLine().trim();
                if (!validator.isNull(inputAu) && !validator.existInputAuthor(inputAu, inputAuthorList))
                // the input must not be null and no re-enter
                {
                    inputAuthorList.add(inputAu);
                }
                else
                {
                    System.out.print("You can not enter null or re-enter the name of the author.");
                    i--;
                    continue;
                }
            }
            ArrayList<Book> containAuthorBookList = validator.containAuthor(inputAuthorList, bookList);// a list contents the books which match the searching 
            if (containAuthorBookList.size() == 0)
            {
                System.out.println("The book is not exist in the database.");
                doSearch = false;
            }
            else
            {
                System.out.println("The book(s) is/are: ");
                displayBookList(containAuthorBookList);
                doSearch = false;
            }
        }
    }
    
    /**
     * star the manage book system
     */
    public void start()
    {
        System.out.print('\u000C');
        loadFile();
        System.out.println("==================================");
        System.out.println(" Welcome to Manage Book System ");
        System.out.println("==================================");
        
        while (true)
        {
            displayMenu();
            String choice = input.nextLine().trim();
            switch(choice.trim())
            {
                case "1": addOneBook();break;
                case "2": deleteBookByTitle();break;
                case "3": listBookByTitleOrAuthor();break;
                case "4": listBookByRating();break;
                case "5": listAllBook();break;
                case "6": editBook();break;
                case "7": System.out.println("Exit the Manage Book System.");
                          saveFile(bookList);System.exit(0);
                default: System.out.println("Wrong option.Please input an integer between 1 and 7.");
            }
        }
    }
}
