package se.jaw.jaxrs.persistence.entity;

import javax.persistence.*;

@Entity
public class Todo {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_id", nullable = true)
    private Long userId;

    @Column(nullable = false)
    private String importance;

    @Column(nullable = false)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
