import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        //Variables
        int opcion;
        String placae;
        String placasal;
        int cambioTarifa;

        //Instancias
        Parqueadero park = new Parqueadero();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        do {
            System.out.println("----- Menu Principal del Parqueadoer -----");
            System.out.println("Ingrese la accion que desea ralizar");
            System.out.println("1. Ingresar carro");
            System.out.println("2. Dar salida al carro");
            System.out.println("3. Informar ingresos del parqueadero");
            System.out.println("4. Consultar puestos disponibles");
            System.out.println("5. Avanzar reloj del parqueadero");
            System.out.println("6. Cambiar tarifa del parqueadero");
            System.out.println("7. salir ");
            opcion = Integer.parseInt(bf.readLine());

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la placa: ");
                    placae = bf.readLine();
                                        Carro carrito = new Carro(placae, park.darHoraActual());
                    if (park.entrarCarro(placae) == Parqueadero.NO_HAY_PUESTO) {
                        System.out.println("El parqueadero esta lleno");
                    } else if (park.entrarCarro(placae) == Parqueadero.PARQUEADERO_CERRADO) {
                        System.out.println("El parqueadero esta cerrado");

                    } else if (park.entrarCarro(placae) == Parqueadero.CARRO_YA_EXISTE) {
                        System.out.println("La placa ya esta en el parqueadero");

                    } else {
                        System.out.println("Ingreso de carro exitoso");
                    }
                    break;

                case 2:
                    System.out.println("Ingrese la placa del carro que desea sacar: ");
                    placasal = bf.readLine();

                    if (park.sacarCarro(placasal) == Parqueadero.PARQUEADERO_CERRADO) {
                        System.out.println("El parqueadero esta cerrado");

                    } else if (park.sacarCarro(placasal) == Parqueadero.CARRO_NO_EXISTE) {
                        System.out.println("No hay carro con la placa mencionada");

                    } else {
                        System.out.println("El carro a sacar es: " + placasal + " se debe pagar: " + park.sacarCarro(placasal));
                    }
                    break;

                case 3:
                    System.out.println("Los ingresos son: " + park.darMontoCaja());
                    break;
                case 4:
                    System.out.println("Los puestos disponibles son: " + park.calcularPuestosLibres());
                case 5:
                    park.avanzarHora();
                    System.out.println("Se avanzo una hora en el parqueadero.");
                case 6:
                    System.out.println("Ingrese el cambio de tarifa");
                    cambioTarifa = Integer.parseInt(bf.readLine());
                    park.cambiarTarifa(cambioTarifa);
                    System.out.println("La tarifa ha sido cambiado");
                    break;
                case 7:
                    break;

            }


        } while (opcion != 7);


    }

}
