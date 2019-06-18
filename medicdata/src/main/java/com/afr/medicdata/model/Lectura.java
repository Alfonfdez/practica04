package com.afr.medicdata.model;

import java.io.Serializable;
import java.util.Date;

public class Lectura implements Serializable {

    //Una clase que implementa 'Serializable' es un objeto que  puede abandonar la memoria para viajar por Internet

    //Atributos
    private Integer codigo;
    //private Date fechaHora;
    private Date fecha;
    private Date hora;
    private double peso;
    private double diastolica;
    private double sistolica;
    private double longitud;
    private double latitud;

    //Constructores
    //Constructor sin argumentos
    public Lectura(){

    }

    //Constructores con argumentos
    //public Lectura(Date fechaHora, double peso, double diastolica, double sistolica, double longitud, double latitud) {
        //this.fechaHora = fechaHora;
    public Lectura(Date fecha, Date hora, double peso, double diastolica, double sistolica, double longitud, double latitud) {
        this.fecha = fecha;
        this.hora = hora;
        this.peso = peso;
        this.diastolica = diastolica;
        this.sistolica = sistolica;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    //public Lectura(Date fechaHora, double peso, double diastolica, double sistolica) {
        //this.fechaHora = fechaHora;

    public Lectura(Date fecha, Date hora, double peso, double diastolica, double sistolica) {
        this.fecha = fecha;
        this.hora = hora;
        this.peso = peso;
        this.diastolica = diastolica;
        this.sistolica = sistolica;
    }

    //Getters y setters
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /*public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }*/

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date Hora) {
        this.hora = hora;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getDiastolica() {
        return diastolica;
    }

    public void setDiastolica(double diastolica) {
        this.diastolica = diastolica;
    }

    public double getSistolica() {
        return sistolica;
    }

    public void setSistolica(double sistolica) {
        this.sistolica = sistolica;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    //Métodos
    /*@Override
    public String toString() {
        return "Lectura{" +
                "codigo=" + codigo +
                ", fechaHora=" + fechaHora +
                ", peso=" + peso +
                ", diastolica=" + diastolica +
                ", sistolica=" + sistolica +
                ", longitud=" + longitud +
                ", latitud=" + latitud +
                '}';
    }*/

    @Override
    public String toString() {
        return "Lectura{" +
                "codigo=" + codigo +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", peso=" + peso +
                ", diastolica=" + diastolica +
                ", sistolica=" + sistolica +
                ", longitud=" + longitud +
                ", latitud=" + latitud +
                '}';
    }

    //1 - Alt + Insert (Generate...)
    //2 - equals() and hashCode()
    //3 - pestaña -> IntelliJ
    //4 - Seleccionamos sólo 'codigo'
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lectura lectura = (Lectura) o;

        return codigo != null ? codigo.equals(lectura.codigo) : lectura.codigo == null;
    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }
}
