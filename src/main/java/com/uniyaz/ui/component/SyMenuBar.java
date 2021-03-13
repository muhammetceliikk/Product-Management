package com.uniyaz.ui.component;

import com.uniyaz.ui.SyUI;
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

        buildUrunIslemleriMenuItem();
        buildMusteriIslemleriMenuItem();
    }

    private void buildUrunIslemleriMenuItem() {
        MenuItem urunIslemleriMenuItem = addItem("Ürün İşlemleri", null);
        urunIslemleriMenuItem.addItem("Ürün Ekle", FontAwesome.PLUS, new Command() {
            @Override
            public void menuSelected(MenuItem menuItem) {
                ProductPage productPage = new ProductPage();
                contentComponent.addComponent(productPage);
            }
        });

        urunIslemleriMenuItem.addItem("Ürün Listele", FontAwesome.LIST, new Command() {
            @Override
            public void menuSelected(MenuItem menuItem) {
                ProductListPage productListPage = new ProductListPage();
                contentComponent.addComponent(productListPage);
            }
        });
    }

    private void buildMusteriIslemleriMenuItem() {
        MenuItem MusteriIslemleriMenuItem = addItem("Müşteri İşlemleri", null);
        MusteriIslemleriMenuItem.addItem("Müşteri Ekle", FontAwesome.PLUS, new Command() {
            @Override
            public void menuSelected(MenuItem menuItem) {

            }
        });

        MusteriIslemleriMenuItem.addItem("Müşteri Listele", FontAwesome.LIST, new Command() {
            @Override
            public void menuSelected(MenuItem menuItem) {

            }
        });
    }
}
