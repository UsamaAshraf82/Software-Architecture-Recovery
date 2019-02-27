/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Software;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class DisplayDiagram extends JFrame
{

    JPanel upperPanel, mainPanel, bottom, north, lower;
    FileScanner fs;
    JScrollPane scrPane;
    JScrollPane scrPane1;
    JLabel text, text1;
    int flagg = 0;
    String temp;
    ObjectInfo clas;

    public DisplayDiagram(FileScanner fs)
    {
        this.fs = fs;
        mainPanel = new JPanel();
        lower = new JPanel();

        bottom = new JPanel();
        upperPanel = new JPanel();

        north = new JPanel();

        mainPanel.setLayout(new BorderLayout(14, 14));
        north.setLayout(new GridLayout(2, 2, 12, 12));

        scrPane = new JScrollPane(upperPanel);
        scrPane1 = new JScrollPane(lower);

        upperPanel.setVisible(true);
        lower.setVisible(true);

        north.setBackground(Color.white);
        upperPanel.setBackground(Color.LIGHT_GRAY);
        upperPanel.setMinimumSize(new Dimension(WIDTH, 300));
        upperPanel.setMaximumSize(new Dimension(WIDTH, 300));
        lower.setBackground(Color.LIGHT_GRAY);
        lower.setMinimumSize(new Dimension(WIDTH, 300));
        lower.setMaximumSize(new Dimension(WIDTH, 300));
        bottom.setBackground(Color.white);
        scrPane.setBackground(Color.LIGHT_GRAY);
        scrPane1.setBackground(Color.LIGHT_GRAY);

        mainPanel.setBackground(Color.LIGHT_GRAY);

        mainPanel.add(north, BorderLayout.NORTH);
        mainPanel.add(scrPane, BorderLayout.CENTER);
        mainPanel.add(bottom, BorderLayout.SOUTH);
        mainPanel.add(scrPane1, BorderLayout.SOUTH);

        add(mainPanel);

        setSize(800, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        AddButtonactionPerformed();


    }

    public void AddButtonactionPerformed()
    {
        clas = new ObjectInfo();

       /// fs.printClasses2();

        for (int i = 0; i < fs.printList2.size(); i++)
        {

            JTextArea jt = new JTextArea(20, 25);

            jt.setText(fs.printList2.get(i));
            jt.setEditable(false);
            upperPanel.add(new JScrollPane(jt));
        }
        JTextArea jt1 = new JTextArea(10, 30);
        text1 = new JLabel("<html><u>Relationships</u></html>");
        lower.add(text1);
        lower.add(new JScrollPane(jt1));

        text1.setForeground(Color.BLACK);
        jt1.setEditable(false);
        Set<String> hs = new HashSet<>();
        Set<String> hf = new HashSet<>();
        hs.addAll(fs.Association);
        fs.Association.clear();
        fs.Association.addAll(hs);
        hf.addAll(fs.Inheritance);
        fs.Inheritance.clear();
        fs.Inheritance.addAll(hf);

        temp = "\n--------------------------------------------------\n         Association   \n--------------------------------------------------\n";

        for (int i = fs.Association.size(); i > 0; i--)
        {
            temp = temp + fs.Association.get(i - 1);

        }
        temp = temp + "\n--------------------------------------------------\n         Inheritance   \n--------------------------------------------------\n";

        for (int i = fs.Inheritance.size(); i > 0; i--)
        {
            temp = temp + fs.Inheritance.get(i - 1);
        }
        System.out.println(temp);
        jt1.setText(temp);
       

    }
    

}
