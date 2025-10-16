package org.iesvdm.univ;

import org.iesvdm.univ.modelo.Asignatura;
import org.iesvdm.univ.modelo.Persona;
import org.iesvdm.univ.modelo.Profesor;
import org.iesvdm.univ.repositorio.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.ResourceTransactionManager;

import java.util.Comparator;
import java.util.List;

/**
 1
 Devuelve un listado con el primer apellido, segundo apellido y el nombre de todos los alumnos. El listado deberá estar ordenado alfabéticamente de menor a mayor por el primer apellido, segundo apellido y nombre.

 2
 Averigua el nombre y los dos apellidos de los alumnos que no han dado de alta su número de teléfono en la base de datos.

 3
 Devuelve el listado de los alumnos que nacieron en 1999.

 4
 Devuelve el listado de profesores que no han dado de alta su número de teléfono en la base de datos y además su nif termina en K.

 5
 Devuelve el listado de las asignaturas que se imparten en el primer cuatrimestre, en el tercer curso del grado que tiene el identificador 7.

 6
 Devuelve un listado con el nombre de las asignaturas, año de inicio y año de fin del curso escolar del alumno con nif 26902806M.

 7
 Devuelve un listado con el nombre de todos los departamentos que tienen profesores que imparten alguna asignatura en el Grado en Ingeniería Informática (Plan 2015).

 8
 Devuelve un listado con todos los alumnos que se han matriculado en alguna asignatura durante el curso escolar 2018/2019.

 9
 Devuelve un listado con los profesores que no imparten ninguna asignatura.

 10
 Devuelve un listado con las asignaturas que no tienen un profesor asignado.

 11
 Devuelve un listado con todos los departamentos que tienen alguna asignatura que no se haya impartido en ningún curso escolar. El resultado debe mostrar el nombre del departamento y el nombre de la asignatura que no se haya impartido nunca.

 12
 Calcula cuántos profesores hay en cada departamento. El resultado sólo debe mostrar dos columnas, una con el nombre del departamento y otra con el número de profesores que hay en ese departamento. El resultado sólo debe incluir los departamentos que tienen profesores asociados y deberá estar ordenado de mayor a menor por el número de profesores.

 13
 Devuelve un listado con el nombre de todos los grados existentes en la base de datos y el número de asignaturas que tiene cada uno, de los grados que tengan más de 40 asignaturas asociadas

 15
 Devuelve un listado con los profesores que tienen un departamento asociado y que no imparten ninguna asignatura.

 16
 Devuelve un listado con las asignaturas que no tienen un profesor asignado.

 */



@SpringBootTest
class UnivApplicationTests {

    @Autowired
    AlumnoSeMatriculaAsignaturaRepository alumnoSeMatriculaAsignaturaRepository;

    @Autowired
    AsignaturaRepository asignaturaRepository;

    @Autowired
    CursoEscolarRepository cursoEscolarRepository;

    @Autowired
    DepartamentoRepository departamentoRepository;

    @Autowired
    GradoRepository gradoRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    ProfesorRepository profesorRepository;
    private ResourceTransactionManager resourceTransactionManager;

    @Test
    void contextLoads() {

        personaRepository.findAll().forEach(System.out::println);

    }
    // Ejercicio1
    @Test
    void listadoAlumnosOrdenadoPorApellidosNombre() {
        List<Persona> personas = personaRepository.findAll();

        personas.stream()
                .filter(p -> p.getTipo().equalsIgnoreCase("alumno"))
                .sorted(Comparator.comparing(Persona::getApellido1)
                        .thenComparing(Persona::getApellido2)
                        .thenComparing(Persona::getNombre))
                .forEach(p -> System.out.println(
                        "Primer Apellido: " + p.getApellido1() +
                                ", Segundo Apellido: " + p.getApellido2() +
                                ", Nombre: " + p.getNombre()));
    }
    // Ejercicio2
    @Test
    void alumnosSinTelefono() {
        List<Persona> personas = personaRepository.findAll();

        personas.stream()
                .filter(p -> p.getTipo().equalsIgnoreCase("alumno"))
                .filter(p -> p.getTelefono() == null || p.getTelefono().isBlank())
                .forEach(p -> System.out.println(
                        "Nombre: " + p.getNombre() +
                                ", Apellido1: " + p.getApellido1() +
                                ", Apellido2: " + p.getApellido2()));
    }

    //Ejercio3
    @Test
    void alumnosNacidosEn1999() {
        List<Persona> personas = personaRepository.findAll();
        personas.stream()
                .filter(p -> p.getTipo().equalsIgnoreCase("alumno"))
                .filter(p -> p.getFechaNacimiento() != null && p.getFechaNacimiento().getYear() == 1999)
                .forEach(p -> System.out.println(
                        "Nombre: " + p.getNombre() +
                                ", Apellido1: " + p.getApellido1() +
                                ", Apellido2: " + p.getApellido2() +
                                ", Fecha de Nacimiento: " + p.getFechaNacimiento()));

    }
    //Ejercicio 4
    //Devuelve el listado de profesores que no han dado de alta su número de teléfono en la base de datos y además su nif termina en K.

    @Test
    void profesoresSinTelefonoYNifTerminaEnK() {
        List<Persona> personas = personaRepository.findAll();
        personas.stream()
                .filter(p -> p.getTipo().equalsIgnoreCase("profesor"))
                .filter(p -> (p.getTelefono() == null || p.getTelefono().isBlank()) && p.getNif() != null && p.getNif().endsWith("K"))
                .forEach(p -> System.out.println(
                        "Nombre: " + p.getNombre() +
                                ", Apellido1: " + p.getApellido1() +
                                ", Apellido2: " + p.getApellido2() +
                                ", NIF: " + p.getNif()));
    }

    //Ejercicio 5
    //Devuelve el listado de las asignaturas que se imparten en el primer cuatrimestre, en el tercer curso del grado que tiene el identificador 7.
    @Test
    void asignaturasPrimerCuatrimestreTercerCursoGrado7() {
        List<Asignatura> asignaturas = asignaturaRepository.findAll();
        asignaturas.stream()
                .filter(a -> a.getCuatrimestre() == 1
                        && a.getCurso() == 3
                        && a.getIdGrado() != null
                        && a.getIdGrado().getId() == 7)
                .forEach(a -> System.out.println(
                        "Nombre Asignatura: " + a.getNombre() +
                                ", Cuatrimestre: " + a.getCuatrimestre() +
                                ", Curso: " + a.getCurso() +
                                ", Grado ID: " + (a.getIdGrado() != null ? a.getIdGrado().getId() : "N/A")
                ));

    }
    //Ejercicio6
    @Test
    void asignaturasYCursoEscolarDeAlumnoPorNif() {
        String nifObjetivo = "26902806M";
        Persona alumno = personaRepository.findAll().stream()
                .filter(p -> "alumno".equalsIgnoreCase(p.getTipo()))
                .filter(p -> nifObjetivo.equalsIgnoreCase(p.getNif()))
                .findFirst()
                .orElse(null);
        if (alumno == null) {
            System.out.println("Alumno no encontrado para NIF: " + nifObjetivo);
            return;
        }
        alumnoSeMatriculaAsignaturaRepository.findAll().stream()
                .filter(m -> m.getIdAlumno() != null && m.getIdAlumno().getId().equals(alumno.getId()))
                .forEach(m -> {
                    String nombreAsig = m.getIdAsignatura() != null ? m.getIdAsignatura().getNombre() : "N/A";
                    Integer anyoInicio = m.getIdCursoEscolar() != null ? m.getIdCursoEscolar().getAnyoInicio() : null;
                    Integer anyoFin = m.getIdCursoEscolar() != null ? m.getIdCursoEscolar().getAnyoFin() : null;
                    System.out.println("Asignatura: " + nombreAsig + ", Año inicio: " + anyoInicio + ", Año fin: " + anyoFin);
                });
    }
    
    //Ejercicio7
    // Devuelve un listado con el nombre de todos los departamentos que tienen profesores que imparten alguna asignatura en el Grado en Ingeniería Informática (Plan 2015).
    @Test
    void nombreDepartamentosConProfesores(){
        String nombreGradoObjetivo = "Grado en Ingeniería Informática (Plan 2015)";
        asignaturaRepository.findAll().stream()
                .filter(a -> a.getIdGrado() != null && nombreGradoObjetivo.equalsIgnoreCase(a.getIdGrado().getNombre()))
                .filter(a -> a.getIdProfesor() != null && a.getIdProfesor().getIdDepartamento() != null)
                .map(a -> a.getIdProfesor().getIdDepartamento().getNombre())
                .distinct()
                .sorted()
                .forEach(nombreDepto -> System.out.println("Departamento: " + nombreDepto));
    }
    
    //Ejercicio 8
    // Devuelve un listado con todos los alumnos que se han matriculado en alguna asignatura durante el curso escolar 2018/2019.
    @Test
    void alumnosMatriculadosCurso2018_2019() {
        int anyoInicioObjetivo = 2018;
        int anyoFinObjetivo = 2019;

        alumnoSeMatriculaAsignaturaRepository.findAll().stream()
                .filter(m -> m.getIdCursoEscolar() != null
                        && m.getIdCursoEscolar().getAnyoInicio() != null
                        && m.getIdCursoEscolar().getAnyoFin() != null
                        && m.getIdCursoEscolar().getAnyoInicio() == anyoInicioObjetivo
                        && m.getIdCursoEscolar().getAnyoFin() == anyoFinObjetivo)
                .map(m -> m.getIdAlumno())
                .filter(a -> a != null && "alumno".equalsIgnoreCase(a.getTipo()))
                .distinct()
                .sorted(Comparator.comparing(Persona::getApellido1)
                        .thenComparing(Persona::getApellido2)
                        .thenComparing(Persona::getNombre))
                .forEach(a -> System.out.println("Alumno: " + a.getNombre() + " " + a.getApellido1() + (a.getApellido2() != null ? (" " + a.getApellido2()) : "")));
    }

    //Ejercicio 9
    // Devuelve un listado con los profesores que no imparten ninguna asignatura.
    @Test
    void profesoresNoImpartenAsignatura() {
        profesorRepository.findAll().stream()
                .filter(pr -> pr.getAsignaturas() == null || pr.getAsignaturas().isEmpty())
                .map(Profesor::getPersona)
                .filter(p -> p != null && "profesor".equalsIgnoreCase(p.getTipo()))
                .sorted(Comparator.comparing(Persona::getApellido1)
                        .thenComparing(p -> p.getApellido2() == null ? "" : p.getApellido2())
                        .thenComparing(Persona::getNombre))
                .forEach(p -> System.out.println(
                        "Profesor sin asignaturas: " + p.getNombre() + " "
                                + p.getApellido1() + (p.getApellido2() != null ? (" " + p.getApellido2()) : "")
                                + " (NIF: " + p.getNif() + ")"));
    }
    
    //Ejercicio 10
    // Devuelve un listado con las asignaturas que no tienen un profesor asignado.
    @Test
    void asignaturaSinProfesor() {
        asignaturaRepository.findAll().stream()
                .filter(a -> a.getIdProfesor() == null)
                .sorted(Comparator.comparing(Asignatura::getNombre))
                .forEach(a -> System.out.println(
                        "Asignatura sin profesor: " + a.getNombre() +
                                ", Créditos: " + a.getCreditos() +
                                ", Curso: " + a.getCurso() +
                                ", Cuatrimestre: " + a.getCuatrimestre()));
    }



}