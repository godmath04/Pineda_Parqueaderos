public class Carro {
    // Atributos
    private String placa;
    // Debe estar entre 6 y 20
    private int horaLlegada;
    // Constructores

    public Carro(String pPlaca, int pHora) {
        placa = pPlaca;
        horaLlegada = pHora;
    }
    // Metodos
    public String darPlaca() {
        return placa;
    }

    /**
     * Retorna la hora de llegada del carro.
     *
     * @return La hora en la que lleg� el carro al parqueadero.
     */
    public int darHoraLlegada() {
        return horaLlegada;
    }

    /**
     * Indica si la placa del carro es igual a la recibida por par�metro.
     *
     * @param pPlaca Placa contra la que se est� comparando. pPlaca != null.
     * @return Retorna true si las placas son iguales, false en caso contrario.
     */
    public boolean tienePlaca(String pPlaca) {
        boolean tienePlaca = false;
        if (placa.equalsIgnoreCase(pPlaca)) {
            tienePlaca = true;
        } else {
            tienePlaca = false;
        }
        return tienePlaca;
    }

    /**
     * Calcula el n�mero de horas que debe pagar el carro seg�n el tiempo que lleva el auto en el parqueadero.
     *
     * @param pHoraSalida Hora a la que el carro sale del parqueadero. pHoraSalida >= horaLlegada.
     * @return Tiempo que ha estado en parqueadero.
     */
    public int darTiempoEnParqueadero(int pHoraSalida) {
        int tiempoParqueadero = pHoraSalida - horaLlegada + 1;
        return tiempoParqueadero;
    }
}