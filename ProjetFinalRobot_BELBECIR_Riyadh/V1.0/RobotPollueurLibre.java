import java.awt.*;

public class RobotPollueurLibre extends Robot {

    private int m_num;
    private static int m_nbRobotsPollueursLibres = 0;

    public int getNum() {
        return m_num;
    }

    public RobotPollueurLibre(Monde monde, DessineTerrain terrain) {
        super(monde, terrain);
        super.setPosX(donneMoiPosXAleatoire());
        super.setPosY(donneMoiPosYAleatoire());
        monde.setRobots(super.getPosX(), super.getPosY(), true);
        this.getXprev()[0] = this.getPosX();
        this.getYprev()[0] = this.getPosY();

        m_nbRobotsPollueursLibres++;
        m_num = m_nbRobotsPollueursLibres;

        String nomRobot = "PL" + String.valueOf(m_num);
        super.setNomRobot(nomRobot);

        super.setRobotImg("rpl.png");

        monde.getListRPL().add(this);
        // monde.getRobotsList().add(this);
        m_terrain.addRobotIconToSquare(this.getPosX(), this.getPosY(), this);
        monde.getGUI().getMainFrame().revalidate();
        monde.getGUI().getMainFrame().repaint();

    }

    public RobotPollueurLibre(int posX, int posY, Monde monde, DessineTerrain terrain) {
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
        m_nbRobotsPollueursLibres++;
        m_num = m_nbRobotsPollueursLibres;
        String nomRobot = "PL" + String.valueOf(m_num);
        super.setNomRobot(nomRobot);

        super.setRobotImg("rpl.png");

        monde.getListRPL().add(this);
        // monde.getRobotsList().add(this);

    }

    public void jeBouge() {
        boolean validate = false;
        int direction;
        int flag = 0;
        this.getXprev()[1] = this.getXprev()[0];
        this.getYprev()[1] = this.getYprev()[0];

        while (!validate) {

            direction = (int) (Math.random() * 100) % 8;
            switch (direction) {
                case 0: // Bouger vers le haut
                    if (super.getPosX() > 0) {
                        if (!m_monde.estTuOccupeParRobot(super.getPosX() - 1, super.getPosY())) {
                            m_monde.jaimeLeDesordre(super.getPosX(), super.getPosY());
                            m_monde.setRobots(super.getPosX(), super.getPosY(), false);
                            super.setPosX(super.getPosX() - 1);
                            m_monde.setRobots(super.getPosX(), super.getPosY(), true);
                            flag = 1;
                        }
                        validate = true;
                    }

                    break;
                case 1: // Bouger vers le bas
                    if (super.getPosX() < m_monde.getLignes() - 1) {
                        if (!m_monde.estTuOccupeParRobot(super.getPosX() + 1, super.getPosY())) {
                            m_monde.jaimeLeDesordre(super.getPosX(), super.getPosY());
                            m_monde.setRobots(super.getPosX(), super.getPosY(), false);
                            super.setPosX(super.getPosX() + 1);
                            m_monde.setRobots(super.getPosX(), super.getPosY(), true);
                            flag = 1;
                        }
                        validate = true;
                    }
                    break;
                case 2: // Bouger vers la gauche
                    if (super.getPosY() > 0) {
                        if (!m_monde.estTuOccupeParRobot(super.getPosX(), super.getPosY() - 1)) {
                            m_monde.jaimeLeDesordre(super.getPosX(), super.getPosY());
                            m_monde.setRobots(super.getPosX(), super.getPosY(), false);
                            super.setPosY(super.getPosY() - 1);
                            m_monde.setRobots(super.getPosX(), super.getPosY(), true);
                            flag = 1;
                        }
                        validate = true;
                    }

                    break;
                case 3: // Bouger vers la droite
                    if (super.getPosY() < m_monde.getColonnes() - 1) {
                        if (!m_monde.estTuOccupeParRobot(super.getPosX(), super.getPosY() + 1)) {
                            m_monde.jaimeLeDesordre(super.getPosX(), super.getPosY());
                            m_monde.setRobots(super.getPosX(), super.getPosY(), false);
                            super.setPosY(super.getPosY() + 1);
                            m_monde.setRobots(super.getPosX(), super.getPosY(), true);
                            flag = 1;
                        }
                        validate = true;
                    }
                    break;
                case 4: // Bouger vers Haut Gauche
                    if (super.getPosX() > 0 && super.getPosY() > 0) {
                        if (!m_monde.estTuOccupeParRobot(super.getPosX() - 1, super.getPosY() - 1)) {
                            m_monde.jaimeLeDesordre(super.getPosX(), super.getPosY());
                            m_monde.setRobots(super.getPosX(), super.getPosY(), false);
                            super.setPosX(super.getPosX() - 1);
                            super.setPosY(super.getPosY() - 1);
                            m_monde.setRobots(super.getPosX(), super.getPosY(), true);
                            flag = 1;
                        }
                        validate = true;
                    }
                    break;
                case 5: // Bouger vers Haut Droite
                    if (super.getPosX() > 0 && super.getPosY() < m_monde.getColonnes() - 1) {
                        if (!m_monde.estTuOccupeParRobot(super.getPosX() - 1, super.getPosY() + 1)) {
                            m_monde.jaimeLeDesordre(super.getPosX(), super.getPosY());
                            m_monde.setRobots(super.getPosX(), super.getPosY(), false);
                            super.setPosX(super.getPosX() - 1);
                            super.setPosY(super.getPosY() + 1);
                            m_monde.setRobots(super.getPosX(), super.getPosY(), true);
                            flag = 1;
                        }
                        validate = true;
                    }
                    break;
                case 6: // Bouger vers Bas Gauche
                    if (super.getPosX() < m_monde.getLignes() - 1 && super.getPosY() > 0) {
                        if (!m_monde.estTuOccupeParRobot(super.getPosX() + 1, super.getPosY() - 1)) {
                            m_monde.jaimeLeDesordre(super.getPosX(), super.getPosY());
                            m_monde.setRobots(super.getPosX(), super.getPosY(), false);
                            super.setPosX(super.getPosX() + 1);
                            super.setPosY(super.getPosY() - 1);
                            m_monde.setRobots(super.getPosX(), super.getPosY(), true);
                            flag = 1;
                        }
                        validate = true;
                    }
                    break;
                case 7: // Bouger vers Bas Droite
                    if (super.getPosX() < m_monde.getLignes() - 1 && super.getPosY() < m_monde.getColonnes() - 1) {
                        if (!m_monde.estTuOccupeParRobot(super.getPosX() + 1, super.getPosY() + 1)) {
                            m_monde.jaimeLeDesordre(super.getPosX(), super.getPosY());
                            m_monde.setRobots(super.getPosX(), super.getPosY(), false);
                            super.setPosX(super.getPosX() + 1);
                            super.setPosY(super.getPosY() + 1);
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
