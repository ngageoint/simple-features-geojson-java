package mil.nga.sf.geojson;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"geometry"})
public interface Coordinates<T> {

	abstract public List<T> getCoordinates();
}
