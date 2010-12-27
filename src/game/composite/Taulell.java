/*
 * Taulell.java
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

/**
 *
 * @author vjuan
 */
abstract public class Taulell {

    abstract public void afegir(Casella obj);
    abstract public void eliminar(int i);
    abstract public int getX();
    abstract public void setX(int x);
    abstract public int getY();
    abstract public void setY(int y);
}
