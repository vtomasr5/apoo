/*
 * Enemic.java
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
 * Clase que implementa l'Enemic del Jugador
 * @author vjuan
 */
public class Enemic extends Jugador {

    private int habilitat;

    @Override
    public boolean play() {
        System.out.println("Enemic (Amb: "+ getHabilitat() + " punts d'habilitat) està a punt per jugar");
        // Implementació de com juga s'Enemic
        return true;
    }

    @Override
    public int getHabilitat() {
        return habilitat;
    }

    @Override
    public void setHabilitat(int habilitat) {
        this.habilitat = habilitat;
    }

//    @Override
//    public void disminuirSalut(int val) {
//        setSalut(getSalut() - (val - 1));
//        System.out.println("Salut reduida " + val + " punts");
//    }

//    public boolean atacarJugador(Jugador p, int salut) {
//        p.disminuirSalut(salut);
//        return true;
//    }

//    @Override
//    public void augmentarSalut(int val) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
}
