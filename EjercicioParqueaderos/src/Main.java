import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
            System.out.println("=== SEGUNDA PARTE DEL EJERCICIO");
            System.out.println("7. Calcular tiempo promedio de estacionamiento");
            System.out.println("8. Verificar carros estacionados mas de 8 horas y mostrarlo ");
            System.out.println("9. Mostrar carros de mas de tres horas y si hay duplicado");
            System.out.println("10. Numero de carros que comienzan con la placa PB");
            System.out.println("11. Verificar si hay un carro con 24 o más horas");
            System.out.println("12. Mostrar información placa PB y carros parqueados por 24 horas o más");
            System.out.println("13. Desocupar el parqueadero ");

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
                    break;
                case 5:
                    park.avanzarHora();
                    System.out.println("Se avanzo una hora en el parqueadero.");
                    break;
                case 6:
                    System.out.println("Ingrese el cambio de tarifa");
                    cambioTarifa = Integer.parseInt(bf.readLine());
                    park.cambiarTarifa(cambioTarifa);

                    System.out.println("La tarifa ha sido cambiado");
                    break;
                case 7:
                    double tiempoPromedio = park.darTiempoPromedio();
                    System.out.println("El tiempo promedio es: " + tiempoPromedio);
                    break;
                case 8:
                    Carro carroMasDeOchoHoras = park.hayCarroMasDeOchoHoras();
                    if (carroMasDeOchoHoras != null) {
                        System.out.println("Hay un carro estacionado más de 8 horas: " + carroMasDeOchoHoras.darPlaca());
                    } else {
                        System.out.println("No hay carros estacionados más de 8 horas.");
                    }
                    break;
                case 9:
                    ArrayList<Carro> carrosMasDeTresHoras = park.darCarrosMasDeTresHorasParqueados();
                    System.out.println("Carros estacionados más de 3 horas:");
                    for (Carro carro : carrosMasDeTresHoras) {
                        System.out.println(carro.darPlaca());
                    }
                    boolean hayCarrosPlacaIgual = park.hayCarrosPlacaIgual();
                    if (hayCarrosPlacaIgual) {
                        System.out.println("Hay carros con la misma placa.");
                    } else {
                        System.out.println("No hay carros con la misma placa.");
                    }
                    break;
                case 10:
                    int contarCarrosPB = park.contarCarrosQueComienzanConPlacaPB();
                    System.out.println("Cantidad de carros con placa que comienza con 'PB': " + contarCarrosPB);
                    break;
                case 11:
                    if (park.hayCarroCon24Horas()) {
                        System.out.println("Hay al menos un carro parqueado por 24 o más horas.");
                    } else {
                        System.out.println("No hay ningún carro parqueado por 24 o más horas.");
                    }
                    break;
                case 12:

                    String mensajeMetodo1 = park.metodo1();
                    System.out.println(mensajeMetodo1);
                    break;
                case 13:

                    int carrosSacados = park.desocuparParqueadero();
                    System.out.println("Se sacaron " + carrosSacados + " carros del parqueadero.");
                    break;

            }


        } while (opcion != 10);


    }

}
