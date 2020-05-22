package com.deeptha;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lines {
	public ArrayList<String> getLines(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
        Scanner scan = new Scanner(file);
        ArrayList<String> lines = new ArrayList<String>();
        while(scan.hasNextLine()) {
        	lines.add(scan.nextLine());
        }
        System.out.println("lines");
		return lines;		
	}

}
