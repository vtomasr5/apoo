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

import game.composite.Casella;
import java.util.ArrayList;

/**
 * Clase que implementa l'Enemic del Jugador
 * @author vjuan
 */
public class Enemic extends Jugador {

    private ArrayList<Casella> recorregut;

    public Enemic(ArrayList<Casella> recorregut) {
        setRecorregut(recorregut);
    }

//    @Override
//    public void play() {
//        System.out.println("Enemic està a punt per jugar");
//    }

    @Override
    public int getHabilitat() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setHabilitat(int habilitat) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getSalut() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSalut(int salut) {
        throw new UnsupportedOperationException("Not supported yet.");
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

    /**
     * @return the recorregut
     */
    public ArrayList<Casella> getRecorregut() {
        return recorregut;
    }

    /**
     * @param recorregut the recorregut to set
     */
    private void setRecorregut(ArrayList<Casella> recorregut) {
        this.recorregut = recorregut;
    }

    @Override
    public String getClasseJugador() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void setJugador(Jugador jug) {
        this.jugador = jug;
    }

    @Override
    public Jugador getJugador() {
        return this.jugador;
    }
    
}
