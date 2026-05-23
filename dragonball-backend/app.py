from flask import Flask
from flask_cors import CORS
from pymongo import MongoClient
from dotenv import load_dotenv
from flasgger import Swagger
import os

load_dotenv()

app = Flask(__name__)

Swagger(app)

CORS(app)

client = MongoClient(
    os.getenv("MONGO_URI")
)

db = client["dragonball"]

from routes.dragonball import dragonball_bp

app.register_blueprint(
    dragonball_bp,
    url_prefix="/dragonball"
)

if __name__ == "__main__":

    port = int(os.environ.get("PORT", 5000))

    app.run(
        host="0.0.0.0",
        port=port
    )
