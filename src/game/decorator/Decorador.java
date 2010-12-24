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

    protected Jugador jugador;
    protected int factor_salut = 0; // increment o decrement de la salut del jugador
    protected int factor_habilitat = 0; // increment o decrement de la habilitat del jugador

    public abstract void canviarComportament(int factor_salut, int factor_habilitat);
    public abstract void setJugador(Jugador jug);
    public abstract Jugador getJugador();
}
