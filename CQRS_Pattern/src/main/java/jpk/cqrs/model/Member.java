package jpk.cqrs.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * {Member} Entity representation for CRUD operations in read and write databases.
 */
@Entity
@Data
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "firstname")
    String firstname;

    @Column(name = "lastname")
    String lastname;

    @Column(name = "email")
    String email;

}
