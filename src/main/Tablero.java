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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
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
    private Finestra finestra;
    private ArrayList<Enemic> enemics;
    private ArrayList<Casella> recorrido_enemic1, recorrido_enemic2, recorrido_enemic3, recorrido_enemic4, recorrido_enemic5, recorrido_enemic6, recorrido_enemic7;

    public Tablero(Finestra finestra) {
        enemics = new ArrayList<Enemic>();
        recorrido_enemic1 = new ArrayList<Casella>();
        recorrido_enemic2 = new ArrayList<Casella>();
        recorrido_enemic3 = new ArrayList<Casella>();
        recorrido_enemic4 = new ArrayList<Casella>();
        recorrido_enemic5 = new ArrayList<Casella>();
        recorrido_enemic6 = new ArrayList<Casella>();
        recorrido_enemic7 = new ArrayList<Casella>();
        initRecorregutEnemics();
        this.finestra = finestra;

    }

    private void initRecorregutEnemics() {
        // enemic del moviment en forma de N
        recorrido_enemic1.add(new Casella(6, 5)); 
        recorrido_enemic1.add(new Casella(7, 5));
        recorrido_enemic1.add(new Casella(8, 5));
        recorrido_enemic1.add(new Casella(9, 5));
        recorrido_enemic1.add(new Casella(9, 4));
        recorrido_enemic1.add(new Casella(9, 3));
        recorrido_enemic1.add(new Casella(10, 3));
        recorrido_enemic1.add(new Casella(11, 3));
        recorrido_enemic1.add(new Casella(12, 3));

        recorrido_enemic2.add(new Casella(8, 9)); 
        recorrido_enemic2.add(new Casella(9, 9));
        recorrido_enemic2.add(new Casella(9, 10));
        recorrido_enemic2.add(new Casella(9, 11));
        recorrido_enemic2.add(new Casella(8, 11));

        recorrido_enemic3.add(new Casella(4, 11)); 
        recorrido_enemic3.add(new Casella(5, 11));
        recorrido_enemic3.add(new Casella(6, 11));
        recorrido_enemic3.add(new Casella(6, 12));
        recorrido_enemic3.add(new Casella(6, 13));
        recorrido_enemic3.add(new Casella(6, 14));

        // enemic d'adalt a l'esquerra
        recorrido_enemic4.add(new Casella(3, 7)); 
        recorrido_enemic4.add(new Casella(4, 5));
        recorrido_enemic4.add(new Casella(3, 5));
        recorrido_enemic4.add(new Casella(2, 5));
        recorrido_enemic4.add(new Casella(1, 5));
        recorrido_enemic4.add(new Casella(1, 4)); 

        // enemic de sa sortida
        recorrido_enemic5.add(new Casella(12, 14));
        recorrido_enemic5.add(new Casella(13, 19));
        recorrido_enemic5.add(new Casella(14, 19));
        recorrido_enemic5.add(new Casella(14, 18));

        recorrido_enemic6.add(new Casella(19, 4));
        recorrido_enemic6.add(new Casella(19, 5));
        recorrido_enemic6.add(new Casella(19, 6));
        recorrido_enemic6.add(new Casella(18, 6));
        recorrido_enemic6.add(new Casella(17, 6));
        recorrido_enemic6.add(new Casella(16, 6));
        recorrido_enemic6.add(new Casella(16, 7));

        recorrido_enemic7.add(new Casella(15, 7)); 
        recorrido_enemic7.add(new Casella(15, 8));
        recorrido_enemic7.add(new Casella(15, 9));
        recorrido_enemic7.add(new Casella(15, 10));
        recorrido_enemic7.add(new Casella(15, 11));
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
        int r = generarRandom(1, 4); // el nombre d'objectes en la casella composta
        int j;
        ArrayList<Casella> array = new ArrayList<Casella>();
        for (int i = 0; i < r; i++) { // elegir objectes a l'atzar i els afegim dins un array
            j = (int) Math.floor(Math.random() * 4);
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
        objectes_random.add(new casellaComposta());
        objectes_random.add(new Bomba());

        jug = new MiniJugador();
        jug.setSalut(100);
        jug.canviarComportament(generarRandom(2, 3), generarRandom(2, 3));

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
//            System.out.println("\nfitxer: " + f.getCanonicalPath());
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
                        casellaComposta composta = new casellaComposta(x, y);
                        composta.setObjectes(randomObjectes(objectes_random));
                        taulell[x][y] = composta;
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Error en la carga de datos " + e.toString());
        }
    }

    public ImageIcon scale(Image src, double scale) {
        int w = (int) (scale * src.getWidth(null));
        int h = (int) (scale * src.getHeight(null));
        int type = BufferedImage.TRANSLUCENT;
        BufferedImage dst = new BufferedImage(w, h, type);
        Graphics2D g2 = dst.createGraphics();
        g2.drawImage(src, 0, 0, w, h, null);
        g2.dispose();
        g2.setBackground(Color.CYAN);
        return new ImageIcon(dst);
    }

    public void cas_bomba() {
        getJug().disminuirSalut(generarRandom(10, 15));
        getJug().disminuirHabilitat(generarRandom(3, 6));
    }

    public void cas_tirita() {
        getJug().augmentarSalut(generarRandom(10, 15));
    }

    public void dialog_casella(JFrame frame, ArrayList<Casella> objectes) {
        final JDialog jd = new JDialog(frame, true);

        jd.setPreferredSize(new Dimension(200, 200));
        jd.setLayout(new GridLayout(2, 2));
        jd.setTitle("Casella composta");
        jd.setResizable(false);

        for (int i = 0; i < objectes.size(); i++) {
            String nombre_img = null;
            if (objectes.get(i) instanceof Pocima) {
                nombre_img = "pocima.png";
            } else if (objectes.get(i) instanceof Bomba) {
                nombre_img = "bomba.png";
            } else if (objectes.get(i) instanceof Tirita) {
                nombre_img = "tirita.png";
            } else if (objectes.get(i) instanceof casellaComposta) {
                nombre_img = "composta2.png";
            }
            JLabel temp = new JLabel(scale(new ImageIcon("images/" + nombre_img).getImage(), 0.65));
            temp.setName(nombre_img);
            temp.setBorder(BorderFactory.createRaisedBevelBorder());
            temp.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel objeto = (JLabel) e.getComponent();

                    if (objeto.getName().equals("bomba.png")) {
                        cas_bomba();
                    } else if (objeto.getName().equals("pocima.png")) {
                        cas_pocima();
                    } else if (objeto.getName().equals("composta2.png")) {
                        dialog_casella(finestra,randomObjectes(objectes_random)); // cridada recursiva
                    } else if (objeto.getName().equals("tirita.png")) {
                        cas_tirita();
                    }
                    jd.dispose(); // tancam es jdialog
                }

                @Override
                public void mousePressed(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void mouseReleased(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void mouseEntered(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void mouseExited(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet.");
                }
             
            });
            jd.getContentPane().add(temp, i);
        
        }
        SwingUtilities.updateComponentTreeUI(jd);
        jd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jd.pack();
        jd.setVisible(true);
    }

    public void hasGuanyat() {
        String msg = "<html><b>HAS ARRIBAT A SA SORTIDA SENSE TRUCS, FELICITATS VICIAT !!!</b></html>";
        JLabel label = new JLabel(msg);
        label.setFont(new Font("serif", Font.PLAIN, 14));
        JOptionPane.showMessageDialog(null, label, "Fi de partida", JOptionPane.PLAIN_MESSAGE);
        System.exit(0); // sortir del joc
    }

    public Casella[][] getTaulell() {
        return taulell;
    }

    public void cas_forat(){
         getJug().disminuirSalut(generarRandom(15, 21));
         getJug().disminuirHabilitat(generarRandom(7, 11));
    }

    public void cas_pocima(){
         getJug().augmentarHabilitat(generarRandom(10, 13));
    }
    
    public void tractar_casella(int f, int c) {
        //pasos++;
        if (taulell[f][c] instanceof Bomba) {//recorrido con tesoros
//            System.out.println("[tractar_casella] Bomba");
            cas_bomba();
            taulell[f][c] = new Cami();
        } else if (taulell[f][c] instanceof Tirita) {
//            System.out.println("[tractar_casella] Tirita");
            cas_tirita();
            taulell[f][c] = new Cami();
        } else if (taulell[f][c] instanceof Forat) {
//            System.out.println("[tractar_casella] Forat");
            cas_forat();
        } else if (taulell[f][c] instanceof Pocima) {
//            System.out.println("[tractar_casella] Pocima");
            cas_pocima();
            taulell[f][c] = new Cami();
        } else if (taulell[f][c] instanceof Sortida) {
//            System.out.println("[tractar_casella] Sortida");
            hasGuanyat();
        } else if (taulell[f][c] instanceof Entrada) {
//            System.out.println("[tractar_casella] Entrada");
            // no tractam
        } else if (taulell[f][c] instanceof casellaComposta) {
//            System.out.println("[tractar_casella] CasellaCompuesta");
            dialog_casella(finestra, taulell[f][c].getObjectes());
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
