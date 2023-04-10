package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Aplicacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.Banco;

public class MainSceneController implements Initializable {
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextArea txtSaldoInicial;

	@FXML
	private TextArea txtTasaAnual;

	@FXML
	private Button btnCrearCuenta;

	@FXML
	private Button btnConsignarAhorros;

	@FXML
	private ComboBox<String> comboBoxTipoCuenta;

	@FXML
	private TextField textFieldDatosCuenta;

	@FXML
	private Button btnRetirarCorriente;

	@FXML
	private TextArea txtNumCuenta;

	@FXML
	private Button btnConsultar;

	@FXML
	private TextArea txtValorTrans;

	@FXML
	private Button btnRetirarAhorros;

	@FXML
	private Button btnConsignarCorriente;

	@FXML
	private TextArea txtNumeroCuenta;

	private Aplicacion aplicacion;

	private Banco banco;

	@FXML
	void agregarCuenta(ActionEvent event) {

		String numeroCuenta = txtNumeroCuenta.getText();
		float saldoInicial = Float.parseFloat(txtSaldoInicial.getText());
		String tipoCuenta = comboBoxTipoCuenta.getValue().toString();
		float tasaAnual = Float.parseFloat(txtTasaAnual.getText());

		/**
		 * 0. User: Ingresa los datos 1. Worker: Captura los datos 2. Worker:
		 * Valida la informacion 3. Worker: Si esta bien -> debe registrar 3.1
		 * Si está mal -> debe mostrar un mensaje en el que indique 3.2 que la
		 * información no es valida.
		 */

		if (datosValidos(numeroCuenta, saldoInicial, tasaAnual)) {

			crearCuenta(numeroCuenta, saldoInicial, tasaAnual, tipoCuenta);
			mostrarMensaje("Notificacion de Cuenta", "Informacion de Cuenta valida", "La cuenta ha sido creada",
					AlertType.INFORMATION);

		} else {

			// notificar al usuario que la información es invalida.
			mostrarMensaje("Notificacion de Cuenta", "Informacion de Cuenta Inválidad",
					"Por favor Ingrese información de " + "cuenta valida", AlertType.WARNING);
		}

	}

	/**
	 * Metodo para crear Alertas de JavaFx de manera recursiva
	 *
	 * @param titulo
	 * @param header
	 * @param contenido
	 * @param alertType
	 */

	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();

	}

	private void crearCuenta(String numeroCuenta, float saldoInicial, float tasaAnual, String tipoCuenta) {

		if (tipoCuenta.equals("AHORROS")) {
			if (aplicacion.crearCuentaAhorro(numeroCuenta, saldoInicial, tasaAnual) == true) {

				// notificar que la cuenta ha sido creada exitosamente
				mostrarMensaje("Notificacion de Cuenta", "Información de cuenta valida",
						"La cuenta ha sido creada exitosamente", AlertType.INFORMATION);

			} else {

				// notificar que la cuenta no se ha podido crear

				mostrarMensaje("Notificacion de Cuenta", "Información de cuenta invalida",
						"La cuenta no se ha podido crear, verifique "
								+ "los datos ingresados o que no tenga ya una cuenta existente",
								AlertType.WARNING);
			}
		}

		// notificar que el cliente fue registrado o no.
		if (tipoCuenta.equals("CORRIENTE")) {
			if (aplicacion.crearCuentaAhorro(numeroCuenta, saldoInicial, tasaAnual) == true) {

				// notificar que la cuenta ha sido creada exitosamente
				mostrarMensaje("Notificacion de Cuenta", "Información de cuenta valida",
						"La cuenta ha sido creada exitosamente", AlertType.INFORMATION);

			} else {

				// notificar que la cuenta no se ha podido crear

				mostrarMensaje("Notificacion de Cuenta", "Información de cuenta invalida",
						"La cuenta no se ha podido crear, verifique "
								+ "los datos ingresados o que no tenga ya una cuenta existente",
								AlertType.WARNING);
			}
		}
	}

	private boolean datosValidos(String numeroCuenta, float saldoInicial, float tasaAnual) {
		String notificacion = "";

		if (numeroCuenta == null || numeroCuenta.equals("")) {
			notificacion += "El numero de cuenta es inválido\n";
		}
		if (saldoInicial < 0) {
			notificacion += "El saldo Inicial es inválido\n";
		}
		if (tasaAnual < 0) {
			notificacion += "El tipo de cuenta es inválido\n";
		}
		if (notificacion.equals("")) {
			return true;
		}

		mostrarMensaje("Notificacion Cuenta", "Información de Cuenta Inválida", notificacion, AlertType.WARNING);

		return false;
	}

	@FXML
	void consultarCuenta(ActionEvent event) {
		String numeroCuenta = txtNumeroCuenta.toString();
		String datosCuenta = aplicacion.buscarCuenta(numeroCuenta);
		textFieldDatosCuenta.setText(datosCuenta);
	}

	@FXML
	void retirarCorriente(ActionEvent event) {
		String numeroCuenta = txtNumCuenta.toString();
		float saldo = Float.parseFloat(txtValorTrans.toString());
		if (aplicacion.retirarCorriente(numeroCuenta, saldo)) {
			mostrarMensaje("Notificacion Cuenta", "Retiro Completado", "El retiro de su cuenta se ha hecho con éxito",
					AlertType.INFORMATION);
		} else {
			mostrarMensaje("Notificacion Cuenta", "Retiro fallido",
					"El retiro de su cuenta no se ha realizado, verifique el monto o la cuenta", AlertType.WARNING);

		}
	}

	@FXML
	void retirarAhorros(ActionEvent event) {
		String numeroCuenta = txtNumCuenta.toString();
		float saldo = Float.parseFloat(txtValorTrans.toString());
		if (aplicacion.retirarAhorros(numeroCuenta, saldo)) {
			mostrarMensaje("Notificacion Cuenta", "Retiro Completado", "El retiro de su cuenta se ha hecho con éxito",
					AlertType.INFORMATION);
		} else {
			mostrarMensaje("Notificacion Cuenta", "Retiro fallido",
					"El retiro de su cuenta no se ha realizado, verifique el monto o la cuenta", AlertType.WARNING);

		}
	}

	@FXML
	void consignarCorriente(ActionEvent event) {
		String numeroCuenta = txtNumCuenta.toString();
		float saldo = Float.parseFloat(txtValorTrans.toString());
		if (aplicacion.consignarCorriente(numeroCuenta, saldo)) {
			mostrarMensaje("Notificacion Cuenta", "Consignacion Completada",
					"La consignacion de su cuenta se ha hecho con éxito", AlertType.INFORMATION);
		} else {
			mostrarMensaje("Notificacion Cuenta", "Consignacion fallida",
					"La consignacion de su cuenta no se ha realizado, verifique el monto o la cuenta",
					AlertType.WARNING);

		}
	}

	@FXML
	void consignarAhorros(ActionEvent event) {
		String numeroCuenta = txtNumCuenta.toString();
		float saldo = Float.parseFloat(txtValorTrans.toString());
		if (aplicacion.consignarAhorros(numeroCuenta, saldo)) {
			mostrarMensaje("Notificacion Cuenta", "Consignacion Completada",
					"La consignacion de su cuenta se ha hecho con éxito", AlertType.INFORMATION);
		} else {
			mostrarMensaje("Notificacion Cuenta", "Consignacion fallida",
					"La consignacion de su cuenta no se ha realizado, verifique el monto o la cuenta",
					AlertType.WARNING);

		}

	}

	@FXML
	void initialize() {
		assert txtSaldoInicial != null : "fx:id=\"txtSaldoInicial\" was not injected: check your FXML file 'MainSceneView.fxml'.";
		assert txtTasaAnual != null : "fx:id=\"txtTasaAnual\" was not injected: check your FXML file 'MainSceneView.fxml'.";
		assert btnCrearCuenta != null : "fx:id=\"btnCrearCuenta\" was not injected: check your FXML file 'MainSceneView.fxml'.";
		assert btnConsignarAhorros != null : "fx:id=\"btnConsignarAhorros\" was not injected: check your FXML file 'MainSceneView.fxml'.";
		assert comboBoxTipoCuenta != null : "fx:id=\"comboBoxTipoCuenta\" was not injected: check your FXML file 'MainSceneView.fxml'.";
		assert textFieldDatosCuenta != null : "fx:id=\"textFieldDatosCuenta\" was not injected: check your FXML file 'MainSceneView.fxml'.";
		assert btnRetirarCorriente != null : "fx:id=\"btnRetirarCorriente\" was not injected: check your FXML file 'MainSceneView.fxml'.";
		assert btnConsultar != null : "fx:id=\"btnConsultar\" was not injected: check your FXML file 'MainSceneView.fxml'.";
		assert txtValorTrans != null : "fx:id=\"txtValorTrans\" was not injected: check your FXML file 'MainSceneView.fxml'.";
		assert btnRetirarAhorros != null : "fx:id=\"btnRetirarAhorros\" was not injected: check your FXML file 'MainSceneView.fxml'.";
		assert btnConsignarCorriente != null : "fx:id=\"btnConsignarCorriente\" was not injected: check your FXML file 'MainSceneView.fxml'.";
		assert txtNumeroCuenta != null : "fx:id=\"txtNumeroCuenta\" was not injected: check your FXML file 'MainSceneView.fxml'.";

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> opcionesTipoCuenta = FXCollections.observableArrayList("AHORROS", "CORRIENTE");
		comboBoxTipoCuenta.setItems(opcionesTipoCuenta);
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
		this.banco = aplicacion.getBanco();
	}

}
