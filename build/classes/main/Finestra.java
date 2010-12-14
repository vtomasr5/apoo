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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author vjuan
 */
public class Finestra extends JFrame implements ActionListener {

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

    public static void initTablero(File fitxer) {
        Tablero tab = new Tablero();
        tab.leerArchivo(fitxer);
    }

    public void initComponents() {
        panel = new Panell();
        menu = new JMenuBar();

        itemMenuArxiu = new JMenu("Joc");
        itemMenuObrir = new JMenuItem("Carregar mapa...");
        itemMenuSeparador1 = new JSeparator();
        itemMenuSortir = new JMenuItem("Sortir");
    }

    public void colocarComponents() {
        this.setLayout(new BorderLayout());
        this.setLocation(200, 100);
        this.setMinimumSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setJMenuBar(menu);
        menu.add(itemMenuArxiu);
        itemMenuArxiu.add(itemMenuObrir);
        itemMenuArxiu.add(itemMenuSortir);

        this.getContentPane().add(panel);

        this.setResizable(true);
        Finestra.initLookAndFeel();
        SwingUtilities.updateComponentTreeUI(this);
        
        this.pack();
        this.setVisible(true);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void afegirListerners() {
        itemMenuObrir.addActionListener(this);
        itemMenuSortir.addActionListener(this);
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
                initTablero(fitxer);
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
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
    
    public void comprobar_taulell(Casella[][] taulell){
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
        int r = (int) Math.floor(Math.random()*3);
        int j;
        ArrayList<Casella> array = new ArrayList<Casella>();

        for (int i = 0; i < r; i++) {
             j = (int) Math.floor(Math.random()*3);
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

}

