/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Danh
 */
@Entity
@Table(name = "dienkhuyet")
public class DienKhuyet implements Serializable {
    @Id
    @Column(name="Id", length = 11)
    private int id;
    @Column(name="CauHoi")
    private String cauHoi;
    @Column(name="DapAn1")
    private String dapAn1;
    @Column(name="DapAn2")
    private String dapAn2;
    @Column(name="DapAn3")
    private String dapAn3;
    @Column(name="DapAn4")
    private String dapAn4;
    @Column(name="DapAnDung")
    private String dapAnDung;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the cauHoi
     */
    public String getCauHoi() {
        return cauHoi;
    }

    /**
     * @param cauHoi the cauHoi to set
     */
    public void setCauHoi(String cauHoi) {
        this.cauHoi = cauHoi;
    }

    /**
     * @return the dapAn1
     */
    public String getDapAn1() {
        return dapAn1;
    }

    /**
     * @param dapAn1 the dapAn1 to set
     */
    public void setDapAn1(String dapAn1) {
        this.dapAn1 = dapAn1;
    }

    /**
     * @return the dapAn2
     */
    public String getDapAn2() {
        return dapAn2;
    }

    /**
     * @param dapAn2 the dapAn2 to set
     */
    public void setDapAn2(String dapAn2) {
        this.dapAn2 = dapAn2;
    }

    /**
     * @return the dapAn3
     */
    public String getDapAn3() {
        return dapAn3;
    }

    /**
     * @param dapAn3 the dapAn3 to set
     */
    public void setDapAn3(String dapAn3) {
        this.dapAn3 = dapAn3;
    }

    /**
     * @return the dapAn4
     */
    public String getDapAn4() {
        return dapAn4;
    }

    /**
     * @param dapAn4 the dapAn4 to set
     */
    public void setDapAn4(String dapAn4) {
        this.dapAn4 = dapAn4;
    }

    /**
     * @return the dapAnDung
     */
    public String getDapAnDung() {
        return dapAnDung;
    }

    /**
     * @param dapAnDung the dapAnDung to set
     */
    public void setDapAnDung(String dapAnDung) {
        this.dapAnDung = dapAnDung;
    }
    
    
}
