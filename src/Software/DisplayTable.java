/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Software;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usama
 */
public class DisplayTable extends javax.swing.JFrame
{
    
    FileScanner fs;

    /**
     * Creates new form DisplayDiagram
     */
    public DisplayTable()
    {
        initComponents();
        path.setEditable(false);
        this.setResizable(false);
        diagramBtn.setEnabled(false);
        tableBtn.setEnabled(false);
        Clear.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tableBtn = new javax.swing.JButton();
        path = new javax.swing.JTextField();
        diagramBtn = new javax.swing.JButton();
        SFolder = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Clear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableBtn.setText("Table");
        tableBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tableBtnActionPerformed(evt);
            }
        });
        jPanel1.add(tableBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 130, 53));
        jPanel1.add(path, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 520, 50));

        diagramBtn.setText("Diagram");
        diagramBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diagramBtnActionPerformed(evt);
            }
        });
        jPanel1.add(diagramBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, 130, 53));

        SFolder.setText("Select Folder");
        SFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SFolderActionPerformed(evt);
            }
        });
        jPanel1.add(SFolder, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 53));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 980, 80));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Class Name", "No. of Variable", "Variables", "No. of Methods", "Methods"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 950, -1));

        Clear.setText("Clear");
        Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearActionPerformed(evt);
            }
        });
        getContentPane().add(Clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 530, 110, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableBtnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_tableBtnActionPerformed
    {//GEN-HEADEREND:event_tableBtnActionPerformed
        for (int i = 0; i < fs.classList.size(); i++)
        {
            boolean flag1 = true;
            ObjectInfo a = fs.classList.get(i);
            for (int j = 0, k = 0; j < a.function_List.size() || k < a.object_List.size(); j++, k++)
            {
                String[] ABC = new String[5];
                
                if (flag1)
                {
                    ABC[0] = a.name;
                    ABC[1] = "" + a.object_List.size();
                    ABC[3] = "" + a.function_List.size();
                    flag1 = false;
                }
                else
                {
                    ABC[0] = "";
                    ABC[1] = "";
                    ABC[3] = "";
                }
                if (j < a.function_List.size())
                {
                    ABC[4] = a.function_List.get(j);
                }
                else
                {
                    ABC[4] = "";
                }
                if (k < a.object_List.size())
                {
                    ABC[2] = a.object_List.get(k);
                }
                else
                {
                    ABC[2] = "";
                }
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.addRow(ABC);
            }
            
        }
        tableBtn.setEnabled(false);
    }//GEN-LAST:event_tableBtnActionPerformed

    private void diagramBtnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_diagramBtnActionPerformed
    {//GEN-HEADEREND:event_diagramBtnActionPerformed
        DisplayDiagram R = new DisplayDiagram(fs);
        R.setVisible(true);
    }//GEN-LAST:event_diagramBtnActionPerformed

    private void SFolderActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_SFolderActionPerformed
    {//GEN-HEADEREND:event_SFolderActionPerformed
        fs = new FileScanner();
        
        fs.selectFolder();
        
        fs.printClasses2();
        
        path.setText(fs.showPath);
        SFolder.setEnabled(false);
        diagramBtn.setEnabled(true);
        tableBtn.setEnabled(true);
        Clear.setEnabled(true);

    }//GEN-LAST:event_SFolderActionPerformed

    private void ClearActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ClearActionPerformed
    {//GEN-HEADEREND:event_ClearActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        path.setText("");
        diagramBtn.setEnabled(false);
        tableBtn.setEnabled(false);
        Clear.setEnabled(false);
        SFolder.setEnabled(true);
    }//GEN-LAST:event_ClearActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(DisplayTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(DisplayTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(DisplayTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(DisplayTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and DisplayDiagram the form */
        java.awt.EventQueue.invokeLater(() ->
        {
            new DisplayTable().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Clear;
    private javax.swing.JButton SFolder;
    private javax.swing.JButton diagramBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField path;
    private javax.swing.JButton tableBtn;
    // End of variables declaration//GEN-END:variables

}
