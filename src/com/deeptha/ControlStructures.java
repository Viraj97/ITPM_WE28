package com.deeptha;
import java.util.*; 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import com.deeptha.Location;
import com.deeptha.Words;

public class ControlStructures {
	public ArrayList<Location> getData(String fileName)throws FileNotFoundException, NoSuchElementException {
	        File file = new File(fileName);
	        Scanner scan = new Scanner(file);
	        int ccspps = 0;
	        final ArrayList<Words> wordList = new ArrayList<Words>();
	        final ArrayList<Location> countList = new ArrayList<Location>();
	        int lineNum = 0;
	        
	        while(scan.hasNextLine()){             
	            String word = scan.nextLine();
	            String[] newArr = word.split("[\\s,()]+");
	            for(String a: newArr){               
	                if(!a.isEmpty()){
	                    Words obj = new Words(a,lineNum);
	                    wordList.add(obj);                    
	                }
	            }
	            lineNum++;
	        }

	        int countIfElse = 0; //2
	        int countLoop = 0; //3
	        int countSwitch = 0; //2
	        int countCase = 0; //1

	        int index = wordList.size();
	        for(int i=0;i<index;i++){
	            if(wordList.get(i).word.equals("if")){
	                int tempNum = 0;
	                while((!wordList.get(i).word.contains(";")) && (!wordList.get(i).word.startsWith("{"))){
	                    if(wordList.get(i).word.equals("||")){
	                        tempNum++;
	                        countIfElse = countIfElse + 2;
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
	                    }else if(wordList.get(i).word.startsWith("||")){
	                        tempNum++;
	                        String[] ar =wordList.get(i).word.split("[^||]+");
	                        countIfElse = countIfElse + (ar.length * 2);
	                        int tempLine = wordList.get(i).lineNumber;
	                        if(!countList.isEmpty()){                     
	                            if(tempLine == countList.get(countList.size()-1).line){
	                                int tempCount = countList.get(countList.size()-1).count + (ar.length * 2);
	                                Location obj = new Location(tempCount, tempLine);
	                                countList.set(countList.size()-1, obj);
	                            }else{
	                                Location obj = new Location((ar.length * 2), tempLine);
	                                countList.add(obj);
	                            }
	                        }else{
	                            Location obj = new Location((ar.length * 2), tempLine);
	                            countList.add(obj);
	                        }
	                    }else if(wordList.get(i).word.contains("||")){
	                        tempNum++;
	                        String[] ar = wordList.get(i).word.split("[^||]+");
	                        countIfElse = countIfElse + ((ar.length-1) * 2);
	                        int tempLine = wordList.get(i).lineNumber;
	                        if(!countList.isEmpty()){                     
	                            if(tempLine == countList.get(countList.size()-1).line){
	                                int tempCount = countList.get(countList.size()-1).count + ((ar.length-1) * 2);
	                                Location obj = new Location(tempCount, tempLine);
	                                countList.set(countList.size()-1, obj);
	                            }else{
	                                Location obj = new Location(((ar.length-1) * 2), tempLine);
	                                countList.add(obj);
	                            }
	                        }else{
	                            Location obj = new Location(((ar.length-1) * 2), tempLine);
	                            countList.add(obj);
	                        }
	                    }
	                    i++;
	                }
	                if(tempNum == 0){
	                    countIfElse = countIfElse + 2;
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
	                int temp = i+1;
	                if((!wordList.get(i).word.contains(";")) && (!wordList.get(i).word.startsWith("{"))){
	                    while(!wordList.get(temp).word.startsWith("}")){
	                        if(wordList.get(temp).word.startsWith("{")){
	                            ccspps = ccspps + 2;
	                            int tempLine = wordList.get(i).lineNumber;
	                            Location obj = new Location(2, tempLine+1);
	                            countList.add(obj);
	                            break;
	                        }
	                        temp++;
	                    }
	                }
	                

	            }else if(wordList.get(i).word.equals("while")){
	                int tempNum = 0;
	                while((!wordList.get(i).word.contains(";")) && (!wordList.get(i).word.startsWith("{"))){
	                    if(wordList.get(i).word.equals("||")){
	                        tempNum++;
	                        countLoop = countLoop + 3;
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
	                    }else if(wordList.get(i).word.startsWith("||")){
	                        tempNum++;
	                        String[] ar = wordList.get(i).word.split("[^||]+");
	                        countLoop = countLoop + (ar.length * 3);
	                        int tempLine = wordList.get(i).lineNumber;
	                        if(!countList.isEmpty()){                     
	                            if(tempLine == countList.get(countList.size()-1).line){
	                                int tempCount = countList.get(countList.size()-1).count + (ar.length * 3);
	                                Location obj = new Location(tempCount, tempLine);
	                                countList.set(countList.size()-1, obj);
	                            }else{
	                                Location obj = new Location((ar.length * 3), tempLine);
	                                countList.add(obj);
	                            }
	                        }else{
	                            Location obj = new Location((ar.length * 3), tempLine);
	                            countList.add(obj);
	                        }
	                    }else if(wordList.get(i).word.contains("||")){
	                        tempNum++;
	                        String[] ar = wordList.get(i).word.split("[^||]+");
	                        countLoop = countLoop + ((ar.length-1) * 3);
	                        int tempLine = wordList.get(i).lineNumber;
	                        if(!countList.isEmpty()){                     
	                            if(tempLine == countList.get(countList.size()-1).line){
	                                int tempCount = countList.get(countList.size()-1).count + ((ar.length-1) * 3);
	                                Location obj = new Location(tempCount, tempLine);
	                                countList.set(countList.size()-1, obj);
	                            }else{
	                                Location obj = new Location(((ar.length-1) * 3), tempLine);
	                                countList.add(obj);
	                            }
	                        }else{
	                            Location obj = new Location(((ar.length-1) * 3), tempLine);
	                            countList.add(obj);
	                        }
	                    }
	                    i++;
	                }
	                if(tempNum == 0){
	                    countLoop = countLoop + 3;
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
	                }
	                int temp = i+1;
	                if((!wordList.get(i).word.contains(";")) && (!wordList.get(i).word.startsWith("{"))){
	                    while(!wordList.get(temp).word.startsWith("}")){
	                        if(wordList.get(temp).word.startsWith("{")){
	                            ccspps = ccspps + 3;
	                            int tempLine = wordList.get(i).lineNumber;
	                            Location obj = new Location(3, tempLine+1);
	                            countList.add(obj);
	                            break;
	                        }
	                        temp++;
	                    }
	                }
	                

	            }else if(wordList.get(i).word.equals("for")){
	                countLoop = countLoop + 3;
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
	                while(!wordList.get(i).word.startsWith("{")){
	                      i++;
	                }
	                int temp = i+1;
	                while(!wordList.get(temp).word.startsWith("}")){
	                    if(wordList.get(temp).word.startsWith("{")){
	                        ccspps = ccspps + 3;
	                        Location obj = new Location(3, tempLine+1);
	                        countList.add(obj);
	                        break;
	                    }
	                    temp++;
	                }
	            }else if(wordList.get(i).word.equals("switch")){
	                int tempNum = 0;
	                while((!wordList.get(i).word.contains(";")) && (!wordList.get(i).word.startsWith("{"))){
	                    if(wordList.get(i).word.equals("||")){
	                        tempNum++;
	                        countSwitch = countSwitch + 2;
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
	                    }else if(wordList.get(i).word.startsWith("||")){
	                        tempNum++;
	                        String[] ar = wordList.get(i).word.split("[^||]+");
	                        countSwitch = countSwitch + (ar.length * 2);
	                        int tempLine = wordList.get(i).lineNumber;
	                        if(!countList.isEmpty()){                     
	                            if(tempLine == countList.get(countList.size()-1).line){
	                                int tempCount = countList.get(countList.size()-1).count + (ar.length * 2);
	                                Location obj = new Location(tempCount, tempLine);
	                                countList.set(countList.size()-1, obj);
	                            }else{
	                                Location obj = new Location((ar.length * 2), tempLine);
	                                countList.add(obj);
	                            }
	                        }else{
	                            Location obj = new Location((ar.length * 2), tempLine);
	                            countList.add(obj);
	                        }
	                    }else if(wordList.get(i).word.contains("||")){
	                        tempNum++;
	                        String[] ar = wordList.get(i).word.split("[^||]+");
	                        countSwitch =  countSwitch + ((ar.length-1) * 2);
	                        int tempLine = wordList.get(i).lineNumber;
	                        if(!countList.isEmpty()){                     
	                            if(tempLine == countList.get(countList.size()-1).line){
	                                int tempCount = countList.get(countList.size()-1).count + ((ar.length-1) * 2);
	                                Location obj = new Location(tempCount, tempLine);
	                                countList.set(countList.size()-1, obj);
	                            }else{
	                                Location obj = new Location(((ar.length-1) * 2), tempLine);
	                                countList.add(obj);
	                            }
	                        }else{
	                            Location obj = new Location(((ar.length-1) * 2), tempLine);
	                            countList.add(obj);
	                        }
	                    }
	                    i++;
	                }
	                if(tempNum == 0){
	                    countSwitch = countSwitch + 2;
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

	            }else if(wordList.get(i).word.equals("case")){
	                countCase++;
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

	}

	