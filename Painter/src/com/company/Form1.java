package com.company;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;

public class Form1 extends JFrame{
    private JButton freeDrawingButton;
    private JButton lineDrawingButton;
    private JButton eclipseDrawingButton;
    private JButton rectangleDrawingButton;
    private JPanel panel1;
    Graphics2D freeDrawing,
            lineDrawing,
            rectangleDrawing,
            eclipseDrawing;
    Point previousPoint;
    boolean draw = false;
    int prevX, prevY;
    int x1,y1,x2,y2;

    public Form1() {

        setTitle("Welcome to Paint");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400,250));
        setContentPane(panel1);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        previousPoint = new Point();

        freeDrawingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setPreferredSize(new Dimension(500,300));
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setTitle("Free Hand Drawing");
                frame.setLocationByPlatform(true);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                JPanel bPanel = new JPanel(new FlowLayout(SwingConstants.RIGHT));
                Icon icon = new ImageIcon("images\\undo.png");
                JButton undoButton = new JButton(icon);
                undoButton.setPreferredSize(new Dimension(25,25));
                Icon icon1 = new ImageIcon("images\\color.png");
                JButton colorButton = new JButton(icon1);
                colorButton.setPreferredSize(new Dimension(25,25));
                Icon icon2 = new ImageIcon("images\\pen.png");
                JButton penButton = new JButton(icon2);
                penButton.setPreferredSize(new Dimension(25,25));
                penButton.setToolTipText("Select the thickness of the pen");
                colorButton.setToolTipText("Select the color of the pen");
//                bPanel.add(undoButton);
                bPanel.add(colorButton);
                bPanel.add(penButton);
                panel.add(bPanel);
                panel.revalidate();
                panel.repaint();
                freeDrawing = (Graphics2D) panel.getGraphics();
                colorButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Color color = JColorChooser.showDialog(Form1.this, "Select the color:",panel.getBackground());
                        freeDrawing.setColor(color);
                    }
                });
                penButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String str = JOptionPane.showInputDialog("Pen Width:");
                        Integer width = Integer.parseInt(str);
                        freeDrawing.setStroke(new BasicStroke(width));
                    }
                });

                panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        prevX = e.getX();
                        prevY = e.getY();
                        draw = true;
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        draw = false;
                    }
                });
                panel.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        if(draw){
                            freeDrawing.drawLine(prevX, prevY, e.getX(), e.getY());
                            prevX = e.getX();
                            prevY = e.getY();
                        }
                    }
                });
            }
        });
        lineDrawingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setPreferredSize(new Dimension(300,300));
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setTitle("Line Drawing");
                frame.setLocationByPlatform(true);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                JPanel bPanel = new JPanel(new FlowLayout(SwingConstants.RIGHT));
                Icon icon1 = new ImageIcon("images\\color.png");
                JButton colorButton = new JButton(icon1);
                colorButton.setPreferredSize(new Dimension(25,25));
                Icon icon2 = new ImageIcon("images\\pen.png");
                JButton penButton = new JButton(icon2);
                penButton.setPreferredSize(new Dimension(25,25));
                JPanel bPanel2 = new JPanel(new FlowLayout(SwingConstants.LEFT));
                Icon icon3 = new ImageIcon("images\\rotate.png");
                JButton button = new JButton(icon3);
                button.setPreferredSize(new Dimension(25,25));
                penButton.setToolTipText("Select the thickness of the pen");
                colorButton.setToolTipText("Select the color of the pen");
                button.setToolTipText("Rotate for 25°");
                bPanel2.add(button);
                bPanel.add(colorButton);
                bPanel.add(penButton);
                panel.add(bPanel);
                panel.add(bPanel2);
                panel.revalidate();
                panel.repaint();
                lineDrawing = (Graphics2D) panel.getGraphics();

                colorButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Color color = JColorChooser.showDialog(Form1.this, "Select the color:",panel.getBackground());
                        lineDrawing.setColor(color);
                    }
                });
                penButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String str = JOptionPane.showInputDialog("Pen Width:");
                        Integer width = Integer.parseInt(str);
                        lineDrawing.setStroke(new BasicStroke(width));
                    }
                });
                panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        y1 = e.getY();
                        x2 = e.getX();

                        String str2 = JOptionPane.showInputDialog("x1: ");
                        String str3 = JOptionPane.showInputDialog("y2: ");
                        Integer x1 = Integer.parseInt(str2);
                        Integer y2 = Integer.parseInt(str3);
                        lineDrawing.drawLine(x2,y1,x1,y2);

                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                lineDrawing.rotate(25);
                                lineDrawing.drawLine(x1+10,y1+10,x2+10,y2+10);
                            }
                        });
                    }
                });
            }
        });
        eclipseDrawingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setPreferredSize(new Dimension(300,300));
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setTitle("Eclipse Drawing");
                frame.setLocationByPlatform(true);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                JPanel bPanel = new JPanel(new FlowLayout(SwingConstants.RIGHT));
                Icon icon = new ImageIcon("images\\undo.png");
                JButton undoButton = new JButton(icon);
                undoButton.setPreferredSize(new Dimension(25,25));
                Icon icon1 = new ImageIcon("images\\color.png");
                JButton colorButton = new JButton(icon1);
                colorButton.setPreferredSize(new Dimension(25,25));
                Icon icon2 = new ImageIcon("images\\pen.png");
                JButton penButton = new JButton(icon2);
                penButton.setPreferredSize(new Dimension(25,25));
                JPanel bPanel2 = new JPanel(new FlowLayout(SwingConstants.LEFT));
                Icon icon3 = new ImageIcon("images\\rotate.png");
                JButton button = new JButton(icon3);
                button.setPreferredSize(new Dimension(25,25));
                penButton.setToolTipText("Select the thickness of the pen");
                colorButton.setToolTipText("Select the color of the pen");
                button.setToolTipText("Rotate for 25°");
                bPanel2.add(button);
//                bPanel.add(undoButton);
                bPanel.add(colorButton);
                bPanel.add(penButton);
                panel.add(bPanel);
                panel.add(bPanel2);
                panel.revalidate();
                panel.repaint();
                eclipseDrawing = (Graphics2D) panel.getGraphics();
                colorButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Color color = JColorChooser.showDialog(Form1.this, "Select the color:",panel.getBackground());
                        eclipseDrawing.setColor(color);
                    }
                });
                penButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String str = JOptionPane.showInputDialog("Pen Width:");
                        Integer width = Integer.parseInt(str);
                        eclipseDrawing.setStroke(new BasicStroke(width));
                    }
                });
                panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        x1 = e.getX()-5;
                        y1 = e.getY()-5;
                        x2 = 20;
                        y2 = 40;
                        eclipseDrawing.drawOval(x1,y1,x2,y2);

                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                eclipseDrawing.rotate(25);
                                eclipseDrawing.drawOval(x1+10,y1+10,x2+10,y2+10);
                            }
                        });
                    }
                });
            }
        });
        rectangleDrawingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setPreferredSize(new Dimension(500,300));
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setTitle("Rectangle Drawing");
                frame.setLocationByPlatform(true);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                JPanel bPanel = new JPanel(new FlowLayout(SwingConstants.RIGHT));
                Icon icon = new ImageIcon("images\\undo.png");
                JButton undoButton = new JButton(icon);
                undoButton.setPreferredSize(new Dimension(25,25));
                Icon icon1 = new ImageIcon("images\\color.png");
                JButton colorButton = new JButton(icon1);
                colorButton.setPreferredSize(new Dimension(25,25));
                Icon icon2 = new ImageIcon("images\\pen.png");
                JButton penButton = new JButton(icon2);
                penButton.setPreferredSize(new Dimension(25,25));
                JPanel bPanel2 = new JPanel(new FlowLayout(SwingConstants.LEFT));
                Icon icon3 = new ImageIcon("images\\rotate.png");
                JButton button = new JButton(icon3);
                button.setPreferredSize(new Dimension(25,25));
                penButton.setToolTipText("Select the thickness of the pen");
                colorButton.setToolTipText("Select the color of the pen");
                undoButton.setToolTipText("UNDO");
                button.setToolTipText("Rotate for 78°");
                bPanel2.add(button);
                bPanel.add(undoButton);
                bPanel.add(colorButton);
                bPanel.add(penButton);
                panel.add(bPanel);
                panel.add(bPanel2);
                panel.revalidate();
                panel.repaint();
                rectangleDrawing = (Graphics2D) panel.getGraphics();

                colorButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Color color = JColorChooser.showDialog(Form1.this, "Select the color:",panel.getBackground());
                        rectangleDrawing.setColor(color);
                    }
                });
                penButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String str = JOptionPane.showInputDialog("Pen Width:");
                        Integer width = Integer.parseInt(str);
                        rectangleDrawing.setStroke(new BasicStroke(width));
                    }
                });
                panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        x1 = e.getX()-10;
                        y1 = e.getY()+5;
                        x2 = e.getX()+15;
                        y2 = e.getY()-20;
                        rectangleDrawing.drawRect(x1,y1,x2,y2);

                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
//                                rectangleDrawing.rotate(.5,1,1);
                                rectangleDrawing.rotate(78);
                                rectangleDrawing.drawRect(x1+45,y1+5,x2+35,y2+5);
                            }
                        });
                    }
                });
                undoButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        rectangleDrawing.clearRect(x1-15,y1-15,x2+25,y2+25);
//                        panel.removeAll();  //clear all the content inside panel
//                        panel.updateUI();
                    }
                });
            }
        });
    }
}
