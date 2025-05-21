package org.iesvdm.univ;

import org.iesvdm.univ.repositorio.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Test
    void contextLoads() {

        personaRepository.findAll().forEach(System.out::println);

    }

}
