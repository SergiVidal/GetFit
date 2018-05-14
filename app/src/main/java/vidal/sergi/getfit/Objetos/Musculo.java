package vidal.sergi.getfit.Objetos;

import android.media.Image;

/**
 * Created by alu2011543 on 23/03/2018.
 */

public class Musculo {

    private String nombre;
    private Image image;

    public Musculo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Musculo{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
