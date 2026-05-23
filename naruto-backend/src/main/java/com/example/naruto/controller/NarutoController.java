package com.example.naruto.controller;

import com.example.naruto.entity.Naruto;

import com.example.naruto.repository.NarutoRepository;

import org.springframework.web.bind.annotation.*;

import java.util.*;

import io.swagger.v3.oas.annotations.Operation;

@RestController

@CrossOrigin("*")

@RequestMapping("/naruto")

public class NarutoController {

    private final NarutoRepository repository;

    public NarutoController(
            NarutoRepository repository) {

        this.repository = repository;
    }

    @Operation(
    summary = "Obtener personaje Naruto por nombre",
    description = "Consulta un personaje de Naruto almacenado en PostgreSQL/Supabase"
)

@GetMapping("/{nombre}")

public Map<String, Object> obtener(
        @PathVariable String nombre) {

    Optional<Naruto> optionalNaruto =
            repository.findByNombre(nombre);

    if (optionalNaruto.isEmpty()) {

        Map<String, Object> error =
                new HashMap<>();

        error.put(
                "mensaje",
                "Personaje no encontrado"
        );

        return error;
    }

    Naruto n = optionalNaruto.get();

    Map<String, Object> response =
            new HashMap<>();

    response.put("nombre", n.getNombre());

    response.put("aldea", n.getAldea());

    response.put("rango", n.getRango());

    response.put(
            "habilidades",
            Arrays.asList(
                    n.getHabilidades().split(",")
            )
    );

    Map<String, String> imagenes =
            new HashMap<>();

    imagenes.put(
            "frontal",
            n.getImagen_frontal()
    );

    imagenes.put(
            "extra",
            n.getImagen_extra()
    );

    response.put(
            "imagenes",
            imagenes
    );

    return response;
  }
}