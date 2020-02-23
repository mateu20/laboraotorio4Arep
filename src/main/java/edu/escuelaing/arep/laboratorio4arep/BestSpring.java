/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.laboratorio4arep;
import edu.escuelaing.arep.laboratorio4.anotaciones.web;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

/**
 *
 * @author david.gonzalez-g
 */
public class BestSpring {
	
	private static HashMap<String,String> URL= new HashMap<>();
    
    public static void Leer(){
    	
    	Reflections reflections = new Reflections("edu.escuelaing.arep.laboratorio4.service", new SubTypesScanner(false)); 
    	Set<Class<? extends Object>> clases =  reflections.getSubTypesOf(Object.class);
    	
      	for (Class c:clases) {
            Method[] methods=c.getMethods();
            for (Method m :methods) {
            	if (m.isAnnotationPresent(web.class)) {            		
            		try {
            			String html= (String) m.invoke(null);
            			String ruta= m.getAnnotation(web.class).value();           			
            			URL.put(ruta, html);
            			} catch (Throwable ex) {
            				System.out.printf("Test %s fallido: %s %n", m, ex.getCause());

            			}
            	}
            	
            }
            }
            
            
	}

	public static HashMap<String, String> getRutaPaginas() {
		// TODO Auto-generated method stub
		return URL;
	}

}
