package views;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    private JButton botaoGerenciarMilitares;
    private JButton botaoSair;

    public MainView() {
        setTitle("SICAMIL - Menu Principal");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        Color verdeOliva = new Color(85, 107, 47);
        Font fonteNegrito = new Font("SansSerif", Font.BOLD, 18);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(verdeOliva);

        JLabel titulo = new JLabel("SICAMIL", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 32));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setOpaque(false);
        painelBotoes.setLayout(new GridLayout(2, 1, 20, 20));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 80, 10, 80));

        botaoGerenciarMilitares = criarBotaoEstilizado("Gerenciar Militares");
        botaoSair = criarBotaoEstilizado("Sair");

        botaoGerenciarMilitares.addActionListener(e -> abrirGerenciamento());
        botaoSair.addActionListener(e -> sair());

        painelBotoes.add(botaoGerenciarMilitares);
        painelBotoes.add(botaoSair);

        painelPrincipal.add(painelBotoes, BorderLayout.CENTER);
        setContentPane(painelPrincipal);
    }

    private JButton criarBotaoEstilizado(String texto) {
        JButton botao = new JButton(texto);
        botao.setFocusPainted(false);
        botao.setFont(new Font("SansSerif", Font.BOLD, 16));
        botao.setBackground(Color.WHITE);
        botao.setForeground(new Color(34, 70, 10));
        botao.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true));
        botao.setPreferredSize(new Dimension(180, 40));
        return botao;
    }

    private void abrirGerenciamento() {
        new MilitarForm().setVisible(true);
    }

    private void sair() {
        dispose();
    }
}
