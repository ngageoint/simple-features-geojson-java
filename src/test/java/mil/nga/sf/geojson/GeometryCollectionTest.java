package mil.nga.sf.geojson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import mil.nga.sf.Geometry;
import mil.nga.sf.LineString;

public class GeometryCollectionTest {

	private static String GEOMETRYCOLLECTION = "{\"type\":\"GeometryCollection\",\"geometries\":[{\"type\":\"Point\",\"coordinates\":[61.34765625,48.63290858589535]},{\"type\":\"LineString\",\"coordinates\":[[100.0,10.0],[101.0,1.0]]}]}";
	private static String GEOMETRYCOLLECTION_WITH_ALT = "{\"type\":\"GeometryCollection\",\"geometries\":[{\"type\":\"Point\",\"coordinates\":[61.34765625,48.63290858589535, 12.7843]},{\"type\":\"LineString\",\"coordinates\":[[100.0,10.0,5.0],[101.0,1.0,10.0]]}]}";

	@Test
	public void itShouldSerializeSFGeometryCollection() throws Exception {
		Geometry geometryCollection = getTestGeometry();
		TestUtils.compareAsNodes(geometryCollection, GEOMETRYCOLLECTION);
	}

	@Test
	public void itShouldSerializeSFGeometryCollectionWithAltitude()
			throws Exception {
		mil.nga.sf.GeometryCollection<mil.nga.sf.Geometry> geometryCollection = new mil.nga.sf.GeometryCollection<>();
		mil.nga.sf.Point point = new mil.nga.sf.Point(61.34765625,
				48.63290858589535, 12.7843);
		geometryCollection.addGeometry(point);
		LineString lineString = new LineString();
		lineString.addPoint(new mil.nga.sf.Point(100.0, 10.0, 5.0));
		lineString.addPoint(new mil.nga.sf.Point(101.0, 1.0, 10.0));
		geometryCollection.addGeometry(lineString);
		TestUtils.compareAsNodes(geometryCollection,
				GEOMETRYCOLLECTION_WITH_ALT);
	}

	@Test
	public void itShouldDeserializeGeometryCollection() throws Exception {
		GeoJsonObject value = TestUtils.getMapper()
				.readValue(GEOMETRYCOLLECTION, GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof GeometryCollection);
		GeometryCollection gjGeometryCollection = (GeometryCollection) value;
		mil.nga.sf.Geometry geometry = gjGeometryCollection.getGeometry();
		assertTrue(geometry instanceof mil.nga.sf.GeometryCollection);
		@SuppressWarnings("unchecked")
		mil.nga.sf.GeometryCollection<Geometry> geometryCollection = (mil.nga.sf.GeometryCollection<Geometry>) geometry;
		List<mil.nga.sf.Geometry> geometries = geometryCollection
				.getGeometries();
		assertTrue(geometries.size() == 2);
		mil.nga.sf.Geometry geometry1 = geometries.get(0);
		assertTrue(geometry1 instanceof mil.nga.sf.Point);
		mil.nga.sf.Point point = (mil.nga.sf.Point) geometry1;
		assertEquals(new mil.nga.sf.Point(61.34765625, 48.63290858589535),
				point);
		mil.nga.sf.Geometry geometry2 = geometries.get(1);
		assertTrue(geometry2 instanceof mil.nga.sf.LineString);
		mil.nga.sf.LineString lineString = (mil.nga.sf.LineString) geometry2;
		assertEquals(new mil.nga.sf.Point(100.0, 10.0), lineString.getPoint(0));
		assertEquals(new mil.nga.sf.Point(101.0, 1.0), lineString.getPoint(1));
	}

	@Test
	public void itShouldDeserializeGeometryCollectionWithAltitude()
			throws Exception {
		GeoJsonObject value = TestUtils.getMapper()
				.readValue(GEOMETRYCOLLECTION_WITH_ALT, GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof GeometryCollection);
		GeometryCollection gjGeometryCollection = (GeometryCollection) value;
		mil.nga.sf.Geometry geometry = gjGeometryCollection.getGeometry();
		assertTrue(geometry instanceof mil.nga.sf.GeometryCollection);
		@SuppressWarnings("unchecked")
		mil.nga.sf.GeometryCollection<Geometry> geometryCollection = (mil.nga.sf.GeometryCollection<Geometry>) geometry;
		List<mil.nga.sf.Geometry> geometries = geometryCollection
				.getGeometries();
		assertTrue(geometries.size() == 2);
		mil.nga.sf.Geometry geometry1 = geometries.get(0);
		assertTrue(geometry1 instanceof mil.nga.sf.Point);
		mil.nga.sf.Point point = (mil.nga.sf.Point) geometry1;
		assertEquals(
				new mil.nga.sf.Point(61.34765625, 48.63290858589535, 12.7843),
				point);
		mil.nga.sf.Geometry geometry2 = geometries.get(1);
		assertTrue(geometry2 instanceof mil.nga.sf.LineString);
		mil.nga.sf.LineString lineString = (mil.nga.sf.LineString) geometry2;
		assertEquals(new mil.nga.sf.Point(100.0, 10.0, 5.0),
				lineString.getPoint(0));
		assertEquals(new mil.nga.sf.Point(101.0, 1.0, 10.0),
				lineString.getPoint(1));
	}

	@Test
	public void toGeometry() {
		TestUtils.toGeometry(getTestGeometry());
	}

	@Test
	public void toMap() {
		TestUtils.toMap(getTestGeometry());
	}

	@Test
	public void toStringValue() {
		TestUtils.toStringValue(getTestGeometry());
	}

	private mil.nga.sf.Geometry getTestGeometry() {

		mil.nga.sf.GeometryCollection<mil.nga.sf.Geometry> geometryCollection = new mil.nga.sf.GeometryCollection<>();
		mil.nga.sf.Point point = new mil.nga.sf.Point(61.34765625,
				48.63290858589535);
		geometryCollection.addGeometry(point);
		LineString lineString = new LineString();
		lineString.addPoint(new mil.nga.sf.Point(100.0, 10.0));
		lineString.addPoint(new mil.nga.sf.Point(101.0, 1.0));
		geometryCollection.addGeometry(lineString);

		return geometryCollection;
	}

}
