package mil.nga.sf.geojson.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.TestCase;
import mil.nga.sf.geojson.GeoJsonObject;
import mil.nga.sf.geojson.GeoJsonObjectFactory;
import mil.nga.sf.geojson.Geometry;
import mil.nga.sf.geojson.Point;
import mil.nga.sf.geojson.Position;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class PointTest {

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void itShouldSerializeASFPoint() throws Exception {
		mil.nga.sf.Point point = new mil.nga.sf.Point(new mil.nga.sf.Position(100d, 10d));
		Geometry geometry = GeoJsonObjectFactory.createObject(point);
		JsonNode pointFromPojo = mapper.valueToTree(geometry);
		JsonNode pointFromString = mapper.readTree("{\"type\":\"Point\",\"coordinates\":[100.0,10.0]}");
		TestCase.assertEquals(pointFromPojo, pointFromString);
	}

	@Test
	public void itShouldDeserializeAPoint() throws Exception {
		GeoJsonObject value = mapper
				.readValue("{\"type\":\"Point\",\"coordinates\":[100.0,5.0]}", GeoJsonObject.class);
		TestCase.assertNotNull(value);
		TestCase.assertTrue(value instanceof Point);
		Point point = (Point)value;
		TestUtils.assertPoint(100d, 5d, null, point);
	}

	@Test
	public void itShouldDeserializeAPointWithAltitude() throws Exception {
		GeoJsonObject value = mapper.readValue("{\"type\":\"Point\",\"coordinates\":[100.0,5.0,123]}",
				GeoJsonObject.class);
		Point point = (Point)value;
		TestUtils.assertPoint(100d, 5d, 123d, point);
	}

	@Test
	public void itShouldSerializeAPointWithAltitude() throws Exception {
		mil.nga.sf.Point point = new mil.nga.sf.Point(new mil.nga.sf.Position(100d, 10d, 256d));
		Geometry geometry = GeoJsonObjectFactory.createObject(point);
		JsonNode pointFromPojo = mapper.valueToTree(geometry);
		JsonNode pointFromString = mapper.readTree("{\"type\":\"Point\",\"coordinates\":[100.0,10.0,256.0]}");
		TestCase.assertEquals(pointFromPojo, pointFromString);
	}

	@Test
	public void itShouldDeserializeAPointWithAdditionalAttributes() throws IOException {
		GeoJsonObject value = mapper.readValue("{\"type\":\"Point\",\"coordinates\":[100.0,5.0,123,456,789.2]}",
				GeoJsonObject.class);
		Point point = (Point)value;
		TestUtils.assertPoint(100d, 5d, 123d, new ArrayList<Double>(Arrays.asList(456d, 789.2)), point);
	}

	@Test
	public void itShouldSerializeAPointWithAdditionalAttributes() throws IOException {
		Position position = new Position(100d, 0d, 256d, 345d, 678d);
		mil.nga.sf.Point point = new mil.nga.sf.Point(position);
		Geometry geometry = GeoJsonObjectFactory.createObject(point);
		JsonNode pointFromPojo = mapper.valueToTree(geometry);
		JsonNode pointFromString = mapper.readTree("{\"type\":\"Point\",\"coordinates\":[100.0,0.0,256.0,345.0,678.0]}");
		TestCase.assertEquals(pointFromPojo, pointFromString);
	}
}
