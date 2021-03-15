package com.uniyaz.ui.component;

import com.uniyaz.core.domain.Product;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ProductCard extends VerticalLayout {

    private VerticalLayout mainLayout;
    private Product product;
    private Label name;
    private Label price;
    private Image image;
    private CheckBox checkBox;

    public ProductCard(Product product) {
        this.product = product;
        buildMainLayout();
    }

    private void buildMainLayout() {

        mainLayout = new VerticalLayout();
        addComponent(mainLayout);

        image = new Image();
        image.setSource(new ExternalResource("https://www.mimsangrup.com.tr/images/haberler/teslimat_76a73.jpg"));
        image.setWidth(150, Unit.PIXELS);
        image.setHeight(100, Unit.PIXELS);
        mainLayout.addComponent(image);

        name = new Label();
        name.setValue(product.getName());
        mainLayout.addComponent(name);

        price = new Label();
        price.setValue(String.valueOf(product.getPrice()));
        mainLayout.addComponent(price);

        checkBox= new CheckBox();
        mainLayout.addComponent(checkBox);

    }
}
