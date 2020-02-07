/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.laboratorio4arep;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david.gonzalez-g
 */
public class BestSpring {
    
    public static void main(String[] args){
        String className = "edu.escuelaing.arep.service";
        try {
            Class c = Class.forName(className);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BestSpring.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
