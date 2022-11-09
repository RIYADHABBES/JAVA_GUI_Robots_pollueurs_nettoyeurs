import java.awt.*;

public class RobotNettoyeurHorizon extends Robot {
    private int m_num;
    private static int m_nbRobotsNettoyeursHorizon = 0;

    public int getNum() {
        return m_num;
    }

    public RobotNettoyeurHorizon(Monde monde, DessineTerrain terrain) {
        super(monde, terrain);
        int posX_start;
        super.setPosY(0);
        posX_start = donneMoiPosXAleatoire();
        super.setPosX(posX_start);
        monde.setRobots(super.getPosX(), super.getPosY(), true);

        this.getXprev()[0] = this.getPosX();
        this.getYprev()[0] = this.getPosY();
        m_nbRobotsNettoyeursHorizon++;
        m_num = m_nbRobotsNettoyeursHorizon;
        String nomRobot = "NH" + String.valueOf(m_num);
        super.setNomRobot(nomRobot);

        super.setRobotImg("rnh.png");

        monde.getListRNH().add(this);
        // monde.getRobotsList().add(this);
        m_terrain.addRobotIconToSquare(this.getPosX(), this.getPosY(), this);
        monde.getGUI().getMainFrame().revalidate();
        monde.getGUI().getMainFrame().repaint();

    }

    public void jeBouge() {
        m_monde.setMatrice(false, super.getPosX(), super.getPosY());
        this.getXprev()[1] = this.getXprev()[0];
        this.getYprev()[1] = this.getYprev()[0];
        if (super.getPosY() < (m_monde.getColonnes() - 1)) {
            if (!m_monde.estTuOccupeParRobot(super.getPosX(), super.getPosY() + 1)) {
                m_monde.setRobots(super.getPosX(), super.getPosY(), false);
                super.setPosY(super.getPosY() + 1);
                m_monde.setRobots(super.getPosX(), super.getPosY(), true);
                m_terrain.addRobotIconToSquare(this.getPosX(), this.getPosY(), this);

            }
        }

        if (!m_monde.estTuGras(this.getXprev()[1], this.getYprev()[1])) {
            m_terrain.getAllSquareIndexed(this.getXprev()[1], this.getYprev()[1]).setBackground(Color.white);
        }
        this.getXprev()[0] = this.getPosX();
        this.getYprev()[0] = this.getPosY();

        if (this.getYprev()[0] == m_monde.getColonnes() - 1 && this.getYprev()[1] == m_monde.getColonnes() - 1) {
          if (!m_monde.estTuOccupeParRobot(super.getPosX(), 0)) {
            m_monde.setRobots(getPosX(), getPosY(), false);
            this.setPosY(0);
            m_monde.setRobots(getPosX(), getPosY(), true);
            this.getYprev()[0] = this.getPosY();
            m_terrain.addRobotIconToSquare(this.getPosX(), this.getPosY(), this);
            m_terrain.getAllSquareIndexed(this.getXprev()[1], this.getYprev()[1]).setBackground(Color.white);
          
          } }

        /*
         * try {
         * m_monde.setMatrice(false, super.getPosX(), super.getPosY());
         * 
         * if (super.getPosY() < (m_monde.getColonnes() - 1) && super.getPosY() >= 0) {
         * 
         * super.setPosY(super.getPosY() + 1);
         * 
         * } else {
         * 
         * }
         * } catch (Exception e) {
         * super.setPosY(donneMoiPosYAleatoire());
         * }
         */
    }

    /*
     * public void jeBouge() {
     * m_monde.setMatrice(false, super.getPosX(), super.getPosY());
     * this.getXprev()[1] = this.getXprev()[0];
     * this.getYprev()[1] = this.getYprev()[0];
     * if (super.getPosY() < (m_monde.getColonnes() - 1) && super.getPosY() >= -1) {
     * super.setPosY(super.getPosY() + 1);
     * }
     * if (m_terrain.getLayer(this.getPosX(), this.getPosY()).getComponentCount() <
     * 1) {
     * m_terrain.addRobotIconToSquare(this.getPosX(), this.getPosY(), this);
     * }
     * 
     * if (!m_monde.estTuGras(this.getXprev()[1], this.getYprev()[1])
     * && m_terrain.getLayer(this.getXprev()[1],
     * this.getYprev()[1]).getComponentCount() < 2
     * && getPosY() != -1) {
     * m_terrain.getAllSquareIndexed(this.getXprev()[1],
     * this.getYprev()[1]).setBackground(Color.white);
     * 
     * }
     * this.getXprev()[0] = this.getPosX();
     * this.getYprev()[0] = this.getPosY();
     * 
     * if (this.getYprev()[0] == m_monde.getColonnes() - 1 && this.getYprev()[1] ==
     * m_monde.getColonnes() - 1) {
     * 
     * m_monde.setMatrice(false, super.getPosX(), m_monde.getColonnes() - 1);
     * this.setPosY(0);
     * this.getYprev()[0] = this.getPosY();
     * m_terrain.addRobotIconToSquare(this.getPosX(), this.getPosY(), this);
     * m_terrain.getAllSquareIndexed(this.getXprev()[1],
     * this.getYprev()[1]).setBackground(Color.white);
     * }
     * 
     * 
     * /* try {
     * m_monde.setMatrice(false, super.getPosX(), super.getPosY());
     * 
     * if (super.getPosY() < (m_monde.getColonnes() - 1) && super.getPosY() >= 0) {
     * 
     * super.setPosY(super.getPosY() + 1);
     * 
     * } else {
     * 
     * }
     * } catch (Exception e) {
     * super.setPosY(donneMoiPosYAleatoire());
     * }* /
     * }
     * 
     */
    public void parcourir() {

    }
}