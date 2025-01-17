package sg.edu.ntu.ftbsolutionscrm.entity;

    
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import sg.edu.ntu.ftbsolutionscrm.entity.Salesperson;
//import sg.edu.ntu.ftbsolutionscrm.entity.HDBUser;

// Activity
// 1: Create Interaction Entity
// 2: Create InteractionRepository interface
// 3: Create InteractionService interface
// 4: Create InteractionServiceImpl
// 5: Create InteractionController
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "saleshdbinteraction")

public class SalesHDBInteraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SalesInteractionID")
    private Long SalesInteractionID;

    @ManyToOne(optional = false)
    @NotNull(message="SalespersonID is required")
    @JoinColumn(name = "salesperson_id", referencedColumnName = "salesperson_id")
    @JsonIgnoreProperties("salesHDBInteraction")
    private Salesperson salesperson;

         
    @ManyToOne(optional = false)
    @NotNull(message="HDB User ID is required")
    @JoinColumn(name = "hdb_userID", referencedColumnName = "id")
    @JsonIgnoreProperties("salesHDBInteraction")
    private HDBUser hdbUser;

    @Column(name = "review")
    @Size(min = 3,  message = "Remarks must be more than 3 characters")
     private String review;

}
