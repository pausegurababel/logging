package com.example.demo.annotations.models;

import com.example.demo.annotations.interfaces.Movimiento;

public class Transferencia implements Movimiento {
    private String origen;
    private String destino;
    private double cantidad;

    private String tipo = "transferencia";

    public Transferencia(String origen, String destino, double cantidad) {
        this.origen = origen;
        this.destino = destino;
        this.cantidad = cantidad;
    }

    @Override
    public String getOrigen() {
        return origen;
    }

    @Override
    public String getDestino() {
        return destino;
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