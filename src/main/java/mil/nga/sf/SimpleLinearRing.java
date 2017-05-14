package mil.nga.sf;

import java.util.List;

import mil.nga.sf.util.PositionUtils;
import mil.nga.sf.util.SFException;

/**
 * The point of LinearRing is to enforce the rule
 * that the begin position must equal the end position
 * 
 * @author JeffYutzler
 *
 */
public class SimpleLinearRing extends AbstractCurve implements LinearRing {

	/**
	 * Main constructor
	 * 
	 * @param hasZ
	 * @param hasM
	 */
	public SimpleLinearRing(boolean hasZ, boolean hasM) {
		super(GeometryType.CURVE, hasZ, hasM);
	}

	/**
	 * Main constructor
	 * 
	 * @param ls the curve providing the positions
	 */
	public SimpleLinearRing(AbstractCurve ls) {
		super(GeometryType.CURVE, ls.hasZ(), ls.hasM());
		setPositions(ls.getPositions());
	}

	/** 
	 * Constructor
	 * @param positions a list of positions
	 */
	public SimpleLinearRing(List<Position> positions) {
		super(GeometryType.CURVE, PositionUtils.hasZ(positions), PositionUtils.hasM(positions));
		setPositions(positions);
	}

	@Override
	public void setPositions(List<Position> positions){
		super.setPositions(positions);
		if ((positions == null) || (positions.size() < 3)) {
			throw new SFException ("A linear ring must have at least three positions.");
		}
		if (!isRing()){
			addPosition(positions.get(0));
		}
	}
	
	@Override
	public void addPosition(Position position) {
		List<Position> positions = getPositions();
		positions.add(positions.size(), position);
	}
}
