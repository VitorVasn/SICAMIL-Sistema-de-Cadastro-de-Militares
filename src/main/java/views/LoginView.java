package views;

import login.Usuario;
import login.UsuarioDAO;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JButton botaoEntrar;
    private JButton botaoCadastrar;

    public LoginView() {
        setTitle("SICAMIL - Login");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        // Cores e fontes
        Color verdeOliva = new Color(85, 107, 47);
        Font fonteNegrito = new Font("SansSerif", Font.BOLD, 16);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setBackground(verdeOliva);
        painelPrincipal.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("SICAMIL", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        // Painel de formulário
        JPanel painelForm = new JPanel(new GridLayout(3, 2, 10, 10));
        painelForm.setOpaque(false);
        painelForm.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));

        JLabel labelLogin = new JLabel("Login:");
        labelLogin.setFont(fonteNegrito);
        labelLogin.setForeground(Color.WHITE);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setFont(fonteNegrito);
        labelSenha.setForeground(Color.WHITE);

        campoLogin = new JTextField(15);
        campoSenha = new JPasswordField(15);

        painelForm.add(labelLogin);
        painelForm.add(campoLogin);
        painelForm.add(labelSenha);
        painelForm.add(campoSenha);

        painelPrincipal.add(painelForm, BorderLayout.CENTER);

        // Painel de botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setOpaque(false);
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        botaoEntrar = criarBotaoEstilizado("Entrar");
        botaoCadastrar = criarBotaoEstilizado("Cadastrar Novo Login");

        botaoEntrar.addActionListener(e -> fazerLogin());
        botaoCadastrar.addActionListener(e -> abrirCadastroLogin());

        painelBotoes.add(botaoEntrar);
        painelBotoes.add(botaoCadastrar);

        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        setContentPane(painelPrincipal);
    }

    private JButton criarBotaoEstilizado(String texto) {
        JButton botao = new JButton(texto);
        botao.setFocusPainted(false);
        botao.setFont(new Font("SansSerif", Font.BOLD, 14));
        botao.setBackground(Color.WHITE);
        botao.setForeground(new Color(34, 70, 10));
        botao.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true));
        botao.setPreferredSize(new Dimension(160, 30));
        return botao;
    }

    private void fazerLogin() {
        String login = campoLogin.getText();
        String senha = new String(campoSenha.getPassword());

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.buscarPorLoginSenha(login, senha);

        if (usuario != null) {
            JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");
            new MainView().setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Login ou senha incorretos.");
        }
    }

    private void abrirCadastroLogin() {
        new CadastroLoginView().setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}
