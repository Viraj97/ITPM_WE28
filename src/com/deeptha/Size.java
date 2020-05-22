package com.deeptha;
import java.util.ArrayList; // import the ArrayList class
import java.util.*; 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import com.deeptha.Location;
import com.deeptha.Words;

public class Size {
	public ArrayList<Integer> getData(String fileName) throws FileNotFoundException, NoSuchElementException {
		
		ArrayList<Location> keyowordcount = keywords(fileName);
		ArrayList<Location> stringcount = strings(fileName);
		ArrayList<Location> numericalcount = numerical(fileName);
		ArrayList<Location> operatorscount = operators(fileName);
		ArrayList<Location> identifierscount = identifiers(fileName);
		
		ArrayList<Integer> exprt = new ArrayList<Integer>();
		
		Lines line = new Lines();
		ArrayList<String> lines = line.getLines(fileName);
		
		int length = lines.size();
		int keyS = keyowordcount.size();
		int strS = stringcount.size();
		int numS = numericalcount.size();
		int opS = operatorscount.size();
		int ideS = identifierscount.size();
		
		 for(int i=0;i<length;i++) {
        	   if(keyS!=0) {
        		   for(int j=0;j<keyS;j++) {
            		   if(keyowordcount.get(j).line == i) {
            			   exprt.add(i,keyowordcount.get(j).count);
            			   break;
            		   }else {
            			   exprt.add(i,0);
            		   }
            	   }  
        	   }else {
        		   exprt.add(i,0);
        	   }
        	   
        	   if(strS!=0) {
      		   for(int j=0;j<strS;j++) {
          		   if(stringcount.get(j).line == i) {
          			   int temp = exprt.get(i);
          			 exprt.add(i,(stringcount.get(j).count + temp));
          			   break;
          		   }
          	   	}  
        	   }
        	   if(numS!=0) {
          		   for(int j=0;j<numS;j++) {
              		   if(numericalcount.get(j).line == i) {
              			   int temp = exprt.get(i);
              			 exprt.add(i,(numericalcount.get(j).count + temp));
              			   break;
              		   }
              	   	}  
            	  }
        	   if(opS!=0) {
          		   for(int j=0;j<opS;j++) {
              		   if(operatorscount.get(j).line == i) {
              			   int temp = exprt.get(i);
              			 exprt.add(i,(operatorscount.get(j).count + temp));
              			   break;
              		   }
              	   	}  
            	  }
        	   if(ideS!=0) {
          		   for(int j=0;j<ideS;j++) {
              		   if(identifierscount.get(j).line == i) {
              			   int temp = exprt.get(i);
              			 exprt.add(i,(identifierscount.get(j).count + temp));
              			   break;
              		   }
              	   	}  
            	  }
        	   
        }
		return exprt;
	}
	
	static ArrayList<Location> keywords(String fileName) throws FileNotFoundException, NoSuchElementException {
        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        int count = 0;
        int lineNum = 0;
        final ArrayList<Words> wordList = new ArrayList<Words>();
        final ArrayList<Location> countList = new ArrayList<Location>();

        while(scan.hasNextLine()){             
            String word = scan.nextLine();
            String[] newArr = word.split("[^A-Za-z]+");
            for(String a: newArr){               
                if(!a.isEmpty()){
                    Words obj = new Words(a,lineNum);
                    wordList.add(obj);                    
                }
            }
            lineNum++;
        }

        int length = wordList.size();
        for(int i=0;i<length;i++){
            
            if(wordList.get(i).word.equals("abstract") || wordList.get(i).word.equals("break") || wordList.get(i).word.equals("catch") || wordList.get(i).word.equals("class") || wordList.get(i).word.equals("continue") || wordList.get(i).word.equals("default") || wordList.get(i).word.equals("extends") || wordList.get(i).word.equals("final") || wordList.get(i).word.equals("finally") || wordList.get(i).word.equals("implments") || wordList.get(i).word.equals("import") || wordList.get(i).word.equals("instanceof") || wordList.get(i).word.equals("interface") || wordList.get(i).word.equals("native") || wordList.get(i).word.equals("new") || wordList.get(i).word.equals("null") || wordList.get(i).word.equals("package") || wordList.get(i).word.equals("private") || wordList.get(i).word.equals("protected") || wordList.get(i).word.equals("public") || wordList.get(i).word.equals("return") || wordList.get(i).word.equals("static") || wordList.get(i).word.equals("strictfp") || wordList.get(i).word.equals("super") || wordList.get(i).word.equals("synchronized") || wordList.get(i).word.equals("this") || wordList.get(i).word.equals("throw") || wordList.get(i).word.equals("throws") || wordList.get(i).word.equals("transient") || wordList.get(i).word.equals("try") || wordList.get(i).word.equals("void") || wordList.get(i).word.equals("volatile")){                   
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
                
            } 
        } 
        return countList;
        //////////////////
    }

	
	static ArrayList<Location> strings(String fileName)throws FileNotFoundException, NoSuchElementException {
		 File file = new File(fileName);
	        Scanner scan = new Scanner(file);
	        double index = 0;
	        int lineNum = 0;
	        final ArrayList<Words> wordList = new ArrayList<Words>();
	        final ArrayList<Location> countList = new ArrayList<Location>();

	        while(scan.hasNextLine()){             
	            String word = scan.nextLine();
	            String[] newArr = word.split("[^\'\"]+");
	            for(String a: newArr){               
	                if(!a.isEmpty()){
	                    Words obj = new Words(a,lineNum);
	                    wordList.add(obj);                    
	                }
	            }
	            lineNum++;
	        }

	        int length = wordList.size();
	        for(int i=0;i<length;i++){
	            
	            if(wordList.get(i).word.equals("\"")){
	                i++;
	                while(!wordList.get(i).word.endsWith("\"")){
	                    i++;
	                }
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
	                
	            } else if(wordList.get(i).word.equals("\'")){
	                i++;
	                while(!wordList.get(i).word.endsWith("\'")){
	                    i++;
	                }
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
	                
	            }  else if(wordList.get(i).word.equals("\"\'\'\"")){
	                
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
	                
	            }else if(wordList.get(i).word.startsWith("\"\'")){
	                i++;
	                while(!wordList.get(i).word.endsWith("\"")){
	                    i++;
	                }
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
	                
	            }
	        } 
	        return countList;

	    }
	
	static ArrayList<Location> numerical(String fileName)throws FileNotFoundException, NoSuchElementException {
		 File file = new File(fileName);
		 Scanner scan = new Scanner(file);

	        final ArrayList<Words> wordList = new ArrayList<Words>();
	        final ArrayList<Location> countList = new ArrayList<Location>();
	        int lineNum = 0;
	        
	        while(scan.hasNextLine()){             
	            String word = scan.nextLine();
	            String[] newArr = word.split("[^0-9]+");
	            for(String a: newArr){               
	                if(!a.isEmpty()){
	                    Words obj = new Words(a,lineNum);
	                    wordList.add(obj);                    
	                }
	            }
	            lineNum++;
	        }

	        int length = wordList.size();
	        for(int i=0;i<length;i++){                   
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
	        } 
	        return countList;
	    }
	
	static ArrayList<Location> operators(String fileName)throws FileNotFoundException, NoSuchElementException {
		 File file = new File(fileName);
		 Scanner scan = new Scanner(file);
		 final ArrayList<Words> wordList = new ArrayList<Words>();
	        final ArrayList<Location> countList = new ArrayList<Location>();
	        int index = 0;
	        int lineNum = 0;

	        // scan.useDelimiter("[\\s]+");
	        while(scan.hasNextLine()){             
	            String word = scan.nextLine();
	            String[] newArr = word.split("[\\s?@#$A-Za-z0-9(){};:]+");
	            for(String a: newArr){               
	                if(!a.isEmpty()){
	                    Words obj = new Words(a,lineNum);
	                    wordList.add(obj);                    
	                }
	            }
	            lineNum++;
	        }

	        int length = wordList.size();
	        for(int i=0;i<length;i++){
	            
	            if(wordList.get(i).word.startsWith("[") || wordList.get(i).word.startsWith("]") || wordList.get(i).word.startsWith("/") || wordList.get(i).word.startsWith("*/") || wordList.get(i).word.startsWith("\"") || wordList.get(i).word.startsWith("\'") || wordList.get(i).word.endsWith("\"") || wordList.get(i).word.endsWith("\'")) continue;
	            else{                   
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
	                
	            } 
	        } 
	        return countList;
	            
	            
	    }
	
	static ArrayList<Location> identifiers(String fileName)throws FileNotFoundException, NoSuchElementException {
		 File file = new File(fileName);
		 final Scanner scan = new Scanner(file);
	        final Scanner scan2 = new Scanner(file);
	        final ArrayList<Words> words = new ArrayList<Words>();
	        final ArrayList<Words> variables = new ArrayList<Words>();
	        final ArrayList<Words> wordsFinal = new ArrayList<Words>();
	        final ArrayList<Location> countList = new ArrayList<Location>();
	        int count = 0;
	        int lineNum = 0;

	        while(scan.hasNextLine()){             
	            String word = scan.nextLine();
	            String[] newArr = word.split("[\\s;+/,=*\\[\\]\\-]+");
	            for(String a: newArr){               
	                if(!a.isEmpty()){
	                    Words obj = new Words(a,lineNum);
	                    words.add(obj);                    
	                }
	            }
	            String[] newArr2 = word.split("[^A-Za-z0-9_-]+");
	            for(String a: newArr2){               
	                if(!a.isEmpty()){
	                    Words obj = new Words(a,lineNum);
	                    wordsFinal.add(obj);                    
	                }
	            }
	            lineNum++;
	        }

	        int index = words.size();
	        for(int i=0;i<index;i++){
	            if(i < index-3){
	                if(words.get(i).word.equals("class")){
	                    System.out.println("clss");
	                    count++;
	                    int tempLine = words.get(i).lineNumber;
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
	                }else if((words.get(i).word.equals("void")  || words.get(i).word.equals("int") || words.get(i).word.equals("float") || words.get(i).word.equals("double") || words.get(i).word.equals("byte") || words.get(i).word.equals("short") || words.get(i).word.equals("long") || words.get(i).word.equals("boolean") || words.get(i).word.equals("char") || words.get(i).word.equals("String") || words.get(i).word.startsWith("HashMap") || words.get(i).word.startsWith("List")) && words.get(i+2).word.startsWith("(")){
	                    // return words.get(i+1);
	                    System.out.println("method");
	                    count++;
	                    int tempLine = words.get(i).lineNumber;
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
	                    int x=i+2; 
	                        if(!words.get(i+2).word.endsWith(")")){                       
	                            while(!words.get(x).word.endsWith(")")){
	                                String t = "";
	                                if(words.get(x).word.contains("(") && !words.get(x).word.equals("(")){
	                                    String[] tempArr = words.get(x).word.split("[(]");
	                                    t = tempArr[1];
	                                }else{
	                                    t = words.get(x).word;
	                                }
	    
	                                if(t.equals("int") || t.equals("float") || t.equals("double") || t.equals("byte") || t.equals("short") || t.equals("long") || t.equals("boolean") || t.equals("char") || t.startsWith("ArrayList") || t.startsWith("String") || t.startsWith("List") || t.startsWith("HashMap")){
	                                    String[] ar = words.get(x+1).word.split("[)]");
	                                    Words obj = new Words(ar[0],words.get(x).lineNumber);
	                                        variables.add(obj);
	                                }
	                                x++;
	                            }    
	                        }                     
	                        
	                }else if((words.get(i).word.equals("void")  || words.get(i).word.equals("int") || words.get(i).word.equals("float") || words.get(i).word.equals("double") || words.get(i).word.equals("byte") || words.get(i).word.equals("short") || words.get(i).word.equals("long") || words.get(i).word.equals("boolean") || words.get(i).word.equals("char") || words.get(i).word.equals("String") || words.get(i).word.startsWith("Array") || words.get(i).word.startsWith("List") ) && words.get(i+1).word.contains("(")){
	                    count++;
	                    int tempLine = words.get(i).lineNumber;
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
	                    System.out.println("method");
	                    int x=i+1; 
	                        if(!words.get(i+1).word.endsWith(")")){                       
	                            while(!words.get(x).word.endsWith(")")){
	                                String t = "";
	                                if(words.get(x).word.contains("(") && !words.get(x).word.endsWith("(")){
	                                    String[] tempArr = words.get(x).word.split("[(]");
	                                    t = tempArr[1];
	                                }else{
	                                    t = words.get(x).word;
	                                }
	    
	                                if(t.equals("int") || t.equals("float") || t.equals("double") || t.equals("byte") || t.equals("short") || t.equals("long") || t.equals("boolean") || t.equals("char") || t.startsWith("ArrayList") || t.startsWith("String") || t.startsWith("List") || t.startsWith("HashMap")){
	                                    String[] ar = words.get(x+1).word.split("[)]");
	                                    Words obj = new Words(ar[0],words.get(x).lineNumber);
	                                        variables.add(obj);
	                                }
	                                x++;
	                            }    
	                        }
	                    // return arrOfStr[0];
	                }else if(words.get(i).word.startsWith("for(")){
	                    // return words.get(i+1);
	                    count++;
	                    int tempLine = words.get(i).lineNumber;
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
	                    if(words.get(i).word.equals("for(")){
	                        Words obj = new Words(words.get(i+2).word,words.get(i+2).lineNumber);
	                            variables.add(obj);
	                        
	                    }else{
	                        Words obj = new Words(words.get(i+1).word,words.get(i+1).lineNumber);
	                            variables.add(obj);
	                    }
	                                        
	                        
	                }else if(words.get(i).word.equals("for")){
	                   count++;
	                   int tempLine = words.get(i).lineNumber;
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
	                   if(words.get(i+1).word.equals("(")){
	                    Words obj = new Words(words.get(i+1).word,words.get(i+1).lineNumber);
	                        variables.add(obj);
	                    }else{
	                        Words obj = new Words(words.get(i+2).word,words.get(i+2).lineNumber);
	                            variables.add(obj);
	                    }
	                    // return arrOfStr[0];
	                }else if(words.get(i).word.equals("new") && words.get(i+1).word.contains("(")){
	                    // count ++;                     
	                }else if(words.get(i).word.equals("int") || words.get(i).word.equals("float") || words.get(i).word.equals("double") || words.get(i).word.equals("byte") || words.get(i).word.equals("short") || words.get(i).word.equals("long") || words.get(i).word.equals("boolean") || words.get(i).word.equals("char") || words.get(i).word.equals("String") || words.get(i).word.startsWith("Array") || words.get(i).word.startsWith("List")){
	                    if(!words.get(i-1).word.equals("new")){
	                        Words obj = new Words(words.get(i+1).word,words.get(i+1).lineNumber);
	                            variables.add(obj);
	                    }
	                }else if(words.get(i).word.contains(".") && words.get(i).word.contains("(")){
	                    String[] arrOfStr = words.get(i).word.split("\\.");
	                    count = count + arrOfStr.length;
	                    int tempLine = words.get(i).lineNumber;
	                        if(!countList.isEmpty()){                     
	                            if(tempLine == countList.get(countList.size()-1).line){
	                                int tempCount = countList.get(countList.size()-1).count + arrOfStr.length;
	                                Location obj = new Location(tempCount, tempLine);
	                                countList.set(countList.size()-1, obj);
	                            }else{
	                                Location obj = new Location(arrOfStr.length, tempLine);
	                                countList.add(obj);
	                            }
	                        }else{
	                            Location obj = new Location(arrOfStr.length, tempLine);
	                            countList.add(obj);
	                        } 
	                }
	            }
	        }
	        int ncount = 0;
	        int wcount = wordsFinal.size();
	        int vcount = variables.size();
	        for(int q=0;q<vcount;q++){
	            for(int r=0;r<wcount;r++){
	                if(variables.get(q).word.equals(wordsFinal.get(r).word)){
	                    if(variables.get(q).lineNumber != wordsFinal.get(r).lineNumber){
	                        int tempLine = wordsFinal.get(r).lineNumber;
	                        int cs = countList.size();
	                        if(!countList.isEmpty()){
	                            int tempass = 0;
	                            for(int n=0;n<cs;n++){
	                                if(countList.get(n).line == tempLine){
	                                    int tempCount = countList.get(n).count + 1;
	                                    Location obj = new Location(tempCount, tempLine);
	                                    countList.set(n, obj);
	                                    tempass++;
	                                }
	                            }
	                            if(tempass == 0){
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

	        return countList;

	    }

}
