import java.util.*;
import java.io.*;
import java.lang.*;

public class PageEntry{
    PageIndex wordex= new PageIndex();
    public String pageName;
    //public MyHashTable wordMap;
    public int wc=0;
    public InvertedPageIndex idx;
    String spage= "";
    
    public PageEntry(String pn){
        String page="";
        try{
            FileInputStream fstream= new FileInputStream(pn);
            Scanner s= new Scanner(fstream);
        
            while (s.hasNextLine()){
                page=page+s.nextLine()+" ";
            }     
            page = page.substring(0, page.length()-1);
        }catch(FileNotFoundException e){
        }
        
        page=page.replaceAll("/", " ");                      //error?
        page=page.replaceAll("-", " ");  
        
        String[] w= page.split("\\s+");
        for (int i = 0; i < w.length; i++) {
            //w[i] = w[i].replace("] [ < > = ( ) } . , ; { ’ ” ? # ! - :", "");
            w[i] = w[i].replace("{", "");
            w[i] = w[i].replace("}", "");
            w[i] = w[i].replace("[", "");
            w[i] = w[i].replace("]", "");
            w[i] = w[i].replace("<", "");
            w[i] = w[i].replace(">", "");
            w[i] = w[i].replace("=", "");
            w[i] = w[i].replace("(", "");
            w[i] = w[i].replace(")", "");
            w[i] = w[i].replace(".", "");
            w[i] = w[i].replace(",", "");
            w[i] = w[i].replace(";", "");
            w[i] = w[i].replace("'", "");
            w[i] = w[i].replace("\"", "");
            w[i] = w[i].replace("?", "");
            w[i] = w[i].replace("#", "");
            w[i] = w[i].replace("!", "");
            w[i] = w[i].replace("-", "");
            w[i] = w[i].replace(":", "");
            if(w[i].equals("stacks")){
                w[i]="stack";
            }
            if(w[i].equals("structures")){
                w[i]="structure";
            }
            if(w[i].equals("applications")){
                w[i]="application";
            }
            
           
            w[i]=w[i].toLowerCase();
        }
        
        //String[] connectors= {"{", "}", "[", "]", "<", ">", "=", "(", ")", ".", ",", ";", "’", "”", "?", "#", "!", "-", ":"};
        
        String[] connectors= { "a", "an", "the", "they", "these", "this", "for", "is", "are", "was", "of", "or", "and", "does", "will", "whose" };
        int k=1;
        
        for(int i=0;i<w.length;i++){
            Boolean b= true;
            for(int j=0;j<connectors.length;j++){
                if(w[i].equals(connectors[j])){
                    b=false;
                    break;
                }
            }
            
            if(b){                
                Position p= new Position(this,i+1,k);                     //temp
                wordex.addPositionForWord(w[i], p);
                spage=spage+w[i]+" ";
                k++;
            }
        }
        
        MyLinkedList<WordEntry> temp= wordex.getWordEntries();
        
        /*while(temp.getSize()!=0){
            wordMap.addPositionsForWord(temp.deleteFirst());
        }*/
        
        wc=w.length;
        pageName= pn;
    }
    
    /*public int getPhraseFreq(String str[]){
        int freq=0;
        String phrase="";
        for(int i=0; i<str.length-1; i++){
            phrase= phrase+ str[i]+ " ";
        }
        phrase=phrase+ str[str.length-1];
        
        int indx=0;
        
        while(indx!=-1){
            indx= spage.indexOf(phrase,indx);
        
            if(indx!=-1){
                freq++;
                indx=indx+1;
            }
        }
        
        return freq;
    }*/        
    
    public int getPhraseFreq(String str[]){
        int freq=0;
        boolean phraseExists=true;
        MyLinkedList<Position> p0=wordex.getWordEntry(str[0]).getAllPositionsForThisWord();
        //System.out.println(pageName+p0.getSize()); 
        
        
                    
        for(int i=1; i<=p0.getSize(); i++, phraseExists=true){
            
            for(int j=1; j<str.length; j++){
                
                if(!wordex.getWordEntry(str[j]).ptree.findPosRel(p0.getAt(i).ri+j)){
                    /*System.out.println(p0.getAt(i).ri+j);
                    MyLinkedList<Position> ten= wordex.getWordEntry(str[j]).getAllPositionsForThisWord();
                    for(int m=1; m<=ten.getSize(); m++){
                        System.out.println(ten.getAt(m).ri);
                    }*/
                    phraseExists=false;
                }
                if(phraseExists){
                    freq++;
                }
            }
        }
        //System.out.println(pageName+" "+freq);                          //temp
        return freq;
        
    }
                
    public float getRelevanceOfPage(String str[], boolean doTheseWordsRepresentAPhrase){
        float rel= 0;
        int tf=0;
        
        if(!doTheseWordsRepresentAPhrase){
            for(int i=0; i<str.length; i++){
                //System.out.println(this.pageName+" idf "+str[i]+" "+(float)(Math.log((float)3/idx.getPagesWhichContainWord(str[i]).size())));
                //System.out.println(this.pageName+" wc "+this.wc);
                //System.out.println(this.pageName+" tf "+((float)wordex.getWordEntry(str[i]).freq));
                rel= rel+(float)(Math.log((float)12/idx.getPagesWhichContainWord(str[i]).size())*((float)wordex.getWordEntry(str[i]).freq/this.wc));
            } 
            //System.out.println(this.pageName+" rel "+rel);
        }
        else{
            rel=(float)Math.log((float)12/idx.getPagesWhichContainPhrase(str).size())*((float)this.getPhraseFreq(str)/(wc-((str.length-1)*this.getPhraseFreq(str))));
            
        }
        return rel;
    }
        
    public PageIndex getPageIndex(){
        return this.wordex;
    }
    
    public int wordCount(){
        return wc;
    }
       
}