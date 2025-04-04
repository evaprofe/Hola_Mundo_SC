import java.util.ArrayList;
import java.util.Iterator;

/**
 * Actividad Q10: Banco
 * @author Pedro Górriz García
 */
class ClienteBanca {
    private String nombre;
    private String dni;
    private ArrayList<CuentaBancaria> listaCuentas;

    ClienteBanca(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
        listaCuentas = new ArrayList();
    }

    public void añadirCuenta(CuentaBancaria cuenta) {
        getListaCuentas().add(cuenta);
    }

    public void quitarCuenta(CuentaBancaria cuenta) {
        getListaCuentas().remove(cuenta);
    }

    public CuentaBancaria obtenerCuenta(int posicion) {
        CuentaBancaria cuenta;
        cuenta = getListaCuentas().get(posicion);
        return (cuenta);
    }

    public void mostrar() {
        CuentaBancaria cuentaX;
        System.out.println();
        System.out.println("Dni: " + getDni());
        System.out.println("Titular: " + getNombre());
        System.out.println("Números de cuenta asociadas:");
        Iterator itera = getListaCuentas().iterator();
        while (itera.hasNext()) {
            cuentaX = (CuentaBancaria) itera.next();
            System.out.println("# " + cuentaX.getNumeroCuenta());
        }
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @return the listaCuentas
     */
    public ArrayList<CuentaBancaria> getListaCuentas() {
        return listaCuentas;
    }

}
