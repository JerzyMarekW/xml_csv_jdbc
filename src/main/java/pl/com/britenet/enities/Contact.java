package pl.com.britenet.enities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    private Integer id;
    private Integer idCustomer;
    private Integer type;
    private String contact;
}
