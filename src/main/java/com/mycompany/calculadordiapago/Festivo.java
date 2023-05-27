/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculadordiapago;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Andrés Gutiérrez
 */
public class Festivo {
    
    public Date Fecha;
    public String Motivo; 
    
    public Festivo (Date _fecha, String _motivo) {
        this.Fecha = _fecha;
        this.Motivo = _motivo;
    }
}
