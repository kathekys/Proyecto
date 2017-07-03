package proyectofinal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Clientes extends javax.swing.JFrame {

    
     VerificarCedula c = new VerificarCedula();
    public DefaultTableModel model;

    public Clientes() {
        initComponents();
         TituloTablaClientes();
         
          cargarTabla("");
//        h1 = new Thread((Runnable) this);
        // h1.start();
        cargarDatos();
    }
    // DefaultTableModel modeloTabla = new DefaultTableModel();

   public void guardarCliente() {
        Connection cn = (new ConexionBD()).conectar();

        if (jTextField_Cédula.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingresar cedula");
           jTextField_Cédula.requestFocus();
        } else if (jTextField_Nombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingresar nombre");
            jTextField_Nombre.requestFocus();
        } else if (jTextField_Apellido.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingresar apellido");
            jTextField_Apellido.requestFocus();
        } else if (jTextField_Dirección.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingresar direccion");
           jTextField_Dirección.requestFocus();
        } else if(jTextField_Teléfono.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingresar TELEFONO");
          jTextField_Teléfono.requestFocus();
        }else {
            try {

                String Cedula = jTextField_Cédula.getText().toUpperCase();
                String Nombre = jTextField_Nombre.getText().toUpperCase();
                String Apellido = jTextField_Apellido.getText().toUpperCase();
                String Telefono = jTextField_Teléfono.getText().toUpperCase();
                String Direccion = jTextField_Dirección.getText().toUpperCase();

                String sql = "insert into clientes (CED_CLI, NOM_CLI, APE_CLI, TEL_CLI, DIR_CLI)values(?,?,?,?,?)";

                PreparedStatement psd = cn.prepareStatement(sql);
                psd.setString(1, Cedula);
                psd.setString(2, Nombre);
                psd.setString(3, Apellido);
                 psd.setString(4, Telefono);
                psd.setString(5, Direccion);
               

                if (psd.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Se ha insertado el dato");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se ha insertado el dato");
            }
        }

    }

    public void cargarDatos() {
        jTable_Clientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (jTable_Clientes.getSelectedRow() != -1) {
                    int fila = jTable_Clientes.getSelectedRow();
                    jTextField_Cédula.setText(jTable_Clientes.getValueAt(fila, 0).toString());
                    jTextField_Nombre.setText(jTable_Clientes.getValueAt(fila, 1).toString());
                    jTextField_Apellido.setText(jTable_Clientes.getValueAt(fila, 2).toString());
                    jTextField_Teléfono.setText(jTable_Clientes.getValueAt(fila, 3).toString());
                    jTextField_Dirección.setText(jTable_Clientes.getValueAt(fila, 4).toString());
                    //desbloquear();                    
                }
            }
        });
    }

    public void limpiar() {
//        txtCedula.setText("");
//        txtNombre.setText("");
//        txtApellido.setText("");
//        txtDireccion.setText("");
//        txtBuscarCedula.setText("");
//        txtTelefono.setText("");
    }

    public void bloquear() {
//        txtCedula.setEnabled(true);
//        txtNombre.setEnabled(true);
//        txtApellido.setEnabled(true);
//        txtDireccion.setEnabled(true);
//        txtTelefono.setEnabled(true);
//        btActualizar.setEnabled(false);
//        btEliminar.setEnabled(false);
//        btCancelar.setEnabled(false);
    }

    public void desbloquear() {
//        txtNombre.setEnabled(true);
//        txtApellido.setEnabled(true);
//        txtDireccion.setEnabled(true);
//        txtTelefono.setEnabled(true);
//        btActualizar.setEnabled(true);
//        btEliminar.setEnabled(true);
//        btCancelar.setEnabled(true);
//        txtBuscarCedula.setEnabled(true);

    }

    public void cargarTabla(String dato) {
        Connection cn = (new ConexionBD()).conectar();

        String[] titulo = {"Cedula", "Nombre", "Apellido", "Direccion", "Teléfono"};
        String[] registros = new String[5];
        String sql;
        sql = "SELECT * FROM CLIENTES WHERE CED_CLI LIKE '" + dato + "%'";
        model = new DefaultTableModel(null, titulo);
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("CED_CLI");
                registros[1] = rs.getString("NOM_CLI");
                registros[2] = rs.getString("APE_CLI");
                registros[3] = rs.getString("DIR_CLI");
                registros[4] = rs.getString("TEL_CLI");
                model.addRow(registros);
                jTable_Clientes.setModel(model);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "La tabla CLIENTES tiene problemas al cargarse\n" + ex);
        }
    }

    public void actualizarClientes() {
        Connection cn = (new ConexionBD()).conectar();

        String sql;
        sql = "update cajeros set  NOM_CLI ='" + jTextField_Nombre.getText() + "',"
                + "APE_CLI='" + jTextField_Apellido.getText() + "',"
                + "DIR_CLI='" + jTextField_Dirección.getText() + "',"
                + "TEL_CLI='" + jTextField_Teléfono.getText() + "'"
                + "where CED_CLI='" + jTextField_Cédula.getText() + "'";
        try {
            PreparedStatement psd = cn.prepareStatement(sql);
            int n = psd.executeUpdate();//Ejecutar el 
            if (n > 0) {
                JOptionPane.showMessageDialog(this, "Se actualizo correctamente");
                cargarTabla("");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar" + ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jTextField_BuscadorCed = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField_Cédula = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField_Nombre = new javax.swing.JTextField();
        jTextField_Apellido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField_Dirección = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField_Teléfono = new javax.swing.JTextField();
        BtnIngresar_Cliente = new javax.swing.JButton();
        BtnCancelar_Clientes = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Clientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscador", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14), new java.awt.Color(0, 51, 51))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 0, 51));
        jPanel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jButton1.setText("Buscar");

        buttonGroup1.add(jCheckBox1);
        jCheckBox1.setText("Cedula");

        buttonGroup1.add(jCheckBox2);
        jCheckBox2.setText("Apellido");

        jTextField_BuscadorCed.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_BuscadorCedKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_BuscadorCed, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton1)
                .addComponent(jCheckBox1)
                .addComponent(jCheckBox2)
                .addComponent(jTextField_BuscadorCed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14), new java.awt.Color(0, 51, 51))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Cédula:");

        jTextField_Cédula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_CédulaFocusLost(evt);
            }
        });
        jTextField_Cédula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_CédulaKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Nombre:");

        jTextField_Nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_NombreKeyTyped(evt);
            }
        });

        jTextField_Apellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_ApellidoKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Apellido:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Dirección:");

        jTextField_Dirección.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_DirecciónKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Teléfono:");

        jTextField_Teléfono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_TeléfonoKeyTyped(evt);
            }
        });

        BtnIngresar_Cliente.setText("Agegar");
        BtnIngresar_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnIngresar_ClienteActionPerformed(evt);
            }
        });

        BtnCancelar_Clientes.setText("Cancelar");
        BtnCancelar_Clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelar_ClientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_Nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(jTextField_Cédula, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Apellido))
                        .addGap(19, 300, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField_Teléfono, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField_Dirección, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(BtnIngresar_Cliente)
                                .addGap(18, 18, 18)
                                .addComponent(BtnCancelar_Clientes)
                                .addContainerGap(71, Short.MAX_VALUE))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField_Cédula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_Apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField_Dirección, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnIngresar_Cliente)
                    .addComponent(BtnCancelar_Clientes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField_Teléfono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4))
        );

        jTable_Clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable_Clientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
 public void Insertarfilas() {
   
//        String[] fila = new String[5];
//        fila[0] = jTextField_Cédula.getText();
//        fila[1] = jTextField_Nombre.getText();
//        fila[2] = jTextField_Apellido.getText();
//        fila[3] = jTextField_Teléfono.getText();
//        fila[4] =  jTextField_Dirección.getText();
//        modeloTabla.addRow(fila);
//        jTable_Clientes.setModel(modeloTabla);
        
    }
  public void TituloTablaClientes() {
//        String[] titulo = {"Cédula", "Nombre", "Apellido", "Teléfono", "Dirección"};
//        modeloTabla = new DefaultTableModel(null, titulo);
//        jTable_Clientes.setModel(modeloTabla);
    }
    private void jTextField_CédulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_CédulaKeyTyped
        // TODO add your handling code here:
        SoloIngresoNumeros(evt);
    }//GEN-LAST:event_jTextField_CédulaKeyTyped

    private void jTextField_NombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_NombreKeyTyped
        // TODO add your handling code here:
        
        SoloIngresoLetras(evt);
    }//GEN-LAST:event_jTextField_NombreKeyTyped

    private void jTextField_ApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_ApellidoKeyTyped
        // TODO add your handling code here:
        SoloIngresoLetras(evt);
    }//GEN-LAST:event_jTextField_ApellidoKeyTyped

    private void jTextField_DirecciónKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_DirecciónKeyTyped
        // TODO add your handling code here:
        SoloIngresoLetras(evt);
    }//GEN-LAST:event_jTextField_DirecciónKeyTyped

    private void jTextField_TeléfonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_TeléfonoKeyTyped
        // TODO add your handling code here:
        SoloIngresoNumeros(evt);
    }//GEN-LAST:event_jTextField_TeléfonoKeyTyped

    private void BtnCancelar_ClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelar_ClientesActionPerformed
        // TODO add your handling code here:
        jTextField_Cédula.setText(null);
        jTextField_Apellido.setText(null);
        jTextField_Nombre.setText(null);
        jTextField_Dirección.setText(null);
        jTextField_Teléfono.setText(null);
    }//GEN-LAST:event_BtnCancelar_ClientesActionPerformed

    private void BtnIngresar_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnIngresar_ClienteActionPerformed
        // TODO add your handling code here:
        //Insertarfilas();
        guardarCliente();
        cargarTabla("");
    }//GEN-LAST:event_BtnIngresar_ClienteActionPerformed

    private void jTextField_BuscadorCedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_BuscadorCedKeyReleased
        // TODO add your handling code here:
         cargarTabla(jTextField_BuscadorCed.getText());
    }//GEN-LAST:event_jTextField_BuscadorCedKeyReleased

    private void jTextField_CédulaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_CédulaFocusLost
        // TODO add your handling code here:
        c.validacion(evt);
    }//GEN-LAST:event_jTextField_CédulaFocusLost
    public void SoloIngresoLetras(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();

        if (Character.isDigit(c)) {
            getToolkit().beep();

            evt.consume();

            JOptionPane.showMessageDialog(this, "ERROR: Ingrese solo letras");

        }
    }

    public void SoloIngresoNumeros(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();

        if (Character.isLetter(c)) {
            getToolkit().beep();

            evt.consume();

            JOptionPane.showMessageDialog(this, "ERROR: Ingrese solo números");

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//////        try {
//////            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//////                if ("Nimbus".equals(info.getName())) {
//////                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//////                    break;
//////                }
//////            }
//////        } catch (ClassNotFoundException ex) {
//////            java.util.logging.Logger.getLogger(Secundaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//////        } catch (InstantiationException ex) {
//////            java.util.logging.Logger.getLogger(Secundaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//////        } catch (IllegalAccessException ex) {
//////            java.util.logging.Logger.getLogger(Secundaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//////        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//////            java.util.logging.Logger.getLogger(Secundaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//////        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Clientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCancelar_Clientes;
    private javax.swing.JButton BtnIngresar_Cliente;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_Clientes;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField_Apellido;
    private javax.swing.JTextField jTextField_BuscadorCed;
    private javax.swing.JTextField jTextField_Cédula;
    private javax.swing.JTextField jTextField_Dirección;
    private javax.swing.JTextField jTextField_Nombre;
    private javax.swing.JTextField jTextField_Teléfono;
    // End of variables declaration//GEN-END:variables
}
