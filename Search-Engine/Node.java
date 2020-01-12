public class Node<T>{
        private T data;
        private Node<T> next;
        
        public Node() {
		next=null;
	}
        
	public Node(T o){
            data=o;
        }
        
        public Node(T o, Node<T> n){
            data=o;
            next=n;
        }
        
        public Node<T> getNext(){
            return next;
        }
        
        public T getData(){
            return data;
        }
        
        public void setNext(Node<T> n){
            next=n;
        }
        
        public void setData(T o){
            data=o;
        }
    }