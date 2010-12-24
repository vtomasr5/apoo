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
 *
 * @author vjuan
 */
public class JugadorHuma extends Jugador {

    @Override
    public void disminuirSalut(int val) {
        setSalut(getSalut() - val);
    }

    @Override
    public void augmentarSalut(int val) {
        setSalut(getSalut() + val);
    }

    @Override
    public void augmentarHabilitat(int val) {
        setHabilitat(getHabilitat() + val);
    }

    @Override
    public void disminuirHabilitat(int val) {
        setHabilitat(getHabilitat() - val);
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

    @Override
    public String getClasseJugador() {
        return this.getClass().getSimpleName();
    }

//    @Override
//    public void setJugador(Jugador jug) {
//        this.jugador = jug;
//    }
//
//    @Override
//    public Jugador getJugador() {
//        return this.jugador;
//    }

}
