/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Danh
 */
public class Cau {

    private int STT = 0;
    private String cauHoi = "";
    private String dapAn1 = "";
    private String dapAn2 = "";
    private String dapAn3 = "";
    private String dapAn4 = "";
    private String dapAnDung = "";
    private String traLoi = "";
    
    public Cau(){
    }
    public Cau(String CauHoi,String DapAnDung){
        this.cauHoi = CauHoi;  
        this.dapAnDung = DapAnDung;
    }
    public Cau(String CauHoi,String DapAn1, String DapAn2,String DapAn3,String DapAn4,String 
            DapAnDung){
        this.cauHoi = CauHoi;
        this.dapAn1 = DapAn1;
        this.dapAn2 = DapAn2;
        this.dapAn3 = DapAn3;
        this.dapAn4 = DapAn4;
        this.dapAnDung = DapAnDung;
    }
    
    /**
     * @return the CauHoi
     */
    public String getCauHoi() {
        return cauHoi;
    }

    /**
     * @param CauHoi the CauHoi to set
     */
    public void setCauHoi(String CauHoi) {
        this.cauHoi = CauHoi;
    }

    /**
     * @return the DapAn1
     */
    public String getDapAn1() {
        return dapAn1;
    }

    /**
     * @param DapAn1 the DapAn1 to set
     */
    public void setDapAn1(String DapAn1) {
        this.dapAn1 = DapAn1;
    }

    /**
     * @return the DapAn2
     */
    public String getDapAn2() {
        return dapAn2;
    }

    /**
     * @param DapAn2 the DapAn2 to set
     */
    public void setDapAn2(String DapAn2) {
        this.dapAn2 = DapAn2;
    }

    /**
     * @return the DapAn3
     */
    public String getDapAn3() {
        return dapAn3;
    }

    /**
     * @param DapAn3 the DapAn3 to set
     */
    public void setDapAn3(String DapAn3) {
        this.dapAn3 = DapAn3;
    }

    /**
     * @return the DapAn4
     */
    public String getDapAn4() {
        return dapAn4;
    }

    /**
     * @param DapAn4 the DapAn4 to set
     */
    public void setDapAn4(String DapAn4) {
        this.dapAn4 = DapAn4;
    }

    /**
     * @return the DapAnDung
     */
    public String getDapAnDung() {
        return dapAnDung;
    }

    /**
     * @param DapAnDung the DapAnDung to set
     */
    public void setDapAnDung(String DapAnDung) {
        this.dapAnDung = DapAnDung;
    }

    /**
     * @return the traLoi
     */
    public String getTraLoi() {
        return traLoi;
    }

    /**
     * @param traLoi the traLoi to set
     */
    public void setTraLoi(String traLoi) {
        this.traLoi = traLoi;
    }

    /**
     * @return the STT
     */
    public int getSTT() {
        return STT;
    }

    /**
     * @param STT the STT to set
     */
    public void setSTT(int STT) {
        this.STT = STT;
    }

    
    
}
