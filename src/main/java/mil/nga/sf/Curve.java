package mil.nga.sf;

import java.util.ArrayList;
import java.util.List;

public abstract class Curve extends Geometry {

	/**
	 * List of points
	 */
	private List<Point> points = new ArrayList<Point>();

	/**
	 * Constructor for overrides
	 * 
	 * @param type
	 *            geometry type
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	protected Curve(GeometryType type, boolean hasZ, boolean hasM) {
		super(type, hasZ, hasM);
	}

	/**
	 * Get the points
	 * 
	 * @return points
	 */
	public List<Point> getPoints(){
		return points;
	}

	/**
	 * Set the points
	 * 
	 * @param points
	 *            points
	 */
	public void setPoints(List<Point> points){
		this.points.clear();
		if(points != null){
			this.points.addAll(points);
		}
	}

	/**
	 * Add a point
	 * 
	 * @param point
	 *            point
	 */
	public void addPoint(Point point){
		points.add(point);
	}

	/**
	 * Get the number of points
	 * 
	 * @return number of points
	 */
	public int numPoints(){
		return points.size();
	}

	/**
	 * 
	 * @return true: is a (closed) ring
	 */
	public boolean isRing(){
		if (isEmpty()){
			return false;
		} else {
			return points.get(0).equals(points.get(points.size() - 1));
		}
	}

	@Override
	public boolean isEmpty() {
		return points.isEmpty();
	}
}