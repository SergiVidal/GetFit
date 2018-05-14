package vidal.sergi.getfit.Objetos;

import java.util.List;

public class Rutina {

    private String nombre;
    private List<Musculo> musculoList;

    public Rutina(String nombre, List<Musculo> musculoList) {
        this.nombre = nombre;
        this.musculoList = musculoList;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Musculo> getMusculoList() {
        return musculoList;
    }

    public void setMusculoList(List<Musculo> musculoList) {
        this.musculoList = musculoList;
    }

    @Override
    public String toString() {
        return "Rutina{" +
                "nombre='" + nombre + '\'' +
                ", musculoList=" + musculoList +
                '}';
    }

    public Musculo getMusculo(int pos){
        return this.musculoList.get(pos);
    }
}
