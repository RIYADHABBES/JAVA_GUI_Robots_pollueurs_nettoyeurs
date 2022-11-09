
import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainClass {
    myGUI GUIforCompilationOnly;// = new myGUI();
    DessineTerrain DforCompilationOnly;// = new DessineTerrain(GUIforCompilationOnly);
    Robot RFC;
    static myGUI g = new myGUI();
    static Monde monde = g.getMonde();
    static int vRPL = 1000;
    static Timer timerRPL;
    static boolean howMoveFlag = false;
    static int howMoveFlagI = 0;
    static int evilFlag = 0;
    static ArrayList<Integer> qIndexes = new ArrayList<Integer>(5);

    static RobotPollueurToutDroit RPD = new RobotPollueurToutDroit(monde, g.getTerrain());
    // static RobotPollueurToutDroit RPD2 = new RobotPollueurToutDroit(monde,
    // g.getTerrain());
    static RobotNettoyeurAutour RNA = new RobotNettoyeurAutour(monde, g.getTerrain());
    // static RobotNettoyeurAutour RNA2 = new RobotNettoyeurAutour(monde,
    // g.getTerrain());
    static RobotPollueurSauteur RPS = new RobotPollueurSauteur(monde,
            g.getTerrain());
    static RobotPollueurLibre RPL = new RobotPollueurLibre(monde, g.getTerrain());
    static RobotNettoyeurHorizon RNH = new RobotNettoyeurHorizon(monde,
            g.getTerrain());
    static int quizCounter = 0;
    static int l; // pour associer correcte a choix 1 ou 2
    static int scoreFinal = 0;
    static boolean added[][];
    static int r = (int) (Math.random() * 1000 + 500);

    static int k = 0;

    static void playSound(String soundFile) {
        try {
            File f = new File(soundFile);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            Clip clip;

            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();

        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {

        for (int k = 0; k < 5; k++) {
            qIndexes.add(k);
        }
        Collections.shuffle(qIndexes);
        // monde.getRobotsList().add(RPL);
        // monde.getRobotsList().add(RPD);
        playSound("nice.wav");
        // playSound("iamrobot.wav");
        playSound("thisisnotadream.wav");
        // pour blocker les robots
        /*
         * for (int j = 0; j < monde.getColonnes(); j++) {
         * if (j!= 0) {
         * monde.setRobots(0, j, true);
         * monde.setRobots(j, 0, true);
         * 
         * }
         * }
         */
        added = new boolean[g.getMonde().getLignes()][g.getMonde().getColonnes()];
        for (int i = 0; i < added.length; i++) {
            for (int j = 0; j < added[i].length; j++) {
                added[i][j] = false;

            }
        }

        g.getMainFrame().addComponentListener(new ComponentListener() {
            public void componentResized(ComponentEvent componentEvent) {
                // do stuff
                System.out.println("Resizing");
                g.setSizeFrame(Toolkit.getDefaultToolkit().getScreenSize());
                g.setLargeur();
                g.setLongueur();
                g.getTerrain().setXStart((int) (g.getMainFrame().getWidth() * (1 - 0.6) / 2));
                g.getTerrain().setYStart((int) (g.getMainFrame().getHeight() * (1 - 0.7) / 2));

                g.getTerrain().setUnityWidth((int) (g.getMainFrame().getWidth() * (1 - 0.4) / monde.getColonnes()
                        - g.getTerrain().getEspace()));
                g.getTerrain().setUnityHight((int) (g.getMainFrame().getHeight() * (1 - 0.4) / monde.getLignes()
                        - g.getTerrain().getEspace()));
                for (int i = 0; i < monde.getLignes(); i++) {
                    for (int j = 0; j < monde.getColonnes(); j++) {
                        g.getTerrain().setAllSquares(i, j);
                    }
                }
                g.getPanelLeft().setBounds(0, 0, (int) (g.getLargeur() * 0.2 - g.getTerrain().getEspace()),
                        g.getLongueur());

                g.getPanelRight().setBounds(
                        (int) (g.getLargeur() * 0.8 - g.getMonde().getColonnes() / 4.0 * g.getTerrain().getEspace()
                                + g.getTerrain().getEspace()),
                        0,
                        (int) (g.getLargeur() * 0.2), g.getLongueur());

                g.getPanelHead().setBounds((int) (g.getLargeur() * 0.2 - g.getTerrain().getEspace()), 0,
                        (int) (g.getLargeur() * 0.65),
                        (int) (g.getLongueur() * 0.15) - g.getTerrain().getEspace());

                g.getPanelFoot().setBounds((int) (g.getLargeur() * 0.2) - g.getTerrain().getEspace(),
                        (int) ((g.getLongueur() * 0.75) - g.getTerrain().getEspace()),
                        (int) (g.getLargeur() * 0.60), (int) (g.getLongueur() * 0.2 + g.getTerrain().getEspace()));

                // pa.setBounds(0, 0, (int) m_size.getWidth(), (int) m_size.getHeight());
                // pa.setBackground(Color.ORANGE);
                g.getB1().setBounds((int) (g.getPanelRight().getWidth() * 0.1),
                        (int) (g.getPanelRight().getHeight() * 0.01),
                        (int) (g.getPanelRight().getWidth() * 0.82), (int) (g.getPanelRight().getHeight() * 0.06));
                g.getB2().setBounds((int) (g.getPanelLeft().getWidth() * 0.10),
                        (int) (g.getPanelLeft().getHeight() * 0.01),
                        (int) (g.getPanelLeft().getWidth() * 0.84), (int) (g.getPanelLeft().getHeight() * 0.06));
                g.getB13().setBounds((int) (g.getPanelLeft().getWidth() * 0.1),
                        (int) (g.getPanelLeft().getHeight() * 0.2),
                        (int) (g.getPanelLeft().getWidth() * 0.4), (int) (g.getPanelLeft().getHeight() * 0.06));
                g.getB14().setBounds((int) (g.getPanelLeft().getWidth() * 0.55),
                        (int) (g.getPanelLeft().getHeight() * 0.2),
                        (int) (g.getPanelLeft().getWidth() * 0.4), (int) (g.getPanelLeft().getHeight() * 0.06));
                g.getVRPL().setBounds((int) (g.getPanelLeft().getWidth() * 0.08),
                        (int) (g.getPanelLeft().getHeight() * 0.15),
                        (int) (g.getPanelLeft().getWidth() * 0.9), (int) (g.getPanelLeft().getHeight() * 0.06));

                g.getHowMove().setBounds((int) (g.getPanelRight().getWidth() * 0.1),
                        (int) (g.getPanelRight().getHeight() * 0.859),
                        (int) (g.getPanelRight().getWidth() * 0.82), (int) (g.getPanelRight().getHeight() * 0.06));

                g.getQuestion().setBounds((int) (g.getPanelLeft().getWidth() * 0.10),
                        (int) (g.getPanelLeft().getHeight() * 0.44),
                        (int) (g.getPanelLeft().getWidth() * 0.84), (int) (g.getPanelLeft().getHeight() * 0.06));

                g.getChoix1().setBounds((int) (g.getPanelLeft().getWidth() * 0.10),
                        (int) (g.getPanelLeft().getHeight() * 0.53),
                        (int) (g.getPanelLeft().getWidth() * 0.84), (int) (g.getPanelLeft().getHeight() * 0.06));
                g.getChoix2().setBounds((int) (g.getPanelLeft().getWidth() * 0.10),
                        (int) (g.getPanelLeft().getHeight() * 0.6),
                        (int) (g.getPanelLeft().getWidth() * 0.84), (int) (g.getPanelLeft().getHeight() * 0.06));

                g.getScore().setBounds((int) (g.getPanelLeft().getWidth() * 0.10),
                        (int) (g.getPanelLeft().getHeight() * 0.43),
                        (int) (g.getPanelLeft().getWidth() * 0.84), (int) (g.getPanelLeft().getHeight() * 0.16));

                BufferedImage instructionIcon;
                BufferedImage smileIcon;
                try {
                    instructionIcon = ImageIO.read(getClass().getResource("howMove.jpg"));
                    Image tmp = instructionIcon.getScaledInstance((int) (g.getPanelRight().getWidth() * 0.8),
                            (int) (g.getPanelRight().getHeight() * 0.6), Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(tmp);
                    g.getInstructionImage().setIcon(icon);
                    smileIcon = ImageIO.read(getClass().getResource("evil.png"));
                    Image tmp2 = smileIcon.getScaledInstance((int) (g.getPanelLeft().getWidth() * 0.8),
                            (int) (g.getPanelLeft().getHeight() * 0.06), Image.SCALE_SMOOTH);
                    ImageIcon icon2 = new ImageIcon(tmp2);
                    g.getSmile().setIcon(icon2);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                g.getInstructionImage().setBounds((int) (g.getPanelRight().getWidth() * 0.1),
                        (int) (g.getPanelRight().getHeight() * 0.14),
                        (int) (g.getPanelRight().getWidth() * 0.8), (int) (g.getPanelRight().getHeight() * 0.6));

                g.getSmile().setBounds((int) (g.getPanelLeft().getWidth() * 0.1),
                        (int) (g.getPanelLeft().getHeight() * 0.28),
                        (int) (g.getPanelLeft().getWidth() * 0.8), (int) (g.getPanelLeft().getHeight() * 0.06));

                for (int i = 0; i < g.getMonde().getLignes(); i++) {
                    for (int j = 0; j < g.getMonde().getColonnes(); j++) {

                        g.getTerrain().getAllSquareIndexed(i, j).removeAll();
                        g.getTerrain().getLayer(i, j).removeAll();
                    }
                }
                for (int i = 1; i <= monde.getListRNA().size(); i++) {

                    monde.getListRNA().get(i - 1).setRobotImg("rna.png");
                    g.getTerrain().addRobotIconToSquare(monde.getListRNA().get(i - 1).getPosX(),
                            monde.getListRNA().get(i - 1).getPosY(), monde.getListRNA().get(i - 1));

                }

                for (int i = 1; i <= monde.getListRNH().size(); i++) {

                    monde.getListRNH().get(i - 1).setRobotImg("rnh.png");
                    g.getTerrain().addRobotIconToSquare(monde.getListRNH().get(i - 1).getPosX(),
                            monde.getListRNH().get(i - 1).getPosY(), monde.getListRNH().get(i - 1));

                }

                for (int i = 1; i <= monde.getListRPS().size(); i++) {

                    monde.getListRPS().get(i - 1).setRobotImg("rps.png");
                    g.getTerrain().addRobotIconToSquare(monde.getListRPS().get(i - 1).getPosX(),
                            monde.getListRPS().get(i - 1).getPosY(), monde.getListRPS().get(i - 1));

                }

                for (int i = 1; i <= monde.getListRPV().size(); i++) {

                    monde.getListRPV().get(i - 1).setRobotImg("rpv.png");
                    g.getTerrain().addRobotIconToSquare(monde.getListRPV().get(i - 1).getPosX(),
                            monde.getListRPV().get(i - 1).getPosY(), monde.getListRPV().get(i - 1));
                }

                for (int i = 1; i <= monde.getListRPL().size(); i++) {

                    monde.getListRPL().get(i - 1).setRobotImg("rpl.png");
                    g.getTerrain().addRobotIconToSquare(monde.getListRPL().get(i - 1).getPosX(),
                            monde.getListRPL().get(i - 1).getPosY(), monde.getListRPL().get(i - 1));

                }

                g.getResised().setText("Window Size: " + String.valueOf(g.getLargeur()) + " x "
                        + String.valueOf(g.getLongueur()) + " Pxs");
                g.getResised().setBounds((int) (g.getPanelLeft().getWidth() * 0.04),
                        (int) (g.getPanelLeft().getHeight() * 0.84),
                        (int) (g.getPanelLeft().getWidth() * 0.9), (int) (g.getPanelLeft().getHeight() * 0.06));

                g.getMail().setBounds((int) (g.getPanelLeft().getWidth() * 0.05),
                        (int) (g.getPanelLeft().getHeight() * 0.82),
                        (int) (g.getPanelLeft().getWidth() * 0.88), (int) (g.getPanelLeft().getHeight() * 0.06));

                g.getQuiz().setBounds((int) (g.getPanelLeft().getWidth() * 0.10),
                        (int) (g.getPanelLeft().getHeight() * 0.68),
                        (int) (g.getPanelLeft().getWidth() * 0.84), (int) (g.getPanelLeft().getHeight() * 0.06));

                g.getRights().setBounds((int) (g.getPanelLeft().getWidth() * 0.05),
                        (int) (g.getPanelLeft().getHeight() * 0.86),
                        (int) (g.getPanelLeft().getWidth() * 0.88), (int) (g.getPanelLeft().getHeight() * 0.06));

                g.getDate().setBounds((int) (g.getPanelLeft().getWidth() * 0.05),
                        (int) (g.getPanelLeft().getHeight() * 0.88),
                        (int) (g.getPanelLeft().getWidth() * 0.88), (int) (g.getPanelLeft().getHeight() * 0.06));

                g.getMainFrame().revalidate();
                g.getMainFrame().repaint();

            }

            @Override
            public void componentMoved(ComponentEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void componentShown(ComponentEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void componentHidden(ComponentEvent e) {
                // TODO Auto-generated method stub

            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                System.out.println("Go");
                g.getB1().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        g.getMainFrame().dispose();
                        System.exit(0);
                    }
                });

                g.getHowMove().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (howMoveFlag == false) {
                            g.getInstructionImage().setVisible(true);
                            howMoveFlag = true;

                            g.getHowMove().setText("Hide My Move");
                        } else {
                            g.getInstructionImage().setVisible(false);
                            howMoveFlag = false;
                            g.getHowMove().setText("How I Move");

                        }
                    }
                });
                g.getB2().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        g.getMonde().nettoyageComplet();
                        for (int jk = 0; jk < g.getMonde().getColonnes(); jk++) {
                            for (int ki = 0; ki < g.getMonde().getColonnes(); ki++) {
                                g.getTerrain().getAllSquareIndexed(jk, ki).setBackground(Color.GRAY);
                            }
                        }
                        g.getNbPapiersGras()
                                .setText(String
                                        .valueOf("The pollution ratio : " + (100 * monde.tasCombienDePapierGras()
                                                / (monde.getLignes() * monde.getColonnes())) + "/"
                                                + "100"));

                    }
                });
                int delay = 2000; // milliseconds
                Timer timerB3 = new Timer(delay, new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        g.getB3().setEnabled(true);
                        g.getB3().setText(" Vertical Polluter  ");
                        if (monde.getListRPV().size() == monde.getColonnes() - 5) {
                            g.getB3().setEnabled(false);
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "The maximum number of vertical polluting robots has been reached.");

                        }
                    }
                });
                timerB3.setRepeats(false);
                g.getB3().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (monde.getListRPV().size() < monde.getColonnes() - 5) {
                            new RobotPollueurToutDroit(monde, g.getTerrain());
                            g.getB4().setEnabled(true);

                        }
                        g.getB3().setEnabled(false);
                        g.getB3().setText("Wait a second");
                        timerB3.start();
                    }
                });

                g.getB4().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (monde.getListRPV().size() > 0) {
                            System.out.println(monde.getListRPV().size());

                            g.getTerrain().getAllSquareIndexed(monde.getListRPV().get(0).getPosX(),
                                    monde.getListRPV().get(0).getPosY()).removeAll();
                            g.getTerrain().getLayer(monde.getListRPV().get(0).getPosX(),
                                    monde.getListRPV().get(0).getPosY()).removeAll();
                            monde.setRobots(monde.getListRPV().get(0).getPosX(), monde.getListRPV().get(0).getPosY(),
                                    false);
                            monde.getListRPV().remove(0);
                            g.getMainFrame().repaint();
                            System.out.println(monde.getListRPV().size());
                            g.getB3().setEnabled(true);

                        } else {
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "There are no vertical polluting robots on the arena.");
                            if (monde.getListRPV().size() == 0)
                                g.getB4().setEnabled(false);
                        }
                    }

                });

                Timer timerB5 = new Timer(delay, new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        g.getB5().setEnabled(true);
                        g.getB5().setText(" Jumper Polluter  ");
                        if (monde.getListRPS().size() == monde.getColonnes() - 5) {
                            g.getB5().setEnabled(false);
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "The maximum number of jumping polluting robots has been reached.");

                        }
                    }
                });
                timerB5.setRepeats(false);
                g.getB5().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (monde.getListRPS().size() < monde.getColonnes() - 5) {
                            new RobotPollueurSauteur(monde, g.getTerrain());
                            g.getB6().setEnabled(true);
                        }
                        g.getB5().setEnabled(false);
                        g.getB5().setText("Wait a second");
                        timerB5.start();
                    }
                });

                g.getB6().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (monde.getListRPS().size() > 0) {
                            System.out.println(monde.getListRPS().size());

                            g.getTerrain().getAllSquareIndexed(monde.getListRPS().get(0).getPosX(),
                                    monde.getListRPS().get(0).getPosY()).removeAll();
                            g.getTerrain().getLayer(monde.getListRPS().get(0).getPosX(),
                                    monde.getListRPS().get(0).getPosY()).removeAll();
                            monde.setRobots(monde.getListRPS().get(0).getPosX(), monde.getListRPS().get(0).getPosY(),
                                    false);
                            monde.getListRPS().remove(0);
                            g.getMainFrame().repaint();
                            System.out.println(monde.getListRPS().size());
                            g.getB5().setEnabled(true);
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "There are no jumping polluting robots on the arena.");
                            if (monde.getListRPS().size() == 0)
                                g.getB6().setEnabled(false);
                        }
                    }

                });

                Timer timerB7 = new Timer(delay, new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        g.getB7().setEnabled(true);
                        g.getB7().setText("    Free  Polluter     ");
                        if (monde.getListRPL().size() == monde.getColonnes() - 5) {
                            g.getB7().setEnabled(false);
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "The maximum number of free polluting robots has been reached.");

                        }
                    }
                });
                timerB7.setRepeats(false);
                g.getB7().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (monde.getListRPL().size() < monde.getColonnes() - 5) {
                            new RobotPollueurLibre(monde, g.getTerrain());
                            g.getB8().setEnabled(true);
                        }
                        g.getB7().setEnabled(false);
                        g.getB7().setText("Wait a second");
                        timerB7.start();
                    }
                });

                g.getB8().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (monde.getListRPL().size() > 0) {
                            System.out.println(monde.getListRPL().size());

                            g.getTerrain().getAllSquareIndexed(monde.getListRPL().get(0).getPosX(),
                                    monde.getListRPL().get(0).getPosY()).removeAll();
                            g.getTerrain().getLayer(monde.getListRPL().get(0).getPosX(),
                                    monde.getListRPL().get(0).getPosY()).removeAll();
                            monde.setRobots(monde.getListRPL().get(0).getPosX(), monde.getListRPL().get(0).getPosY(),
                                    false);
                            monde.getListRPL().remove(0);
                            g.getMainFrame().repaint();
                            System.out.println(monde.getListRPL().size());
                            g.getB7().setEnabled(true);

                        } else {
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "There are no free polluting robots on the arena.");
                            if (monde.getListRPL().size() == 0)
                                g.getB8().setEnabled(false);
                        }
                    }

                });

                Timer timerB9 = new Timer(delay, new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        g.getB9().setEnabled(true);
                        g.getB9().setText("Horizontal Cleaner");
                        if (monde.getListRNH().size() == monde.getColonnes() - 5) {
                            g.getB9().setEnabled(false);
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "The maximum number of horizontal cleaning robots has been reached.");

                        }
                    }
                });
                timerB9.setRepeats(false);
                g.getB9().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (monde.getListRNH().size() < monde.getColonnes() - 5) {
                            new RobotNettoyeurHorizon(monde, g.getTerrain());
                            g.getB10().setEnabled(true);
                        }
                        g.getB9().setEnabled(false);
                        g.getB9().setText("Wait a second");
                        timerB9.start();
                    }
                });

                g.getB10().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (monde.getListRNH().size() > 0) {
                            System.out.println(monde.getListRNH().size());

                            g.getTerrain().getAllSquareIndexed(monde.getListRNH().get(0).getPosX(),
                                    monde.getListRNH().get(0).getPosY()).removeAll();
                            g.getTerrain().getLayer(monde.getListRNH().get(0).getPosX(),
                                    monde.getListRNH().get(0).getPosY()).removeAll();
                            monde.setRobots(monde.getListRNH().get(0).getPosX(), monde.getListRNH().get(0).getPosY(),
                                    false);
                            monde.getListRNH().remove(0);
                            g.getMainFrame().repaint();
                            System.out.println(monde.getListRNH().size());
                            g.getB9().setEnabled(true);

                        } else {
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "There are no horizontal cleaning robots on the arena.");
                            if (monde.getListRNH().size() == 0)
                                g.getB10().setEnabled(false);
                        }
                    }

                });

                Timer timerB11 = new Timer(delay, new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        g.getB11().setEnabled(true);
                        g.getB11().setText(" Around Cleaner    ");
                        if (monde.getListRNA().size() == monde.getColonnes() - 5) {
                            g.getB11().setEnabled(false);
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "The maximum number of around cleaning robots has been reached.");

                        }
                    }
                });
                timerB11.setRepeats(false);
                g.getB11().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (monde.getListRNA().size() < monde.getColonnes() - 5) {
                            new RobotNettoyeurAutour(monde, g.getTerrain());
                            g.getB12().setEnabled(true);
                        }
                        g.getB11().setEnabled(false);
                        g.getB11().setText("Wait a second");
                        timerB11.start();
                    }
                });

                g.getB12().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (monde.getListRNA().size() > 0) {
                            System.out.println(monde.getListRNA().size());

                            g.getTerrain().getAllSquareIndexed(monde.getListRNA().get(0).getPosX(),
                                    monde.getListRNA().get(0).getPosY()).removeAll();
                            g.getTerrain().getLayer(monde.getListRNA().get(0).getPosX(),
                                    monde.getListRNA().get(0).getPosY()).removeAll();
                            monde.setRobots(monde.getListRNA().get(0).getPosX(), monde.getListRNA().get(0).getPosY(),
                                    false);

                            monde.getListRNA().remove(0);
                            g.getB11().setEnabled(true);
                            g.getMainFrame().repaint();
                            System.out.println(monde.getListRNA().size());

                        } else {
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "There are no around cleaning robots in the arena.");
                            if (monde.getListRNA().size() == 0)
                                g.getB12().setEnabled(false);
                        }
                    }

                });

                g.getB13().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (vRPL > 500) {
                            vRPL -= 100;
                            g.getB14().setEnabled(true);
                        } else {
                            g.getB13().setEnabled(false);
                        }

                    }
                });

                g.getB14().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (vRPL < 1500) {
                            vRPL += 100;
                            g.getB13().setEnabled(true);
                        } else {
                            g.getB14().setEnabled(false);
                        }

                    }
                });

                g.getSmile().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (evilFlag == 1) {

                            try {
                                BufferedImage smileIcon;
                                smileIcon = ImageIO.read(getClass().getResource("evil.png"));
                                Image tmp2 = smileIcon.getScaledInstance((int) (g.getPanelLeft().getWidth() * 0.8),
                                        (int) (g.getPanelLeft().getHeight() * 0.06), Image.SCALE_SMOOTH);
                                ImageIcon icon2 = new ImageIcon(tmp2);
                                g.getSmile().setIcon(icon2);
                                evilFlag = 0;
                            } catch (IOException e3) {
                                // TODO Auto-generated catch block
                                e3.printStackTrace();
                            }
                        } else {

                            try {
                                BufferedImage smileIcon;
                                smileIcon = ImageIO.read(getClass().getResource("smile.png"));
                                Image tmp2 = smileIcon.getScaledInstance((int) (g.getPanelLeft().getWidth() * 0.8),
                                        (int) (g.getPanelLeft().getHeight() * 0.06), Image.SCALE_SMOOTH);
                                ImageIcon icon2 = new ImageIcon(tmp2);
                                g.getSmile().setIcon(icon2);
                                evilFlag = 1;
                            } catch (IOException e3) {
                                // TODO Auto-generated catch block
                                e3.printStackTrace();
                            }
                        }
                    }
                });

                g.getQuiz().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (quizCounter == 6) {
                            quizCounter = 0;
                        }
                        g.getQuiz().setVisible(false);
                        g.getQuestion().setText(g.getQaA().getQuestions().get(qIndexes.get(0)));
                        l = (int) (Math.random() * 100) % 2;
                        if (l == 0) {
                            g.getChoix1().setText(g.getQaA().getCorrAns().get(qIndexes.get(0)));
                            g.getChoix2().setText(g.getQaA().getWroAns().get(qIndexes.get(0)));
                        } else {
                            g.getChoix2().setText(g.getQaA().getCorrAns().get(qIndexes.get(0)));
                            g.getChoix1().setText(g.getQaA().getWroAns().get(qIndexes.get(0)));
                        }
                        qIndexes.remove(0);
                        g.getQuestion().setVisible(true);
                        g.getChoix1().setVisible(true);
                        g.getChoix2().setVisible(true);
                        quizCounter++;
                    }
                });
                g.getChoix1().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (l == 0) {
                            scoreFinal++;
                        }
                        quizCounter++;
                        if (qIndexes.size() != 0) {

                            g.getQuestion().setText(g.getQaA().getQuestions().get(qIndexes.get(0)));
                            l = (int) (Math.random() * 100) % 2;
                            if (l == 0) {
                                g.getChoix1().setText(g.getQaA().getCorrAns().get(qIndexes.get(0)));
                                g.getChoix2().setText(g.getQaA().getWroAns().get(qIndexes.get(0)));
                            } else {
                                g.getChoix2().setText(g.getQaA().getCorrAns().get(qIndexes.get(0)));
                                g.getChoix1().setText(g.getQaA().getWroAns().get(qIndexes.get(0)));
                            }
                            qIndexes.remove(0);
                        }
                        if (quizCounter == 6) {

                            g.getQuestion().setVisible(false);
                            g.getChoix1().setVisible(false);
                            g.getChoix2().setVisible(false);
                            String affiche = "<html>  <center>  Score " + scoreFinal
                                    + "/5 <br>Tap To Retry</center></html>";
                            if (scoreFinal < 3) {
                                g.getScore().setBackground(Color.red);
                            }
                            if (scoreFinal == 3) {
                                g.getScore().setBackground(Color.gray);
                            }
                            if (scoreFinal > 3) {
                                g.getScore().setBackground(Color.green);
                            }
                            g.getScore().setText(affiche);
                            g.getScore().setVisible(true);

                            quizCounter = 0;
                        }

                    }
                });
                g.getChoix2().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (l == 1) {
                            scoreFinal++;
                        }
                        quizCounter++;
                        if (qIndexes.size() != 0) {

                            g.getQuestion().setText(g.getQaA().getQuestions().get(qIndexes.get(0)));
                            l = (int) (Math.random() * 100) % 2;
                            if (l == 0) {
                                g.getChoix1().setText(g.getQaA().getCorrAns().get(qIndexes.get(0)));
                                g.getChoix2().setText(g.getQaA().getWroAns().get(qIndexes.get(0)));
                            } else {
                                g.getChoix2().setText(g.getQaA().getCorrAns().get(qIndexes.get(0)));
                                g.getChoix1().setText(g.getQaA().getWroAns().get(qIndexes.get(0)));
                            }
                            qIndexes.remove(0);
                        }
                        if (quizCounter == 6) {

                            g.getQuestion().setVisible(false);
                            g.getChoix1().setVisible(false);
                            g.getChoix2().setVisible(false);
                            String affiche = "<html> <center> Score " + scoreFinal
                                    + "/5 <br>Tap To Retry</center></html>";
                            if (scoreFinal < 3) {
                                g.getScore().setBackground(Color.red);
                            }
                            if (scoreFinal == 3) {
                                g.getScore().setBackground(Color.gray);
                            }
                            if (scoreFinal > 3) {
                                g.getScore().setBackground(Color.green);
                            }
                            g.getScore().setText(affiche);
                            g.getScore().setVisible(true);

                            quizCounter = 0;
                        }

                    }
                });
                g.getScore().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int k = 0; k < 5; k++) {
                            qIndexes.add(k);
                        }
                        Collections.shuffle(qIndexes);
                        g.getQuiz().setVisible(true);
                        g.getScore().setVisible(false);
                        quizCounter = 0;
                        scoreFinal = 0;
                    }
                });

                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                Timer timer = new Timer(1000, new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Timer running... ");
                        // System.out.println(RPD.getNomRobot() + " " + RNA.getNomRobot() + " " +
                        // RNA2.getNomRobot());
                        // RNA.jeBouge();
                        // RNA2.jeBouge();
                        // RPS.jeBouge();
                        // RPD.jeBouge();
                        // RPD2.jeBouge();
                        /*
                         * for (int i = 0; i < monde.getRobotsList().size(); i++) {
                         * monde.getRobotsList().get(i).jeBouge();
                         * }
                         */
                        for (int i = 1; i <= monde.getListRNA().size(); i++) {
                            monde.getListRNA().get(i - 1).jeBouge();
                        }
                        for (int i = 1; i <= monde.getListRNH().size(); i++) {
                            monde.getListRNH().get(i - 1).jeBouge();
                        }
                        // for (int i = 1; i <= monde.getListRPL().size(); i++) {
                        // monde.getListRPL().get(i - 1).jeBouge();
                        // }
                        for (int i = 1; i <= monde.getListRPS().size(); i++) {
                            monde.getListRPS().get(i - 1).jeBouge();
                        }
                        for (int i = 1; i <= monde.getListRPV().size(); i++) {
                            monde.getListRPV().get(i - 1).jeBouge();
                        }
                        g.getMainFrame().revalidate();
                        g.getMainFrame().repaint();
                        // RPL.jeBouge();
                        for (int i = 0; i < monde.getLignes(); i++) {
                            for (int j = 0; j < monde.getColonnes(); j++) {
                                if (monde.getRobots()[i][j])
                                    System.out.print(
                                            ConsoleColors.RED + " " + monde.getRobots()[i][j] + " "
                                                    + ConsoleColors.RESET);
                                else
                                    System.out
                                            .print(ConsoleColors.GREEN + " " + monde.getRobots()[i][j]
                                                    + ConsoleColors.RESET);
                            }
                            System.out.print("\n");

                        }
                        g.getNbPapiersGras()
                                .setText(String
                                        .valueOf("The pollution ratio : " + (100 * monde.tasCombienDePapierGras()
                                                / (monde.getLignes() * monde.getColonnes())) + "/"
                                                + "100"));

                    }
                });
                timer.start();

                timerRPL = new Timer(vRPL, new ActionListener() {

                    // @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Timer vRPL running... ");

                        for (int i = 1; i <= monde.getListRPL().size(); i++) {
                            monde.getListRPL().get(i - 1).jeBouge();
                        }

                        g.getMainFrame().revalidate();
                        g.getMainFrame().repaint();
                        // RPL.jeBouge();
                        for (int i = 0; i < monde.getLignes(); i++) {
                            for (int j = 0; j < monde.getColonnes(); j++) {
                                if (monde.getRobots()[i][j])
                                    System.out.print(
                                            ConsoleColors.RED + " " + monde.getRobots()[i][j] + " "
                                                    + ConsoleColors.RESET);
                                else
                                    System.out
                                            .print(ConsoleColors.GREEN + " " + monde.getRobots()[i][j]
                                                    + ConsoleColors.RESET);
                            }
                            System.out.print("\n");

                        }
                        g.getNbPapiersGras()
                                .setText(String
                                        .valueOf("The pollution ratio : " + (100 * monde.tasCombienDePapierGras()
                                                / (monde.getLignes() * monde.getColonnes())) + "/"
                                                + "100"));
                        timerRPL.setDelay(vRPL);
                    }
                });
                timerRPL.start();

                /*
                 * Timer timer2 = new Timer(r, new ActionListener() {
                 * 
                 * @Override
                 * public void actionPerformed(ActionEvent e) {
                 * System.out.println("Timer running... " + " Interval " + r);
                 * System.out.println(RPD.getNomRobot() + " " + RNA.getNomRobot() + " " +
                 * RNA2.getNomRobot());
                 * RNA.jeBouge();
                 * // RNA2.jeBouge();
                 * r = (int) (((Math.random() * 1000000) % 10) * 1000);
                 * System.out.println("Timer running... " + " Interval " + r);
                 * 
                 * // RPD.jeBouge();
                 * g.getNbPapiersGras().setText(String.valueOf(monde.tasCombienDePapierGras()));
                 * 
                 * }
                 * });
                 * timer2.start();
                 */
            }
        });

    }
}
