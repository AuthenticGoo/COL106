public class SearchResult implements Comparable<SearchResult>{
    
    PageEntry pe;
    float rev;

    public SearchResult(PageEntry p, float r){
        pe=p;
        rev=r;
    }
    
    public PageEntry getPageEntry(){
        return pe;
    }
    
    public float getRelevance(){
        return rev;
    }
    
    @Override
    public int compareTo(SearchResult otherObject){
        if (otherObject.rev > this.rev) {
            return 1;
        } 
        else if (otherObject.rev == this.rev) {
            return 0;
        } 
        else { 
            return -1;
        }
    }
}