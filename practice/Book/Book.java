
/**
 * A class that maintains information on a book.  This might form part of a larger
 * application such as a library system, for instance.
 * 
 * @author (Robin Smith) 
 * @version (16/10/2008)
 */
public class Book
{
    
    private String author;
    private String title;
    private int pages;
    private String ref;
    private int borrowed;
    
    public Book(String BookAuthor, String BookTitle, int BookPages)
{
    author = BookAuthor;
    title = BookTitle;
    pages = BookPages;
    
}

public String setRefNumber()
{
ref = author.substring(0,3) + title.substring(0,3);
return ref;
}

public String BookAuthor()
{
return author;
}

public String BookTitle()
{
return title;
}

public int BookPages()
{
return pages;
}

public void printBookDetails()
{
System.out.println("Title: " + title + "  Author: " + author + "  No. pages: " + pages);
System.out.println("  Ref. number: " + ref + "  No. times borrowed: " + borrowed);
}

public void Borrow()
{
borrowed = borrowed + 1;
}

public int getBorrowed()
{
return borrowed;
}
}