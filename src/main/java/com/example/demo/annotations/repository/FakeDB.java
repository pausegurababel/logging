package com.example.demo.annotations.repository;


import com.example.demo.annotations.interfaces.Movimiento;
import com.example.demo.annotations.models.Cliente;
import com.example.demo.annotations.models.CuentaBancaria;
import com.example.demo.annotations.models.Ingreso;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class FakeDB {

    private List<Cliente> clientes;
    private HashMap<Cliente, List<CuentaBancaria>> cuentasBancarias;

    private HashMap<CuentaBancaria, List<Movimiento>> movimientos;

    public FakeDB() {
        this.clientes  = new ArrayList<>();
        this.cuentasBancarias = new HashMap<>();
        this.movimientos = new HashMap<>();
    }

    public List<Cliente> getClientes() {
        return this.clientes;
    }

    public void setCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public HashMap<Cliente, List<CuentaBancaria>> getCuentasBancarias() {
        return this.cuentasBancarias;
    }

    public void setCuentasBancarias(HashMap<Cliente, List<CuentaBancaria>> cuentasBancarias) {
        this.cuentasBancarias = cuentasBancarias;
    }

    public HashMap<CuentaBancaria, List<Movimiento>> getMovimientos() {
        return this.movimientos;
    }

    public void setMovimientos(HashMap<CuentaBancaria, List<Movimiento>> movimientos) {
        this.movimientos = movimientos;
    }
}
