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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class TelaPrincipalFrame extends JFrame {
    CardLayout cardLayout;
    JPanel contentPanel;
    
    private JTextField tfNome;
    private JTextField tfIdade;
    private JTextField tfCidade;
    private JButton btnAddCad;
    private JTextField tfAttId;
    private JTextField tfAttNome;
    private JTextField tfAttIdade;
    private JTextField tfAttCidade;
    private JButton btnAttCad;
    private JTextField tfExId;
    private JTextField tfExNome;
    private JTextField tfExIdade;
    private JTextField tfExCidade;
    private JButton btnExCad;
    private JTextField txBusca;
    private JButton btnBusca;
    private JTable jTabela;
    
    
    public TelaPrincipalFrame() {
        setTitle("Tela Principal");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        JButton btnSelecionar = new JButton("Selecionar");
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");

        buttonPanel.add(btnSelecionar);
        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnAtualizar);
        buttonPanel.add(btnExcluir);

        // Painéis de conteúdo
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        contentPanel.add(panelSelecionar(), "Selecionar");
        contentPanel.add(panelAdicionar(), "Adicionar");
        contentPanel.add(panelAtualizar(), "Atualizar");
        contentPanel.add(panelExcluir(), "Excluir");

        // Eventos dos botões
        //btnSelecionar.addActionListener(e -> cardLayout.show(contentPanel, "Selecionar"));
        btnAdicionar.addActionListener(e -> cardLayout.show(contentPanel, "Adicionar"));
        btnAtualizar.addActionListener(e -> cardLayout.show(contentPanel, "Atualizar"));
        btnExcluir.addActionListener(e -> cardLayout.show(contentPanel, "Excluir"));

        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        btnSelecionar.addActionListener(e -> {
            cardLayout.show(contentPanel, "Selecionar");
            Process p = new Process();
            List<Cadastros> lista = p.listaCadastros();
            
            if(lista != null) {
                String[] colunas = {"ID", "Nome", "Idade", "Cidade"};
                Object[][] dados = new Object[lista.size()][4];
                for (int i = 0; i < lista.size(); i++) {
                    Cadastros c = lista.get(i);
                    dados[i][0] = c.getId();
                    dados[i][1] = c.getNome();
                    dados[i][2] = c.getIdade();
                    dados[i][3] = c.getCidade();
                }
                jTabela.setModel(new javax.swing.table.DefaultTableModel(dados, colunas));
            }
        });

        
        
        setVisible(true);
    }

    private JPanel panelSelecionar() {
        JPanel panel = new JPanel();
        txBusca = new JTextField(20);
        btnBusca = new JButton("Localizar");
        jTabela = new JTable();
        
        panel.add(new JLabel("Nome:"));
        panel.add(txBusca);
        panel.add(btnBusca);
        panel.add(jTabela);
        
        return panel;
    }

    private JPanel panelAdicionar() {
        tfNome = new JTextField(20);
        tfIdade = new JTextField(5);
        tfCidade = new JTextField(20);
        btnAddCad = new JButton("Adicionar");
        
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Adicionando cadastro"));

        panel.add(new JLabel("Nome:"));
        panel.add(tfNome);

        panel.add(new JLabel("Idade:"));
        panel.add(tfIdade);

        panel.add(new JLabel("Cidade:"));
        panel.add(tfCidade);

        panel.add(btnAddCad);
        
        btnAddCad.addActionListener(e -> {
            try {
                String nome = tfNome.getText();
                int idade = Integer.parseInt(tfIdade.getText());
                String cidade = tfCidade.getText();

                Process process = new Process();
                process.adicionar(nome, idade, cidade);

                JOptionPane.showMessageDialog(this, "Cadastro adicionado com sucesso!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar cadastro");
            }
        });

        
        return panel;
    }

    private JPanel panelAtualizar() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Atualizando cadastro"));
        
        tfAttId = new JTextField(5);
        tfAttNome = new JTextField(20);
        tfAttIdade = new JTextField(5);
        tfAttCidade = new JTextField(20);
        btnAttCad = new JButton("Atualizar");
        

        panel.add(new JLabel("ID:"));
        panel.add(tfAttId);

        panel.add(new JLabel("Nome:"));
        panel.add(tfAttNome);

        panel.add(new JLabel("Idade:"));
        panel.add(tfAttIdade);

        panel.add(new JLabel("Cidade:"));
        panel.add(tfAttCidade);
        
        panel.add(btnAttCad);
        
        btnAttCad.addActionListener(e -> {
            try {
                int id = Integer.parseInt(tfAttId.getText());
                String nome = tfAttNome.getText();
                int idade = Integer.parseInt(tfAttIdade.getText());
                String cidade = tfAttCidade.getText();
                
                Process process = new Process();
                process.atualizar(id, nome, idade, cidade);
                
                JOptionPane.showMessageDialog(this, "Cadastro adicionado com sucesso!");
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Não foi possivel realizar a atualização!");
            }
        });

        return panel;
    }

    private JPanel panelExcluir() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Excluindo cadastro"));
        
        tfExId = new JTextField(5);
        tfExNome = new JTextField(20);
        tfExIdade = new JTextField(5);
        tfExCidade = new JTextField(20);
        btnExCad = new JButton("Excluir");

        panel.add(new JLabel("ID:"));
        panel.add(tfExId);

        panel.add(new JLabel("Nome:"));
        panel.add(tfExNome);

        panel.add(new JLabel("Idade:"));
        panel.add(tfExIdade);

        panel.add(new JLabel("Cidade:"));
        panel.add(tfExCidade);

        panel.add(new JLabel(""));
        panel.add(btnExCad);
        
        tfExId.addActionListener(e -> {
            try {
                int id = Integer.parseInt(tfExId.getText());
                Process process = new Process();
            
                Cadastros cad = process.buscando(id);
            
                tfExNome.setText(cad.getNome());
                tfExIdade.setText(String.valueOf(cad.getIdade()));
                tfExCidade.setText(cad.getCidade());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            
        });
        
        btnExCad.addActionListener(e -> {
            try {
            
                int id = Integer.parseInt(tfExId.getText());
            Process process = new Process();
            process.excluir(id);
            
            tfExNome.setText("");
            tfExIdade.setText("");
            tfExCidade.setText("");
            tfAttId.setText("");

            JOptionPane.showMessageDialog(null, "Cadastro excluído com sucesso!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possivel realizar a exclusão!");
            }
            

            
        });
        
        

        return panel;
    }
    
    
}

