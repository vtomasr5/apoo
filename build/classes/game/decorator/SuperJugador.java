/*
 * SuperJugador.java
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
 * Clase que implementa el Super Jugador
 * @author vjuan
 */
public class SuperJugador extends JugadorHuma {
    
    private Jugador jugador;

    public SuperJugador(JugadorHuma jugador) {
        this.jugador = jugador;
    }

    @Override
    public boolean play() {
        System.out.println("Super Jugador");
        jugador.play();
        return true;
    }

//    @Override
//    public void disminuirSalut(int val) {
//        int val2 = val/2; // Sa salut d'es super jugador se redueix lentament
////        System.out.println("Salut reduida " + (val2) + " punts");
//        jugador.disminuirSalut(val2);
//    }

    public Jugador getPlayer() {
        return jugador;
    }

}
