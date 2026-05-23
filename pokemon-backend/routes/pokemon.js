const express = require("express");

const router = express.Router();

const db = require("../db");

/**
 * @swagger
 * /pokemon/{nombre}:
 *   get:
 *     summary: Obtener Pokémon por nombre
 *     parameters:
 *       - in: path
 *         name: nombre
 *         required: true
 *         schema:
 *           type: string
 *
 *     responses:
 *       200:
 *         description: Pokémon encontrado
 *       404:
 *         description: Pokémon no encontrado
 */

router.get("/:nombre", async (req, res) => {

  try {

    const nombre = req.params.nombre;

    const result = await db.query(
      `
      SELECT *
      FROM pokemones
      WHERE LOWER(nombre) = LOWER($1)
      `,
      [nombre]
    );

    if (result.rows.length === 0) {

      return res.status(404).json({
        mensaje: "Pokémon no encontrado"
      });
    }

    const p = result.rows[0];

    res.json({

      nombre: p.nombre,

      altura: p.altura,

      peso: p.peso,

      habilidades: p.habilidades.split(","),

      imagenes: {
        frontal: p.imagen_front,
        trasera: p.imagen_back
      }

    });

  } catch (error) {

    res.status(500).json(error);
  }
});

module.exports = router;