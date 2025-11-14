# UTN-TUPaD-P2-TFI

Trabajo Final Integrador (TFI) - Programación II - Tecnicatura Universitaria en Programación UTN

Grupo 147



\## Dominio seleccionado

Vehículo -> SeguroVehicular (relación 1:1 unidireccional por composición)



\## Justificación

Para el desarrollo de ambos trabajos (Programación II y BD1) elegimos el dominio Vehículo -> SeguroVehicular porque es una temática que nos interesa y entendemos que puede representar un caso real de uso en el cual aplicar los contenidos vistos en las materias. 

Vehículo (A) contiene una referencia a SeguroVehicular (B), definido en una relación de composición.



\## Requisitos

\- Java 17 o superior

\- MySQL 8.0

\- JDBC (driver mysql-connector-j-8.4.0)

\- JDK 21

\- IDE



\## Recursos

\- En la carpeta /docs del repositorio se encuentran los scripts SQL, el informe en PDF y el UML.



\## Pasos

1\. Crear la base ejecutando `01\_creacion\_tablas.sql`.

2\. Ejecutar `02\_datos\_prueba.sql` si se desean datos iniciales.

3\. Configurar la conexión en `config/ConexionBD.java`.

4\. Ejecutar el proyecto desde la clase `main/AppMenu.java`.



\## Video

Link: 



