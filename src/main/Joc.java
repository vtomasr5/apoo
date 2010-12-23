/*
 * Joc.java
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

import game.decorator.Enemic;
import game.decorator.Jugador;
import java.util.ArrayList;

/**
 * Clase que implementa el Joc
 * @author vjuan
 */
public class Joc {
    
    private Jugador jugador;
    private ArrayList <Enemic> bots;

    public Joc() {
        bots = new ArrayList();
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void afegirJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public ArrayList <Enemic> getBots() {
        return bots;
    }

    public void setBots(ArrayList <Enemic> bots) {
        this.bots = bots;
    }

    public void afegirBot(Enemic bot) {
        bots.add(bot);
    }

}