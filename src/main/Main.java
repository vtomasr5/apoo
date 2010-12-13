/*
 * Main.java
 * 
 * Copyright (C) 2010 Vicenç Juan Tomàs Monserrat
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package main;

import game.composite.*;
import game.composite.Casella;
import game.decorator.Jugador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;

/**
 * Clase que inicia el joc
 * @author vjuan
 */
public class Main {

    private Casella[][] taulell;
    private int valor;
    private int files;
    private int columnes;
    private int tamany;
    private int cpocima;
    private int cbomba;
    private int cforat;
    private int ctirita;
    private ArrayList<Casella> objectes_random;

    public void inicializartaulell(Casella[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                m[i][j] = new Cami(i, j);
            }
        }
    }
    
    public void comprobar_taulell(Casella[][] taulell){
    	for (int i = 0; i < taulell.length; i++) {
            for (int j = 0; j < taulell.length; j++) {
                System.out.println("comprovacio taulell: " + taulell[i][j]);
            }
        }
    }

    public void setFilasxColumnas(int files, int columnes) {
        this.files = files;
        this.columnes = columnes;
    }

    public ArrayList<Casella> randomObjectes(ArrayList<Casella> objectes_random) {
        int r = (int) Math.floor(Math.random()*3);
        int j;
        ArrayList<Casella> array = new ArrayList<Casella>();

        for (int i = 0; i < r; i++) {
             j = (int) Math.floor(Math.random()*3);
             array.add(objectes_random.get(j));
        }
        return array;
    }


    public void leerArchivo() {
        cbomba = 0;
        cforat = 0;
        ctirita = 0;
        cpocima = 0;
        FileReader elFichero;
        BufferedReader lector = null;
        String unaLinea;
        StringTokenizer str;

        objectes_random = new ArrayList<Casella>();
        objectes_random.add(new Pocima());
        objectes_random.add(new Tirita());
        objectes_random.add(new Bomba());
        objectes_random.add(new Casella());

        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setApproveButtonText("Obrir");
            chooser.showOpenDialog(null);
            File archivo = chooser.getSelectedFile();
            elFichero = new FileReader(archivo);
//            elFichero = new FileReader("tele5x5_2.txt");
            lector = new BufferedReader(elFichero);
            unaLinea = lector.readLine();
            str = new StringTokenizer(unaLinea, ",");
            String palabra = str.nextToken();
            if (!(palabra.equals("dimensio"))) {
                throw new Exception(); // Si la primera sentencia no es "dimensio" lanzamos excepcion.
            }
            int fi = Integer.valueOf(str.nextToken()).intValue();
            int co = Integer.valueOf(str.nextToken()).intValue();
            setFilasxColumnas(fi, co); // insertamos las filas y las columnas
            tamany = (640 + fi) / fi; // indicamos el tamaño de la ventana;
//            ventana.setTamaño((tamany / 100));
            if ((fi <= 0) || (co <= 0)) {
                throw new Exception(); // si los indices de la taulell son inferiores a cero, lanzamos excepcion.
            }
            taulell = new Casella[fi][co];
            inicializartaulell(taulell); // inicializamos el taulell poniendo que todo sea Camino
        } catch (Exception e) {
            System.out.println("ERROR AL INSERTAR DATOS");
        }
        
        try {
            while ((unaLinea = lector.readLine()) != null) {
                str = new StringTokenizer(unaLinea, ",");
                String palabra = str.nextToken();
                if (!palabra.equals("##fi")) {
                    int x = Integer.valueOf(str.nextToken()).intValue();
                    int y = Integer.valueOf(str.nextToken()).intValue();
                    if (palabra.equals("entrada")) {
                        taulell[x][y] = new Entrada(x, y);
                    } else if (palabra.equals("sortida")) {
                        taulell[x][y] = new Sortida(x, y);
                    } else if (palabra.equals("pocima")) {
                        valor = Integer.valueOf(str.nextToken()).intValue();
                        taulell[x][y] = new Pocima(x, y, valor);
                        cpocima++;
                    } else if (palabra.equals("bomba")) {
                        valor = Integer.valueOf(str.nextToken()).intValue();
                        taulell[x][y] = new Bomba(x, y, valor);
                        cbomba++;
                    } else if (palabra.equals("tirita")) {
                        valor = Integer.valueOf(str.nextToken()).intValue();
                        taulell[x][y] = new Tirita(x, y, valor);
                        ctirita++;
                    } else if (palabra.equals("forat")) {
                        valor = Integer.valueOf(str.nextToken()).intValue();
                        taulell[x][y] = new Forat(x, y, valor);
                        cforat++;
                    } else if (palabra.equals("paret")) {
                        taulell[x][y] = new Paret(x, y);
                    } else if (palabra.equals("casellaComposta")) {
                        Casella composta = new Casella(x, y);
                        composta.setObjectes(randomObjectes(objectes_random));
                        taulell[x][y] = composta;
                    }
                }
            }
            comprobar_taulell(taulell);
        } catch (Exception e) {
            System.err.println("Error en la carga de datos");
        }
    }

//    public boolean validarCasilla(int f, int c) {    /* Una casilla es valida cuando esta dentro del taulell y NO esta visitada */
//        boolean resultado = true;
//        /* Controla si la posicion esta dentro del taulell */
//        if (((f >= 0) && (f < filas) && (c >= 0) && (c < columnas))) {
//            /* Controla si la posicion ya fue visitada o es paret */
//            if (taulell[f][c] instanceof Paret || recorridoAux.casillaVisitada(taulell[f][c])) {
//                resultado = false;
//            }
//        } else {
//            resultado = false;
//        }
//        return resultado;
//    }

    public void tratar_casella(int f, int c, Jugador jugador) {
        //pasos++;
        if (taulell[f][c] instanceof Bomba) {//recorrido con tesoros
            jugador.setSalut(jugador.getSalut() - 50); // -50 salut
            taulell[f][c] = new Cami();
        } else if (taulell[f][c] instanceof Tirita) {
            jugador.setSalut(jugador.getSalut() + 20); // +20 salut
            taulell[f][c] = new Cami();
        } else if (taulell[f][c] instanceof Forat) {
            jugador.setSalut(0); //Termina el juego
        } else if (taulell[f][c] instanceof Pocima) {
            jugador.setHabilitat(jugador.getHabilitat() + 10); // +10 habilitat
            taulell[f][c] = new Cami();
        } else if (taulell[f][c] instanceof Sortida) {
            // joc acabat
            // missatge "has guanyat"
        } else if (taulell[f][c] instanceof Entrada) {
            // no tractam
        } else if (taulell[f][c] instanceof Casella) { // IMPORTANTE: este caso va el último para diferencias la casilla normal de una compuesta!!
            // getArray() de sa casella
            // mostrar la finestra (JDialog)
            // con mouseEvent coger el elemento que ha pulsado
            // tratar el objeto (tratar_casella)
            // quitar el objeto del array
            // cerrar la ventana
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main joc = new Main();
        joc.leerArchivo();
        
        Finestra f = new Finestra();
        f.pintarFinestra();
    }

    /**
     * Genera numeros aleatoris dins un interval
     */
    public static int generarRandom(int m, int n) {
        int num = (int) Math.floor(Math.random()*(n - m + 1) + m);
        return num;
    }

}

/* Informe de la ultima vez 8 dic - en 5 hemos hecho:*/
/*
 * Creamos todas las clases y conseguimos hacer que lea del fichero los objetos
 * y los ponga en la matriz.
 * Tambien los objetos de casilla compuesta.
 * Cada casilla compuesta puede tener varios objetos y otra casilla compuesta.
 *
 * Siguiente paso:
 * Implementar la parte decorator al composite
 */  