/*
 * Panell.java
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
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 *
 * @author vjuan
 */
public class Panell extends JPanel implements ActionListener, KeyListener {

    private Casella[][] taulell;
    protected JLabel[][] imgMatriz;
    private int tamany;

    public Panell() {}

    public void initPanell(Casella[][] taulell, int filas, int columnas) {
        dibujarElementos(taulell, filas, columnas);
    }

    public void dibujarElementos(Casella[][] taulell, int filas, int columnas) {
//        panel = new JPanel(new GridLayout(filas, columnas));
        this.removeAll();
        this.setLayout(new GridLayout(filas, columnas));
        this.setBackground(Color.BLACK);// color del camino del laberinto

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
                    ImageIcon img = new ImageIcon("images/Tele.gif");
                    imgMatriz[f][c] = new JLabel(scale(img.getImage(), tamany));
                } else if (taulell[f][c] instanceof Sortida) {
                    ImageIcon img = new ImageIcon("images/arribada.gif");
                    imgMatriz[f][c] = new JLabel(scale(img.getImage(), tamany));
                } else if (taulell[f][c] instanceof Tirita) {
                    ImageIcon img = new ImageIcon("images/tresor.png");
                    imgMatriz[f][c] = new JLabel(scale(img.getImage(), tamany));
                } else if (taulell[f][c] instanceof Pocima) {
                    ImageIcon img = new ImageIcon("images/tunel.gif");
                    imgMatriz[f][c] = new JLabel(scale(img.getImage(), tamany));
                }
                this.add(imgMatriz[f][c]);
            }
        }
        //this.pack();
        this.repaint();
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
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
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

}
