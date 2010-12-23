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
public class SuperJugador extends Decorador {

    public void canviarComportament(Jugador jug, int nivell_reduccio_salut, int nivell_reduccio_habilitat) {
        
    }

    @Override
    public void disminuirSalut(int val) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void augmentarSalut(int val) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void augmentarHabilitat(int val) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void disminuirHabilitat(int val) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getClasseJugador(Jugador jug) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void play() {
        System.out.println("Enemic (Amb: "+ getHabilitat() + " punts d'habilitat) està a punt per jugar");
        // Implementació de com juga s'Enemic
    }

    @Override
    public int getHabilitat() {
        return habilitat;
    }

    @Override
    public void setHabilitat(int habilitat) {
        this.habilitat = habilitat;
    }

    @Override
    public int getSalut() {
        return salut;
    }

    @Override
    public void setSalut(int salut) {
        this.salut = salut;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

}
