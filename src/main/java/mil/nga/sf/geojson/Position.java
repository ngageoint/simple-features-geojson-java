package mil.nga.sf.geojson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import mil.nga.sf.geojson.jackson.CoordinatesDeserializer;
import mil.nga.sf.geojson.jackson.CoordinatesSerializer;

/**
 * Position
 * 
 * @author yutzlejp
 */
@JsonDeserialize(using = CoordinatesDeserializer.class)
@JsonSerialize(using = CoordinatesSerializer.class)
public class Position implements Serializable {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Coordinate values
	 */
	private final double[] coordinates;

	/**
	 * Additional coordinate values beyond long, lat, and altitude, such as m
	 */
	private final List<Double> additionalElements;

	/**
	 * Constructor
	 * 
	 * @param point
	 *            simple point
	 */
	public Position(mil.nga.sf.Point point) {
		this(point.getX(), point.getY(), point.getZ(), point.getM());
	}

	/**
	 * Constructor
	 * 
	 * @param longitude
	 *            longitude value
	 * @param latitude
	 *            latitude value
	 */
	public Position(Double longitude, Double latitude) {
		this(longitude, latitude, null);
	}

	/**
	 * Construct a Coordinates with additional elements. The specification
	 * allows for any number of additional elements in a position, after lng,
	 * lat, alt. https://tools.ietf.org/html/rfc7946#section-3.1.1
	 * 
	 * @param longitude
	 *            The longitude.
	 * @param latitude
	 *            The latitude.
	 * @param altitude
	 *            The altitude.
	 * @param additionalElements
	 *            The additional elements.
	 */
	public Position(Double longitude, Double latitude, Double altitude,
			Double... additionalElements) {
		if ((longitude == null) || (latitude == null)) {
			coordinates = new double[0];
			this.additionalElements = new ArrayList<>();
		} else if (altitude == null) {
			coordinates = new double[] { longitude, latitude };
			this.additionalElements = new ArrayList<>();
		} else {
			coordinates = new double[] { longitude, latitude, altitude };

			if (additionalElementsPresent(additionalElements)) {
				this.additionalElements = Arrays.asList(additionalElements);

				for (Double element : this.additionalElements) {
					if (Double.isNaN(element)) {
						throw new IllegalArgumentException(
								"No additional elements may be NaN.");
					}
					if (Double.isInfinite(element)) {
						throw new IllegalArgumentException(
								"No additional elements may be infinite.");
					}
				}
			} else {
				this.additionalElements = new ArrayList<>();
			}
		}

		checkAltitudeAndAdditionalElements();
	}

	/**
	 * Check if the position has additional elements
	 * 
	 * @return true if additional elements
	 */
	public boolean hasAdditionalElements() {
		return !additionalElements.isEmpty();
	}

	/**
	 * Get the additional elements
	 * 
	 * @return additional elements
	 */
	public List<Double> getAdditionalElements() {
		return additionalElements;
	}

	/**
	 * Verify altitude is present if additional elements were provided
	 */
	private void checkAltitudeAndAdditionalElements() {
		if ((this.getZ() == null) && hasAdditionalElements()) {
			throw new IllegalArgumentException(
					"Additional Elements are only valid if Altitude is also provided.");
		}
	}

	/**
	 * Check if additional elements are present
	 * 
	 * @param additionalElements
	 *            additional elements
	 * @return true if contains additional elements
	 */
	private static boolean additionalElementsPresent(
			Double[] additionalElements) {
		return (additionalElements.length > 0)
				&& (additionalElements[0] != null);
	}

	/**
	 * Get the x value
	 * 
	 * @return x
	 */
	public Double getX() {
		return coordinates.length > 0 ? coordinates[0] : null;
	}

	/**
	 * Get the y value
	 * 
	 * @return y
	 */
	public Double getY() {
		return coordinates.length > 1 ? coordinates[1] : null;
	}

	/**
	 * Get the z value
	 * 
	 * @return z
	 */
	public Double getZ() {
		return hasZ() ? coordinates[2] : null;
	}

	/**
	 * Get the m value
	 * 
	 * @return m
	 */
	public Double getM() {
		return hasM() ? additionalElements.get(0) : null;
	}

	/**
	 * Check if position has a z value
	 * 
	 * @return true if has z value
	 */
	public boolean hasZ() {
		return coordinates.length > 2;
	}

	/**
	 * Check if position has a m value
	 * 
	 * @return true if has m value
	 */
	public boolean hasM() {
		return additionalElements.size() > 0;
	}

	/**
	 * Convert to simple point
	 * 
	 * @return simple point
	 */
	public mil.nga.sf.Point toSimplePoint() {
		mil.nga.sf.Point point = null;
		Double x = getX();
		Double y = getY();
		if (x != null && y != null) {
			point = new mil.nga.sf.Point(x, y, getZ(), getM());
		}
		return point;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalElements == null) ? 0
				: additionalElements.hashCode());
		result = prime * result + Arrays.hashCode(coordinates);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (additionalElements == null) {
			if (other.additionalElements != null)
				return false;
		} else if (!additionalElements.equals(other.additionalElements))
			return false;
		if (!Arrays.equals(coordinates, other.coordinates))
			return false;
		return true;
	}

}
