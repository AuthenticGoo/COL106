import java.util.*;

public class MyHashTable{
    MyLinkedList<WordEntry> arr[]= new MyLinkedList[100];  //MIGHT GIVE NULL POINTER
    public MyLinkedList<String> wordList;
    
    public MyHashTable(){
        for(int i=0;i<100;i++){
            arr[i]=new MyLinkedList();
        }
        wordList= new MyLinkedList();
    }
    
    private int getHashIndex(String str){
        
        int hash = 7;
        for (int i = 0; i < str.length(); i++) {
            hash = hash*11 + str.charAt(i);
        }
        hash=hash%100;
        
        return hash;
    }
    
    void addPositionsForWord(WordEntry w){
        String wrd= w.getWord();
        
        int x= this.getHashIndex(w.getWord());
        
        for(int i=1;i<=this.arr[x].getSize();i++){
            if(this.arr[x].getAt(i).getWord()==wrd){
                WordEntry we=this.arr[x].getAt(i);
                MyLinkedList<Position> pos=w.getAllPositionsForThisWord();
                we.addPositions(pos);
                return;
            }
        }
        
        this.wordList.addFirst(wrd);
        this.arr[x].addFirst(w);        
        return;
    }
}        