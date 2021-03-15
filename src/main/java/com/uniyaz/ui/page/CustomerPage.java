package com.uniyaz.ui.page;

import com.uniyaz.core.domain.Customer;
import com.uniyaz.core.domain.Product;
import com.uniyaz.core.service.CustomerService;
import com.uniyaz.core.service.ProductService;
import com.uniyaz.ui.SyUI;
import com.uniyaz.ui.component.SySaveButton;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class CustomerPage extends VerticalLayout {

    @PropertyId("id")
    private TextField id;

    @PropertyId("name")
    private TextField name;

    private FormLayout mainLayout;

    private BeanItem<Customer> customerBeanItem;
    private FieldGroup binder;
    private SySaveButton sySaveButton;
    private Button syDeleteButton;

    public CustomerPage() {
        this(new Customer());
    }

    public CustomerPage(Customer customer) {

        customerBeanItem = new BeanItem<Customer>(customer);
        binder = new FieldGroup(customerBeanItem);

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

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        mainLayout.addComponent(horizontalLayout);

        sySaveButton = new SySaveButton();
        sySaveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    binder.commit();
                    Customer customer = customerBeanItem.getBean();
                    CustomerService customerService = new CustomerService();
                    customerService.saveCustomer(customer);
                } catch (FieldGroup.CommitException e) {
                    Notification.show("Alanlar nesne ile uyumlu değil", Notification.Type.ERROR_MESSAGE);
                } catch (Exception e) {
                    Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
                Notification.show(customerBeanItem.getBean().getName()+" kaydedildi.");
            }
        });
        horizontalLayout.addComponent(sySaveButton);

        if (customerBeanItem.getBean().getId() != null) {
            syDeleteButton = new Button();
            syDeleteButton.setIcon(FontAwesome.REMOVE);
            syDeleteButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
            syDeleteButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    try {
                        binder.commit();
                        Customer customer = customerBeanItem.getBean();
                        CustomerService customerService = new CustomerService();
                        customerService.deleteCustomer(customer);
                        SyUI syUI = (SyUI) UI.getCurrent();
                        syUI.getContentComponent().addComponent(new CustomerListPage());
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
