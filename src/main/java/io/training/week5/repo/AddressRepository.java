package io.training.week5.repo;

import io.training.week5.model.Account;
import io.training.week5.model.Address;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;


public interface AddressRepository extends JpaRepository<Address, Long> {

//  @Query(nativeQuery=true, name="retrieveAddress")
//  List<Address> retrieveAddress(long id);

  @Nullable
  List<Address> getAddressByAccountId(long accountId);

  @Nullable
  Address getAddressByAccountIdAndId(long accountId, long id);

  void deleteAddressesByAccountId(long accountId);

  void deleteAddressByAccountIdAndId(long accountId, long id);

}
