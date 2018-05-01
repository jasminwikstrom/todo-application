package se.jaw.jaxrs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.jaw.jaxrs.persistence.entity.Todo;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>, JpaSpecificationExecutor<Todo> {

    @Query(value = "SELECT * FROM todo t where " +
            "(:importance is null or t.importance = :importance) AND " +
            "(:userId is null or t.user_id = :userId)", nativeQuery=true)
    List<Todo> findAllByQuery(@Param("importance") String importance,
                              @Param("userId") Long userId);
}
