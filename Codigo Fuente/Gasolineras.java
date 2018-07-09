/**
 * @author Francisco Carlos López Porcel
 * @version 0.1
 */
public class Gasolineras {

    // Array con la distancia entre gasolineras
    private Integer[] distanciaGasolineras;

    // Distancia máxima sin repostar
    private int distanciaSinRepostar;

    // Número de gasolineras
    private int numeroGasolineras;

    /**
     * Constructor
     */
    public Gasolineras(Integer[] distanciaGasolineras, 
            int distanciaSinRepostar, int numeroGasolineras) {
        this.distanciaGasolineras = distanciaGasolineras;
        this.distanciaSinRepostar = distanciaSinRepostar;
        this.numeroGasolineras = numeroGasolineras;
    }

     /**
     * Algoritmo Voraz que resuelve el problema
     * @param traza
     * @return Resultado de aplicar el algoritmo
     */
    public String getResultadosGasolineras(Boolean traza) {
         // Distancia que lleva reccorida
        int distanciaRecorrida = 0;
        
        // Resultado del algoritmo
        String resultadoGasolinerasDevuelto = new String();
        
        // Contenido de la traza
        String resultadoTraza = new String();
        resultadoTraza = "Kil�metros sin repostar: " + distanciaSinRepostar + System.getProperty("line.separator");
        resultadoTraza += "N� de paradas: " + numeroGasolineras + System.getProperty("line.separator");
        resultadoTraza += "Distancia parcial" + " | " + "Distancia recorrida" + System.getProperty("line.separator");

        int pos = 0;
        do {
            distanciaRecorrida = distanciaRecorrida + distanciaGasolineras[pos];
            
            if (distanciaRecorrida > distanciaSinRepostar) {
                pos -= 1;
                resultadoGasolinerasDevuelto = resultadoGasolinerasDevuelto + pos + " ";
                if (traza) 
                    resultadoTraza += " <-Se añade el nodo " + pos;
                distanciaRecorrida = 0;
            } else {
                if (traza && pos > 0) {
                    if (pos > 1)    resultadoTraza += System.getProperty("line.separator");
                    resultadoTraza += pos + ". ";
                    resultadoTraza += distanciaGasolineras[pos] + "   ";
                    resultadoTraza += distanciaRecorrida;
                }
            }
           
            pos += 1;
        } while(pos < numeroGasolineras);

        if (traza) {
            resultadoTraza += System.getProperty("line.separator") + pos + ". Destino" + System.getProperty("line.separator") + "Resultado: ";
            resultadoGasolinerasDevuelto = resultadoTraza + resultadoGasolinerasDevuelto;
        }
        return resultadoGasolinerasDevuelto;
    }
    
    /**
     * Comprueba que los datos son correctos
     */
    public void compruebaDatos() {
        Boolean distancia = false;
        Boolean gasolineras = false;
        
        for (int i = 0; i < distanciaGasolineras.length; i++) {
            if (distanciaGasolineras[i] > distanciaSinRepostar) {
                distancia = true;
            }
        }
        
        if (numeroGasolineras < distanciaGasolineras.length) {
            gasolineras = true;
        }
        
        if (distancia) {
            System.out.println("Error, una de las distancia entre gasolineras");
            System.out.println("es mayor que la distancia maxima sin repostar");
        }
        if (gasolineras) {
            System.out.println("Error en el número de gasolineras indicado");
            System.out.println("y la cantidad de gasolineras reales");
        }
        if (distancia || gasolineras) {
            System.out.println("El problema no tiene solución");
            System.exit(0);
        }
    }
}