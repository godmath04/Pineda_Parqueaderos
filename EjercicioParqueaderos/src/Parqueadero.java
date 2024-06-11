public class Parqueadero {
    //CONSTANTES

    public static final int TAMANO = 40;
    public static final int NO_HAY_PUESTO = -1 ;
    public static final int PARQUEADERO_CERRADO = -2;
    public static final int CARRO_NO_EXISTE = -3;
    public static final int CARRO_YA_EXISTE = -4;
    public static final int HORA_INICIAL = 6;
    public static final int HORA_CIERRE = 20;
    public static final int TARIFA_INICIAL = 1200;

    //Atributos

    private Puesto puestos[];
    private int tarifa;
    private int caja;
    private int horaActual;
    private boolean abierto;

    //Constructores

    public Parqueadero(){
        horaActual = HORA_INICIAL;
        abierto = true;
        tarifa = TARIFA_INICIAL;
        caja = 0;

        // Inicio del arreglo de puestos
        puestos = new Puesto[TAMANO];
        for (int i = 0; i< TAMANO; i++){
            puestos[i] = new Puesto(i);
        }
    }

}

