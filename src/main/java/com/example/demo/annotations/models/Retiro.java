package com.example.demo.annotations.models;

import com.example.demo.annotations.interfaces.Movimiento;

public class Retiro implements Movimiento {
    private String origen;
    private double cantidad;

    private String tipo = "retiro";

    public Retiro(String origen, double cantidad) {
        this.origen = origen;
        this.cantidad = cantidad;
    }

    @Override
    public String getOrigen() {
        return origen;
    }

    @Override
    public String getDestino() {
        return null; // No aplicable para retiros
    }

    @Override
    public double getCantidad() {
        return cantidad;
    }

    @Override
    public String getTipo(){
        return tipo;
    }
}
