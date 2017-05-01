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
import mil.nga.sf.geojson.MultiPolygon;
import mil.nga.sf.geojson.Polygon;
import mil.nga.sf.geojson.Position;

public class MultiPolygonTest {

	private static String MULTIPOLYGON = "{\"type\":\"MultiPolygon\",\"coordinates\":[[[[100.0,10.0],[101.0,1.0],[101.0,10.0],[100.0,10.0]]]]}";
	private static String MULTIPOLYGON_WITH_ALT = "{\"type\":\"MultiPolygon\",\"coordinates\":[[[[100.0,10.0,5.0],[101.0,1.0,10.0],[101.0,10.0,15.0],[100.0,10.0,5.0]]]]}";
	private static String MULTIPOLYGON_WITH_RINGS = "{\"type\":\"MultiPolygon\",\"coordinates\":[[[[-100.0,-50.0],[100.0,-50.0],[1.0,50.0],[-100.0,-50.0]],[[-50.0,-25.0],[50.0,-25.0],[-1.0,25.0],[-50.0,-25.0]]]]}";
	private static String MULTIPOLYGON_WITH_MULTI = "{\"type\":\"MultiPolygon\",\"coordinates\":[[[[-100.0,-50.0],[100.0,-50.0],[1.0,50.0],[-100.0,-50.0]]],[[[-50.0,-25.0],[50.0,-25.0],[-1.0,25.0],[-50.0,-25.0]]]]}";
	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void itShouldSerializeASFMultiPolygon() throws Exception {
		List<mil.nga.sf.Polygon> polygons = new ArrayList<mil.nga.sf.Polygon>();
		List<LinearRing> rings = new ArrayList<LinearRing>();
		List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(100d, 10d));
		positions.add(new Position(101d, 1d));
		positions.add(new Position(101d, 10d));
		LinearRing ring = new LinearRing(positions);
		rings.add(ring);
		mil.nga.sf.Polygon polygon = new mil.nga.sf.Polygon(rings);
		polygons.add(polygon);
		mil.nga.sf.MultiPolygon multiPolygon = new mil.nga.sf.MultiPolygon(polygons);
		String text = mapper.writeValueAsString(GeoJsonObjectFactory.createObject(multiPolygon));
		assertEquals(MULTIPOLYGON, text);
	}

	@Test
	public void itShouldSerializeASFMultiPolygonWithAltitude() throws Exception {
		List<mil.nga.sf.Polygon> polygons = new ArrayList<mil.nga.sf.Polygon>();
		List<LinearRing> rings = new ArrayList<LinearRing>();
		List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(100d, 10d,  5d));
		positions.add(new Position(101d,  1d, 10d));
		positions.add(new Position(101d, 10d, 15d));
		LinearRing ring = new LinearRing(positions);
		rings.add(ring);
		mil.nga.sf.Polygon polygon = new mil.nga.sf.Polygon(rings);
		polygons.add(polygon);
		mil.nga.sf.MultiPolygon multiPolygon = new mil.nga.sf.MultiPolygon(polygons);
		String text = mapper.writeValueAsString(GeoJsonObjectFactory.createObject(multiPolygon));
		assertEquals(MULTIPOLYGON_WITH_ALT, text);
	}

	@Test
	public void itShouldSerializeASFMultiPolygonWithRings() throws Exception {
		List<mil.nga.sf.Polygon> polygons = new ArrayList<mil.nga.sf.Polygon>();
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
		polygon = new mil.nga.sf.Polygon(rings);
		polygons.add(polygon);
		mil.nga.sf.MultiPolygon multiPolygon = new mil.nga.sf.MultiPolygon(polygons);
		String text = mapper.writeValueAsString(GeoJsonObjectFactory.createObject(multiPolygon));
		assertEquals(MULTIPOLYGON_WITH_RINGS, text);
	}

	@Test
	public void itShouldSerializeASFMultiPolygonWithMulti() throws Exception {
		List<mil.nga.sf.Polygon> polygons = new ArrayList<mil.nga.sf.Polygon>();
		// Polygon 1
		List<LinearRing> rings = new ArrayList<LinearRing>();
		List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(-100d, -50d));
		positions.add(new Position( 100d, -50d));
		positions.add(new Position(   1d,  50d));
		LinearRing ring = new LinearRing(positions);
		rings.add(ring);
		mil.nga.sf.Polygon polygon = new mil.nga.sf.Polygon(rings);
		polygons.add(polygon);

		// P2R1
		rings = new ArrayList<LinearRing>();
		positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(-50d, -25d));
		positions.add(new Position( 50d, -25d));
		positions.add(new Position( -1d,  25d));
		ring = new LinearRing(positions);
		rings.add(ring);
		polygon = new mil.nga.sf.Polygon(rings);
		polygons.add(polygon);
		
		mil.nga.sf.MultiPolygon multiPolygon = new mil.nga.sf.MultiPolygon(polygons);
		String text = mapper.writeValueAsString(GeoJsonObjectFactory.createObject(multiPolygon));
		assertEquals(MULTIPOLYGON_WITH_MULTI, text);
	}

	@Test
	public void itShouldDeserializeAMultiPolygon() throws Exception {
		GeoJsonObject value = mapper.readValue(MULTIPOLYGON, GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof MultiPolygon);
		MultiPolygon gjMultiPolygon = (MultiPolygon)value;
		mil.nga.sf.Geometry geometry = gjMultiPolygon.getGeometry();
		assertTrue(geometry instanceof mil.nga.sf.MultiPolygon);
		mil.nga.sf.MultiPolygon multiPolygon = (mil.nga.sf.MultiPolygon)geometry;
		List<mil.nga.sf.Polygon> polygons = multiPolygon.getPolygons();
		assertTrue(polygons.size() == 1);
		mil.nga.sf.Polygon polygon = polygons.get(0);
		List<LinearRing> rings = polygon.getRings();
		assertTrue(rings.size() == 1);
		mil.nga.sf.LinearRing ring = rings.get(0);
		List<mil.nga.sf.Position> positions = ring.getPositions();
		TestUtils.assertPosition(100d, 10d, null, null, positions.get(0));
		TestUtils.assertPosition(101d,  1d, null, null, positions.get(1));
		TestUtils.assertPosition(101d, 10d, null, null, positions.get(2));
		TestUtils.assertPosition(100d, 10d, null, null, positions.get(3));
	}

	@Test
	public void itShouldDeserializeAMultiPolygonWithAltitude() throws Exception {
		GeoJsonObject value = mapper.readValue(MULTIPOLYGON_WITH_ALT, GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof MultiPolygon);
		MultiPolygon gjMultiPolygon = (MultiPolygon)value;
		mil.nga.sf.Geometry geometry = gjMultiPolygon.getGeometry();
		assertTrue(geometry instanceof mil.nga.sf.MultiPolygon);
		mil.nga.sf.MultiPolygon multiPolygon = (mil.nga.sf.MultiPolygon)geometry;
		List<mil.nga.sf.Polygon> polygons = multiPolygon.getPolygons();
		assertTrue(polygons.size() == 1);
		mil.nga.sf.Polygon polygon = polygons.get(0);
		List<LinearRing> rings = polygon.getRings();
		assertTrue(rings.size() == 1);
		mil.nga.sf.LinearRing ring = rings.get(0);
		List<mil.nga.sf.Position> positions = ring.getPositions();
		TestUtils.assertPosition(100d, 10d,  5d, null, positions.get(0));
		TestUtils.assertPosition(101d,  1d, 10d, null, positions.get(1));
		TestUtils.assertPosition(101d, 10d, 15d, null, positions.get(2));
		TestUtils.assertPosition(100d, 10d,  5d, null, positions.get(3));
	}

	@Test
	public void itShouldDeserializeAMultiPolygonWithRings() throws Exception {
		GeoJsonObject value = mapper.readValue(MULTIPOLYGON_WITH_RINGS, GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof MultiPolygon);
		MultiPolygon gjMultiPolygon = (MultiPolygon)value;
		mil.nga.sf.Geometry geometry = gjMultiPolygon.getGeometry();
		assertTrue(geometry instanceof mil.nga.sf.MultiPolygon);
		mil.nga.sf.MultiPolygon multiPolygon = (mil.nga.sf.MultiPolygon)geometry;
		List<mil.nga.sf.Polygon> polygons = multiPolygon.getPolygons();
		assertTrue(polygons.size() == 1);
		mil.nga.sf.Polygon polygon = polygons.get(0);
		List<LinearRing> rings = polygon.getRings();
		assertTrue(rings.size() == 2);
		mil.nga.sf.LinearRing ring = rings.get(0);
		List<mil.nga.sf.Position> positions = ring.getPositions();
		TestUtils.assertPosition(-100d, -50d, null, null, positions.get(0));
		TestUtils.assertPosition( 100d, -50d, null, null, positions.get(1));
		TestUtils.assertPosition(   1d,  50d, null, null, positions.get(2));
		TestUtils.assertPosition(-100d, -50d, null, null, positions.get(3));
		ring = rings.get(1);
		positions = ring.getPositions();
		TestUtils.assertPosition(-50d, -25d, null, null, positions.get(0));
		TestUtils.assertPosition( 50d, -25d, null, null, positions.get(1));
		TestUtils.assertPosition( -1d,  25d, null, null, positions.get(2));
		TestUtils.assertPosition(-50d, -25d, null, null, positions.get(3));
	}

	@Test
	public void itShouldDeserializeAMultiPolygonWithMulti() throws Exception {
		GeoJsonObject value = mapper.readValue(MULTIPOLYGON_WITH_MULTI, GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof MultiPolygon);
		MultiPolygon gjMultiPolygon = (MultiPolygon)value;
		mil.nga.sf.Geometry geometry = gjMultiPolygon.getGeometry();
		assertTrue(geometry instanceof mil.nga.sf.MultiPolygon);
		mil.nga.sf.MultiPolygon multiPolygon = (mil.nga.sf.MultiPolygon)geometry;
		List<mil.nga.sf.Polygon> polygons = multiPolygon.getPolygons();
		assertTrue(polygons.size() == 2);
		mil.nga.sf.Polygon polygon = polygons.get(0);
		List<LinearRing> rings = polygon.getRings();
		assertTrue(rings.size() == 1);
		mil.nga.sf.LinearRing ring = rings.get(0);
		List<mil.nga.sf.Position> positions = ring.getPositions();
		TestUtils.assertPosition(-100d, -50d, null, null, positions.get(0));
		TestUtils.assertPosition( 100d, -50d, null, null, positions.get(1));
		TestUtils.assertPosition(   1d,  50d, null, null, positions.get(2));
		TestUtils.assertPosition(-100d, -50d, null, null, positions.get(3));
		polygon = polygons.get(1);
		rings = polygon.getRings();
		assertTrue(rings.size() == 1);
		ring = rings.get(0);
		positions = ring.getPositions();
		TestUtils.assertPosition(-50d, -25d, null, null, positions.get(0));
		TestUtils.assertPosition( 50d, -25d, null, null, positions.get(1));
		TestUtils.assertPosition( -1d,  25d, null, null, positions.get(2));
		TestUtils.assertPosition(-50d, -25d, null, null, positions.get(3));
	}
}
