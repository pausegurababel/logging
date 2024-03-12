package com.example.demo.annotations.models;


import com.example.demo.annotations.interfaces.Movimiento;

import java.util.ArrayList;
import java.util.List;

public class CuentaBancaria {

    private String iban;

    private double saldo;

    private List<Movimiento> movimientos;

    public CuentaBancaria(){
        this.saldo = 10000;
        this.movimientos = new ArrayList<>();

    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void actualizarMovimientos(List<Movimiento> movList) {
        this.movimientos = movList;
    }
}
