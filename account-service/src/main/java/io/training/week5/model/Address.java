package io.training.week5.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;


@Entity
@SqlResultSetMapping(
    name="addressMapping",
    classes=@ConstructorResult(
        targetClass=Address.class,
        columns= {
            @ColumnResult(name="building", type=String.class),
            @ColumnResult(name="street", type=String.class),
            @ColumnResult(name="city", type=String.class),
            @ColumnResult(name="state", type=String.class),
            @ColumnResult(name="zip", type=String.class),
            @ColumnResult(name="country", type=String.class),
        }))
@NamedNativeQuery(
    name="retrieveAddress",
    query="select building, street, city, state, zip, country "
        + "from address "
        + "where account_id=?1",
    resultSetMapping = "addressMapping"
)
public class Address {

  @Id
  @GeneratedValue
  private long id;
  private String building;
  private String street;
  private String city;
  private String state;
  private String zip;
  private String country;
  @ManyToOne
  @JoinColumn(name="accountId")
  private Account account;

  public Address(String building, String street, String city, String state, String zip,
      String country) {
    this.building = building;
    this.street = street;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.country = country;
  }

  public Address() {}

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getBuilding() {
    return building;
  }

  public void setBuilding(String building) {
    this.building = building;
  }

  public String getCity() { return city; }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }
}