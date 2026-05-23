const swaggerJsdoc = require("swagger-jsdoc");

const options = {

  definition: {

    openapi: "3.0.0",

    info: {

      title: "Pokemon API",

      version: "1.0.0",

      description: "Microservicio Pokémon Node.js + PostgreSQL"
    },

    servers: [
      {
        url: "https://pokemon-backend-at7n.onrender.com"
      }
    ]
  },

  apis: ["./routes/*.js"]
};

const specs = swaggerJsdoc(options);

module.exports = specs;
