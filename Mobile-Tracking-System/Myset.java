import java.lang.*;
import java.util.*;

public class Myset{
    public LinkedList ll;
    
    public Myset(){
        ll=new LinkedList();
    }
        
    public Boolean IsEmpty(){
        if(ll.getSize()==0)
        return true;
        return false;
    }
    
    public int size(){
        return ll.getSize();
    }
    
    public Boolean IsMember(Object o){
        return ll.findData(o);
    }
    
    public void Insert(Object o){
        if(ll.findData(o)==false)
        ll.addFirst(o);
        else
        return;
    }
    
    public void Delete(Object o){
        if(ll.findData(o)==false){
            throw new NoSuchElementException("Object not found");
        }
        else
        ll.delete(o);
    }
    
    public Object get(int i){
        return ll.getAt(ll.getSize()-i+1);
    }
    
    public Myset Union(Myset a){
        Myset x= a;
        LinkedList ll2= ll;
        while(ll2.getSize()!=0){
            x.Insert(ll2.deleteFirst());
        }
        return x;
    }
    
    public Myset Intersection(Myset a){
        Myset x= new Myset();
        LinkedList ll2= ll;
        while(ll2.getSize()!=0){
            Object o= ll2.deleteFirst();
            if(a.IsMember(o))
            x.Insert(o);
        }
        return x;
    }
}