package io.training.week5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;


@Entity
@SqlResultSetMapping(
    name="accountMapping",
    classes=@ConstructorResult(
        targetClass=Account.class,
        columns= {
            @ColumnResult(name="firstName", type=String.class),
            @ColumnResult(name="lastName", type=String.class),
            @ColumnResult(name="emailAddress", type=String.class),
        }))
@NamedNativeQuery(
    name="retrieveAccount",
    query="select first_name as firstName, last_name as lastName, email_address as emailAddress "
        + "from account "
        + "where account.id=?1",
    resultSetMapping = "accountMapping"
)
public class Account {
  @Id
  @GeneratedValue
  private long id;
  private String firstName;
  private String lastName;
  private String emailAddress;

  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//  @JoinColumn(name="accountId")
  private List<Address> addressList;

  public Account(String firstName, String lastName, String emailAddress) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.emailAddress = emailAddress;
    this.addressList = null;
  }

  public Account() {}

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public List<Address> getAddressList() {
    return addressList;
  }

  public void setAddressList(List<Address> addressList) {
    this.addressList = addressList;
  }
}