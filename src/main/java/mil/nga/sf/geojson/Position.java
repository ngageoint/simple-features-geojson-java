package mil.nga.sf.geojson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mil.nga.sf.geojson.jackson.CoordinatesDeserializer;
import mil.nga.sf.geojson.jackson.CoordinatesSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize(using = CoordinatesDeserializer.class)
@JsonSerialize(using = CoordinatesSerializer.class)
public class Position implements Serializable {

	private static final long serialVersionUID = 4454307738904889423L;

	private final double[] coordinates;
	
	/**
	 * 
	 */
	private final List<Double> additionalElements;
	
	public Position(mil.nga.sf.Point point) {
		this(point.getX(), point.getY(), point.getZ(), point.getM());
	}
	
	public Position(Double longitude, Double latitude) {
		this(longitude, latitude, null);
	}

	/**
	 * Construct a Coordinates with additional elements.
	 * The specification allows for any number of additional elements in a position, after lng, lat, alt.
	 * https://tools.ietf.org/html/rfc7946#section-3.1.1
	 * @param longitude The longitude.
	 * @param latitude The latitude.
	 * @param altitude The altitude.
	 * @param additionalElements The additional elements.
     */
	public Position(Double longitude, Double latitude, Double altitude, Double... additionalElements) {
		if ((longitude == null) || (latitude == null)){
			coordinates = new double[0];
			this.additionalElements = new ArrayList<Double>();
		} else if (altitude == null){
			coordinates = new double[]{longitude, latitude};
			this.additionalElements = new ArrayList<Double>();
		} else {
			coordinates = new double[]{longitude, latitude, altitude};

			if (additionalElementsPresent(additionalElements)) {
				this.additionalElements = Arrays.asList(additionalElements);
	
				for(Double element : this.additionalElements) {
					if (Double.isNaN(element)) {
						throw new IllegalArgumentException("No additional elements may be NaN.");
					}
					if (Double.isInfinite(element)) {
						throw new IllegalArgumentException("No additional elements may be infinite.");
					}
				}
			} else {
				this.additionalElements = new ArrayList<Double>();
			}
		}

		checkAltitudeAndAdditionalElements();
	}

	public boolean hasAdditionalElements() {
		return !additionalElements.isEmpty();
	}

	public List<Double> getAdditionalElements() {
		return additionalElements;
	}

	private void checkAltitudeAndAdditionalElements() {
		if ((this.getZ() == null) && hasAdditionalElements()) {
			throw new IllegalArgumentException("Additional Elements are only valid if Altitude is also provided.");
		}
	}
	
	private static boolean additionalElementsPresent(Double[] additionalElements){
		 return (additionalElements.length > 0) && (additionalElements[0] != null);
	}

	public Double getX() {
		return coordinates.length > 0 ? coordinates[0] : null;
	}

	public Double getY() {
		return coordinates.length > 1 ? coordinates[1] : null;
	}

	public Double getZ() {
		return hasZ() ? coordinates[2] : null;
	}

	public Double getM() {
		return hasM() ? additionalElements.get(0) : null;
	}

	public boolean hasZ() {
		return coordinates.length > 2;
	}

	public boolean hasM() {
		return additionalElements.size() > 0;
	}
	
	public mil.nga.sf.Point toSimplePoint(){
		mil.nga.sf.Point point = null;
		Double x = getX();
		Double y = getY();
		if(x != null && y != null){
			point =  new mil.nga.sf.Point(x, y, getZ(), getM());
		}
		return point;
	}
	
}
