package mil.nga.sf.geojson.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mil.nga.sf.geojson.GeoJsonObject;
import mil.nga.sf.geojson.GeoJsonObjectFactory;
import mil.nga.sf.geojson.Point;
import mil.nga.sf.geojson.Position;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PointTest {

	private ObjectMapper mapper = new ObjectMapper();

	public static void assertPoint(Double expectedLongitude, Double expectedLatitude, Double expectedAltitude,
									   Point point) {
		assertPoint(expectedLongitude, expectedLatitude, expectedAltitude, new Double[0], point);
	}

	public static void assertPoint(Double expectedLongitude, Double expectedLatitude, Double expectedAltitude,
									   Double[] expectedAdditionalElements, Point point) {
		assertEquals(expectedLongitude, point.getCoordinates().getLongitude(), 0.00001);
		assertEquals(expectedLatitude, point.getCoordinates().getLatitude(), 0.00001);
		if (expectedAltitude == null) {
			assertFalse(point.getCoordinates().hasAltitude());
		} else {
			assertEquals(expectedAltitude, point.getCoordinates().getAltitude(), 0.00001);
			assertTrue(Arrays.equals(expectedAdditionalElements, point.getCoordinates().getAdditionalElements()));
		}
	}

	@Test
	public void itShouldSerializeASFPoint() throws Exception {
		mil.nga.sf.Point point = new mil.nga.sf.Point(100d, 10d);
		String text = mapper.writeValueAsString(GeoJsonObjectFactory.createObject(point));
		assertEquals("{\"type\":\"Point\",\"coordinates\":[100.0,10.0]}",
				text);
	}

	@Test
	public void itShouldDeserializeAPoint() throws Exception {
		GeoJsonObject value = mapper
				.readValue("{\"type\":\"Point\",\"coordinates\":[100.0,5.0]}", GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof Point);
		Point point = (Point)value;
		assertPoint(100d, 5d, null, point);
	}

	@Test
	public void itShouldDeserializeAPointWithAltitude() throws Exception {
		GeoJsonObject value = mapper.readValue("{\"type\":\"Point\",\"coordinates\":[100.0,5.0,123]}",
				GeoJsonObject.class);
		Point point = (Point)value;
		assertPoint(100d, 5d, 123d, point);
	}

	@Test
	public void itShouldSerializeAPointWithAltitude() throws Exception {
		mil.nga.sf.Point point = new mil.nga.sf.Point(100d, 0d, 256d);
		assertEquals("{\"type\":\"Point\",\"coordinates\":[100.0,0.0,256.0]}",
				mapper.writeValueAsString(GeoJsonObjectFactory.createObject(point)));
	}

	@Test
	public void itShouldDeserializeAPointWithAdditionalAttributes() throws IOException {
		GeoJsonObject value = mapper.readValue("{\"type\":\"Point\",\"coordinates\":[100.0,5.0,123,456,789.2]}",
				GeoJsonObject.class);
		Point point = (Point)value;
		assertPoint(100d, 5d, 123d, new Double[] {456d, 789.2}, point);
	}

	@Test
	public void itShouldSerializeAPointWithAdditionalAttributes() throws JsonProcessingException {
		Position position = new Position(100d, 0d, 256d, 345d, 678d);
		Point point = new Point(position);
		assertEquals("{\"type\":\"Point\",\"coordinates\":[100.0,0.0,256.0,345.0,678.0]}",
				mapper.writeValueAsString(point));
	}
}
