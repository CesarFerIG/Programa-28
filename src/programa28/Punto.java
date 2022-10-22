package programa28;

/**
 * La clase {@code Punto} envuelve los valores que constituyen la coordenada 
 * de un punto para que puedan ser utilizado en cálculos posteriores.
 * @author Islas García y Escalante Monsalvo
 */
public class Punto {

    public char nomPunto;
    public double x, y;

    /**
     * Instancia una clase {@code Punto} con valores por defecto.
     */
    public Punto() {
        this.nomPunto = 'A';
        this.x = 0;
        this.y = 0;
    }

    /**
     * Instancia una clase {@code Punto} a partir de la coordenadas.
     * @param x Posición de <i>x</i>
     * @param y Posición de <i>y</i>
     */
    public Punto(double x, double y) {
        this.nomPunto = 'A';
        this.x = x;
        this.y = y;
    }
    
    /**
     * Instancia una clase {@code Punto} a partir de la coordenadas y un nombre.
     * @param nomPunto Nombre del punto
     * @param x Posición de <i>x</i>
     * @param y Posición de <i>y</i>
     */
    public Punto(char nomPunto, double x, double y) {
        this.nomPunto = nomPunto;
        this.x = x;
        this.y = y;
    }

    /**
     * Devuelve una cadena que contiene el nombre y la coordenada del punto {@code (Sobreescrito)}.
     * @return Una cadena con la sintaxis {@code "nP ( x, y )"}
     */
    @Override
    public String toString() {
        return nomPunto + " ( " + x + " , " + y + " )";
    }

}
