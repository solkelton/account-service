package io.training.week5.controller;

import io.training.week5.model.Account;
import io.training.week5.model.Address;
import io.training.week5.services.AccountService;
import io.training.week5.services.AddressService;
import java.util.List;
import javax.ws.rs.Path;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AccountController {

  private AccountService accountService;
  private AddressService addressService;

  public AccountController(AccountService accountService,
      AddressService addressService) {
    this.accountService = accountService;
    this.addressService = addressService;
  }

  @GetMapping("/{accountId}")
  public Account retrieveAccount(@PathVariable("accountId") long accountId) {
    return accountService.retrieveAccount(accountId);
  }

  @GetMapping("/{accountId}/address")
  public List<Address> retrieveAddress(@PathVariable("accountId") long accountId) {
    return addressService.retrieveAddress(accountId);
  }

  @GetMapping("{accountId}/address/{addressId}")
  public Address retrieveAddress(@PathVariable("accountId") long accountId, @PathVariable("addressId") long addressId) {
    return addressService.retrieveOneAddress(accountId, addressId);
  }

  @PostMapping("/{accountId}/address")
  public Address addAddress(@PathVariable("accountId") long accountId, @RequestBody Address newAddress) {
    return addressService.addAddress(accountId, newAddress);
  }

  @PostMapping
  public Account addAccount(@RequestBody Account account) {
    return accountService.addAccount(account);
  }

  @PutMapping("{accountId}")
  public Account updateAccount(@PathVariable("accountId") long accountId, @RequestBody Account account) {
    return accountService.updateAccount(accountId, account);
  }

  @PutMapping("/{accountId}/address/{addressId}")
  public Address updateAddress(@PathVariable("accountId") long accountId,
      @PathVariable("addressId") long addressId, @RequestBody Address address) {
    return addressService.updateAddress(accountId, addressId, address);
  }

  @Transactional
  @DeleteMapping("/{accountId}/address")
  public boolean removeAllAddress(@PathVariable("accountId") long accountId) {
    return addressService.removeAllAddresses(accountId);
  }

  @Transactional
  @DeleteMapping("/{accountId}/address/{addressId}")
  public boolean removeAnAddress(@PathVariable("accountId") long accountId, @PathVariable("addressId") long addressId) {
    return addressService.removeAnAddress(accountId, addressId);
  }

  @Transactional
  @DeleteMapping("/{accountId}")
  public boolean removeAccount(@PathVariable("accountId") long accountId) {
    return accountService.removeAccount(accountId);
    //will need to delete everything associated with that account or just all addresses associated?
  }


}
