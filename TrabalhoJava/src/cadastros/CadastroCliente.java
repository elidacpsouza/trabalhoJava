/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastros;

import dao.ClienteDao;
import entidades.Cliente;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Elida
 */
public class CadastroCliente extends javax.swing.JDialog {

    /**
     * Creates new form CadastroCliente
     */
    private Boolean novoCadastro = false;
    private Cliente cliente;

    public CadastroCliente(java.awt.Dialog parent, boolean modal, boolean novo, Cliente cl) {
        super(parent, modal);
        initComponents();
        this.novoCadastro = novo;
        this.cliente = cl;
        populaTela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTNome = new javax.swing.JTextField();
        jTSobrenome = new javax.swing.JTextField();
        jFCpf = new javax.swing.JFormattedTextField();
        jBCancelar = new javax.swing.JButton();
        jBSalvar = new javax.swing.JButton();
        jBLimpar = new javax.swing.JButton();
        jLmensagem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);

        jLabel1.setText("Nome");

        jLabel2.setText("Sobrenome");

        jLabel3.setText("CPF");

        jTNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTNomeFocusGained(evt);
            }
        });

        jTSobrenome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTSobrenomeFocusGained(evt);
            }
        });

        try {
            jFCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFCpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFCpfFocusGained(evt);
            }
        });

        jBCancelar.setText("Cancelar");
        jBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelarActionPerformed(evt);
            }
        });

        jBSalvar.setText("Salvar");
        jBSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalvarActionPerformed(evt);
            }
        });

        jBLimpar.setText("Limpar");
        jBLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLimparActionPerformed(evt);
            }
        });

        jLmensagem.setForeground(new java.awt.Color(204, 0, 0));
        jLmensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLmensagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                .addComponent(jBCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBLimpar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBSalvar))
                            .addComponent(jTNome)
                            .addComponent(jTSobrenome)
                            .addComponent(jFCpf))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTSobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jFCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLmensagem, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBCancelar)
                    .addComponent(jBSalvar)
                    .addComponent(jBLimpar)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void jBLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLimparActionPerformed
        limparCadastro();
    }//GEN-LAST:event_jBLimparActionPerformed

    private void jBSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalvarActionPerformed
        if (validaCampos()) {
            if (novoCadastro) {
                cliente.setNome(jTNome.getText().toUpperCase());
                cliente.setSobrenome(jTSobrenome.getText().toUpperCase());
                cliente.setCpf(jFCpf.getText().replace(".", "").replace("-", "").trim());
                ClienteDao dao = new ClienteDao();
                try {
                    dao.adiciona(cliente);
                    dispose();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao realizar cadastro!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                cliente.setNome(jTNome.getText().toUpperCase());
                cliente.setSobrenome(jTSobrenome.getText().toUpperCase());
                cliente.setCpf(jFCpf.getText().replace(".", "").replace("-", "").trim());
                ClienteDao dao = new ClienteDao();
                try {
                    dao.atualiza(cliente);
                    dispose();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao Atualizar cadastro!", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao Atualizar cadastro!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jBSalvarActionPerformed

    private void jTNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTNomeFocusGained
        jLmensagem.setText("");
    }//GEN-LAST:event_jTNomeFocusGained

    private void jTSobrenomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTSobrenomeFocusGained
        jLmensagem.setText("");
    }//GEN-LAST:event_jTSobrenomeFocusGained

    private void jFCpfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFCpfFocusGained
        jLmensagem.setText("");
    }//GEN-LAST:event_jFCpfFocusGained

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastroCliente dialog = new CadastroCliente(new javax.swing.JDialog(), true, true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBLimpar;
    private javax.swing.JButton jBSalvar;
    private javax.swing.JFormattedTextField jFCpf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLmensagem;
    private javax.swing.JTextField jTNome;
    private javax.swing.JTextField jTSobrenome;
    // End of variables declaration//GEN-END:variables

    private void limparCadastro() {
        jTNome.setText("");
        jTSobrenome.setText("");
        jFCpf.setText("");
    }

    private void populaTela() {
        if (novoCadastro) {
            cliente = new Cliente();
        } else {
            jTNome.setText(cliente.getNome());
            jTSobrenome.setText(cliente.getSobrenome());
            jFCpf.setText(cliente.getCpf());
        }
    }

    private boolean validaCampos() {
        if (jTNome.getText().isEmpty() || jTNome.getText().trim().isEmpty()) {
            jLmensagem.setText("Digite o Nome");
            return false;
        }
        if (jTSobrenome.getText().isEmpty() || jTSobrenome.getText().trim().isEmpty()) {
           jLmensagem.setText("Digite o Sobrenome");
            return false;
        }
        if (jFCpf.getText().replace(".", "").replace("-", "").isEmpty() || jFCpf.getText().replace(".", "").replace("-", "").trim().isEmpty()) {
            jLmensagem.setText("Digite o CPF");
            return false;
        }
        return true;
    }
}
