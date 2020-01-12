public class Exchange{
    private ExchangeList children; 
    private int n;
    private Exchange parent;
    private MobilePhoneSet phones;
    
    public Exchange(){
        parent= null;
        children=new ExchangeList();
    }
    
    public Exchange(int number){
        n=number;
        children= new ExchangeList();
        parent= null;
        phones= new MobilePhoneSet();
    }
    
    public ExchangeList children(){
        return this.children;
    }
    
    public Exchange parent(){
        return parent;
    }
    
    public void setParent(Exchange parent){
        this.parent=parent;
    }
    
    public int numChildren(){
        return children.size();
    }
    
    public Exchange child(int i){
        return this.children.exAt(i);
    }
    
    public void addChild(Exchange c){
        children.addFirst(c);
        c.setParent(this);
        this.phones.Union(c.phones);
    }
    
    public void addPhone(MobilePhone m){
        phones.Insert(m);
    }
    
    public void delPhone(MobilePhone o){
        phones.Delete(o);
    }
    
    public int getn(){
        return n;
    }
    
    public Boolean isRoot(){
        if(this.parent==null)
        return true;
        return false;
    }
    
    public MobilePhoneSet residentSet(){
        return phones;
    }
    
    public RoutingMapTree subtree(int i){
        RoutingMapTree r= new RoutingMapTree(this.child(i));
        return r;
    }
    
    public MobilePhone findPhone(int a){
        for(int i=1; i<=phones.size();i++){
            if(phones.get(i).number()==a)
            return phones.get(i);
        }
        return null;
    }
    
    
        
}
