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
import game.decorator.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
 * Classe que implementa sa finestra amb el taulell de joc
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
    private static JLabel info_salut, label_salut, info_hab, label_hab, info_estat, label_estat;
    private JProgressBar prog_salut, prog_hab;
    private double tamany = 0.65;
    private int pos_jugador_f, pos_temp_f;
    private int pos_jugador_c, pos_temp_c;
    private Tablero tab;
    private JPanel panelinfo;

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
        tab.initObjectes();
        tab.leerArchivo(fitxer); //leemos el archivo y guardamos el mapa en la variable taulell
        taulell = tab.getTaulell(); //cogemos la variable taulell

        //inicializamos las variables de posicion del jugador, y del tablero
        pos_jugador_f = tab.getJug().getX();
        pos_jugador_c = tab.getJug().getY();
        //System.out.println(pos_jugador_x+" "+pos_jugador_y);
        files = tab.getFiles();
        columnes = tab.getColumnes();
    }

    public void initComponents() {
        panel = new JPanel();
        panelinfo = new JPanel();

        menu = new JMenuBar();

        itemMenuArxiu = new JMenu("Joc");
        itemMenuObrir = new JMenuItem("Carregar mapa...");
        itemMenuReset = new JMenuItem("Recarregar mapa actual");
        itemMenuSeparador1 = new JSeparator();
        itemMenuSortir = new JMenuItem("Sortir");

        info_salut = new JLabel("Salut: ");
        info_hab = new JLabel(" Habilitat: ");
        info_estat = new JLabel(" Estat: " );
        setLabelEstatJugador(new JLabel(""));

        prog_salut = new JProgressBar(0, 100);
        prog_hab = new JProgressBar(0, 100);

        prog_salut.setValue(0);
        prog_salut.setStringPainted(true);
        prog_hab.setValue(0);
        prog_hab.setStringPainted(true);

        addKeyListener(this); //control eventos de teclado
    }

    public void dibujarElementos(Casella[][] taulell, int filas, int columnas) {
        panel.removeAll();
        panel.setLayout(new GridLayout(filas, columnas));
        panel.setBackground(Color.BLACK);// color del camino del laberinto

        tamany = tab.getTamany()/100;
//        System.out.println("tamany: "+tamany);
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

                } else if (taulell[f][c] instanceof Casella) {
                    ImageIcon img = new ImageIcon("images/composta2.png");
                    imgMatriz[f][c] = new JLabel(scale(img.getImage(), tamany));

                }
                panel.add(imgMatriz[f][c]);
            }
        }
        // Inici moviments dels enemics (threads)
        moureEnemics0 me0 = new moureEnemics0();
        me0.start();
        moureEnemics1 me1 = new moureEnemics1();
        me1.start();
        moureEnemics2 me2 = new moureEnemics2();
        me2.start();
        moureEnemics3 me3 = new moureEnemics3();
        me3.start();
        moureEnemics4 me4 = new moureEnemics4();
        me4.start();
        moureEnemics5 me5 = new moureEnemics5();
        me5.start();
        moureEnemics6 me6 = new moureEnemics6();
        me6.start();

        comprovar_valors cv = new comprovar_valors();
        cv.start();

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

    public void colocarComponents() {
        this.setLayout(new BorderLayout());
        this.setLocation(200, 100);
        this.setPreferredSize(new Dimension(705, 705));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        menu.add(itemMenuArxiu);
        itemMenuArxiu.add(itemMenuObrir);
        itemMenuArxiu.add(itemMenuReset);
        itemMenuArxiu.add(itemMenuSeparador1);
        itemMenuArxiu.add(itemMenuSortir);

        this.setJMenuBar(menu);
        this.getContentPane().add(panel);

        this.setResizable(false);

        panelinfo.setPreferredSize(new Dimension(this.getHeight(), 28)); //Panel horizontal de arriba para informacion

        panelinfo.add(info_salut);
        panelinfo.add(prog_salut);
        panelinfo.add(info_hab);
        panelinfo.add(prog_hab);
        panelinfo.add(info_estat);
        panelinfo.add(label_estat);
        
        this.add(panelinfo, BorderLayout.NORTH);

        Finestra.initLookAndFeel();
        SwingUtilities.updateComponentTreeUI(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        setVisible(true);
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
                    initTablero(fitxer); //Leemos el mapa
                    dibujarElementos(taulell, files, columnes); //dibujamos el mapa   
                } else {
                    JOptionPane.showMessageDialog(this,
                            "No s'ha pogut carregar el fitxer!",
                            "Alerta",
                            JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        } else if (e.getSource() == itemMenuReset) {
            if (fitxer != null) {
                initTablero(fitxer);
                dibujarElementos(taulell, files, columnes);
            } else {
                JOptionPane.showMessageDialog(this,
                        "No hi ha cap fitxer carregat!",
                        "Alerta",
                        JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == itemMenuSortir) {
            System.exit(0);
        }
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
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (pos_jugador_c > 0) {
                    pos_jugador_c -= 1;
                }
//                System.out.println("LEFT" + e.getKeyChar());
                break;
            case KeyEvent.VK_RIGHT:
                if (pos_jugador_c < columnes - 1) {
                    pos_jugador_c += 1;
                }
//                System.out.println("RIGHT" + e.getKeyChar());
                break;
            case KeyEvent.VK_UP:
                if (pos_jugador_f > 0) {
                    pos_jugador_f -= 1;
                }
//                System.out.println("UP" + e.getKeyChar());
                break;
            case KeyEvent.VK_DOWN:
                if (pos_jugador_f < files - 1) {
                    pos_jugador_f += 1;
                }
//                System.out.println("DOWN" + e.getKeyChar());
                break;
            default:
                break;
        }
        // cada vez que cambiamos la posicion del jugador,llamamos a la funcion Tractar_element()
        tab.tractar_casella(pos_jugador_f, pos_jugador_c);

        // actualizamos la info de los labels
        prog_salut.setValue(tab.getJug().getSalut());
        prog_salut.repaint();
        prog_hab.setValue(tab.getJug().getHabilitat());
        prog_hab.repaint();

        System.out.println("pos_jugador f:" + pos_jugador_f + " c:" + pos_jugador_c);

        // si es pared, volvemos a la posicion anterior
        if (taulell[pos_jugador_f][pos_jugador_c] instanceof Paret) {
            pos_jugador_c = pos_temp_c;
            pos_jugador_f = pos_temp_f;
        }

        // pintamos la casilla actual donde se encuentra el jugador
        try {
            pintarCasilla(pos_jugador_f, pos_jugador_c, e);
        } catch (InterruptedException ex) {
            Logger.getLogger(Finestra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pintarCasilla(int x, int y, KeyEvent e) throws InterruptedException {
        if (!(taulell[pos_temp_f][pos_temp_c] instanceof Forat) && !(taulell[pos_temp_f][pos_temp_c] instanceof Sortida) && !(taulell[pos_temp_f][pos_temp_c] instanceof Entrada)) {
            ImageIcon img = new ImageIcon("images/fondo.png");
            imgMatriz[pos_temp_f][pos_temp_c].setIcon(scale(img.getImage(), tamany));
            imgMatriz[pos_temp_f][pos_temp_c].repaint();
            this.pack();
            taulell[pos_temp_f][pos_temp_c] = new Cami(); //reseteamos la casilla Tirita, Pocima o Bomba
        } else if ((taulell[pos_temp_f][pos_temp_c] instanceof Forat)) {
            ImageIcon img = new ImageIcon("images/forat.png");
            imgMatriz[pos_temp_f][pos_temp_c].setIcon(scale(img.getImage(), tamany));
            imgMatriz[pos_temp_f][pos_temp_c].repaint();
        } else if ((taulell[pos_temp_f][pos_temp_c] instanceof Entrada)) {
            ImageIcon img = new ImageIcon("images/entrada.gif");
            imgMatriz[pos_temp_f][pos_temp_c].setIcon(scale(img.getImage(), tamany));
            imgMatriz[pos_temp_f][pos_temp_c].repaint();
        } else if ((taulell[pos_temp_f][pos_temp_c] instanceof Sortida)) {
            ImageIcon img = new ImageIcon("images/arribada.gif");
            imgMatriz[pos_temp_f][pos_temp_c].setIcon(scale(img.getImage(), tamany));
            imgMatriz[pos_temp_f][pos_temp_c].repaint();
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            ImageIcon img = new ImageIcon("images/marioD.png"); // dreta
            imgMatriz[x][y].setIcon(scale(img.getImage(), tamany));
            imgMatriz[x][y].repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            ImageIcon img = new ImageIcon("images/marioE.png"); // esquerra
            imgMatriz[x][y].setIcon(scale(img.getImage(), tamany));
            imgMatriz[x][y].repaint();
        } else {
            ImageIcon img = new ImageIcon("images/mario1.png"); // amunt i avall
            imgMatriz[x][y].setIcon(scale(img.getImage(), tamany));
            imgMatriz[x][y].repaint();
        }
        this.pack();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the salut
     */
    public static JLabel getLabelSalut() {
        return label_salut;
    }

    /**
     * @param aSalut the salut to set
     */
    public static void setLabelSalut(JLabel aSalut) {
        label_salut = aSalut;
    }

    /**
     * @return the hab
     */
    public static JLabel getLabelHab() {
        return label_hab;
    }

    /**
     * @param aHab the hab to set
     */
    public static void setLabelHab(JLabel aHab) {
        label_hab = aHab;
    }

    /**
     * @return the estatJugador
     */
    public static JLabel getLabelEstatJugador() {
        return label_estat;
    }

    /**
     * @param aEstatJugador the estatJugador to set
     */
    public static void setLabelEstatJugador(JLabel aEstatJugador) {
        label_estat = aEstatJugador;
    }

    /**
     * @return the prog_salut
     */
    public JProgressBar getProg_salut() {
        return prog_salut;
    }

    /**
     * @return the prog_hab
     */
    public JProgressBar getProg_hab() {
        return prog_hab;
    }
    
    private class moureEnemics0 extends Thread {

        private ArrayList<Enemic> enemics;

        public moureEnemics0() {
            super();
        }

        @Override
        public void run() {
            while (true) {
                enemics = tab.getEnemics();
                try {
                    pintarRecorrido(getEnemic(enemics, 0).getRecorregut());
                } catch (InterruptedException ex) {
                    Logger.getLogger(Finestra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private class moureEnemics1 extends Thread {

        private ArrayList<Enemic> enemics;

        public moureEnemics1() {
            super();
        }

        @Override
        public void run() {
            while (true) {
                enemics = tab.getEnemics();
                try {
                    pintarRecorrido(getEnemic(enemics, 1).getRecorregut());
                } catch (InterruptedException ex) {
                    Logger.getLogger(Finestra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private class moureEnemics2 extends Thread {

        private ArrayList<Enemic> enemics;

        public moureEnemics2() {
            super();
        }

        @Override
        public void run() {
            while (true) {
                enemics = tab.getEnemics();
                try {
                    pintarRecorrido(getEnemic(enemics, 2).getRecorregut());
                } catch (InterruptedException ex) {
                    Logger.getLogger(Finestra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private class moureEnemics3 extends Thread {

        private ArrayList<Enemic> enemics;

        public moureEnemics3() {
            super();
        }

        @Override
        public void run() {
            while (true) {
                enemics = tab.getEnemics();
                try {
                    pintarRecorrido(getEnemic(enemics, 3).getRecorregut());
                } catch (InterruptedException ex) {
                    Logger.getLogger(Finestra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private class moureEnemics4 extends Thread {

        private ArrayList<Enemic> enemics;

        public moureEnemics4() {
            super();
        }

        @Override
        public void run() {
            while (true) {
                enemics = tab.getEnemics();
                try {
                    pintarRecorrido(getEnemic(enemics, 4).getRecorregut());
                } catch (InterruptedException ex) {
                    Logger.getLogger(Finestra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private class moureEnemics5 extends Thread {

        private ArrayList<Enemic> enemics;

        public moureEnemics5() {
            super();
        }

        @Override
        public void run() {
            while (true) {
                enemics = tab.getEnemics();
                try {
                    pintarRecorrido(getEnemic(enemics, 5).getRecorregut());
                } catch (InterruptedException ex) {
                    Logger.getLogger(Finestra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private class moureEnemics6 extends Thread {

        private ArrayList<Enemic> enemics;

        public moureEnemics6() {
            super();
        }

        @Override
        public void run() {
            while (true) {
                enemics = tab.getEnemics();
                try {
                    pintarRecorrido(getEnemic(enemics, 6).getRecorregut());
                } catch (InterruptedException ex) {
                    Logger.getLogger(Finestra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void hasGuanyat() {
        String msg = "<html><b>G A M E   O V E R !!! xDDDDDD</b></html>";
        JLabel label = new JLabel(msg);
        label.setFont(new Font("serif", Font.PLAIN, 14));
        JOptionPane.showMessageDialog(null, label, "Fi de partida", JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }

    private class comprovar_valors extends Thread {

        public comprovar_valors() {
            super();
        }

        @Override
        public void run() {
            while (true) {
                prog_salut.setValue(tab.getJug().getSalut());
                prog_hab.setValue(tab.getJug().getHabilitat());
                label_estat.setText(tab.getJug().getClasseJugador());
                
                if (tab.getJug().getClasseJugador().equals("MiniJugador")) {
                    System.out.println("mini");
                    System.out.println("salut " +Integer.valueOf(tab.getJug().getSalut()));
                    System.out.println("habilitat " +Integer.valueOf(tab.getJug().getHabilitat()));
                    if (tab.getJug().getHabilitat() >= 40) {
                        JugadorNormal jn = new JugadorNormal();
                        jn.setSalut(tab.getJug().getSalut());
                        jn.setHabilitat(tab.getJug().getHabilitat());

                        // millora del jugador (de MiniJugador a JugadorNormal)
                        jn.augmentarHabilitat(20);
                        jn.canviarComportament(0, 0);

                        System.out.println("abans getClasseJugador: " + tab.getJug().getClasseJugador());
                        tab.setJug(jn);
                        System.out.println("despres getClasseJugador: " + tab.getJug().getClasseJugador());
                    }
                } else if (tab.getJug().getClasseJugador().equals("JugadorNormal")) {
                    System.out.println("normal");
                    System.out.println("salut " +Integer.valueOf(tab.getJug().getSalut()));
                    System.out.println("habilitat " +Integer.valueOf(tab.getJug().getHabilitat()));
                    if (tab.getJug().getHabilitat() <= 20) {
                        MiniJugador mj = new MiniJugador();
                        mj.setSalut(tab.getJug().getSalut());
                        mj.setHabilitat(tab.getJug().getHabilitat());

                        // el jugador empitjora (de JugadorNormal a MiniJugador)
                        mj.disminuirHabilitat(5);
                        mj.canviarComportament(4, 2);

                        System.out.println("abans getClasseJugador: " + tab.getJug().getClasseJugador());
                        tab.setJug(mj);
                        System.out.println("despres getClasseJugador: " + tab.getJug().getClasseJugador());
                    }                    
                }

                if (tab.getJug().getHabilitat() >= 100) {
                    tab.getJug().setHabilitat(100);
                    prog_hab.setValue(100);
                }

                if (tab.getJug().getHabilitat() < 1) {
                    tab.getJug().setHabilitat(0);
                    prog_hab.setValue(0);
                }
                
                if (tab.getJug().getSalut() >= 100) {
                    tab.getJug().setSalut(100);
                    prog_salut.setValue(100);
                }

                if (tab.getJug().getSalut() < 1) {
                    prog_salut.setValue(0);
                    hasGuanyat();
                }
                
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Finestra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public Enemic getEnemic(ArrayList<Enemic> enemics, int i) {
        return enemics.get(i);
    }

    public boolean comprobar_choque(int pos_jugador_f, int pos_jugador_c, int i, int j) {
        if (pos_jugador_f == i && pos_jugador_c == j) {
            return true;
        } else {
            return false;
        }
    }

    // pintar recorregut dels enemics
    public void pintarRecorrido(ArrayList<Casella> r) throws InterruptedException {
        // per anar endavant
        for (int i = 2; i < r.size(); i++) {
            // Comprobamos si el jugador se topa con el enemigo
            if (comprobar_choque(pos_jugador_f,pos_jugador_c,r.get(i).getX(),r.get(i).getY())){
                tab.getJug().disminuirSalut(25);
                tab.getJug().disminuirHabilitat(5);
                System.out.println("colision");
            }
            
            ImageIcon img = new ImageIcon("images/enemic.png");
            imgMatriz[r.get(i).getX()][r.get(i).getY()].setIcon(scale(img.getImage(), tamany));
            imgMatriz[r.get(i).getX()][r.get(i).getY()].repaint();

            ImageIcon img2 = new ImageIcon("images/fondo.png");
            imgMatriz[r.get(i - 1).getX()][r.get(i - 1).getY()].setIcon(scale(img2.getImage(), tamany));
            imgMatriz[r.get(i - 1).getX()][r.get(i - 1).getY()].repaint();

            Thread.sleep(500);
        }
        // per tornar enrrera
        for (int i = r.size() - 2; i > 0; i--) {
//            System.out.println("size " + r.size() + " i: " + i);
            if (comprobar_choque(pos_jugador_f,pos_jugador_c,r.get(i).getX(),r.get(i).getY())){
                tab.getJug().disminuirSalut(25);
                System.out.println("colision");
            }
            ImageIcon img = new ImageIcon("images/enemic.png");
            imgMatriz[r.get(i).getX()][r.get(i).getY()].setIcon(scale(img.getImage(), tamany));
            imgMatriz[r.get(i).getX()][r.get(i).getY()].repaint();

            ImageIcon img2 = new ImageIcon("images/fondo.png");
            imgMatriz[r.get(i + 1).getX()][r.get(i + 1).getY()].setIcon(scale(img2.getImage(), tamany));
            imgMatriz[r.get(i + 1).getX()][r.get(i + 1).getY()].repaint();

            Thread.sleep(500);
        }
    }

}
