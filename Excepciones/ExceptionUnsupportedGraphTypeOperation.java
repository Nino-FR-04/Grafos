package Excepciones;

/**
 * Excepcion que se usa en caso de que se quiera realizar alguna operacion
 * en un grafo de tipo no permitido.
 */
public class ExceptionUnsupportedGraphTypeOperation extends RuntimeException {
    
    /**
     * @param mnsj Mensaje que acompaña la excepcion
     */
    public ExceptionUnsupportedGraphTypeOperation(String mnsj) {
        super(mnsj);
    }

    // Excepcion sin mensaje
    public ExceptionUnsupportedGraphTypeOperation(){}
}
