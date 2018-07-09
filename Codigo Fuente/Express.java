import java.io.IOException;
import java.util.List;
/**
 * Clase principal.
 *
 * @author Francisco Carlos L�pez Porcel
 * @version 0.1
 */
public class Express
{
    /**
     * Método main
     * @throws IOException 
     */
    public static void main (String[] args) throws IOException
    {
        // Lee los argumentos de entrada
        ProcesadorArgumentos argumentos = new ProcesadorArgumentos(args);
        // Procesa los argumentos
        argumentos.procesarArgumentos();
        // Archivo de entrada
        String archivoEntrada = argumentos.getArchivoEntrada();
        // Archivo de salida
        String archivoSalida = argumentos.getArchivoSalida();

        // Muestra la ayuda
        if (argumentos.getAyuda()) {
            argumentos.mostrarAyuda();
        }

        // Si hay archivos de entrada se opera
        if (archivoEntrada != null) {
            // Lee el archivo de entrada
            List<String> contenidoArchivo = ManejadorArchivos.leerArchivo(archivoEntrada);

            // Lee el contenido del archivo de entrada
            Integer[] gasolineras = new Integer[contenidoArchivo.size() - 1];
            gasolineras[0] = 0;
            for (int i = 2; i < contenidoArchivo.size(); i++) {
                gasolineras[i-1] = Integer.valueOf(contenidoArchivo.get(i));
            }

            // Inicia el algoritmo
            Gasolineras gaso = new Gasolineras(gasolineras,
                    Integer.valueOf(contenidoArchivo.get(0)),
                    Integer.valueOf(contenidoArchivo.get(1)));
            // Comprueba que los datos son correctos
            gaso.compruebaDatos();
            // Si hay hay archivo de salida guarda del resultado en �l si no lo muestra en pantalla
            if (archivoSalida != null) {
                String contenidoArchivoAGuardar = gaso.getResultadosGasolineras(argumentos.getTraza());
                ManejadorArchivos.guardarArchivo(archivoSalida, contenidoArchivoAGuardar);
            } else {
                System.out.println(gaso.getResultadosGasolineras(argumentos.getTraza()));
            }
        }
    }
}