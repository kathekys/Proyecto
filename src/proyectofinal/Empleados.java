/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Admin
 */
public class Empleados extends javax.swing.JFrame {

    /**
     * Creates new form Empleados
     */
    
    VerificarCedula c = new VerificarCedula();
    public DefaultTableModel model;
    public Empleados() {
        initComponents();
     cargarTabla("");
//        h1 = new Thread((Runnable) this);
        // h1.start();
        cargarDatos();
    }
   
public void guardarEmpeleados() {
        Connection cn = (new ConexionBD()).conectar();

        if (jTextField_CedEmpleado.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingresar cedula");
           jTextField_CedEmpleado.requestFocus();
        } else if (jTextField_NombreEmpleado.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingresar nombre");
           jTextField_NombreEmpleado.requestFocus();
        } else if (jTextField_ApellidoEmpleado.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingresar apellido");
            jTextField_ApellidoEmpleado.requestFocus();
        } else if (jTextField_DireccionEmpleado.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingresar direccion");
           jTextField_DireccionEmpleado.requestFocus();
        } else if(jTextField_TelefonoEmpleado.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingresar TELEFONO");
          jTextField_TelefonoEmpleado.requestFocus();
        }else {
            try {

                String Cedula = jTextField_CedEmpleado.getText().toUpperCase();
                String Nombre = jTextField_NombreEmpleado.getText().toUpperCase();
                String Apellido = jTextField_ApellidoEmpleado.getText().toUpperCase();
                String Telefono = jTextField_TelefonoEmpleado.getText().toUpperCase();
                String Direccion = jTextField_DireccionEmpleado.getText().toUpperCase();

                String sql = "insert into empleados (CED_EMP, NOM_EMP, APE_EMP, TEL_EMP, DIR_EMP)values(?,?,?,?,?)";

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
        jTable_Empleados.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if ( jTable_Empleados.getSelectedRow() != -1) {
                    int fila =  jTable_Empleados.getSelectedRow();
                    jTextField_CedEmpleado.setText( jTable_Empleados.getValueAt(fila, 0).toString());
                    jTextField_NombreEmpleado.setText( jTable_Empleados.getValueAt(fila, 1).toString());
                    jTextField_ApellidoEmpleado.setText( jTable_Empleados.getValueAt(fila, 2).toString());
                    jTextField_TelefonoEmpleado.setText( jTable_Empleados.getValueAt(fila, 3).toString());
                    jTextField_DireccionEmpleado.setText( jTable_Empleados.getValueAt(fila, 4).toString());
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
        sql = "SELECT * FROM EMPLEADOS WHERE CED_EMP LIKE '" + dato + "%'";
        model = new DefaultTableModel(null, titulo);
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("CED_EMP");
                registros[1] = rs.getString("NOM_EMP");
                registros[2] = rs.getString("APE_EMP");
                registros[3] = rs.getString("DIR_EMP");
                registros[4] = rs.getString("TEL_EMP");
                model.addRow(registros);
                jTable_Empleados.setModel(model);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "La tabla CLIENTES tiene problemas al cargarse\n" + ex);
        }
    }

    public void actualizarClientes() {
        Connection cn = (new ConexionBD()).conectar();

        String sql;
        sql = "update cajeros set  NOM_EMP ='" + jTextField_NombreEmpleado.getText() + "',"
                + "APE_EMP='" + jTextField_ApellidoEmpleado.getText() + "',"
                + "DIR_EMP='" + jTextField_DireccionEmpleado.getText() + "',"
                + "TEL_EMP='" + jTextField_TelefonoEmpleado.getText() + "'"
                + "where CED_EMP='" + jTextField_CedEmpleado.getText() + "'";
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField_CedEmpleado = new javax.swing.JTextField();
        jTextField_NombreEmpleado = new javax.swing.JTextField();
        jTextField_ApellidoEmpleado = new javax.swing.JTextField();
        jTextField_DireccionEmpleado = new javax.swing.JTextField();
        jTextField_TelefonoEmpleado = new javax.swing.JTextField();
        jButton_IngresarEmpleado = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Empleados = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jTextField_BuscadorEmp = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Empleados");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14), new java.awt.Color(0, 51, 51))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Cédula:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Nombre:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Direccion:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Telefono:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Apellido:");

        jTextField_CedEmpleado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_CedEmpleadoFocusLost(evt);
            }
        });

        jButton_IngresarEmpleado.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton_IngresarEmpleado.setText("Ingresar");
        jButton_IngresarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_IngresarEmpleadoActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_DireccionEmpleado))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_NombreEmpleado)
                            .addComponent(jTextField_ApellidoEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(jTextField_CedEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(28, 28, 28))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jButton_IngresarEmpleado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_TelefonoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(45, 45, 45))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_CedEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField_TelefonoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_NombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField_ApellidoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField_DireccionEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_IngresarEmpleado)
                    .addComponent(jButton2))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jTable_Empleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable_Empleados);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscador", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14), new java.awt.Color(0, 51, 51))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 0, 51));
        jPanel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jTextField_BuscadorEmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_BuscadorEmpKeyReleased(evt);
            }
        });

        jButton1.setText("Buscar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jTextField_BuscadorEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTextField_BuscadorEmp)
                .addComponent(jButton1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_IngresarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_IngresarEmpleadoActionPerformed
        // TODO add your handling code here:
      guardarEmpeleados();
        cargarTabla("");
    }//GEN-LAST:event_jButton_IngresarEmpleadoActionPerformed

    private void jTextField_BuscadorEmpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_BuscadorEmpKeyReleased
        // TODO add your handling code here:
        cargarTabla(jTextField_BuscadorEmp.getText());
    }//GEN-LAST:event_jTextField_BuscadorEmpKeyReleased

    private void jTextField_CedEmpleadoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_CedEmpleadoFocusLost
        // TODO add your handling code here:
          c.validacion(evt);
    }//GEN-LAST:event_jTextField_CedEmpleadoFocusLost

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
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Empleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton_IngresarEmpleado;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_Empleados;
    private javax.swing.JTextField jTextField_ApellidoEmpleado;
    private javax.swing.JTextField jTextField_BuscadorEmp;
    private javax.swing.JTextField jTextField_CedEmpleado;
    private javax.swing.JTextField jTextField_DireccionEmpleado;
    private javax.swing.JTextField jTextField_NombreEmpleado;
    private javax.swing.JTextField jTextField_TelefonoEmpleado;
    // End of variables declaration//GEN-END:variables
}
