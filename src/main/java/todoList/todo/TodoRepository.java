package todoList.todo;

import todoList.HibernateUtil;

import java.util.List;

public class TodoRepository {
    List<Todo> findAll() {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.createQuery("from Todo", Todo.class).list();
        transaction.commit();
        session.close();
        return result;
    }
    Todo toogleTodo(Integer todoId){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        Todo result = session.get(Todo.class,todoId);
        result.setDone(!result.getDone());
        transaction.commit();
        session.close();
        return result;
    }
    Todo addTodo(Todo newTodo){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.persist("Todo",newTodo);
        transaction.commit();
        session.close();
        return newTodo;
    }


}
