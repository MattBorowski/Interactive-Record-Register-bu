package io.github.MattBorowski.todo;

import io.github.MattBorowski.HibernateUtil;

import java.util.List;

public class TodoRepository {
    List<Todo> findAll(){
        var session= HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.createQuery("from Todo", Todo.class).list();

        transaction.commit();
        session.close();
        return result;
    }
    Todo toggleTodo(Integer id){
        var session= HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var result = session.get(Todo.class, id);
        result.setDone(!result.isDone());

        transaction.commit();
        session.close();
        return result;

    }
    Todo addTodo(Todo newTodo){
        var session= HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        session.persist(newTodo);

        transaction.commit();
        session.close();
        return newTodo;
    }


}
