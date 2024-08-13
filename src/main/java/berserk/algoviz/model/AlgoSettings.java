package berserk.algoviz.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlgoSettings{

    private int length;
    private int lowerBound = 0;
    private int upperBound = 100;
}
