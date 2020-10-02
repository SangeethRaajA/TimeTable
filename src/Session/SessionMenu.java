/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Service.DbConnection;
import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author vivekjeevanarajh
 */
public class SessionMenu extends javax.swing.JFrame {

    /**
     * Creates new form SessionMenu
     */
      Connection con = null;
    PreparedStatement pst = null;
    ResultSet result = null;
    
    
    PreparedStatement pst1 = null;
    ResultSet result1 = null;
    
    PreparedStatement pst2 = null;
    ResultSet result2 = null;
    
    PreparedStatement pst3 = null;
    ResultSet result3 = null;
    
    PreparedStatement pst4 = null;
    ResultSet result4 = null;
    
    PreparedStatement pst5 = null;
    ResultSet result5 = null;
    
     PreparedStatement pst6 = null;
    ResultSet result6 = null;
    
     PreparedStatement pst7 = null;
    ResultSet result7 = null;
    
     PreparedStatement pst8 = null;
    ResultSet result8 = null;
    
     PreparedStatement pst9 = null;
    ResultSet result9 = null;
    
     PreparedStatement pst10 = null;
    ResultSet result10 = null;
    
     PreparedStatement pst11 = null;
    ResultSet result11 = null;
    
    
    CardLayout cardLayout;
    
    public SessionMenu() {
        initComponents();
        con = DbConnection.ConnectDb();
        FillLecturesCombo();
         FillSessionList();
        FillGroupCombo();
        FillSubjectCombo();
        FillLectures1Combo();
        
        
        cardLayout = (CardLayout) pnl_cards.getLayout();
    }
    
     private void FillLecturesCombo(){
        try{
            String sql ="select * from lecture1";
            
            pst = con.prepareStatement(sql);
            result = pst.executeQuery();
            
            while(result.next()){
                String lectureName = result.getString("name");
                lecturesCombo.addItem(lectureName);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

     private void FillLecturesList(){
         String sessionID = session_id.getText().toString();
         DefaultListModel model = new DefaultListModel(); 
        try{
            String sql ="select * from sessionLectures where sessionID= '"+sessionID+"'";
            
            pst = con.prepareStatement(sql);
            result = pst.executeQuery();
            
            while(result.next()){
                String lectureName = result.getString("lectureName");
//                lecture_list.setToolTipText(lectureName);
                model.addElement(lectureName);
            }
            lecture_list.setModel(model);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
      private boolean isExistLecture(String sid){
         
          String sessionID = session_id.getText().toString();
          int s = 0;
        
        try{
            String sql ="select * from sessionLectures where sessionID= '"+sessionID+"'";
            
            pst = con.prepareStatement(sql);
            result = pst.executeQuery();
             System.out.println(s);
            while(result.next()){
                String lectureName = result.getString("lectureName");
                String k = lecturesCombo.getSelectedItem().toString();
                System.out.println(k);
                if(lectureName.equals(k)){
                    s++;
                }
//                lecture_list.setToolTipText(lectureName);
               
            }
           
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        if(s>0){
            return false;
        }
        else {
            return true;
        }
    }
    
    
       private boolean isExistSession(){
         
          String sessionID = session_id.getText().toString();
          int s = 0;
        
        try{
            String sql ="select * from session1 where session_id= '"+sessionID+"'";
            
            pst = con.prepareStatement(sql);
            result = pst.executeQuery();
             System.out.println(result);
            while(result.next()){
             
                    s++;
                
//                lecture_list.setToolTipText(lectureName);
               
            }
           
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        if(s>0){
            return false;
        }
        else {
            return true;
        }
    }
       
       
       private void FillSessionList(){
        
         DefaultListModel model = new DefaultListModel(); 
        try{
            String sql ="select * from session1";
            
            pst = con.prepareStatement(sql);
            result = pst.executeQuery();
            
            while(result.next()){
                String session_id = result.getString("session_id");
//                lecture_list.setToolTipText(lectureName);
                model.addElement(session_id);
            }
            sessions.setModel(model);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
       
       
       
       ////////////////////////////////////////////////////////////////////////////////////////////////
       
       
        private void FillSubjectCombo(){
        try{
            String sql ="select distinct subject from session1";
            
            pst = con.prepareStatement(sql);
            result = pst.executeQuery();
            
            while(result.next()){
                String subject_name = result.getString("subject");
                
                subjectCombo.addItem(subject_name);
                
            }
            
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void FillSessionList_search_subject(){
       String subjectSearch = subjectCombo.getSelectedItem().toString();
         DefaultListModel model = new DefaultListModel(); 
        
        try{
            String sql ="select * from session1 where subject='"+subjectSearch+"'";
            
            pst1 = con.prepareStatement(sql);
            result1 = pst1.executeQuery();
            
            while(result1.next()){
                String session_id = result1.getString("session_id");
//                lecture_list.setToolTipText(lectureName);
                model.addElement(session_id);
            }
            session_Subject_search.setModel(model);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
  
         
     private void setSessionDetailsSubject(String sid){
        try{
            String sql ="select * from session1 where session_id ='"+sid+"'";
            
            pst2 = con.prepareStatement(sql);
            result2 = pst2.executeQuery();
            
            
                String subject_nameData = result2.getString("subject");
                String subjectIDData = result2.getString("subject_code");
                String sessionIDData = result2.getString("session_id");
                String tagData = result2.getString("tag");
                String groupData = result2.getString("student_group");
                String noOfStudentData = result2.getString("number_of_student");
                String durationData = result2.getString("duration");
                tag.setText(tagData);
                group.setText(groupData);
                subject.setText(subject_nameData);
                subject_code.setText("("+subjectIDData+")");
                noOfStudent.setText(noOfStudentData);
                duration.setText("("+durationData+")");
                
                
                
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
         private void setSessionLectureDetailsSubject(String sid){
        try{
            String sql ="select * from sessionLectures where sessionID ='"+sid+"'";
            
            pst3 = con.prepareStatement(sql);
            result3 = pst3.executeQuery();
            String name="";
            while(result3.next()){
                name =name+""+ result3.getString("lectureName")+", ";
                System.out.println(name);
            }
            
              System.out.println(name);
                lectureName.setText(name);
                 
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
         
         
   //************************************************************************* 
         
         
   //Group base Search coding********************************************
     
     private void FillGroupCombo(){
        try{
            String sql ="select distinct student_group  from session1";
            
            pst4 = con.prepareStatement(sql);
            result4 = pst4.executeQuery();
            
            while(result4.next()){
                String student_group = result4.getString("student_group");
                
                groupCombo.addItem(student_group);
                
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
         
         
     private void FillSessionList_search_Group(){
       String groupSearch = groupCombo.getSelectedItem().toString();
         DefaultListModel model1 = new DefaultListModel(); 
        
        try{
            String sql ="select * from session1 where student_group='"+groupSearch+"'";
            
            pst5 = con.prepareStatement(sql);
            result5 = pst5.executeQuery();
            
            while(result5.next()){
                String session_id = result5.getString("session_id");
//                lecture_list.setToolTipText(lectureName);
                model1.addElement(session_id);
            }
            session_Group_search.setModel(model1);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
     
     private void setSessionDetailsGroup(String sid){
        try{
            String sql ="select * from session1 where session_id ='"+sid+"'";
            
            pst6 = con.prepareStatement(sql);
            result6 = pst6.executeQuery();
            
            
                String subject_nameData = result6.getString("subject");
                String subjectIDData = result6.getString("subject_code");
                String sessionIDData = result6.getString("session_id");
                String tagData = result6.getString("tag");
                String groupData = result6.getString("student_group");
                String noOfStudentData = result6.getString("number_of_student");
                String durationData = result6.getString("duration");
                tag1.setText(tagData);
                group1.setText(groupData);
                subject1.setText(subject_nameData);
                subject_code1.setText("("+subjectIDData+")");
                noOfStudent1.setText(noOfStudentData);
                duration1.setText("("+durationData+")");
                
                
                
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
         private void setSessionLectureDetailsGroup(String sid){
        try{
            String sql ="select * from sessionLectures where sessionID ='"+sid+"'";
            
            pst7 = con.prepareStatement(sql);
            result7 = pst7.executeQuery();
            String name="";
            while(result7.next()){
                name =name+""+ result7.getString("lectureName")+", ";
                System.out.println(name);
            }
            
              System.out.println(name);
                lectureName1.setText(name);
                 
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
         
          
     //**********************************************************************************
         
    //Lectures base Search coding********************************************
     
     private void FillLectures1Combo(){
        try{
            String sql ="select distinct lectureName  from sessionLectures";
            
            pst8 = con.prepareStatement(sql);
            result8 = pst8.executeQuery();
            
            while(result8.next()){
                String student_group = result8.getString("lectureName");
                
                lectureCombo.addItem(student_group);
                
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
         
         
     private void FillSessionList_search_Lecture(){
       String LectureSearch = lectureCombo.getSelectedItem().toString();
         DefaultListModel model1 = new DefaultListModel(); 
        
        try{
            String sql ="select * from sessionLectures where lectureName='"+LectureSearch+"'";
            
            pst9 = con.prepareStatement(sql);
            result9 = pst9.executeQuery();
            
            while(result9.next()){
                String session_id = result9.getString("sessionID");
//                lecture_list.setToolTipText(lectureName);
                model1.addElement(session_id);
            }
            session_Lecture_search.setModel(model1);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
     
     private void setSessionDetailsLecture(String sid){
        try{
            String sql ="select * from session1 where session_id ='"+sid+"'";
            
            pst10 = con.prepareStatement(sql);
            result10 = pst10.executeQuery();
            
            
                String subject_nameData = result10.getString("subject");
                String subjectIDData = result10.getString("subject_code");
                String sessionIDData = result10.getString("session_id");
                String tagData = result10.getString("tag");
                String groupData = result10.getString("student_group");
                String noOfStudentData = result10.getString("number_of_student");
                String durationData = result10.getString("duration");
                tag2.setText(tagData);
                group2.setText(groupData);
                subject2.setText(subject_nameData);
                subject_code2.setText("("+subjectIDData+")");
                noOfStudent2.setText(noOfStudentData);
                duration2.setText("("+durationData+")");
                
                
                
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
         private void setSessionLectureDetailsLecture(String sid){
        try{
            String sql ="select * from sessionLectures where sessionID ='"+sid+"'";
            
            pst11 = con.prepareStatement(sql);
            result11 = pst11.executeQuery();
            String name="";
            while(result11.next()){
                name =name+""+ result11.getString("lectureName")+", ";
                System.out.println(name);
            }
            
              System.out.println(name);
                lectureName2.setText(name);
                 
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
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

        jPanel1 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        lectureMenu_btn = new javax.swing.JButton();
        sessionMenu_btn = new javax.swing.JButton();
        subjectMenu_btn = new javax.swing.JButton();
        pnl_cards = new javax.swing.JPanel();
        createSession_card_pnl = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        session_id = new javax.swing.JTextField();
        lecturesCombo = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        addSessionLecture_btn = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lecture_list = new javax.swing.JList<>();
        session_continue_btn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        showSession_card_pnl = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        sessions = new javax.swing.JList<>();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        lectureName = new javax.swing.JLabel();
        tag = new javax.swing.JLabel();
        group = new javax.swing.JLabel();
        subject = new javax.swing.JLabel();
        subject_code = new javax.swing.JLabel();
        noOfStudent = new javax.swing.JLabel();
        duration = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        searchSession_card_pnl = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        lectureName2 = new javax.swing.JLabel();
        tag2 = new javax.swing.JLabel();
        group2 = new javax.swing.JLabel();
        subject2 = new javax.swing.JLabel();
        subject_code2 = new javax.swing.JLabel();
        noOfStudent2 = new javax.swing.JLabel();
        duration2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        session_Lecture_search = new javax.swing.JList<>();
        jLabel12 = new javax.swing.JLabel();
        lectureCombo = new javax.swing.JComboBox<>();
        jPanel17 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        search_group_btn = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        lectureName1 = new javax.swing.JLabel();
        tag1 = new javax.swing.JLabel();
        group1 = new javax.swing.JLabel();
        subject1 = new javax.swing.JLabel();
        subject_code1 = new javax.swing.JLabel();
        noOfStudent1 = new javax.swing.JLabel();
        duration1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        session_Group_search = new javax.swing.JList<>();
        jLabel14 = new javax.swing.JLabel();
        groupCombo = new javax.swing.JComboBox<>();
        jPanel21 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        search_subject_btn = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        lectureName3 = new javax.swing.JLabel();
        tag3 = new javax.swing.JLabel();
        group3 = new javax.swing.JLabel();
        subject3 = new javax.swing.JLabel();
        subject_code3 = new javax.swing.JLabel();
        noOfStudent3 = new javax.swing.JLabel();
        duration3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        session_Subject_search = new javax.swing.JList<>();
        jLabel16 = new javax.swing.JLabel();
        subjectCombo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 204, 255));

        jPanel1.setBackground(new java.awt.Color(84, 167, 237));

        jSplitPane1.setDividerSize(1);

        jPanel2.setBackground(new java.awt.Color(84, 167, 237));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lectureMenu_btn.setBackground(new java.awt.Color(153, 204, 255));
        lectureMenu_btn.setText("SHOW ALL SESSION");
        lectureMenu_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lectureMenu_btnActionPerformed(evt);
            }
        });
        jPanel2.add(lectureMenu_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 150, 60));

        sessionMenu_btn.setBackground(new java.awt.Color(153, 204, 255));
        sessionMenu_btn.setText("CREATE SESSION");
        sessionMenu_btn.setName(""); // NOI18N
        sessionMenu_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sessionMenu_btnActionPerformed(evt);
            }
        });
        jPanel2.add(sessionMenu_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 150, 60));

        subjectMenu_btn.setBackground(new java.awt.Color(153, 204, 255));
        subjectMenu_btn.setText("SEARCH SESSION");
        subjectMenu_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subjectMenu_btnActionPerformed(evt);
            }
        });
        jPanel2.add(subjectMenu_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 150, 60));

        jSplitPane1.setLeftComponent(jPanel2);

        pnl_cards.setBackground(new java.awt.Color(153, 204, 255));
        pnl_cards.setLayout(new java.awt.CardLayout());

        createSession_card_pnl.setBackground(new java.awt.Color(102, 204, 255));

        jPanel4.setBackground(new java.awt.Color(84, 167, 237));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Mshtakan", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Create Session");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel5)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel7.setBackground(new java.awt.Color(153, 204, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Enter Session ID:");

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Select Lecture:");

        addSessionLecture_btn.setText("ADD LECTURE FOR SESSION");
        addSessionLecture_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSessionLecture_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(addSessionLecture_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lecturesCombo, 0, 232, Short.MAX_VALUE)
                            .addComponent(session_id))
                        .addGap(0, 7, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(session_id, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lecturesCombo, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addSessionLecture_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(153, 204, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane1.setViewportView(lecture_list);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        session_continue_btn.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        session_continue_btn.setText("Continue");
        session_continue_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                session_continue_btnActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Luminari", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Select Lecture");

        javax.swing.GroupLayout createSession_card_pnlLayout = new javax.swing.GroupLayout(createSession_card_pnl);
        createSession_card_pnl.setLayout(createSession_card_pnlLayout);
        createSession_card_pnlLayout.setHorizontalGroup(
            createSession_card_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(createSession_card_pnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(createSession_card_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createSession_card_pnlLayout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(session_continue_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        createSession_card_pnlLayout.setVerticalGroup(
            createSession_card_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createSession_card_pnlLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(createSession_card_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(session_continue_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        pnl_cards.add(createSession_card_pnl, "createSession");

        showSession_card_pnl.setBackground(new java.awt.Color(153, 204, 255));

        jPanel5.setBackground(new java.awt.Color(84, 167, 237));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Mshtakan", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Show All Session");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel6)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(102, 204, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        sessions.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                sessionsValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(sessions);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(102, 204, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel12.setBackground(new java.awt.Color(204, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lectureName.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lectureName.setText("Lecture Name");

        tag.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        tag.setText("Tag");

        group.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        group.setText("group");

        subject.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        subject.setText("Subject");

        subject_code.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        subject_code.setText("SubjCode");

        noOfStudent.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        noOfStudent.setText("Student number");

        duration.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        duration.setText("Duration");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(group, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tag, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(subject, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subject_code, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(noOfStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(duration, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lectureName, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lectureName, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subject, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subject_code, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tag, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(group, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noOfStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(duration, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Mshtakan", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Sessions IDS:");

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Mshtakan", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Session Details");

        javax.swing.GroupLayout showSession_card_pnlLayout = new javax.swing.GroupLayout(showSession_card_pnl);
        showSession_card_pnl.setLayout(showSession_card_pnlLayout);
        showSession_card_pnlLayout.setHorizontalGroup(
            showSession_card_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showSession_card_pnlLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(showSession_card_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(showSession_card_pnlLayout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(showSession_card_pnlLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(77, 77, 77))
        );
        showSession_card_pnlLayout.setVerticalGroup(
            showSession_card_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showSession_card_pnlLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(showSession_card_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(showSession_card_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pnl_cards.add(showSession_card_pnl, "showSession");

        searchSession_card_pnl.setBackground(new java.awt.Color(102, 204, 255));

        jPanel13.setBackground(new java.awt.Color(102, 204, 255));

        jLabel11.setText("Enter Lecture : ");

        jButton3.setText("Search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel14.setBackground(new java.awt.Color(102, 204, 255));

        jPanel15.setBackground(new java.awt.Color(153, 204, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel16.setBackground(new java.awt.Color(204, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lectureName2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lectureName2.setText("Lecture Name");

        tag2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        tag2.setText("Tag");

        group2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        group2.setText("group");

        subject2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        subject2.setText("Subject");

        subject_code2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        subject_code2.setText("SubjCode");

        noOfStudent2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        noOfStudent2.setText("Student number");

        duration2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        duration2.setText("Duration");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(group2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tag2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(subject2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subject_code2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(noOfStudent2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(duration2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lectureName2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lectureName2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subject_code2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subject2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tag2)
                .addGap(16, 16, 16)
                .addComponent(group2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noOfStudent2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(duration2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        session_Lecture_search.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                session_Lecture_searchValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(session_Lecture_search);

        jLabel12.setText("Sessions:");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        lectureCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lectureComboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(41, 41, 41)
                        .addComponent(lectureCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addGap(23, 23, 23))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jButton3)
                    .addComponent(lectureCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lecture", jPanel13);

        jPanel17.setBackground(new java.awt.Color(102, 204, 255));

        jLabel13.setText("Enter Group : ");

        search_group_btn.setText("Search");
        search_group_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_group_btnActionPerformed(evt);
            }
        });

        jPanel18.setBackground(new java.awt.Color(153, 204, 255));

        jPanel19.setBackground(new java.awt.Color(153, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel20.setBackground(new java.awt.Color(102, 204, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lectureName1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lectureName1.setText("Lecture Name");

        tag1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        tag1.setText("Tag");

        group1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        group1.setText("group");

        subject1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        subject1.setText("Subject");

        subject_code1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        subject_code1.setText("SubjCode");

        noOfStudent1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        noOfStudent1.setText("Student number");

        duration1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        duration1.setText("Duration");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(group1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tag1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(subject1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subject_code1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(noOfStudent1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(duration1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lectureName1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lectureName1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subject1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subject_code1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tag1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(group1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noOfStudent1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(duration1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        session_Group_search.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                session_Group_searchValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(session_Group_search);

        jLabel14.setText("Sessions:");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        groupCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                groupComboItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(groupCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(search_group_btn)))
                .addGap(23, 23, 23))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(search_group_btn)
                    .addComponent(groupCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 325, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Group", jPanel17);

        jLabel15.setText("Enter Subject : ");

        search_subject_btn.setText("Search");
        search_subject_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_subject_btnActionPerformed(evt);
            }
        });

        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lectureName3.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lectureName3.setText("Lecture Name");

        tag3.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        tag3.setText("Tag");

        group3.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        group3.setText("group");

        subject3.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        subject3.setText("Subject");

        subject_code3.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        subject_code3.setText("SubjCode");

        noOfStudent3.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        noOfStudent3.setText("Student number");

        duration3.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        duration3.setText("Duration");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(group3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tag3, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(subject3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subject_code3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(noOfStudent3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(duration3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lectureName3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lectureName3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subject3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subject_code3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tag3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(group3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noOfStudent3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(duration3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        session_Subject_search.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                session_Subject_searchValueChanged(evt);
            }
        });
        jScrollPane5.setViewportView(session_Subject_search);

        jLabel16.setText("Sessions:");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5))
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        subjectCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                subjectComboItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(subjectCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(search_subject_btn)))
                .addGap(23, 23, 23))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(search_subject_btn)
                    .addComponent(subjectCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Subject", jPanel21);

        javax.swing.GroupLayout searchSession_card_pnlLayout = new javax.swing.GroupLayout(searchSession_card_pnl);
        searchSession_card_pnl.setLayout(searchSession_card_pnlLayout);
        searchSession_card_pnlLayout.setHorizontalGroup(
            searchSession_card_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        searchSession_card_pnlLayout.setVerticalGroup(
            searchSession_card_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchSession_card_pnlLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnl_cards.add(searchSession_card_pnl, "searchSession");

        jSplitPane1.setRightComponent(pnl_cards);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Mshtakan", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("TimeTable Creater");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void subjectMenu_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subjectMenu_btnActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnl_cards, "searchSession");
    }//GEN-LAST:event_subjectMenu_btnActionPerformed

    private void sessionMenu_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sessionMenu_btnActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnl_cards, "createSession");
    }//GEN-LAST:event_sessionMenu_btnActionPerformed

    private void lectureMenu_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lectureMenu_btnActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnl_cards, "showSession");
    }//GEN-LAST:event_lectureMenu_btnActionPerformed

    private void session_continue_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_session_continue_btnActionPerformed
        // TODO add your handling code here:

        if(lecture_list.getModel().getSize()==0){
            String message = "You need Add atleast one lecture!";
            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                JOptionPane.ERROR_MESSAGE);

        }
        else{
            String sessionID = session_id.getText();

            new SessionDetails(sessionID).setVisible(true);

            dispose();
        }
    }//GEN-LAST:event_session_continue_btnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void addSessionLecture_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSessionLecture_btnActionPerformed
        // TODO add your handling code here:
        if(session_id.getText().isEmpty()){
            String message = "Session ID should be filled!";
            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                JOptionPane.ERROR_MESSAGE);

        }
        else{
            boolean x =isExistLecture(lecturesCombo.getSelectedItem().toString());
            boolean y = isExistSession();
            if(y){
                if(x){
                    try{
                        String sql = "INSERT INTO sessionLectures(sessionID,lectureName) VALUES(?,?)";

                        pst = con.prepareStatement(sql);
                        pst.setString(1, session_id.getText());
                        pst.setString(2, lecturesCombo.getSelectedItem().toString());

                        pst.execute();
                        JOptionPane.showMessageDialog(null, "Inserted Successfully");
                    }
                    catch(Exception e){
                        JOptionPane.showMessageDialog(null, e);
                    }

                    FillLecturesList();
                }
                else{
                    
                    String message = "Already Exist Session";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                JOptionPane.ERROR_MESSAGE);
                    
                    
                }
            }
            else{
                String message = "Already Session Lecture";
            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_addSessionLecture_btnActionPerformed

    private void setSessionDetails(String sid){
        try{
            String sql ="select * from session1 where session_id ='"+sid+"'";
            
            pst = con.prepareStatement(sql);
            result = pst.executeQuery();
            
            
                String subject_nameData = result.getString("subject");
                String subjectIDData = result.getString("subject_code");
                String sessionIDData = result.getString("session_id");
                String tagData = result.getString("tag");
                String groupData = result.getString("student_group");
                String noOfStudentData = result.getString("number_of_student");
                String durationData = result.getString("duration");
                tag.setText(tagData);
                group.setText(groupData);
                subject.setText(subject_nameData);
                subject_code.setText("("+subjectIDData+")");
                noOfStudent.setText(noOfStudentData);
                duration.setText("("+durationData+")");
                
                
                
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
     private void setSessionLectureDetails(String sid){
        try{
            String sql ="select * from sessionLectures where sessionID ='"+sid+"'";
            
            pst = con.prepareStatement(sql);
            result = pst.executeQuery();
            String name="";
            while(result.next()){
                name =name+""+ result.getString("lectureName")+", ";
                System.out.println(name);
            }
            
              System.out.println(name);
                lectureName.setText(name);
                 
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void sessionsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_sessionsValueChanged
        // TODO add your handling code here:

        String session_id = sessions.getSelectedValue().toString();

        setSessionDetails(session_id);
        setSessionLectureDetails(session_id);
    }//GEN-LAST:event_sessionsValueChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        FillSessionList_search_Lecture();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void session_Lecture_searchValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_session_Lecture_searchValueChanged
        // TODO add your handling code here:
        String session_id = session_Lecture_search.getSelectedValue();
        setSessionDetailsLecture(session_id);
        setSessionLectureDetailsLecture(session_id);
    }//GEN-LAST:event_session_Lecture_searchValueChanged

    private void lectureComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lectureComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lectureComboActionPerformed

    private void search_group_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_group_btnActionPerformed
        // TODO add your handling code here:

        FillSessionList_search_Group();

    }//GEN-LAST:event_search_group_btnActionPerformed

    private void session_Group_searchValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_session_Group_searchValueChanged
        // TODO add your handling code here:
        String session_id = session_Group_search.getSelectedValue();
        setSessionDetailsGroup(session_id);
        setSessionLectureDetailsGroup(session_id);
    }//GEN-LAST:event_session_Group_searchValueChanged

    private void groupComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_groupComboItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_groupComboItemStateChanged

    private void search_subject_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_subject_btnActionPerformed
        // TODO add your handling code here:
        FillSessionList_search_subject();

    }//GEN-LAST:event_search_subject_btnActionPerformed

    private void session_Subject_searchValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_session_Subject_searchValueChanged
        // TODO add your handling code here:
        String session_id = session_Subject_search.getSelectedValue();

        setSessionDetailsSubject(session_id);
        setSessionLectureDetailsSubject(session_id);

    }//GEN-LAST:event_session_Subject_searchValueChanged

    private void subjectComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_subjectComboItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_subjectComboItemStateChanged

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
            java.util.logging.Logger.getLogger(SessionMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SessionMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SessionMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SessionMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SessionMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSessionLecture_btn;
    private javax.swing.JPanel createSession_card_pnl;
    private javax.swing.JLabel duration;
    private javax.swing.JLabel duration1;
    private javax.swing.JLabel duration2;
    private javax.swing.JLabel duration3;
    private javax.swing.JLabel group;
    private javax.swing.JLabel group1;
    private javax.swing.JLabel group2;
    private javax.swing.JLabel group3;
    private javax.swing.JComboBox<String> groupCombo;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> lectureCombo;
    private javax.swing.JButton lectureMenu_btn;
    private javax.swing.JLabel lectureName;
    private javax.swing.JLabel lectureName1;
    private javax.swing.JLabel lectureName2;
    private javax.swing.JLabel lectureName3;
    private javax.swing.JList<String> lecture_list;
    private javax.swing.JComboBox<String> lecturesCombo;
    private javax.swing.JLabel noOfStudent;
    private javax.swing.JLabel noOfStudent1;
    private javax.swing.JLabel noOfStudent2;
    private javax.swing.JLabel noOfStudent3;
    private javax.swing.JPanel pnl_cards;
    private javax.swing.JPanel searchSession_card_pnl;
    private javax.swing.JButton search_group_btn;
    private javax.swing.JButton search_subject_btn;
    private javax.swing.JButton sessionMenu_btn;
    private javax.swing.JList<String> session_Group_search;
    private javax.swing.JList<String> session_Lecture_search;
    private javax.swing.JList<String> session_Subject_search;
    private javax.swing.JButton session_continue_btn;
    private javax.swing.JTextField session_id;
    private javax.swing.JList<String> sessions;
    private javax.swing.JPanel showSession_card_pnl;
    private javax.swing.JLabel subject;
    private javax.swing.JLabel subject1;
    private javax.swing.JLabel subject2;
    private javax.swing.JLabel subject3;
    private javax.swing.JComboBox<String> subjectCombo;
    private javax.swing.JButton subjectMenu_btn;
    private javax.swing.JLabel subject_code;
    private javax.swing.JLabel subject_code1;
    private javax.swing.JLabel subject_code2;
    private javax.swing.JLabel subject_code3;
    private javax.swing.JLabel tag;
    private javax.swing.JLabel tag1;
    private javax.swing.JLabel tag2;
    private javax.swing.JLabel tag3;
    // End of variables declaration//GEN-END:variables
}
