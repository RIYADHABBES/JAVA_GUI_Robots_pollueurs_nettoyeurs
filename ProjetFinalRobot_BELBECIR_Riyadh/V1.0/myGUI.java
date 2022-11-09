
import javax.swing.plaf.ColorUIResource;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class myGUI {
    private Dimension m_size = Toolkit.getDefaultToolkit().getScreenSize();

    private JFrame m_mainFrame;

    private int m_largeur = (int) (m_size.getWidth() * 80.0 / 100.0);
    private int m_longueur = (int) (m_size.getHeight() * 80.0 / 100.0);
    private int m_echelle = 10;
    private String m_nomGUI = "Ultimate Robots :)";
    private Monde m_monde = new Monde(10, 10, this);
    private DessineTerrain m_terrain;
    private ColorUIResource m_bgFrameColor = new ColorUIResource(20, 21, 24);
    private JPanel m_panelHead;
    private JPanel m_panelRight;
    private JPanel m_panelLeft;
    private JPanel m_panelFoot;
    private JPanel m_panelFootCenter;
    private JPanel m_panelFootHead;
    private JPanel m_mainPanel;

    private JButton m_b1;
    private JButton m_b2;
    private JButton m_b3;
    private JButton m_b4;
    private JButton m_b5;
    private JButton m_b6;
    private JButton m_b7;
    private JButton m_b8;
    private JButton m_b9;
    private JButton m_b10;
    private JButton m_b11;
    private JButton m_b12;
    private JButton m_b13;
    private JButton m_b14;
    private JButton m_forResised = new JButton("I'm Resized");
    private JButton m_smile;
    private JButton m_date = new JButton("February - 2022");
    private JButton m_mail = new JButton("noufelbelbecir@gmail.com");
    private JButton m_rights = new JButton("all rights aren't reserved " + Character.toString(169));
    private JButton m_quiz = new JButton("Quick Quiz");
    private JButton m_instruction;
    private JButton m_nbPapiersGras = new JButton();
    private JButton m_vRPL = new JButton("Free Polluter Speed");
    private JButton m_howMove = new JButton("How I Move");
    private JButton m_question = new JButton();
    private JButton m_choix1 = new JButton();
    private JButton m_choix2 = new JButton();
    private JButton m_score = new JButton();

    public class AllQuestionsAndAnsewers {
        public ArrayList<String> questions, corrAns, wroAns;

        public ArrayList<String> getQuestions() {
            return questions;
        }

        public ArrayList<String> getCorrAns() {
            return corrAns;
        }

        public ArrayList<String> getWroAns() {
            return wroAns;
        }

        AllQuestionsAndAnsewers() {
            questions = new ArrayList<String>(5);
            questions.add("C++ inventor is:");
            questions.add("Enigma code cracker:");
            questions.add("WWW inventor is:");
            questions.add("Java inventor is:");
            questions.add("RDBMS inventor is:");
            corrAns = new ArrayList<String>(5);
            corrAns.add("Bjarne Stroustrup");
            corrAns.add("Alan Turing");
            corrAns.add("Tim Berners-Lee");
            corrAns.add("James Gosling");
            corrAns.add("Edgar Frank Codd");

            wroAns = new ArrayList<String>(5);
            wroAns.add("Dennis M. Ritchie");
            wroAns.add("George Boole");
            wroAns.add("Robert Kahn");
            wroAns.add("Guido van Rossum");
            wroAns.add("Carlo Strozzi");

        }
    }

    private AllQuestionsAndAnsewers m_QaA = new AllQuestionsAndAnsewers();

    public AllQuestionsAndAnsewers getQaA() {
        return m_QaA;
    }

    public JButton getSmile() {
        return m_smile;
    }

    public JButton getDate() {
        return m_date;
    }

    public JButton getScore() {
        return m_score;
    }

    public JButton getQuestion() {
        return m_question;
    }

    public JButton getChoix1() {
        return m_choix1;
    }

    public JButton getChoix2() {
        return m_choix2;
    }

    public JButton getQuiz() {
        return m_quiz;
    }

    public JButton getResised() {
        return m_forResised;
    }

    public JButton getRights() {
        return m_rights;
    }

    public JButton getMail() {
        return m_mail;
    }

    public JButton getHowMove() {
        return m_howMove;
    }

    public JButton getInstructionImage() {
        return m_instruction;
    }

    public Dimension getSizeFrame() {
        return m_size;
    }

    public void setSizeFrame(Dimension size) {
        m_size = size;
    }

    public JButton getNbPapiersGras() {
        return m_nbPapiersGras;
    }

    public JPanel getPanelHead() {
        return m_panelHead;
    }

    public JPanel getPanelFoot() {
        return m_panelFoot;
    }

    public JPanel getPanelRight() {
        return m_panelRight;
    }

    public JPanel getPanelLeft() {
        return m_panelLeft;
    }

    public JButton getB1() {
        return m_b1;
    }

    public JButton getB2() {
        return m_b2;
    }

    public JButton getB3() {
        return m_b3;
    }

    public JButton getB4() {
        return m_b4;
    }

    public JButton getB5() {
        return m_b5;
    }

    public JButton getB6() {
        return m_b6;
    }

    public JButton getB7() {
        return m_b7;
    }

    public JButton getB8() {
        return m_b8;
    }

    public JButton getB9() {
        return m_b9;
    }

    public JButton getB10() {
        return m_b10;
    }

    public JButton getB11() {
        return m_b11;
    }

    public JButton getB12() {
        return m_b12;
    }

    public JButton getB13() {
        return m_b13;
    }

    public JButton getB14() {
        return m_b14;
    }

    public JButton getVRPL() {
        return m_vRPL;
    }

    public Monde getMonde() {
        return m_monde;
    }

    public ColorUIResource getBgFrameColor() {
        return m_bgFrameColor;
    }

    public JFrame getMainFrame() {
        return m_mainFrame;
    }

    public int getLargeur() {
        return m_largeur;
    }

    public void setLargeur() {
        // m_largeur = ((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() *
        // 80.0 / 100.0));
        m_largeur = m_mainFrame.getWidth();

    }

    public int getLongueur() {
        return m_longueur;
    }

    public void setLongueur() {
        // m_longueur = ((int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() *
        // 80.0 / 100.0));
        m_longueur = m_mainFrame.getHeight();
    }

    public int getEchelle() {
        return m_echelle;
    }

    public String getNomGUI() {
        return m_nomGUI;
    }

    public DessineTerrain getTerrain() {
        return m_terrain;
    }

    public myGUI() {
        System.out.println(m_size + " " + m_size.getWidth() + " " + m_size.getHeight());

        m_mainFrame = new JFrame(m_nomGUI);

        m_nbPapiersGras.setText(String.valueOf(
                "The pollution ratio : " + (100 * m_monde.tasCombienDePapierGras()
                        / (m_monde.getLignes() * m_monde.getColonnes())) + "/"
                        + "100"));
        // m_mainFrame.setMinimumSize(new Dimension(m_largeur * m_echelle, m_longueur *
        // m_echelle));
        // m_mainFrame.setMaximumSize(new Dimension(m_largeur * m_echelle, m_longueur *
        // m_echelle));
        // m_mainFrame.setPreferredSize(new Dimension(m_largeur * m_echelle, m_longueur
        // * m_echelle));
        // m_mainFrame.setMinimumSize(new Dimension(size));
        // m_mainFrame.setMaximumSize(new Dimension(size));
        // m_mainFrame.setPreferredSize(new Dimension(size));

        m_mainFrame.setBounds((int) (m_size.getWidth() * 0.2 / 2.0), (int) (m_size.getHeight() * 0.1 / 2.0), 0, 0);
        m_mainFrame.setMinimumSize(new Dimension(m_largeur, m_longueur));
        m_mainFrame.setMaximumSize(new Dimension(m_largeur, m_longueur));
        m_mainFrame.setPreferredSize(new Dimension(m_largeur, m_longueur));
        m_terrain = new DessineTerrain(this);

        m_panelLeft = new JPanel();
        m_panelLeft.setLayout(null);
        m_panelRight = new JPanel();
        m_panelRight.setLayout(null);
        m_panelHead = new JPanel();
        // m_panelFoot = new JPanel(new GridLayout(5, 2));
        m_panelFoot = new JPanel(new BorderLayout());
        GridLayout panelFootCenterlayout = new GridLayout(5, 2);
        GridLayout panelFootHeadlayout = new GridLayout(1, 2);
        Color colorMain = new Color(33, 110, 124);
        Color colorText = new Color(223, 228, 222);
        panelFootCenterlayout.setHgap(25);
        panelFootCenterlayout.setVgap(2);

        m_panelFootCenter = new JPanel(panelFootCenterlayout);
        m_panelFootHead = new JPanel(panelFootHeadlayout);

        m_panelFoot.add(m_panelFootCenter, BorderLayout.CENTER);
        m_panelFoot.add(m_panelFootHead, BorderLayout.NORTH);
        JLabel ajoutRobot = new JLabel("   ADD A ROBOT");
        JLabel supprimeRobot = new JLabel("             DELETE A ROBOT");
        ajoutRobot.setForeground(colorText);
        supprimeRobot.setForeground(colorText);
        ajoutRobot.setHorizontalAlignment(JLabel.CENTER);
        supprimeRobot.setHorizontalAlignment(JLabel.CENTER);

        m_panelFootHead.setBackground(colorMain);
        m_panelFootHead.add(ajoutRobot);
        m_panelFootHead.add(supprimeRobot);

        m_mainPanel = new JPanel();
        // m_panelLeft.setBackground(new Color(29, 60, 88));
        // 255, 110, 65
        m_mainPanel.setBackground(Color.ORANGE);
        // m_mainPanel.setBackground(new Color(253, 184, 57));
        m_panelLeft.setBackground(colorMain);
        m_panelRight.setBackground(colorMain);
        m_panelHead.setBackground(colorMain);
        m_panelFoot.setBackground(colorMain);
        m_panelFootCenter.setBackground(colorMain);

        m_panelLeft.setBounds(0, 0, (int) (m_largeur * 0.2 - m_terrain.getEspace()), (int) (m_longueur));

        // m_panelRight.setBackground(new Color(255, 110, 65));
        m_panelRight.setBounds(
                (int) (m_largeur * 0.8 - m_monde.getColonnes() / 4.0 * m_terrain.getEspace() + m_terrain.getEspace()),
                0, (int) (m_largeur * 0.2), m_longueur);

        // m_panelHead.setBackground(new Color(29, 60, 88));
        m_panelHead.setBounds((int) (m_largeur * 0.2 - m_terrain.getEspace()), 0, (int) (m_largeur * 0.65),
                (int) (m_longueur * 0.15 - m_terrain.getEspace()));

        m_panelFoot.setBounds((int) (m_largeur * 0.2 - m_terrain.getEspace()),
                (int) ((m_longueur * 0.75) - m_terrain.getEspace()),
                (int) (m_largeur * 0.60), (int) (m_longueur * 0.2));

        m_mainPanel.setBounds(0, 0, (int) m_size.getWidth(), (int) m_size.getHeight() + 100);
        // m_mainPanel.setBackground(new Color(253, 184, 57));

        m_mainFrame.add(m_panelLeft);
        m_mainFrame.add(m_panelRight);
        m_mainFrame.add(m_panelHead);
        m_mainFrame.add(m_panelFoot);

        m_mainPanel.setVisible(true);
        m_mainFrame.add(m_mainPanel);

        m_mainFrame.setBackground(m_bgFrameColor);
        ImageIcon myIcon = new ImageIcon("robotIcon.png");
        m_mainFrame.setIconImage(myIcon.getImage());
        // m_mainFrame.setResizable(false);
        m_mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m_b1 = new JButton("CLOSE");

        m_b2 = new JButton("CLEAN THE ARENA");

        m_b3 = new JButton(" Vertical Polluter  ");
        m_b4 = new JButton(" Vertical Polluter  ");

        m_b5 = new JButton(" Jumper Polluter  ");
        m_b6 = new JButton(" Jumper Polluter  ");

        m_b7 = new JButton("    Free  Polluter     ");
        m_b8 = new JButton("    Free  Polluter     ");

        m_b9 = new JButton("Horizontal Cleaner");
        m_b10 = new JButton("Horizontal Cleaner");

        m_b11 = new JButton(" Around Cleaner    ");
        m_b12 = new JButton(" Around Cleaner    ");

        m_b13 = new JButton("   +   ");
        m_b14 = new JButton("   -   ");

        BufferedImage instructionIcon;
        BufferedImage smileIcon;
        try {
            instructionIcon = ImageIO.read(getClass().getResource("howMove.jpg"));
            Image tmp = instructionIcon.getScaledInstance((int) (m_panelRight.getWidth() * 0.8),
                    (int) (m_panelRight.getHeight() * 0.6), Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(tmp);
            m_instruction = new JButton(icon);
            smileIcon = ImageIO.read(getClass().getResource("smile.png"));
            Image tmp2 = smileIcon.getScaledInstance((int) (m_panelLeft.getWidth() * 0.8),
                    (int) (m_panelLeft.getHeight() * 0.06), Image.SCALE_SMOOTH);
            ImageIcon icon2 = new ImageIcon(tmp2);
            m_smile = new JButton(icon2);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // m_b10.setFont(new Font("Dialog", Font.BOLD, 10));
        m_panelLeft.add(m_b2);
        m_panelLeft.add(m_vRPL);
        m_panelLeft.add(m_b13);
        m_panelLeft.add(m_b14);
        m_b1.setBounds((int) (m_panelRight.getWidth() * 0.1), (int) (m_panelRight.getHeight() * 0.01),
                (int) (m_panelRight.getWidth() * 0.82), (int) (m_panelRight.getHeight() * 0.06));

        m_b2.setBounds((int) (m_panelLeft.getWidth() * 0.10), (int) (m_panelLeft.getHeight() * 0.01),
                (int) (m_panelLeft.getWidth() * 0.84), (int) (m_panelLeft.getHeight() * 0.06));
        m_b13.setBounds((int) (m_panelLeft.getWidth() * 0.1), (int) (m_panelLeft.getHeight() * 0.2),
                (int) (m_panelLeft.getWidth() * 0.4), (int) (m_panelLeft.getHeight() * 0.06));
        m_b14.setBounds((int) (m_panelLeft.getWidth() * 0.55), (int) (m_panelLeft.getHeight() * 0.2),
                (int) (m_panelLeft.getWidth() * 0.4), (int) (m_panelLeft.getHeight() * 0.06));
        m_vRPL.setBounds((int) (m_panelLeft.getWidth() * 0.08), (int) (m_panelLeft.getHeight() * 0.15),
                (int) (m_panelLeft.getWidth() * 0.9), (int) (m_panelLeft.getHeight() * 0.06));
        m_instruction.setBounds((int) (m_panelRight.getWidth() * 0.1), (int) (m_panelRight.getHeight() * 0.14),
                (int) (m_panelRight.getWidth() * 0.8), (int) (m_panelRight.getHeight() * 0.6));

        m_smile.setBounds((int) (m_panelLeft.getWidth() * 0.1), (int) (m_panelLeft.getHeight() * 0.28),
                (int) (m_panelLeft.getWidth() * 0.8), (int) (m_panelLeft.getHeight() * 0.06));

        m_forResised
                .setText("Window Size: " + String.valueOf(getLargeur()) + " x " + String.valueOf(getLongueur())
                        + " Pxs");
        m_forResised.setBounds((int) (m_panelLeft.getWidth() * 0.04), (int) (m_panelLeft.getHeight() * 0.84),
                (int) (m_panelLeft.getWidth() * 0.9), (int) (m_panelLeft.getHeight() * 0.06));

        m_mail.setBounds((int) (m_panelLeft.getWidth() * 0.05), (int) (m_panelLeft.getHeight() * 0.82),
                (int) (m_panelLeft.getWidth() * 0.88), (int) (m_panelLeft.getHeight() * 0.06));

        m_rights.setBounds((int) (m_panelLeft.getWidth() * 0.05), (int) (m_panelLeft.getHeight() * 0.86),
                (int) (m_panelLeft.getWidth() * 0.88), (int) (m_panelLeft.getHeight() * 0.06));

        m_date.setBounds((int) (m_panelLeft.getWidth() * 0.05), (int) (m_panelLeft.getHeight() * 0.88),
                (int) (m_panelLeft.getWidth() * 0.88), (int) (m_panelLeft.getHeight() * 0.06));

        m_howMove.setBounds((int) (m_panelRight.getWidth() * 0.1), (int) (m_panelRight.getHeight() * 0.855),
                (int) (m_panelRight.getWidth() * 0.82), (int) (m_panelRight.getHeight() * 0.06));

        m_quiz.setBounds((int) (m_panelLeft.getWidth() * 0.10), (int) (m_panelLeft.getHeight() * 0.68),
                (int) (m_panelLeft.getWidth() * 0.84), (int) (m_panelLeft.getHeight() * 0.06));

        m_question.setBounds((int) (m_panelLeft.getWidth() * 0.10), (int) (m_panelLeft.getHeight() * 0.44),
                (int) (m_panelLeft.getWidth() * 0.84), (int) (m_panelLeft.getHeight() * 0.06));

        m_choix1.setBounds((int) (m_panelLeft.getWidth() * 0.10), (int) (m_panelLeft.getHeight() * 0.53),
                (int) (m_panelLeft.getWidth() * 0.84), (int) (m_panelLeft.getHeight() * 0.06));
        m_choix2.setBounds((int) (m_panelLeft.getWidth() * 0.10), (int) (m_panelLeft.getHeight() * 0.6),
                (int) (m_panelLeft.getWidth() * 0.84), (int) (m_panelLeft.getHeight() * 0.06));

        m_score.setBounds((int) (m_panelLeft.getWidth() * 0.10), (int) (m_panelLeft.getHeight() * 0.43),
                (int) (m_panelLeft.getWidth() * 0.84), (int) (m_panelLeft.getHeight() * 0.16));

        m_vRPL.setLayout(null);
        m_vRPL.setBorderPainted(false);
        m_vRPL.setFocusPainted(false);
        m_vRPL.setContentAreaFilled(false);
        m_nbPapiersGras.setLayout(null);
        m_nbPapiersGras.setBorderPainted(false);
        // m_nbPapiersGras.setFocusPainted(false);
        // m_nbPapiersGras.setContentAreaFilled(false);
        m_forResised.setLayout(null);
        m_forResised.setBorderPainted(false);
        m_forResised.setFocusPainted(false);
        m_forResised.setContentAreaFilled(false);

        m_date.setLayout(null);
        m_date.setBorderPainted(false);
        m_date.setFocusPainted(false);
        m_date.setContentAreaFilled(false);

        m_mail.setLayout(null);
        m_mail.setBorderPainted(false);
        m_mail.setFocusPainted(false);
        m_mail.setContentAreaFilled(false);

        m_rights.setLayout(null);
        m_rights.setBorderPainted(false);
        m_rights.setFocusPainted(false);
        m_rights.setContentAreaFilled(false);

        m_instruction.setLayout(null);
        m_instruction.setBorderPainted(false);
        m_instruction.setFocusPainted(false);
        m_instruction.setContentAreaFilled(false);
        m_instruction.setVisible(false);

        m_smile.setLayout(null);
        m_smile.setBorderPainted(false);
        m_smile.setFocusPainted(false);
        m_smile.setContentAreaFilled(false);
        m_smile.setVisible(true);
        // m_forResised.setVisible(true);

        m_vRPL.setForeground(new Color(255, 255, 255));
        m_nbPapiersGras.setBackground(new Color(23, 102, 76));
        m_nbPapiersGras.setForeground(Color.WHITE);
        m_nbPapiersGras.setFont(new Font("Dialog", Font.BOLD, 20));
        m_vRPL.setFont(new Font("Dialog", Font.BOLD, 12));
        m_b13.setFont(new Font("Dialog", Font.BOLD, 16));
        m_b14.setFont(new Font("Dialog", Font.BOLD, 16));
        m_quiz.setFont(new Font("Dialog", Font.BOLD, 16));
        m_choix1.setFont(new Font("Dialog", Font.BOLD, 14));
        m_choix2.setFont(new Font("Dialog", Font.BOLD, 14));
        m_question.setFont(new Font("Dialog", Font.BOLD, 13));
        m_forResised.setFont(new Font("Dialog", Font.BOLD, 10));
        m_date.setFont(new Font("Dialog", Font.BOLD, 10));
        m_panelFoot.add(new JLabel(" "), BorderLayout.EAST);
        m_panelFoot.add(new JLabel(" "), BorderLayout.WEST);
        // m_panelFoot.add(new JLabel(" "), BorderLayout.NORTH);
        m_panelFoot.add(new JLabel(" "), BorderLayout.SOUTH);

        Color bouttonColor = new Color(239, 84, 84);

        m_b1.setBackground(bouttonColor);
        m_b2.setBackground(bouttonColor);
        m_b3.setBackground(bouttonColor);
        m_b4.setBackground(bouttonColor);
        m_b5.setBackground(bouttonColor);
        m_b6.setBackground(bouttonColor);
        m_b7.setBackground(bouttonColor);
        m_b8.setBackground(bouttonColor);
        m_b9.setBackground(bouttonColor);
        m_b10.setBackground(bouttonColor);
        m_b11.setBackground(bouttonColor);
        m_b12.setBackground(bouttonColor);
        m_b13.setBackground(bouttonColor);
        m_b14.setBackground(bouttonColor);
        m_quiz.setBackground(bouttonColor);
        m_howMove.setBackground(bouttonColor);
        m_question.setBackground(bouttonColor);
        m_choix1.setBackground(bouttonColor);
        m_choix2.setBackground(bouttonColor);
        m_score.setBackground(bouttonColor);
        m_score.setBackground(bouttonColor);

        m_b1.setBorderPainted(false);
        m_b1.setFocusPainted(false);
        m_b2.setBorderPainted(false);
        m_b2.setFocusPainted(false);
        m_b3.setBorderPainted(false);
        m_b3.setFocusPainted(false);
        m_b4.setBorderPainted(false);
        m_b4.setFocusPainted(false);
        m_b5.setBorderPainted(false);
        m_b5.setFocusPainted(false);
        m_b6.setBorderPainted(false);
        m_b6.setFocusPainted(false);
        m_b7.setBorderPainted(false);
        m_b7.setFocusPainted(false);
        m_b8.setBorderPainted(false);
        m_b8.setFocusPainted(false);
        m_b9.setBorderPainted(false);
        m_b9.setFocusPainted(false);
        m_b10.setBorderPainted(false);
        m_b10.setFocusPainted(false);
        m_b11.setBorderPainted(false);
        m_b11.setFocusPainted(false);
        m_b12.setBorderPainted(false);
        m_b12.setFocusPainted(false);
        m_b13.setFocusPainted(false);
        m_b13.setBorderPainted(false);
        m_b14.setFocusPainted(false);
        m_b14.setBorderPainted(false);
        m_quiz.setFocusPainted(false);
        m_quiz.setBorderPainted(false);
        m_howMove.setFocusPainted(false);
        m_howMove.setBorderPainted(false);
        m_nbPapiersGras.setFocusPainted(false);
        m_nbPapiersGras.setBorderPainted(false);
        m_rights.setFocusPainted(false);
        m_rights.setBorderPainted(false);
        m_date.setFocusPainted(false);
        m_date.setBorderPainted(false);
        m_question.setFocusPainted(false);
        m_question.setBorderPainted(false);
        m_choix1.setFocusPainted(false);
        m_choix1.setBorderPainted(false);
        m_choix2.setFocusPainted(false);
        m_choix2.setBorderPainted(false);
        m_score.setFocusPainted(false);
        m_score.setBorderPainted(false);

        m_question.setVisible(false);
        m_choix1.setVisible(false);
        m_choix2.setVisible(false);
        m_score.setVisible(false);

        m_panelRight.add(m_b1);
        m_panelRight.add(m_howMove);
        m_panelRight.add(m_instruction);
        m_panelLeft.add(m_smile);
        m_panelLeft.add(m_score);
        m_panelLeft.add(m_choix1);
        m_panelLeft.add(m_choix2);
        m_panelLeft.add(m_question);
        m_panelLeft.add(m_quiz);
        m_panelLeft.add(m_date);
        m_panelLeft.add(m_forResised);
        m_panelLeft.add(m_rights);
        m_panelLeft.add(m_mail);
        m_panelFootCenter.add(m_b3);
        m_panelFootCenter.add(m_b4);
        m_panelFootCenter.add(m_b5);
        m_panelFootCenter.add(m_b6);
        m_panelFootCenter.add(m_b7);
        m_panelFootCenter.add(m_b8);
        m_panelFootCenter.add(m_b9);
        m_panelFootCenter.add(m_b10);
        m_panelFootCenter.add(m_b11);
        m_panelFootCenter.add(m_b12);

        m_panelHead.add(m_nbPapiersGras);
               
        
 try {
            BufferedImage rnh, rna, rpv, rps, rpl;
            rnh = ImageIO.read(getClass().getResource("rnh.png"));
            rna = ImageIO.read(getClass().getResource("rna.png"));
            rpv = ImageIO.read(getClass().getResource("rpv.png"));
            rps = ImageIO.read(getClass().getResource("rps.png"));
            rpl = ImageIO.read(getClass().getResource("rpl.png"));
            Image tmp = rnh.getScaledInstance((int) (m_mainFrame.getHeight() * 0.05),
                    (int) (m_mainFrame.getHeight() * 0.03),
                    Image.SCALE_SMOOTH);
            ImageIcon rnhIcon = new ImageIcon(tmp);
            m_b10.setIcon(rnhIcon);
            m_b9.setIcon(rnhIcon);
            tmp = rna.getScaledInstance((int) (m_mainFrame.getHeight() * 0.05), (int) (m_mainFrame.getHeight() * 0.03),
                    Image.SCALE_SMOOTH);
            ImageIcon rnaIcon = new ImageIcon(tmp);
            m_b11.setIcon(rnaIcon);
            m_b12.setIcon(rnaIcon);
            tmp = rpv.getScaledInstance((int) (m_mainFrame.getHeight() * 0.05), (int) (m_mainFrame.getHeight() * 0.03),
                    Image.SCALE_SMOOTH);
            ImageIcon rpvIcon = new ImageIcon(tmp);
            m_b3.setIcon(rpvIcon);
            m_b4.setIcon(rpvIcon);
            tmp = rps.getScaledInstance((int) (m_mainFrame.getHeight() * 0.05), (int) (m_mainFrame.getHeight() * 0.03),
                    Image.SCALE_SMOOTH);
            ImageIcon rpsIcon = new ImageIcon(tmp);
            m_b5.setIcon(rpsIcon);
            m_b6.setIcon(rpsIcon);
            tmp = rpl.getScaledInstance((int) (m_mainFrame.getHeight() * 0.05), (int) (m_mainFrame.getHeight() * 0.03),
                    Image.SCALE_SMOOTH);
            ImageIcon rplIcon = new ImageIcon(tmp);
            m_b7.setIcon(rplIcon);
            m_b8.setIcon(rplIcon);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        m_mainFrame.setVisible(true);

    }

}