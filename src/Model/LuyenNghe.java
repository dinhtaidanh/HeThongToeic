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
@Table(name = "luyennghe")
public class LuyenNghe implements Serializable {
    @Id
    @Column(name="Id", length = 11)
    private int id;
    @Column(name="DapAn", length = 45)
    private String dapAn;
    @Column(name="LinkAudio", length = 1000)
    private String linkAudio;
    @Column(name="LinkPhoto", length = 1000)
    private String linkPhoto;
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
     * @return the dapAn
     */
    public String getDapAn() {
        return dapAn;
    }

    /**
     * @param dapAn the dapAn to set
     */
    public void setDapAn(String dapAn) {
        this.dapAn = dapAn;
    }

    /**
     * @return the linkAudio
     */
    public String getLinkAudio() {
        return linkAudio;
    }

    /**
     * @param linkAudio the linkAudio to set
     */
    public void setLinkAudio(String linkAudio) {
        this.linkAudio = linkAudio;
    }

    /**
     * @return the linkPhoto
     */
    public String getLinkPhoto() {
        return linkPhoto;
    }

    /**
     * @param linkPhoto the linkPhoto to set
     */
    public void setLinkPhoto(String linkPhoto) {
        this.linkPhoto = linkPhoto;
    }
 
}
