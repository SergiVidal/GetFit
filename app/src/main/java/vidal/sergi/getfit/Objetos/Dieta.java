package vidal.sergi.getfit.Objetos;

import java.util.List;

public class Dieta {

    private int img;
    private String nombre;
    private List<Comida> comidaList;

    public Dieta(int img, String nombre, List<Comida> comidaList) {
        this.img = img;
        this.nombre = nombre;
        this.comidaList = comidaList;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
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
