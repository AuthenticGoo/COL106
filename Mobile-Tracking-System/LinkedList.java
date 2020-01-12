import java.util.*;
import java.lang.*;

public class LinkedList{
    
    Node Head;
    private int size;
    
    public LinkedList(){
        Head=null;
        size=0;
    }
    
    public LinkedList(Node head){
        this.Head=head;
        size=1;
    }
	
    public int getSize(){
        return size;
    }
    
    public Object getAt(int i){
        if(i>size){
            throw new IndexOutOfBoundsException("Index out of bound");
        }
        
        Node temp= Head;
        for(int j=1; j<i; j++){
            temp= temp.getNext();
        }
        return temp.getData();
    }
    
    public void addFirst(Object o){
        Head=new Node(o, Head);
        size++;
    }
    
    public Object deleteFirst(){
        if(Head==null){
            throw new IllegalStateException("LinkedList is empty.");
        }
        Object o= Head.getData();
        Head=Head.getNext();
        size--;
        return o;
    }
    
    public Boolean findData(Object o){
        Node tmp= Head;
        
        while(tmp!=null){
            if(tmp.getData()==o)
            return true;
            else
            tmp=tmp.getNext();
        }
        return false;
    }
    
    public void delete(Object o){
        if(Head==null)
        return;
        
        if(Head.getData()==o){
            Head=Head.getNext();
            size--;
            return;
        }
        
        Node temp= Head;
        while(temp.getNext().getData()!=o){
            temp=temp.getNext();
        }
        temp.setNext(temp.getNext().getNext());
        size--;
    }
    
    class Node{
        private Object data;
        private Node next;
        
        public Node(Object o, Node n){
            data=o;
            next=n;
        }
        
        public Node getNext(){
            return next;
        }
        
        public Object getData(){
            return data;
        }
        
        public void setNext(Node n){
            next=n;
        }
        
        public void setData(Object o){
            data=o;
        }
    }
}

