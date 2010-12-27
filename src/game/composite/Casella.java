/*
 * Casella.java
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

package game.composite;

import java.util.ArrayList;

/**
 *
 * @author vjuan
 */
public class Casella extends Taulell {

    private String nom;
    private int x;
    private int y;
    private int valor;
    private ArrayList<Casella> objectes;

    /**
     * Constructor
     */
    public Casella() {}

    /**
     *
     * @param x
     * @param y
     * @param valor
     * @param obj
     */
    public Casella(int x, int y, int valor, Object obj) { // casella composta
        this.x = x;
        this.y = y;
        this.valor = valor;
        this.objectes = (ArrayList<Casella>) obj;
    }

    /**
     *
     * @param x
     * @param y
     * @param valor
     */
    public Casella(int x, int y, int valor) { // casella simple
        this.x = x;
        this.y = y;
        this.valor = valor;
    }

    /**
     *
     * @param x
     * @param y
     */
    public Casella(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    @Override
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    @Override
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the valor
     */
    @Override
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    @Override
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * @return the objectes
     */
    public ArrayList<Casella> getObjectes() {
        return objectes;
    }

    /**
     * @param objectes the objectes to set
     */
    public void setObjectes(ArrayList<Casella> objectes) {
        this.objectes = objectes;
    }

    /**
     *
     * @param obj
     */
    @Override
    public void afegir(Casella obj) {
        getObjectes().add(obj);
    }

    /**
     *
     * @param i
     */
    @Override
    public void eliminar(int i) {
        getObjectes().remove(i);
    }

    /**
     *
     * @return
     */
    @Override
    public String getNom() {
        return this.nom;
    }

    /**
     *
     * @param nom
     */
    @Override
    public void setNom(String nom) {
        this.nom = nom;
    }

}