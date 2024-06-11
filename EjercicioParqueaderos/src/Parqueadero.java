import java.util.ArrayList;


public class Parqueadero {
    // Constantes
    public static final int TAMANO = 40;
    public static final int NO_HAY_PUESTO = -1;
    public static final int PARQUEADERO_CERRADO = -2;
    public static final int CARRO_NO_EXISTE = -3;
    public static final int CARRO_YA_EXISTE = -4;
    public static final int HORA_INICIAL = 6;
    public static final int HORA_CIERRE = 20;
    public static final int TARIFA_INICIAL = 1200;
    // Atributos
    private Puesto puestos[];
    private int tarifa;
    private int caja;
    private int horaActual;
    private boolean abierto;

    // Constructores
    public Parqueadero() {
        horaActual = HORA_INICIAL;
        abierto = true;
        tarifa = TARIFA_INICIAL;
        caja = 0;
        // Crea el arreglo de puestos
        puestos = new Puesto[TAMANO];
        for (int i = 0; i < TAMANO; i++)
            puestos[i] = new Puesto(i);

    }

    // Metodos
    public String darPlacaCarro(int pPosicion) {
        String respuesta = "";
        if (estaOcupado(pPosicion)) {
            respuesta = "Placa: " + puestos[pPosicion].darCarro().darPlaca();
        } else {
            respuesta = "No hay un carro en esta posici�n";
        }

        return respuesta;
    }

    public int entrarCarro(String pPlaca) {
        int resultado = 0;
        if (!abierto) {
            resultado = PARQUEADERO_CERRADO;
        } else {
            // Buscar en el parqueadero un carro con la placa indicada
            int numPuestoCarro = buscarPuestoCarro(pPlaca.toUpperCase());
            if (numPuestoCarro != CARRO_NO_EXISTE) {
                resultado = CARRO_YA_EXISTE;
            }

            // Buscar un puesto libre para el carro y agregarlo
            resultado = buscarPuestoLibre();
            if (resultado != NO_HAY_PUESTO) {
                Carro carroEntrando = new Carro(pPlaca, horaActual);
                puestos[resultado].parquearCarro(carroEntrando);
            }
        }

        return resultado;
    }

    public int sacarCarro(String pPlaca) {
        int resultado = 0;
        if (!abierto) {
            resultado = PARQUEADERO_CERRADO;
        } else {
            int numPuesto = buscarPuestoCarro(pPlaca.toUpperCase());
            if (numPuesto == CARRO_NO_EXISTE) {
                resultado = CARRO_NO_EXISTE;
            } else {
                Carro carro = puestos[numPuesto].darCarro();
                int nHoras = carro.darTiempoEnParqueadero(horaActual);
                int porPagar = nHoras * tarifa;
                caja = caja + porPagar;
                puestos[numPuesto].sacarCarro();
                resultado = porPagar;
            }
        }

        return resultado;
    }

    public int darMontoCaja() {
        return caja;
    }

    public int calcularPuestosLibres() {
        int puestosLibres = 0;
        for (Puesto puesto : puestos) {
            if (!puesto.estaOcupado()) {
                puestosLibres = puestosLibres + 1;
            }
        }
        return puestosLibres;
    }

    public void cambiarTarifa(int pTarifa) {
        tarifa = pTarifa;
    }

    private int buscarPuestoLibre() {
        int puesto = NO_HAY_PUESTO;
        for (int i = 0; i < TAMANO && puesto == NO_HAY_PUESTO; i++) {
            if (!puestos[i].estaOcupado()) {
                puesto = i;
            }
        }
        return puesto;
    }

    private int buscarPuestoCarro(String pPlaca) {
        int puesto = CARRO_NO_EXISTE;
        for (int i = 0; i < TAMANO && puesto == CARRO_NO_EXISTE; i++) {
            if (puestos[i].tieneCarroConPlaca(pPlaca)) {
                puesto = i;
            }
        }
        return puesto;
    }

    public void avanzarHora() {
        if (horaActual <= HORA_CIERRE) {
            horaActual = (horaActual + 1);
        }
        if (horaActual == HORA_CIERRE) {
            abierto = false;
        }
    }

    public int darHoraActual() {
        return horaActual;
    }

    public boolean estaAbierto() {
        return abierto;
    }

    public int darTarifa() {
        return tarifa;
    }

    public boolean estaOcupado(int pPuesto) {
        boolean ocupado = puestos[pPuesto].estaOcupado();
        return ocupado;
    }

    /// SEGUNDA PARTE DEL EJERCICIO
    //Metodos de extension
    public double darTiempoPromedio() {
        int totalCarritos = 0;
        int totalTiempo = 0;
        int tiempoActual;
        double tiempoPromedio;

        for (int i = 0; i < puestos.length; i++) {
            Puesto puestocontar = puestos[i];
            Carro carro = puestocontar.darCarro();

            // Ver si hay carro en ese puesto
            if (carro != null) {
                tiempoActual = carro.darTiempoEnParqueadero(horaActual);

                totalTiempo = totalTiempo + tiempoActual;
                totalCarritos++;
            }
        }

        if (totalCarritos == 0) {
            System.out.println("No hay carros en el parqueadeor");
            return 0.0;
        } else {
            tiempoPromedio = (double) totalTiempo / totalCarritos;
            return tiempoPromedio;
        }
    }


    public Carro hayCarroMasDeOchoHoras() {
        Carro carroMasOcho = null;
        int maxH = 8;
        int tiempoActual;
        for (int i = 0; i < puestos.length; i++) {
            Puesto puestocontar = puestos[i];
            Carro carro = puestocontar.darCarro();

            // Ver si hay carro en ese puesto
            if (carro != null) {
                tiempoActual = carro.darTiempoEnParqueadero(horaActual);
                if (tiempoActual > maxH) {
                    carroMasOcho = carro;
                    maxH = tiempoActual;
                    return carroMasOcho;

                }
            }
        }
        return null;
    }

    public boolean hayCarroMasDeOchoHorasTF() {
        boolean carroMasOcho = false;
        int maxH = 8;
        int tiempoActual;
        for (int i = 0; i < puestos.length; i++) {
            Puesto puestocontar = puestos[i];
            Carro carro = puestocontar.darCarro();

            // Ver si hay carro en ese puesto
            if (carro != null) {
                tiempoActual = carro.darTiempoEnParqueadero(horaActual);
                if (tiempoActual > maxH) {
                    carroMasOcho = true;
                    break;

                }
            }
        }
        return carroMasOcho;
    }


    public ArrayList<Carro> darCarrosMasDeTresHorasParqueados() {
        ArrayList<Carro> carrosMasDeTresH = new ArrayList<>();
        int tiempoMax = 3;

        for (int i = 0; i < puestos.length; i++) {
            Puesto puestocontar = puestos[i];
            Carro carro = puestocontar.darCarro();
            if (carro != null) {
                // Calcular el tiempo  en el parqueadero
                int tiempoActual = carro.darTiempoEnParqueadero(horaActual);

                if (tiempoActual > tiempoMax) {
                    carrosMasDeTresH.add(carro);
                }
            }
        }
        return carrosMasDeTresH;
    }

    public boolean hayCarrosPlacaIgual() {
        for (int i = 0; i < puestos.length; i++) {
            Carro carro1 = puestos[i].darCarro();
            if (carro1 != null) {
                String placaCarro1 = carro1.darPlaca();
                for (int j = i + 1; j < puestos.length; j++) {
                    Carro carro2 = puestos[j].darCarro();
                    if (carro2 != null) {
                        String placaCarro2 = carro2.darPlaca();
                        if (placaCarro1.equals(placaCarro2)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    // TERCERA PARTE DEL EJERCICIO

    public int contarCarrosQueComienzanConPlacaPB() {
        int count = 0;
        for (int i = 0; i < puestos.length; i++) {
            Puesto puesto = puestos[i];
            Carro carro = puesto.darCarro();
            if (carro != null && carro.darPlaca().startsWith("PB")) {
                count++;
            }
        }
        return count;
    }

    public boolean hayCarroCon24Horas() {
        int horasMaximas = 24;
        for (Puesto puesto : puestos) {
            Carro carro = puesto.darCarro();
            if (carro != null && carro.darTiempoEnParqueadero(horaActual) >= horasMaximas) {
                return true;
            }
        }
        return false;
    }

    public String metodo1() {
        int countCarrosPB = contarCarrosQueComienzanConPlacaPB();
        String mensajeCarrosPB = "Cantidad de carros de placa PB: " + countCarrosPB;
        String mensajeCarro24Horas = hayCarroCon24Horas() ? "Yess" : "No";
        return mensajeCarrosPB + " - Hay carro parqueado por 24 o más horas: " + mensajeCarro24Horas;
    }


    public int desocuparParqueadero() {
        int carrosSacados = 0;
        for (int i = 0; i < puestos.length; i++) {
            if (puestos[i].estaOcupado()) {
                puestos[i].sacarCarro();
                carrosSacados++;
            }
        }
        return carrosSacados;
    }










}