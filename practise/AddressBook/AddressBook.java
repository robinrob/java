import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * A class to maintain an arbitrary number of contact details.
 * Details are indexed by both name and phone number.
 * @author David J. Barnes and Michael Kolling.
 * @version 2008.03.30
 */
public class AddressBook
{
    // Storage for an arbitrary number of details.
    private TreeMap<String, ContactDetails> book;
    private int numberOfEntries;

    /**
     * Perform any initialization for the address book.
     */
    public AddressBook()
    {
        book = new TreeMap<String, ContactDetails>();
        numberOfEntries = 0;
    }
    
    /**
     * Look up a name or phone number and return the
     * corresponding contact details.
     * @param key The name or number to be looked up.
     * @return The details corresponding to the key.
     */
    public ContactDetails getDetails(String key)
    {
        if (keyInUse(key))
            return book.get(key);
        else return null;
    }

    /**
     * Return whether or not the current key is in use.
     * @param key The name or number to be looked up.
     * @return true if the key is in use, false otherwise.
     */
    public boolean keyInUse(String key)
    {
        return book.containsKey(key);
    }

    /**
     * Add a new set of details to the notebook.
     * @param details The details to associate with the person.
     */
    public boolean addDetails(ContactDetails details)
    {
        if (details != null){
        book.put(details.getName(), details);
        book.put(details.getPhone(), details);
        numberOfEntries++;
        return true;
    }
    return false;
    }
    
    /**
     * Change the details previously stored under the given key.
     * @param oldKey One of the keys used to store the details.
     * @param details The replacement details.
     */
    public boolean changeDetails(String oldKey,
                              ContactDetails details)
    {
        if (keyInUse(oldKey) && details != null){
        removeDetails(oldKey);
        addDetails(details);
        return true;
    }
    return false;
    }
    
    /**
     * Search for all details stored under a key that starts with
     * the given prefix.
     * @param keyPrefix The key prefix to search on.
     * @return An array of those details that have been found.
     */
    public ContactDetails[] search(String keyPrefix)
    {
        // Build a list of the matches.
        List<ContactDetails> matches = new LinkedList<ContactDetails>();
        // Find keys that are equal-to or greater-than the prefix.
        SortedMap<String, ContactDetails> tail = book.tailMap(keyPrefix);
        Iterator<String> it = tail.keySet().iterator();
        // Stop when we find a mismatch.
        boolean endOfSearch = false;
        while(!endOfSearch && it.hasNext()) {
            String key = it.next();
            if(key.startsWith(keyPrefix)) {
                matches.add(book.get(key));
            }
            else {
                endOfSearch = true;
            }
        }
        ContactDetails[] results = new ContactDetails[matches.size()];
        matches.toArray(results);
        return results;
    }

    /**
     * @return The number of entries currently in the
     *         address book.
     */
    public int getNumberOfEntries()
    {
        return numberOfEntries;
    }

    /**
     * Remove an entry with the given key from the address book.
     * @param key One of the keys of the entry to be removed.
     */
    public boolean removeDetails(String key)
    {
        if (keyInUse(key)){
        ContactDetails details = book.get(key);
        book.remove(details.getName());
        book.remove(details.getPhone());
        numberOfEntries--;
        return true;
    }
    return false;
    }


    /**
     * @return All the contact details, sorted according
     * to the sort order of the ContactDetails class.
     */
    public String listDetails()
    {
        // Because each entry is stored under two keys, it is
        // necessary to build a set of the ContactDetails. This
        // eliminates duplicates.
        StringBuffer allEntries = new StringBuffer();
        Set<ContactDetails> sortedDetails = new TreeSet<ContactDetails>(book.values());
        for(ContactDetails details : sortedDetails) {
            allEntries.append(details);
            allEntries.append('\n');
            allEntries.append('\n');
        }
        return allEntries.toString();
    }
}
