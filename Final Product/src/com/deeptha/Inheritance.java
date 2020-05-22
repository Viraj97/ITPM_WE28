package com.deeptha;

import java.util.*; 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import com.deeptha.Location;
import com.deeptha.Words;

public class Inheritance {
    public ArrayList<Location> getData(String fileName)throws FileNotFoundException, NoSuchElementException {
    	File file = new File(fileName);
        Scanner scan = new Scanner(file);
        final ArrayList<Words> wordList = new ArrayList<Words>();
        final ArrayList<Location> countList = new ArrayList<Location>();
        int lineNum = 0;
        
        while(scan.hasNextLine()){             
            String word = scan.nextLine();
            String[] newArr = word.split("[\\s,]+");
            for(String a: newArr){               
                if(!a.isEmpty()){
                    Words obj = new Words(a,lineNum);
                    wordList.add(obj);                    
                }
            }
            lineNum++;
        }
        int index = wordList.size();
        for(int i=0;i<index;i++){
            if(wordList.get(i).word.equals("extends")){
                int temp = i + 1;
                int pcount = 0;
                if(wordList.get(temp).word.contains("{")){
                    pcount++;
                }else{
                    while(!wordList.get(temp).word.contains("{")){
                            pcount++;
                            if((!wordList.get(temp + 1).word.startsWith("{")) && wordList.get(temp + 1).word.contains("{")){
                                pcount++;
                            }
                            temp++;
                    }
                }
                if(pcount > 3){
                    int tempLine = wordList.get(i).lineNumber;
                    if(!countList.isEmpty()){                     
                        if(tempLine == countList.get(countList.size()-1).line){
                            int tempCount = countList.get(countList.size()-1).count + 3;
                            Location obj = new Location(tempCount, tempLine);
                            countList.set(countList.size()-1, obj);
                        }else{
                            Location obj = new Location(3, tempLine);
                            countList.add(obj);
                        }
                    }else{
                        Location obj = new Location(3, tempLine);
                        countList.add(obj);
                    }
                }else{
                    int tempLine = wordList.get(i).lineNumber;
                    if(!countList.isEmpty()){                     
                        if(tempLine == countList.get(countList.size()-1).line){
                            int tempCount = countList.get(countList.size()-1).count + pcount;
                            Location obj = new Location(tempCount, tempLine);
                            countList.set(countList.size()-1, obj);
                        }else{
                            Location obj = new Location(pcount, tempLine);
                            countList.add(obj);
                        }
                    }else{
                        Location obj = new Location(pcount, tempLine);
                        countList.add(obj);
                    }
                }
                
            }
        }
        return countList;
    }

}