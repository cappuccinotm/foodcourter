package com.cappuccino.foodcourter.services;

import com.cappuccino.foodcourter.models.api.VendorDTO;
import com.cappuccino.foodcourter.models.db.Vendor;
import com.cappuccino.foodcourter.repositories.VendorsRepository;
import org.springframework.stereotype.Service;

/**
 * @author Yelshat Duskaliyev <semior> ura2178@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */
@Service
public class VendorServiceImpl implements VendorService {

    private final VendorsRepository vendorsRepository;

    public VendorServiceImpl(VendorsRepository vendorsRepository) {
        this.vendorsRepository = vendorsRepository;
    }

    @Override
    public Vendor create(VendorDTO vendorDTO) {
        Vendor newVendor = new Vendor();
        newVendor.setName(vendorDTO.getName());
        vendorsRepository.save(newVendor);
        return newVendor;
    }
}
