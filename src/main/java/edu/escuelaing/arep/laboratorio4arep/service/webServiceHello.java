/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.laboratorio4arep.service;

import edu.escuelaing.arep.laboratorio4.anotaciones.web;




/**
 *
 * @author david.gonzalez-g
 */
public class webServiceHello {
    
    @web("campus.html") 
    public static String hello(){
        return "<<!DOCTYPE html>\n" + 
        		"<html>\n" + 
        		"  <head>\n" + 
        		"    <title>Arep</title>\n" + 
        		"  </head>\n" + 
        		"  <body>\n" + 
        		"    <h1>Beinvenido!</h1>\n" + 
        		"    <p>Beinvenido a Arep 2020-1...</p>\n" +
        		"    <p> Este es el campus de la Escuela "+
        		"    <img src=\"campusEci.png\" >\r\n" +
        		"  </body>\n" + 
        		"</html>";
    }
    
}
