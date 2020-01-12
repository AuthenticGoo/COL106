public class MobilePhoneSet{
    Myset m;
    
    public MobilePhoneSet(){
        m= new Myset();
    }
    
    public Boolean IsEmpty(){
        return m.IsEmpty();
    }
    
    public int size(){
        return m.size();
    }
        
    public Boolean IsMember(MobilePhone o){
        return m.IsMember(o) ;
    }
    
    public void Insert(MobilePhone o){
        m.Insert(o);
    }
    
    public void Delete(MobilePhone o){
        m.Delete(o);
    }
    
    public MobilePhone get(int i){
        return (MobilePhone)m.get(i);
    }
    
    public MobilePhoneSet Union(MobilePhoneSet a){
        MobilePhoneSet x= new MobilePhoneSet();
        x.m= m.Union(a.m);
        return x;
    }
    
    public MobilePhoneSet Intersection(MobilePhoneSet a){
        MobilePhoneSet x= new MobilePhoneSet();
        x.m= this.m.Intersection(a.m);
        return x;
    }
}