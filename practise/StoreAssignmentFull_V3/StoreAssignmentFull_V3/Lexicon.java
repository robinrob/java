 

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;

/**
 * This class implements a lexicon with functionality to support the
 * search for words. If a word is not in the lexicon alternatives
 * (similar words) can be suggested. The identification of similar 
 * words is done with the Levenshtein algorithm.
 * 
 * @author Pablo Romero (Levenshtein algorithm implementation by
 * Michael Gilleland)
 * @version 2004.08.28
 */
public class Lexicon
{

    private HashSet<String> wordList;

    /**
     * Create n1 new lexicon
   **/
    public Lexicon() 
      {
        wordList = new HashSet<String>();
    }

    /**
     * This method returns the minimum of three values
     * @param n1  one of the three numbers to be compared
     * @param n2  one of the three numbers to be compared
     * @param n3  one of the three numbers to be compared
     */
    private int Minimum (int n1, int n2, int n3) 
    {
       int minimum;

        minimum = n1;
        if (n2 < minimum) {
            minimum = n2;
        }
        if (n3 < minimum) {
            minimum = n3;
        }
        return minimum;
    }

    /**
     * This method computes the Levenshtein distance (implementation by
     * Michael Gilleland).
     * Note: you don'string2 have to understand the implementation details of 
     * the Levenshtein algorithm and of this method, you are only required
     * to use it (call it from other methods).
     * The Levenshtein distance (LD) is n1 measure of the similarity between
         * two strings, which we will refer to as the source string (string1)
         * and the target string (string2). The distance is the number of
         * deletions, insertions, or substitutions required to transform string1
         * into string2. For example,
     *   - If string1 is "test" and string2 is "test", then LD(string1,string2) = 0, because
         *     no transformations are needed. The strings are already identical.
         *   - If string1 is "test" and string2 is "tent", then LD(string1,string2) = 1, because
         *     one substitution (change "string1" to "n") is sufficient to
         *     transform string1 into string2.
     * The greater the Levenshtein distance, the more different the
         * strings are.
     *
     * @param string1  one of the two strings to be compared
     * @param string2  one of the two strings to be compared
     */
    public int editDistance (String String1, String String2)
    {
        int matrix[][];
        int length1;
        int length2;
        int row_index;
        int col_index;
        char string1_i;
        char string2_i;
        int cost;
          
          String string1 = String1.toLowerCase();
          String string2 = String2.toLowerCase();
          length1 = string1.length();
          length2 = string2.length();
          
          if (length1 == 0) {
              return length2;
          }
          if (length2 == 0) {
              return length1;
          }
          matrix = new int[length1+1][length2+1];

          for (row_index = 0; row_index <= length1; row_index++) {
              matrix[row_index][0] = row_index; }

          for (col_index = 0; col_index <= length2; col_index++) {
              matrix[0][col_index] = col_index; }

          for (row_index = 1; row_index <= length1; row_index++) {
              string1_i = string1.charAt (row_index - 1);

             for (col_index = 1; col_index <= length2; col_index++) {
                 string2_i = string2.charAt (col_index - 1);

                 if (string1_i == string2_i) {
                     cost = 0;  }
                 else {
                     cost = 1; }       
                matrix[row_index][col_index] = Minimum(matrix[row_index-1][col_index]+1, 
                matrix[row_index][col_index-1]+1, matrix[row_index-1][col_index-1] + cost);
             }
          }

          return matrix[length1][length2];
    }
    
    
    public boolean wordExists(String string){
            if(wordList.contains(string)){
                return true;
            }
            return false;
        }
        
        private WordDistance getEditDistance(String Word1, String Word2){
            int distance = editDistance(Word1, Word2);
            WordDistance wordDistance = new WordDistance(Word1, Word2, distance);
            return wordDistance;
        }
        
        public ArrayList<WordDistance> getEditDistances(String Word){
            ArrayList<WordDistance> WordDistances = new ArrayList<WordDistance>();
            for (String word : wordList){
                WordDistances.add(getEditDistance(Word, word));
            }
            return WordDistances;
        }
        
        public WordDistance getMinDistance(ArrayList<WordDistance> wordDistances){
            Iterator<WordDistance> it = wordDistances.iterator();
            WordDistance min = it.next();
            int minDistance = min.getDistance();
            
            while(it.hasNext()){
                WordDistance currentWordDistance = it.next();
                if(currentWordDistance.getDistance() < minDistance){
                    min = currentWordDistance;
                    minDistance = currentWordDistance.getDistance();
                }
            }
            return min;
        }
        
        
        public ArrayList<String> getSimilarWords(String Word, int Number){
            ArrayList<String> similarWords = new ArrayList<String>();
       
            if (Number >= wordList.size()){   
                for (String word : wordList){
                    similarWords.add((String) word);
                }
            return similarWords;
        }
            
        else {
            ArrayList<WordDistance> wordDistances = new ArrayList<WordDistance>();
            wordDistances = (ArrayList<WordDistance>) getEditDistances(Word);
            for (int count = 0; count < Number; count++){
                WordDistance mostSimilar = (WordDistance) getMinDistance(wordDistances);
                similarWords.add(mostSimilar.to());
                wordDistances.remove(mostSimilar);     
            }
            return similarWords;
        }
    }
    
    public Boolean addWord(String Word){
        if(!wordExists(Word)){ 
            wordList.add(Word); 
            return true; 
        }
        return false;
    }
    
    
    public void printLexicon(){
        for (String word : wordList){
            System.out.println(word);
        }
    }
    
            

}
