package entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@AllArgsConstructor
public class Customer {
    @Id
    private String id;
    private String name;
    private String contactNumber;
    private String email;


}
