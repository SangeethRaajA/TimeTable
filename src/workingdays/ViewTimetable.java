/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingdays;

import Commen.SubMenu;
import SpecialRoomAllocation.NavSpl;
import Student.studentDashboard;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
//import javax.swing.text.Document;

import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;



import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import sesssionSpecial.sessionDashboard;

/**
 *
 * @author asvin
 */
public class ViewTimetable extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet result = null;
    
    /**
     * Creates new form ViewTimetable
     */
    public ViewTimetable() {
        initComponents();
        conn = DbConn.ConnecrDb();
        loadCategory();
    }
    
    private void loadCategory() {
        ArrayList<String> categories = new ArrayList<String>();

        categories.add("Select");
        categories.add("Student Group");
        categories.add("Lecturer");
        categories.add("Room");

        comboCategory.setModel(new DefaultComboBoxModel<String>(categories.toArray(new String[0])));
    }
    
    
     private void loadSelectOne(String category) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<String> categoryList = new ArrayList<String>();
        String query = null;
        String query2 = null;

        if (category.equals("Student Group")) {
            query = "SELECT DISTINCT(studentGroup) from timetable";
        } 

          else if (category.equals("Lecturer")){
              //quer2 change query
              query = "SELECT DISTINCT(lecturer) from timetable";
          }
        else if (category.equals("Room")) {
            query = "SELECT DISTINCT(room) from timetable";
        } else {
            categoryList.add(null);

            DefaultTableModel dtm = (DefaultTableModel) viewTable.getModel();
            ArrayList<String> dayandtimenull = new ArrayList<String>();
            dayandtimenull.add(null);
            dtm.setColumnIdentifiers(dayandtimenull.toArray(new String[0]));
            dtm.setRowCount(0);
        }

        if (query != null) {
            try {
                if (conn == null) {
                 
                }

                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    categoryList.add(rs.getString(1));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (query2 != null) {
            try {
                if (conn == null) {
              
                }

                stmt = conn.prepareStatement(query2);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    categoryList.add(rs.getString(1) + "-" + rs.getString(2));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        comboSelect.setModel(new DefaultComboBoxModel<String>(categoryList.toArray(new String[0])));
    }
    
    public void loadStudentTable() {
        DefaultTableModel dtm = (DefaultTableModel)viewTable.getModel();
        
        PreparedStatement stmt, stmt2, stmt3 = null;
        ResultSet rs, rs2, rs3 = null;
        
        String studentGroup = comboSelect.getSelectedItem().toString();
        
        try {            

            String query = "select * from timetable where studentGroup='"+studentGroup+"'";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            ArrayList<String> timetableList = new ArrayList<String>();
            while (rs.next()) {
                timetableList.add(rs.getString("batch"));
            }

                String query2 = "select days from day where batch=(select batch from workingdays where batch='" + timetableList.get(0) + "')";
                stmt2 = conn.prepareStatement(query2);
                rs2 = stmt2.executeQuery();

                int[] arr = new int[]{0, 0, 0, 0, 0, 0, 0};

                ArrayList<String> days = new ArrayList<String>();
                while (rs2.next()) {
                    days.add(rs2.getString("days"));
                    if (rs2.getString("days").equalsIgnoreCase("Monday")) {
                        arr[0] = 1;
                    } else if (rs2.getString("days").equalsIgnoreCase("Tuesday")) {
                        arr[1] = 1;
                    } else if (rs2.getString("days").equalsIgnoreCase("Wednesday")) {
                        arr[2] = 1;
                    } else if (rs2.getString("days").equalsIgnoreCase("Thursday")) {
                        arr[3] = 1;
                    } else if (rs2.getString("days").equalsIgnoreCase("Friday")) {
                        arr[4] = 1;
                    } else if (rs2.getString("days").equalsIgnoreCase("Saturday")) {
                        arr[5] = 1;
                    } else if (rs2.getString("days").equalsIgnoreCase("Sunday")) {
                        arr[6] = 1;
                    }
                }

                ArrayList<String> dayandtime = new ArrayList<String>();
                dayandtime.add("Time");

                if (arr[0] == 1) {
                    dayandtime.add("Monday");
                }

                if (arr[1] == 1) {
                    dayandtime.add("Tuesday");
                }

                if (arr[2] == 1) {
                    dayandtime.add("Wednesday");
                }

                if (arr[3] == 1) {
                    dayandtime.add("Thursday");
                }

                if (arr[4] == 1) {
                    dayandtime.add("Friday");
                }

                if (arr[5] == 1) {
                    dayandtime.add("Saturday");
                }

                if (arr[6] == 1) {
                    dayandtime.add("Sunday");
                }

                dtm.setColumnIdentifiers(dayandtime.toArray(new String[0]));
                dtm.setRowCount(0);

                String query3 = "select time_slot from timeSlot where batch='" + timetableList.get(0) + "'";

                
                stmt3 = conn.prepareStatement(query3);
                rs3 = stmt3.executeQuery();

                //ArrayList<String> timeslots = new ArrayList<String>();
                while (rs3.next()) {
                    Vector vector = new Vector();
                    vector.add(rs3.getString("time_slot"));

                    if (arr[0] == 1) {
                        String monData = getTableData(studentGroup, timetableList.get(0), "Monday", rs3.getString("time_slot"));
                        vector.add(monData);
                    }
                    
                    if (arr[1] == 1) {
                        String tueData = getTableData(studentGroup, timetableList.get(0), "Tuesday", rs3.getString("time_slot"));
                        vector.add(tueData);
                    }
                    
                    if (arr[2] == 1) {
                        String wedData = getTableData(studentGroup, timetableList.get(0), "Wednesday", rs3.getString("time_slot"));
                        vector.add(wedData);
                    }
                    
                    if (arr[3] == 1) {
                        String thurData = getTableData(studentGroup, timetableList.get(0), "Thursday", rs3.getString("time_slot"));
                        vector.add(thurData);
                    }
                    
                    if (arr[4] == 1) {
                        String friData = getTableData(studentGroup, timetableList.get(0), "Friday", rs3.getString("time_slot"));
                        vector.add(friData);
                    }
                    
                    if (arr[5] == 1) {
                        String satData = getTableData(studentGroup, timetableList.get(0), "Saturday", rs3.getString("time_slot"));
                        vector.add(satData);
                    }
                    
                    if (arr[6] == 1) {
                        String sunData = getTableData(studentGroup, timetableList.get(0), "Sunday", rs3.getString("time_slot"));
                        vector.add(sunData);
                    }
                    
                    dtm.addRow(vector);
                }
                loadGroupTable(studentGroup,timetableList.get(0));
                loadRoomTable(studentGroup,timetableList.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }
    
    private String getTableData(String studentGroup, String batch, String day, String timeslot) {
     
         ArrayList<String> data = new ArrayList<String>();

        PreparedStatement stmt, stmt2, stmt3,pst = null;
        ResultSet rs, rs2, rs3,result = null;

        String retVal = null;
        

        
        try {

            String query = "select * from timetable where studentGroup=? and batch=? and day=? and timeSlot=?";

            pst = conn.prepareStatement(query);
            pst.setString(1, studentGroup);
            pst.setString(2, batch);
            pst.setString(3, day);
            pst.setString(4, timeslot);

            result = pst.executeQuery();

             while (result.next()) {
                data.add(result.getString("session"));
                data.add(result.getString("room"));
                data.add(result.getString("studentGroup"));
            }
             if (!data.isEmpty()) {
                retVal = data.get(0);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }


        return retVal;

         
     }
   
    private void loadGroupTable(String gp, String batch) {
        DefaultTableModel dtm = (DefaultTableModel) viewTable.getModel();

        PreparedStatement stmt, stmt2, stmt3 = null;
        ResultSet rs, rs2, rs3 = null;

        try {
            if (conn == null) {
                
            }
               
            String query2 = "select days from day where batch=(select batch from workingdays where batch='" + batch + "')";
            stmt2 = conn.prepareStatement(query2);
            rs2 = stmt2.executeQuery();

            int[] arr = new int[]{0, 0, 0, 0, 0, 0, 0};

            ArrayList<String> days = new ArrayList<String>();
            while (rs2.next()) {
                days.add(rs2.getString("day"));
                if (rs2.getString("day").equalsIgnoreCase("Monday")) {
                    arr[0] = 1;
                } else if (rs2.getString("day").equalsIgnoreCase("Tuesday")) {
                    arr[1] = 1;
                } else if (rs2.getString("day").equalsIgnoreCase("Wednesday")) {
                    arr[2] = 1;
                } else if (rs2.getString("day").equalsIgnoreCase("Thursday")) {
                    arr[3] = 1;
                } else if (rs2.getString("day").equalsIgnoreCase("Friday")) {
                    arr[4] = 1;
                } else if (rs2.getString("day").equalsIgnoreCase("Saturday")) {
                    arr[5] = 1;
                } else if (rs2.getString("day").equalsIgnoreCase("Sunday")) {
                    arr[6] = 1;
                }
            }

            ArrayList<String> dayandtime = new ArrayList<String>();
            dayandtime.add("Time");

            if (arr[0] == 1) {
                dayandtime.add("Monday");
            }

            if (arr[1] == 1) {
                dayandtime.add("Tuesday");
            }

            if (arr[2] == 1) {
                dayandtime.add("Wednesday");
            }

            if (arr[3] == 1) {
                dayandtime.add("Thursday");
            }

            if (arr[4] == 1) {
                dayandtime.add("Friday");
            }

            if (arr[5] == 1) {
                dayandtime.add("Saturday");
            }

            if (arr[6] == 1) {
                dayandtime.add("Sunday");
            }

            dtm.setColumnIdentifiers(dayandtime.toArray(new String[0]));
            dtm.setRowCount(0);

            String query3 = "select time_slot from timeSlot where batch='" + batch + "'";
            stmt3 = conn.prepareStatement(query3);
            rs3 = stmt3.executeQuery();

            //ArrayList<String> timeslots = new ArrayList<String>();
            while (rs3.next()) {
                Vector vector = new Vector();
                vector.add(rs3.getString("time_slot"));

                if (arr[0] == 1) {
                    String monData = getGroupTableData(gp, batch, "Monday", rs3.getString("time_slot"));
                    vector.add(monData);
                }

                if (arr[1] == 1) {
                    String tueData = getGroupTableData(gp, batch, "Tuesday", rs3.getString("time_slot"));
                    vector.add(tueData);
                }

                if (arr[2] == 1) {
                    String wedData = getGroupTableData(gp, batch, "Wednesday", rs3.getString("time_slot"));
                    vector.add(wedData);
                }

                if (arr[3] == 1) {
                    String thurData = getGroupTableData(gp, batch, "Thursday", rs3.getString("time_slot"));
                    vector.add(thurData);
                }

                if (arr[4] == 1) {
                    String friData = getGroupTableData(gp, batch, "Friday", rs3.getString("time_slot"));
                    vector.add(friData);
                }

                if (arr[5] == 1) {
                    String satData = getGroupTableData(gp, batch, "Saturday", rs3.getString("time_slot"));
                    vector.add(satData);
                }

                if (arr[6] == 1) {
                    String sunData = getGroupTableData(gp, batch, "Sunday", rs3.getString("time_slot"));
                    vector.add(sunData);
                }

                dtm.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private String getGroupTableData(String gp, String batch, String day, String timeslot) {
         ArrayList<String> data = new ArrayList<String>();

        PreparedStatement stmt, stmt2, stmt3,pst = null;
        ResultSet rs, rs2, rs3,result = null;
        

        String retVal = null;


        try {
            if (conn == null) {
               
            }

            String query = "select session from timetable where studentGroup=? and batch=? and day=? and timeSlot=?";

            stmt = conn.prepareStatement(query);
            stmt.setString(1, gp);
            stmt.setString(2, batch);
            stmt.setString(3, day);
            stmt.setString(4, timeslot);

            rs = stmt.executeQuery();

            while (rs.next()) {
                data.add(rs.getString("session"));

            }
            if (!data.isEmpty()) {
                retVal = data.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return retVal;


    }
     
     
    private void loadRoomTable(String room, String batch) {
        DefaultTableModel dtm = (DefaultTableModel) viewTable.getModel();

        PreparedStatement stmt, stmt2, stmt3 = null;
        ResultSet rs, rs2, rs3 = null;

        try {
            if (conn == null) {
                
            }
               
            String query2 = "select days from day where batch=(select batch from workingdays where batch='" + batch + "')";
            stmt2 = conn.prepareStatement(query2);
            rs2 = stmt2.executeQuery();

            int[] arr = new int[]{0, 0, 0, 0, 0, 0, 0};

            ArrayList<String> days = new ArrayList<String>();
            while (rs2.next()) {
                days.add(rs2.getString("day"));
                if (rs2.getString("day").equalsIgnoreCase("Monday")) {
                    arr[0] = 1;
                } else if (rs2.getString("day").equalsIgnoreCase("Tuesday")) {
                    arr[1] = 1;
                } else if (rs2.getString("day").equalsIgnoreCase("Wednesday")) {
                    arr[2] = 1;
                } else if (rs2.getString("day").equalsIgnoreCase("Thursday")) {
                    arr[3] = 1;
                } else if (rs2.getString("day").equalsIgnoreCase("Friday")) {
                    arr[4] = 1;
                } else if (rs2.getString("day").equalsIgnoreCase("Saturday")) {
                    arr[5] = 1;
                } else if (rs2.getString("day").equalsIgnoreCase("Sunday")) {
                    arr[6] = 1;
                }
            }

            ArrayList<String> dayandtime = new ArrayList<String>();
            dayandtime.add("Time");

            if (arr[0] == 1) {
                dayandtime.add("Monday");
            }

            if (arr[1] == 1) {
                dayandtime.add("Tuesday");
            }

            if (arr[2] == 1) {
                dayandtime.add("Wednesday");
            }

            if (arr[3] == 1) {
                dayandtime.add("Thursday");
            }

            if (arr[4] == 1) {
                dayandtime.add("Friday");
            }

            if (arr[5] == 1) {
                dayandtime.add("Saturday");
            }

            if (arr[6] == 1) {
                dayandtime.add("Sunday");
            }

            dtm.setColumnIdentifiers(dayandtime.toArray(new String[0]));
            dtm.setRowCount(0);

            String query3 = "select time_slot from timeSlot where batch='" + batch + "'";
            stmt3 = conn.prepareStatement(query3);
            rs3 = stmt3.executeQuery();

            //ArrayList<String> timeslots = new ArrayList<String>();
            while (rs3.next()) {
                Vector vector = new Vector();
                vector.add(rs3.getString("time_slot"));

                if (arr[0] == 1) {
                    String monData = getRoomTableData(room, batch, "Monday", rs3.getString("time_slot"));
                    vector.add(monData);
                }

                if (arr[1] == 1) {
                    String tueData = getRoomTableData(room, batch, "Tuesday", rs3.getString("time_slot"));
                    vector.add(tueData);
                }

                if (arr[2] == 1) {
                    String wedData = getRoomTableData(room, batch, "Wednesday", rs3.getString("time_slot"));
                    vector.add(wedData);
                }

                if (arr[3] == 1) {
                    String thurData = getRoomTableData(room, batch, "Thursday", rs3.getString("time_slot"));
                    vector.add(thurData);
                }

                if (arr[4] == 1) {
                    String friData = getRoomTableData(room, batch, "Friday", rs3.getString("time_slot"));
                    vector.add(friData);
                }

                if (arr[5] == 1) {
                    String satData = getRoomTableData(room, batch, "Saturday", rs3.getString("time_slot"));
                    vector.add(satData);
                }

                if (arr[6] == 1) {
                    String sunData = getRoomTableData(room, batch, "Sunday", rs3.getString("time_slot"));
                    vector.add(sunData);
                }

                dtm.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
      
    private String getRoomTableData(String room, String batch, String day, String timeslot) {
         ArrayList<String> data = new ArrayList<String>();

        PreparedStatement stmt, stmt2, stmt3,pst = null;
        ResultSet rs, rs2, rs3,result = null;
        
//        StringBuffer retVal = new StringBuffer();
        String retVal=null;

        try {
            if (conn == null) {
               
            }

            String query = "select session,studentSubGroup,studentGroup from timetable where room=? and batch=? and day=? and timeSlot=?";

            stmt = conn.prepareStatement(query);
            stmt.setString(1, room);
            stmt.setString(2, batch);
            stmt.setString(3, day);
            stmt.setString(4, timeslot);

            rs = stmt.executeQuery();

            while (rs.next()) {
                data.add(rs.getString("session"));
                data.add(rs.getString("studentGroup"));
                data.add(rs.getString("studentSubGroup"));
            }
            if (!data.isEmpty()) {
                retVal = data.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

//        return retVal.toString();
        return retVal;

    }
private void printStudent(Document doc) {
        PreparedStatement stmt, stmt2, stmt3, stmt4 = null;
        ResultSet rs, rs2, rs3, rs4 = null;

        try {
            if (conn == null) {
                //conn = DBConnection.getDBConnection();
            }

            ArrayList<String> studentGroups = new ArrayList<>();
            //String query4 = "select distinct* from timetable";
            String query4 = "select distinct(studentGroup) from timetable";
            stmt4 = conn.prepareStatement(query4);
            rs4 = stmt4.executeQuery();

            while (rs4.next()) {
                studentGroups.add(rs4.getString("studentGroup"));
            }

            for (String studentGroup : studentGroups) {

                doc.add(new Paragraph("-------------" + studentGroup + "-----------------"));

                String query = "select batch from timetable where studentGroup='" + studentGroup + "'";
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();

                ArrayList<String> timetableList = new ArrayList<String>();
                while (rs.next()) {
                    timetableList.add(rs.getString("batch"));
                }

                String query2 = "select days from day where batch=(select batch from workingdays where batch='" + timetableList.get(0) + "')";
                
                //String query2 = "select day from days where workingID=(select workingID from workingdaysandhours where batch='" + timetableList.get(0) + "')";
                stmt2 = conn.prepareStatement(query2);
                rs2 = stmt2.executeQuery();

                int[] arr = new int[]{0, 0, 0, 0, 0, 0, 0};
                int count = 1;

                ArrayList<String> days = new ArrayList<String>();
                while (rs2.next()) {
                    days.add(rs2.getString("day"));
                    if (rs2.getString("day").equalsIgnoreCase("Monday")) {
                        arr[0] = 1;
                    } else if (rs2.getString("day").equalsIgnoreCase("Tuesday")) {
                        arr[1] = 1;
                    } else if (rs2.getString("day").equalsIgnoreCase("Wednesday")) {
                        arr[2] = 1;
                    } else if (rs2.getString("day").equalsIgnoreCase("Thursday")) {
                        arr[3] = 1;
                    } else if (rs2.getString("day").equalsIgnoreCase("Friday")) {
                        arr[4] = 1;
                    } else if (rs2.getString("day").equalsIgnoreCase("Saturday")) {
                        arr[5] = 1;
                    } else if (rs2.getString("day").equalsIgnoreCase("Sunday")) {
                        arr[6] = 1;
                    }
                    count++;
                }

                PdfPTable stTable = new PdfPTable(count);

                stTable.setWidthPercentage(100);
                stTable.setSpacingBefore(10f);
                stTable.setSpacingAfter(10f);

                stTable.addCell(new PdfPCell(new Paragraph("Time")));

                if (arr[0] == 1) {
                    stTable.addCell(new PdfPCell(new Paragraph("Monday")));
                }

                if (arr[1] == 1) {
                    stTable.addCell(new PdfPCell(new Paragraph("Tuesday")));
                }

                if (arr[2] == 1) {
                    stTable.addCell(new PdfPCell(new Paragraph("Wednesday")));
                }

                if (arr[3] == 1) {
                    stTable.addCell(new PdfPCell(new Paragraph("Thursday")));
                }

                if (arr[4] == 1) {
                    stTable.addCell(new PdfPCell(new Paragraph("Friday")));
                }

                if (arr[5] == 1) {
                    stTable.addCell(new PdfPCell(new Paragraph("Saturday")));
                }

                if (arr[6] == 1) {
                    stTable.addCell(new PdfPCell(new Paragraph("Sunday")));
                }

                String query3 = "select time_slot from timeSlot where batch='" + timetableList.get(0) + "'";
              //  String query3 = "select startTime from timeslots where workingID=(select workingID from workingdaysandhours where batch='" + timetableList.get(0) + "')";
                stmt3 = conn.prepareStatement(query3);
                rs3 = stmt3.executeQuery();

                while (rs3.next()) {
                    stTable.addCell(new PdfPCell(new Paragraph(rs3.getString("time_slot"))));

                    if (arr[0] == 1) {
                        String monData = getTableData(studentGroup, timetableList.get(0), "Monday", rs3.getString("time_slot"));
                        stTable.addCell(new PdfPCell(new Paragraph(monData)));
                    }

                    if (arr[1] == 1) {
                        String tueData = getTableData(studentGroup, timetableList.get(0), "Tuesday", rs3.getString("time_slot"));
                        stTable.addCell(new PdfPCell(new Paragraph(tueData)));
                    }

                    if (arr[2] == 1) {
                        String wedData = getTableData(studentGroup, timetableList.get(0), "Wednesday", rs3.getString("time_slot"));
                        stTable.addCell(new PdfPCell(new Paragraph(wedData)));
                    }

                    if (arr[3] == 1) {
                        String thurData = getTableData(studentGroup, timetableList.get(0), "Thursday", rs3.getString("time_slot"));
                        stTable.addCell(new PdfPCell(new Paragraph(thurData)));
                    }

                    if (arr[4] == 1) {
                        String friData = getTableData(studentGroup, timetableList.get(0), "Friday", rs3.getString("time_slot"));
                        stTable.addCell(new PdfPCell(new Paragraph(friData)));
                    }

                    if (arr[5] == 1) {
                        String satData = getTableData(studentGroup, timetableList.get(0), "Saturday", rs3.getString("time_slot"));
                        stTable.addCell(new PdfPCell(new Paragraph(satData)));
                    }

                    if (arr[6] == 1) {
                        String sunData = getTableData(studentGroup, timetableList.get(0), "Sunday", rs3.getString("time_slot"));
                        stTable.addCell(new PdfPCell(new Paragraph(sunData)));
                    }
                }
                doc.add(stTable);
                System.out.println(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

        panel1 = new java.awt.Panel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        viewTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboCategory = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        comboSelect = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        jToggleButton13 = new javax.swing.JToggleButton();
        jToggleButton14 = new javax.swing.JToggleButton();
        jToggleButton15 = new javax.swing.JToggleButton();
        jToggleButton17 = new javax.swing.JToggleButton();
        jToggleButton18 = new javax.swing.JToggleButton();
        jToggleButton16 = new javax.swing.JToggleButton();

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        viewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(viewTable);

        jPanel3.setBackground(new java.awt.Color(51, 102, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Category:");

        comboCategory.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comboCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCategoryActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Select One:");

        comboSelect.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comboSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSelectActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Download");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jButton1)
                            .addComponent(comboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 16, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(comboSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jButton1)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 75, 97));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Generate Timetable");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(248, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(84, 84, 84))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToggleButton13.setText("LECTURER");
        jToggleButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton13ActionPerformed(evt);
            }
        });

        jToggleButton14.setText("HOME");
        jToggleButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton14ActionPerformed(evt);
            }
        });

        jToggleButton15.setText("TIMETABLE");
        jToggleButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton15ActionPerformed(evt);
            }
        });

        jToggleButton17.setText("SESSION");
        jToggleButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton17ActionPerformed(evt);
            }
        });

        jToggleButton18.setText("LOCATION");
        jToggleButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton18ActionPerformed(evt);
            }
        });

        jToggleButton16.setText("STUDENT");
        jToggleButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton16ActionPerformed(evt);
            }
        });

        jDesktopPane2.setLayer(jToggleButton13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jToggleButton14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jToggleButton15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jToggleButton17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jToggleButton18, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jToggleButton16, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jToggleButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jToggleButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jToggleButton15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jToggleButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jToggleButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jToggleButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
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
                .addContainerGap())
        );

        jDesktopPane2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jToggleButton13, jToggleButton14, jToggleButton15, jToggleButton16, jToggleButton17, jToggleButton18});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jDesktopPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jDesktopPane2)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCategoryActionPerformed
        // TODO add your handling code here:
        loadSelectOne(comboCategory.getSelectedItem().toString()); 
        if (comboCategory.getSelectedItem().toString().equals("Student Group")) {
            loadStudentTable();
        }     
                
    }//GEN-LAST:event_comboCategoryActionPerformed

    private void comboSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSelectActionPerformed
        // TODO add your handling code here:
           loadStudentTable();     
    }//GEN-LAST:event_comboSelectActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        File file = null;

        try {
            boolean confirm = false;

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                confirm = true;
            } else {
                JOptionPane.showMessageDialog(null, "Please select a download path", "Download Failed", JOptionPane.ERROR_MESSAGE);
                confirm = false;
            }

            if (confirm) {
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream(file + "/Student Group Timetables.pdf"));
                doc.open();
                doc.add(new Paragraph("Student Group Timetables",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, Font.BOLD)));
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                doc.add(new Paragraph(dtf.format(now)));
                doc.add(new Paragraph("------------------------------------------------------------------"));

                printStudent(doc);

                doc.close();

                Thread.sleep(10);

//           

                JOptionPane.showMessageDialog(null, "student Group, Lecturer and Room Timetables Generated", "Success", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jToggleButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton13ActionPerformed
        // TODO add your handling code here:
        SubMenu s = new SubMenu();
        s.setVisible(true);
        s.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dispose();
    }//GEN-LAST:event_jToggleButton13ActionPerformed

    private void jToggleButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton14ActionPerformed

    private void jToggleButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton15ActionPerformed
        // TODO add your handling code here:
        TimeTableSystemManagementMain s = new TimeTableSystemManagementMain();
        s.setVisible(true);
        s.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dispose();
    }//GEN-LAST:event_jToggleButton15ActionPerformed

    private void jToggleButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton17ActionPerformed
        // TODO add your handling code here:
        sessionDashboard s = new sessionDashboard();
        s.setVisible(true);
        s.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dispose();
    }//GEN-LAST:event_jToggleButton17ActionPerformed

    private void jToggleButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton18ActionPerformed
        // TODO add your handling code here:
        NavSpl s = new NavSpl();
        s.setVisible(true);
        s.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dispose();
    }//GEN-LAST:event_jToggleButton18ActionPerformed

    private void jToggleButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton16ActionPerformed
        // TODO add your handling code here:
        studentDashboard s = new studentDashboard();
        s.setVisible(true);
        s.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dispose();
    }//GEN-LAST:event_jToggleButton16ActionPerformed

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
            java.util.logging.Logger.getLogger(ViewTimetable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewTimetable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewTimetable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewTimetable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewTimetable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboCategory;
    private javax.swing.JComboBox<String> comboSelect;
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton13;
    private javax.swing.JToggleButton jToggleButton14;
    private javax.swing.JToggleButton jToggleButton15;
    private javax.swing.JToggleButton jToggleButton16;
    private javax.swing.JToggleButton jToggleButton17;
    private javax.swing.JToggleButton jToggleButton18;
    private java.awt.Panel panel1;
    private javax.swing.JTable viewTable;
    // End of variables declaration//GEN-END:variables
}
