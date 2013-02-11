import java.util.ArrayList;
import java.util.Iterator;

public class Notebook
{

private ArrayList notes;

public Notebook()
{
notes = new ArrayList();
}

public void storeNote(String note)
{
notes.add(note);
}

public int numberOfNotes()
{
return notes.size();
}

public void showNote(int noteNumber)
{
if(noteNumber < 0){
}
else if(noteNumber < numberOfNotes()){
System.out.println(notes.get(noteNumber));
}
else {
}
}

public void listNotesbasic(){
int index = 0;
while(index < notes.size()){
System.out.println(notes.get(index));
index++;
}
}

public void listNotesV2(){
Iterator it = notes.iterator();
while(it.hasNext()){
System.out.println(it.next());
}
}

public void removeNote(int noteNumber)
{
if(noteNumber < 0){
}
else if(noteNumber < numberOfNotes()){
notes.remove(noteNumber);
}
else {
}
}

}