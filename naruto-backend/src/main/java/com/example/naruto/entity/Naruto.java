package com.example.naruto.entity;

import jakarta.persistence.*;

@Entity

@Table(name = "personajes_naruto")

public class Naruto {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String nombre;

    private String aldea;

    private String rango;

    private String chakra;

    private String habilidades;

    private String imagen_frontal;

    private String imagen_extra;

    public Naruto() {}

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAldea() {
        return aldea;
    }

    public String getRango() {
        return rango;
    }

    public String getChakra() {
        return chakra;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public String getImagen_frontal() {
        return imagen_frontal;
    }

    public String getImagen_extra() {
        return imagen_extra;
    }
}