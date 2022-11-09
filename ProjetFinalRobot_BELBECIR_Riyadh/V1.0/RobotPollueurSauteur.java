import java.awt.*;

public class RobotPollueurSauteur extends Robot {

    private int m_num;
    private static int m_nbRobotsPollueursSauteurs = 0;

    public int Num() {
        return m_num;
    }

    public RobotPollueurSauteur(Monde monde, DessineTerrain terrain) {
        super(monde, terrain);
        super.setPosX(donneMoiPosXAleatoire());
        super.setPosY(donneMoiPosYAleatoire());
        monde.setRobots(super.getPosX(), super.getPosY(), true);
        this.getXprev()[0] = this.getPosX();
        this.getYprev()[0] = this.getPosY();
        m_nbRobotsPollueursSauteurs++;
        m_num = m_nbRobotsPollueursSauteurs;
        String nomRobot = "PS" + String.valueOf(m_num);
        super.setNomRobot(nomRobot);

        super.setRobotImg("rps.png");

        monde.getListRPS().add(this);
        // monde.getRobotsList().add(this);
        m_terrain.addRobotIconToSquare(this.getPosX(), this.getPosY(), this);
        monde.getGUI().getMainFrame().revalidate();
        monde.getGUI().getMainFrame().repaint();

    }

    public RobotPollueurSauteur(int posX, int posY, Monde monde, DessineTerrain terrain) {
        super(monde, terrain);
        if (monde.estTuSurGrille(posX, posY) && !monde.estTuOccupeParRobot(posX, posY)) {
            super.setPosX(posX);
            super.setPosY(posY);
            monde.setRobots(super.getPosX(), super.getPosY(), true);
        } else {
            super.setPosX(donneMoiPosXAleatoire());
            super.setPosY(donneMoiPosYAleatoire());
            monde.setRobots(super.getPosX(), super.getPosY(), true);
        }
        m_nbRobotsPollueursSauteurs++;
        m_num = m_nbRobotsPollueursSauteurs;
        String nomRobot = "PS" + String.valueOf(m_num);
        super.setNomRobot(nomRobot);

        super.setRobotImg("rps.png");

        monde.getListRPS().add(this);
        monde.getRobotsList().add(this);
        m_terrain.addRobotIconToSquare(this.getPosX(), this.getPosY(), this);
        monde.getGUI().getMainFrame().revalidate();
        monde.getGUI().getMainFrame().repaint();

    }

    public void jeBouge() {
        boolean validate = false;
        int direction;
        int flag = 0;
        this.getXprev()[1] = this.getXprev()[0];
        this.getYprev()[1] = this.getYprev()[0];
        while (!validate) {

            direction = (int) (Math.random() * 100) % 4;
            switch (direction) {
                case 0: // Bouger vers le haut
                    if (super.getPosX() > 1) {
                        if (!m_monde.estTuOccupeParRobot(super.getPosX() - 2, super.getPosY())) {
                            m_monde.jaimeLeDesordre(super.getPosX(), super.getPosY());
                            m_monde.setRobots(super.getPosX(), super.getPosY(), false);
                            super.setPosX(super.getPosX() - 2);
                            m_monde.setRobots(super.getPosX(), super.getPosY(), true);
                            flag = 1;
                        }
                        validate = true;
                    }

                    break;
                case 1: // Bouger vers le bas
                    if (super.getPosX() < m_monde.getLignes() - 2) {
                        if (!m_monde.estTuOccupeParRobot(super.getPosX() + 2, super.getPosY())) {
                            m_monde.jaimeLeDesordre(super.getPosX(), super.getPosY());
                            m_monde.setRobots(super.getPosX(), super.getPosY(), false);
                            super.setPosX(super.getPosX() + 2);
                            m_monde.setRobots(super.getPosX(), super.getPosY(), true);
                            flag = 1;
                        }
                        validate = true;
                    }
                    break;
                case 2: // Bouger vers la gauche
                    if (super.getPosY() > 1) {
                        if (!m_monde.estTuOccupeParRobot(super.getPosX(), super.getPosY() - 2)) {
                            m_monde.jaimeLeDesordre(super.getPosX(), super.getPosY());
                            m_monde.setRobots(super.getPosX(), super.getPosY(), false);
                            super.setPosY(super.getPosY() - 2);
                            m_monde.setRobots(super.getPosX(), super.getPosY(), true);
                            flag = 1;
                        }
                        validate = true;
                    }

                    break;
                case 3: // Bouger vers la droite
                    if (super.getPosY() < m_monde.getColonnes() - 2) {
                        if (!m_monde.estTuOccupeParRobot(super.getPosX(), super.getPosY() + 2)) {
                            m_monde.jaimeLeDesordre(super.getPosX(), super.getPosY());
                            m_monde.setRobots(super.getPosX(), super.getPosY(), false);
                            super.setPosY(super.getPosY() + 2);
                            m_monde.setRobots(super.getPosX(), super.getPosY(), true);
                            flag = 1;
                        }
                        validate = true;
                    }
                    break;
                default:
                    break;
            }
        }
        if (flag == 1) {
            m_terrain.addRobotIconToSquare(this.getPosX(), this.getPosY(), this);
            m_terrain.getAllSquareIndexed(this.getXprev()[1], this.getYprev()[1]).setBackground(Color.black);

            this.getXprev()[0] = this.getPosX();
            this.getYprev()[0] = this.getPosY();
        }
    }

    public void parcourir() {

    }
}
