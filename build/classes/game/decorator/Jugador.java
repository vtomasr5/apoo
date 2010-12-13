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
    
    private int salut;
    private int habilitat; // experiencia del jugador

    public abstract boolean play();
    //public abstract void disminuirSalut(int val);
    //public abstract void augmentarSalut(int val);

    public int getSalut() {
        return salut;
    }

    public void setSalut(int salut) {
        this.salut = salut;
    }

    /**
     * @return the habilitat
     */
    public int getHabilitat() {
        return habilitat;
    }

    /**
     * @param habilitat the habilitat to set
     */
    public void setHabilitat(int habilitat) {
        this.habilitat = habilitat;
    }
}
