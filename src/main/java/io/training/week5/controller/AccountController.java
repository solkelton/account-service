package io.training.week5.controller;

import io.training.week5.model.Account;
import io.training.week5.model.Address;
import io.training.week5.services.AccountService;
import io.training.week5.services.AddressService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
  public void addAddressToAccount(@PathVariable("accountId") long accountId, @RequestBody Address newAddress) {
    addressService.addAddress(accountId, newAddress);
  }
}
