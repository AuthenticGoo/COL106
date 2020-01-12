import java.io.*;
import java.util.*;

public class SearchEngine{
    InvertedPageIndex ind;
    
    public SearchEngine(){
        ind= new InvertedPageIndex();
    }
    
    public void performAction(String actionMessage){
        String ans= new String();
        String s[]= actionMessage.split(" ");
            
        String p= s[0];
        String x= s[1];
        String y= "";
        if(s[0].equals("queryFindPositionsOfWordInAPage")){
            y= s[2];
        }
        
        
            if(s[0].equals("addPage")){
                PageEntry pen= new PageEntry(x);
                ind.addPage(pen);
            }
        
            else if(s[0].equals("queryFindPagesWhichContainWord")){
                x=x.toLowerCase();
                MySet<PageEntry> ps= ind.getPagesWhichContainWord(x);
                //System.out.println(ps.size());                              //temp
                
                if(ps.size()==0){
                    ans=ans+"No webpage contains word "+x;
                    System.out.println(ans);
                    return;
                }
                
                for(int i=1; i<ps.size();i++){
                    ans=ans+ps.get(i).pageName+", ";            //changed
                }
                ans=ans+ps.get(ps.size()).pageName;
                System.out.println(ans);
            }
            
            else if(s[0].equals("queryFindPositionsOfWordInAPage")){
                if(!ind.isAdded(y)){
                    ans=ans+"No webpage "+y+" found";
                    System.out.println(ans);
                }
                else{
                    PageEntry pen= ind.getPage(y);
                    
                    Boolean b=false;
                    for(int j=1; j<=pen.wordex.wordList.getSize();j++){
                        if(pen.wordex.wordList.getAt(j).equals(x)){
                        b=true;}
                    }
                    
                    if(!b){
                        
                        ans=ans+"Webpage "+y+" does not contain word "+x;
                        System.out.println(ans);
                    }
                    else{
                        WordEntry wen= pen.getPageIndex().getWordEntry(x);
                        /*for(int j=1; j<=pen.getPageIndex().getWordEntries().getSize();j++){
                            System.out.println(pen.getPageIndex().getWordEntries().getAt(j).getWord());
                        }*/
                                             
                        MyLinkedList<Position> ps=wen.getAllPositionsForThisWord();
                        for(int i=1; i<ps.getSize();i++){
                            ans=ans+ps.getAt(i).getWordIndex()+", ";                             //changed
                        }
                        ans=ans+ps.getAt(ps.getSize()).getWordIndex();
                        System.out.println(ans);
                    }
                }
            }
            /*else if(s[0].equals("queryFindPagesWhichContainAllWords")){
                String[] str=new String[s.length-1];
                for (int i = 0; i < str.length; i++) {
                    if (s[i].equals("stacks")) {
                        s[i] = "stack";
                    } 
                    else if (s[i].equals("structures")) {
                        s[i] = "structure";
                    } 
                    else if (s[i].equals("applications")) {
                        s[i] = "application";
                    }
                    str[i] = s[i + 1];
                }
                MySet<PageEntry> pen= ind.getPagesWhichContainAllWords(str);
                MySet<SearchResult> res=new MySet<SearchResult>();
                
                for(int i=1; i<=pen.size(); i++){
                    SearchResult sr= new SearchResult(pen.get(i), pen.get(i).getRelevanceOfPage(str,false));
                    res.Insert(sr);
                }
                
                MySort<SearchResult> srt= new MySort<SearchResult>();
                ArrayList<SearchResult> al= srt.sortThisList(res);
                
                for(int i=0; i<al.size()-1; i++){
                    ans=ans+al.get(i).getPageEntry().pageName+", ";
                }
                ans=ans+al.get(al.size()-1).getPageEntry().pageName;
                System.out.println(ans);
            }*/
            
            else{
                String[] str=new String[s.length-1];
                for (int i = 0; i < str.length; i++) {
                    if (s[i].equals("stacks")) {
                        s[i] = "stack";
                    } 
                    else if (s[i].equals("structures")) {
                        s[i] = "structure";
                    } 
                    else if (s[i].equals("applications")) {
                        s[i] = "application";
                    }
                    str[i] = s[i + 1];
                }
                
                MySet<PageEntry> pen= new MySet<PageEntry>();
                
                if(s[0].equals("queryFindPagesWhichContainAllWords")){
                    pen= ind.getPagesWhichContainAllWords(str);
                    if(pen.size()==0){
                        ans=ans+"No webpage contains all words "+str[0];
                        for(int j=1; j<str.length; j++){
                            ans=ans+", "+str[j];
                        }
                        System.out.println(ans);
                        return;
                    }                
                }
                else if(s[0].equals("queryFindPagesWhichContainAnyOfTheseWords")){
                    pen= ind.getPagesWhichContainAnyOfTheseWords(str);
                }
                else if(s[0].equals("queryFindPagesWhichContainPhrase")){
                    pen= ind.getPagesWhichContainPhrase(str);
                    if(pen.size()==0){
                        ans=ans+"No webpage contains phrase "+str[0];
                        for(int j=1; j<str.length; j++){
                            ans=ans+" "+str[j];
                        }
                        System.out.println(ans);
                        return;
                    }
                }
                
                MySet<SearchResult> res=new MySet<SearchResult>();
                
                if(s[0].equals("queryFindPagesWhichContainPhrase")){
                    for(int i=1; i<=pen.size(); i++){
                        SearchResult sr= new SearchResult(pen.get(i), pen.get(i).getRelevanceOfPage(str,true));
                        res.Insert(sr);
                    }
                }
                else{
                    for(int i=1; i<=pen.size(); i++){
                        SearchResult sr= new SearchResult(pen.get(i), pen.get(i).getRelevanceOfPage(str,false));
                        res.Insert(sr);
                    }
                }
                
                /*for(int i=1; i<=res.size(); i++){
                    System.out.println(res.get(i).getPageEntry().pageName+" ");
                }*/
                
                MySort<SearchResult> srt= new MySort<SearchResult>();
                ArrayList<SearchResult> al= srt.sortThisList(res);
                
                for(int i=0; i<al.size()-1; i++){
                    ans=ans+al.get(i).getPageEntry().pageName+", ";
                }
                
                ans=ans+al.get(al.size()-1).getPageEntry().pageName;
                System.out.println(ans);
            }
            
        }
        
        /* public static void main(String[] args){
            SearchEngine se= new SearchEngine();
            
            //se.performAction("addPage cmu_stack");
            se.performAction("addPage stacks_and_queues");
            //se.performAction("addPage random.txt");
            
            //PageIndex pd= new PageEntry("stack_datastructure_wiki").getPageIndex();
            
            /*for(int i=1;i<=pd.getWordEntries().getSize();i++){
                System.out.println(pd.getWordEntries().getAt(i).getWord());
            }*
            
         
            
            se.performAction("queryFindPagesWhichContainPhrase data structure");
            System.out.println("ok");
            
        } */
    }
    
        
        
                
                
                
        
            