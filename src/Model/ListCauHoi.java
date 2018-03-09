/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Danh
 */
public class ListCauHoi {
    private ArrayList<Cau> dsCau;
    private static int cauHienTai;
    private static int STT = 0;
    public ListCauHoi(String tenBang,int viTriBatDau, int soLuongCau){
        dsCau = new ArrayList<>();
        try{
            Connection connection = ConnectionUtils.getMyConnection();
 
             // Tạo đối tượng Statement.
            Statement statement = connection.createStatement();

            String sql = "Select CauHoi, DapAn1, DapAn2, DapAn3, DapAn4,"
                  + "DapAnDung from " + tenBang;
                    //+ "limit " + viTriBatDau + ", " + soLuongCau;

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
              Cau cau = new Cau();
              STT++;
              cau.setSTT(STT);
              cau.setCauHoi(rs.getString("CauHoi"));
              cau.setDapAn1(rs.getString("DapAn1"));
              cau.setDapAn2(rs.getString("DapAn2"));
              cau.setDapAn3(rs.getString("DapAn3"));
              cau.setDapAn4(rs.getString("DapAn4"));
              cau.setDapAnDung(rs.getString("DapAnDung")); 
                dsCau.add(cau);
            }
        }catch (ClassNotFoundException | SQLException ex){            
        }
        
    }

    /**
     * @return the dsCau
     */
    public ArrayList<Cau> getDsCau() {
        return dsCau;
    }

    /**
     * @param dsCau the dsCau to set
     */
    public void setDsCau(ArrayList<Cau> dsCau) {
        this.dsCau = dsCau;
    }

    /**
     * @return the cauHienTai
     */
    public static int getCauHienTai() {
        return cauHienTai;
    }
   
}
