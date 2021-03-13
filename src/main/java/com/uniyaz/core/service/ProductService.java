package com.uniyaz.core.service;

import com.uniyaz.core.dao.ProductDao;
import com.uniyaz.core.domain.Product;

import java.util.List;

/**
 * Created by AKARTAL on 12.3.2021.
 */
public class ProductService {

    ProductDao productDao = new ProductDao();

    public void saveProduct(Product product) {
        validateSaveProudct(product);
        productDao.saveProduct(product);
    }

    private void validateSaveProudct(Product product) {

        if (!product.getKodu().startsWith("U")) throw new RuntimeException("Ürün Kodu U ile başlamak zorunda");
    }

    public void deleteProduct(Product product) {
        productDao.deleteProduct(product);
    }

    public List<Product> findAllHql() {
        return productDao.findAllHql();
    }
}
