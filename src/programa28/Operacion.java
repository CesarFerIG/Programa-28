package programa28;

import java.util.List;

/**
 * La clase {@code Operación} contiene métodos que permiten realizar operaciones
 * básicas de geometría analítica, requiere de las clases {@link Punto} y 
 * {@link Pendiente}.
 * 
 * @see Punto 
 * @see Pendiente
 * 
 * @author Islas García y Escalante Monsalvo
 */
public class Operacion {
    
    /**
     * Calcula la distancia entre dos puntos.
     * @param a El punto inicial
     * @param b El punto final
     * @return La distancia entre el punto a y b
     */
    public double distancia(Punto a, Punto b) {
        double disX = a.x - b.x; // Diferencia de X
        double disY = a.y - b.y; // Diferencia de Y
        return Math.sqrt(Math.pow(disX, 2) + Math.pow(disY, 2));
    }
    
    /**
     * Calcula el área de un polígono a partir de sus vértices.
     * @param puntos Los vértices del polígono
     * @return El área del poligono
     */
    public double area(List<Punto> puntos) {
        double sumar = 0, restar = 0;

        comprobarPuntos(puntos);
        
        for (int i = 0; i < (puntos.size() - 1); i++) {
            sumar += puntos.get(i).x * puntos.get(i + 1).y;
        }

        for (int i = puntos.size() - 1; i > 0; i--) {
            restar -= (puntos.get(i).x * puntos.get(i - 1).y);
        }
        
        return 0.5 * Math.abs(sumar + restar);
    }

    /**
     * Calcula el perímetro de un polígono a partir de sus vértices.
     * @param puntos Los vértices del polígono
     * @return El perímetro del polígono
     */
    public double perimetro(List<Punto> puntos) {
        double perimetro = 0;

        comprobarPuntos(puntos);

        for (int i = 0; i < puntos.size() - 1; i++) {
            perimetro += distancia(puntos.get(i), puntos.get(i + 1));
        }

        return perimetro;
    }
    
    /**
     * Calcula la posición de <i>y</i> en una recta a partir de la ecuación 
     * punto-pendiente.
     * @param punto Un punto dado
     * @param pendiente La pendiente de la recta
     * @param x La posicion de <i>x</i>
     * @return La posición de <i>y</i>
     */
    public double puntoPendienteY(Punto punto, Pendiente pendiente, double x) {
        return pendiente.m * (x - punto.x) + punto.y;
    }
    
    /**
     * Calcula la ecuación de la ordenada al orígen.
     * @param punto Un punto dado
     * @param pendiente La pendiente de la recta
     * @return Una cadena que incluye la ecuación de la ordenada al orígen
     */
    public String ordenadaOrigen(Punto punto, Pendiente pendiente) {
        double b = ((pendiente.m * -punto.x) - punto.y);
        return "y = " + pendiente.m + "X" + ((Math.signum(b) == 1) ? " + " : " - ") + Math.abs(b);
    }
    
    /**
     * Comprueba si en una lista de puntos el primer punto es igual al último,
     * y en caso de que no lo sea agrega el primer punto al final de la lista
     * con el proposito de poder ser usado en los cálculos de {@code area} y 
     * {@code perimetro}.
     * @param puntos Una lista de puntos
     * @see Operacion#area(java.util.List) 
     * @see Operacion#perimetro(java.util.List)  
     */
    private void comprobarPuntos(List<Punto> puntos) {
        if (!puntos.get(puntos.size() - 1).equals(puntos.get(0))) {
            puntos.add(puntos.get(0));
        }
    }

}
