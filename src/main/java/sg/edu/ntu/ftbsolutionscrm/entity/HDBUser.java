package sg.edu.ntu.ftbsolutionscrm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import jakarta.persistence.CascadeType;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "hdb_user")
public class HDBUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    @NotBlank(message = "First name required")
    private String firstName;
    @Column(name = "last_name")
    @NotBlank(message = "Last name required")
    private String lastName;
    @Column(name = "email")
    @Email(message = "Email should be valid")
    private String email;
    @Column(name = "marital_status")
    @NotNull(message = "Please select an option")
    private Boolean isMarriedBoolean;
    @Column(name = "contact_no")
    @Size(min = 8, max = 8)
    private String contactNo;
    @Column(name = "year_of_birth")
    private int yearofbirth;
    @Column(name = "Close to School")
    @NotNull(message = "Please select an option")
    private Boolean closetoschool;
    @Column(name = "Close to Mall")
    @NotNull(message = "Please select an option")
    private Boolean closetomall;
    @Column(name = "Close to Public Transportation")
    @NotNull(message = "Please select an option")
    private Boolean closetotransportation;
    @Column(name = "Close to Major Roadways")
    @NotNull(message = "Please select an option")
    private Boolean closetoroadways;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({ "user", "flat" })
    private Set<Favourite> favourites;
     

   @OneToMany(mappedBy = "hdbUser", cascade = CascadeType.ALL)
   @JsonIgnoreProperties("hdbUser") // To prevent cyclic JSON serialization
    private List<SalesHDBInteraction> salesHDBInteraction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsMarriedBoolean() {
        return isMarriedBoolean;
    }

    public void setIsMarriedBoolean(Boolean isMarriedBoolean) {
        this.isMarriedBoolean = isMarriedBoolean;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public int getYear_of_birth() {
        return yearofbirth;
    }

    public void setYear_of_birth(int year_of_birth) {
        this.yearofbirth = year_of_birth;
    }

    public Boolean getClose_to_school() {
        return closetoschool;
    }

    public void setClose_to_school(Boolean close_to_school) {
        this.closetoschool = close_to_school;
    }

    public Boolean getClose_to_mall() {
        return closetomall;
    }

    public void setClose_to_mall(Boolean close_to_mall) {
        this.closetomall = close_to_mall;
    }

    public Boolean getClose_to_transportation() {
        return closetotransportation;
    }

    public void setClose_to_transportation(Boolean close_to_transportation) {
        this.closetotransportation = close_to_transportation;
    }

    public Boolean getClose_to_roadways() {
        return closetoroadways;
    }

    public void setClose_to_roadways(Boolean closetoroadways) {
        this.closetoroadways = closetoroadways;

    }

}
