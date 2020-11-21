package ma.markware.charybdis.demo.repository;

import java.util.Optional;
import java.util.UUID;
import ma.markware.charybdis.CqlTemplate;
import ma.markware.charybdis.demo.domain.User;
import ma.markware.charybdis.demo.domain.User_Table;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  private final CqlTemplate cqlTemplate;

  public UserRepository(final CqlTemplate cqlTemplate) {
    this.cqlTemplate = cqlTemplate;
  }

  public Optional<User> findByUserId(UUID userId) {
    return cqlTemplate.crud().findOptional(User_Table.user, User_Table.userId.eq(userId));
  }

  public Optional<User> findByUsername(String username) {
    return cqlTemplate.crud().findOptional(User_Table.user, User_Table.username.eq(username));
  }

  public User create(User user) {
    return cqlTemplate.crud().create(User_Table.user, user);
  }
}
