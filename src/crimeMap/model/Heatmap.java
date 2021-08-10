package crimeMap.model;

import java.util.ArrayList;
import java.util.List;

public class Heatmap {
    protected List<Double> lng;
    protected List<Double> lat;

    public Heatmap(List<Double> lng, List<Double> lat) {
        this.lng = lng;
        this.lat = lat;
    }
}
