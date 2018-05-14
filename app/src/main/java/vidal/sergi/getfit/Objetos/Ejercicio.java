package vidal.sergi.getfit.Objetos;

import android.media.Image;

/**
 * Created by alu2011543 on 23/03/2018.
 */

public class Ejercicio {
    private int series;
    private int repeticiones;
    private double descanso;
    private Image image;

    public Ejercicio(int series, int repeticiones, double descanso) {
        this.series = series;
        this.repeticiones = repeticiones;
        this.descanso = descanso;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public double getDescanso() {
        return descanso;
    }

    public void setDescanso(double descanso) {
        this.descanso = descanso;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Ejercicio{" +
                "series=" + series +
                ", repeticiones=" + repeticiones +
                ", descanso=" + descanso +
                ", image=" + image +
                '}';
    }
}
