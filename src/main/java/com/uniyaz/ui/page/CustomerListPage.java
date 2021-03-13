package com.uniyaz.ui.page;

import com.uniyaz.core.domain.Customer;
import com.uniyaz.core.service.CustomerService;
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

import java.util.List;

public class CustomerListPage extends VerticalLayout {

    private VerticalLayout mainLayout;
    private Table table;
    private Container container;

    public CustomerListPage() {

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
        table.setColumnHeaders("ID", "NAME", "");
    }

    private void buildContainer() {

        container = new IndexedContainer();
        container.addContainerProperty("id", Long.class, null);
        container.addContainerProperty("name", String.class, null);
        container.addContainerProperty("update", SyEditButton.class, null);
    }

    private void fillTable() {

        CustomerService customerService = new CustomerService();
        List<Customer> customerList = customerService.findAllHql();
        for (Customer customer : customerList) {
            Item item = container.addItem(customer);
            item.getItemProperty("id").setValue(customer.getId());
            item.getItemProperty("name").setValue(customer.getName());

            SyEditButton update = new SyEditButton();
            update.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {

                    SyUI syUI = (SyUI) SyUI.getCurrent();
                    ContentComponent contentComponent = syUI.getContentComponent();

                    CustomerPage customerPage = new CustomerPage(customer);
                    contentComponent.addComponent(customerPage);
                }
            });
            item.getItemProperty("update").setValue(update);
        }
    }
}
