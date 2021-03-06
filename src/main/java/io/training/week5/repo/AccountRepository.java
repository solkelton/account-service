package io.training.week5.repo;

import io.training.week5.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

//  @Query(nativeQuery=true, name="retrieveAccount")
//  Account retrieveAccount(long id);
//  Account findById(long id);

  @Nullable
  Account getAccountById(long id);

  void deleteAccountById(long id);


}
