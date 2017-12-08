package com.example.a2dam.actividad4a;


/**
 * Created by 2dam on 04/12/2017.
 */

public class Alumno {
    private int id;
    private String nombre;
    private String ciclo;
    private String curso;
    private int edad;
    private double nota;

    public Alumno(int id, String nombre, int edad, String ciclo, String curso, double nota) {
        this.id = id;
        this.nombre = nombre;
        this.ciclo = ciclo;
        this.curso = curso;
        this.edad = edad;
        this.nota = nota;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
