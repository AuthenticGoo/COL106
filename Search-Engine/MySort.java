import java.util.*;

public class MySort<Sortable>{
    ArrayList<SearchResult> sortThisList(MySet<SearchResult> listOfSortableEntries){
        ArrayList<SearchResult> sorted= new ArrayList<SearchResult>();
        
        
        for (int i = 1; i <= listOfSortableEntries.size(); i++) {
            sorted.add(listOfSortableEntries.get(i));
            
        }
        
        Collections.sort(sorted);
        /*for(int i=0; i<sorted.size(); i++){                                                                     //TEMP
            System.out.println(sorted.get(i).getPageEntry().pageName+" "+(float)sorted.get(i).getRelevance());
        }*/
        
        return sorted;
    }
}