package beu;

public class Curso {

    private String titulo;
    private String descripcion;
    private float costo;

    public Curso() {
    }

    public Curso(String titulo, String descripcion, float costo) {
        this.titulo = titulo;

        this.descripcion = descripcion;
        this.costo = costo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
//para leer enumerado

    @Override
    public String toString() {
        return titulo;
    }

}
