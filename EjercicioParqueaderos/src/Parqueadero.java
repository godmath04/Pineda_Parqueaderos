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

    //TERCERA PARTE DEL EJERCICIOO

}