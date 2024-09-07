package com.mycompany.finalproject_dinogame;

import javax.swing.*;

/* Name: Darren Drew
 * Date: June 4th, 2023
 * Desc: Dino Game: Sets up the JPanel for the game.java window
 */
public class main {
    main() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        game open = new game();
        window.add(open);
        window.addKeyListener(open);
        window.addMouseListener(open);

        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }
}
