package com.uniyaz.ui.page;

import com.uniyaz.core.domain.Product;
import com.uniyaz.core.service.ProductService;
import com.uniyaz.ui.SyUI;
import com.uniyaz.ui.component.ContentComponent;
import com.uniyaz.ui.component.SyEditButton;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by AKARTAL on 12.3.2021.
 */
public class ProductListPage extends VerticalLayout {

    private VerticalLayout mainLayout;
    private Table table;
    private Container container;

    public ProductListPage() {

        setSizeFull();
        buildMainLayout();
        addComponent(mainLayout);

        setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);

        fillTable();
    }

    private void buildMainLayout() {

        mainLayout = new VerticalLayout();
        mainLayout.setSizeUndefined();

        buildTable();
        mainLayout.addComponent(table);
    }

    private void buildTable() {

        table = new Table();

        buildContainer();
        table.setContainerDataSource(container);
        table.setColumnHeaders("ID", "NAME", "KODU", "PRICE", "");
    }

    private void buildContainer() {

        container = new IndexedContainer();
        container.addContainerProperty("id", Long.class, null);
        container.addContainerProperty("name", String.class, null);
        container.addContainerProperty("kodu", String.class, null);
        container.addContainerProperty("price", BigDecimal.class, null);
        container.addContainerProperty("update", SyEditButton.class, null);
    }

    private void fillTable() {

        ProductService productService = new ProductService();
        List<Product> productList = productService.findAllHql();
        for (Product product : productList) {
            Item item = container.addItem(product);
            item.getItemProperty("id").setValue(product.getId());
            item.getItemProperty("name").setValue(product.getName());
            item.getItemProperty("kodu").setValue(product.getKodu());
            item.getItemProperty("price").setValue(product.getPrice());

            SyEditButton update = new SyEditButton();
            update.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {

                    SyUI syUI = (SyUI) SyUI.getCurrent();
                    ContentComponent contentComponent = syUI.getContentComponent();

                    ProductPage productPage = new ProductPage(product);
                    contentComponent.addComponent(productPage);
                }
            });
            item.getItemProperty("update").setValue(update);
        }
    }
}
