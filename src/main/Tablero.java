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
import java.awt.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Classe que implementa la lectura del fitxer i crea els objectes del taulell
 * @author vjuan
 */
public class Tablero {

    private Casella[][] taulell;
    private int valor;
    private int files;
    private int columnes;
    private double tamany;
    private int cpocima;
    private int cbomba;
    private int cforat;
    private int ctirita;
    private ArrayList<Casella> objectes_random;
    private String rutamapa;
    private Enemic enemic1, enemic2, enemic3, enemic4, enemic5, enemic6, enemic7;
    private Jugador jug;
    private ArrayList<Enemic> enemics;
    private ArrayList<Casella> recorrido_enemic1, recorrido_enemic2, recorrido_enemic3, recorrido_enemic4, recorrido_enemic5, recorrido_enemic6, recorrido_enemic7;

    public Tablero() {
        enemics = new ArrayList<Enemic>();
        recorrido_enemic1 = new ArrayList<Casella>();
        recorrido_enemic2 = new ArrayList<Casella>();
        recorrido_enemic3 = new ArrayList<Casella>();
        recorrido_enemic4 = new ArrayList<Casella>();
        recorrido_enemic5 = new ArrayList<Casella>();
        recorrido_enemic6 = new ArrayList<Casella>();
        recorrido_enemic7 = new ArrayList<Casella>();        
        initRecorregutEnemics();
    }

    private void initRecorregutEnemics() {
        // enemic del moviment en forma de N
        recorrido_enemic1.add(new Casella(6,5)); // no se veu el moviment
        recorrido_enemic1.add(new Casella(7,5));
        recorrido_enemic1.add(new Casella(8,5));
        recorrido_enemic1.add(new Casella(9,5));
        recorrido_enemic1.add(new Casella(9,4));
        recorrido_enemic1.add(new Casella(9,3));
        recorrido_enemic1.add(new Casella(10,3));
        recorrido_enemic1.add(new Casella(11,3));
        recorrido_enemic1.add(new Casella(12,3));

        recorrido_enemic2.add(new Casella(8,9)); // no se veu el moviment
        recorrido_enemic2.add(new Casella(9,9));
        recorrido_enemic2.add(new Casella(9,10));
        recorrido_enemic2.add(new Casella(9,11));
        recorrido_enemic2.add(new Casella(8,11));

        recorrido_enemic3.add(new Casella(4,11)); // no se veu el moviment
        recorrido_enemic3.add(new Casella(5,11));
        recorrido_enemic3.add(new Casella(6,11));
        recorrido_enemic3.add(new Casella(6,12));
        recorrido_enemic3.add(new Casella(6,13));
        recorrido_enemic3.add(new Casella(6,14));

        // enemic d'adalt a l'esquerra
        recorrido_enemic4.add(new Casella(3,7)); // no se veu el moviment
        recorrido_enemic4.add(new Casella(4,5));
        recorrido_enemic4.add(new Casella(3,5));
        recorrido_enemic4.add(new Casella(2,5));
        recorrido_enemic4.add(new Casella(1,5));
        recorrido_enemic4.add(new Casella(1,4)); // afegit

        // enemic de sa sortida
        recorrido_enemic5.add(new Casella(12,14)); // no se veu el moviment
        recorrido_enemic5.add(new Casella(13,19));
        recorrido_enemic5.add(new Casella(14,19));
        recorrido_enemic5.add(new Casella(14,18));

        recorrido_enemic6.add(new Casella(19,4)); //
        recorrido_enemic6.add(new Casella(19,5));
        recorrido_enemic6.add(new Casella(19,6));
        recorrido_enemic6.add(new Casella(18,6));
        recorrido_enemic6.add(new Casella(17,6));
        recorrido_enemic6.add(new Casella(16,6));
        recorrido_enemic6.add(new Casella(16,7));

        recorrido_enemic7.add(new Casella(15,7)); //
        recorrido_enemic7.add(new Casella(15,8));
        recorrido_enemic7.add(new Casella(15,9));
        recorrido_enemic7.add(new Casella(15,10));
        recorrido_enemic7.add(new Casella(15,11));
    }

    public ArrayList<Enemic> getEnemics() {
        return enemics;
    }

    public void setEnemics(ArrayList<Enemic> enemics) {
        this.enemics = enemics;
    }

    public void afegirEnemic(Enemic bot) {
        enemics.add(bot);
    }

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

     public void initObjectes() {
        cbomba = 0;
        cforat = 0;
        ctirita = 0;
        cpocima = 0;
        
        objectes_random = new ArrayList<Casella>();

        objectes_random.add(new Pocima());
        objectes_random.add(new Tirita());
        objectes_random.add(new Bomba());
        objectes_random.add(new Casella());

        jug = new MiniJugador();
        jug.setSalut(100);
        jug.canviarComportament(3, 3);

        enemic1 = new Enemic(recorrido_enemic1);
        enemic2 = new Enemic(recorrido_enemic2);
        enemic3 = new Enemic(recorrido_enemic3);
        enemic4 = new Enemic(recorrido_enemic4);
        enemic5 = new Enemic(recorrido_enemic5);
        enemic6 = new Enemic(recorrido_enemic6);
        enemic7 = new Enemic(recorrido_enemic7);

        afegirEnemic(enemic1);
        afegirEnemic(enemic2);
        afegirEnemic(enemic3);
        afegirEnemic(enemic4);
        afegirEnemic(enemic5);
        afegirEnemic(enemic6);
        afegirEnemic(enemic7);
     }

    public void leerArchivo(File f) {
        FileReader elFichero;
        BufferedReader lector = null;
        String unaLinea;
        StringTokenizer str;

        try {
            elFichero = new FileReader(f);
            System.out.println("\nfitxer: " + f.getCanonicalPath());
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
            setTamany((705 + fi) / fi); // indicamos el tamaño de la ventana;

            if ((fi <= 0) || (co <= 0)) {
                throw new Exception(); // si los indices de la taulell son inferiores a cero, lanzamos excepcion.
            }
            taulell = new Casella[fi][co];
            inicializartaulell(taulell); // inicializamos el taulell poniendo que todo sea Camino
        } catch (Exception e) {
            System.out.println("ERROR AL INSERTAR DATOS " + e.toString());
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
                        getJug().setY(y);
                        getJug().setY(x);
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

        } catch (Exception e) {
            System.err.println("Error en la carga de datos " + e.toString());
        }
    }

//    private ImageIcon scale(Image src, double scale) {
//        int w = (int) (scale * src.getWidth(null)); //this
//        int h = (int) (scale * src.getHeight(null)); //this
//        int type = BufferedImage.TRANSLUCENT;
//        BufferedImage dst = new BufferedImage(w, h, type);
//        Graphics2D g2 = dst.createGraphics();
//        g2.drawImage(src, 0, 0, w, h, null); //this
//        g2.dispose();
//        g2.setBackground(Color.CYAN);
//        return new ImageIcon(dst);
//    }


//    public void dialog_casella(JFrame frame) {
//        JDialog jd = new JDialog(frame, true);
//
//        jd.setPreferredSize(new Dimension(200, 200)); // per provar
//        jd.setLayout(new GridLayout(2, 2));
//
//        jd.getContentPane().add(new JLabel(scale(new ImageIcon("images/pocima.png").getImage(), tamany)), 0);
//        jd.getContentPane().add(new JLabel(scale(new ImageIcon("images/tirita.png").getImage(), tamany)), 1);
//        jd.getContentPane().add(new JLabel(scale(new ImageIcon("images/bomba.png").getImage(), tamany)), 2);
//        jd.getContentPane().add(new JLabel(scale(new ImageIcon("images/composta2.png").getImage(), tamany)), 3);
//
//        jd.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//        jd.pack();
//        jd.setVisible(true);
//    }
    
    public void hasPerdut() {
        String msg = "<html><b>HAS ARRIBAT A SA SORTIDA, FELICITATS VICIAT !!!</b></html>";
        JLabel label = new JLabel(msg);
        label.setFont(new Font("serif", Font.PLAIN, 14));
        JOptionPane.showMessageDialog(null, label, "Fi de partida", JOptionPane.PLAIN_MESSAGE);
        System.exit(0); // sortir del joc
    }

    public Casella[][] getTaulell() {
        return taulell;
    }

    public void tractar_casella(int f, int c) {
        //pasos++;
        if (taulell[f][c] instanceof Bomba) {//recorrido con tesoros
            System.out.println("[tractar_casella] Bomba");
            getJug().disminuirSalut(15);
            getJug().disminuirHabilitat(5);
            taulell[f][c] = new Cami();
        } else if (taulell[f][c] instanceof Tirita) {
            System.out.println("[tractar_casella] Tirita");
            getJug().augmentarSalut(15);
            taulell[f][c] = new Cami();
        } else if (taulell[f][c] instanceof Forat) {
            System.out.println("[tractar_casella] Forat");
            getJug().disminuirSalut(25);
            getJug().disminuirHabilitat(10);
        } else if (taulell[f][c] instanceof Pocima) {
            System.out.println("[tractar_casella] Pocima");
            getJug().augmentarHabilitat(15);
            taulell[f][c] = new Cami();
        } else if (taulell[f][c] instanceof Sortida) {
            System.out.println("[tractar_casella] Sortida");
            hasPerdut();
        } else if (taulell[f][c] instanceof Entrada) {
            System.out.println("[tractar_casella] Entrada");
            // no tractam
        } else if (taulell[f][c] instanceof Casella) { // IMPORTANTE: este caso va el último para diferencias la casilla normal de una compuesta!!
//            dialog_casella();
//            System.out.println("[tractar_casella] Casella"); // PROBLEMA: TOTES SES CASELLES CAMI SON "Casella"
            // getArray() de sa casella
            // mostrar la finestra (JDialog)
            // con mouseEvent coger el elemento que ha pulsado
            // tratar el objeto (tratar_casella)
            // quitar el objeto del array
            // cerrar la ventana
        }
    }

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
    public double getTamany() {
        return tamany;
    }

    /**
     * @param tamany the tamany to set
     */
    public void setTamany(int tamany) {
        this.tamany = tamany;
    }

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


    /**
     * @return the mj
     */
    public Jugador getJug() {
        return jug;
    }

    /**
     * @param mj the mj to set
     */
    public void setJug(Jugador mj) {
        this.jug = mj;
    }
    
}
