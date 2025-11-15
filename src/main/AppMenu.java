package main;

import entities.Cobertura;
import entities.SeguroVehicular;
import entities.Vehiculo;
import service.SeguroVehicularService;
import service.SeguroVehicularServiceImpl;
import service.VehiculoService;
import service.VehiculoServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AppMenu {

    private static final Scanner SC = new Scanner(System.in);

    private static final VehiculoService vehiculoService = new VehiculoServiceImpl();
    private static final SeguroVehicularService seguroService = new SeguroVehicularServiceImpl();

    public static void main(String[] args) {

        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Elegí una opción: ");

            try {
                switch (opcion) {
                    case 1 -> menuVehiculos();
                    case 2 -> menuSeguros();
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida.");
                }
            } catch (RuntimeException e) {
                System.out.println("⚠ Error: " + e.getMessage());
            }

            System.out.println();

        } while (opcion != 0);
    }

    // ===== MENÚS =====

    private static void mostrarMenuPrincipal() {
        System.out.println("===== MENÚ PRINCIPAL =====");
        System.out.println("1 - Gestionar Vehículos");
        System.out.println("2 - Gestionar Seguros");
        System.out.println("0 - Salir");
    }

    private static void menuVehiculos() {
        int op;
        do {
            System.out.println("===== MENÚ VEHÍCULOS =====");
            System.out.println("1 - Alta vehículo + seguro (transacción)");
            System.out.println("2 - Buscar vehículo por dominio");
            System.out.println("3 - Buscar vehículo por ID");
            System.out.println("4 - Listar vehículos");
            System.out.println("5 - Actualizar vehículo + seguro");
            System.out.println("6 - Baja lógica vehículo + seguro");
            System.out.println("0 - Volver");
            op = leerEntero("Opción: ");

            try {
                switch (op) {
                    case 1 -> altaVehiculoConSeguro();
                    case 2 -> buscarVehiculoPorDominio();
                    case 3 -> buscarVehiculoPorId();
                    case 4 -> listarVehiculos();
                    case 5 -> actualizarVehiculoConSeguro();
                    case 6 -> bajaLogicaVehiculo();
                    case 0 -> System.out.println("Volviendo al menú principal...");
                    default -> System.out.println("Opción inválida.");
                }
            } catch (RuntimeException e) {
                System.out.println("⚠ Error: " + e.getMessage());
            }

            System.out.println();

        } while (op != 0);
    }

    private static void menuSeguros() {
        int op;
        do {
            System.out.println("===== MENÚ SEGUROS =====");
            System.out.println("1 - Alta de seguro");
            System.out.println("2 - Buscar seguro por ID");
            System.out.println("3 - Listar seguros");
            System.out.println("4 - Actualizar seguro");
            System.out.println("5 - Baja lógica de seguro");
            System.out.println("0 - Volver");
            op = leerEntero("Opción: ");

            try {
                switch (op) {
                    case 1 -> altaSeguro();
                    case 2 -> buscarSeguroPorId();
                    case 3 -> listarSeguros();
                    case 4 -> actualizarSeguro();
                    case 5 -> bajaLogicaSeguro();
                    case 0 -> System.out.println("Volviendo al menú principal...");
                    default -> System.out.println("Opción inválida.");
                }
            } catch (RuntimeException e) {
                System.out.println("⚠ Error: " + e.getMessage());
            }

            System.out.println();

        } while (op != 0);
    }

    // ===== CRUD VEHÍCULO =====

    private static void altaVehiculoConSeguro() {
        System.out.println("--- Alta de vehículo y seguro ---");

        // Vehículo
        String dominio = leerTexto("Dominio: ").toUpperCase();
        String marca   = leerTexto("Marca: ");
        String modelo  = leerTexto("Modelo: ");
        int anio       = leerEntero("Año: ");
        String nroChasis = leerTexto("Número de chasis: ");

        // Seguro
        SeguroVehicular seguro = leerDatosSeguroDesdeConsola();

        Vehiculo vehiculo = new Vehiculo(
                null,
                false,
                dominio,
                marca,
                modelo,
                anio,
                nroChasis,
                seguro
        );

        Vehiculo creado = vehiculoService.crearVehiculoConSeguro(vehiculo);
        System.out.println("✅ Vehículo creado: " + creado);
    }

    private static void buscarVehiculoPorDominio() {
        System.out.println("--- Buscar vehículo por dominio ---");
        String dominio = leerTexto("Dominio a buscar: ").toUpperCase();

        Vehiculo v = vehiculoService.obtenerPorDominio(dominio);
        if (v == null) {
            System.out.println("No se encontró vehículo con dominio " + dominio);
        } else {
            System.out.println("Encontrado: " + v);
        }
    }

    private static void buscarVehiculoPorId() {
        System.out.println("--- Buscar vehículo por ID ---");
        long id = leerEntero("ID de vehículo: ");
        Vehiculo v = vehiculoService.obtenerPorId(id);
        if (v == null) {
            System.out.println("No se encontró vehículo con id " + id);
        } else {
            System.out.println("Encontrado: " + v);
        }
    }

    private static void listarVehiculos() {
        System.out.println("--- Listado de vehículos ---");
        List<Vehiculo> lista = vehiculoService.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay vehículos cargados.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private static void actualizarVehiculoConSeguro() {
        System.out.println("--- Actualizar vehículo y seguro ---");
        long id = leerEntero("ID de vehículo a actualizar: ");
        Vehiculo existente = vehiculoService.obtenerPorId(id);

        if (existente == null) {
            System.out.println("No se encontró vehículo con id " + id);
            return;
        }

        System.out.println("Actual actual: " + existente);

        // Datos nuevos (si querés, podrías permitir enter vacío para dejar igual)
        String dominio = leerTexto("Nuevo dominio (" + existente.getDominio() + "): ").toUpperCase();
        if (!dominio.isBlank()) existente.setDominio(dominio);

        String marca = leerTexto("Nueva marca (" + existente.getMarca() + "): ");
        if (!marca.isBlank()) existente.setMarca(marca);

        String modelo = leerTexto("Nuevo modelo (" + existente.getModelo() + "): ");
        if (!modelo.isBlank()) existente.setModelo(modelo);

        int anio = leerEntero("Nuevo año (" + existente.getAnio() + "): ");
        existente.setAnio(anio);

        String nroChasis = leerTexto("Nuevo nº chasis (" + existente.getNroChasis() + "): ");
        if (!nroChasis.isBlank()) existente.setNroChasis(nroChasis);

        // Actualizar datos del seguro asociado
        System.out.println("---- Datos de seguro a actualizar ----");
        SeguroVehicular seguro = existente.getSeguro();
        actualizarDatosSeguroDesdeConsola(seguro);

        Vehiculo actualizado = vehiculoService.actualizarVehiculoYSeguro(existente);
        System.out.println("✅ Vehículo actualizado: " + actualizado);
    }

    private static void bajaLogicaVehiculo() {
        System.out.println("--- Baja lógica de vehículo y seguro ---");
        long id = leerEntero("ID de vehículo a dar de baja: ");
        vehiculoService.bajaLogicaVehiculoYSeguro(id);
        System.out.println("✅ Baja lógica aplicada sobre el vehículo id " + id);
    }

    // ===== CRUD SEGURO =====

    private static void altaSeguro() {
        System.out.println("--- Alta de seguro ---");
        SeguroVehicular s = leerDatosSeguroDesdeConsola();
        SeguroVehicular creado = seguroService.insertar(s);
        System.out.println("✅ Seguro creado: " + creado);
    }

    private static void buscarSeguroPorId() {
        System.out.println("--- Buscar seguro por ID ---");
        long id = leerEntero("ID de seguro: ");
        SeguroVehicular s = seguroService.getById(id);
        if (s == null) {
            System.out.println("No se encontró seguro con id " + id);
        } else {
            System.out.println("Encontrado: " + s);
        }
    }

    private static void listarSeguros() {
        System.out.println("--- Listado de seguros ---");
        List<SeguroVehicular> lista = seguroService.getAll();
        if (lista.isEmpty()) {
            System.out.println("No hay seguros cargados.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private static void actualizarSeguro() {
        System.out.println("--- Actualizar seguro ---");
        long id = leerEntero("ID de seguro a actualizar: ");
        SeguroVehicular existente = seguroService.getById(id);

        if (existente == null) {
            System.out.println("No se encontró seguro con id " + id);
            return;
        }

        System.out.println("Actual actual: " + existente);
        actualizarDatosSeguroDesdeConsola(existente);
        SeguroVehicular actualizado = seguroService.actualizar(existente);
        System.out.println("✅ Seguro actualizado: " + actualizado);
    }

    private static void bajaLogicaSeguro() {
        System.out.println("--- Baja lógica de seguro ---");
        long id = leerEntero("ID de seguro a dar de baja: ");
        seguroService.eliminar(id);
        System.out.println("✅ Baja lógica aplicada sobre el seguro id " + id);
    }

    // ===== Helpers para leer/actualizar seguros =====

    private static SeguroVehicular leerDatosSeguroDesdeConsola() {
        String aseguradora = leerTexto("Aseguradora: ");
        String nroPoliza   = leerTexto("Número de póliza: ");

        System.out.println("Cobertura (1-RC, 2-TERCEROS, 3-TODO_RIESGO): ");
        int c = leerEntero("Opción: ");
        Cobertura cobertura = switch (c) {
            case 1 -> Cobertura.RC;
            case 2 -> Cobertura.TERCEROS;
            case 3 -> Cobertura.TODO_RIESGO;
            default -> throw new IllegalArgumentException("Cobertura inválida.");
        };

        System.out.println("Fecha de vencimiento:");
        int anioV = leerEntero("Año (YYYY): ");
        int mesV  = leerEntero("Mes (1-12): ");
        int diaV  = leerEntero("Día (1-31): ");
        LocalDate vencimiento = LocalDate.of(anioV, mesV, diaV);

        return new SeguroVehicular(
                null,
                false,
                aseguradora,
                nroPoliza,
                cobertura,
                vencimiento
        );
    }

    private static void actualizarDatosSeguroDesdeConsola(SeguroVehicular seguro) {
        String aseguradora = leerTexto("Aseguradora (" + seguro.getAseguradora() + "): ");
        if (!aseguradora.isBlank()) seguro.setAseguradora(aseguradora);

        String nroPoliza = leerTexto("N° póliza (" + seguro.getNroPoliza() + "): ");
        if (!nroPoliza.isBlank()) seguro.setNroPoliza(nroPoliza);

        System.out.println("Cobertura actual: " + seguro.getCobertura());
        System.out.println("Cobertura (1-RC, 2-TERCEROS, 3-TODO_RIESGO, 0 = mantener): ");
        int c = leerEntero("Opción: ");
        if (c != 0) {
            Cobertura cobertura = switch (c) {
                case 1 -> Cobertura.RC;
                case 2 -> Cobertura.TERCEROS;
                case 3 -> Cobertura.TODO_RIESGO;
                default -> throw new IllegalArgumentException("Cobertura inválida.");
            };
            seguro.setCobertura(cobertura);
        }

        System.out.println("Vencimiento actual: " + seguro.getVencimiento());
        System.out.println("¿Actualizar vencimiento? (S/N): ");
        String resp = leerTexto("");
        if (resp.equalsIgnoreCase("S")) {
            int anioV = leerEntero("Año (YYYY): ");
            int mesV  = leerEntero("Mes (1-12): ");
            int diaV  = leerEntero("Día (1-31): ");
            seguro.setVencimiento(LocalDate.of(anioV, mesV, diaV));
        }
    }

    // ===== LECTURA BÁSICA =====

    private static String leerTexto(String msg) {
        System.out.print(msg);
        return SC.nextLine().trim();
    }

    private static int leerEntero(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                String linea = SC.nextLine();
                return Integer.parseInt(linea.trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingresá un número válido.");
            }
        }
    }
}
