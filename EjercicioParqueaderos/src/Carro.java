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

    public int darHoraLlegada() {
        return horaLlegada;
    }

    //return Retorna true si las placas son iguales, false en caso contrario.

    public boolean tienePlaca(String pPlaca) {
        boolean tienePlaca = false;
        if (placa.equalsIgnoreCase(pPlaca)) {
            tienePlaca = true;
        } else {
            tienePlaca = false;
        }
        return tienePlaca;
    }
    // Tiempo en parqueadero
    public int darTiempoEnParqueadero(int pHoraSalida) {
        int tiempoParqueadero = pHoraSalida - horaLlegada + 1;
        return tiempoParqueadero;
    }
}