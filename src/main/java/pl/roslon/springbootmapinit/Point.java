package pl.roslon.springbootmapinit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Point {

    private double lat;
    private double lon;
    private String text;

}
