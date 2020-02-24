package edu.escuelaing.arep.laboratorio4.server;

import org.apache.commons.io.FilenameUtils;

import edu.escuelaing.arep.laboratorio4.*;
import edu.escuelaing.arep.laboratorio4arep.BestSpring;

import java.net.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

/**
*
* @author david.gonzalez-g
*/

public class Server {
	static PrintWriter out;
	static BufferedReader in;
	static ServerSocket serverSocket = null;	
	static Socket clientSocket = null;	
	
	/**
	 * metodo constructor de nuestros recursos.
	 * @param args 
	 * @throws IOException
	 */
  public static void main(String[] args) throws IOException {
	  serverSocket = null;
	  BestSpring.Leer();	   
	   try { 
	      serverSocket = new ServerSocket(getPort());
	   } catch (IOException e) {
	      System.err.println("Could not listen on port: 35000.");
	      System.exit(1);
	   }
	   while(true) {		   
		   try {
		       clientSocket = serverSocket.accept();
		   } catch (IOException e) {
		       System.err.println("no se pudo leer");
		       System.exit(1);
		   }
		   out = new PrintWriter(
		                         clientSocket.getOutputStream(), true);
		   in = new BufferedReader(
		                         new InputStreamReader(clientSocket.getInputStream()));
		   
		   HashMap<String,String> rutaPaginas=BestSpring.getRutaPaginas();
		   StringBuilder stringBuilder = new StringBuilder();
		   Matcher matcher = null;
		   Pattern pattern = Pattern.compile("GET /([^\\s]+)");	       
	       String inputLine;	       
		   while ((inputLine = in.readLine()) != null) {
		      System.out.println("lei: " + inputLine);
		      stringBuilder.append(inputLine);
		      if (!in.ready()) {
		    	  matcher = pattern.matcher(stringBuilder.toString());
	              if (matcher.find()) {
	                  String req = matcher.group().substring(5);
	                  System.out.println("VALUE: " + req);
	                  if (rutaPaginas.containsKey(req)) {
	                	  out.println("HTTP/1.1 200 \r\nAccess-Control-Allow-Origin: *\r\nContent-Type: text/html\r\n\r\n");
                          out.println(rutaPaginas.get(req));
	                  }
	                  else {
	                	  returnRequest(req);
	                  }
	              }
		    	  
		    	  break; }
		   }
		  
		    out.close(); 
		    in.close(); 
		    clientSocket.close(); 
	   }
  }
  /**
   * 
   * @param req lee los archivos .html , .png , jpg y js solicitados 
   * @throws IOException
   */
  public static void returnRequest(String req) throws IOException {	  
	  String path = "src/main/resources/";
      String ext = FilenameUtils.getExtension(req);
      if (ext.equals("js")) {
    	  path=path+"js/";
    	  
      }else if (ext.equals("jpg") || ext.equals("png")) {
    	  path=path+"img/";
      }
      
      System.out.println(path+req);
      File file = new File(path+req);
      
      if (file.exists() && !file.isDirectory()) {
	      if (ext.equals("png") || ext.equals("jpg")) {
				FileInputStream files = new FileInputStream(file);
				byte[] data = new byte[(int) file.length()];
				files.read(data);
				files.close();
				DataOutputStream binaryOut = new DataOutputStream(clientSocket.getOutputStream());
				binaryOut.writeBytes("HTTP/1.0 200 OK\r\n");
				binaryOut.writeBytes("Content-Type: image/"+ext+"\r\n");
				binaryOut.writeBytes("Content-Length: " + data.length);
				binaryOut.writeBytes("\r\n\r\n");
				binaryOut.write(data);	
				binaryOut.close();
	    	  
	      }
	      else {
				  out.println("HTTP/1.1 200 \r\nContent-Type: text/html\r\n\r\n");
		    	  BufferedReader br = new BufferedReader(new FileReader(file));
	
	              StringBuilder sB = new StringBuilder();
	              String st;
	              while ((st = br.readLine()) != null) {
	                  sB.append(st);
	              }
	              out.println(sB.toString());
	              br.close();
	      }
      }
      else {
    	  out.println("HTTP/1.1 404 \r\n\r\n<html><body><h1>ERROR 404: NOT FOUND</h1></body></html>");
    	  
      }
	  
  }
  /**
   * 
   * @return el puerto por defecto de heroku 
   */
  static int getPort() {
      if (System.getenv("PORT") != null) {
          return Integer.parseInt(System.getenv("PORT"));
      }        
         
      return 4567; //returns default port if heroku-port isn't set(i.e. on localhost)    }
  }
}
