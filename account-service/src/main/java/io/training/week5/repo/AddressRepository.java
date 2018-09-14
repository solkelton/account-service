package io.training.week5.repo;

import io.training.week5.model.Address;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AddressRepository extends JpaRepository<Address, Long> {

  @Query(nativeQuery=true, name="retrieveAddress")
  List<Address> retrieveAddress(long id);

}
