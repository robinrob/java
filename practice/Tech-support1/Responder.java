import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Collection;
import java.io.*;

/**
 * The responder class represents a response generator object. It is
 * used to generate an automatic response.
 * 
 * @author     Michael Kolling and David J. Barnes
 * @version    0.1  (1.Feb.2002)
 */
public class Responder
{

    private Random randomGenerator;
    private ArrayList responses;
    private HashMap responseMap;
    private HashMap synonymMap;
    private ArrayList matchArray;
    private InputReader choiceReader;
    private static final String RESPONSE_FILE = "responses.txt";

    
    /**
     * Construct a Responder - nothing to do
     */
    public Responder()
    {
        randomGenerator = new Random();
        responses = new ArrayList();
        fillResponses();
        responseMap = new HashMap();
        fillResponseMap();
        synonymMap = new HashMap();
        fillSynonymMap();
    }
    
    
    
    public String generateResponse()
    {
       int index = randomGenerator.nextInt(responses.size());
       return (String) responses.get(index);
    }
    
    public String generateResponse(String word)
    {
        String response = (String) responseMap.get(word);
        if(response != null){
            return response;
        }
        else {
            return generateResponse();
        }
    }
    
    public String generateResponse(HashSet words)
    {
        Iterator it = words.iterator();
        matchArray = new ArrayList();
        String word2 = new String();

        if (words.contains("listindex")){
            listRecognisedIndex();
            return null;
        }
        while(it.hasNext()){
            String word = (String) it.next();
             if(synonymMap.get(word) != null){
                 word = (String) synonymMap.get(word);
                }
             if(word.equals("why")){
                    return (String) responseMap.get("why");
                }
             else if(word.equals("what")){
                    return (String) responseMap.get("what");
             }
             else if(word.equals("how")){
                    return (String) responseMap.get("how");
             }
             else if(word.equals("pint")){
                    return (String) responseMap.get("pint");
             }
            }
        
            Iterator it2 = words.iterator();
            
        while(it2.hasNext()){
           word2 = (String) it2.next();
            
            if(synonymMap.get(word2) != null){
                word2 = (String) synonymMap.get(word2);
                }
                if (responseMap.get(word2) != null){
                    matchArray.add(word2);
                }
            }
            
            if (matchArray.size() == 0){
                return generateResponse();
            }
            if (matchArray.size() == 1){
                return (String) responseMap.get(word2);
            }
            
            String choice = chooseOption(matchArray);
            
            return (String) responseMap.get(choice);
            //response = (String) responseMap.get(word);


 
            //String response = (String) responseMap.get(word);
            
    }
    
    private String chooseOption(ArrayList options){
        choiceReader = new InputReader();
        Iterator it = options.iterator();
        System.out.println("Choose one: ");
        while(it.hasNext()){
            System.out.println("    "+ (String) it.next());
        }
        String choice = choiceReader.getInput();
        return choice;
    }
        
    
    private void listRecognisedIndex()
    {
        Collection<ArrayList> entries = (Collection<ArrayList>) responseMap.entrySet();
        Iterator it = entries.iterator();
        int index = 1;
        while(it.hasNext()){
            System.out.println("item " + index +")  " + it.next());
            index++;
        }
    }
    
    private void fillSynonymMap()
    {
        synonymMap.put("lewis","hamilton");
        synonymMap.put("felipe","massa");
        synonymMap.put("king","coulthard");
        synonymMap.put("lewishamilton","hamilton");
        synonymMap.put("threw","sick");
    }
     
    private void fillResponseMap()
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(RESPONSE_FILE));
            String line = reader.readLine();
            while (line != null){
                String nextLine = reader.readLine();
                responseMap.put(line, nextLine);
                line = reader.readLine();
            }
            reader.close();
        }
        catch (IOException e) {
            System.out.println("Error reading file: " + e);
        }
        
    }
    
    public void testWrite()
    {
        try {
            FileWriter writer = new FileWriter("responses2.txt");
            writer.write("bob");
            writer.close();
        }
        catch (IOException e) {
            System.out.println("Error writing file: " + e);
        }
    }

   
        
    private void fillResponses()
    {
        responses.add("You what?  Ur chattin shit.");

    }
}

