package vidal.sergi.getfit.Objetos;

import java.util.List;

public class Rutina {

    private int bgColor;
    private String nombre;
    private List<Musculo> musculoList;

    public Rutina(int bgColor, String nombre, List<Musculo> musculoList) {
        this.bgColor = bgColor;
        this.nombre = nombre;
        this.musculoList = musculoList;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
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

    public Musculo getMusculo(int pos){
        return this.musculoList.get(pos);
    }
}
