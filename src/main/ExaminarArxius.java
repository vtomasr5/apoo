/*
 * ExaminarArxius.java
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

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Classe que serveix per a obrir i guardar els fitxers de rutes al sistema
 * de fitxers del SO
 * @author vjuan
 */
public class ExaminarArxius {

    private static JFileChooser eleccioFitxer;

    /**
     * S'obri un examinador de fitxers per elegir quin carregar el programa
     * @param finestra
     * @return retorna el fitxer elegit
     */
    public static File obrirFixer(JFrame finestra) {
        eleccioFitxer = new JFileChooser();
        eleccioFitxer.setFileFilter(new FileNameExtensionFilter("Només " + "mapes (*.txt)", "txt"));
        eleccioFitxer.setDialogTitle("Elegeix el mapa");
        int ret = eleccioFitxer.showOpenDialog(finestra);

        if (ret == JFileChooser.APPROVE_OPTION){
            return eleccioFitxer.getSelectedFile();
        } else {
            return null;
        }
    }

}
