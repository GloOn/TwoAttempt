import java.util.*;
/**
 * Write a description of class ErrorPrint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ErrorPrint
{
   
    /**
     * Constructor for objects of class ErrorPrint
     */
    public ErrorPrint()
    {
        
    }
    
    public void s()
    {
        System.out.print(" ");
        Scanner n = new Scanner(System.in);
        String input = n.nextLine();
        if (input == null)
            System.out.print("null");
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void invalidInput(String name)
    {
        System.out.println("Invalid input. Please enter the " + name + " again.");
    }
    
    public void print()
    {
        int n = 3;
        System.out.println(n++);// first print n, then n+1
        System.out.println(n);
        System.out.println(++n);// first n+1, then print n+1
        System.out.println(n);
    }
}
