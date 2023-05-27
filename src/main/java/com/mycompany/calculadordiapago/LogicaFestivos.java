/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculadordiapago;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Andrés Gutiérrez
 */
public class LogicaFestivos {

    public String observaciones = "";
    private List<Festivo> _lstFestivos = new ArrayList<>();

    //constructor llama la funcion que carga los festivos
    public LogicaFestivos() {
        this.cargarFestivos();
    }

    private void cargarFestivos() {
        
        _lstFestivos.add(new Festivo(new Date(2024, 0, 1), "Año nuevo"));
        _lstFestivos.add(new Festivo(new Date(2024, 0, 9), "Día de los reyes magos"));
        _lstFestivos.add(new Festivo(new Date(2024, 2, 20), "Día de San José"));        
        _lstFestivos.add(new Festivo(new Date(2024, 3, 2), "Domingo de Ramos"));
        _lstFestivos.add(new Festivo(new Date(2024, 3, 6), "Jueves santo"));
        _lstFestivos.add(new Festivo(new Date(2024, 3, 7), "viernes santo"));
        _lstFestivos.add(new Festivo(new Date(2024, 3, 9), "Domingo de Resurreción"));
        _lstFestivos.add(new Festivo(new Date(2023, 4, 1), "Día del trabajador"));
        _lstFestivos.add(new Festivo(new Date(2023, 4, 22), "Día de la Ascensión"));
        _lstFestivos.add(new Festivo(new Date(2023, 5, 12), "Corpus Christi"));
        _lstFestivos.add(new Festivo(new Date(2023, 5, 19), "Sagrado Corazón"));
        _lstFestivos.add(new Festivo(new Date(2023, 6, 3), "San Pedro y San Pablo"));
        _lstFestivos.add(new Festivo(new Date(2023, 6, 20), "Día de la Independencia"));
        _lstFestivos.add(new Festivo(new Date(2023, 7, 7), "Batalla de Boyacá"));
        _lstFestivos.add(new Festivo(new Date(2023, 7, 21), "La asunción de la Virgen"));
        _lstFestivos.add(new Festivo(new Date(2023, 9, 16), "Día de la Raza"));
        _lstFestivos.add(new Festivo(new Date(2023, 10, 6), "Todos los Santos"));
        _lstFestivos.add(new Festivo(new Date(2023, 10, 13), "Independencia de Cartagena"));
        _lstFestivos.add(new Festivo(new Date(2023, 11, 8), "Día de la Inmaculada Concepción"));
        _lstFestivos.add(new Festivo(new Date(2023, 11, 25), " Día de Navidad"));        

    }

    public String validarFechaDePago(Date date) {

        int dia = date.getDay();
        Date fechaProxima = date;

        if (date.getDate() != 15 && date.getDate() != 30) {
            return "Solo se permite ingresar los días 15 y 30";
        }

        if (dia == 0) {
            //validar si es domingo
            date.setDate(date.getDate() + 1);
            fechaProxima = encontrarFechaHabil(date);
        } else if (dia == 6) {
            //validar si es sabado
            date.setDate(date.getDate() - 1);
            fechaProxima = encontrarFechaHabil(date);
        } else {
            //dia de la semana 
            fechaProxima = encontrarFechaHabil(date);
        }
        
        // Formato de salida de la fecha
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy/MM/dd");
        String stringDate= DateFor.format(fechaProxima);
        
        
        return "La fecha próxima a pagar es: " + stringDate;
    }

    public Date encontrarFechaHabil(Date date) {
        boolean encontroFecha = false;

        while (encontroFecha == false) {

            boolean diaHabil = esDiaHabil(date);
            
            // Si es false, es porque es un día habil
            if (diaHabil == true) {
                encontroFecha = true;
            } else {
                date.setDate(date.getDate() - 1);
            }
        }
        
        return date;
    }

    // Valida si la fecha ingresada es un día NO hábil
    public boolean esDiaHabil(Date date) {

        int dia = date.getDay();

        // Validamos si es sabado o domingo
        if (dia == 0 || dia == 6) {
            return false;
        }

        // Busca en la lista de festivos SI existe la fecha que se envía
        boolean noEsFestivo = !_lstFestivos.stream().filter(o -> o.Fecha.getDate() == date.getDate() && o.Fecha.getMonth() == date.getMonth() ).findFirst().isPresent();
        
        if (noEsFestivo == false) {
            //se valida que sea festivo
            SimpleDateFormat DateFor = new SimpleDateFormat("yyyy/MM/dd");
            String stringDate= DateFor.format(date);
            observaciones = "La fecha " + stringDate + " es festivo";
        }
        
        return noEsFestivo;
    }

}
