/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Danh
 */
public class MaHoaPass {
        // encode data using 
    public static String maHoa(String str){ 
        String encoded = DatatypeConverter.printBase64Binary(str.getBytes());
        return encoded;
    }
    public static String giaiMa(String pass){    
        String decoded = new String(DatatypeConverter.parseBase64Binary(pass));
        return decoded;
    }
}
