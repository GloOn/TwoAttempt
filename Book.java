import java.util.ArrayList;

/**
 * This Book class is for create books with 4 attributes
 * that includes its title, author, cost and rating.
 * It is obvious that the title and author here are Strings,
 * and the cost is a double while the rating is integer.
 * 
 * @author Jing Guo 
 * @version FIT5131 assignment2 (Due Date:24th Oct 2014)
 */
public class Book
{
    // the book's title
    private String title;
    // the book's author
    private ArrayList<String> author;
    // the book's cost
    private int cost;
    // the book's rating
    private int rating;

    /**
     * Constructor 1 for objects of class Book without insert value
     */
    public Book()
    {
        // create a book without field value
        title = "unknown";
        author = new ArrayList<String>();
        cost = 0;
        rating = 0;
    }

    /**
     * Constructor 2 for objects of class Book by insert some value
     */
    public Book(String newTitle, ArrayList<String> author, int newCost, int newRating)
    {
        // the title equal to newTitle
        title = newTitle;
        // the author equal to newAuthor
        author = new ArrayList<String>();
        // the cost equal to newCost
        cost = newCost;
        // the rating equal to newRating
        rating = newRating;
    }
    
    /**
     * Constructor 3 for objects of class Book by insert a line of value detail
     */
    public Book(String detailOfBook)
    {
        // create a book without field value
        String bookDetail[] = detailOfBook.split(",");
        int detailLength = bookDetail.length;
        title = bookDetail[0].trim();
        author = new ArrayList<String>();
        for (int i = 1; i < detailLength - 2; i++)
        {
            author.add(bookDetail[i].trim());
        }
        cost = Integer.parseInt(bookDetail[detailLength - 2].trim());
        rating = Integer.parseInt(bookDetail[detailLength - 1].trim());
    }
    
    /**
     * display the author(s) of the book
     */
    public String displayAuthor()
    {
        String authorStr = "";
        for (int i = 0; i < author.size(); i++)
        {
            authorStr = authorStr + author.get(i) + ",";
        }
        return authorStr;
    }
    
    /**
     * display the detail of the book
     */
    public String displayBook()
    {
        String authorStr = "";
        for (int i = 0; i < author.size(); i++)
        {
            authorStr = authorStr + author.get(i) + ",";
        }
        return (title + "," + authorStr + cost + "," + rating);
    }
    
    /**
     * display the detail of the book in good format
     */
    public String displayBookInFormat()
    {
        String authorStr = "";
        for (int i = 0; i < author.size(); i++)
        {
            authorStr = authorStr + author.get(i) + ",";
        }
        return ("Title: " + title + ";  Author(s): " + authorStr + "\nCost: $" + cost + ";  Rating: " + rating);
    }
    
    /**
     * get the author of the book
     * @return     author
     */
    public ArrayList<String> getAuthor()
    {
        return author;
    }
    
    /**
     * get the cost of the book
     * @return     cost
     */
    public int getCost()
    {
        return cost;
    }
    
    /**
     * get the rating of the book
     * @return     rating
     */
    public int getRating()
    {
        return rating;
    }
    
    /**
     * get the title of the book 
     * @return     title  
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * set new author of the book
     * @param  author
     */
    public void setAuthor(ArrayList<String> newAuthor)
    {
        author = newAuthor;
    }
    
    /**
     * set new cost of the book
     * @param  cost
     */
    public void setCost(int newCost)
    {
        cost = newCost;
    }
    
    /**
     * set new rating of the book
     * @param  rating
     */
    public void setRating(int newRating)
    {
        rating = newRating;
    }
    
    /**
     * set new title of the book
     * @param  title
     */
    public void setTitle(String newTitle)
    {
        title = newTitle;
    }
}
