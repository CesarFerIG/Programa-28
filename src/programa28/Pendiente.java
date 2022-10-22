package programa28;

/**
 * La clase {@code Pendiente} envuelve el valor de la pendiente para que pueda
 * ser utilizado en cálculos posteriores.
 * @author Islas García y Escalante Monsalvo
 */
public class Pendiente {
    public double m;
    
    /**
     * Instancia una clase {@code pendiente} con valores por defecto.
     */
    public Pendiente() {
        m = 0;
    }
    
    /**
     * Instancia una clase {@code pendiente} a partir de un valor decimal.
     * @param m La pendiente
     */
    public Pendiente(double m) {
        this.m = m;
    }
    
    /**
     * Instancia una clase {@code pendiente} a partir de una fracción.
     * @param n Numerador
     * @param d Denominador
     */
    public Pendiente(int n, int d) {
        m = (double)n/d;
    }
    
    /**
     * Calcula el ángulo de inclinación de la pendiente.
     * @return El ángulo de inclinación.
     */
    public double angulo(){
        return Math.pow(Math.tan(m), -1);
    }
}
