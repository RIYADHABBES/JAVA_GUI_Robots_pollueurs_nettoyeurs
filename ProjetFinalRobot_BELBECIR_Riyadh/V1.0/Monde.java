
import java.util.ArrayList;

public class Monde {
    private int m_nbLignes; /// Nombre des lignes de la grille (matrice)
    private int m_nbColonnes; /// Nombre des colonnes de la grille (matrice)
    private boolean m_matrice[][]; // Matrice de booleans indiquants vrai si la case est polluée
    private boolean m_robots[][]; // Matrice de booleans indiquants vrai si la case est occupée par un robot
    private myGUI m_gui;
    private ArrayList<RobotNettoyeurAutour> m_RNA = new ArrayList<RobotNettoyeurAutour>();
    private ArrayList<RobotNettoyeurHorizon> m_RNH = new ArrayList<RobotNettoyeurHorizon>();
    private ArrayList<RobotPollueurLibre> m_RPL = new ArrayList<RobotPollueurLibre>();
    private ArrayList<RobotPollueurSauteur> m_RPS = new ArrayList<RobotPollueurSauteur>();
    private ArrayList<RobotPollueurToutDroit> m_RPV = new ArrayList<RobotPollueurToutDroit>();
    private ArrayList<Robot> m_robotsList = new ArrayList<Robot>();

    public ArrayList<Robot> getRobotsList() {
        return m_robotsList;
    }

    public ArrayList<RobotNettoyeurAutour> getListRNA() {
        return m_RNA;
    }

    public ArrayList<RobotNettoyeurHorizon> getListRNH() {
        return m_RNH;
    }

    public ArrayList<RobotPollueurLibre> getListRPL() {
        return m_RPL;
    }

    public ArrayList<RobotPollueurSauteur> getListRPS() {
        return m_RPS;
    }

    public ArrayList<RobotPollueurToutDroit> getListRPV() {
        return m_RPV;
    }

    public myGUI getGUI() {
        return m_gui;
    }

    public int getLignes() {
        return this.m_nbLignes;
    }

    public int getColonnes() {
        return this.m_nbColonnes;
    }

    public boolean[][] getMatrice() {
        return this.m_matrice;
    }

    public boolean[][] getRobots() {
        return m_robots;
    }

    public void setRobots(int i, int j, boolean boolValue) {
        m_robots[i][j] = boolValue;
    }

    public void setMatrice(boolean pollueJe, int i, int j) {
        if (estTuSurGrille(i, j)) {
            m_matrice[i][j] = pollueJe;
        } else {
        }
    }

    /**
     * @brief : Un constructeur par défaut qui crée un monde (10x10) sans papiers
     *        gras (matrice == faux)
     */

    public Monde() {
        this.m_nbLignes = 10;
        this.m_nbColonnes = 10;
        this.m_matrice = new boolean[m_nbLignes][m_nbColonnes];
        for (int i = 0; i < m_matrice.length; i++) {
            for (int j = 0; j < m_matrice[i].length; j++) {
                m_matrice[i][j] = false;
            }
        }
        this.m_robots = new boolean[m_nbLignes][m_nbColonnes];
        for (int i = 0; i < m_robots.length; i++) {
            for (int j = 0; j < m_robots[i].length; j++) {
                m_robots[i][j] = false;
            }
        }
    }

    /**
     * 
     * @brief : Un constructeur qui initialise le monde à une taille passée en
     *        parametres
     * @param Lignes   : nombre des lignes
     * @param Colonnes : nombre des colonnes
     * 
     */

    public Monde(int Lignes, int Colonnes, myGUI gui) {
        m_gui = gui;
        this.m_nbLignes = Lignes;
        this.m_nbColonnes = Colonnes;
        this.m_matrice = new boolean[m_nbLignes][m_nbColonnes];
        for (int i = 0; i < m_matrice.length; i++) {
            for (int j = 0; j < m_matrice[i].length; j++) {
                m_matrice[i][j] = false;
            }
        }
        this.m_robots = new boolean[m_nbLignes][m_nbColonnes];
        for (int i = 0; i < m_robots.length; i++) {
            for (int j = 0; j < m_robots[i].length; j++) {
                m_robots[i][j] = false;
            }
        }
    }

    /**
     * @brief : jaimeLeDesordre() : Methode qui met un papier gras (matrice = true)
     *        dans la grille d'une façon aléatoire.
     * @return : void
     */

    public void jaimeLeDesordre() {
        try {
            int i_gras;
            int j_gras;
            int maxI = m_nbLignes - 1;
            int minI = 0;
            int maxJ = m_nbColonnes - 1;
            int minJ = 0;
            do {
                i_gras = (int) (Math.random() * (maxI - minI + 1) + minI);
                j_gras = (int) (Math.random() * (maxJ - minJ + 1) + minJ);

            } while (i_gras > m_nbLignes - 1 || j_gras > m_nbColonnes);

            m_matrice[i_gras][j_gras] = true;

        } catch (Exception e) {
            m_matrice[0][0] = true;

        }
    }

    /**
     * @brief : jaimeLeDesordre(int i_gras, int j_gras) : Methode qui met un papier
     *        gras (matrice = true) dans la grille.
     * @param : int i_gras :
     * @param : int j_gras :
     * @return : void
     */

    public void jaimeLeDesordre(int i_gras, int j_gras) {
        try {
            m_matrice[i_gras][j_gras] = true;

        } catch (Exception e) {
            // m_matrice[0][0] = true;
            jaimeLeDesordre();
        }
    }

    /**
     * @brief : monsieurPropre() : une méthode qui nettoie la grille
     *        (matrice = false) à une position (i,j) choisit aléatoirement
     * @return : void
     */

    public void monsieurPropre() {

        int i_propre;
        int j_propre;
        int maxI = m_nbLignes - 1;
        int minI = 0;
        int maxJ = m_nbColonnes - 1;
        int minJ = 0;
        do {
            i_propre = (int) (Math.random() * (maxI - minI + 1) + minI);
            j_propre = (int) (Math.random() * (maxJ - minJ + 1) + minJ);

        } while (i_propre > m_nbLignes - 1 || j_propre > m_nbColonnes);

        m_matrice[i_propre][j_propre] = false;

    }

    /**
     * @brief : monsieurPropre(int i_propre, int j_propre) : une méthode qui nettoie
     *        la grille
     *        (matrice = false) à une position (i,j) choisit aléatoirement
     * @param : int i_propre :
     * @param : int j_propre :
     * @return : void
     */

    public void monsieurPropre(int i_propre, int j_propre) {
        try {
            m_matrice[i_propre][j_propre] = false;
            // Attention /!\ /!\ : ici lorsque les reliefs du projet sont clairs, un choix
            // est fait si on prend le papier d'une case déja propre ou bien forcément on
            // prends d'une case non propre
        } catch (Exception e) {
            // m_matrice[0][0] = false;
            monsieurPropre();
        }
    }

    public void nettoyageComplet() {
        for (int i = 0; i < m_matrice.length; i++) {
            for (int j = 0; j < m_matrice[i].length; j++) {
                monsieurPropre(i, j);
            }
        }
    }

    public void DesordreComplet() {
        for (int i = 0; i < getLignes(); i++) {
            for (int j = 0; j < getColonnes(); j++) {
                setMatrice(true, i, j);
            }
        }
    }

    /**
     * @brief : estTuGras(int i_ask, int j_ask) : Methode qui retourne vrai si la
     *        case contient un papier faux sinon.
     * @param : int i_ask :
     * @param : int j_ask :
     * @return : boolean
     */

    public boolean estTuGras(int i_ask, int j_ask) {
        try {
            return m_matrice[i_ask][j_ask];
        } catch (Exception e) {
            return m_matrice[0][0];
        }
    }

    public int tasCombienDePapierGras() {
        int papiers = 0;
        for (int i = 0; i < m_matrice.length; i++) {
            for (int j = 0; j < m_matrice[i].length; j++) {
                if (estTuGras(i, j)) {
                    papiers++;
                }
            }
        }
        return papiers;
    }

    public void maTerasseVueDeCiel() {
        System.out
                .println(ConsoleColors.BLUE + "-------------Welcome To The Arena----------- \n" + ConsoleColors.RESET);

        for (int i = 0; i < getLignes(); i++) {
            for (int j = 0; j < getColonnes(); j++) {
                if (m_matrice[i][j]) {
                    System.out.print(" " + ConsoleColors.RED + m_matrice[i][j] + " " + ConsoleColors.RESET);

                } else {
                    System.out.print(" " + ConsoleColors.GREEN + m_matrice[i][j] + ConsoleColors.RESET);
                }
            }
            System.out.println();

        }

    }

    /**
     * @brief : donneMoiPosXAleatoire() : une methode qui retourne une position X
     *        aléatoire dans l'interval de nombre de lignes
     * @return : int i_rand
     */
    public int donneMoiPosXAleatoire() {
        int i_rand;
        int maxI = m_nbLignes - 1;
        int minI = 0;
        do {
            i_rand = (int) (Math.random() * (maxI - minI + 1) + minI);

        } while (i_rand > m_nbLignes - 1);

        return i_rand;
    }

    /**
     * @brief : donneMoiPosYAleatoire() : une methode qui retourne une position Y
     *        aléatoire dans l'interval de nombre de colonnes
     * @return : int j_rand
     */
    public int donneMoiPosYAleatoire() {
        int j_rand;
        int maxI = m_nbColonnes - 1;
        int minI = 0;
        do {
            j_rand = (int) (Math.random() * (maxI - minI + 1) + minI);

        } while (j_rand > m_nbColonnes - 1);

        return j_rand;
    }

    /**
     * @brief : estTuSurLignes(int i) : une methode qui retourne vrai si la position
     *        x est dans l'interval de nombre de lignes
     * @param i : position X
     * @return boolean
     */
    public boolean estTuSurLignes(int i) {
        if (i < m_nbLignes && i >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @brief : estTuSurColonnes(int i) : une methode qui retourne vrai si la
     *        position
     *        Y est dans l'interval de nombre de colonnes
     * @param j : position Y
     * @return boolean
     */
    public boolean estTuSurColonnes(int j) {
        if (j < m_nbColonnes && j >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @brief : estTuSurGrille(int i,int j) : une methode qui retourne vrai si la
     *        position
     *        X et Y sont dans l'interval de la grille
     * @param i : position X
     * @param j : position Y
     * 
     * @return boolean
     */
    public boolean estTuSurGrille(int i, int j) {
        if (estTuSurLignes(i) && estTuSurColonnes(j)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean estTuOccupeParRobot(int i, int j) {
        return m_robots[i][j];
    }
}