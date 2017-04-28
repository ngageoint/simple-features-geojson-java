package mil.nga.sf.geojson.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import mil.nga.sf.LinearRing;
import mil.nga.sf.geojson.GeoJsonObject;
import mil.nga.sf.geojson.GeoJsonObjectFactory;
import mil.nga.sf.geojson.LineString;
import mil.nga.sf.geojson.Position;

public class PolygonTest {

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void itShouldSerializeASFPolygon() throws Exception {
		List<LinearRing> rings = new ArrayList<LinearRing>();
		List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(100d, 10d));
		positions.add(new Position(101d, 1d));
		positions.add(new Position(101d, 10d));
		LinearRing ring = new LinearRing(positions);
		rings.add(ring);
		mil.nga.sf.Polygon polygon = new mil.nga.sf.Polygon(rings);
		String text = mapper.writeValueAsString(GeoJsonObjectFactory.createObject(polygon));
		assertEquals("{\"type\":\"Polygon\",\"coordinates\":[[[100.0,10.0],[101.0,1.0],[101.0,10.0],[100.0,10.0]]]}",
				text);
	}

	@Test
	public void itShouldSerializeASFPolygonWithAltitude() throws Exception {
		List<LinearRing> rings = new ArrayList<LinearRing>();
		List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(100d, 10d,  5d));
		positions.add(new Position(101d,  1d, 10d));
		positions.add(new Position(101d, 10d, 15d));
		LinearRing ring = new LinearRing(positions);
		rings.add(ring);
		mil.nga.sf.Polygon polygon = new mil.nga.sf.Polygon(rings);
		String text = mapper.writeValueAsString(GeoJsonObjectFactory.createObject(polygon));
		assertEquals("{\"type\":\"Polygon\",\"coordinates\":[[[100.0,10.0,5.0],[101.0,1.0,10.0],[101.0,10.0,15.0],[100.0,10.0,5.0]]]}",
				text);
	}

	@Test
	public void itShouldSerializeASFPolygonWithRings() throws Exception {
		List<LinearRing> rings = new ArrayList<LinearRing>();
		List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(-100d, -50d));
		positions.add(new Position( 100d, -50d));
		positions.add(new Position(   1d,  50d));
		LinearRing ring = new LinearRing(positions);
		rings.add(ring);
		positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(-50d, -25d));
		positions.add(new Position( 50d, -25d));
		positions.add(new Position( -1d,  25d));
		ring = new LinearRing(positions);
		rings.add(ring);
		mil.nga.sf.Polygon polygon = new mil.nga.sf.Polygon(rings);
		String text = mapper.writeValueAsString(GeoJsonObjectFactory.createObject(polygon));
		assertEquals("{\"type\":\"Polygon\",\"coordinates\":[[[-100.0,-50.0],[100.0,-50.0],[1.0,50.0],[-100.0,-50.0]],[[-50.0,-25.0],[50.0,-25.0],[-1.0,25.0],[-50.0,-25.0]]]}",
				text);
	}

//
//	@Test
//	public void itShouldDeserializeALineString() throws Exception {
//		GeoJsonObject value = mapper
//				.readValue("{\"type\":\"LineString\",\"coordinates\":[[100.0, 0.0],[101.0, 1.0]]}", GeoJsonObject.class);
//		assertNotNull(value);
//		assertTrue(value instanceof LineString);
//		LineString lineString = (LineString)value;
//		List<Position> positions = lineString.getCoordinates();
//		TestUtils.assertPosition(100d, 0d, null, null, positions.get(0));
//		TestUtils.assertPosition(101d, 1.0d, null, null, positions.get(1));
//	}
//
//	@Test
//	public void itShouldDeserializeALineStringWithAltitude() throws Exception {
//		GeoJsonObject value = mapper
//				.readValue("{\"type\":\"LineString\",\"coordinates\":[[100.0, 10.0, -20.0],[101.0, 1.0, -10.0]]}", GeoJsonObject.class);
//		assertNotNull(value);
//		assertTrue(value instanceof LineString);
//		LineString lineString = (LineString)value;
//		List<Position> positions = lineString.getCoordinates();
//		TestUtils.assertPosition(100d, 10d, -20d, null, positions.get(0));
//		TestUtils.assertPosition(101d, 1d, -10d, null, positions.get(1));
//	}
}
