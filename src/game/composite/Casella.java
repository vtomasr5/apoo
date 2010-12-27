/*
 * Casella.java
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

package game.composite;

import java.util.ArrayList;

/**
 *
 * @author vjuan
 */
public class Casella extends Taulell {

    private int x;
    private int y;
    private ArrayList<Casella> objectes;

    /**
     * Constructor
     */
    public Casella() {}

    /**
     *
     * @param x
     * @param y
     * @param obj
     */
    public Casella(int x, int y, Object obj) { // casella composta
        this.x = x;
        this.y = y;
        this.objectes = (ArrayList<Casella>) obj;
    }


    /**
     *
     * @param x
     * @param y
     */
    public Casella(int x, int y) {
        this.x = x;
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
     * @return the objectes
     */
    public ArrayList<Casella> getObjectes() {
        return objectes;
    }

    /**
     * @param objectes the objectes to set
     */
    public void setObjectes(ArrayList<Casella> objectes) {
        this.objectes = objectes;
    }

    /**
     *
     * @param obj
     */
    @Override
    public void afegir(Casella obj) {
        getObjectes().add(obj);
    }

    /**
     *
     * @param i
     */
    @Override
    public void eliminar(int i) {
        getObjectes().remove(i);
    }

}