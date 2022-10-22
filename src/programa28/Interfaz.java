package programa28;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * La clase {@code Interfaz} agrupa distintos métodos que permiten al usuario
 * interactuar de forma gráfica con la clase {@link Operacion}.
 * 
 * @see Operacion
 * @see Punto
 * @see Pendiente
 * 
 * @author Islas García y Escalante Monsalvo
 */
public class Interfaz {

    public String appNombre = "Calculadora | Geometría Analítica", titulo = "";
    public Operacion o = new Operacion();

    /**
     * Imprime el menu principal de opciones.
     * @return La opción seleccionada por el usuario
     */
    private int menuPrincipal() {
        String[] opciones = {" 1 ", " 2 ", " 3 ", " 4 "};
        return JOptionPane.showOptionDialog(null, "Seleccione una operación:"
                + "\n 1. Distancia"
                + "\n 2. Área"
                + "\n 3. Perímetro"
                + "\n 4. Punto pendiente (y)",
                appNombre, -1, -1, null, opciones, 0)+1;
    }
    
    /**
     * Pregunta al usuario por otra operación y en caso de que sí muestra
     * nuevamente el menu principal.
     */
    private void preguntarOtraOperacion(){
        String[] opciones = {" Sí ", " No "};
        int res = JOptionPane.showOptionDialog(null, "¿Desea realizar otra operación?",
                appNombre, -1, -1, null, opciones, 0);
        
        if (res == 0) {
            imprimirMenuPrincipal();
        }
    }
    
    /**
     * Muestra el menu principal y redirige a la opción seleccionada.
     */
    public void imprimirMenuPrincipal() {
        switch (menuPrincipal()) {
            case 1:
                calcularDistancia();
                break;
            case 2:
                calcularArea();
                break;
            case 3:
                calcularPerimetro();
                break;
            case 4:
                calcularPuntosPP();
                break;
        }
        
        preguntarOtraOperacion();
    }

    /**
     * Calcula e imprime la distancia que hay entre dos puntos
     */
    public void calcularDistancia() {
        titulo = "DISTANCIA";
        Punto a = ingresarPunto('A');
        Punto b = ingresarPunto('B');

        imprimir(titulo, a.toString()
                + "\n" + b.toString()
                + "\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -"
                + "\nD(A,B) = " + o.distancia(a, b) + " u");
    }

    /**
     * Calcula e imprime el área
     */
    public void calcularArea() {
        titulo = "ÁREA";
        
        imprimir(titulo, "A = " + o.area(ingresarPuntos()) + " u²");
    }

    /**
     * Calcula e imprime el perímetro
     */
    public void calcularPerimetro() {
        titulo = "PERÍMETRO";

        imprimir(titulo, "P = " + o.perimetro(ingresarPuntos()) + " u");
    }

    /**
     * Calcula e imprime x número de coordenadas determinado por el usuario a
     * partir de un punto y una pendiente.
     */
    public void calcularPuntosPP() {
        Punto puntoA = ingresarPunto('A');
        Pendiente pendiente = ingresarPendiente();

        List<Punto> listaPuntos = calcularPuntosX(puntoA, pendiente);
        
        listaPuntos.add(0, puntoA);
        
        imprimir(titulo, "DATOS:"
                + "\n• Θ = " + pendiente.angulo() + "°"
                + "\n• m = " + pendiente.m
                + "\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -"
                + "\nVALOR DE Y:"
                + "\n• " + o.ordenadaOrigen(puntoA, pendiente)
                + "\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -"
                + "\nCOORDENADAS:"
                + "\n" + lPuntosToString(listaPuntos));
    }

    /**
     * Permite al usuario ingresar una coordenada.
     * @param nomPunto Nombre del punto a ingresar
     * @return El {@code Punto} creado por el usuario
     */
    public Punto ingresarPunto(char nomPunto) {
        double pX = 0, pY = 0;
        String p = ingresar(titulo, "Ingrese la coordenada " + nomPunto + " (x,y):");

        if (p.contains(",") && !(p.substring(p.indexOf(',') + 1, p.length())).isEmpty()) {
            pX = Double.parseDouble(p.substring(0, p.indexOf(',')));
            pY = Double.parseDouble(p.substring(p.indexOf(',') + 1, p.length()));
            return new Punto(nomPunto, pX, pY);
        } else {
            imprimir("Error", "Valores incorrectos, intente de nuevo");
            return ingresarPunto(nomPunto);
        }
    }

    /**
     * Permite al usuario ingresar una {@code Pendiente}.
     * @return La {@code Pendiente} creada por el usuario
     */
    public Pendiente ingresarPendiente() {
        String p = ingresar(titulo, "Pendiente (para fracción introduzca '/')");
        if (!p.contains("/")) {
            double m = Double.parseDouble(p);
            return new Pendiente(m);
        } else if (!(p.substring(p.indexOf('/') + 1, p.length())).isEmpty()) {
            int n = Integer.parseInt(p.substring(0, p.indexOf('/')));
            int d = Integer.parseInt(p.substring(p.indexOf('/') + 1, p.length()));
            return new Pendiente(n, d);
        } else {
            imprimir("Error", "Valores incorrectos, intente de nuevo");
            return ingresarPendiente();
        }
    }

    /**
     * Permite al usuario ingresar una cierta cantidad de puntos que él 
     * mismo determina.
     * @return Una lista con los puntos ingresados por el usuario.
     */
    public List<Punto> ingresarPuntos() {
        List<Punto> puntos = new ArrayList<>();

        int nPuntos = Integer.parseInt(ingresar(titulo, "Número de vertices del polígono (máx. 25):"));
        if (nPuntos > 2 && nPuntos < 26) {

            char nP = 'A';

            for (int i = 0; i < nPuntos; i++) {
                puntos.add(ingresarPunto(nP++));
            }
            return puntos;
        } else {
            imprimir("Error", "Valor no válido, intente nuevamente");
            return ingresarPuntos();
        }
    }
    
    /**
     * Permite al usuario ingresar el valor de <i>x</i>.
     * @param nP Nombre del punto
     * @return El valor de <i>x</i>
     */
    public double ingresarValorX(char nP) {
        return Double.parseDouble(ingresar(titulo, "Ingrese el valor de x para la coordenada " + nP));
    }
    
    /**
     * Permite calcular una cantidad determinada por el usuario de 
     * puntos en una recta a partir de la posición que tenga <i>x</i>, 
     * usando la ecuación punto-pendiente.
     * @param punto Un punto dado
     * @param pendiente La pendiente de la recta
     * @return Una lista con los puntos calculados
     */
    public List<Punto> calcularPuntosX(Punto punto, Pendiente pendiente) {
        List<Punto> puntos = new ArrayList<>();
        
        int numPuntos = Integer.parseInt(ingresar(titulo, "Número de puntos a calcular (máx. 25):"));
        
        if (numPuntos < 26) {
            char nP = 'B';
            for (int i = 0; i < numPuntos; i++) {
                double x = ingresarValorX(nP);
                puntos.add(new Punto(nP, x, o.puntoPendienteY(punto, pendiente, x)));
                nP++;
            }
            return puntos;
        } else {
            imprimir("Error", "Solo se pueden calcular hasta 25 puntos, intente nuevamente");
            return calcularPuntosX(punto, pendiente);
        }
    }
    
    /**
     * Convierte una lista de puntos a una cadena con formato.
     * @param listaPuntos La lista de puntos a convertir
     * @return Una cadena con los puntos separados por un renglón
     */
    public String lPuntosToString(List<Punto> listaPuntos) {
        String puntos = "";
        for (int i = 0; i < listaPuntos.size(); i++) {
            puntos += "• " + listaPuntos.get(i).toString() + "\n";
        }
        return puntos;
    }

    /**
     * Imprime un mensaje en un JOptionPane.
     * @param titulo Título del JOptionPane.
     * @param mensaje Menasaje a mostrar.
     */
    public void imprimir(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, -1);
    }

    /**
     * Permite al usuario ingresar información mediante un JOptionPane
     * @param titulo Título del JOptionPane.
     * @param mensaje Menasaje a mostrar.
     * @return La información introducida por el usuario
     */
    public String ingresar(String titulo, String mensaje) {
        return JOptionPane.showInputDialog(null, mensaje, titulo, -1);
    }
}
