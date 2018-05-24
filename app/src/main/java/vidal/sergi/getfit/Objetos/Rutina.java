package vidal.sergi.getfit.Objetos;

import java.util.List;

public class Rutina {

    private int img;
    private String nombre;
    private List<Musculo> musculoList;

    public Rutina(int img, String nombre, List<Musculo> musculoList) {
        this.img = img;
        this.nombre = nombre;
        this.musculoList = musculoList;
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
