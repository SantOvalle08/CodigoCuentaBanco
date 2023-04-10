package application;

import controllers.MainSceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Banco;
//import model.Cuenta;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Aplicacion extends Application {

	private Stage primaryStage;
	Banco banco;

	@Override
	public void start(Stage primaryStage) throws Exception {

		try {

			this.primaryStage = primaryStage;
			this.banco = new Banco();

			mostrarVentanaPrincipal();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void mostrarVentanaPrincipal() throws Exception {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/views/MainSceneView.fxml"));
			AnchorPane anchorPane = (AnchorPane) loader.load();
			MainSceneController mainSceneController = loader.getController();
			mainSceneController.setAplicacion(this);

			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public boolean crearCuentaAhorro(String numCuenta, float saldo, float tasaAnual){
		return banco.crearCuenta(numCuenta, saldo, tasaAnual);
	}

	public boolean crearCuentaCorriente(String numCuenta, float saldo, float tasaAnual){
		return banco.crearCuenta(numCuenta, saldo, tasaAnual);
	}

	public String buscarCuenta(String numeroCuenta) {
		String resultado = banco.buscarCuenta(numeroCuenta);
		return resultado;
	}

	public boolean retirarCorriente(String numeroCuenta, float saldo) {
		return banco.retirarCorriente(numeroCuenta, saldo);
	}

	public boolean retirarAhorros(String numeroCuenta, float saldo) {
		return banco.retirarAhorro(numeroCuenta, saldo);
	}

	public boolean consignarCorriente(String numeroCuenta, float saldo) {
		return banco.consignarCorriente(numeroCuenta, saldo);
	}

	public boolean consignarAhorros(String numeroCuenta, float saldo) {
		return banco.consignarAhorro(numeroCuenta, saldo);
	}

}
