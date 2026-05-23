require("dotenv").config();

const express = require("express");

const cors = require("cors");

const pokemonRoutes = require("./routes/pokemon");

const app = express();

const swaggerUi = require("swagger-ui-express");

const swaggerSpecs = require("./swagger");

app.use(cors());

app.use(express.json());

app.use("/pokemon", pokemonRoutes);

app.use(
  "/api-docs",
  swaggerUi.serve,
  swaggerUi.setup(swaggerSpecs)
);

const PORT = process.env.PORT || 3000;

app.listen(PORT, () => {
  console.log(`Servidor Node corriendo en puerto ${PORT}`);
});