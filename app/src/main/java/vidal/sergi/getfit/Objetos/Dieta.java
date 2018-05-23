package vidal.sergi.getfit.Objetos;

import java.util.List;

public class Dieta {

    private int bgColor;
    private String nombre;
    private List<Comida> comidaList;

    public Dieta(int bgColor, String nombre, List<Comida> comidaList) {
        this.bgColor = bgColor;
        this.nombre = nombre;
        this.comidaList = comidaList;
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

    public List<Comida> getComidaList() {
        return comidaList;
    }

    public void setComidaList(List<Comida> comidaList) {
        this.comidaList = comidaList;
    }

    public Comida getComida(int pos){
        return this.comidaList.get(pos);
    }
}
