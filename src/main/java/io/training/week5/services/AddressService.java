package io.training.week5.services;

import io.training.week5.model.Address;
import io.training.week5.repo.AddressRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

  private AddressRepository addressRepository;

  public AddressService(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  public List<Address> retrieveAddress(long id) {
    return addressRepository.retrieveAddress(id);
  }

  public Optional<Address> retrieveOneAddress(long accountId, long id) {
    return addressRepository.findById(id);
  }

}
