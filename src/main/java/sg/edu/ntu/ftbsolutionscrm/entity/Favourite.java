package sg.edu.ntu.ftbsolutionscrm.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "favourite", schema = "public")
public class Favourite {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private HDBUser user;

    @ManyToOne
    @JoinColumn(name = "flat_id", nullable = false)
    @JsonIgnoreProperties("favourites")
    private ResaleHdb flat;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Favourite() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResaleHdb getFlat() {
        return flat;
    }

    public void setFlat(ResaleHdb flat) {
        this.flat = flat;
    }

    public HDBUser getUser() {
        return user;
    }

    public void setUser(HDBUser user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
