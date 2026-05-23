from flask import Blueprint, jsonify
from pymongo import MongoClient
from dotenv import load_dotenv
import os

load_dotenv()

dragonball_bp = Blueprint(
    "dragonball",
    __name__
)

client = MongoClient(
    os.getenv("MONGO_URI")
)

db = client["dragonballB"]

coleccion = db["personajes"]

@dragonball_bp.route(
    "/<nombre>",
    methods=["GET"]
)

def obtener_personaje(nombre):
    """
    Obtener personaje Dragon Ball por nombre
    ---
    tags:
      - Dragon Ball

    parameters:
      - name: nombre
        in: path
        type: string
        required: true
        description: Nombre del personaje

    responses:
      200:
        description: Personaje encontrado

      404:
        description: Personaje no encontrado
    """

    personaje = coleccion.find_one({
        "nombre": {
            "$regex": f"^{nombre}$",
            "$options": "i"
        }
    })

    if not personaje:

        return jsonify({
            "mensaje":
            "Personaje no encontrado"
        }), 404

    personaje["_id"] = str(
        personaje["_id"]
    )

    return jsonify(personaje)
