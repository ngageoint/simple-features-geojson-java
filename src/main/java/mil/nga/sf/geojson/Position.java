package mil.nga.sf.geojson;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import mil.nga.sf.geojson.jackson.CoordinatesDeserializer;
import mil.nga.sf.geojson.jackson.CoordinatesSerializer;
import java.io.Serializable;
import java.util.Arrays;

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
	private double[] additionalElements = new double[0];

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
	public Position(double longitude, double latitude, double altitude, double... additionalElements) {
		super(longitude, latitude, altitude, (additionalElements != null) && (additionalElements.length > 0) ? additionalElements[0] : Double.NaN);

		if (additionalElements != null) {
			this.additionalElements = additionalElements;
		}

		for(double element : this.additionalElements) {
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
		return !Double.isNaN(getAltitude());
	}

	public boolean hasAdditionalElements() {
		return additionalElements.length > 0;
	}

	public double getLongitude() {
		return getX();
	}

	public double getLatitude() {
		return getY();
	}

	public double getAltitude() {
		return getZ();
	}

	public double[] getAdditionalElements() {
		return additionalElements;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Position)) {
			return false;
		}
		Position position = (Position)o;
		return Double.compare(position.getLatitude(), getLatitude()) == 0 && Double.compare(position.getLongitude(), getLongitude()) == 0
				&& Double.compare(position.getAltitude(), getAltitude()) == 0 &&
				Arrays.equals(position.getAdditionalElements(), additionalElements);
	}

	@Override
	public int hashCode() {
		long temp = Double.doubleToLongBits(getLongitude());
		int result = (int)(temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(getLatitude());
		result = 31 * result + (int)(temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(getAltitude());
		result = 31 * result + (int)(temp ^ (temp >>> 32));
		for(double element : additionalElements) {
			temp = Double.doubleToLongBits(element);
			result = 31 * result + (int) (temp ^ (temp >>> 32));
		}
		return result;
	}

	@Override
	public String toString() {
		String s =  "Position{" + "longitude=" + getLongitude() + ", latitude=" + getLatitude() + ", altitude=" + getAltitude();

		if (hasAdditionalElements()) {
			s += ", additionalElements=[";

			String suffix = "";
			for (Double element : additionalElements) {
				if (element != null) {
					s += suffix + element;
					suffix = ", ";
				}
			}
			s += ']';
		}

		s += '}';

		return s;
	}

	private void checkAltitudeAndAdditionalElements() {
		if (!hasAltitude() && hasAdditionalElements()) {
			throw new IllegalArgumentException("Additional Elements are only valid if Altitude is also provided.");
		}
	}
}
