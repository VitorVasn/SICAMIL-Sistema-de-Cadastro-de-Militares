package views;

import logica.Militar;
import logica.Patente;
import logica.Situacao;
import logica.militarDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MilitarForm extends JFrame {

    private JTable tabela;
    private DefaultTableModel modelo;
    private JButton btnCadastrar, btnAlterar, btnRemover, btnContar;
    private militarDAO dao = new militarDAO();

    public MilitarForm() {
        setTitle("SICAMIL - Gerenciamento de Militares");
        setSize(900, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        carregarTabela();
    }

    private void initComponents() {
        Color verdeOliva = new Color(85, 107, 47);
        Color verdeClaro = new Color(170, 190, 140);
        Font fonteNegrito = new Font("SansSerif", Font.BOLD, 14);

        // Cabeçalho com título
        JLabel titulo = new JLabel("GERENCIAMENTO DE MILITARES - SICAMIL", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setOpaque(true);
        titulo.setBackground(verdeOliva.darker());
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Criar painel principal
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(verdeClaro);
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        // Campo de busca
        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelBusca.setBackground(verdeClaro);
        JLabel labelBusca = new JLabel("Buscar por Nome ou SARAM:");
        labelBusca.setFont(fonteNegrito);
        labelBusca.setForeground(Color.DARK_GRAY);

        JTextField campoBusca = new JTextField(30);
        painelBusca.add(labelBusca);
        painelBusca.add(campoBusca);

        painelPrincipal.add(painelBusca, BorderLayout.BEFORE_FIRST_LINE);

        // Tabela
        modelo = new DefaultTableModel(new Object[]{
                "Patente", "Nome Completo", "Nome de Guerra", "SARAM", "Identidade","Seção","Turma", "Situação"
        }, 0);

        tabela = new JTable(modelo);
        tabela.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabela.setRowHeight(22);
        tabela.getTableHeader().setFont(fonteNegrito);
        tabela.getTableHeader().setBackground(verdeOliva.brighter());
        tabela.getTableHeader().setForeground(Color.BLACK);
        JScrollPane scroll = new JScrollPane(tabela);

        painelPrincipal.add(scroll, BorderLayout.CENTER);

        // RowSorter para filtro
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        tabela.setRowSorter(sorter);
        campoBusca.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }

            private void filtrar() {
                String texto = campoBusca.getText().trim();
                if (texto.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto, 1, 3));
                }
            }
        });

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painelBotoes.setBackground(verdeClaro);

        btnCadastrar = criarBotao("Cadastrar", "\u2795");
        btnAlterar = criarBotao("Alterar", "\u270F");
        btnRemover = criarBotao("Remover", "\uD83D\uDDD1");
        btnContar = criarBotao("Contar por Patente", "\uD83D\uDCCA");

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnAlterar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnContar);

        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
        setContentPane(painelPrincipal);

        // Ações dos botões
        btnCadastrar.addActionListener(e -> abrirFormulario(null));
        btnAlterar.addActionListener(e -> editarSelecionado());
        btnRemover.addActionListener(e -> removerSelecionado());
        btnContar.addActionListener(e -> contarPorPatente());
    }

    private JButton criarBotao(String texto, String emoji) {
        JButton botao = new JButton(emoji + " " + texto);
        botao.setFont(new Font("SansSerif", Font.BOLD, 13));
        botao.setBackground(Color.WHITE);
        botao.setForeground(new Color(34, 70, 10));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        botao.setPreferredSize(new Dimension(180, 35));
        return botao;
    }

    private void carregarTabela() {
        modelo.setRowCount(0);
        List<Militar> lista = dao.listarTodos();
        for (Militar m : lista) {
            modelo.addRow(new Object[]{
                    m.getPatente(), m.getNomeCompleto(), m.getNomeGuerra(),
                    m.getSaram(), m.getIdentidadeMilitar(),
                    m.getTurma(), m.getSituacao()
            });
        }
    }

    private void abrirFormulario(Militar militarExistente) {
        JTextField campoSaram = new JTextField();
        JTextField campoNomeCompleto = new JTextField();
        JTextField campoNomeGuerra = new JTextField();
        JTextField campoIdentidade = new JTextField();
        JTextField campoTurma = new JTextField();

        JComboBox<Patente> comboPatente = new JComboBox<>(Patente.values());
        JComboBox<Situacao> comboSituacao = new JComboBox<>(Situacao.values());

        if (militarExistente != null) {
            campoSaram.setText(militarExistente.getSaram());
            campoSaram.setEnabled(false);
            campoNomeCompleto.setText(militarExistente.getNomeCompleto());
            campoNomeGuerra.setText(militarExistente.getNomeGuerra());
            campoIdentidade.setText(militarExistente.getIdentidadeMilitar());
            campoTurma.setText(militarExistente.getTurma());
            comboPatente.setSelectedItem(militarExistente.getPatente());
            comboSituacao.setSelectedItem(militarExistente.getSituacao());
        }

        JPanel painel = new JPanel(new GridLayout(7, 2, 5, 5));
        painel.add(new JLabel("SARAM:"));
        painel.add(campoSaram);
        painel.add(new JLabel("Nome Completo:"));
        painel.add(campoNomeCompleto);
        painel.add(new JLabel("Nome de Guerra:"));
        painel.add(campoNomeGuerra);
        painel.add(new JLabel("Identidade:"));
        painel.add(campoIdentidade);
        painel.add(new JLabel("Turma:"));
        painel.add(campoTurma);
        painel.add(new JLabel("Patente:"));
        painel.add(comboPatente);
        painel.add(new JLabel("Situação:"));
        painel.add(comboSituacao);

        int opcao = JOptionPane.showConfirmDialog(this, painel, militarExistente == null ? "Cadastrar Militar" : "Alterar Militar",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (opcao == JOptionPane.OK_OPTION) {
            Militar m = new Militar();
            m.setSaram(campoSaram.getText());
            m.setNomeCompleto(campoNomeCompleto.getText());
            m.setNomeGuerra(campoNomeGuerra.getText());
            m.setIdentidadeMilitar(campoIdentidade.getText());
            m.setTurma(campoTurma.getText());
            m.setPatente((Patente) comboPatente.getSelectedItem());
            m.setSituacao((Situacao) comboSituacao.getSelectedItem());

            dao.salvar(m);
            carregarTabela();
        }
    }

    private void editarSelecionado() {
        int linha = tabela.getSelectedRow();
        if (linha >= 0) {
            String saram = modelo.getValueAt(tabela.convertRowIndexToModel(linha), 3).toString();
            Militar militar = dao.buscarPorSaram(saram);
            if (militar != null) {
                abrirFormulario(militar);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um militar para alterar.");
        }
    }

    private void removerSelecionado() {
        int linha = tabela.getSelectedRow();
        if (linha >= 0) {
            String saram = modelo.getValueAt(tabela.convertRowIndexToModel(linha), 3).toString();
            int confirm = JOptionPane.showConfirmDialog(this, "Confirmar remoção do militar?", "Remover", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dao.remover(saram);
                carregarTabela();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um militar para remover.");
        }
    }

    private void contarPorPatente() {
        List<Militar> lista = dao.listarTodos();
        Map<Patente, Long> contagem = lista.stream()
                .collect(Collectors.groupingBy(Militar::getPatente, Collectors.counting()));

        StringBuilder msg = new StringBuilder("Total por Patente:\n");
        for (Map.Entry<Patente, Long> entry : contagem.entrySet()) {
            msg.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        JOptionPane.showMessageDialog(this, msg.toString());
    }
}
