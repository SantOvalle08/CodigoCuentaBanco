package model;

import java.util.ArrayList;
import java.util.List;

public class Banco {

	/**
	 * ATRIBUTOS
	 */

	private String nombre;
	private List<Cuenta> listaCuentas = new ArrayList<>();

	public Banco(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Banco() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cuenta> getListaCuentas() {
		return listaCuentas;
	}

	public void setListaCuentas(List<Cuenta> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	public CuentaAhorros consultarCuenta(String numeroCuenta) {
		if (verificarCuenta(numeroCuenta)) {
			for (int i = 0; i < listaCuentas.size(); i++) {
				if (listaCuentas.get(i).getNumeroCuenta().equals(numeroCuenta)) {
					return (CuentaAhorros) listaCuentas.get(i);
				}
			}
		}
		return null;
	}

	public CuentaCorriente consultarCuentaCorriente(String numeroCuenta) {
		if (verificarCuenta(numeroCuenta)) {
			for (int i = 0; i < listaCuentas.size(); i++) {
				if (listaCuentas.get(i).getNumeroCuenta().equals(numeroCuenta)) {
					return (CuentaCorriente) listaCuentas.get(i);
				}
			}
		}
		return null;
	}

	public boolean crearCuenta(String numeroCuenta, float saldo, float tasaAnual) {

		if (!verificarCuenta(numeroCuenta)) {
			Cuenta cuenta = new CuentaAhorros(numeroCuenta, saldo, tasaAnual);
			listaCuentas.add(cuenta);
			return true;
		}
		return false;
	}

	public boolean verificarCuenta(String numeroCuenta) {
		boolean encontrado = false;
		for (Cuenta cuenta : listaCuentas) {
			if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
				encontrado = true;
				return encontrado;
			}
		}
		return encontrado;
	}

	public boolean consignarAhorro(String numeroCuenta, float saldoConsignar) {
		CuentaAhorros cuenta = new CuentaAhorros();
		boolean valor = false;
		cuenta = (CuentaAhorros) consultarCuenta(numeroCuenta);
		if (cuenta != null) {
			cuenta.consignarSaldo(saldoConsignar);
			valor = true;
		}
		return valor;
	}

	public boolean retirarAhorro(String numeroCuenta, float saldoRetirar) {
		boolean valor = false;
		CuentaAhorros cuenta = new CuentaAhorros();
		cuenta = (CuentaAhorros) consultarCuenta(numeroCuenta);
		if (cuenta != null) {
			cuenta.retirarSaldo(saldoRetirar);
			valor = true;
		}
		return valor;
	}

	public float calcularExtractoMensualAhorro(String numeroCuenta) {
		CuentaAhorros cuenta = consultarCuenta(numeroCuenta);
		float extracto = cuenta.extractoMensual();
		return extracto;
	}

	public float calcularExtractoMensualCorriente(String numeroCuenta) {
		float extracto = 0;
		return extracto;
	}

	public String buscarCuenta(String numeroCuenta) {
		String cuenta1 = "";
		for (Cuenta cuenta : listaCuentas) {
			if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
				cuenta1 +=  cuenta.toString();
				return cuenta1;
			}
		}
		return cuenta1;
	}

	public boolean retirarCorriente(String numeroCuenta, float saldoRetirar) {
		boolean valor = false;
		CuentaCorriente cuenta = new CuentaCorriente();
		cuenta = (CuentaCorriente) consultarCuentaCorriente(numeroCuenta);
		if (cuenta != null) {
			cuenta.retirarSaldo(saldoRetirar);
			valor = true;
		}
		return valor;
	}

	public boolean consignarCorriente(String numeroCuenta, float saldoConsignar) {
		CuentaCorriente cuenta = new CuentaCorriente();
		boolean valor = false;
		cuenta = (CuentaCorriente) consultarCuentaCorriente(numeroCuenta);
		if (cuenta != null) {
			cuenta.consignarSaldo(saldoConsignar);
			valor = true;
		}
		return valor;
	}

}
