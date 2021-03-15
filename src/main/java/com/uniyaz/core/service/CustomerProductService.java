package com.uniyaz.core.service;

import com.uniyaz.core.dao.CustomerProductDao;
import com.uniyaz.core.domain.CustomerProduct;

import java.util.List;

public class CustomerProductService {

    CustomerProductDao customerProductDao = new CustomerProductDao();

    public void saveCustomerProduct(CustomerProduct customerProduct){
        customerProductDao.saveCustomerProduct(customerProduct);
    }

    public void deleteCustomerProduct(CustomerProduct customerProduct){
        customerProductDao.deleteCustomerProduct(customerProduct);
    }

    public List<CustomerProduct> findAllHql(){
        return CustomerProductDao.findAllHql();
    }
}
