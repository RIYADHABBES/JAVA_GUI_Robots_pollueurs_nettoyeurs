import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
public abstract class Robot {
    private int m_posX; /// La position X du robot
    private int m_posY; /// La position Y du robot
    private int m_xprev[] = { m_posX, 0 };
    private int m_yprev[] = { m_posY, 0 };
    private String m_nom;
    private JLabel m_robotImg;
    public static Monde m_monde; /// Le monde ou le robot evolu
    public static DessineTerrain m_terrain;

    /// /!\ Attention monde n'est pas initialiser; je vais faire la décision le
    /// moment ou je décide comment créer le monde
    public JLabel getRobotImg() {
        return m_robotImg;
    }

    public void setRobotImg(String imageNom) {
        try {
            BufferedImage playerImg;
            playerImg = ImageIO.read(getClass().getResource(imageNom));
            Image tmp = playerImg.getScaledInstance(m_terrain.getUnityWidth(), m_terrain.getUnityHight(),
                    Image.SCALE_SMOOTH);
            setRobotImg(new JLabel(new ImageIcon(tmp)));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void setRobotImg(JLabel robotImgLabel) {
        m_robotImg = robotImgLabel;
    }

    public String getNomRobot() {
        return m_nom;
    }

    public void setNomRobot(String nomRobot) {
        m_nom = nomRobot;
    }

    public int[] getXprev() {
        return m_xprev;
    }

    public int[] getYprev() {
        return m_yprev;
    }

    public int getPosX() {
        return m_posX;
    }

    public int getPosY() {
        return m_posY;
    }

    public void setPosX(int posX) {
        m_posX = posX;
    }

    public void setPosY(int posY) {
        m_posY = posY;
    }

    public Robot(Monde monde, DessineTerrain terrain) {

        m_monde = monde;
        m_terrain = terrain;
    }

    public Robot(int x_start, int y_start) {
        /*
         * while (m_monde.estTuOccupeParRobot(x_start, y_start)) {
         * x_start = donneMoiPosXAleatoire();
         * y_start = donneMoiPosYAleatoire();
         * }
         */
        m_posX = x_start;
        m_posY = y_start;
    }

    public Robot(int x_start, int y_start, Monde monde) {
        /*
         * while (m_monde.estTuOccupeParRobot(x_start, y_start)) {
         * x_start = donneMoiPosXAleatoire();
         * y_start = donneMoiPosYAleatoire();
         * }
         */
        m_posX = x_start;
        m_posY = y_start;
        m_monde = monde;
    }

    public Robot() {
        try {
            int i_start;
            int j_start;
            int maxI = m_monde.getLignes() - 1;
            int minI = 0;
            int maxJ = m_monde.getColonnes() - 1;
            int minJ = 0;
            do {
                i_start = (int) (Math.random() * (maxI - minI + 1) + minI);
                j_start = (int) (Math.random() * (maxJ - minJ + 1) + minJ);

            } while (i_start > m_monde.getLignes() - 1 || j_start > m_monde.getColonnes());
            // || m_monde.estTuOccupeParRobot(i_start, j_start));

            m_posX = i_start;
            m_posY = j_start;

        } catch (Exception e) {
            m_posX = 0;
            m_posY = 0;
        }
    }

    /**
     * @brief : poseMoiAleatoirement() : Une méthde qui pose le robot aléatoirement
     *        sur la grille
     * 
     * @return : void
     */
    public void poseMoiAleatoirement() {
        try {
            int i_rand;
            int j_rand;
            int maxI = m_monde.getLignes() - 1;
            int minI = 0;
            int maxJ = m_monde.getColonnes() - 1;
            int minJ = 0;
            do {
                i_rand = (int) (Math.random() * (maxI - minI + 1) + minI);
                j_rand = (int) (Math.random() * (maxJ - minJ + 1) + minJ);

            } while (i_rand > m_monde.getLignes() - 1 || j_rand > m_monde.getColonnes());// ||
                                                                                         // m_monde.estTuOccupeParRobot(i_rand,
                                                                                         // j_rand));

            m_posX = i_rand;
            m_posY = j_rand;
        } catch (Exception e) {
            m_posX = 0;
            m_posY = 0;
        }
    }

    abstract public void jeBouge();

    /*
     * public void jeBouge(int i_go, int j_go) {
     * try {
     * if (i_go < m_monde.getLignes() && i_go >= 0 && j_go < m_monde.getColonnes()
     * && j_go >= 0) {// &&
     * // !m_monde.estTuOccupeParRobot(i_go,
     * // j_go)) {
     * /** /!\ peutere j'ajoute aussi le cas ou c'est ça position elle meme * /
     * m_posX = i_go;
     * m_posX = j_go;
     * } else {
     * throw new Exception("Exception thrown");
     * }
     * } catch (Exception e) {
     * 
     * poseMoiAleatoirement();
     * }
     * }
     */

    /**
     * @brief : donneMoiPosXAleatoire() : une methode qui retourne une position X
     *        aléatoire dans l'interval de nombre de lignes
     * @return : int i_rand
     */
    public int donneMoiPosXAleatoire() {
        int i_rand;
        int maxI = m_monde.getLignes() - 1;
        int minI = 0;
        int compteur = 0;
        do {
            i_rand = (int) (Math.random() * (maxI - minI + 1) + minI);
            compteur++;
            if (compteur == 100) {
                return 0;
            }
        } while (i_rand > m_monde.getLignes() - 1 || m_monde.estTuOccupeParRobot(i_rand, m_posY) == true);

        return i_rand;
    }

    /**
     * @brief : donneMoiPosYAleatoire() : une methode qui retourne une position Y
     *        aléatoire dans l'interval de nombre de colonnes
     * @return : int j_rand
     */
    public int donneMoiPosYAleatoire() {
        int j_rand;
        int maxI = m_monde.getColonnes() - 1;
        int minI = 0;
        int compteur = 0;
        do {
            j_rand = (int) (Math.random() * (maxI - minI + 1) + minI);
            compteur++;
            if (compteur == 100) {
                return 0;
            }
        } while (j_rand > m_monde.getColonnes() - 1 || m_monde.estTuOccupeParRobot(m_posX, j_rand) == true);

        return j_rand;
    }

    public abstract void parcourir();

}
