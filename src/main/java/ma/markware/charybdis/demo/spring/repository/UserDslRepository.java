package ma.markware.charybdis.demo.spring.repository;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import ma.markware.charybdis.demo.spring.domain.Address;
import ma.markware.charybdis.demo.spring.domain.RoleEnum;
import ma.markware.charybdis.demo.spring.domain.User;
import ma.markware.charybdis.demo.spring.domain.User_Table;
import ma.markware.charybdis.dsl.DslQuery;
import org.springframework.stereotype.Repository;

@Repository
public class UserDslRepository {

  private final DslQuery dslQuery;

  public UserDslRepository(DslQuery dslQuery) {
    this.dslQuery = dslQuery;
  }

  public boolean createUser(final String username, final String userAgent) {
    Instant now = Instant.now();
    return dslQuery.insertInto(User_Table.user, User_Table.name, User_Table.role, User_Table.accessLogs, User_Table.creationDate,
                               User_Table.lastUpdatedDate)
                   .values(username, RoleEnum.DEFAULT, Map.of(now, userAgent), now, nowuse)
                   .ifNotExists()
                   .execute();
  }

  public Optional<User> findUserByUsername(final String username) {
    return dslQuery.selectFrom(User_Table.user)
                   .where(User_Table.name.eq(username))
                   .fetchOptional()
                   .map(record -> new User(record.get(User_Table.name), record.get(User_Table.addresses), record.get(User_Table.role),
                                           record.get(User_Table.accessLogs)));
  }

  public List<String> fetchUsernames() {
    return dslQuery.select(User_Table.name)
                   .from(User_Table.user)
                   .fetch()
                   .stream()
                   .map(record -> record.get(User_Table.name))
                   .collect(Collectors.toList());
  }

  public boolean updateRole(final String username, final RoleEnum newRole) {
    return dslQuery.update(User_Table.user)
                   .set(User_Table.role, newRole)
                   .set(User_Table.lastUpdatedDate, Instant.now())
                   .where(User_Table.name.eq(username))
                   .execute();
  }

  public boolean updateAccessLogs(final String username, final String userAgent) {
    // Access logs are persisted only if user's role is not ADMIN
    return dslQuery.update(User_Table.user)
                   .set(User_Table.accessLogs, User_Table.accessLogs.append(Map.of(Instant.now(), userAgent)))
                   .set(User_Table.lastUpdatedDate, Instant.now())
                   .where(User_Table.name.eq(username))
                   .if_(User_Table.role.neq(RoleEnum.ADMIN))
                   .execute();
  }

  public boolean addAddress(final String username, final Address address) {
    return dslQuery.update(User_Table.user)
                   .set(User_Table.addresses, User_Table.addresses.append(List.of(address)))
                   .set(User_Table.lastUpdatedDate, Instant.now())
                   .where(User_Table.name.eq(username))
                   .execute();
  }

  public boolean deleteAddressAtIndex(final String username, final int index) {
    return dslQuery.delete(User_Table.addresses.entry(index))
                   .from(User_Table.user)
                   .where(User_Table.name.eq(username))
                   .execute();
  }

  public boolean deleteAddresses(final String username) {
    return dslQuery.delete(User_Table.addresses)
                   .from(User_Table.user)
                   .where(User_Table.name.eq(username))
                   .execute();
  }
}
