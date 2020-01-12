import java.lang.*;
import java.util.*;

public class MySet<T>{
    
    private MyLinkedList<T> ll;
    
    public MySet() {        
        ll=new MyLinkedList<T>();
    }
    
    public boolean IsEmpty(){
        if(ll.getSize()==0)
        return true;
        return false;
    }
    
    public int size(){
        return ll.getSize();
    }
    
    public Boolean IsMember(T o){
        return ll.findData(o);
    }
    
    public void Insert(T o){
        if(ll.findData(o)==false)
        ll.addFirst(o);
        else
        return;
    }
    
    public T get(int i){         
        return ll.getAt(i);                         //changed
        //return ll.getAt(ll.getSize()-i+1);
    }
    
    public void Delete(T o){
        if(ll.findData(o)==false){
            throw new NoSuchElementException("Object not found");
        }
        else
        ll.delete(o);
    }
    
    public MySet Union(MySet a){
        MySet x= a;
        MyLinkedList<T> ll2= ll;
        while(ll2.getSize()!=0){
            x.Insert(ll2.deleteFirst());
        }
        return x;
    }
    
    public MySet Intersection(MySet a){
        MySet x= new MySet();
        MyLinkedList<T> ll2= ll;
        while(ll2.getSize()!=0){
            T o= ll2.deleteFirst();
            if(a.IsMember(o))
            x.Insert(o);
        }
        return x;
    }        
}
