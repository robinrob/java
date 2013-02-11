import java.util.ArrayList;

public class PhraseGenerator
{


    private final int SENSITIVITY = 5;
    private Lexicon lexicon;
    
    public PhraseGenerator()
    {
        lexicon = new Lexicon();
    }
    
    
    public void addTermWordsToLexicon(String termWords){
    String[] split_words = termWords.split(" ");
        
    for (int i = 0; i < split_words.length; i++){
                lexicon.addWord(split_words[i]);
        }
    }
    
    public ArrayList<String> createPossiblePhrases(ArrayList<String> phrases, ArrayList<String> words){
    ArrayList<String> possiblePhrases = new ArrayList<String>();
    for (String phrase : phrases){
        for (String word : words){
            if (phrase.equals("")){ 
                possiblePhrases.add(word); 
            }             
            else {
            possiblePhrases.add(phrase + " " + word);
        }
      }
    }
        return possiblePhrases;
    }
    
    public ArrayList<String> findAlternativePhrases(String wholePhrase){
        String[] phraseWords = wholePhrase.split(" ");
        ArrayList<String> phrases = new ArrayList<String>();
        phrases.add("");
        ArrayList<String> words = new ArrayList<String>();
        
        for (String word : phraseWords){
            if (lexicon.wordExists(word)){
                words.add(word);
            }
            else { 
                words.addAll(lexicon.getSimilarWords(word, 5));
            }
            phrases = (createPossiblePhrases(phrases, words));
        }
        
        return phrases;
    }
    

}