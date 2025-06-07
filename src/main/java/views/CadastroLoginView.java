package views;

import login.Usuario;
import login.UsuarioDAO;

import javax.swing.*;
import java.awt.*;

public class CadastroLoginView extends JFrame {

    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JButton botaoSalvar;

    public CadastroLoginView() {
        setTitle("SICAMIL - Cadastrar Novo Login");
        setSize(400, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        Color verdeOliva = new Color(85, 107, 47);
        Font fonteNegrito = new Font("SansSerif", Font.BOLD, 16);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(verdeOliva);

        JLabel titulo = new JLabel("Cadastrar Novo Login", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel painelForm = new JPanel(new GridLayout(2, 2, 10, 10));
        painelForm.setOpaque(false);
        painelForm.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));

        JLabel labelLogin = new JLabel("Login:");
        labelLogin.setFont(fonteNegrito);
        labelLogin.setForeground(Color.WHITE);
        campoLogin = new JTextField(15);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setFont(fonteNegrito);
        labelSenha.setForeground(Color.WHITE);
        campoSenha = new JPasswordField(15);

        painelForm.add(labelLogin);
        painelForm.add(campoLogin);
        painelForm.add(labelSenha);
        painelForm.add(campoSenha);

        painelPrincipal.add(painelForm, BorderLayout.CENTER);

        JPanel painelBotao = new JPanel();
        painelBotao.setOpaque(false);
        botaoSalvar = criarBotaoEstilizado("Salvar");
        botaoSalvar.addActionListener(e -> cadastrarUsuario());
        painelBotao.add(botaoSalvar);

        painelPrincipal.add(painelBotao, BorderLayout.SOUTH);

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

    private void cadastrarUsuario() {
        String login = campoLogin.getText();
        String senha = new String(campoSenha.getPassword());

        if (login.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (usuarioDAO.existeLogin(login)) {
            JOptionPane.showMessageDialog(this, "Login já existente!");
            return;
        }

        Usuario novoUsuario = new Usuario(login, senha);
        usuarioDAO.salvar(novoUsuario);
        JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
        this.dispose();
    }
}
