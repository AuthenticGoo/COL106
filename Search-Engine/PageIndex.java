public class PageIndex{
    MyLinkedList<WordEntry> pi;
    public MyLinkedList<String> wordList;
    
    public PageIndex(){
        pi= new MyLinkedList();
        wordList= new MyLinkedList();
    }
    
    public void addPositionForWord(String str, Position p){
        Node<WordEntry> temp;
        temp= pi.Head;
        
        while(temp!=null){
            if(temp.getData().getWord().equals(str)){
                WordEntry w= temp.getData();
                w.addPosition(p);
                return;
            }
            temp=temp.getNext();
        }
        
        WordEntry w= new WordEntry(str);
        w.addPosition(p);
        pi.addFirst(w);
        wordList.addFirst(str);
        return;
    }
    
    public MyLinkedList<WordEntry> getWordEntries(){
        return pi;
    }
    
    public WordEntry getWordEntry(String wrd){
        WordEntry w= new WordEntry();
        for(int i=1;i<=pi.getSize();i++){
            if(pi.getAt(i).getWord().equals(wrd)){
                return pi.getAt(i);
            }            
        }
        return w;
    }
}
            