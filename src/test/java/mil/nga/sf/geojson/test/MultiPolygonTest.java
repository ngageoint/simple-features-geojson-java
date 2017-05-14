package mil.nga.sf.geojson.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mil.nga.sf.Geometry;
import mil.nga.sf.SimpleLinearRing;
import mil.nga.sf.geojson.GeoJsonObject;
import mil.nga.sf.geojson.MultiPolygon;
import mil.nga.sf.geojson.Position;

public class MultiPolygonTest {

	private static String MULTIPOLYGON = "{\"type\":\"MultiPolygon\",\"coordinates\":[[[[100.0,10.0],[101.0,1.0],[101.0,10.0],[100.0,10.0]]]]}";
	private static String MULTIPOLYGON_WITH_ALT = "{\"type\":\"MultiPolygon\",\"coordinates\":[[[[100.0,10.0,5.0],[101.0,1.0,10.0],[101.0,10.0,15.0],[100.0,10.0,5.0]]]]}";
	private static String MULTIPOLYGON_WITH_RINGS = "{\"type\":\"MultiPolygon\",\"coordinates\":[[[[-100.0,-50.0],[100.0,-50.0],[1.0,50.0],[-100.0,-50.0]],[[-50.0,-25.0],[50.0,-25.0],[-1.0,25.0],[-50.0,-25.0]]]]}";
	private static String MULTIPOLYGON_WITH_MULTI = "{\"type\":\"MultiPolygon\",\"coordinates\":[[[[-100.0,-50.0],[100.0,-50.0],[1.0,50.0],[-100.0,-50.0]]],[[[-50.0,-25.0],[50.0,-25.0],[-1.0,25.0],[-50.0,-25.0]]]]}";

	@Test
	public void itShouldSerializeASFMultiPolygon() throws Exception {
		List<mil.nga.sf.SimplePolygon> polygons = new ArrayList<mil.nga.sf.SimplePolygon>();
		List<SimpleLinearRing> rings = new ArrayList<SimpleLinearRing>();
		List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(100d, 10d));
		positions.add(new Position(101d, 1d));
		positions.add(new Position(101d, 10d));
		SimpleLinearRing ring = new SimpleLinearRing(positions);
		rings.add(ring);
		mil.nga.sf.SimplePolygon polygon = new mil.nga.sf.SimplePolygon(rings);
		polygons.add(polygon);
		mil.nga.sf.SimpleMultiPolygon multiPolygon = new mil.nga.sf.SimpleMultiPolygon(polygons);
		TestUtils.compareAsNodes(multiPolygon, MULTIPOLYGON);
	}

	@Test
	public void itShouldSerializeASFMultiPolygonWithAltitude() throws Exception {
		List<mil.nga.sf.SimplePolygon> polygons = new ArrayList<mil.nga.sf.SimplePolygon>();
		List<SimpleLinearRing> rings = new ArrayList<SimpleLinearRing>();
		List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(100d, 10d,  5d));
		positions.add(new Position(101d,  1d, 10d));
		positions.add(new Position(101d, 10d, 15d));
		SimpleLinearRing ring = new SimpleLinearRing(positions);
		rings.add(ring);
		mil.nga.sf.SimplePolygon polygon = new mil.nga.sf.SimplePolygon(rings);
		polygons.add(polygon);
		mil.nga.sf.SimpleMultiPolygon multiPolygon = new mil.nga.sf.SimpleMultiPolygon(polygons);
		TestUtils.compareAsNodes(multiPolygon, MULTIPOLYGON_WITH_ALT);
	}

	@Test
	public void itShouldSerializeASFMultiPolygonWithRings() throws Exception {
		TestUtils.compareAsNodes(TestUtils.getMultiPolygonWithRings(), MULTIPOLYGON_WITH_RINGS);
	}

	@Test
	public void itShouldSerializeASFMultiPolygonWithMulti() throws Exception {
		List<mil.nga.sf.SimplePolygon> polygons = new ArrayList<mil.nga.sf.SimplePolygon>();
		// Polygon 1
		List<SimpleLinearRing> rings = new ArrayList<SimpleLinearRing>();
		List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(-100d, -50d));
		positions.add(new Position( 100d, -50d));
		positions.add(new Position(   1d,  50d));
		SimpleLinearRing ring = new SimpleLinearRing(positions);
		rings.add(ring);
		mil.nga.sf.SimplePolygon polygon = new mil.nga.sf.SimplePolygon(rings);
		polygons.add(polygon);

		// P2R1
		rings = new ArrayList<SimpleLinearRing>();
		positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(-50d, -25d));
		positions.add(new Position( 50d, -25d));
		positions.add(new Position( -1d,  25d));
		ring = new SimpleLinearRing(positions);
		rings.add(ring);
		polygon = new mil.nga.sf.SimplePolygon(rings);
		polygons.add(polygon);
		
		mil.nga.sf.SimpleMultiPolygon multiPolygon = new mil.nga.sf.SimpleMultiPolygon(polygons);
		TestUtils.compareAsNodes(multiPolygon, MULTIPOLYGON_WITH_MULTI);
	}

	@Test
	public void itShouldDeserializeAMultiPolygon() throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(MULTIPOLYGON, GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof MultiPolygon);
		MultiPolygon gjMultiPolygon = (MultiPolygon)value;
		Geometry simpleGeometry = gjMultiPolygon.getGeometry();
		assertTrue(simpleGeometry instanceof mil.nga.sf.SimpleMultiPolygon);
		mil.nga.sf.SimpleMultiPolygon multiPolygon = (mil.nga.sf.SimpleMultiPolygon)simpleGeometry;
		List<mil.nga.sf.SimplePolygon> polygons = multiPolygon.getPolygons();
		assertTrue(polygons.size() == 1);
		mil.nga.sf.SimplePolygon polygon = polygons.get(0);
		List<SimpleLinearRing> rings = polygon.getRings();
		assertTrue(rings.size() == 1);
		mil.nga.sf.SimpleLinearRing ring = rings.get(0);
		List<mil.nga.sf.Position> positions = ring.getPositions();
		TestUtils.assertPosition(100d, 10d, null, null, positions.get(0));
		TestUtils.assertPosition(101d,  1d, null, null, positions.get(1));
		TestUtils.assertPosition(101d, 10d, null, null, positions.get(2));
		TestUtils.assertPosition(100d, 10d, null, null, positions.get(3));
	}

	@Test
	public void itShouldDeserializeAMultiPolygonWithAltitude() throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(MULTIPOLYGON_WITH_ALT, GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof MultiPolygon);
		MultiPolygon gjMultiPolygon = (MultiPolygon)value;
		Geometry simpleGeometry = gjMultiPolygon.getGeometry();
		assertTrue(simpleGeometry instanceof mil.nga.sf.SimpleMultiPolygon);
		mil.nga.sf.SimpleMultiPolygon multiPolygon = (mil.nga.sf.SimpleMultiPolygon)simpleGeometry;
		List<mil.nga.sf.SimplePolygon> polygons = multiPolygon.getPolygons();
		assertTrue(polygons.size() == 1);
		mil.nga.sf.SimplePolygon polygon = polygons.get(0);
		List<SimpleLinearRing> rings = polygon.getRings();
		assertTrue(rings.size() == 1);
		mil.nga.sf.SimpleLinearRing ring = rings.get(0);
		List<mil.nga.sf.Position> positions = ring.getPositions();
		TestUtils.assertPosition(100d, 10d,  5d, null, positions.get(0));
		TestUtils.assertPosition(101d,  1d, 10d, null, positions.get(1));
		TestUtils.assertPosition(101d, 10d, 15d, null, positions.get(2));
		TestUtils.assertPosition(100d, 10d,  5d, null, positions.get(3));
	}

	@Test
	public void itShouldDeserializeAMultiPolygonWithRings() throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(MULTIPOLYGON_WITH_RINGS, GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof MultiPolygon);
		MultiPolygon gjMultiPolygon = (MultiPolygon)value;
		Geometry simpleGeometry = gjMultiPolygon.getGeometry();
		assertTrue(simpleGeometry instanceof mil.nga.sf.SimpleMultiPolygon);
		mil.nga.sf.SimpleMultiPolygon multiPolygon = (mil.nga.sf.SimpleMultiPolygon)simpleGeometry;
		List<mil.nga.sf.SimplePolygon> polygons = multiPolygon.getPolygons();
		assertTrue(polygons.size() == 1);
		mil.nga.sf.SimplePolygon polygon = polygons.get(0);
		List<SimpleLinearRing> rings = polygon.getRings();
		assertTrue(rings.size() == 2);
		mil.nga.sf.SimpleLinearRing ring = rings.get(0);
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
		GeoJsonObject value = TestUtils.getMapper().readValue(MULTIPOLYGON_WITH_MULTI, GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof MultiPolygon);
		MultiPolygon gjMultiPolygon = (MultiPolygon)value;
		Geometry simpleGeometry = gjMultiPolygon.getGeometry();
		assertTrue(simpleGeometry instanceof mil.nga.sf.SimpleMultiPolygon);
		mil.nga.sf.SimpleMultiPolygon multiPolygon = (mil.nga.sf.SimpleMultiPolygon)simpleGeometry;
		List<mil.nga.sf.SimplePolygon> polygons = multiPolygon.getPolygons();
		assertTrue(polygons.size() == 2);
		mil.nga.sf.SimplePolygon polygon = polygons.get(0);
		List<SimpleLinearRing> rings = polygon.getRings();
		assertTrue(rings.size() == 1);
		mil.nga.sf.SimpleLinearRing ring = rings.get(0);
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
