package todoList.todo;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="todos")
class Todo {
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name="inc",strategy = "increment")
    private Integer id;
    private String text;
    boolean done;
    /*
    Hibernate needs it
     */
    @SuppressWarnings("unused")
    Todo(){ }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
