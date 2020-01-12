import java.util.*;
import java.lang.*;

public class MyLinkedList<T>{
    
    Node<T> Head=null;   //MADE A CHANGE HERE
    private int size;
    
    public MyLinkedList() {
        Head=null;
        size=0;
    }
    
    public MyLinkedList(Node<T> head){    
        this.Head=Head;
        size=1;
    }
    
    public int getSize(){
        return size;
    }
    
    public void addFirst(T data){
        Head=new Node<T>(data, Head);
        size++;
    }
    
    public void delete(T o){
        if(Head==null)
        return;
        
        if(Head.getData()==o){
            Head=Head.getNext();
            size--;
            return;
        }
        
        Node<T> temp= Head;
        while(temp.getNext().getData()!=o){
            temp=temp.getNext();
        }
        temp.setNext(temp.getNext().getNext());
        size--;
    }
    
    public T deleteFirst(){
        if(Head==null){
            throw new IllegalStateException("LinkedList is empty.");
        }
        T o= Head.getData();
        Head=Head.getNext();
        size--;
        return o;
    }
    
    public Boolean findData(T o){
        Node<T> tmp= Head;
        
        while(tmp!=null){
            if(tmp.getData()==o)
            return true;
            else
            tmp=tmp.getNext();
        }
        return false;
    }
    
    public T getAt(int i){
        if(i>size){
            throw new IndexOutOfBoundsException("Index out of bound");
        }
        
        Node<T> temp= Head;
        for(int j=1; j<i; j++){
            temp= temp.getNext();
        }
        return temp.getData();
    }	
    
    public int getIndex(T o){
        Node<T> temp= Head;
        for(int j=1; j<=this.getSize(); j++){
            if(temp.getData()==o){
                return j;
            }
            temp= temp.getNext();
        }
        return 0;
    }
    
    public static void mail(String[] args){
        MyLinkedList<Integer> ll= new MyLinkedList();
        ll.addFirst(4);
        ll.addFirst(5);
        ll.addFirst(6);
        ll.addFirst(7);
        ll.addFirst(8);
        
        System.out.println(ll.getIndex(8));
    }
}