package mil.nga.sf.geojson.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mil.nga.sf.Geometry;
import mil.nga.sf.SimpleLinearRing;
import mil.nga.sf.geojson.GeoJsonObject;
import mil.nga.sf.geojson.Polygon;
import mil.nga.sf.geojson.Position;

public class PolygonTest {

	private static String POLYGON = "{\"type\":\"Polygon\",\"coordinates\":[[[100.0,10.0],[101.0,1.0],[101.0,10.0],[100.0,10.0]]]}";
	private static String POLYGON_WITH_ALT = "{\"type\":\"Polygon\",\"coordinates\":[[[100.0,10.0,5.0],[101.0,1.0,10.0],[101.0,10.0,15.0],[100.0,10.0,5.0]]]}";
	private static String POLYGON_WITH_RINGS = "{\"type\":\"Polygon\",\"coordinates\":[[[-100.0,-50.0],[100.0,-50.0],[1.0,50.0],[-100.0,-50.0]],[[-50.0,-25.0],[50.0,-25.0],[-1.0,25.0],[-50.0,-25.0]]]}";

	@Test
	public void itShouldSerializeASFPolygon() throws Exception {
		List<SimpleLinearRing> rings = new ArrayList<SimpleLinearRing>();
		List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(100d, 10d));
		positions.add(new Position(101d, 1d));
		positions.add(new Position(101d, 10d));
		SimpleLinearRing ring = new SimpleLinearRing(positions);
		rings.add(ring);
		mil.nga.sf.Polygon polygon = new mil.nga.sf.SimplePolygon(rings);
		TestUtils.compareAsNodes(polygon, POLYGON);
	}

	@Test
	public void itShouldSerializeASFPolygonWithAltitude() throws Exception {
		List<SimpleLinearRing> rings = new ArrayList<SimpleLinearRing>();
		List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(100d, 10d,  5d));
		positions.add(new Position(101d,  1d, 10d));
		positions.add(new Position(101d, 10d, 15d));
		SimpleLinearRing ring = new SimpleLinearRing(positions);
		rings.add(ring);
		mil.nga.sf.Polygon polygon = new mil.nga.sf.SimplePolygon(rings);
		TestUtils.compareAsNodes(polygon, POLYGON_WITH_ALT);
	}

	@Test
	public void itShouldSerializeASFPolygonWithRings() throws Exception {
		List<SimpleLinearRing> rings = new ArrayList<SimpleLinearRing>();
		List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(-100d, -50d));
		positions.add(new Position( 100d, -50d));
		positions.add(new Position(   1d,  50d));
		SimpleLinearRing ring = new SimpleLinearRing(positions);
		rings.add(ring);
		positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(-50d, -25d));
		positions.add(new Position( 50d, -25d));
		positions.add(new Position( -1d,  25d));
		ring = new SimpleLinearRing(positions);
		rings.add(ring);
		mil.nga.sf.Polygon polygon = new mil.nga.sf.SimplePolygon(rings);
		TestUtils.compareAsNodes(polygon, POLYGON_WITH_RINGS);
	}


	@Test
	public void itShouldDeserializeAPolygon() throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(POLYGON, GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof Polygon);
		Polygon gjPolygon = (Polygon)value;
		Geometry simpleGeometry = gjPolygon.getGeometry();
		assertTrue(simpleGeometry instanceof mil.nga.sf.Polygon);
		mil.nga.sf.SimplePolygon polygon = (mil.nga.sf.SimplePolygon)simpleGeometry;
		List<SimpleLinearRing> rings = polygon.getRings();
		assertTrue(rings.size() == 1);
		mil.nga.sf.SimpleLinearRing ring = rings.get(0);
		List<mil.nga.sf.Position> positions = ring.getPoints();
		TestUtils.assertPosition(100d, 10d, null, null, positions.get(0));
		TestUtils.assertPosition(101d,  1d, null, null, positions.get(1));
		TestUtils.assertPosition(101d, 10d, null, null, positions.get(2));
		TestUtils.assertPosition(100d, 10d, null, null, positions.get(3));
	}

	@Test
	public void itShouldDeserializeAPolygonWithAltitude() throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(POLYGON_WITH_ALT, GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof Polygon);
		Polygon gjPolygon = (Polygon)value;
		Geometry simpleGeometry = gjPolygon.getGeometry();
		assertTrue(simpleGeometry instanceof mil.nga.sf.Polygon);
		mil.nga.sf.SimplePolygon polygon = (mil.nga.sf.SimplePolygon)simpleGeometry;
		List<SimpleLinearRing> rings = polygon.getRings();
		assertTrue(rings.size() == 1);
		mil.nga.sf.SimpleLinearRing ring = rings.get(0);
		List<mil.nga.sf.Position> positions = ring.getPoints();
		TestUtils.assertPosition(100d, 10d,  5d, null, positions.get(0));
		TestUtils.assertPosition(101d,  1d, 10d, null, positions.get(1));
		TestUtils.assertPosition(101d, 10d, 15d, null, positions.get(2));
		TestUtils.assertPosition(100d, 10d,  5d, null, positions.get(3));
	}

	@Test
	public void itShouldDeserializeAPolygonWithRings() throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(POLYGON_WITH_RINGS, GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof Polygon);
		Polygon gjPolygon = (Polygon)value;
		Geometry simpleGeometry = gjPolygon.getGeometry();
		assertTrue(simpleGeometry instanceof mil.nga.sf.Polygon);
		mil.nga.sf.SimplePolygon polygon = (mil.nga.sf.SimplePolygon)simpleGeometry;
		List<SimpleLinearRing> rings = polygon.getRings();
		assertTrue(rings.size() == 2);
		mil.nga.sf.SimpleLinearRing ring = rings.get(0);
		List<mil.nga.sf.Position> positions = ring.getPoints();
		TestUtils.assertPosition(-100d, -50d, null, null, positions.get(0));
		TestUtils.assertPosition( 100d, -50d, null, null, positions.get(1));
		TestUtils.assertPosition(   1d,  50d, null, null, positions.get(2));
		TestUtils.assertPosition(-100d, -50d, null, null, positions.get(3));
		ring = rings.get(1);
		positions = ring.getPoints();
		TestUtils.assertPosition(-50d, -25d, null, null, positions.get(0));
		TestUtils.assertPosition( 50d, -25d, null, null, positions.get(1));
		TestUtils.assertPosition( -1d,  25d, null, null, positions.get(2));
		TestUtils.assertPosition(-50d, -25d, null, null, positions.get(3));
	}
}
