import java.awt.*;

public class RobotPollueurToutDroit extends Robot {
    private int m_num;
    private static int m_nbRobotsPollueursToutDroit = 0;

    public int getNum() {
        return m_num;
    }

    public RobotPollueurToutDroit(Monde monde, DessineTerrain terrain) {
        super(monde, terrain);

        int posY_start;
        // do {
        posY_start = donneMoiPosYAleatoire();
        super.setPosY(posY_start);
        super.setPosX(0);
        monde.setRobots(super.getPosX(), super.getPosY(), true);
        this.getXprev()[0] = this.getPosX();
        this.getYprev()[0] = this.getPosY();

        // } while (m_monde.estTuOccupeParRobot(0, super.getPosY()));

        m_nbRobotsPollueursToutDroit++;
        m_num = m_nbRobotsPollueursToutDroit;
        String nomRobot = "PV" + String.valueOf(m_num);
        super.setNomRobot(nomRobot);

        super.setRobotImg("rpv.png");

        monde.getListRPV().add(this);
        // monde.getRobotsList().add(this);
        m_terrain.addRobotIconToSquare(this.getPosX(), this.getPosY(), this);
        monde.getGUI().getMainFrame().revalidate();
        monde.getGUI().getMainFrame().repaint();
    }

    public RobotPollueurToutDroit(int posY) {
        super(m_monde, m_terrain);
        super.setPosX(0);

        try {

            if (posY < m_monde.getColonnes() && posY >= 0 && !m_monde.estTuOccupeParRobot(super.getPosX(), posY)) { // &&
                                                                                                                    // !m_monde.estTuOccupeParRobot(0,
                                                                                                                    // super.getPosY()))
                                                                                                                    // {
                /** /!\ peutere j'ajoute aussi le cas ou c'est ça position elle meme */
                super.setPosY(posY);
                m_monde.setRobots(super.getPosX(), super.getPosY(), true);

                m_num = posY;
            } else {
                throw new Exception("Exception thrown");
            }
        } catch (Exception e) {
            int posY_start;
            // do {
            posY_start = donneMoiPosYAleatoire();
            // } while (m_monde.estTuOccupeParRobot(0, super.getPosY()));

            super.setPosY(posY_start);
            m_monde.setRobots(super.getPosX(), super.getPosY(), true);

        }
    }

    /**
     * @brief : jeBouge() : une methode surchergée qui permet de bouger tout droit
     *        du haut vers le bas et reste en bas.
     * @return : void
     */
    public void jeBouge() {
        m_monde.setMatrice(true, super.getPosX(), super.getPosY());
        this.getXprev()[1] = this.getXprev()[0];
        this.getYprev()[1] = this.getYprev()[0];
        if (super.getPosX() < (m_monde.getLignes() - 1)) {
            if (!m_monde.estTuOccupeParRobot(super.getPosX() + 1, super.getPosY())) {
                m_monde.setRobots(super.getPosX(), super.getPosY(), false);
                super.setPosX(super.getPosX() + 1);
                m_monde.setRobots(super.getPosX(), super.getPosY(), true);
                m_terrain.addRobotIconToSquare(this.getPosX(), this.getPosY(), this);

            }
        }

        if (m_monde.estTuGras(this.getXprev()[1], this.getYprev()[1])) {
            m_terrain.getAllSquareIndexed(this.getXprev()[1], this.getYprev()[1]).setBackground(Color.black);
        }
        this.getXprev()[0] = this.getPosX();
        this.getYprev()[0] = this.getPosY();

        if (this.getXprev()[0] == m_monde.getLignes() - 1 && this.getXprev()[1] == m_monde.getLignes() - 1) {
            if (!m_monde.estTuOccupeParRobot(0, super.getPosY())) {
                m_monde.setRobots(super.getPosX(), super.getPosY(), false);
                this.setPosX(0);
                m_monde.setRobots(super.getPosX(), super.getPosY(), true);
                this.getXprev()[0] = this.getPosX();
                m_terrain.addRobotIconToSquare(this.getPosX(), this.getPosY(), this);
                m_terrain.getAllSquareIndexed(this.getXprev()[1], this.getYprev()[1]).setBackground(Color.black);
            }
        }

    }

    public void parcourir() {

    }

}
