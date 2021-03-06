/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Service.DbConnection;
//import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import sesssionSpecial.sessionDashboard;

/**
 *
 * @author vivekjeevanarajh
 */
public class SessionDetails extends javax.swing.JFrame {

    /**
     * Creates new form SessionDetails
     */
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet result = null;
    
    PreparedStatement pst1 = null;
    ResultSet result1 = null;
    
    PreparedStatement pst2 = null;
    ResultSet result2 = null;
    
    
    
    String k;
    public SessionDetails() {
        initComponents();
    }

    public SessionDetails(String sessionID) {
        initComponents();
        
        sessionID_lbl.setText(sessionID);
        con = DbConnection.ConnectDb();
        FillTagsCombo();
        
        FillSubjectCombo();
        
        subject_id.setEditable(false);
    }
     
    
    private void FillTagsCombo(){
        try{
            String sql ="select * from tag";
            
            pst = con.prepareStatement(sql);
            result = pst.executeQuery();
            
            while(result.next()){
                String tag_name = result.getString("tag_name");
                tagsCombo.addItem(tag_name);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    private void FillSubGroupCombo(String tag){
        try{
           
                    String sql ="select * from subgroupIdNew ";
           
                    pst = con.prepareStatement(sql);
                    result = pst.executeQuery();
            
                while(result.next()){
                    String subgroupID = result.getString("subgroupID");
                    groupCombo.addItem(subgroupID);
                    
                }
            }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void FillMainGroupCombo(String tag){
        try{
           
                    String sql ="select * from groupIdNew ";
           
                    pst = con.prepareStatement(sql);
                    result = pst.executeQuery();
            
                while(result.next()){
                    String subgroupID = result.getString("groupID");
                    groupCombo.addItem(subgroupID);
                    
                }
            }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void FillSubjectCombo(){
        try{
            String sql ="select * from subject";
            
            pst = con.prepareStatement(sql);
            result = pst.executeQuery();
            
            while(result.next()){
                String subject_name = result.getString("subject_name");
                String subjectID = result.getString("subject_code");
                subjectCombo.addItem(subject_name);
                
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void setSubject(String s){
        this.k = s;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        tagsCombo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        subjectCombo = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        subject_id = new javax.swing.JTextField();
        lockTag = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        groupCombo = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        number_of_student = new javax.swing.JTextField();
        duration_h = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        sessionID_lbl = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cancel = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 204, 255));

        jPanel4.setBackground(new java.awt.Color(65, 105, 170));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Session Details");

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Select Tags:");

        tagsCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tagsComboItemStateChanged(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Select Subject:");

        subjectCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                subjectComboItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Subject ID:");

        subject_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subject_idActionPerformed(evt);
            }
        });

        lockTag.setText("Lock tag");
        lockTag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lockTagActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(tagsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lockTag, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(subjectCombo, 0, 232, Short.MAX_VALUE)
                            .addComponent(subject_id))
                        .addContainerGap())))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tagsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(lockTag))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subjectCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subject_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText(" Student Group:");

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Num Of Students:");

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Duration:");

        number_of_student.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                number_of_studentKeyPressed(evt);
            }
        });

        duration_h.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(groupCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(duration_h, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(number_of_student))))))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(groupCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(number_of_student, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(duration_h, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Session ID :");

        sessionID_lbl.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        sessionID_lbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sessionID_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(sessionID_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CREATE SESSION");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 927, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        cancel.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jButton2.setText("CREATE SESSION");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(565, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(23, 23, 23)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cancel, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addGap(28, 28, 28))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(357, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(391, 391, 391)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addGap(18, 18, 18)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void subject_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subject_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subject_idActionPerformed

    private void subjectComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_subjectComboItemStateChanged
        // TODO add your handling code here:
        String SubjectID = subjectCombo.getSelectedItem().toString();
         
        try{
            String sql ="select * from subject where subject_name ='"+SubjectID+"'";
            
            pst1 = con.prepareStatement(sql);
            result1 = pst1.executeQuery();
            
            
                String subject_name = result1.getString("subject_name");
                String subjectID = result1.getString("subject_code");
                subject_id.setText(subjectID);
                
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_subjectComboItemStateChanged

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
        sessionDashboard s = new sessionDashboard();
        s.setVisible(true);
        s.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dispose();
         
    }//GEN-LAST:event_cancelActionPerformed

    private void number_of_studentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_number_of_studentKeyPressed
        // TODO add your handling code here:
        
        int key = evt.getKeyCode();
        
        if((key>=evt.VK_0 && key<=evt.VK_9) || (key>=evt.VK_NUMPAD0 && key<=evt.VK_NUMPAD9) || (key==KeyEvent.VK_BACK_SPACE)){
            number_of_student.setEditable(true);
            number_of_student.setBackground(Color.white);
        }
        else{
            number_of_student.setEditable(false);
            number_of_student.setBackground(Color.red);
        }
    }//GEN-LAST:event_number_of_studentKeyPressed

    private void tagsComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tagsComboItemStateChanged
        // TODO add your handling code here:
        
 
    }//GEN-LAST:event_tagsComboItemStateChanged

    private void lockTagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lockTagActionPerformed
        // TODO add your handling code here:
         String k =tagsCombo.getSelectedItem().toString();
         int n=groupCombo.getItemCount();
//       
       if(n>0){
           groupCombo.removeAllItems();
       }
       if("labs".equals(k)){
           System.out.println(k);
           FillSubGroupCombo(k);
       }
       else{
           FillMainGroupCombo(k);
           System.out.println("Chexk");
       }
        
    }//GEN-LAST:event_lockTagActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
          if(number_of_student.getText().isEmpty()){
               String message = "number of student field should be filled!";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                JOptionPane.ERROR_MESSAGE);

        }
           else{
        
        System.out.println(duration_h.getValue().toString());
         String sql = "INSERT INTO session1(session_id,tag,subject,subject_code,student_group,number_of_student,duration) VALUES(?,?,?,?,?,?,?)";
       
   
           
        try{
            pst2 = con.prepareStatement(sql);
            pst2.setString(1, sessionID_lbl.getText());
            pst2.setString(2, tagsCombo.getSelectedItem().toString());
            pst2.setString(3, subjectCombo.getSelectedItem().toString());
            pst2.setString(4, subject_id.getText());
            pst2.setString(5, groupCombo.getSelectedItem().toString());
            pst2.setString(6, number_of_student.getText());
            pst2.setString(7, duration_h.getValue().toString());
            
            
            pst2.execute();
            JOptionPane.showMessageDialog(null, "Inserted Successfully");
            dispose();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
      }
        
       
    }//GEN-LAST:event_jButton2ActionPerformed

 
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
            java.util.logging.Logger.getLogger(SessionDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SessionDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SessionDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SessionDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SessionDetails().setVisible(true);
                
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    private javax.swing.JSpinner duration_h;
    private javax.swing.JComboBox<String> groupCombo;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JButton lockTag;
    private javax.swing.JTextField number_of_student;
    private javax.swing.JLabel sessionID_lbl;
    private javax.swing.JComboBox<String> subjectCombo;
    private javax.swing.JTextField subject_id;
    private javax.swing.JComboBox<String> tagsCombo;
    // End of variables declaration//GEN-END:variables
}
