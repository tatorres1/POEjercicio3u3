package bll;

import Torres.BaseBllCrud;
import Torres.BasePersistencia;
import beu.Curso;
import beu.Estudiante;
import beu.Matricula;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;

public class GestionMatricula extends BasePersistencia<Matricula> implements BaseBllCrud<Matricula> {

    private Matricula matricula;
    private final String directorio = "matriculas";

    public GestionMatricula() {
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    //se genera el mensaje 
    public String calificar(float valor) {
        String mensaje = "";
        int num = matricula.addCalificacion(valor);
        switch (num) {
            case 0:
                mensaje = "¡Se han registrado todas las notas!\n";
                break;
            case 1:
                mensaje = "Se ingresó correctamente la calificación de la Unidad I\n\n";
                break;
            case 2:
                mensaje = "Se ingresó correctamente la calificación de la Unidad II\n\n";
                break;
            case 3:
                mensaje = "Se ingresó correctamente la calificación de la Unidad III\n\n";
                break;
            default:
                mensaje = "\tError al ingresar la calificacion\n";

        }
        return mensaje;
    }

    public void promediar() {
        matricula.calcularPromedio();
    }

    public String imprimir() {
        StringBuilder sb = new StringBuilder();
        sb.append("Estudiante: ").append(matricula.getEstudiante()).append("\n");
        sb.append("Curso: ").append(matricula.getCurso()).append("\n");
        sb.append("Promedio: ").append(matricula.getPromedio()).append("\n");
        sb.append(matricula.imprimirDetalle());
        return sb.toString();
    }

    public void archivar() throws IOException {
        this.escribir(directorio, this.matricula.getNumero(), matricula);
    }

    public void configurar(Curso cr, Estudiante est) {
        //coloco a la matricula un curso y estudiante
        this.matricula.setCurso(cr);
        this.matricula.setEstudiante(est);
    }

    @Override
    public void crear() {
        matricula = new Matricula();
    }

    @Override
    public void consultar(String id) throws IOException {
        String archivo = id + ".json";
        //con el contenido se tranforma en un objeto de la clase matricula   
        String contenido = this.leer(directorio, archivo);
        GsonBuilder gb = new GsonBuilder();
        gb.setPrettyPrinting();
        Gson gson = gb.create();
        matricula = gson.fromJson(contenido, Matricula.class);

    }

    @Override
    public void actualizar() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
