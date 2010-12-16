/*
 * Finestra.java
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
import game.decorator.JugadorHuma;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.ImageIcon;

/**
 *
 * @author vjuan
 */
public class Finestra extends JFrame implements ActionListener, KeyListener {

    private int files;
    private int columnes;
    private Casella[][] taulell;
    private JPanel panel;
    private JMenuBar menu; // barra de menu
    private static File fitxer;
    private JMenu itemMenuArxiu;
    private JMenuItem itemMenuObrir;
    private JSeparator itemMenuSeparador1;
    private JMenuItem itemMenuSortir;
    private JMenuItem itemMenuReset;
    private JLabel[][] imgMatriz;
    private double tamany = 0.65;
    private int pos_jugador_f, pos_temp_f;
    private int pos_jugador_c, pos_temp_c;
    private Tablero tab;

    public Finestra() {}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Finestra finestra = new Finestra();
        finestra.setTitle("Ampliació a sa programació orientada a objectes");
        finestra.initComponents();
        finestra.colocarComponents();
        finestra.afegirListerners();
    }

    public void initTablero(File fitxer) {
        tab = new Tablero();
        tab.leerArchivo(fitxer); //leemos el archivo y guardamos el mapa en la variable taulell
        taulell = tab.getTaulell(); //cogemos la variable taulell

        //inicializamos las variables de posicion del jugador, y del tablero
        pos_jugador_f = tab.get_pos_f_jugador();
        pos_jugador_c = tab.get_pos_c_jugador();
        //System.out.println(pos_jugador_x+" "+pos_jugador_y);
        files = tab.getFiles();
        columnes = tab.getColumnes();
    }

    public void initComponents() {
        panel = new JPanel();
        menu = new JMenuBar();

        itemMenuArxiu = new JMenu("Joc");
        itemMenuObrir = new JMenuItem("Carregar mapa...");
        itemMenuReset = new JMenuItem("Recarregar mapa actual");
        itemMenuSeparador1 = new JSeparator();
        itemMenuSortir = new JMenuItem("Sortir");

        addKeyListener(this); //control eventos de teclado
    }

    public void dibujarElementos(Casella[][] taulell, int filas, int columnas) {

        //panel = new JPanel(new GridLayout(filas, columnas));
        panel.removeAll();
        panel.setLayout(new GridLayout(filas, columnas));
        panel.setBackground(Color.BLACK);// color del camino del laberinto


        imgMatriz = new JLabel[filas][columnas];
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {

                if (taulell[f][c] instanceof Entrada) {
                    ImageIcon img = new ImageIcon("images/entrada.gif");
                    imgMatriz[f][c] = new JLabel(scale(img.getImage(), tamany));

                } else if (taulell[f][c] instanceof Paret) {
                    ImageIcon img = new ImageIcon("images/paret.gif");
                    imgMatriz[f][c] = new JLabel(scale(img.getImage(), tamany));

                } else if (taulell[f][c] instanceof Cami) {
                    imgMatriz[f][c] = new JLabel();

                } else if (taulell[f][c] instanceof Forat) {
                    ImageIcon img = new ImageIcon("images/forat.png");
                    imgMatriz[f][c] = new JLabel(scale(img.getImage(), tamany));

                } else if (taulell[f][c] instanceof Bomba) {
                    ImageIcon img = new ImageIcon("images/bomba.png");
                    imgMatriz[f][c] = new JLabel(scale(img.getImage(), tamany));

                } else if (taulell[f][c] instanceof Sortida) {
                    ImageIcon img = new ImageIcon("images/arribada.gif");
                    imgMatriz[f][c] = new JLabel(scale(img.getImage(), tamany));

                } else if (taulell[f][c] instanceof Tirita) {
                    ImageIcon img = new ImageIcon("images/tirita.png");
                    imgMatriz[f][c] = new JLabel(scale(img.getImage(), tamany));

                } else if (taulell[f][c] instanceof Pocima) {
                    ImageIcon img = new ImageIcon("images/pocima.png");
                    imgMatriz[f][c] = new JLabel(scale(img.getImage(), tamany));

                }
                panel.add(imgMatriz[f][c]);
            }
        }
        this.add(panel, BorderLayout.CENTER);
        this.pack();
    }

    private ImageIcon scale(Image src, double scale) {
        int w = (int) (scale * src.getWidth(this));
        int h = (int) (scale * src.getHeight(this));
        int type = BufferedImage.TRANSLUCENT;
        BufferedImage dst = new BufferedImage(w, h, type);
        Graphics2D g2 = dst.createGraphics();
        g2.drawImage(src, 0, 0, w, h, this);
        g2.dispose();
        g2.setBackground(Color.CYAN);
        return new ImageIcon(dst);
    }

//    private Icon scale(String string, int i) { //creo que no hace falta
//        throw new UnsupportedOperationException("Not yet implemented");
//    }

    public void colocarComponents() {
        this.setLayout(new BorderLayout());
        this.setLocation(200, 100);
        this.setMinimumSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        menu.add(itemMenuArxiu);
        itemMenuArxiu.add(itemMenuObrir);
        itemMenuArxiu.add(itemMenuReset);
        itemMenuArxiu.add(itemMenuSeparador1);
        itemMenuArxiu.add(itemMenuSortir);

        this.setJMenuBar(menu);
        //this.getContentPane().add(panel);

        this.setResizable(true);
        Finestra.initLookAndFeel();
        SwingUtilities.updateComponentTreeUI(this);

//        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.pack();
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void afegirListerners() {
        itemMenuObrir.addActionListener(this);
        itemMenuSortir.addActionListener(this);
        itemMenuReset.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == itemMenuObrir) {
            try {
                fitxer = ExaminarArxius.obrirFixer(this);
                if (fitxer != null) {
                    JOptionPane.showMessageDialog(this,
                            "El fitxer s'ha carregat correctament.",
                            "Informació",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                initTablero(fitxer); //Leemos el mapa
                dibujarElementos(taulell, files, columnes); //dibujamos el mapa
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        } else if (e.getSource() == itemMenuReset) {
            initTablero(fitxer);
            dibujarElementos(taulell, files, columnes);
        } else if (e.getSource() == itemMenuSortir) {
            System.exit(0);
        }
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

    /**
     *  Adapta l'aparenca del Java Swing al sistema operatiu
     */
    private static void initLookAndFeel() {
        String lookAndFeel = null;
        String osname = System.getProperty("os.name").toLowerCase();

        if (osname.equals("linux")) {
            lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
        } else if (osname.startsWith("windows")) {
            lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        } else {
            lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
        }

        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (ClassNotFoundException e) {
            e.toString();
        } catch (UnsupportedLookAndFeelException e) {
            e.toString();
        } catch (Exception e) {
            e.toString();
        }
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
     * @return the taulell
     */
    public Casella[][] getTaulell() {
        return taulell;
    }

    /**
     * @param taulell the taulell to set
     */
    public void setTaulell(Casella[][] taulell) {
        this.taulell = taulell;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //copiamos los valores anteriores al apretar tecla
        pos_temp_c = pos_jugador_c;
        pos_temp_f = pos_jugador_f;
        //-- Process arrow "virtual" keys
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (pos_jugador_c > 0) {
                    pos_jugador_c -= 1;
                }
                System.out.print("LEFT" + e.getKeyChar());
                break;
            case KeyEvent.VK_RIGHT:
                if (pos_jugador_c < columnes - 1) {
                    pos_jugador_c += 1;
                }
                System.out.print("RIGHT" + e.getKeyChar());
                break;
            case KeyEvent.VK_UP:
                if (pos_jugador_f > 0) {
                    pos_jugador_f -= 1;
                }
                System.out.print("UP" + e.getKeyChar());
                break;
            case KeyEvent.VK_DOWN:
                if (pos_jugador_f < files - 1) {
                    pos_jugador_f += 1;
                }
                System.out.print("DOWN" + e.getKeyChar());
                break;
        }
        //cada vez que cambiamos la posicion del jugador,
        //llamamos a la funcion Tractar_element()

        System.out.println("pos_jugador: " + pos_jugador_f + " " + pos_jugador_c);

        JugadorHuma jugador = new JugadorHuma(); //esto deberia ir en otro lado

        tab.tractar_casella(pos_jugador_f, pos_jugador_c, jugador);

        if (taulell[pos_jugador_f][pos_jugador_c] instanceof Paret) {
            //si es pared, volvemos a la posicion anterior
            pos_jugador_c = pos_temp_c;
            pos_jugador_f = pos_temp_f;
        }

        try {
            pintarCasilla(pos_jugador_f, pos_jugador_c);
        } catch (InterruptedException ex) {
            Logger.getLogger(Finestra.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void pintarCasilla(int x, int y) throws InterruptedException {
        Thread.sleep(1);
        if (taulell[pos_temp_f][pos_temp_c] instanceof Forat) {
            ImageIcon img = new ImageIcon("images/forat.png");
            imgMatriz[pos_temp_f][pos_temp_c] = new JLabel(scale(img.getImage(), tamany));
        } else {
            taulell[pos_temp_f][pos_temp_c] = new Cami();
//            imgMatriz[pos_temp_f][pos_temp_c].setOpaque(true);
//            imgMatriz[pos_temp_f][pos_temp_c].setBackground(Color.BLACK);
            ImageIcon img = new ImageIcon("images/arribada.gif");
            imgMatriz[pos_temp_f][pos_temp_c] = new JLabel(scale(img.getImage(), tamany));
            imgMatriz[pos_temp_f][pos_temp_c].repaint();
        } 
        imgMatriz[x][y].setOpaque(true);
        imgMatriz[x][y].setBackground(Color.ORANGE);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
