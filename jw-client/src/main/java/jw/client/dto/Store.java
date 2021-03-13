package jw.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Store {

    private String name;
    private String address;

    public Store() {
    }
}
