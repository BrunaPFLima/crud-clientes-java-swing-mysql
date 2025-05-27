package com.exemplo.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.exemplo.dao.PessoaDAO;
import com.exemplo.model.Pessoa;

import java.awt.*;


import java.awt.event.*;

public class PessoaForm extends JFrame {

    private JTextField txtCpf, txtNome, txtEmail, txtTelefone;
    private JButton btnIncluir, btnAtualizar, btnLimpar, btnExcluir;
    private JTable tabela;
    private DefaultTableModel tableModel;

    private PessoaDAO dao;
    private String cpfSelecionado = null;

    public PessoaForm() throws ClassNotFoundException {
        dao = new PessoaDAO();
        initialize();
        listarDados();
    }

    private void initialize() {
        setTitle("CRUD Pessoa");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel do formulário
        JPanel painelForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // CPF
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelForm.add(new JLabel("CPF:"), gbc);
        txtCpf = new JTextField(15);
        gbc.gridx = 1;
        painelForm.add(txtCpf, gbc);

        // Nome
        gbc.gridx = 0;
        gbc.gridy = 1;
        painelForm.add(new JLabel("Nome:"), gbc);
        txtNome = new JTextField(15);
        gbc.gridx = 1;
        painelForm.add(txtNome, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 2;
        painelForm.add(new JLabel("Email:"), gbc);
        txtEmail = new JTextField(15);
        gbc.gridx = 1;
        painelForm.add(txtEmail, gbc);

        // Telefone
        gbc.gridx = 0;
        gbc.gridy = 3;
        painelForm.add(new JLabel("Telefone:"), gbc);
        txtTelefone = new JTextField(15);
        gbc.gridx = 1;
        painelForm.add(txtTelefone, gbc);

        // Botões
        JPanel painelBotoes = new JPanel();
        btnIncluir = new JButton("Incluir");
        btnAtualizar = new JButton("Atualizar");
        btnLimpar = new JButton("Limpar");
        btnExcluir = new JButton("Excluir");

        painelBotoes.add(btnIncluir);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnExcluir);

        // Tabela
        tableModel = new DefaultTableModel(new Object[]{"CPF", "Nome", "Email", "Telefone"}, 0);
        tabela = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabela);

        // Adiciona componentes à frame
        add(painelForm, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Inicializa estados
        atualizarEstadoAtualizar(false);

        // Eventos
        btnIncluir.addActionListener(e -> inserirPessoa());
        btnAtualizar.addActionListener(e -> atualizarPessoa());
        btnLimpar.addActionListener(e -> limparCampos());
        btnExcluir.addActionListener(e -> excluirPessoa());

        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (tabela.getSelectedRow() != -1) {
                    preencherCampos(tabela.getSelectedRow());
                }
            }
        });
    }

    private void listarDados() {
        tableModel.setRowCount(0);
        for (Pessoa p : dao.listarTodos()) {
            tableModel.addRow(new Object[]{p.getCpf(), p.getNome(), p.getEmail(), p.getTelefone()});
        }
    }

    private void inserirPessoa() {
        String cpf = txtCpf.getText().trim();
        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();
        String telefone = txtTelefone.getText().trim();

        if (cpf.isEmpty() || nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "CPF e Nome são obrigatórios!");
            return;
        }

        Pessoa p = new Pessoa(cpf, nome, email, telefone);
        dao.inserir(p);
        listarDados();
        limparCampos();
    }

    private void atualizarPessoa() {
        if (cpfSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para atualizar!");
            return;
        }

        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();
        String telefone = txtTelefone.getText().trim();

        Pessoa p = new Pessoa(cpfSelecionado, nome, email, telefone);
        dao.atualizar(p);
        listarDados();
        limparCampos();
        atualizarEstadoAtualizar(false);
        cpfSelecionado = null;
    }

    private void preencherCampos(int linha) {
        cpfSelecionado = (String) tableModel.getValueAt(linha, 0);
        txtCpf.setText(cpfSelecionado);
        txtNome.setText((String) tableModel.getValueAt(linha, 1));
        txtEmail.setText((String) tableModel.getValueAt(linha, 2));
        txtTelefone.setText((String) tableModel.getValueAt(linha, 3));
        atualizarEstadoAtualizar(true);
        txtCpf.setEnabled(false); // CPF não pode ser alterado
    }

    private void limparCampos() {
        txtCpf.setText("");
        txtNome.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        cpfSelecionado = null;
        atualizarEstadoAtualizar(false);
        txtCpf.setEnabled(true);
        tabela.clearSelection();
    }

    private void excluirPessoa() {
        if (cpfSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para excluir!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                dao.excluir(cpfSelecionado);
                JOptionPane.showMessageDialog(this, "Pessoa excluída com sucesso!");
                listarDados();
                limparCampos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir: " + e.getMessage());
            }
        }
    }

    private void atualizarEstadoAtualizar(boolean estado) {
        btnAtualizar.setEnabled(estado);
        btnIncluir.setEnabled(!estado);
        btnExcluir.setEnabled(estado);
    }
}

