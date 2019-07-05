package com.afr.restpolloloko.model;

public class Camarero {

    //Atributos
    private int codigo;
    private String nombre;

    //Contructores
    public Camarero(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Camarero() {
    }

    //Getters y setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
