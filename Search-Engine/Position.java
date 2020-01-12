public class Position{
    public PageEntry pe;
    public int i;
    public int ri;
    
    public Position(PageEntry p, int wordIndex){
        pe=p;
        i=wordIndex;
    }
    
    public Position(PageEntry p, int wordIndex, int relative){
        pe=p;
        i=wordIndex;
        ri= relative;
    }
    
    public PageEntry getPageEntry(){
        return this.pe;
    }
    
    public int getWordIndex(){
        return this.i;
    }
    
    public int getWordIndexRel(){
        return this.ri;
    }
}