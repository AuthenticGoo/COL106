import java.util.*;

public class AVLTree{
    public TNode root;
    public int h;
    
    public AVLTree(){
        root= null;
        h=0;
    }
    
    public AVLTree(TNode t){
        root= t;
        h=Integer.max(t.lst.h,t.rst.h)+1;
    }
    
    public boolean isEmpty(){
        if(root==null)
        return true;
        return false;
    }
    
    public void insert(Position p){
        if(this.isEmpty()){
            this.root=new TNode(p);
        }
        else{
            if(p.getWordIndex()<this.root.getPos().getWordIndex()){
                this.root.lst.insert(p);
                if(root.lst.h-root.rst.h==2){
                    if(p.getWordIndex()<root.lst.root.getPos().getWordIndex()){
                        this.lbalance();
                    }
                    else{
                        root.lst.rbalance();
                        this.lbalance();
                    }
                }
            }
            else if(p.getWordIndex()>this.root.getPos().getWordIndex()){
                this.root.rst.insert(p);
                if(root.rst.h-root.lst.h==2){
                    if(p.getWordIndex()>root.rst.root.getPos().getWordIndex()){
                        this.rbalance();
                    }
                    else{
                        root.rst.lbalance();
                        this.rbalance();
                    }
                }
            }
        }
        this.h=Integer.max(root.lst.h,root.rst.h)+1;
    }
            
    public void lbalance(){               //CHANGED
        AVLTree l= this.root.lst;
        this.root.lst=l.root.rst;
        l.root.rst=this;
        this.h=Integer.max(root.lst.h,root.rst.h)+1;
        l.h=Integer.max(l.root.lst.h,l.root.rst.h)+1;
        AVLTree temp=new AVLTree();
        temp.root= l.root.rst.root;
        temp.h=l.root.rst.h;
        this.root=l.root;
        this.root.rst=temp;
        this.h=l.h;
    }
    
    public void rbalance(){
        AVLTree r= this.root.rst;
        this.root.rst=r.root.lst;
        //System.out.println(this.root.getPos().getWordIndex());
        r.root.lst=this;
        this.h=Integer.max(root.lst.h,root.rst.h)+1;
        r.h=Integer.max(r.root.lst.h,r.root.rst.h)+1;
        //System.out.println(r.root.lst.root.getPos().getWordIndex());
        AVLTree temp=new AVLTree();
        temp.root= r.root.lst.root;
        temp.h=r.root.lst.h;
        this.root=r.root;
        this.root.lst=temp;
        this.h=r.h;
        //System.out.println(this.h);
        //System.out.println(this.root.lst.h);
    }
    
    public boolean findPos(Position p){
        if(this.isEmpty()){
            return false;
        }
        else{
            if(this.root.getPos().getWordIndex()==p.getWordIndex()){
                return true;
            }
            else if(this.root.getPos().getWordIndex()>p.getWordIndex()){
                return this.root.lst.findPos(p);
            }
            else {
                return this.root.rst.findPos(p);
            }
        }
    }
    
    public boolean findPosRel(int relp){
        if(this.isEmpty()){
            return false;
        }
        else{
            if(this.root.getPos().getWordIndexRel()==relp){
                return true;
            }
            else if(this.root.getPos().getWordIndexRel()>relp){
                return this.root.lst.findPosRel(relp);
            }
            else {
                return this.root.rst.findPosRel(relp);
            }
        }
    }
    
    
}     
        
        