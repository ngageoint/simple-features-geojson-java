package mil.nga.sf;

import java.util.List;

import mil.nga.sf.util.PositionUtils;

/**
 * The point of LinearRing is to enforce the rule
 * that the begin position must equal the end position
 * 
 * @author JeffYutzler
 *
 */
public class LinearRing extends LineString {

	/**
	 * Main constructor
	 * 
	 * @param hasZ
	 * @param hasM
	 */
	public LinearRing(boolean hasZ, boolean hasM) {
		super(hasZ, hasM);
	}

	/**
	 * Main constructor
	 * 
	 * @param ls the curve providing the positions
	 */
	public LinearRing(Curve ls) {
		super(ls.hasZ(), ls.hasM());
		setPositions(ls.getPositions());
	}

	/** 
	 * Constructor
	 * @param positions a list of positions
	 */
	public LinearRing(List<Position> positions) {
		super(PositionUtils.hasZ(positions), PositionUtils.hasM(positions));
		setPositions(positions);
	}

	@Override
	public void setPositions(List<Position> positions){
		super.setPositions(positions);
		if (!positions.isEmpty()){
			if (!positions.get(0).equals(positions.get(positions.size() - 1))){
				addPosition(positions.get(0));
			}
		}
	}
	
	@Override
	public void addPosition(Position position) {
		List<Position> positions = getPositions();
		positions.add(positions.size(), position);
	}
}
