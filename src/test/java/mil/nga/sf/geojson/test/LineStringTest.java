package mil.nga.sf.geojson.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


import mil.nga.sf.geojson.GeoJsonObject;
import mil.nga.sf.geojson.LineString;
import mil.nga.sf.geojson.Position;

public class LineStringTest {

	@Test
	public void itShouldSerializeASFLineString() throws Exception {
		List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(100d, 10d));
		positions.add(new Position(101d, 1d));
		mil.nga.sf.LineString lineString = new mil.nga.sf.LineString(positions);
		TestUtils.compareAsNodes(lineString, "{\"type\":\"LineString\",\"coordinates\":[[100.0,10.0],[101.0,1.0]]}");
	}

	@Test
	public void itShouldSerializeASFLineStringWithAltitude() throws Exception {
		List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(100d, 10d, 15d));
		positions.add(new Position(101d, 1d, 11d));
		mil.nga.sf.LineString lineString = new mil.nga.sf.LineString(positions);
		TestUtils.compareAsNodes(lineString, "{\"type\":\"LineString\",\"coordinates\":[[100.0,10.0,15.0],[101.0,1.0,11.0]]}");
	}

	@Test
	public void itShouldDeserializeALineString() throws Exception {
		GeoJsonObject value = TestUtils.getMapper()
				.readValue("{\"type\":\"LineString\",\"coordinates\":[[100.0, 0.0],[101.0, 1.0]]}", GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof LineString);
		LineString lineString = (LineString)value;
		List<Position> positions = lineString.getCoordinates();
		assertEquals(2, positions.size());
		TestUtils.assertPosition(100d, 0d, null, null, positions.get(0));
		TestUtils.assertPosition(101d, 1.0d, null, null, positions.get(1));
	}

	@Test
	public void itShouldDeserializeALineStringWithAltitude() throws Exception {
		GeoJsonObject value = TestUtils.getMapper()
				.readValue("{\"type\":\"LineString\",\"coordinates\":[[100.0, 10.0, -20.0],[101.0, 1.0, -10.0]]}", GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof LineString);
		LineString lineString = (LineString)value;
		List<Position> positions = lineString.getCoordinates();
		assertEquals(2, positions.size());
		TestUtils.assertPosition(100d, 10d, -20d, null, positions.get(0));
		TestUtils.assertPosition(101d, 1d, -10d, null, positions.get(1));
	}
}
