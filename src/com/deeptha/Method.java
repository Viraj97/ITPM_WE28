package com.deeptha;

import java.util.*; 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import com.deeptha.Location;
import com.deeptha.Words;

public class Method {
    public ArrayList<Location> getData(String fileName)throws FileNotFoundException, NoSuchElementException {
        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        int lineNum = 0;
        final ArrayList<Words> wordList = new ArrayList<Words>();
        final ArrayList<Location> countList = new ArrayList<Location>();

        while(scan.hasNextLine()){             
            String word = scan.nextLine();
            String[] newArr = word.split("[\\s;=+-,/*}{]+");
            for(String a: newArr){               
                if(!a.isEmpty()){
                    Words obj = new Words(a,lineNum);
                    wordList.add(obj);                    
                }
            }
            lineNum++;
        }
        ////////

        

        int index = wordList.size();
        for(int i=0;i<index;i++){
            String[] arr = wordList.get(i).word.split("\\[");
            String temp = arr[0]; 
            if(i==index-4){ //checking for overflow
                break;
            }else if((temp.equals("void")  || temp.equals("int") || temp.equals("float") || temp.equals("double") || temp.equals("byte") || temp.equals("short") || temp.equals("long") || temp.equals("boolean") || temp.equals("char") || temp.equals("String") || temp.startsWith("HashMap") || temp.startsWith("List")) && wordList.get(i+2).word.startsWith("(")){
                // return words.get(i+1);
                if(wordList.get(i).word.equals("void")){
                    continue;
                }else if(wordList.get(i).word.equals("int") || wordList.get(i).word.equals("float") || wordList.get(i).word.equals("double") || wordList.get(i).word.equals("byte") || wordList.get(i).word.equals("short") || wordList.get(i).word.equals("long") || wordList.get(i).word.equals("boolean")){
                    System.out.println("prmitive");
                    int tempLine = wordList.get(i).lineNumber;
                    if(!countList.isEmpty()){                     
                        if(tempLine == countList.get(countList.size()-1).line){
                            int tempCount = countList.get(countList.size()-1).count + 1;
                            Location obj = new Location(tempCount, tempLine);
                            countList.set(countList.size()-1, obj);
                        }else{
                            Location obj = new Location(1, tempLine);
                            countList.add(obj);
                        }
                    }else{
                        Location obj = new Location(1, tempLine);
                        countList.add(obj);
                    }
                }else{
                    System.out.println("non prmitive");
                    int tempLine = wordList.get(i).lineNumber;
                    if(!countList.isEmpty()){                     
                        if(tempLine == countList.get(countList.size()-1).line){
                            int tempCount = countList.get(countList.size()-1).count + 2;
                            Location obj = new Location(tempCount, tempLine);
                            countList.set(countList.size()-1, obj);
                        }else{
                            Location obj = new Location(2, tempLine);
                            countList.add(obj);
                        }
                    }else{
                        Location obj = new Location(2, tempLine);
                        countList.add(obj);
                    }
                }
                int x=i+2; 
                    if(!wordList.get(i+2).word.endsWith(")")){                       
                        while(!wordList.get(x).word.endsWith(")")){
                            String t = "";
                            if(wordList.get(x).word.contains("(") && !wordList.get(x).word.equals("(")){
                                String[] tempArr = wordList.get(x).word.split("[(]");
                                t = tempArr[1];
                            }else{
                                t = wordList.get(x).word;
                            }

                            if((t.equals("int") || t.equals("float") || t.equals("double") || t.equals("byte") || t.equals("short") || t.equals("long") || t.equals("boolean") || t.equals("char")) && wordList.get(x+1).word.startsWith("[")){
                                System.out.println("non");
                                int tempLine = wordList.get(x).lineNumber;
                                if(!countList.isEmpty()){                     
                                    if(tempLine == countList.get(countList.size()-1).line){
                                        int tempCount = countList.get(countList.size()-1).count + 2;
                                        Location obj = new Location(tempCount, tempLine);
                                        countList.set(countList.size()-1, obj);
                                    }else{
                                        Location obj = new Location(2, tempLine);
                                        countList.add(obj);
                                    }
                                }else{
                                    Location obj = new Location(2, tempLine);
                                    countList.add(obj);
                                }
                            }else if((t.contains("int") || t.contains("float") || t.contains("double") || t.contains("byte") || t.contains("short") || t.contains("long") || t.contains("boolean") || t.contains("char")) && wordList.get(x).word.contains("[")){
                                System.out.println("non");
                                int tempLine = wordList.get(x).lineNumber;
                                if(!countList.isEmpty()){                     
                                    if(tempLine == countList.get(countList.size()-1).line){
                                        int tempCount = countList.get(countList.size()-1).count + 2;
                                        Location obj = new Location(tempCount, tempLine);
                                        countList.set(countList.size()-1, obj);
                                    }else{
                                        Location obj = new Location(2, tempLine);
                                        countList.add(obj);
                                    }
                                }else{
                                    Location obj = new Location(2, tempLine);
                                    countList.add(obj);
                                }
                            }else if(t.startsWith("ArrayList") || t.startsWith("String") || t.startsWith("List") || t.startsWith("HashMap")){
                                System.out.println("non");
                                int tempLine = wordList.get(x).lineNumber;
                                if(!countList.isEmpty()){                     
                                    if(tempLine == countList.get(countList.size()-1).line){
                                        int tempCount = countList.get(countList.size()-1).count + 2;
                                        Location obj = new Location(tempCount, tempLine);
                                        countList.set(countList.size()-1, obj);
                                    }else{
                                        Location obj = new Location(2, tempLine);
                                        countList.add(obj);
                                    }
                                }else{
                                    Location obj = new Location(2, tempLine);
                                    countList.add(obj);
                                }
                            }else if((t.equals("int") || t.equals("float") || t.equals("double") || t.equals("byte") || t.equals("short") || t.equals("long") || t.equals("boolean") || t.equals("char"))){
                                System.out.println("prim");
                                int tempLine = wordList.get(x).lineNumber;
                                if(!countList.isEmpty()){                     
                                    if(tempLine == countList.get(countList.size()-1).line){
                                        int tempCount = countList.get(countList.size()-1).count + 1;
                                        Location obj = new Location(tempCount, tempLine);
                                        countList.set(countList.size()-1, obj);
                                    }else{
                                        Location obj = new Location(1, tempLine);
                                        countList.add(obj);
                                    }
                                }else{
                                    Location obj = new Location(1, tempLine);
                                    countList.add(obj);
                                }
                            }
                            x++;
                        }    
                    }                     
                    
            }else if( (temp.equals("void")  || temp.equals("int") || temp.equals("float") || temp.equals("double") || temp.equals("byte") || temp.equals("short") || temp.equals("long") || temp.equals("boolean") || temp.equals("char") || temp.equals("String") || temp.startsWith("Array") || temp.startsWith("List") ) && wordList.get(i+1).word.contains("(")){
                if(wordList.get(i).word.equals("void")){
                    // System.out.println("void");
                }else if(wordList.get(i).word.equals("int") || wordList.get(i).word.equals("float") || wordList.get(i).word.equals("double") || wordList.get(i).word.equals("byte") || wordList.get(i).word.equals("short") || wordList.get(i).word.equals("long") || wordList.get(i).word.equals("boolean")){
                    System.out.println("prmitive");
                    int tempLine = wordList.get(i).lineNumber;
                    if(!countList.isEmpty()){                     
                        if(tempLine == countList.get(countList.size()-1).line){
                            int tempCount = countList.get(countList.size()-1).count + 1;
                            Location obj = new Location(tempCount, tempLine);
                            countList.set(countList.size()-1, obj);
                        }else{
                            Location obj = new Location(1, tempLine);
                            countList.add(obj);
                        }
                    }else{
                        Location obj = new Location(1, tempLine);
                        countList.add(obj);
                    }
                }else{
                    System.out.println("non prmitive");
                    int tempLine = wordList.get(i).lineNumber;
                    if(!countList.isEmpty()){                     
                        if(tempLine == countList.get(countList.size()-1).line){
                            int tempCount = countList.get(countList.size()-1).count + 2;
                            Location obj = new Location(tempCount, tempLine);
                            countList.set(countList.size()-1, obj);
                        }else{
                            Location obj = new Location(2, tempLine);
                            countList.add(obj);
                        }
                    }else{
                        Location obj = new Location(2, tempLine);
                        countList.add(obj);
                    }
                }
                int x=i+1; 
                    if(!wordList.get(i+1).word.endsWith(")")){                       
                        while(!wordList.get(x).word.endsWith(")")){
                            String t = "";
                            if(wordList.get(x).word.contains("(") && !wordList.get(x).word.endsWith("(")){
                                String[] tempArr = wordList.get(x).word.split("[(]");
                                t = tempArr[1];
                            }else{
                                t = wordList.get(x).word;
                            }

                            if((t.equals("int") || t.equals("float") || t.equals("double") || t.equals("byte") || t.equals("short") || t.equals("long") || t.equals("boolean") || t.equals("char")) && wordList.get(x+1).word.startsWith("[")){
                                System.out.println("non");
                                int tempLine = wordList.get(x).lineNumber;
                                if(!countList.isEmpty()){                     
                                    if(tempLine == countList.get(countList.size()-1).line){
                                        int tempCount = countList.get(countList.size()-1).count + 2;
                                        Location obj = new Location(tempCount, tempLine);
                                        countList.set(countList.size()-1, obj);
                                    }else{
                                        Location obj = new Location(2, tempLine);
                                        countList.add(obj);
                                    }
                                }else{
                                    Location obj = new Location(2, tempLine);
                                    countList.add(obj);
                                }
                            }else if((t.contains("int") || t.contains("float") || t.contains("double") || t.contains("byte") || t.contains("short") || t.contains("long") || t.contains("boolean") || t.contains("char")) && wordList.get(x).word.contains("[")){
                                System.out.println("non");
                                int tempLine = wordList.get(x).lineNumber;
                                if(!countList.isEmpty()){                     
                                    if(tempLine == countList.get(countList.size()-1).line){
                                        int tempCount = countList.get(countList.size()-1).count + 2;
                                        Location obj = new Location(tempCount, tempLine);
                                        countList.set(countList.size()-1, obj);
                                    }else{
                                        Location obj = new Location(2, tempLine);
                                        countList.add(obj);
                                    }
                                }else{
                                    Location obj = new Location(2, tempLine);
                                    countList.add(obj);
                                }
                            }else if(t.startsWith("ArrayList") || t.startsWith("String") || t.startsWith("List") || t.startsWith("HashMap")){
                                System.out.println("non");
                                int tempLine = wordList.get(x).lineNumber;
                                if(!countList.isEmpty()){                     
                                    if(tempLine == countList.get(countList.size()-1).line){
                                        int tempCount = countList.get(countList.size()-1).count + 2;
                                        Location obj = new Location(tempCount, tempLine);
                                        countList.set(countList.size()-1, obj);
                                    }else{
                                        Location obj = new Location(2, tempLine);
                                        countList.add(obj);
                                    }
                                }else{
                                    Location obj = new Location(2, tempLine);
                                    countList.add(obj);
                                }
                            }else if((t.equals("int") || t.equals("float") || t.equals("double") || t.equals("byte") || t.equals("short") || t.equals("long") || t.equals("boolean") || t.equals("char"))){
                                System.out.println("prim");
                                int tempLine = wordList.get(x).lineNumber;
                                if(!countList.isEmpty()){                     
                                    if(tempLine == countList.get(countList.size()-1).line){
                                        int tempCount = countList.get(countList.size()-1).count + 1;
                                        Location obj = new Location(tempCount, tempLine);
                                        countList.set(countList.size()-1, obj);
                                    }else{
                                        Location obj = new Location(1, tempLine);
                                        countList.add(obj);
                                    }
                                }else{
                                    Location obj = new Location(1, tempLine);
                                    countList.add(obj);
                                }
                            }
                            x++;
                        }    
                    }
                // return arrOfStr[0];
            }else{
                // return null;
            }
        }
        System.out.println("meth");
        
        return countList;

    }
    
}
