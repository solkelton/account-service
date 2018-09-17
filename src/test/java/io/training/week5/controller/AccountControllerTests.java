package io.training.week5.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.training.week5.model.Account;
import io.training.week5.model.Address;
import io.training.week5.services.AccountService;
import io.training.week5.services.AddressService;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AccountController.class)
@AutoConfigureMockMvc(secure=false)
public class AccountControllerTests {

  @Autowired private MockMvc mockMvc;
  @MockBean private AccountService accountService;
  @MockBean private AddressService addressService;

  private Account account;

  @Before
  public void setUpAccount() {
    account = createTestingAccount();
  }

  @Test
  public void testRetrieveAccount_ValidInput_ShouldReturnFoundAccountEntry() throws Exception {
    when(accountService.retrieveAccount(1)).thenReturn(account);

    mockMvc.perform(get("/accounts/{id}",1))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.*", hasSize(3)))
        .andExpect(jsonPath("$.firstName", Matchers.is(account.getFirstName())))
        .andExpect(jsonPath("$.lastName", Matchers.is(account.getLastName())))
        .andExpect(jsonPath("$.emailAddress", Matchers.is(account.getEmailAddress())));

    verify(accountService, times(1)).retrieveAccount(1);
    verifyNoMoreInteractions(accountService);
  }

  @Test
  public void testRetrieveAddress_ValidInput_ShouldReturnArrayOfAddresses() throws Exception {
    when(addressService.retrieveAddress(1)).thenReturn(account.getAddressList());

    mockMvc.perform(get("/accounts/{accountId}/address", 1))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$[0].*", hasSize(8)))
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].building", Matchers.is("18")))
        .andExpect(jsonPath("$[1].building", Matchers.is("11700")));

    verify(addressService, times(1)).retrieveAddress(1);
    verifyNoMoreInteractions(addressService);
  }

  @Test
  public void testRetrieveOneAddress_ValidInput_ShouldReturnFoundAddressEntry() throws Exception {
    when(addressService.retrieveOneAddress(1,1)).thenReturn(
        java.util.Optional.ofNullable(account.getAddressList().get(0)));

    mockMvc.perform(get("/accounts/{accountId}/address/{id}",1,1 ))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.building", Matchers.is("18")));

    verify(addressService, times(1)).retrieveOneAddress(1,1);
    verifyNoMoreInteractions(addressService);
  }

  private Account createTestingAccount() {
    Account account = new Account("Nick", "Kelton", "nick@gmail.com");
    account.setId(1);

    Address address1 = new Address("18","E. Elm","Chicago","IL", "60611", "U.S");
    address1.setId(1);

    Address address2 = new Address("11700","Hampton Greens Drive","Fort Myers","FL", "33913", "U.S");
    address2.setId(2);

    List<Address> addressList = new ArrayList<>();
    addressList.add(address1);
    addressList.add(address2);

    account.setAddressList(addressList);
    return account;
  }



}
