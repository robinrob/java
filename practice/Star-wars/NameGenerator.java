

public class NameGenerator {



public String generateStarWarsName(String first, String last, String mothers, String town){

String swFirstName = last.substring(0,2) + first.substring(0,1);
String swSurname = mothers.substring(0,1) + town.substring(town.length()-3,town.length()-1);

return swFirstName + ' ' + swSurname ;
}

public void printUpper(String s){
System.out.println(s.toUpperCase());
}

}
                                
                                