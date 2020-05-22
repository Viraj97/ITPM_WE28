package com.deeptha;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.deeptha.Location;

public class Add extends HttpServlet{
	private final String UPLOAD_DIRECTORY = "C:/uploads";
	   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String fileName = "";
    	ControlStructures con = new ControlStructures();
    	Coupling cup = new Coupling();
    	Inheritance inhe = new Inheritance();
    	Method meth = new Method();
    	Variables var = new Variables();
    	Lines lines = new Lines();
    	Size size = new Size();
    	
    	
    	 if(ServletFileUpload.isMultipartContent(request)){
             try {
                 List<FileItem> multiparts = new ServletFileUpload(
                                          new DiskFileItemFactory()).parseRequest(request);
                
                 for(FileItem item : multiparts){
                     if(!item.isFormField()){
                         String name = new File(item.getName()).getName();
                         fileName = UPLOAD_DIRECTORY + File.separator + name;
                         item.write( new File(fileName));
                     }
                 }
                 
                 ArrayList<String> line = lines.getLines(fileName);                  

                 ArrayList<Integer> conditionalArr = new ArrayList<Integer>();
                 ArrayList<Integer> inheritanceArr = new ArrayList<Integer>();
                 ArrayList<Integer> methodArr = new ArrayList<Integer>();
                 ArrayList<Integer> variablesArr = new ArrayList<Integer>();
                 ArrayList<Integer> couplingArr = new ArrayList<Integer>();

                 ArrayList<Integer> sizeArr = size.getData(fileName);
                 
                 ArrayList<Location> conditional = con.getData(fileName);
                 ArrayList<Location> inheritance = inhe.getData(fileName);
                 ArrayList<Location> method = meth.getData(fileName);
                 ArrayList<Location> variables = var.getData(fileName);
                 ArrayList<Location> coupling = cup.getData(fileName);
                 
                 int length = line.size();
                 int conSize = conditional.size();
                 int inheSize = inheritance.size();
                 int methSize = method.size();
                 int varSize = variables.size();
                 int cupSize = coupling.size();
                 System.out.println(length);
                 System.out.println(conSize); 
                 System.out.println(inheSize);
                 System.out.println(methSize);
                 for(int i=0;i<length;i++) {
              	   if(conSize!=0) {
              		   for(int j=0;j<conSize;j++) {
                  		   if(conditional.get(j).line == i) {
                  			   conditionalArr.add(i,conditional.get(j).count);
                  			   break;
                  		   }else {
                  			 conditionalArr.add(i,0);
                  		   }
                  	   }  
              	   }else {
              		 conditionalArr.add(i,0);
              	   }
              	   
              	   if(inheSize!=0) {
            		   for(int j=0;j<inheSize;j++) {
                		   if(inheritance.get(j).line == i) {
                			   inheritanceArr.add(i,inheritance.get(j).count);
                			   break;
                		   }else {
                			   inheritanceArr.add(i,0);
                		   }
                	   }  
            	   }else {
            		   inheritanceArr.add(i,0);
            	   }
              	   
              	   if(methSize!=0) {
          		    for(int j=0;j<methSize;j++) {
              		   if(method.get(j).line == i) {
              			   methodArr.add(i,method.get(j).count);
              			   break;
              		   }else {
              			 methodArr.add(i,0);
              		   }
              	    }  
          	       }else {
          	    	 methodArr.add(i,0);
          	       }
              	 if(varSize!=0) {
           		    for(int j=0;j<varSize;j++) {
               		   if(variables.get(j).line == i) {
               			   variablesArr.add(i,variables.get(j).count);
               			   break;
               		   }else {
               			variablesArr.add(i,0);
               		   }
               	    }  
           	       }else {
           	    	variablesArr.add(i,0);
           	       }

              	 if(cupSize!=0) {
           		    for(int j=0;j<varSize;j++) {
               		   if(coupling.get(j).line == i) {
               			   couplingArr.add(i,coupling.get(j).count);
               			   break;
               		   }else {
               			couplingArr.add(i,0);
               		   }
               	    }  
           	       }else {
           	    	couplingArr.add(i,0);
           	       }
              }
                 int conX = 0;
                 int cupX = 0;
                 int inheX = 0;
                 int methX = 0;
                 int sizeX = 0;
                 int varX = 0;
                 
                 for(int i=0;i<length;i++) {
                	 conX = conX + conditionalArr.get(i);
                	 cupX = cupX + couplingArr.get(i);
                	 inheX = inheX + inheritanceArr.get(i);
                	 methX = methX + methodArr.get(i);
                	 sizeX = sizeX + sizeArr.get(i);
                	 varX = varX + variablesArr.get(i);
                 }
                 
                 int total = conX+cupX+inheX+methX+sizeX+varX;
                 
               request.setAttribute("conditional", conditionalArr);
               request.setAttribute("lines", line);
               request.setAttribute("variables", variablesArr);
               request.setAttribute("inheritance", inheritanceArr);
               request.setAttribute("method", methodArr);
               request.setAttribute("coupling", couplingArr);
               request.setAttribute("size", sizeArr);
               request.setAttribute("conX", conX);
               request.setAttribute("cupX", cupX);
               request.setAttribute("inheX", inheX);
               request.setAttribute("methX", methX);
               request.setAttribute("sizeX", sizeX);
               request.setAttribute("varX", varX);
               request.setAttribute("total", total);
                
             } catch (Exception ex) {
                request.setAttribute("message", "File Upload Failed due to " + ex);
             }          
           
         }else{
             request.setAttribute("message",
                                  "Sorry this Servlet only handles file upload request");
         }
      
         request.getRequestDispatcher("/result.jsp").forward(request, response);
  
    	
       
    	
      
    }
}
