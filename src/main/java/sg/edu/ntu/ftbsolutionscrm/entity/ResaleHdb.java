package sg.edu.ntu.ftbsolutionscrm.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "resale_hdb")
public class ResaleHdb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "flat", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({ "user", "flat" })
    private Set<Favourite> favourites;

    @Column(name = "month")
    @NotBlank(message = "Field Month is mandatory")
    @Size(min = 7, max = 7, message = "Must follow YYYY-MM format in 7 characters")
    private String month;

    @Column(name = "town")
    @NotBlank(message = "Field Town is mandatory")
    private String town;

    @Column(name = "flat_type")
    @NotBlank(message = "Field FlatType is mandatory")
    private String flatType;

    @Column(name = "block")
    @NotBlank(message = "Field Block is mandatory")
    private String block;

    @Column(name = "street_name")
    @NotBlank(message = "Field StreetName is mandatory")
    private String streetName;

    @Column(name = "storey_range")
    @NotBlank(message = "Field StoreyRange is mandatory")
    private String storeyRange;

    @Column(name = "floor_area_sqm")
    @NotNull
    private Double floorAreaSqm;

    @Column(name = "flat_model")
    @NotBlank(message = "Field FlatModel is mandatory")
    private String flatModel;

    @Column(name = "lease_commence_date")
    @NotNull
    @Min(1900)
    private Integer leaseCommenceDate;

    @Column(name = "remaining_lease")
    @NotBlank(message = "Field RemaingLease is mandatory")
    private String remainingLease;

    @Column(name = "resale_price")
    @NotNull
    private Integer resalePrice;

    public ResaleHdb() {

    }

    public ResaleHdb(String month, String town, String flatType, String block, String streetName, String storeyRange,
            Double floorAreaSqm, String flatModel, Integer leaseCommenceDate, String remainingLease,
            Integer resalePrice) {
        this.month = month;
        this.town = town;
        this.flatType = flatType;
        this.block = block;
        this.streetName = streetName;
        this.storeyRange = storeyRange;
        this.floorAreaSqm = floorAreaSqm;
        this.flatModel = flatModel;
        this.leaseCommenceDate = leaseCommenceDate;
        this.remainingLease = remainingLease;
        this.resalePrice = resalePrice;
    }
}
