# UTN-TUPaD-P2-TFI

Trabajo Final Integrador (TFI) - Programación II - Tecnicatura Universitaria en Programación UTN

Grupo 147


**Dominio seleccionado**
Vehículo -> SeguroVehicular (relación 1:1 unidireccional)

**Justificación**
Para el desarrollo de ambos trabajos (Programación II y BD1) elegimos el dominio Vehículo -> SeguroVehicular porque es una temática que nos interesa y entendemos que puede representar un caso real de uso en el cual aplicar los contenidos vistos en las materias. 

Vehículo (A) contiene una referencia a SeguroVehicular (B), definido en una relación de composición.

**Recursos**
En la carpeta 📂/docs del repositorio se encuentran los scripts SQL, el informe en PDF y el UML.

**Requisitos**
- Java 17 o superior
- MySQL 8.0
- Driver JDBC mysql-connector-j-8.4.0 (provisto en el proyecto)
- JDK 24 (provisto en el proyecto)

**Para crear la BD**
1\. Crear la base ejecutando `01_crear_base.sql`.
2\. Cargar datos de prueba con `02_datos_de_prueba.sql`

**Para compilar y ejecutar**

1\. Configurar la conexión en `config/DatabaseConnection.java`, verificando que las credenciales *USER* y *PASSWORD* concidan con su usuario MySQL
2\. Ejecutar el proyecto desde `main/main.java`.

**Video **
📼 Link: 