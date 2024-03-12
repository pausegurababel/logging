package com.example.demo.annotations.services;

import com.example.demo.annotations.interfaces.Movimiento;
import com.example.demo.annotations.models.*;
import com.example.demo.annotations.repository.FakeDB;
import org.springframework.stereotype.Service;

import javax.swing.undo.CannotUndoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
public class AreaCliente {

    private CuentaBancaria cuentaBancaria;
    private Ingreso movimiento;

    private FakeDB fakeDB;

    private List<Movimiento> movimientoList;

    private List<CuentaBancaria> cuentasBancarias;

    public AreaCliente() {
        this.movimientoList = new ArrayList<>();
        this.cuentasBancarias = new ArrayList<>();
    }

    public Cliente createPrimerCliente(Cliente cliente){

        Cliente cliente1 = new Cliente(cliente.getId(), cliente.getNombre(), cliente.getApellido());

        cuentaBancaria = new CuentaBancaria();
        cuentaBancaria.setIban("ES10 0000 4563 4512 6789 7752");
        Movimiento movimiento = new Transferencia(cuentaBancaria.getIban(), "ES10 4367 2321 4567 1212 4444", 35.34);
        movimientoList.add(movimiento);
        double restante = calcularSaldo(cuentaBancaria, movimiento);
        cuentaBancaria.setSaldo(cuentaBancaria.getSaldo() + restante);
        movimiento = new Transferencia(cuentaBancaria.getIban(), "ES10 7676 2321 8888 0000 2323", 109.22);
        movimientoList.add(movimiento);
        cuentaBancaria.actualizarMovimientos(movimientoList);
        cuentasBancarias.add(cuentaBancaria);
        restante = calcularSaldo(cuentaBancaria, movimiento);
        cuentaBancaria.setSaldo(cuentaBancaria.getSaldo() + restante);
        cliente1.actualizarCuentasBancarias(cuentasBancarias);


        return cliente1;
    }

    private String generarIBAN(){
        String ibanPrefijo = "ES10 ";

        StringBuilder ibanAleatorio = new StringBuilder();

        Random random = new Random();

        for (int i = 1; i <= 20;i++){
            int digitoAleatorio = random.nextInt(10);
            if (i % 4 == 0 && i != 20){{
                ibanAleatorio.append(" ");
            }
            ibanAleatorio.append(digitoAleatorio);
            }
        }
        return ibanPrefijo + ibanAleatorio.toString();
    }

    public void subirCliente(Cliente cliente, FakeDB fakeDB){
        fakeDB.setCliente(cliente);
    }
    private double calcularSaldo(CuentaBancaria cuentaBancaria, Movimiento movimiento){
        double cantidadInicial = 0.0;

        String tipo = movimiento.getTipo();
        if (tipo.equalsIgnoreCase("ingreso")){
                cantidadInicial += movimiento.getCantidad();
        }
        else if (tipo.equalsIgnoreCase("retiro")){
            cantidadInicial -= movimiento.getCantidad();

        }
        else{
            if (movimiento.getOrigen().equalsIgnoreCase(cuentaBancaria.getIban())){
                cantidadInicial -= movimiento.getCantidad();
            }
            else{
                cantidadInicial += movimiento.getCantidad();
            }
        }
    return cantidadInicial;
    }

    public CuentaBancaria crearCuentaBancaria(CuentaBancaria cuentaBancaria, Cliente client1, FakeDB fakeDB){



        CuentaBancaria cuentaBancaria1 = new CuentaBancaria();
        cuentaBancaria1.setIban(cuentaBancaria.getIban());
        List<CuentaBancaria> listaCuentas = client1.getCuentasBancarias();
        listaCuentas.add(cuentaBancaria1);

        client1.actualizarCuentasBancarias(listaCuentas);

        subirCuentaBancaria(client1, fakeDB, listaCuentas);
        return cuentaBancaria1;

    }

    public void subirCuentaBancaria(Cliente cliente1, FakeDB fakeDB, List<CuentaBancaria> cuentasBancarias){
        HashMap<Cliente, List<CuentaBancaria>> mapa = fakeDB.getCuentasBancarias();
        mapa.put(cliente1, cuentasBancarias);
        fakeDB.setCuentasBancarias(mapa);

    }

    public void subirMovimiento(Cliente cliente, FakeDB fakeDB, List<Movimiento> movimientos, CuentaBancaria cuentaBancaria){
        HashMap<CuentaBancaria, List<Movimiento>> mapa = fakeDB.getMovimientos();
        mapa.put(cuentaBancaria, movimientos);
        fakeDB.setMovimientos(mapa);
        subirCuentaBancaria(cliente, fakeDB, cliente.getCuentasBancarias());
    }

    public Movimiento crearIngreso(Cliente cliente, FakeDB fakeDB, Movimiento movimiento){

        List<CuentaBancaria> cuentasBancarias = cliente.getCuentasBancarias();
        Movimiento mov1 = new Ingreso(movimiento.getDestino(), movimiento.getCantidad());
        for (int i = 0; i < cuentasBancarias.size();i++){
            if(cuentasBancarias.get(i).getIban().equalsIgnoreCase(movimiento.getDestino())){
                List<Movimiento> movimientos = cuentasBancarias.get(i).getMovimientos();
                movimientos.add(mov1);
                cuentasBancarias.get(i).actualizarMovimientos(movimientos);
                double restante = calcularSaldo(cuentasBancarias.get(i), mov1);
                cuentasBancarias.get(i).setSaldo(cuentasBancarias.get(i).getSaldo() + restante);
                cliente.actualizarCuentasBancarias(cuentasBancarias);
                //cuentasBancarias.get(i).actualizarMovimientos(movimientos);
                subirMovimiento(cliente, fakeDB, movimientos, cuentasBancarias.get(i));
            }
        }

        return mov1;
    }
    public Movimiento crearRetiro(Cliente cliente, FakeDB fakeDB, Movimiento movimiento){

        List<CuentaBancaria> cuentasBancarias = cliente.getCuentasBancarias();
        Movimiento mov1 = new Retiro(movimiento.getOrigen(), movimiento.getCantidad());
        for (int i = 0; i < cuentasBancarias.size();i++){
            if(cuentasBancarias.get(i).getIban().equalsIgnoreCase(movimiento.getOrigen())){
                List<Movimiento> movimientos = cuentasBancarias.get(i).getMovimientos();
                movimientos.add(mov1);
                cuentasBancarias.get(i).actualizarMovimientos(movimientos);
                double restante = calcularSaldo(cuentasBancarias.get(i), mov1);
                cuentasBancarias.get(i).setSaldo(cuentasBancarias.get(i).getSaldo() + restante);
                cliente.actualizarCuentasBancarias(cuentasBancarias);
                //cuentasBancarias.get(i).actualizarMovimientos(movimientos);
                subirMovimiento(cliente, fakeDB, movimientos, cuentasBancarias.get(i));
            }
        }

        return mov1;
    }
    public Movimiento crearTransferencia(Cliente cliente, FakeDB fakeDB, Movimiento movimiento){

        List<CuentaBancaria> cuentasBancarias = cliente.getCuentasBancarias();
        Movimiento mov1 = new Transferencia(movimiento.getOrigen(), movimiento.getDestino(), movimiento.getCantidad());
        for (int i = 0; i < cuentasBancarias.size();i++){
            if(cuentasBancarias.get(i).getIban().equalsIgnoreCase(movimiento.getOrigen())){
                List<Movimiento> movimientos = cuentasBancarias.get(i).getMovimientos();
                movimientos.add(mov1);
                cuentasBancarias.get(i).actualizarMovimientos(movimientos);
                double restante = calcularSaldo(cuentasBancarias.get(i), mov1);
                cuentasBancarias.get(i).setSaldo(cuentasBancarias.get(i).getSaldo() + restante);
                cliente.actualizarCuentasBancarias(cuentasBancarias);
                //cuentasBancarias.get(i).actualizarMovimientos(movimientos);
                subirMovimiento(cliente, fakeDB, movimientos, cuentasBancarias.get(i));
            }
        }

        return mov1;
    }


}
