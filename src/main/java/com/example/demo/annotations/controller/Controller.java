package com.example.demo.annotations.controller;


import com.example.demo.annotations.interfaces.Movimiento;
import com.example.demo.annotations.models.*;
import com.example.demo.annotations.repository.FakeDB;
import com.example.demo.annotations.services.AreaCliente;
import com.example.demo.annotations.utils.LogUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.midi.Track;
import java.util.List;

@RestController
public class Controller {
    private AreaCliente areaCliente = new AreaCliente();
    public FakeDB fakeDB = new FakeDB();

    @PostMapping("/cliente")
    public Cliente crearPrimerCliente(@RequestBody Cliente cliente){

        Cliente cliente1 = areaCliente.createPrimerCliente(cliente);
        System.out.println(cliente1.getId());
        areaCliente.subirCliente(cliente1,this.fakeDB);

        LogUtils.info("Cliente creado");
        return cliente1;

    }
    @GetMapping("/clientes")
    public List<Cliente> getClientes(){
        return this.fakeDB.getClientes();
    }

    @PostMapping("/cliente/nuevaCuenta")
    public CuentaBancaria crearCuentaBancaria(@RequestBody CuentaBancaria cuentaBancaria){


        Cliente client1 = fakeDB.getClientes().get(0);
        CuentaBancaria cuentaBancaria1 = areaCliente.crearCuentaBancaria(cuentaBancaria, client1, this.fakeDB);


        return cuentaBancaria1;

    }
    @PostMapping("/cliente/ingreso")
    public Movimiento ingreso(@RequestBody Ingreso ingreso){
        Cliente client1 = getClientes().get(0);
        Movimiento movimiento1 = areaCliente.crearIngreso(client1, this.fakeDB, ingreso);

        return movimiento1;
    }

    @PostMapping("/cliente/retiro")
    public Movimiento retiro(@RequestBody Retiro retiro){
        Cliente client1 = getClientes().get(0);
        Movimiento movimiento1 = areaCliente.crearRetiro(client1, this.fakeDB, retiro);

        return movimiento1;
    }

    @PostMapping("/cliente/transferencia")
    public Movimiento transferencia(@RequestBody Transferencia transferencia){
        Cliente client1 = getClientes().get(0);
        Movimiento movimiento1 = areaCliente.crearTransferencia(client1, this.fakeDB, transferencia);

        return movimiento1;
    }





}
