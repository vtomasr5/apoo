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
    
    protected int salut;
    protected int habilitat; // experiencia del jugador
    protected int x, y;

    public abstract void play(); // tal vegada no faci falta
    public abstract void disminuirSalut(int val);
    public abstract void augmentarSalut(int val);
    public abstract void augmentarHabilitat(int val);
    public abstract void disminuirHabilitat(int val);
    public abstract String getClasseJugador(Jugador jug);
    public abstract int getSalut();
    public abstract void setSalut(int sal);
    public abstract int getHabilitat();
    public abstract void setHabilitat(int hab);
    public abstract int getX();
    public abstract void setX(int x);
    public abstract int getY();
    public abstract void setY(int y);

}
