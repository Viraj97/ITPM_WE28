package com.deeptha;
import java.util.ArrayList; // import the ArrayList class
import java.util.*; 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import com.deeptha.Location;
import com.deeptha.Methods;
import com.deeptha.Words;

public class Coupling {
	
	public ArrayList<Location> getData(String fileName) throws FileNotFoundException, NoSuchElementException {
        final File file = new File(fileName);
        final Scanner scan = new Scanner(file);
        final ArrayList<Words> words = new ArrayList<Words>();
        final ArrayList<Methods> list = new ArrayList<Methods>();
        final ArrayList<String> globalVariables = new ArrayList<String>();
        final ArrayList<Location> countList = new ArrayList<Location>();
        int count = 0;
        int lineNum = 0;
        
        while(scan.hasNextLine()){             
            String word = scan.nextLine();
            String[] newArr = word.split("[\\s;=+-,/*}{]+");
            for(String a: newArr){               
                if(!a.isEmpty()){
                    Words obj = new Words(a,lineNum);
                    words.add(obj);                    
                }
            }
            lineNum++;
        }
        
        int index = words.size();

        
        // index = index-10;
        int o = 0;
        //obtaining global variables
            while(findMethod(words,o) == null){
                if(words.get(o).word.equals("int") || words.get(o).word.equals("float") || words.get(o).word.equals("double") || words.get(o).word.equals("char") || words.get(o).word.equals("long") || words.get(o).word.equals("byte") || words.get(o).word.equals("short") || words.get(o).word.equals("boolean")){
                    String[] temp = words.get(o+1).word.split("[)(]+");
                    globalVariables.add(temp[0]);
                }else if(words.get(o).word.startsWith("int") || words.get(o).word.startsWith("float") || words.get(o).word.startsWith("double") || words.get(o).word.startsWith("char") || words.get(o).word.startsWith("long") || words.get(o).word.startsWith("byte") || words.get(o).word.startsWith("short") || words.get(o).word.startsWith("boolean") || words.get(o).word.startsWith("String") || words.get(o).word.startsWith("Array") || words.get(o).word.startsWith("List")){
                    globalVariables.add(words.get(o+1).word);
                }
                o++;
                if(o == index-10){
                    break;
                }
            }
        //obtaining regular and recursive methods
        for(int i=0;i<index;i++){
            String methodName = findMethod(words,i);
            if(i > (index-9)){
                break;
            }else if(methodName != null){
                String methodType = "regular";
                int start = i+2;
                int temparary = start;
                while(findMethod(words,start) == null ){
                    if(words.get(start).word.contains(methodName)){
                        String[] temp = words.get(start).word.split("[)(]+");
                        for (String a : temp){
                            if(a.equals(methodName)){
                                methodType = "recursive";
                                break;
                            }
                        }
                    }
                    // System.out.println(start);
                    start++;
                    if(start == index-10){
                        break;
                    }
                }
                int end = start-1;
                Methods obj = new Methods(methodType, methodName, temparary, end );
                list.add(obj);
            }
        }
        int length = list.size();

        for(int i=0;i<length;i++){
            int s = list.get(i).startIndex;
            int e = list.get(i).endIndex;
            String n = list.get(i).name;
            String t = list.get(i).type;

            for(int j=s;j<e;j++){
                for(int z=0;z<length;z++){
                    if(z==i) continue;
                    if(words.get(j).word.contains(list.get(z).name)){
                        String[] temp = words.get(j).word.split("[)(]+");
                            for (String a : temp){
                                if(a.equals(list.get(z).name)){
                                    if(t.equals("regular")){
                                        if(list.get(z).type.equals("regular")){
                                            System.out.println("regular calls regular");
                                            int tempLine = words.get(j).lineNumber;
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
                                        }else{
                                            System.out.println("regular calls recursive");
                                            int tempLine = words.get(j).lineNumber;
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
                                        }
                                    }else if(t.equals("recursive")){
                                        if(list.get(z).type.equals("regular")){
                                            System.out.println("recursive calls regular");
                                            int tempLine = words.get(j).lineNumber;
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
                                            System.out.println("recursive calls recursive");
                                            int tempLine = words.get(j).lineNumber;
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
                                        }
                                    }
                                }
                            }
                    }
                }                
            }
        }
        
        int gLength = globalVariables.size();
        for(int i=0;i<length;i++){
            int s = list.get(i).startIndex;
            int e = list.get(i).endIndex;
            // String n = list.get(i).name;
            String t = list.get(i).type;
            System.out.println(s+"start");
            System.out.println(e+"end");
            for(int j=s;j<e;j++){
                for(int z=0;z<gLength;z++){
                    if(words.get(j).word.contains(globalVariables.get(z))){
                        System.out.println(globalVariables.get(z));
                        String[] temp = words.get(j).word.split("[)(]+");
                            for (String a : temp){
                                if(a.equals(globalVariables.get(z))){
                                    if(t.equals("recursive")){
                                        System.out.println("recursive calls global");
                                        int tempLine = words.get(j).lineNumber;
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
                                        System.out.println("recursive calls local");
                                        int tempLine = words.get(j).lineNumber;
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
                                }
                    }
                }
            }
        }
        }

        System.out.println("cup");
        return countList;
        
    }
        
    

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

class Methods{
    String type,name;
    int startIndex;
    int endIndex;

    Methods(String type, String name, int startIndex, int endIndex){
        this.type = type;
        this.name = name;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }
}

