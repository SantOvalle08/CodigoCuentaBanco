package model;

public class CuentaAhorros extends Cuenta {

	/**
	 * ATRIBUTOS
	 */

	protected boolean cuentaActiva;

	public CuentaAhorros() {
	}

	public CuentaAhorros(boolean cuentaActiva) {
		this.cuentaActiva = cuentaActiva;
	}

	public CuentaAhorros(String numeroCuenta, float saldo, float tasa_anual) {
		super(numeroCuenta,saldo, tasa_anual);
	}

	public boolean isCuentaActiva() {
		return cuentaActiva;
	}

	public void setCuentaActiva(boolean cuentaActiva) {
		this.cuentaActiva = cuentaActiva;
	}

	@Override
	public String toString() {
		return "Cuenta ahorro\n" + super.toString() + "\nLa cuenta está activa: " + cuentaActiva;
	}

	@Override
	public String consignarSaldo(float saldoConsignar) {
		String salida = "";
		if (this.saldo >= 10000) {
			cuentaActiva = true;
		} else {
			cuentaActiva = false;
		}

		if (cuentaActiva) {
			salida = super.consignarSaldo(saldoConsignar);
		} else {
			salida = "La cuenta está inactiva";
		}
		return salida;
	}

	@Override
	public String retirarSaldo(float saldoRetirar) {
		String salida = "";
		if (this.saldo >= 10000) {
			cuentaActiva = true;
		} else {
			cuentaActiva = false;
		}

		if (cuentaActiva) {
			salida = super.retirarSaldo(saldoRetirar);
		} else {
			salida = "La cuenta está inactiva";
		}

		return salida;
	}

}
