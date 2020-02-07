/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.laboratorio4arep;
import edu.escuelaing.arep.laboratorio4.anotaciones.web;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david.gonzalez-g
 */
public class BestSpring {
    
    public static void main(String[] args){
        String className = "edu.escuelaing.arep.laboratorio4arep.service.webServiceHello";
        try {
            Class c = Class.forName(className);
            Method[] methods= c.getMethods();
            for (Method m : methods)
            { 
                if (m.isAnnotationPresent(web.class)) {
                    try {
                        System.out.println(m.invoke(null));
    
                    }
                    catch (Throwable ex) {
                        System.out.printf("Test %s failed: %s %n", m, ex.getCause()); ;  }}}
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BestSpring.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
