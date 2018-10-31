package pl.com.britenet.enities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    @EqualsAndHashCode.Exclude
    private Integer id;
    private Integer idCustomer;
    private Integer type;
    private String contact;
}
