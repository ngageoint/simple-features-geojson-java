package mil.nga.sf.geojson.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import mil.nga.sf.geojson.GeoJsonObject;
import mil.nga.sf.geojson.GeoJsonObjectFactory;
import mil.nga.sf.geojson.MultiPoint;
import mil.nga.sf.geojson.Position;

public class MultiPointTest {

	private static String MULTIPOINT = "{\"type\":\"MultiPoint\",\"coordinates\":[[100.0,10.0],[101.0,1.0]]}";
	private static String MULTIPOINT_WITH_ALT = "{\"type\":\"MultiPoint\",\"coordinates\":[[100.0,10.0,-20.0],[101.0,1.0,-10.0]]}";
	private ObjectMapper mapper = new ObjectMapper();


	@Test
	public void itShouldSerializeASFMultiPoint() throws Exception {
		List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(100d, 10d));
		positions.add(new Position(101d, 1d));
		mil.nga.sf.MultiPoint multiPoint = new mil.nga.sf.MultiPoint(positions);
		String text = mapper.writeValueAsString(GeoJsonObjectFactory.createObject(multiPoint));
		assertEquals(MULTIPOINT, text);
	}

	@Test
	public void itShouldSerializeASFMultiPointWithAltitude() throws Exception {
		List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(100d, 10d, -20d));
		positions.add(new Position(101d, 1d, -10d));
		mil.nga.sf.MultiPoint multiPoint = new mil.nga.sf.MultiPoint(positions);
		String text = mapper.writeValueAsString(GeoJsonObjectFactory.createObject(multiPoint));
		assertEquals(MULTIPOINT_WITH_ALT, text);
	}

	@Test
	public void itShouldDeserializeAMultiPoint() throws Exception {
		GeoJsonObject value = mapper.readValue(MULTIPOINT, GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof MultiPoint);
		MultiPoint multiPoint = (MultiPoint)value;
		List<Position> positions = multiPoint.getCoordinates();
		assertEquals(2, positions.size());
		TestUtils.assertPosition(100d, 10d, null, null, positions.get(0));
		TestUtils.assertPosition(101d, 1.0d, null, null, positions.get(1));
	}

	@Test
	public void itShouldDeserializeAMultiPointWithAltitude() throws Exception {
		GeoJsonObject value = mapper.readValue(MULTIPOINT_WITH_ALT, GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof MultiPoint);
		MultiPoint multiPoint = (MultiPoint)value;
		List<Position> positions = multiPoint.getCoordinates();
		assertEquals(2, positions.size());
		TestUtils.assertPosition(100d, 10d, -20d, null, positions.get(0));
		TestUtils.assertPosition(101d, 1d, -10d, null, positions.get(1));
	}
}
