package com.uniyaz.core.service;

import com.uniyaz.core.dao.CustomerDao;
import com.uniyaz.core.dao.ProductDao;
import com.uniyaz.core.domain.Customer;
import com.uniyaz.core.domain.Product;

import java.util.List;

public class CustomerService {

    CustomerDao customerDao = new CustomerDao();

    public void saveCustomer(Customer customer) {
        validateSaveCustomer(customer);
        customerDao.saveCustomer(customer);
    }

    private void validateSaveCustomer(Customer customer) {
    }

    public void deleteCustomer(Customer customer) {
        customerDao.deleteCustomer(customer);
    }

    public List<Customer> findAllHql() {
        return customerDao.findAllHql();
    }
}
