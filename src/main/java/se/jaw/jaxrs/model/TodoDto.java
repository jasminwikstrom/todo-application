package se.jaw.jaxrs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import se.jaw.jaxrs.persistence.entity.Todo;


@JsonIgnoreProperties(ignoreUnknown = true)
public final class TodoDto {

    private String id;
    private String userId;
    private ImportanceType importance;
    private String description;

    public TodoDto() {
    }

    public TodoDto(Todo todo) {
        this.id = todo.getId().toString();
        this.userId = todo.getUserId() != null ? todo.getUserId().toString() : null;
        this.importance = ImportanceType.valueOf(todo.getImportance());
        this.description = todo.getDescription();
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public ImportanceType getImportance() {
        return importance;
    }

    public String getDescription() {
        return description;
    }
}