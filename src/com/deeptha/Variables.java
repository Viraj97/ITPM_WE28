package com.deeptha;

import java.util.ArrayList; // import the ArrayList class
import java.util.*; 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import com.deeptha.Location;
import com.deeptha.Words;

public class Variables {
    public ArrayList<Location> getData(String fileName) throws FileNotFoundException, NoSuchElementException {
        int countGlobal = 0;
        int countLocal = 0;

        final File file = new File(fileName);
        final Scanner scan = new Scanner(file);
        int index = 0;
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

        index = wordList.size();
        int i = 0;
        while(findMethod(wordList,i) == null){
            if((wordList.get(i).word.equals("int") || wordList.get(i).word.equals("float") || wordList.get(i).word.equals("double") || wordList.get(i).word.equals("byte") || wordList.get(i).word.equals("short") || wordList.get(i).word.equals("long") || wordList.get(i).word.equals("boolean") || wordList.get(i).word.equals("char")) && wordList.get(i+1).word.startsWith("[")){
                System.out.println("non");
                int tempLine = wordList.get(i).lineNumber;
                if(!countList.isEmpty()){                     
                    if(tempLine == countList.get(countList.size()-1).line){
                        int tempCount = countList.get(countList.size()-1).count + 4;
                        Location obj = new Location(tempCount, tempLine);
                        countList.set(countList.size()-1, obj);
                    }else{
                        Location obj = new Location(4, tempLine);
                        countList.add(obj);
                    }
                }else{
                    Location obj = new Location(4, tempLine);
                    countList.add(obj);
                }
            }else if((wordList.get(i).word.contains("int") || wordList.get(i).word.contains("float") || wordList.get(i).word.contains("double") || wordList.get(i).word.contains("byte") || wordList.get(i).word.contains("short") || wordList.get(i).word.contains("long") || wordList.get(i).word.contains("boolean") || wordList.get(i).word.contains("char")) && wordList.get(i).word.contains("[")){
                System.out.println("non");
                int tempLine = wordList.get(i).lineNumber;
                if(!countList.isEmpty()){                     
                    if(tempLine == countList.get(countList.size()-1).line){
                        int tempCount = countList.get(countList.size()-1).count + 4;
                        Location obj = new Location(tempCount, tempLine);
                        countList.set(countList.size()-1, obj);
                    }else{
                        Location obj = new Location(4, tempLine);
                        countList.add(obj);
                    }
                }else{
                    Location obj = new Location(4, tempLine);
                    countList.add(obj);
                }
            }else if(wordList.get(i).word.startsWith("ArrayList") || wordList.get(i).word.startsWith("String") || wordList.get(i).word.startsWith("List") || wordList.get(i).word.startsWith("HashMap")){
                System.out.println("non");
                int tempLine = wordList.get(i).lineNumber;
                if(!countList.isEmpty()){                     
                    if(tempLine == countList.get(countList.size()-1).line){
                        int tempCount = countList.get(countList.size()-1).count + 4;
                        Location obj = new Location(tempCount, tempLine);
                        countList.set(countList.size()-1, obj);
                    }else{
                        Location obj = new Location(4, tempLine);
                        countList.add(obj);
                    }
                }else{
                    Location obj = new Location(4, tempLine);
                    countList.add(obj);
                }
            }else if((wordList.get(i).word.equals("int") || wordList.get(i).word.equals("float") || wordList.get(i).word.equals("double") || wordList.get(i).word.equals("byte") || wordList.get(i).word.equals("short") || wordList.get(i).word.equals("long") || wordList.get(i).word.equals("boolean") || wordList.get(i).word.equals("char"))){
                System.out.println("prim");
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
            i++;
            if(i == index-3){
                break;
            } 
        }
        int end = i;
        for(int j=end;j<index;j++){
            if(findMethod(wordList, j) == null){
                // System.out.println(words.get(j));
                if((wordList.get(j).word.equals("int") || wordList.get(j).word.equals("float") || wordList.get(j).word.equals("double") || wordList.get(j).word.equals("byte") || wordList.get(j).word.equals("short") || wordList.get(j).word.equals("long") || wordList.get(j).word.equals("boolean") || wordList.get(j).word.equals("char")) && wordList.get(j+1).word.startsWith("[")){
                    if(wordList.get(j-1).word.equals("new")){

                    }if(wordList.get(j-1).word.endsWith("(")){

                    }else if(wordList.get(j-1).word.equals("if(") || wordList.get(j-1).word.equals("for(") || wordList.get(j-1).word.equals("while(") || wordList.get(j-1).word.equals("do(") || wordList.get(j-1).word.equals("switch(")){

                    }else if(wordList.get(j-1).word.equals("if") || wordList.get(j-1).word.equals("for") || wordList.get(j-1).word.equals("while") || wordList.get(j-1).word.equals("do") || wordList.get(j-1).word.equals("switch")){

                    }else{
                        // System.out.println(words.get(j+1));
                        int tempLine = wordList.get(j+1).lineNumber;
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
                }else if(wordList.get(j).word.equals("int") || wordList.get(j).word.equals("float") || wordList.get(j).word.equals("double") || wordList.get(j).word.equals("byte") || wordList.get(j).word.equals("short") || wordList.get(j).word.equals("long") || wordList.get(j).word.equals("boolean") || wordList.get(j).word.equals("char")){
                    if(wordList.get(j-1).word.equals("new")){

                    }if(wordList.get(j-1).word.endsWith("(")){

                    }else if(wordList.get(j-1).word.equals("if(") || wordList.get(j-1).word.equals("for(") || wordList.get(j-1).word.equals("while(") || wordList.get(j-1).word.equals("do(") || wordList.get(j-1).word.equals("switch(")){

                    }else if(wordList.get(j-1).word.equals("if") || wordList.get(j-1).word.equals("for") || wordList.get(j-1).word.equals("while") || wordList.get(j-1).word.equals("do") || wordList.get(j-1).word.equals("switch")){

                    }else{
                        // System.out.println(words.get(j+1));
                        int tempLine = wordList.get(j+1).lineNumber;
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
                }else if(wordList.get(j).word.endsWith("(int") || wordList.get(j).word.endsWith("(float") || wordList.get(j).word.endsWith("(double") || wordList.get(j).word.endsWith("(byte") || wordList.get(j).word.endsWith("(short") || wordList.get(j).word.endsWith("(long") || wordList.get(j).word.endsWith("(boolean") || wordList.get(j).word.endsWith("(char")){
                    if(wordList.get(j-1).word.equals("if") || wordList.get(j-1).word.equals("for") || wordList.get(j-1).word.equals("while") || wordList.get(j-1).word.equals("do") || wordList.get(j-1).word.equals("switch")){
                        
                    }else if(wordList.get(j).word.startsWith("if") || wordList.get(j).word.startsWith("for") || wordList.get(j).word.startsWith("while") || wordList.get(j).word.startsWith("do") || wordList.get(j).word.startsWith("switch")){

                    }else{
                        // System.out.println(words.get(j+1));
                    }
                }else if(wordList.get(j).word.startsWith("ArrayList") || wordList.get(j).word.startsWith("String") || wordList.get(j).word.startsWith("List") || wordList.get(j).word.startsWith("HashMap") || wordList.get(j).word.startsWith("int") || wordList.get(j).word.startsWith("float") || wordList.get(j).word.startsWith("double") || wordList.get(j).word.startsWith("byte") || wordList.get(j).word.startsWith("short") || wordList.get(j).word.startsWith("long") || wordList.get(j).word.startsWith("boolean") || wordList.get(j).word.startsWith("char")){
                    if(wordList.get(j-1).word.equals("new")){

                    }if(wordList.get(j-1).word.endsWith("(")){

                    }else if(wordList.get(j-1).word.equals("if(") || wordList.get(j-1).word.equals("for(") || wordList.get(j-1).word.equals("while(") || wordList.get(j-1).word.equals("do(") || wordList.get(j-1).word.equals("switch(")){

                    }else if(wordList.get(j-1).word.equals("if") || wordList.get(j-1).word.equals("for") || wordList.get(j-1).word.equals("while") || wordList.get(j-1).word.equals("do") || wordList.get(j-1).word.equals("switch")){

                    }else{
                        System.out.println("nonLocal");
                        int tempLine = wordList.get(j).lineNumber;
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
                }else if(wordList.get(j).word.contains("(ArrayList") || wordList.get(j).word.contains("(String") || wordList.get(j).word.contains("(List") || wordList.get(j).word.contains("(HashMap") || wordList.get(j).word.contains("(int") || wordList.get(j).word.contains("(float") || wordList.get(j).word.contains("(double") || wordList.get(j).word.contains("(byte") || wordList.get(j).word.contains("(short") || wordList.get(j).word.contains("(long") || wordList.get(j).word.contains("(boolean") || wordList.get(j).word.contains("(char")){
                    if(wordList.get(j-1).word.equals("new")){

                    }else if(wordList.get(j-1).word.equals("if") || wordList.get(j-1).word.equals("for") || wordList.get(j-1).word.equals("while") || wordList.get(j-1).word.equals("do") || wordList.get(j-1).word.equals("switch")){
                        
                    }else if(wordList.get(j).word.startsWith("if") || wordList.get(j).word.startsWith("for") || wordList.get(j).word.startsWith("while") || wordList.get(j).word.startsWith("do") || wordList.get(j).word.startsWith("switch")){

                    }else{
                        // System.out.println("nonLocal");
                    }
                }
            }
        }

        return countList;
    }
    ///////
    
    ///////
    static String findMethod(ArrayList<Words> words, int i){
        String[] arr = words.get(i).word.split("\\[");
        String temp = arr[0]; 
        if((temp.equals("void")  || temp.equals("int") || temp.equals("float") || temp.equals("double") || temp.equals("byte") || temp.equals("short") || temp.equals("long") || temp.equals("boolean") || temp.equals("char") || temp.equals("String") || temp.startsWith("HashMap") || temp.startsWith("List")) && words.get(i+2).word.startsWith("(")){
            return words.get(i+1).word;
        }else if( (temp.equals("void")  || temp.equals("int") || temp.equals("float") || temp.equals("double") || temp.equals("byte") || temp.equals("short") || temp.equals("long") || temp.equals("boolean") || temp.equals("char") || temp.equals("String") || temp.startsWith("Array") || temp.startsWith("List") ) && words.get(i+1).word.contains("(")){
            String[] arrOfStr = words.get(i+1).word.split("[(]+");
            return arrOfStr[0];
        }else{
            return null;
        }
    }
}

class Words{
    String word;
    int lineNumber;

    Words(String word,int lineNumber){
        this.word = word;
        this.lineNumber = lineNumber;
    }
}

class Location{
    int count;
    int line;

    Location(int count,int line){
        this.count = count;
        this.line = line;
    }
}
