package com.uniyaz.ui.page;

import com.uniyaz.core.domain.Product;
import com.uniyaz.core.service.ProductService;
import com.uniyaz.ui.component.ProductCard;
import com.uniyaz.ui.component.SySaveButton;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

public class CustomerProductPage extends VerticalLayout {

    private VerticalLayout mainLayout;
    private SySaveButton saveButton;

    public CustomerProductPage() {
        buildMainLayout();
    }

    private void buildMainLayout() {

        mainLayout = new VerticalLayout();
        addComponent(mainLayout);

        buildCards();

        saveButton = new SySaveButton();
        mainLayout.addComponent(saveButton);

    }

    private void buildCards() {
        ProductService productService = new ProductService();
        List<Product> productList = productService.findAllHql();

        for (Product product : productList) {
            ProductCard productCard = new ProductCard(product);
            mainLayout.addComponent(productCard);
        }
    }

}
