package pl.com.britenet.enities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @EqualsAndHashCode.Exclude
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
}
