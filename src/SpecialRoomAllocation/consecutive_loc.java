/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpecialRoomAllocation;


import Commen.SubMenu;
import Service.DbConnection;
import Student.studentDashboard;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;
import sesssionSpecial.sessionDashboard;
import workingdays.TimeTableSystemManagementMain;

/**
 *
 * @author User
 */
public class consecutive_loc extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Statement stm = null;
    /**
     * Creates new form consecutive_loc
     */
    public consecutive_loc() {
        initComponents();
        conn = DbConnection.ConnectDb();

//        consecutiveDrop();
        show_conselocation();
//        conseloc();
        loadRoom();
        updateTable();
    }

    private void updateTable(){
        String sql = "select * from consecutive";
        try{
	pst = conn.prepareStatement(sql);
	rs = pst.executeQuery();
	jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
	JOptionPane.showMessageDialog(null,e);
	}
    }
    private void loadRoom() {
        if (jComboBox2.getItemCount() != 0) {
            String selSession;
            selSession = jComboBox2.getSelectedItem().toString();

            PreparedStatement pst, pst2 = null;
            ResultSet rs, rs2 = null;
            try {
                
                ArrayList<String> rooms = new ArrayList<String>();

                if (!selSession.equalsIgnoreCase("select")) {
                    String query2 = null;

                    if (selSession.equalsIgnoreCase(null) ) {
                        query2 = "select from subloc where tag_name='labs'";
                    } else{
                        query2 = "select distinct room from subloc where tag_name='lecture'";
                    }

                    pst2 = conn.prepareStatement(query2);
                    rs2 = pst2.executeQuery();

                    while (rs2.next()) {
                        rooms.add(rs2.getString("room"));
                    }
                } else {
                    rooms.add(null);
                }

                jComboBox5.setModel(new DefaultComboBoxModel<String>(rooms.toArray(new String[0])));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ArrayList<String> sessions = new ArrayList<String>();
            sessions.add(null);
            jComboBox5.setModel(new DefaultComboBoxModel<String>(sessions.toArray(new String[0])));
        }
    }


     public ArrayList<consecutive_loc1> conseList(){
        ArrayList<consecutive_loc1> conseList = new ArrayList<>();
        try{
            String query1 = "Select * from consecutive";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query1);
            consecutive_loc1 conse;
            while(rs.next()){
                conse = new consecutive_loc1(rs.getInt("ConID"), rs.getString("subject_code"), rs.getString("lecture"), rs.getString("tutorial"), rs.getString("lab"), rs.getString("room"));
                conseList.add(conse);
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return conseList;
    }
    
    public void show_conselocation(){
        ArrayList<consecutive_loc1> list = conseList();
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        Object[] row = new Object[5];
        for(int i =0;i<list.size();i++){
            row[0]=list.get(i).getConID();
//            row[1]=list.get(i).getsubject_code();
            row[1]=list.get(i).getlecture();
            row[2]=list.get(i).gettutorial();
            row[3]=list.get(i).getlab();
            row[4]=list.get(i).getroom();
            model.addRow(row);
        }
    }
    
//     private void conseloc() {
//
//        try {
//            conn = DbConnection.ConnectDb();
//            String query = "select lecture from consecutive";
//            pst = conn.prepareStatement(query);
//            rs = pst.executeQuery();
//
//            ArrayList<String> loc = new ArrayList<String>();
//            loc.add("Select");
//            while (rs.next()) {
//                loc.add(rs.getString("lecture"));
//            }
//            jComboBox2.setModel(new DefaultComboBoxModel<String>(loc.toArray(new String[0])));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        loadRoom();
//    }
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jToggleButton14 = new javax.swing.JToggleButton();
        jToggleButton13 = new javax.swing.JToggleButton();
        jToggleButton16 = new javax.swing.JToggleButton();
        jToggleButton18 = new javax.swing.JToggleButton();
        jToggleButton17 = new javax.swing.JToggleButton();
        jToggleButton15 = new javax.swing.JToggleButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToggleButton14.setText("HOME");
        jToggleButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton14ActionPerformed(evt);
            }
        });

        jToggleButton13.setText("LECTURER");
        jToggleButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton13ActionPerformed(evt);
            }
        });

        jToggleButton16.setText("STUDENT");
        jToggleButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton16ActionPerformed(evt);
            }
        });

        jToggleButton18.setText("LOCATION");
        jToggleButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton18ActionPerformed(evt);
            }
        });

        jToggleButton17.setText("SESSION");
        jToggleButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton17ActionPerformed(evt);
            }
        });

        jToggleButton15.setText("TIMETABLE");
        jToggleButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton15ActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(jToggleButton14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jToggleButton13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jToggleButton16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jToggleButton18, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jToggleButton17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jToggleButton15, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jToggleButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jToggleButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jToggleButton15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jToggleButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jToggleButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jToggleButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToggleButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jToggleButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jDesktopPane1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jToggleButton13, jToggleButton14, jToggleButton15, jToggleButton16, jToggleButton17, jToggleButton18});

        jToggleButton1.setBackground(new java.awt.Color(0, 102, 102));
        jToggleButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton1.setText("ROOMS FOR CONSECUTIVE SESSIONS");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ConID", "Lecture", "Tute", "Lab", "Room"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CON ID : -");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("LECTURE : -");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("TUTE : -");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("LAB : -");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ROOM : -");

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 0, 102));
        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("ALLOCATE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, 0, 119, Short.MAX_VALUE)
                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel6.setText("SEARCH");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))
                        .addGap(21, 21, 21))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         String sql = "update consecutive set "
                + "room = '"+jComboBox5.getSelectedItem().toString()+"' where conID = "+jComboBox1.getSelectedItem();
        try{
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Record updated successfully");
           updateTable();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
//        String sql5 = "INSERT INTO conseroom(conID,lecture,tutorial,labs, room) VALUES(?,?,?,?,?)";
//        try{
//            pst = conn.prepareStatement(sql5);
//           
//            String consession;
//            consession = jComboBox1.getSelectedItem().toString();
//            pst.setString(1, consession);
//            String lec;
//            lec = jComboBox2.getSelectedItem().toString();
//            pst.setString(2, lec);
//            String tute;
//            tute = jComboBox3.getSelectedItem().toString();
//            pst.setString(3, tute);
//            String lab;
//            lab = jComboBox4.getSelectedItem().toString();
//            pst.setString(4, lab);
//            String room;
//            room = jComboBox5.getSelectedItem().toString();
//            pst.setString(5, room);
//            
//             if(jComboBox1.getSelectedItem().toString().isEmpty()){
//               String message = "All Field Should be Filled!";
//                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
//                JOptionPane.ERROR_MESSAGE);
//
//        }
//        else{
//            
//            pst.execute();
//            JOptionPane.showMessageDialog(null, "Inserted Successfully");
////             retrievelocation retrieveLocation = new retrievelocation();
////             retrieveLocation.setVisible(true);
////             retrieveLocation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            }
//           
//        } catch(Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        String selection = jTable1.getModel().getValueAt(row,0).toString();
        String selection1 = jTable1.getModel().getValueAt(row, 0).toString();
        String selection2 = jTable1.getModel().getValueAt(row, 0).toString();
        String selection3 = jTable1.getModel().getValueAt(row, 0).toString();
        try{
                    jComboBox1.removeAllItems();
                    jComboBox2.removeAllItems();
                    jComboBox3.removeAllItems();
                    jComboBox4.removeAllItems();
                    String sql= "select * from consecutive where conID ='"+selection+"'";
                    pst = conn.prepareStatement(sql);
                    rs = pst.executeQuery();
                    if(rs.next()){
//                 String add1 = rs.getString("tag");
//                 jTextField1.setText(add1);
                    jComboBox1.addItem(rs.getString("conID"));  
                   
                    }    
                    
                    String sql1 = "select * from consecutive where conID ='"+selection1+"'";
                    pst = conn.prepareStatement(sql1);
                    rs = pst.executeQuery();
                    ArrayList<String> loc = new ArrayList<String>();
                    loc.add("Select");
                    if(rs.next()){
//                 String add1 = rs.getString("tag");
//                 jTextField1.setText(add1);
                    jComboBox2.addItem(rs.getString("lecture"));  
                    
                    } 
                     
                    String sql2 = "select * from consecutive where conID ='"+selection2+"'";
                    pst = conn.prepareStatement(sql2);
                    rs = pst.executeQuery();
                     ArrayList<String> loc1 = new ArrayList<String>();
                    loc1.add("Select");
                    if(rs.next()){
//                 String add1 = rs.getString("student_group");
//                 jTextField1.setText(add1);

                    jComboBox3.addItem(rs.getString("tutorial"));
                    }      
                    String sql3 = "select * from consecutive where conID ='"+selection3+"'";
                    pst = conn.prepareStatement(sql3);
                    rs = pst.executeQuery();
                     ArrayList<String> loc2 = new ArrayList<String>();
                    loc2.add("Select");
                    if(rs.next()){
//                 String add1 = rs.getString("student_group");
//                 jTextField1.setText(add1);

                    jComboBox4.addItem(rs.getString("lab"));      
            }
//            jComboBox1.setModel(new DefaultComboBoxModel<String>(loc.toArray(new String[0])));
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
//         String building;
//        building = jComboBox2.getSelectedItem().toString();
//        System.out.println(building);
//        jComboBox5.removeAllItems();
//        try{
//            
//            String sql = "SELECT distinct room FROM subloc where tag_name = '"+building+"'";
//            
//            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
//            ResultSet rs = pst.executeQuery();
//           
//            while(rs.next())
//            {
//                jComboBox5.addItem(rs.getString("room"));             
//            }
//        }
//        catch(Exception e){
//            JOptionPane.showMessageDialog(null,e);
//        } 
         loadRoom();
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        DefaultTableModel table = (DefaultTableModel)jTable1.getModel();
        String search = jTextField1.getText().toLowerCase();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
        jTable1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jToggleButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton14ActionPerformed

    private void jToggleButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton13ActionPerformed
        // TODO add your handling code here:
        SubMenu s = new SubMenu();
        s.setVisible(true);
        s.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dispose();
    }//GEN-LAST:event_jToggleButton13ActionPerformed

    private void jToggleButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton16ActionPerformed
        // TODO add your handling code here:
        studentDashboard s = new studentDashboard();
        s.setVisible(true);
        s.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dispose();
    }//GEN-LAST:event_jToggleButton16ActionPerformed

    private void jToggleButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton18ActionPerformed
        // TODO add your handling code here:
        NavSpl s = new NavSpl();
        s.setVisible(true);
        s.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dispose();
    }//GEN-LAST:event_jToggleButton18ActionPerformed

    private void jToggleButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton17ActionPerformed
        // TODO add your handling code here:
        sessionDashboard s = new sessionDashboard();
        s.setVisible(true);
        s.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dispose();
    }//GEN-LAST:event_jToggleButton17ActionPerformed

    private void jToggleButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton15ActionPerformed
        // TODO add your handling code here:
        TimeTableSystemManagementMain s = new TimeTableSystemManagementMain();
        s.setVisible(true);
        s.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dispose();
    }//GEN-LAST:event_jToggleButton15ActionPerformed

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
            java.util.logging.Logger.getLogger(consecutive_loc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(consecutive_loc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(consecutive_loc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(consecutive_loc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new consecutive_loc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton13;
    private javax.swing.JToggleButton jToggleButton14;
    private javax.swing.JToggleButton jToggleButton15;
    private javax.swing.JToggleButton jToggleButton16;
    private javax.swing.JToggleButton jToggleButton17;
    private javax.swing.JToggleButton jToggleButton18;
    // End of variables declaration//GEN-END:variables
}
