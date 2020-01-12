import java.util.*;
import java.lang.*;

public class RoutingMapTree{
    private Exchange root;
    //public LinkedList offSet= new LinkedList();
    
    
    public RoutingMapTree(){
        root= new Exchange(0);
    }
    
    public RoutingMapTree(Exchange e){
        root= e;
    }
    
    public Exchange getRoot(){
        return root;
    }
    
    public boolean isExternal(Exchange e){
        if(e.numChildren()==0)
        return true;
        return false;
    }
    
    public Boolean isEmpty(){
        if(root==null)
        return true;
        return false;
    }
    
    public Exchange giveParent(Exchange e){
        return e.parent();
    }
    
    public Exchange getEx(int i){
        if(this.root.getn()==i)
        return this.root;
        
        if(this.isExternal(root))
        return null;
        
        Exchange temp= new Exchange();
        
        for(int j=1; j<=this.getRoot().numChildren();j++){
            temp=this.getRoot().subtree(j).getEx(i) ;
            
            if(temp!=null)
            break;
        }
        
        //if(temp==null && this.root.getn()==0)
        //throw new NoSuchElementException("Error- Exchange Not Found");
        
        return temp;
    }
    
    public void addExchange(int a, int b)throws AlreadyAChildException{
        Exchange Ex1= this.getEx(a);
        if(Ex1==null){
            throw new NoSuchElementException();
        }
        Exchange Ex2= new Exchange(b);
        for(int i=1;i<=Ex1.numChildren();i++){
            if(Ex2==Ex1.child(i)){
                throw new AlreadyAChildException();
            }
        }
        Ex1.addChild(Ex2);
    }
    
    public void switchOnMobile(int a, int b)throws ExchangeBNotFoundException, ExchangeNotBaseStationException{
        Exchange x= getEx(b);
        
        if(x==null){
            throw new ExchangeBNotFoundException();
        }
        
        if(!this.isExternal(x)){
            throw new ExchangeNotBaseStationException();
        }
        
        /*if(offSet.findData(a))
        offSet.delete(a);*/
        
        if(x.findPhone(a)!=null){
            if(x.findPhone(a).status()==true){
                return;
            }
            else{
                while(!x.isRoot()){
                    x.findPhone(a).switchOn();
                    x=x.parent();
                }
                x.findPhone(a).switchOn();
            }
        }
        else{
            MobilePhone m= new MobilePhone(a,x);
            m.switchOn();
            
            while(!x.isRoot()){
                x.addPhone(m);
                x=x.parent();
            }
            
            x.addPhone(m);
        }
    }
    
    public void switchOffMobile(int a)throws PhoneNotFoundException{
        
        /*if(offSet.findData(a))
        return;*/
        
        if(this.root.findPhone(a)==null && this.root.getn()==0/* && !offSet.findData(a)*/){
            throw new PhoneNotFoundException();
        }
        //else if(this.root.findPhone(a)==null && this.root.getn()!=0){
        //    return;
        //}
        //offSet.addFirst(a);
        MobilePhone m= this.root.findPhone(a);
        /*if(!m.status()){
            //exep
        }*/
        if(m.status()==true){
            Exchange e= m.location();
            
            while(!e.isRoot()){
                e.delPhone(m);
                e=e.parent();
            }
            
            e.delPhone(m);
            m.switchOff();
            
        }   
    }
        
    public MobilePhoneSet queryMobilePhoneSet(int a){
        Exchange e= this.getEx(a);
        if(e==null){
            throw new NoSuchElementException();
        }
        return e.residentSet();
    }
    
    public int queryNthChild(int a, int b)throws NoBChildException{
        try{
            Exchange e= this.getEx(a);
            if(e==null){
                throw new NoSuchElementException();
            }
            return e.child(e.numChildren()-b).getn();
        }
        catch(IndexOutOfBoundsException e){
            throw new NoBChildException();
        }
    }
    
    public Exchange findPhone(MobilePhone m)throws PhoneNotFoundException, SwitchedOffException{
        int a= m.number();
        
        /*if(offSet.findData(m)){
            String sa= Integer.toString(a);
            throw new SwitchedOffException(sa);
        }*/
        
        if(this.root.findPhone(a)!=null && this.root.numChildren()==0){
            return this.root;
        }
        if(this.root.findPhone(a)==null && this.root.getn()==0/* && !offSet.findData(m)*/){            
            throw new PhoneNotFoundException();
        }
        else if(this.root.findPhone(a)==null && this.root.getn()!=0){
            return null;
        }
        Exchange temp=null;
        for(int j=1; j<=this.getRoot().numChildren();j++){
            temp=this.getRoot().subtree(j).findPhone(m) ;
            
            if(temp!=null)
            break;
        }
        RoutingMapTree r= new RoutingMapTree(temp);
        return r.findPhone(m);
    }
    
    public Exchange lowestRouter(Exchange a, Exchange b){
        if(a==b)
        return a;
        
        Exchange temp1= a.parent();
        Exchange temp2= b.parent();
        
        while(temp1!=root){
            while(temp2!=root){
                if(temp1==temp2)
                return temp1;
                temp2=temp2.parent();
            }
            temp1=temp1.parent();
        }
        
        /*while(temp!=null){
            if(temp.children().findData(b)==true)
            break;
            temp=temp.parent();
        }*/
        
        return root;
    }
    
    public ExchangeList routeCall(MobilePhone a, MobilePhone b)throws PhoneNotFoundException, SwitchedOffException{
        try{
        Exchange Ex1= this.findPhone(a);}
        catch(PhoneNotFoundException e){
            String sa= Integer.toString(a.number());
            throw new SwitchedOffException(sa);
        }
        
        try{
        Exchange Ex2= this.findPhone(b);}
        catch(PhoneNotFoundException e){
            String sb= Integer.toString(b.number());
            throw new SwitchedOffException(sb);
        }
        
        Exchange Ex1= this.findPhone(a);
        Exchange Ex2= this.findPhone(b);
        Exchange Ex= this.lowestRouter(Ex1, Ex2);
        ExchangeList e= new ExchangeList();
        ExchangeList h= new ExchangeList();
        
        Exchange temp;
        temp= Ex2;
        while(temp!=Ex){
            e.addFirst(temp);
            temp=temp.parent();
        }
        e.addFirst(temp);
        temp=Ex1;
        while(temp!=Ex){
            h.addFirst(temp);
            temp=temp.parent();
        }
        while(h.size()!=0){
            e.addFirst(h.deleteFirst());
        }
        return e;
    }
    
    public void movePhone(MobilePhone a, Exchange b)throws PhoneNotFoundException, ExchangeBNotFoundException, ExchangeNotBaseStationException{
        switchOffMobile(a.number());
        switchOnMobile(a.number(),b.getn());
    }
        
    public String performAction(String actionMessage){
            String ans= new String();
            String s[]= actionMessage.split(" ");
            
            String p= s[0];
            int a= Integer.parseInt(s[1]);
            int b= 0;
            
            if(!s[0].equals("queryMobilePhoneSet") && !s[0].equals("switchOffMobile") && !s[0].equals("findPhone")){
                b= Integer.parseInt(s[2]);
            }
        
        try{  
            if(s[0].equals("addExchange")){
                this.addExchange(a,b);
            }
        
            else if(s[0].equals("switchOnMobile")){
                this.switchOnMobile(a,b);
            }
        
            else if(s[0].equals("switchOffMobile")){
                this.switchOffMobile(a);
            }
        
            else if(s[0].equals("queryNthChild")){
                ans= ans+"queryNthChild "+a+" "+b+": "+this.queryNthChild(a,b);
            }
        
            else if(s[0].equals("queryMobilePhoneSet")){
                MobilePhoneSet mset= this.queryMobilePhoneSet(a);
                if(mset.IsEmpty()){
                    throw new EmptyPhoneSetException();
                }
                ans=ans+"queryMobilePhoneSet "+a+": ";
                for(int i=1; i<mset.size(); i++){
                    ans=ans+mset.get(i).number()+", ";
                }
                ans=ans+mset.get(mset.size()).number();
            }
            
            else if(s[0].equals("findPhone")){
                MobilePhone m1= new MobilePhone(a);
                Exchange Ex1= findPhone(m1);
                ans=ans+ "queryFindPhone "+a+": "+Ex1.getn();
            }
            
            else if(s[0].equals("lowestRouter")){
                Exchange Ex1= this.getEx(a);
                Exchange Ex2= this.getEx(b);
                Exchange low= lowestRouter(Ex1,Ex2);
                ans=ans+"queryLowestRouter "+a+" "+b+": "+low.getn();
            }
            
            else if(s[0].equals("findCallPath")){
                MobilePhone m1= new MobilePhone(a);
                MobilePhone m2= new MobilePhone(b);
                ExchangeList el= routeCall(m1, m2);
                ans=ans+"queryFindCallPath "+a+" "+b+": ";
                for(int i=1; i<el.size();i++){
                    ans=ans+el.exAt(i).getn()+", ";
                }
                ans=ans+el.exAt(el.size()).getn();
            }
            
            else if(s[0].equals("movePhone")){
                MobilePhone m1= new MobilePhone(a);
                Exchange e2= new Exchange(b);
                movePhone(m1,e2);
            }
            
            return ans;
        }
        catch(NoSuchElementException e){
            String q="queryNthChild "+a+" "+b+": "+"Error- Exchange Not Found";
            return q;        
        }
        catch(NoBChildException e){
            String q="queryNthChild "+a+" "+b+": "+"Error - No "+b+" child of Exchange "+a;
            return q;        
        }
        catch(ExchangeBNotFoundException e){
            String q="switchOnMobile "+a+" "+b+": "+"Error- No exchange with identifier "+b;
            return q;        
        }
        catch(ExchangeNotBaseStationException e){
            String q="switchOnMobile "+a+" "+b+": "+"Error- Cannot add phone as Exchange is not base station";
            return q;        
        }
        catch(AlreadyAChildException e){
            String q="addExchange "+a+" "+b+": "+"Error- Exchange with id "+a+" already has a child with id "+b;
            return q;        
        }
        catch(PhoneNotFoundException e){
            String q="queryFindPhone "+a+": "+"Error - No mobile phone with identifier "+a+" found in the network";
            return q;        
        }
        catch(EmptyPhoneSetException e){
            String q="queryMobilePhoneSet "+a+": "+"Resident Set of Exchange"+ a +"is empty";
            return q;        
        }
        catch(SwitchedOffException e){            
            String q="queryFindCallPath "+a+" "+b+": "+"Error - Mobile phone with identifier "+e.getMessage()+" is currently switched off";
            return q;
        }
        
     }
     
    /* public static void mail(String[] args)throws PhoneNotFoundException, SwitchedOffException{
         RoutingMapTree r = new RoutingMapTree();
         r.performAction("addExchange 0 1");
         r.performAction("addExchange 0 2");
         r.performAction("addExchange 0 3");
         r.performAction("addExchange 1 4");
         r.performAction("addExchange 1 5");
         r.performAction("addExchange 2 6");
         r.performAction("addExchange 2 7");
         r.performAction("addExchange 2 8");
         r.performAction("addExchange 3 9");
         /*r.performAction("switchOnMobile 989 4");
         r.performAction("switchOnMobile 876 4");
         r.performAction("switchOnMobile 656 5");
         r.performAction("switchOnMobile 54 5");
         r.performAction("switchOffMobile 656");
         //r.performAction("movePhone 989 7");
         r.performAction("switchOffMobile 989");
         
         System.out.println(r.performAction("findPhone 989"));
         
        }*/
    }   