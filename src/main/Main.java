package main;

//import entities.Vehiculo;
//import entities.SeguroVehicular;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AppMenu app = new AppMenu();
        System.out.println("***********************************************************************************************");
        System.out.println("**************** TFI: PROGRAMACION II - GRUPO 147: VEHICULO -> SEGUROVEHICULAR ****************");
        System.out.println("***********************************************************************************************\n");
        app.run();


        // prueba
//        SeguroVehicular segundaSeguros = new SeguroVehicular(
//      
//        );       
//        Vehiculo auto = new Vehiculo(
//                1L, // id vehículo
//                false, // eliminado
//                "Dominio", //dominio
//                "Toyota", // marca
//                "Corolla", // modelo
//                2020, // año
//                "CHS123456XYZ", // número de chasis
//                segundaSeguros
//      );
//        System.out.println(auto.toString());
    }
}