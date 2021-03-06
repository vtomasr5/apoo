/*
 * Decorador.java
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
 * Clase abstracta que implementa el Decorador
 * @author vjuan
 */
public abstract class Decorador extends Jugador {

    /**
     * Constructor
     */
    public Decorador() {}

    /**
     *
     * @param jug
     */
    public Decorador(Jugador jug) {
        this.jugador = jug;
    }
}
