package com.cappuccino.foodcourter.services;

import com.cappuccino.foodcourter.models.api.VendorDTO;
import com.cappuccino.foodcourter.models.db.Vendor;

/**
 * @author Yelshat Duskaliyev <semior> ura2178@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */
public interface VendorService {

    Vendor create(VendorDTO vendorDTO);

}
