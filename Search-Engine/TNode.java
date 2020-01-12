public class TNode{
    public AVLTree lst, rst;
    Position pos;
    
    public TNode(){
        lst= new AVLTree();
        rst= new AVLTree();
        pos= null;
    }
    
    public TNode(Position x){
        lst= new AVLTree();
        rst= new AVLTree();
        pos= x;
    }
    
    public Position getPos() {
        return pos;
    }
}
        