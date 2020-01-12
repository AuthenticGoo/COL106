public class InvertedPageIndex{
    MyLinkedList<PageEntry> pageList;
    MyLinkedList<String> pageNameList;
    
    public InvertedPageIndex(){
        pageList= new MyLinkedList();
        pageNameList= new MyLinkedList();
    }

    public void addPage(PageEntry p){
        if(!pageList.findData(p)){
            p.idx=this;
            pageList.addFirst(p);
            pageNameList.addFirst(p.pageName);
        }
    }
    
    public MySet<PageEntry> getPagesWhichContainWord(String str){
        MySet<PageEntry> pset= new MySet();
        
        for(int i=1;i<=pageList.getSize();i++){
            Boolean b=false;
            MyLinkedList<String> x= pageList.getAt(i).wordex.wordList;
            for(int j=1; j<=x.getSize();j++){
                if(x.getAt(j).equals(str)){
                    b=true;}
            }
            if(b){
                pset.Insert(pageList.getAt(i));
            }
        }
        
        return pset;
    }
    
   
    public MySet<PageEntry> getPagesWhichContainAllWords(String[] str){
        MySet<PageEntry> pset= getPagesWhichContainWord(str[0]);
        
        for(int i=1; i<str.length; i++){
            pset= pset.Intersection(getPagesWhichContainWord(str[i]));
        }
        
        return pset;
    }
    
    public MySet<PageEntry> getPagesWhichContainAnyOfTheseWords(String[] str){
        MySet<PageEntry> pset= getPagesWhichContainWord(str[0]);
        
        for(int i=1; i<str.length; i++){
            pset= pset.Union(getPagesWhichContainWord(str[i]));
        }
        
        return pset;
    }
        
    public MySet<PageEntry> getPagesWhichContainPhrase(String[] str){
        MySet<PageEntry> pset= new MySet<PageEntry>();
        
        for(int i=1; i<=pageList.getSize(); i++){
            if(pageList.getAt(i).getPhraseFreq(str)!=0){
                pset.Insert(pageList.getAt(i));
            }
        }
        return pset;
    }
      
    public PageEntry getPage(String pgn){
        int i= pageNameList.getIndex(pgn);
        return pageList.getAt(i);       
    }
        
    public Boolean isAdded(String pn){
        Boolean b=false;
        
        for(int j=1; j<=pageNameList.getSize();j++){
                if(pageNameList.getAt(j).equals(pn)){
                    b=true;}
            }
        return b;
    }
}
 