public class ExchangeList{
    LinkedList el;
    
    public ExchangeList(){
        el= new LinkedList();
    }
    
    public int size(){
        return el.getSize();
    }
    
    public void addFirst(Exchange e){
        el.addFirst(e);
    }
    
    public Exchange deleteFirst(){
        Exchange e1=(Exchange)el.deleteFirst();
        return e1;
    }
    
    public Exchange exAt(int i){
       Exchange temp= (Exchange)el.getAt(i);
       return temp ;
    }
    
    public Boolean findData(Exchange e){
        return el.findData(e);
    }
    
    public void delete(Exchange e){
        el.delete(e);
    }
}