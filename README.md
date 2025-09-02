Trabajo Práctico
Grupo F
Integrantes
+ Carolina Aguirre
+ Sabrina Barbiero
+ Nicolas Naceli
+ Juan Pico


Requisitos de instalación:
+ Python - [version]
+ Java - JDK 17
+ PostgreSQL 17 - default port 
   * se debe crear una base de datos bajo el nombre "empuje_comunitario_db"


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