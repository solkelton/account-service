package io.training.week5.controller;

import io.training.week5.model.Account;
import io.training.week5.model.Address;
import io.training.week5.services.AccountService;
import io.training.week5.services.AddressService;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  private AccountService accountService;
  private AddressService addressService;

  public AccountController(AccountService accountService,
      AddressService addressService) {
    this.accountService = accountService;
    this.addressService = addressService;
  }

  @GetMapping("/{id}")
  public Account retrieveAccount(@PathVariable("id") long id) {
    Account account = accountService.retrieveAccount(id);
    account.setAddressList(addressService.retrieveAddress(id));
    return account;
  }

  @GetMapping("/{id}/address")
  public List<Address> retrieveAddress(@PathVariable("id") long id) {
    return addressService.retrieveAddress(id);
  }

  @GetMapping("{accountId}/address/{id}")
  public Optional<Address> retrieveAddress(@PathVariable("accountId") long accountId, @PathVariable("id") long id) {
    return addressService.retrieveOneAddress(accountId, id);
  }

}
