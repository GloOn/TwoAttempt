import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This Validation class is for checking things.
 * Such as checking whether the input is valid,
 * checking the input is numeric,
 * checking the title or author is exist
 * and so on.
 * 
 * @author Jing Guo 
 * @version FIT5131 assignment2 (Due Date:24th Oct 2014)
 */
public class Validation
{    

    /**
     * Constructor for objects of class Validation
     */
    public Validation()
    {
        //null
    }

    /**
     * check the input author name(s) is/are an exist book's author 
     * in database and return to an ArrayList include all the books 
     * whose have the same author in the list
    */
    public ArrayList<Book> containAuthor(ArrayList<String> inputAuthorList, BookDatabase bookList)
    {
        ArrayList<Book> containAuthorBookList = new ArrayList<Book>();
        a:for (int index = 0; index < bookList.getSize(); index++)
        {
            Book eachBook = bookList.getBook(index);
            String authorOfEachBook = eachBook.getAuthor().toString().toLowerCase();
            // if all the author of this book do not contain all the input search author name
            // then this book is not the book we want to find, so found = false
            b:for (int j = 0; j < inputAuthorList.size(); j++)
            {
                String eachSearchName = inputAuthorList.get(j).toLowerCase();
                // if all the author of this book do not contain this search author name
                if (!authorOfEachBook.contains(eachSearchName))
                {
                    continue a;
                }
            }
            containAuthorBookList.add(eachBook);
        }
        return containAuthorBookList;
    }
    
    /**
     * check the input String is an exist book's title 
     * and return to an ArrayList include all the books 
     * whose titles contain the input String
    */
    public ArrayList<Book> containTitle(String searchTitle, BookDatabase bookList)
    {
        ArrayList<Book> containTitleBookList = new ArrayList<Book>();
        for (int index = 0; index < bookList.getSize(); index++)
        {
            Book eachBook = bookList.getBook(index);
            if (eachBook.getTitle().toLowerCase().contains(searchTitle.toLowerCase().trim()))
            {
                containTitleBookList.add(eachBook);
            }
        }
        return containTitleBookList;
    }
    
    /**
     * check whether the input search author name is inputed twice  
    */
    public boolean existInputAuthor(String searchAuthor, ArrayList<String> authorList)
    {
        for (int i = 0; i < authorList.size(); i++)
        {
            String existName = authorList.get(i);
            if (searchAuthor.equalsIgnoreCase(existName))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * check the input String is an exist book's title 
     * and return to an integer as its index in the list
    */
    public int existTitle(String searchTitle, BookDatabase bookList)
    {
        int index = -1;
        for (int i = 0; i < bookList.getSize(); i++)
        {
            Book eachBook = bookList.getBook(i);
            if (searchTitle.trim().equalsIgnoreCase(eachBook.getTitle()))
            {
                index = i;
                break;
            }
        }
        return index;
    }
    
    /**
     * check the input String is an integer
     */
    public boolean isInteger(String aString)
    {
        String regexPattern = "^(-|\\+)?\\d+$";
        return isValid(aString, regexPattern);
    }
    
    /**
     * check the input is null or just content space
     */
    public boolean isNull(String aString)
    {
        if (aString == null || aString.trim().length() == 0)
        {
            return true;
        }
        return false;
    }
    
    /**
     * check the input is only letters
     */
    public boolean isOnlyLetter(String aString)
    {
        String regexPattern = "^([A-Za-z]|\\s)+$";
        return isValid(aString, regexPattern);
    }
    
    /**
     * check the input is the thing I want
     */
    public boolean isValid(String aString,String regexPattern)
    {
        boolean isValid = false;
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(aString);
        if (matcher.find()) {
            isValid = true;
        }
        return isValid;
    }
}
