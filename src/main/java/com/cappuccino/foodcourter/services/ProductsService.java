package com.cappuccino.foodcourter.services;

import com.cappuccino.foodcourter.models.api.ProductDTO;
import com.cappuccino.foodcourter.models.db.Product;

/**
 * @author Yelshat Duskaliyev <semior> ura2178@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */
public interface ProductsService {

    Product create(ProductDTO product);

}
