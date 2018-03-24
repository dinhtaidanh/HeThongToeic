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
@Table(name = "user")
public class User implements Serializable {
    @Id
    @Column(name="Id", length = 11)
    private int id;
    @Column(name="ten_dang_nhap", length = 45)
    private String username;
    @Column(name="mat_khau", length = 45)
    private String password;
    @Column(name="quyen", length = 45)
    private String quyen;
    @Column(name="ho_ten", length = 45)
    private String hoTen;
    @Column(name="email", length = 80)
    private String email;
    @Column(name="dia_chi", length = 200)
    private String diaChi;
    @Column(name="gioi_tinh", length = 45)
    private String gioiTinh;

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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the quyen
     */
    public String getQuyen() {
        return quyen;
    }

    /**
     * @param quyen the quyen to set
     */
    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    /**
     * @return the hoTen
     */
    public String getHoTen() {
        return hoTen;
    }

    /**
     * @param hoTen the hoTen to set
     */
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the diaChi
     */
    public String getDiaChi() {
        return diaChi;
    }

    /**
     * @param diaChi the diaChi to set
     */
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    /**
     * @return the gioiTinh
     */
    public String getGioiTinh() {
        return gioiTinh;
    }

    /**
     * @param gioiTinh the gioiTinh to set
     */
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
}
