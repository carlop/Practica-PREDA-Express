/**
 * Procesa todos los argumentos que se le pasan al programa.
 *
 * @author Francisco Carlos López Porcel
 * @version 0.1
 */
public class ProcesadorArgumentos {

    // Argumentos que se pasan al programa por línea de comandos.
    private String[] args;

    // Argumentos procesados
    private Boolean traza;
    private Boolean ayuda;
    private String archivoEntrada;
    private String archivoSalida;

    /**
     * Constructor
     * @param args los argumentos que se le pasan al programa
     */
    public ProcesadorArgumentos(String[] args) {
        this.args = args;
        this.traza = false;
        this.ayuda = false;
        this.archivoEntrada = null;
        this.archivoSalida = null;
    }

    /**
     * Procesa los argumentos y si son válidos continua con la ejecuci�n
     * en caso contrario termina el programa
     */
    public void procesarArgumentos() {
        if (args.length == 4) {
            if ((args[0].equals("-t") && args[1].equals("-h")) || (args[1].equals("-h") || args[1].equals("-t"))) {
                this.traza = true;
                this.ayuda = true;
            }
            this.archivoEntrada = args[2];
            this.archivoSalida = args[3];
        } else if (args.length == 3) {
            if ((args[0].equals("-t") && args[1].equals("-h")) || (args[0].equals("-h") && args[1].equals("-t"))) {
                this.traza = true;
                this.ayuda = true;
                this.archivoEntrada = args[2];
            } else if (args[0].equals("-t") && !args[1].equals("-h")) {
                this.traza = true;
                this.archivoEntrada = args[1];
                this.archivoSalida = args[2];
            } else if (args[0].equals("-h") && !args[1].equals("-t")) {
                this.ayuda = true;
                this.archivoEntrada = args[1];
                this.archivoSalida = args[2];
            }
        } else if (args.length == 2) {
            if ((args[0].equals("-t") && args[1].equals("-h")) || (args[0].equals("-h") && args[1].equals("-t"))) {
                malosArgumentos();
            } else if (args[0].equals("-t") && !args[1].equals("-h")) {
                this.traza = true;
                this.archivoEntrada = args[1];
            } else if (args[0].equals("-h") && !args[1].equals("-t")) {
                this.ayuda = true;
                this.archivoEntrada = args[1];
            } else {
                this.archivoEntrada = args[0];
                this.archivoSalida = args[1];
            }
        } else if (args.length == 1) {
            if (args[0].equals("-t")) {
                malosArgumentos();
            }
            else if (args[0].equals("-h")) {
                this.ayuda = true;
            } else {
                this.archivoEntrada = args[0];
            }
        } else {
            mostrarAyuda();
        }
    }

    /**
     * Muestra la ayuda del programa
     */
    public void mostrarAyuda()
    {
        System.out.println("Express v0.1");
        System.out.println("java -jar express.jar -t -h [fichero_entrada] [fichero_salida]");
        System.out.println("-t traza cada paso de modo que se describe la aplicación del algoritmo");
        System.out.println("-h muestra la ayuda (esta pantalla)");
        System.out.println("[fichero_entrada] fichero del que se leen los datos");
        System.out.println("[fichero_salida] fichero en el que se guardan los resultados");

    }

    public Boolean getTraza() {
        return traza;
    }

    public Boolean getAyuda() {
        return ayuda;
    }

    public String getArchivoEntrada() {
        return archivoEntrada;
    }

    public String getArchivoSalida() {
        return archivoSalida;
    }

    /**
     * Termina el programa y muestra información de error por malos argumentos
     */
    private void malosArgumentos() {
        System.out.println("Argumentos inválidos");
        this.mostrarAyuda();
        System.exit(0);
    }
}