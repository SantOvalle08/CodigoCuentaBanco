package test;

import javax.swing.JOptionPane;

import model.Cuenta;
import model.CuentaAhorros;
import model.CuentaCorriente;
import views.Funciones;

public class test {
    public static void main(String[] args) {
        menu();
    }

    	/**
		 * 			Se crea una clase main en la que se realizan las diferente pruebas,
		 * 		se hace de esta manera para que sea el usuario quien digite la información
		 * 				y así sea más entendible y agradable para la vista.
		 */

    public static void menu(){
        int opc;
        Cuenta cuenta= new Cuenta("1234",50000, 175 );
        Cuenta cuenta2= new CuentaAhorros("12",30000, 175);
        Cuenta cuenta3= new CuentaCorriente("34",40000, 175);

        do{
            opc= Funciones.leerNumero("1.Mostrar cuenta \n2.Consignar \n3.Retirar \n4.Consignacion ahorros \n5.Retiro ahorros \n6.Consignacion corriente \n7.Retiro corriente \n8.Salir");

            switch(opc){
                case 1:
                JOptionPane.showMessageDialog(null, cuenta3.toString());
                break;
                case 2:
                float saldoConsignacion= Funciones.leerRealPeque("Ingrese el valor a consignar: ");
                JOptionPane.showMessageDialog(null, cuenta.consignarSaldo(saldoConsignacion));
                break;
                case 3:
                float saldoRetiro= Funciones.leerRealPeque("Ingrese el valor que desea retirar: ");
                JOptionPane.showMessageDialog(null, cuenta.retirarSaldo(saldoRetiro));
                break;
                case 4:
                float saldoConsignacionAhorro= Funciones.leerRealPeque("Ingrese el valor a consignar");
                JOptionPane.showMessageDialog(null, cuenta2.consignarSaldo(saldoConsignacionAhorro));
                break;
                case 5:
                float saldoRetiroAhorro= Funciones.leerRealPeque("Ingrese el valor a retirar");
                JOptionPane.showMessageDialog(null, cuenta2.retirarSaldo(saldoRetiroAhorro));
                break;
                case 6:
                float saldoConsignacionCorriente= Funciones.leerRealPeque("Ingrese el valor a consignar");
                JOptionPane.showMessageDialog(null, cuenta3.consignarSaldo(saldoConsignacionCorriente));
                break;
                case 7:
                float saldoRetiroCorriente= Funciones.leerRealPeque("Ingrese el valor a retirar");
                JOptionPane.showMessageDialog(null, cuenta3.retirarSaldo(saldoRetiroCorriente));
                break;
                case 8:
                JOptionPane.showMessageDialog(null, "Saliendo");
                break;
                default:
                JOptionPane.showMessageDialog(null, "Opcion invalida");
                break;
            }


        }while(opc!=8);
    }


}
