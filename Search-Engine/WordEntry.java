public class WordEntry{
    String word;
    MyLinkedList<Position> positions;
    int freq=0;
    public AVLTree ptree;
    
    public WordEntry(){
        freq=0;
        positions= new MyLinkedList();
        ptree= new AVLTree();
    }
    
    public WordEntry(String w){
        this.word=w;
        positions= new MyLinkedList();
        this.freq=0;
        ptree= new AVLTree();
    }
    
    public String getWord(){
        return this.word;
    }
    
    public void addPosition(Position p){
        positions.addFirst(p);
        ptree.insert(p);
        freq++;
    }
    
    public void addPositions(MyLinkedList<Position> ps){
        Position temp;
        while(ps.getSize()!=0){
            temp=ps.deleteFirst();
            positions.addFirst(temp);
            ptree.insert(temp);
            freq++;
        }
    }
    
    public MyLinkedList<Position> getAllPositionsForThisWord(){
        return positions;
    }
    
    /*public float getTermFrequency(String word){                      //CHANGE THIS
        return freq;
    }*/
}