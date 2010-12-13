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
import javax.swing.*;

/**
 *
 * @author vjuan
 */
public class Finestra extends JFrame implements KeyListener, ActionListener {

    private int files;
    private int columnes;
    private Casella[][] taulell;
    private JPanel panel;
    public JLabel[][] imgMatriz;
    private int tamany;
    
    private JMenuBar menu; // barra de menu
    private static File fitxer;

    private JMenu itemMenuArxiu;
    private JMenuItem itemMenuObrir;
    private JSeparator itemMenuSeparador1;
    private JMenuItem itemMenuSortir;

    public Finestra() {}

    public void pintarFinestra() {
        new JFrame("Super Mario Bros");
        this.initComponents();
        this.colocarComponents();
        this.afegirListerners();
    }

    public void initComponents() {
        panel = new JPanel();
        menu = new JMenuBar();

        itemMenuArxiu = new JMenu("Arxiu");
        itemMenuObrir = new JMenuItem("Obrir...");
        itemMenuSeparador1 = new JSeparator();
        itemMenuSortir = new JMenuItem("Sortir");
    }

    public void colocarComponents() {
        this.setLayout(new BorderLayout());
        this.setLocation(200, 100);
        this.setMinimumSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setJMenuBar(menu);

        this.setResizable(false);
        initLookAndFeel();
        SwingUtilities.updateComponentTreeUI(this);
        
        this.pack();
        this.setVisible(true);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void afegirListerners() {
        itemMenuArxiu.addActionListener(this);
        itemMenuSortir.addActionListener(this);
    }

    public void dibujarElementos(Casella[][] laberinto, int filas, int columnas) {

//        panel = new JPanel(new GridLayout(filas, columnas));
        panel.removeAll();
        panel.setLayout(new GridLayout(filas, columnas));
        panel.setBackground(Color.BLACK);// color del camino del laberinto

        imgMatriz = new JLabel[filas][columnas];
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                if (laberinto[f][c] instanceof Entrada) {
                    ImageIcon img = new ImageIcon("images/entrada.gif");
                    imgMatriz[f][c] = new JLabel(scale(img.getImage(), tamany));
                } else if (laberinto[f][c] instanceof Paret) {
                    ImageIcon img = new ImageIcon("images/paret.gif");
                    imgMatriz[f][c] = new JLabel(scale(img.getImage(), tamany));
                } else if (laberinto[f][c] instanceof Cami) {
                    imgMatriz[f][c] = new JLabel();
                } else if (laberinto[f][c] instanceof Forat) {
                    ImageIcon img = new ImageIcon("images/forat.png");
                    imgMatriz[f][c] = new JLabel(scale(img.getImage(), tamany));
                } else if (laberinto[f][c] instanceof Bomba) {
                    ImageIcon img = new ImageIcon("images/Tele.gif");
                    imgMatriz[f][c] = new JLabel(scale(img.getImage(), tamany));
                } else if (laberinto[f][c] instanceof Sortida) {
                    ImageIcon img = new ImageIcon("images/arribada.gif");
                    imgMatriz[f][c] = new JLabel(scale(img.getImage(), tamany));
                } else if (laberinto[f][c] instanceof Tirita) {
                    ImageIcon img = new ImageIcon("images/tresor.png");
                    imgMatriz[f][c] = new JLabel(scale(img.getImage(), tamany));
                } else if (laberinto[f][c] instanceof Pocima) {
                    ImageIcon img = new ImageIcon("images/tunel.gif");
                    imgMatriz[f][c] = new JLabel(scale(img.getImage(), tamany));
                }
                panel.add(imgMatriz[f][c]);
            }
        }
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

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == itemMenuObrir) {
            try {
                fitxer = ExaminarArxius.obrirFixer(this);
                if (fitxer != null) {
                    JOptionPane.showMessageDialog(this,
                        "El fitxer s'ha carregat correctament",
                        "Informació",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        } else if (e.getSource() == itemMenuSortir) {
            System.exit(0);
        }
    }

    /**
     * Adecua l'aparenca del Java Swing al sistema operatiu
     */
    private static void initLookAndFeel() {
        String lookAndFeel = null;
        String osname = System.getProperty("os.name").toLowerCase();

        if (osname.equals("linux"))
            lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
        else if (osname.startsWith("windows"))
            lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        else
            lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();

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
}
