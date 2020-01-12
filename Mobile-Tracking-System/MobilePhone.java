public class MobilePhone{
    private int n;
    private Boolean state;
    private Exchange base;
    
    public MobilePhone(int number){
        n=number;
        state=false;
    }
    
    public MobilePhone(int number, Exchange base){
        n= number;
        this.base= base;
    }
    
    public int number(){
        return n;
    }
    
    public Boolean status(){
        return state;
    }
    
    public void switchOn(){
        state=true;
    }
    
    public void switchOff(){
        state=false;
    }
    
    public Exchange location(){
        return this.base;
    }
}