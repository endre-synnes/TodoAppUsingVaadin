package com.endre.java.TodoAppUsingVaadin;

import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
public class TodoUI extends UI {

    private VerticalLayout root;


    @Autowired
    TodoLayout todoLayout;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        addHeader();
        addForm();
        addTodoList();
        addDeleteButton();
    }

    private void setupLayout() {
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(root);
    }

    private void addHeader() {
        Label header = new Label("TODOs");
        header.addStyleName(ValoTheme.LABEL_H1);
        root.addComponent(header);
    }

    private void addForm() {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setWidth("80%");

        TextField task = new TextField();
        Button add = new Button("");
        add.addStyleName(ValoTheme.BUTTON_PRIMARY);
        add.setIcon(VaadinIcons.PLUS);

        //Task vil få all plass som er til overs.
        formLayout.addComponentsAndExpand(task);
        formLayout.addComponents(add);


        add.addClickListener(click-> {
            todoLayout.add(new Todo(task.getValue()));
            task.clear();
            task.focus();
        });
        task.focus();
        add.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        root.addComponent(formLayout);
    }

    private void addTodoList() {
        todoLayout.setWidth("80%");
        root.addComponent(todoLayout);
    }

    private void addDeleteButton() {
        root.addComponent(new Button("Delete completed", click -> {
            todoLayout.deleteCompleted();
        }));
    }


}
