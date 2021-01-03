package pl.coderslab.charity.entities;import lombok.Getter;import lombok.Setter;import javax.persistence.*;import javax.validation.constraints.NotBlank;import javax.validation.constraints.NotNull;import javax.validation.constraints.Positive;import javax.validation.constraints.Size;import java.time.LocalDate;import java.time.LocalTime;import java.util.List;@Entity@Getter@Setter@Table(name = "donations")public class Donation {    @Id    @GeneratedValue(strategy = GenerationType.IDENTITY)    private Long id;    @Positive    private Integer quantity;    @OneToMany(targetEntity = Category.class, mappedBy = "donation")    private List<Category> categories;    @NotNull    @OneToOne    private Institution institution;    @NotBlank    @Size(max = 50)    private String street;    @Size(max = 50)    private String city;    @Size(min = 5, max = 6)    private String zipCode;    private LocalDate pickUpDate;    private LocalTime pickUpTime;    private String pickUpComment;    public Donation() {    }}