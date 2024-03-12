package com.example.demo.annotations.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cliente {
    private Integer id;
    private String nombre;

    private String apellido;

    private List<CuentaBancaria> cuentasBancarias;

    public Cliente(Integer id, String nombre, String apellido){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cuentasBancarias = new ArrayList<>();
    }

    public void actualizarCuentasBancarias(List<CuentaBancaria> cuentas) {
        this.cuentasBancarias = cuentas;

    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public List<CuentaBancaria> getCuentasBancarias() {
        return cuentasBancarias;
    }
}
