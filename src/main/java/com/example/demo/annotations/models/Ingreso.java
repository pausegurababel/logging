package com.example.demo.annotations.models;

import com.example.demo.annotations.interfaces.Movimiento;

public class Ingreso implements Movimiento {
    private String destino;
    private double cantidad;

    private String tipo = "ingreso";

    public Ingreso(String destino, double cantidad) {
        this.destino = destino;
        this.cantidad = cantidad;
    }

    @Override
    public String getDestino() {
        return destino;
    }

    @Override
    public String getOrigen() {
        return null; // No aplicable para ingresos
    }

    @Override
    public String getTipo(){
        return tipo;
    }

    @Override
    public double getCantidad() {
        return cantidad;
    }
}