/*
 * Jugador.java
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
 * Clase abstracta que implementa el Jugador
 * @author vjuan
 */
public abstract class Jugador {

    protected Jugador jugador;
    protected int salut;
    protected int habilitat; // experiencia del jugador
    protected int x, y;
    protected int factor_salut = 0; // increment o decrement de la salut del jugador
    protected int factor_habilitat = 0; // increment o decrement de la habilitat del jugador

    /**
     *
     * @param val
     */
    public abstract void disminuirSalut(int val);
    /**
     *
     * @param val
     */
    public abstract void augmentarSalut(int val);
    /**
     *
     * @param val
     */
    public abstract void augmentarHabilitat(int val);
    /**
     *
     * @param val
     */
    public abstract void disminuirHabilitat(int val);
    /**
     *
     * @return
     */
    public abstract int getSalut();
    /**
     *
     * @param sal
     */
    public abstract void setSalut(int sal);
    /**
     *
     * @return
     */
    public abstract int getHabilitat();
    /**
     *
     * @param hab
     */
    public abstract void setHabilitat(int hab);
    /**
     *
     * @return
     */
    public abstract int getX();
    /**
     *
     * @param x
     */
    public abstract void setX(int x);
    /**
     *
     * @return
     */
    public abstract int getY();
    /**
     *
     * @param y
     */
    public abstract void setY(int y);
    /**
     *
     * @return
     */
    public abstract String getClasseJugador();
    /**
     *
     * @param jug
     */
    public abstract void setJugador(Jugador jug);
    /**
     *
     * @return
     */
    public abstract Jugador getJugador();
    /**
     *
     * @param factor_salut
     * @param factor_habilitat
     */
    public abstract void canviarComportament(int factor_salut, int factor_habilitat);
}
