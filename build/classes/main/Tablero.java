/*
 * Tablero.java
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
import game.decorator.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author vjuan
 */
public class Tablero {

    private Casella[][] taulell;
    private int valor;
    private int files;
    private int columnes;
    private int tamany;
    private int cpocima;
    private int cbomba;
    private int cforat;
    private int ctirita;
<<<<<<< HEAD
    private int pos_jugador_f, pos_jugador_c;
=======
    private int pos_jugador_x, pos_jugador_y;
>>>>>>> d521ff3af397c4592ec093a4623f0ed5933e00a6
    private ArrayList<Casella> objectes_random;
    private String rutamapa;

    public void inicializartaulell(Casella[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                m[i][j] = new Cami(i, j);
            }
        }
    }

    public void comprobar_taulell(Casella[][] taulell) {
        for (int i = 0; i < taulell.length; i++) {
            for (int j = 0; j < taulell.length; j++) {
                System.out.println("comprovacio taulell: " + taulell[i][j]);
            }
        }
    }

    public void setFilesxColumnes(int files, int columnes) {
        this.setFiles(files);
        this.setColumnes(columnes);
    }

    public ArrayList<Casella> randomObjectes(ArrayList<Casella> objectes_random) {
        int r = (int) Math.floor(Math.random() * 3);
        int j;
        ArrayList<Casella> array = new ArrayList<Casella>();

        for (int i = 0; i < r; i++) {
            j = (int) Math.floor(Math.random() * 3);
            array.add(objectes_random.get(j));
        }
        return array;
    }

    public void leerArchivo(File f) {
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
//            JFileChooser chooser = new JFileChooser();
//            chooser.setApproveButtonText("Obrir");
//            chooser.showOpenDialog(null);
//            File archivo = chooser.getSelectedFile();
            elFichero = new FileReader(f);
            System.out.print("\nfitxer: \n" + elFichero.toString());
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
            setFilesxColumnes(fi, co); // insertamos las filas y las columnas
            //setTamany((640 + fi) / fi); // indicamos el tamaño de la ventana;
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
                        //Inicializamos la posicion del jugador en la entrada.
<<<<<<< HEAD
                        pos_jugador_f = y;
                        pos_jugador_c = x;
=======
                        pos_jugador_x = x;
                        pos_jugador_y = y;
>>>>>>> d521ff3af397c4592ec093a4623f0ed5933e00a6
                    } else if (palabra.equals("sortida")) {
                        taulell[x][y] = new Sortida(x, y);
                    } else if (palabra.equals("pocima")) {
                        setValor(Integer.valueOf(str.nextToken()).intValue());
                        taulell[x][y] = new Pocima(x, y, getValor());
                        cpocima++;
                    } else if (palabra.equals("bomba")) {
                        setValor(Integer.valueOf(str.nextToken()).intValue());
                        taulell[x][y] = new Bomba(x, y, getValor());
                        cbomba++;
                    } else if (palabra.equals("tirita")) {
                        setValor(Integer.valueOf(str.nextToken()).intValue());
                        taulell[x][y] = new Tirita(x, y, getValor());
                        ctirita++;
                    } else if (palabra.equals("forat")) {
                        
                        setValor(Integer.valueOf(str.nextToken()).intValue());
                        taulell[x][y] = new Forat(x, y, getValor());
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
<<<<<<< HEAD
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+cforat);
=======
>>>>>>> d521ff3af397c4592ec093a4623f0ed5933e00a6
            comprobar_taulell(taulell);
        } catch (Exception e) {
            System.err.println("Error en la carga de datos");
        }
    }

    public Casella[][] getTaulell() {
        return taulell;
    }

    public void tractar_casella(int f, int c, Jugador jugador) {
        //pasos++;
        if (taulell[f][c] instanceof Bomba) {//recorrido con tesoros
            System.out.println("[tractar_casella] Bomba");
            jugador.setSalut(jugador.getSalut() - 50); // -50 salut
            taulell[f][c] = new Cami();
        } else if (taulell[f][c] instanceof Tirita) {
            System.out.println("[tractar_casella] Tirita");
            jugador.setSalut(jugador.getSalut() + 20); // +20 salut
            taulell[f][c] = new Cami();
        } else if (taulell[f][c] instanceof Forat) {
            System.out.println("[tractar_casella] Forat");
            jugador.setSalut(0); //Termina el juego
        } else if (taulell[f][c] instanceof Pocima) {
            System.out.println("[tractar_casella] Pocima");
            jugador.setHabilitat(jugador.getHabilitat() + 10); // +10 habilitat
            taulell[f][c] = new Cami();
        } else if (taulell[f][c] instanceof Sortida) {
            System.out.println("[tractar_casella] Sortida");
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

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        Tablero joc = new Tablero();
//        //joc.leerArchivo(file);
//
//        Finestra f = new Finestra();
//        f.pintarFinestra();
//    }
    /**
     * Genera numeros aleatoris dins un interval
     */
    public static int generarRandom(int m, int n) {
        int num = (int) Math.floor(Math.random() * (n - m + 1) + m);
        return num;
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * @return the files
     */
    public int getFiles() {
        return files;
    }

<<<<<<< HEAD
    public int get_pos_f_jugador() {
        return pos_jugador_f;
    }

    public int get_pos_c_jugador() {
        return pos_jugador_c;
    }

    public void set_pos_x_jugador(int pos_jugador_x) {
        this.pos_jugador_f = pos_jugador_x;
    }

    public void set_pos_y_jugador(int pos_jugador_y) {
        this.pos_jugador_c = pos_jugador_y;
=======
    public int get_pos_x_jugador() {
        return pos_jugador_x;
    }

    public int get_pos_y_jugador() {
        return pos_jugador_y;
    }

    public void set_pos_x_jugador(int pos_jugador_x) {
        this.pos_jugador_x = pos_jugador_x;
    }

    public void set_pos_y_jugador(int pos_jugador_y) {
        this.pos_jugador_y = pos_jugador_y;
>>>>>>> d521ff3af397c4592ec093a4623f0ed5933e00a6
    }

    /**
     * @param files the files to set
     */
    public void setFiles(int files) {
        this.files = files;
    }

    /**
     * @return the columnes
     */
    public int getColumnes() {
        return columnes;
    }

    /**
     * @param columnes the columnes to set
     */
    public void setColumnes(int columnes) {
        this.columnes = columnes;
    }

    /**
     * @return the tamany
     */
    public int getTamany() {
        return tamany;
    }

    /**
     * @param tamany the tamany to set
     */
    public void setTamany(int tamany) {
        this.tamany = tamany;
    }
<<<<<<< HEAD

    /**
     * @return the rutamapa
     */
    public String getRutamapa() {
        return rutamapa;
    }

    /**
     * @param rutamapa the rutamapa to set
     */
    public void setRutamapa(String rutamapa) {
        this.rutamapa = rutamapa;
    }
=======
>>>>>>> d521ff3af397c4592ec093a4623f0ed5933e00a6
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
