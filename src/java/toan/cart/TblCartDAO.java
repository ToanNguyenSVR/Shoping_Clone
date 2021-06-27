/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toan.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author toann
 */
public class TblCartDAO implements Serializable {

    private List<TblCartDTO> icons;

    public List<TblCartDTO> getIcons() {
        return icons;
    }

    public boolean addBookToCart(TblCartDTO dto) {
        // 1 : check existed items 
        if (this.icons == null) {
            this.icons = new ArrayList<>();

        }// end if item is not existed 
        // 2 : check title exist 
        boolean isExist = true;
        boolean update = false;
        if (this.icons.size() > 0) {
            System.out.println(dto.getProductQuantity());
            for (TblCartDTO icon : this.icons) {
                if (icon.getProductID().trim().equals(dto.getProductID().trim())) {
                    if (dto.getProductQuantity() == 1) {
                        icon.setProductQuantity(icon.getProductQuantity() + 1);

                    } else {
                       
                        icon.setProductQuantity(dto.getProductQuantity());
                        update = true;
                    }
                    isExist = false;
                }
            }
            if (isExist) {
                dto.setProductQuantity(1);
                this.icons.add(dto);
            }
        } else {
            dto.setProductQuantity(1);
            this.icons.add(dto);
        }
        return update;
    }

    public void removeBookFromCart(String title) {
        // 1 : check existed items 
        if (this.icons == null) {
            return;
        }
        //2 : check exist book 
        for (TblCartDTO icon : icons) {
            if (icon.getProductID().trim().equals(title.trim())) {
                // 3 : remove 
                this.icons.remove(icon);
                
                if (this.icons.isEmpty()) {
                    this.icons = null;
                }

            }
        }

    }
}
