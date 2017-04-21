package mil.nga.sf.geojson.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mil.nga.sf.geojson.GeoJsonObject;
import mil.nga.sf.geojson.Point;

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
	public void itShouldSerializeAPoint() throws Exception {
		Point point = new Point(100, 0);
		assertEquals("{\"type\":\"Point\",\"coordinates\":[100.0,0.0]}",
				mapper.writeValueAsString(point));
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
		Point point = new Point(100, 0, 256);
		assertEquals("{\"type\":\"Point\",\"coordinates\":[100.0,0.0,256.0]}",
				mapper.writeValueAsString(point));
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
		Point point = new Point(100d, 0d, 256d, 345d, 678d);
		assertEquals("{\"type\":\"Point\",\"coordinates\":[100.0,0.0,256.0,345.0,678.0]}",
				mapper.writeValueAsString(point));
	}

	@Test
	public void itShouldSerializeAPointWithAdditionalAttributesAndNull() throws JsonProcessingException {
		Point point = new Point(100d, 0d, 256d, 345d, 678d);
		assertEquals("{\"type\":\"Point\",\"coordinates\":[100.0,0.0,256.0,345.0,678.0]}",
				mapper.writeValueAsString(point));
	}
}
