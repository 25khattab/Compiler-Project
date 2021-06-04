/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.compiler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.text.*;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Utilities;

/**
 *
 * @author 25kha
 */
public class Test {

    JFrame frame;
    File f = null;

    public void AddComp() {
        JButton BrowseBtn = new JButton("Browse");
        JButton CompileBtn = new JButton("Compile");
        JButton CommentBtn = new JButton("Comment");
        JButton UnCommentBtn = new JButton("UnComment");
        JTextPane Editor = new JTextPane();
        JScrollPane TextHolder = new JScrollPane(Editor);

        Editor.getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
        AutoComplete panel = new AutoComplete(Editor);
        TextHolder.setBounds(0, 0, 500, 300);
        CommentBtn.setBounds(0, 320, 100, 40);
        UnCommentBtn.setBounds(110, 320, 120, 40);
        BrowseBtn.setBounds(240, 320, 100, 40);
        CompileBtn.setBounds(350, 320, 100, 40);

        CommentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String t1 = Editor.getSelectedText();
                if (!t1.isBlank()) {
                    String[] arrt = t1.lines().toArray(String[]::new);
                    if (arrt.length > 1) {
                        arrt[0] = "/@" + arrt[0];
                        arrt[arrt.length - 1] = arrt[arrt.length - 1] + "@/";
                    } else if (arrt.length == 1) {
                        arrt[0] = "/^" + arrt[0];
                    }
                    t1 = "";
                    for (int i = 0; i < arrt.length; i++) {
                        t1 += arrt[i] + "\n";
                    }
                }
                Editor.replaceSelection(t1);
            }
        });

        UnCommentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String t1 = Editor.getSelectedText();
                if (t1.startsWith("/^")) {
                    t1 = t1.substring(2);
                } else if (t1.startsWith("/@") && t1.endsWith("@/")) {
                    t1 = t1.substring(2, t1.length() - 2);
                }
                Editor.replaceSelection(t1);
            }
        });

        BrowseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Create an object of JFileChooser class
                JFileChooser j = new JFileChooser();

                // Invoke the showsOpenDialog function to show the save dialog
                int dialog = j.showOpenDialog(null);

                // If the user selects a file
                if (dialog == JFileChooser.APPROVE_OPTION) {
                    // Set the label to the path of the selected directory
                    f = new File(j.getSelectedFile().getAbsolutePath());
                }
            }

        });

        CompileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (f == null) {
                    //get text from Editor
                } else {
                    //get text from File
                }

            }

        });

        frame.add(BrowseBtn);

        frame.add(CompileBtn);

        frame.add(CommentBtn);

        frame.add(UnCommentBtn);

        frame.add(TextHolder);

    }

    public Test() {
        frame = new JFrame("Compiler");

        AddComp();

        //frame prop
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void main(String args[]) {
        Test t = new Test();
    }

}
