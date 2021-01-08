package pl.coderslab.charity.entities;import lombok.Getter;import lombok.Setter;import org.springframework.format.annotation.DateTimeFormat;import javax.persistence.*;import javax.validation.constraints.NotBlank;import javax.validation.constraints.NotNull;import javax.validation.constraints.Positive;import javax.validation.constraints.Size;import java.time.LocalDate;import java.time.LocalTime;import java.util.ArrayList;import java.util.HashSet;import java.util.List;import java.util.Set;@Entity@Getter@Setter@Table(name = "donations")public class Donation {    @Id    @GeneratedValue(strategy = GenerationType.IDENTITY)    private Long id;    @Positive    private Integer quantity;    @ManyToMany    private Set<Category> categories = new HashSet<>();    @NotNull    @ManyToOne    @JoinColumn(name = "institution_id" )    private Institution institution;    @NotBlank    @Size(max = 50)    private String street;    @Size(max = 50)    private String city;    @Size(min = 5, max = 6)    private String zipCode;    @Size(min = 12, max = 16)    private String phoneNumber;    @DateTimeFormat(pattern = "yyyy-MM-dd")    private LocalDate pickUpDate;    private LocalTime pickUpTime;    private String pickUpComment;    public Donation() {    }}