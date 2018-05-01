package se.jaw.jaxrs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.jaw.jaxrs.persistence.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
