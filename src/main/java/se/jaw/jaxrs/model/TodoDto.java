package se.jaw.jaxrs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import se.jaw.jaxrs.persistence.entity.Todo;


@JsonIgnoreProperties(ignoreUnknown = true)
public final class TodoDto {


    private Long id;
    private Long userId;

    private ImportanceType importance;
    private String description;

    public TodoDto(Todo todo) {
        this.id = todo.getId();
        this.userId = todo.getUserId();
        this.importance = ImportanceType.valueOf(todo.getImportance());
        this.description = todo.getDescription();
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public ImportanceType getImportance() {
        return importance;
    }

    public String getDescription() {
        return description;
    }
}