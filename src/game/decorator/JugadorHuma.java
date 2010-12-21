/*
 * JugadorHuma.java
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

package game.decorator;

/**
 * Clase que implementa el Jugador Humà
 * @author vjuan
 */
public class JugadorHuma extends Jugador {

//    public int x, y;

    @Override
    public boolean play() {
        System.out.println("Jugador (Amb: "+ getHabilitat() + " punts d'habilitat) està a punt per jugar");
        // Implementació de com juga es JugadorHuma
        return true;
    }

    @Override
    public int getSalut() {
        return salut;
    }

    @Override
    public void setSalut(int salut) {
        this.salut = salut;
    }

    /**
     * @return the y
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    @Override
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the x
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    @Override
    public void setX(int x) {
        this.x = x;
    }

//    @Override
//    public void disminuirSalut(int val) {
//        setSalut(getSalut() - val);
//        System.out.println("Salut reduida " + val + " punts");
//    }
//
//    @Override
//    public void augmentarSalut(int val) {
//        setSalut(getSalut() + val);
//        System.out.println("Salut augmentada " + val + " punts");
//    }
}
