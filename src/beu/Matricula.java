package beu;

import Torres.Persona;
import Torres.UNIDADES;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class Matricula {

    //datos
    private final String numero;
    private Calendar fecha;
    private Estado estado;
    private Persona estudiante;
    private Curso curso;
    private final List<Calificacion> calificaciones = new ArrayList<>();
    //informacion solo con getter porque ya lo colocamos nostros

    private float promedio;

    //objetos creados a traves del contructor
    public Matricula() {
        fecha = Calendar.getInstance();
        estado = estado.REGISTRADA;
        UUID numrandomico = UUID.randomUUID();
        this.numero = numrandomico.toString();

    }

    public String getNumero() {
        return numero;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public float getPromedio() {
        return promedio;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Persona getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Persona estudiante) {
        this.estudiante = estudiante;
    }

    public void calcularPromedio() {
        //si es vacio retorna, se acaba
        if (this.calificaciones.isEmpty()) {
            return;
        }
        float suma = 0;
        for (Calificacion c : calificaciones) {
            suma += c.valor;//se puede acceder al atributo porque es clase interna

        }
        int divisor = this.calificaciones.size();
        promedio = (float) suma / (float) divisor;//en funcion del numero de notas registradas
        if (divisor == 3) {
            if (promedio > 14) {
                this.estado = Estado.APROBADA;
            } else {
                this.estado = Estado.REPROBADA;
            }
        }

    }

    /*
    el metodo registra la calificacion y retorna un numero 
    1 si es la nota de la unidad I
    2 si es l anota d ela unidad II
    3 si es la nota de la unidad III
    0 si ya tiene todas las notas */
    public int addCalificacion(float v) {
        Calificacion cal = new Calificacion();
        int cuentaNotas = this.calificaciones.size();
        switch (cuentaNotas) {
            case 0:
                cal.setUnidad(UNIDADES.I);
                break;
            case 1:
                cal.setUnidad(UNIDADES.II);
                break;
            case 2:
                cal.setUnidad(UNIDADES.III);
                break;

            default:
                return 0;//si completa todas la notas retorna cero

        }

        cal.setvalor(v);
        cal.setFecha(Calendar.getInstance());
        this.calificaciones.add(cal);
        this.calcularPromedio();//cad vez que agrego la calificacion llamo a calcular promedio 
        return calificaciones.size();//retorna el tamaño de la lista calificaciones
    }

    //clase interna con propios atributos, constructor ademas de  getter and setter.
    class Calificacion {

        private Calendar fecha;
        private float valor;
        private UNIDADES unidad;

        public Calificacion() {
        }

        public Calendar getFecha() {
            return fecha;
        }

        public void setFecha(Calendar fecha) {
            this.fecha = fecha;
        }

        public float getvalor() {
            return valor;
        }

        public void setvalor(float valor) {
            this.valor = valor;
        }

        public UNIDADES getUnidad() {
            return unidad;
        }

        public void setUnidad(UNIDADES unidad) {
            this.unidad = unidad;
        }

    }

    @Override
    public String toString() {

        return estudiante.toString() + " # " + numero;
    }
    //se imprime la linea 
    //nombre del estudiante con calificaciones de columna en columna

    public String imprimirDetalle() {
        String str = "\n\t" + this.estudiante;
        for (Calificacion c : this.calificaciones) {
            str += " \t\t" + c.getvalor();
        }
        str += "\t\t" + this.promedio + "\n";
        return str;
    }

    public String toSave() {
        GsonBuilder gb = new GsonBuilder();
        gb.setPrettyPrinting();
        Gson gson = gb.create();
        return gson.toJson(this);
    }

}
