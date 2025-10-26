Trabajo Práctico
Grupo F
Integrantes
+ Carolina Aguirre
+ Sabrina Barbiero
+ Nicolas Naceli
+ Juan Pico


Requisitos de instalación:
+ Python - 3.13.7
  * asegurarse de tener la variable de entorno PATH configurada
  * "pip install" grpcio, flask, zeep, ariadne entre otras librerías necesarias
  * cd grpc-client/src | "python app.py" para correr el servidor gRPC
  * cd soap-client | "python main.py" para correr el cliente SOAP
  * cd graphql-server | "python app.py" para correr el servidor graphql

    * NOTA: antess de correr este servidor debemos correr el siguiente comando para crear las tablas correspondientes
    "python"
    "from db import Base, engine
    from models.FiltroDonacion import FiltroDonacion
    Base.metadata.create_all(bind=engine)
    exit()  "

+ Java - JDK 17
+ Maven - 3.9.6
  * asegurarse de tener la variable de entorno PATH configurada
  * "mvn clean install" para instalar las dependencias y compilar el proyecto
  * Correr ServerApplcation.java para iniciar el servidor gRPC
+ React
    * "npm install" para instalar las dependencias
    * "npm run start" para correr la aplicación web
    * https://localhost:3000 para acceder a la aplicación web
+ PostgreSQL 17 - default port 
   * se debe crear una base de datos bajo el nombre "empuje_comunitario_db"
* Docker
  * Docker Desktop o bien desde la consola de comando: "docker compose up -d --build"


Directorios:
+ grpc-server
  + java/com/unla/grupoF
    + entity - clases que reflejan la base de datos (y el proto)
    + dto - clases para las vistas
    + mapper - clases para mappear los DTo a las tablas de la base de datos y viceversa.
    + repository - clases para manejar las operaciones CRUD con la base de datos
    + service - clases autogeneradas por gRPC desde los archivos .prot
    + serviceImpl - clases que implementan las clases autogeneradas por gRPC
  + proto
  
+ kafka
    + kafka-consumer
    + kafka-producer

+ grpc-front
  