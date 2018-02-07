package com.endre.java.TodoAppUsingVaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class TodoUI extends UI {

    private VerticalLayout root;


    @Autowired
    TodoList todoList;

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
        task.focus();
        Button add = new Button("");

        //Task vil få all plass som er til overs.
        formLayout.addComponentsAndExpand(task);
        formLayout.addComponents(add);
        root.addComponent(formLayout);

        add.addStyleName(ValoTheme.BUTTON_PRIMARY);
        add.setIcon(VaadinIcons.PLUS);

        add.addClickListener(click-> {
            todoList.add(new Todo(task.getValue()));
            task.clear();
            task.focus();
        });
        add.setClickShortcut(ShortcutAction.KeyCode.ENTER);

    }

    private void addTodoList() {
        root.addComponent(todoList);
    }

    private void addDeleteButton() {
        root.addComponent(new Button("Delete completed", click -> {
            todoList.deleteCompleted();
        }));
    }


}
