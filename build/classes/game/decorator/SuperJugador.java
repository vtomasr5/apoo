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

    public SuperJugador() {}

    public SuperJugador(int factor_salut, int factor_habilitat) {
        this.factor_salut = factor_salut;
        this.factor_habilitat = factor_habilitat;
    }

    public SuperJugador(Jugador jug, int factor_salut, int factor_habilitat) {
        this.jugador = jug;
        this.factor_salut = factor_salut;
        this.factor_habilitat = factor_habilitat;
    }

    @Override
    public void disminuirSalut(int val) {
        setSalut(getSalut() - (val - getFactor_salut()));
    }

    @Override
    public void augmentarSalut(int val) {
        setSalut(getSalut() + (val + getFactor_salut()));
    }

    @Override
    public void augmentarHabilitat(int val) {
        setHabilitat(getHabilitat() + (val + getFactor_habilitat()));
    }

    @Override
    public void disminuirHabilitat(int val) {
        setHabilitat(getHabilitat() - (val - getFactor_habilitat()));
    }

    @Override
    public String getClasseJugador() {
        return this.getClass().getSimpleName();
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

    /**
     * @return the factor_salut
     */
    public int getFactor_salut() {
        return factor_salut;
    }

    /**
     * @param factor_salut the factor_salut to set
     */
    public void setFactor_salut(int factor_salut) {
        this.factor_salut = factor_salut;
    }

    /**
     * @return the factor_habilitat
     */
    public int getFactor_habilitat() {
        return factor_habilitat;
    }

    /**
     * @param factor_habilitat the factor_habilitat to set
     */
    public void setFactor_habilitat(int factor_habilitat) {
        this.factor_habilitat = factor_habilitat;
    }

    @Override
    public void canviarComportament(int factor_salut, int factor_habilitat) {
        this.setFactor_salut(factor_salut);
        this.setFactor_habilitat(factor_habilitat);
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
