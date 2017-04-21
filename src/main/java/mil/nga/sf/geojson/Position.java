package mil.nga.sf.geojson;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import mil.nga.sf.geojson.jackson.CoordinatesDeserializer;
import mil.nga.sf.geojson.jackson.CoordinatesSerializer;
import java.io.Serializable;

@JsonDeserialize(using = CoordinatesDeserializer.class)
@JsonSerialize(using = CoordinatesSerializer.class)
public class Position extends mil.nga.sf.Position implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6991668001425440884L;
	/**
	 * 
	 */
	private Double[] additionalElements = new Double[0];

	public Position() {
		super();
	}

	public Position(double longitude, double latitude) {
		super(longitude, latitude);
	}

	public Position(double longitude, double latitude, double altitude) {
		super(longitude, latitude, altitude);
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
		super(longitude, latitude, altitude, (additionalElements != null) && (additionalElements.length > 0) ? additionalElements[0] : Double.NaN);

		if (additionalElements != null) {
			this.additionalElements = additionalElements;
		}

		for(Double element : this.additionalElements) {
			if (Double.isNaN(element)) {
				throw new IllegalArgumentException("No additional elements may be NaN.");
			}
			if (Double.isInfinite(element)) {
				throw new IllegalArgumentException("No additional elements may be infinite.");
			}
		}

		checkAltitudeAndAdditionalElements();
	}

	public boolean hasAltitude() {
		return !(getAltitude() == null);
	}

	public boolean hasAdditionalElements() {
		return additionalElements.length > 0;
	}

	public Double getLongitude() {
		return getX();
	}

	public Double getLatitude() {
		return getY();
	}

	public Double getAltitude() {
		return getZ();
	}

	public Double[] getAdditionalElements() {
		return additionalElements;
	}

	private void checkAltitudeAndAdditionalElements() {
		if (!hasAltitude() && hasAdditionalElements()) {
			throw new IllegalArgumentException("Additional Elements are only valid if Altitude is also provided.");
		}
	}
}
