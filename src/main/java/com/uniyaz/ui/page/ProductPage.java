package com.uniyaz.ui.page;

import com.uniyaz.core.domain.Product;
import com.uniyaz.core.service.ProductService;
import com.uniyaz.ui.SyUI;
import com.uniyaz.ui.component.SySaveButton;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by AKARTAL on 12.3.2021.
 */
public class ProductPage extends VerticalLayout {

    @PropertyId("id")
    private TextField id;

    @PropertyId("name")
    private TextField name;

    @PropertyId("kodu")
    private TextField kodu;

    @PropertyId("price")
    private TextField price;

    private FormLayout mainLayout;

    private BeanItem<Product> productBeanItem;
    private FieldGroup binder;
    private SySaveButton sySaveButton;
    private Button syDeleteButton;

    public ProductPage() {
        this(new Product());
    }

    public ProductPage(Product product) {

        productBeanItem = new BeanItem<Product>(product);
        binder = new FieldGroup(productBeanItem);

        setSizeFull();
        buildMainLayout();
        addComponent(mainLayout);
        setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);

        binder.bindMemberFields(this);
        id.setEnabled(false);
    }

    private void buildMainLayout() {

        mainLayout = new FormLayout();
        mainLayout.setSizeUndefined();

        id = new TextField();
        id.setCaption("ID");
        id.setNullRepresentation("");
        mainLayout.addComponent(id);

        name = new TextField();
        name.setCaption("Name");
        name.setNullRepresentation("");
        mainLayout.addComponent(name);

        kodu = new TextField();
        kodu.setCaption("Kodu");
        kodu.setNullRepresentation("");
        mainLayout.addComponent(kodu);

        price = new TextField();
        price.setCaption("Price");
        price.setNullRepresentation("");
        mainLayout.addComponent(price);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        mainLayout.addComponent(horizontalLayout);

        sySaveButton = new SySaveButton();
        sySaveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    binder.commit();
                    Product product = productBeanItem.getBean();
                    ProductService productService = new ProductService();
                    productService.saveProduct(product);
                } catch (FieldGroup.CommitException e) {
                    Notification.show("Alanlar nesne ile uyumlu değil", Notification.Type.ERROR_MESSAGE);
                } catch (Exception e) {
                    Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        horizontalLayout.addComponent(sySaveButton);

        if (productBeanItem.getBean().getId() != null) {
            syDeleteButton = new Button();
            syDeleteButton.setIcon(FontAwesome.REMOVE);
            syDeleteButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
            syDeleteButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    try {
                        binder.commit();
                        Product product = productBeanItem.getBean();
                        ProductService productService = new ProductService();
                        productService.deleteProduct(product);
                        SyUI syUI = (SyUI) UI.getCurrent();
                        syUI.getContentComponent().addComponent(new ProductListPage());
                    } catch (FieldGroup.CommitException e) {
                        Notification.show("Alanlar nesne ile uyumlu değil", Notification.Type.ERROR_MESSAGE);
                    } catch (Exception e) {
                        Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
                    }
                }
            });
            horizontalLayout.addComponent(syDeleteButton);
        }
    }
}