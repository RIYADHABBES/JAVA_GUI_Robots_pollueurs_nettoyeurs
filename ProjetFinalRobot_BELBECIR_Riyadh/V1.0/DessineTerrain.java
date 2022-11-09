
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DessineTerrain {

  private myGUI m_myGUI;
  private int m_nbLignes = 5;
  private int m_nbColonnes = 5;
 // private int m_largeur;
  //private int m_longeur;
  // private double m_echelle = 8.0 / 13.0;
  private int m_xStart;
  private int m_yStart;
  private int m_UnityWidth;
  private int m_UnityHeight;
  private int m_espace = 5;
  private JLabel m_grasLabel[][];
  private ImageIcon m_grasIcon[][];

  // private JLabel m_grasRef;
  private JPanel[][] m_allSquares;
  private JLayeredPane m_lay[][];

  public void setXStart(int x_start) {
    m_xStart = x_start;
  }

  public void setYStart(int y_start) {
    m_yStart = y_start;
  }

  public void setAllSquares(int i, int j) {
    m_allSquares[i][j].setBounds(m_xStart + (m_UnityWidth + m_espace) * j,
        m_yStart + (m_UnityHeight + m_espace) * i, m_UnityWidth, m_UnityHeight);
    m_lay[i][j].setBounds(0, 0, m_UnityWidth, m_UnityHeight);

  }

  public JLayeredPane getLayer(int i, int j) {
    return m_lay[i][j];
  }

  public ImageIcon getGrasIcon(int i, int j) {
    return m_grasIcon[i][j];
  }

  public JLabel[][] getGrasLabel() {

    return m_grasLabel;
  }

  public JLabel getGrasLabel(int i, int j) {

    return m_grasLabel[i][j];
  }

  public int getEspace() {
    return m_espace;
  }

  public void setUnityWidth(int unityWidth) {
    m_UnityWidth = unityWidth;
  }

  public void setUnityHight(int unityHeight) {
    m_UnityHeight = unityHeight;
  }

  public int getUnityWidth() {
    return m_UnityWidth;
  }

  public int getUnityHight() {
    return m_UnityHeight;
  }

  public DessineTerrain(myGUI GUI) {
    m_myGUI = GUI;
    m_nbColonnes = m_myGUI.getMonde().getColonnes();
    m_nbLignes = m_myGUI.getMonde().getLignes();

    // m_myMainPanel = new JPanel();// JPanel();
    // m_myMainPanel.setVisible(true);

    // m_myMainPanel.setBackground(Color.green);
    // m_myGUI.getMainFrame().add(m_myMainPanel);

    m_allSquares = new JPanel[m_nbLignes][m_nbColonnes];
    m_lay = new JLayeredPane[m_nbLignes][m_nbColonnes];
    m_grasIcon = new ImageIcon[m_nbLignes][m_nbColonnes];
    m_grasLabel = new JLabel[m_nbLignes][m_nbColonnes];

    System.out.println("lignes " + m_allSquares.length + " Colonnes " + m_allSquares[0].length);
    System.out.println(m_nbColonnes + " Je suis dans dessinTerrain");
    System.out.println(m_nbLignes);

    m_xStart = (int) (m_myGUI.getMainFrame().getWidth() * (1 - 0.6) / 2);
    m_yStart = (int) (m_myGUI.getMainFrame().getHeight() * (1 - 0.7) / 2);
    System.out.println(" xS " + m_xStart + " yS " + m_yStart + " myGUI Width " + m_myGUI.getMainFrame().getWidth());
    //double m_tailleTerrain = m_myGUI.getMainFrame().getHeight() * (0.6);
    m_UnityWidth = (int) (m_myGUI.getMainFrame().getWidth() * (1 - 0.4) / m_nbColonnes - m_espace);
    m_UnityHeight = (int) (m_myGUI.getMainFrame().getHeight() * (1 - 0.4) / m_nbLignes - m_espace);

    // m_myMainPanel.setBounds(m_xStart -espace, m_yStart -espace, (m_UnityWidth +
    // espace) * m_nbLignes +espace, (m_UnityHeight + espace) * m_nbColonnes +
    // espace);
    // m_myGUI.getMonde().DesordreComplet();
    ;

    for (int i = 0; i < m_allSquares.length; i++) {
      for (int j = 0; j < m_allSquares[i].length; j++) {
        m_allSquares[i][j] = new JPanel();
        m_allSquares[i][j].setBackground(Color.gray);
        // m_allSquares[i][j].setBackground(new Color(167, 179, 186));

        m_allSquares[i][j].setBounds(m_xStart + (m_UnityWidth + m_espace) * j,
            m_yStart + (m_UnityHeight + m_espace) * i, m_UnityWidth, m_UnityHeight);
        // m_myMainPanel.add(m_allSquares[i][j]);
        m_allSquares[i][j].setVisible(true);
        m_lay[i][j] = new JLayeredPane();
        m_lay[i][j].setLayout(new BorderLayout());
        m_lay[i][j].setBounds(0, 0, m_UnityWidth, m_UnityHeight);
        addIconToSquareGras(i, j);
        m_allSquares[i][j].setLayout((LayoutManager) new OverlayLayout(m_allSquares[i][j]));
        // m_lay[i][j].add(m_grasLabel[i][j]);
        // m_grasLabel[i][j].setVisible(true);

        m_allSquares[i][j].add(m_lay[i][j]);

        m_lay[i][j].setVisible(true);

        m_myGUI.getMainFrame().add(m_allSquares[i][j]);
        // System.out.println("Je suis dans la boucle");
      }
    }
  }

  /*
   * public JPanel getMyMainPanel() {
   * return m_myMainPanel;
   * }
   */
  public JPanel getAllSquareIndexed(int i, int j) {
    return m_allSquares[i][j];
  }

  public void addRobotIconToSquare(int i, int j, Robot robot) {

    m_allSquares[i][j].setLayout((LayoutManager) new OverlayLayout(m_allSquares[i][j]));
    m_lay[i][j].add(robot.getRobotImg());
    m_lay[i][j].moveToFront(robot.getRobotImg());
    m_allSquares[i][j].add(m_lay[i][j]);

  }

  public void addIconToSquareGras(int i, int j) {

    try {
      BufferedImage grasImg;
      grasImg = ImageIO.read(getClass().getResource("gras.png"));
      Image tmp = grasImg.getScaledInstance(m_UnityWidth, m_UnityHeight, Image.SCALE_SMOOTH);
      m_grasIcon[i][j] = new ImageIcon(tmp);
      m_grasLabel[i][j] = new JLabel(m_grasIcon[i][j]);

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}