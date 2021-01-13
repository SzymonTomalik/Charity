package pl.coderslab.charity.models;import lombok.Getter;import lombok.Setter;import javax.persistence.Column;import javax.validation.constraints.Email;import javax.validation.constraints.NotBlank;import javax.validation.constraints.Size;@Getter@Setterpublic class RegistrationForm {    @NotBlank(message = "firstName is mandatory")    @Size(max = 30)    private String firstName;    @NotBlank(message = "lastName is mandatory")    @Size(max = 30)    private String lastName;    @Email    @Column(unique = true)    @NotBlank(message = "Email is mandatory")    private String email;    @NotBlank    @Size(min = 8)    private String pass1;    @NotBlank    private String pass2;    public RegistrationForm() {    }}