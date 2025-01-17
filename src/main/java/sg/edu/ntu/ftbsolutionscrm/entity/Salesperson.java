package sg.edu.ntu.ftbsolutionscrm.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "salesperson")
public class Salesperson 
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) 
  @Column(name = "salesperson_id")
  private Long id;

  @Column(name = "first_name")
  @NotBlank(message = "First name is mandatory")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  @Email(message = "Email should be valid")
  private String email;

  @Column(name = "contact_no")
  @Size(min = 8, max = 8, message = "Contact number must be 8 characters long")
  private String contactNo;

  @Column(name = "awards")
  private String awards;

  @Column(name = "yearsinservice")
  private int yearsInService;  


  // One salesperson can have many sales interactions
  @OneToMany(mappedBy = "salesperson", cascade = CascadeType.ALL)
  @JsonIgnoreProperties("salesperson") // To prevent cyclic JSON serialization
  private List<SalesHDBInteraction> salesHDBInteraction;


}
