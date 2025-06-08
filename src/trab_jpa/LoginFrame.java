/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trab_jpa;

/**
 *
 * @author douglas
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    
    Process process = new Process();
    
    public LoginFrame() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel userLabel = new JLabel("Usuário");
        JTextField userField = new JTextField(10);
        JLabel passLabel = new JLabel("Senha");
        JPasswordField passField = new JPasswordField(10);
        JButton loginButton = new JButton("Entrar");

        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        add(userLabel, gbc);

        gbc.gridx = 1;
        add(userField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(passLabel, gbc);

        gbc.gridx = 1;
        add(passField, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            String usuario = userField.getText();
            String senha = new String(passField.getPassword());
            
            boolean aut = process.autenticacao(usuario, senha);
            
            if (aut) {
                dispose();
                new TelaPrincipalFrame();
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}
