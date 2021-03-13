package com.uniyaz.ui.component;

import com.uniyaz.ui.SyUI;
import com.uniyaz.ui.page.CustomerListPage;
import com.uniyaz.ui.page.CustomerPage;
import com.uniyaz.ui.page.ProductListPage;
import com.uniyaz.ui.page.ProductPage;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;

/**
 * Created by AKARTAL on 12.3.2021.
 */
public class SyMenuBar extends MenuBar {

    private ContentComponent contentComponent;

    public SyMenuBar() {
        setSizeFull();

        SyUI syUI = (SyUI) UI.getCurrent();
        contentComponent = syUI.getContentComponent();

        buildProductTransactionsMenuItem();
        buildCustomerTransactionsMenuItem();
    }

    private void buildProductTransactionsMenuItem() {
        MenuItem productTransactionsMenuItem = addItem("Product Transactions", null);
        productTransactionsMenuItem.addItem("Add Product", FontAwesome.PLUS, new Command() {
            @Override
            public void menuSelected(MenuItem menuItem) {
                ProductPage productPage = new ProductPage();
                contentComponent.addComponent(productPage);
            }
        });

        productTransactionsMenuItem.addItem("List Products", FontAwesome.LIST, new Command() {
            @Override
            public void menuSelected(MenuItem menuItem) {
                ProductListPage productListPage = new ProductListPage();
                contentComponent.addComponent(productListPage);
            }
        });
    }

    private void buildCustomerTransactionsMenuItem() {
        MenuItem customerTransactionsMenuItem = addItem("Customer Transactions", null);
        customerTransactionsMenuItem.addItem("Add Customer", FontAwesome.PLUS, new Command() {
            @Override
            public void menuSelected(MenuItem menuItem) {
                CustomerPage customerPage = new CustomerPage();
                contentComponent.addComponent(customerPage);
            }
        });

        customerTransactionsMenuItem.addItem("List Customers", FontAwesome.LIST, new Command() {
            @Override
            public void menuSelected(MenuItem menuItem) {
                CustomerListPage customerListPage = new CustomerListPage();
                contentComponent.addComponent(customerListPage);
            }
        });
    }
}
