package com.exemplo;

import javax.swing.SwingUtilities;

import com.exemplo.view.PessoaForm;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PessoaForm view;
            try {
                view = new PessoaForm();
                view.setVisible(true);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        });
    }
}
