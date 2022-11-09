import java.awt.*;


public class RobotNettoyeurAutour extends Robot {
    private int m_num;
    private static int m_nbRobotsNettoyeursAutour = 0;

    public int getNum() {
        return m_num;
    }

    public RobotNettoyeurAutour(Monde monde, DessineTerrain terrain) {
        super(monde, terrain);
        super.setPosX(donneMoiPosXAleatoire());
        super.setPosY(donneMoiPosYAleatoire());
        monde.setRobots(super.getPosX(), super.getPosY(), true);
        m_nbRobotsNettoyeursAutour++;
        m_num = m_nbRobotsNettoyeursAutour;

        String nomRobot = "NA" + String.valueOf(m_num);
        super.setNomRobot(nomRobot);

        super.setRobotImg("rna.png");

        monde.getListRNA().add(this);
        // monde.getRobotsList().add(this);
        // jeNettoieAutour(getPosX(), getPosY());
        m_terrain.addRobotIconToSquare(this.getPosX(), this.getPosY(), this);
        monde.getGUI().getMainFrame().revalidate();

        monde.getGUI().getMainFrame().repaint();
    }

    public void jeBouge() {

        // m_monde.getGUI().getMainFrame().revalidate();
        // m_monde.getGUI().getMainFrame().repaint();

        boolean validate = false;
        int direction;
        int flag = 0;
        while (!validate) {

            direction = (int) (Math.random() * 100) % 8;

            switch (direction) {
                case 0: // Bouger vers le haut
                    if (super.getPosX() > 0) {
                        if (!m_monde.estTuOccupeParRobot(super.getPosX() - 1, super.getPosY())) {
                            m_monde.setRobots(super.getPosX(), super.getPosY(), false);
                            super.setPosX(super.getPosX() - 1);
                            m_monde.setRobots(super.getPosX(), super.getPosY(), true);
                            jeNettoieAutour(super.getPosX(), super.getPosY());
                            flag = 1;
                        }
                        validate = true;
                    }

                    break;
                case 1: // Bouger vers le bas
                    if (super.getPosX() < m_monde.getLignes() - 1) {
                        if (!m_monde.estTuOccupeParRobot(super.getPosX() + 1, super.getPosY())) {
                            m_monde.setRobots(super.getPosX(), super.getPosY(), false);
                            super.setPosX(super.getPosX() + 1);
                            m_monde.setRobots(super.getPosX(), super.getPosY(), true);
                            jeNettoieAutour(super.getPosX(), super.getPosY());
                            flag = 1;

                        }
                        validate = true;

                    }
                    break;
                case 2: // Bouger vers la gauche
                    if (super.getPosY() > 0) {

                        if (!m_monde.estTuOccupeParRobot(super.getPosX(), super.getPosY() - 1)) {
                            m_monde.setRobots(super.getPosX(), super.getPosY(), false);
                            super.setPosY(super.getPosY() - 1);
                            m_monde.setRobots(super.getPosX(), super.getPosY(), true);
                            jeNettoieAutour(super.getPosX(), super.getPosY());
                            flag = 1;
                        }
                        validate = true;

                    }

                    break;
                case 3: // Bouger vers la droite
                    if (super.getPosY() < m_monde.getColonnes() - 1) {

                        if (!m_monde.estTuOccupeParRobot(super.getPosX(), super.getPosY() + 1)) {
                            m_monde.setRobots(super.getPosX(), super.getPosY(), false);
                            super.setPosY(super.getPosY() + 1);
                            m_monde.setRobots(super.getPosX(), super.getPosY(), true);
                            jeNettoieAutour(super.getPosX(), super.getPosY());
                            flag = 1;
                        }
                        validate = true;

                    }
                    break;
                case 4: // Bouger vers Haut Gauche
                    if (super.getPosX() > 0 && super.getPosY() > 0) {

                        if (!m_monde.estTuOccupeParRobot(super.getPosX() - 1, super.getPosY() - 1)) {
                            m_monde.setRobots(super.getPosX(), super.getPosY(), false);
                            super.setPosX(super.getPosX() - 1);
                            super.setPosY(super.getPosY() - 1);
                            m_monde.setRobots(super.getPosX(), super.getPosY(), true);

                            jeNettoieAutour(super.getPosX(), super.getPosY());
                            flag = 1;
                        }
                        validate = true;
                    }
                    break;
                case 5: // Bouger vers Haut Droite
                    if (super.getPosX() > 0 && super.getPosY() < m_monde.getColonnes() - 1) {

                        if (!m_monde.estTuOccupeParRobot(super.getPosX() - 1, super.getPosY() + 1)) {
                            m_monde.setRobots(super.getPosX(), super.getPosY(), false);
                            super.setPosX(super.getPosX() - 1);
                            super.setPosY(super.getPosY() + 1);
                            m_monde.setRobots(super.getPosX(), super.getPosY(), true);

                            jeNettoieAutour(super.getPosX(), super.getPosY());
                            flag = 1;
                        }
                        validate = true;
                    }
                    break;
                case 6: // Bouger vers Bas Gauche
                    if (super.getPosX() < m_monde.getLignes() - 1 && super.getPosY() > 0) {

                        if (!m_monde.estTuOccupeParRobot(super.getPosX() + 1, super.getPosY() - 1)) {
                            m_monde.setRobots(super.getPosX(), super.getPosY(), false);
                            super.setPosX(super.getPosX() + 1);
                            super.setPosY(super.getPosY() - 1);
                            m_monde.setRobots(super.getPosX(), super.getPosY(), true);

                            jeNettoieAutour(super.getPosX(), super.getPosY());
                            flag = 1;
                        }
                        validate = true;
                    }
                    break;
                case 7: // Bouger vers Bas Droite
                    if (super.getPosX() < m_monde.getLignes() - 1 && super.getPosY() < m_monde.getColonnes() - 1) {

                        if (!m_monde.estTuOccupeParRobot(super.getPosX() + 1, super.getPosY() + 1)) {
                            m_monde.setRobots(super.getPosX(), super.getPosY(), false);
                            super.setPosX(super.getPosX() + 1);
                            super.setPosY(super.getPosY() + 1);
                            m_monde.setRobots(super.getPosX(), super.getPosY(), true);

                            jeNettoieAutour(super.getPosX(), super.getPosY());
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

        }

    }

    public void jeNettoieAutour(int i, int j) {
        Color C = Color.white;
        for (int k = 0; k < 8; k++) {

            switch (k) {
                case 0:// Nettoie Haut Gauche
                    if (i > 0 && j > 0) {
                        m_monde.setMatrice(false, i - 1, j - 1);
                        m_terrain.getAllSquareIndexed(i - 1, j - 1).setBackground(C);
                    }
                    break;
                case 1: // Nettoie Haut
                    if (i > 0) {
                        m_monde.setMatrice(false, i - 1, j);
                        m_terrain.getAllSquareIndexed(i - 1, j).setBackground(C);
                    }
                    break;
                case 2:// Nettoie Haut droite
                    if (i > 0 && j < m_monde.getColonnes() - 1) {
                        m_monde.setMatrice(false, i - 1, j + 1);
                        m_terrain.getAllSquareIndexed(i - 1, j + 1).setBackground(C);
                    }
                    break;
                case 3:// Nettoie Droite
                    if (j < m_monde.getColonnes() - 1) {
                        m_monde.setMatrice(false, i, j + 1);
                        m_terrain.getAllSquareIndexed(i, j + 1).setBackground(C);
                    }
                    break;
                case 4:// Nettoie Droite Bas
                    if (i < m_monde.getLignes() - 1 && j < m_monde.getColonnes() - 1) {
                        m_monde.setMatrice(false, i + 1, j + 1);
                        m_terrain.getAllSquareIndexed(i + 1, j + 1).setBackground(C);
                    }
                    break;
                case 5:// Nettoie Bas
                    if (i < m_monde.getLignes() - 1) {
                        m_monde.setMatrice(false, i + 1, j);
                        m_terrain.getAllSquareIndexed(i + 1, j).setBackground(C);
                    }
                    break;
                case 6:// Nettoie Bas Gauche
                    if (i < m_monde.getLignes() - 1 && j > 0) {
                        m_monde.setMatrice(false, i + 1, j - 1);
                        m_terrain.getAllSquareIndexed(i + 1, j - 1).setBackground(C);
                    }
                    break;
                case 7:// Nettoie Gauche
                    if (j > 0) {
                        m_monde.setMatrice(false, i, j - 1);
                        m_terrain.getAllSquareIndexed(i, j - 1).setBackground(C);
                    }
                    break;
                default:
                    break;
            }
        }

    }

    public void parcourir() {

    }
}
