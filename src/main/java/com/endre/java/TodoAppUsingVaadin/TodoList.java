package com.endre.java.TodoAppUsingVaadin;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringComponent
public class TodoList extends VerticalLayout implements TodoChangeListener {

    @Autowired
    TodoRepository repo;

    private List<Todo> todos;

    @PostConstruct
    void init(){
        setWidth("80%");
        update();
    }


    private void update() {
        setTodos(repo.findAll());
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
        removeAllComponents();
        todos.forEach(todo -> addComponent(new TodoLayout(todo, this)));
    }


    public void add(Todo todo){
        repo.save(todo);
        update();
    }

    public void deleteCompleted() {
        repo.deleteByDone(true);
        update();
    }

    @Override
    public void todoChange(Todo todo) {
        add(todo);
    }
}
