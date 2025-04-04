package Archivos;

import ADO.*;
import MisListas.*;
import java.io.*;

public class Ficheros {

    public static boolean guardarArticulos(ListaArticulos listaA) {
        FileOutputStream fs;
        ObjectOutputStream oos;
        Articulo a;
        boolean guardado = false;
        //escritura del fichero
        try {
            fs = new FileOutputStream("./src/Archivos/Articulos.txt");
            oos = new ObjectOutputStream(fs);
            for (int i = 0; i < listaA.numArticulos(); i++) {
                a = (Articulo) listaA.getArticulo(i);
                oos.writeObject(a); //escribe el objeto en el flujo salida
                System.out.println(a.getCodArt());
            }
            System.out.println("Array Almacenado");
            // Cerramos los flujos abiertos una vez utilizados
            oos.close();
            fs.close();
            guardado = true;
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
            guardado = false;
        }
        return guardado;
    }

    public static boolean cargarArticulos(ListaArticulos listaA) {
        FileInputStream fe;
        DataInputStream d;
        ObjectInputStream ois;
        File f;
        Articulo v;
        boolean cargado = false;
        // lectura del fichero (de manera análoga a la escritura)
        listaA.vaciarLista();
        try {
            f = null;
            fe = null;
            d = null;
            try {
                f = new File("./src/Archivos/Articulos.txt");
                if (f.exists()) {
                    // Asociamos el FileInputStream con el ObjectInputStream
                    fe = new FileInputStream(f);
                    ois = new ObjectInputStream(fe);
                    while (fe.available() > 0) {
                        v = (Articulo) ois.readObject();
                        cargado = listaA.anadirArticulo(v);
                        System.out.println(v.info());
                    }
                    System.out.println("Array cargado");
                }
            } catch (EOFException eof) {
                System.err.println("Fin de Fichero encontrado ");
            } catch (FileNotFoundException fnf) {
                System.err.println("Fichero no encontrado " + fnf);
            } catch (IOException e) {
                System.err.println("Se ha producido una IOException" + e.toString());
            } catch (ClassNotFoundException e) {
                System.err.println("Error de programa" + e);
            } finally {
                if (d != null) {
                    d.close();
                    fe.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cargado;
    }

}
