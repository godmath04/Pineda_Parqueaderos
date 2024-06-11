
public class Puesto {
    // Atributos
    private Carro carro;
    // Constructores
    public Puesto( int pPuesto )
    {
        carro = null;
        numeroPuesto = pPuesto;
    }
    // Metodos
     // Retorna el carro del puesto. Si no hay ning�n carro retorna null.
     //El carro que ocupa el puesto.
    public Carro darCarro( )
    {
        return carro;
    }

    public boolean estaOcupado( )
    {
        boolean ocupado = carro != null;
        return ocupado;
    }

    public void parquearCarro( Carro pCarro )
    {
        carro = pCarro;
    }


    public void sacarCarro( )
    {
        carro = null;
    }

    public int darNumeroPuesto( )
    {
        return numeroPuesto;
    }

    public boolean tieneCarroConPlaca( String pPlaca )
    {
        boolean tieneCarro = true;

        if( carro == null )
        {
            tieneCarro = false;
        }
        else if( carro.tienePlaca( pPlaca ) )
        {
            tieneCarro = true;
        }
        else
        {
            tieneCarro = false;
        }

        return tieneCarro;
    }

}