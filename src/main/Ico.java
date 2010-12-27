/*
 * Ico.java
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

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author vjuan
 */
public class Ico extends JLabel {

    /**
     * 
     * @param nombre
     */
    public Ico(String nombre) {
        super(new ImageIcon("images/" + nombre));
    }

}
