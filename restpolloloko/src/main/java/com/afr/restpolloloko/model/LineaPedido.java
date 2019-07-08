package com.afr.restpolloloko.model;

public class LineaPedido {

    //Atributos
    private Producto producto;
    private int cantidad;
    private double precio;


    //Constructores
    public LineaPedido() {
    }

    public LineaPedido(Producto producto, int cantidad, double precio) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    //Getters y setters
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "LineaPedido{" +
                "producto=" + producto +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                '}';
    }
}
