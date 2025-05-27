package com.exemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;

import com.exemplo.model.Pessoa;

public class PessoaDAO {

    private DataSource dataSource;

    public PessoaDAO() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
        JdbcDataSource ds = new JdbcDataSource();
        //ds.setURL("jdbc:h2:C:/teste/pessoadb;MODE=MYSQL;AUTO_SERVER=TRUE");
        ds.setURL("jdbc:h2:./src/main/resources/pessoadb;MODE=MYSQL;AUTO_SERVER=TRUE");
        ds.setUser("sa");
        ds.setPassword("");
        this.dataSource = ds;
        criarTabelaSeNecessario();
    }

    private void criarTabelaSeNecessario() {
        String sql = "CREATE TABLE IF NOT EXISTS pessoas (" +
                     "cpf VARCHAR(20) PRIMARY KEY," +
                     "nome VARCHAR(100)," +
                     "email VARCHAR(100)," +
                     "telefone VARCHAR(20)" +
                     ")";
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Pessoa p) {
        String sql = "INSERT INTO pessoas (cpf, nome, email, telefone) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, p.getCpf());
            pstmt.setString(2, p.getNome());
            pstmt.setString(3, p.getEmail());
            pstmt.setString(4, p.getTelefone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Pessoa p) {
        String sql = "UPDATE pessoas SET nome = ?, email = ?, telefone = ? WHERE cpf = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, p.getNome());
            pstmt.setString(2, p.getEmail());
            pstmt.setString(3, p.getTelefone());
            pstmt.setString(4, p.getCpf());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(String cpf) {
        String sql = "DELETE FROM pessoas WHERE cpf = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cpf);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Pessoa buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM pessoas WHERE cpf = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cpf);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Pessoa(
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("telefone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Pessoa> listarTodos() {
        List<Pessoa> lista = new ArrayList<>();
        String sql = "SELECT * FROM pessoas";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Pessoa(
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("telefone")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}




