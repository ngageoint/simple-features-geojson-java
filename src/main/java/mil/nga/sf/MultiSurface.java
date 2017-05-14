package mil.nga.sf;

/**
 * A restricted form of GeometryCollection where each Geometry in the collection
 * must be of type Surface.
 * 
 * @author osbornb
 */
public interface MultiSurface extends GeometryCollection<Surface> {

}
