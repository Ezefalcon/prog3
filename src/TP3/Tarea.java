package TP3;

/**
 * Created by efalcon
 */
public class Tarea {
    private String nombre;
    private String descripción;
    private int duración;

    public Tarea(String nombre, String descripción, int duración) {
        this.nombre = nombre;
        this.descripción = descripción;
        this.duración = duración;
    }

    public Tarea(String nombre, int duración) {
        this.nombre = nombre;
        this.duración = duración;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public int getDuración() {
        return duración;
    }

    public void setDuración(int duración) {
        this.duración = duración;
    }
}
