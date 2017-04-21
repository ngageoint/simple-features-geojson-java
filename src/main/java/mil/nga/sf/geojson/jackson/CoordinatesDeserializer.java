package mil.nga.sf.geojson.jackson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import mil.nga.sf.geojson.Position;

public class CoordinatesDeserializer extends JsonDeserializer<Position> {

	@Override
	public Position deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException,
			JsonProcessingException {
		if (jp.isExpectedStartArrayToken()) {
			return deserializeArray(jp, ctxt);
		}
		throw ctxt.mappingException(Position.class);
	}

	protected Position deserializeArray(JsonParser jp, DeserializationContext ctxt) throws IOException,
			JsonProcessingException {
		
		double x = extractDouble(jp, ctxt, false);
		double y = extractDouble(jp, ctxt, false);
		double z = extractDouble(jp, ctxt, true);

		List<Double> additionalElementsList = new ArrayList<Double>();
		while (jp.hasCurrentToken() && jp.getCurrentToken() != JsonToken.END_ARRAY) {
			double element = extractDouble(jp, ctxt, true);
			if (!Double.isNaN(element)) {
				additionalElementsList.add(element);
			}
		}

		double[] additionalElements = new double[additionalElementsList.size()];
		for(int i = 0; i < additionalElements.length; i++) {
			additionalElements[i] = additionalElementsList.get(i);
		}

		return new Position(x, y, z, additionalElements);
	}

	private double extractDouble(JsonParser jp, DeserializationContext ctxt, boolean optional)
			throws JsonParseException, IOException {
		JsonToken token = jp.nextToken();
		if (token == null) {
			if (optional)
				return Double.NaN;
			else
				throw ctxt.mappingException("Unexpected end-of-input when binding data into Coordinates");
		}
		else {
			switch (token) {
				case END_ARRAY:
					if (optional)
						return Double.NaN;
					else
						throw ctxt.mappingException("Unexpected end-of-input when binding data into Coordinates");
				case VALUE_NUMBER_FLOAT:
					return jp.getDoubleValue();
				case VALUE_NUMBER_INT:
					return jp.getLongValue();
				case VALUE_STRING:
					return jp.getValueAsDouble();
				default:
					throw ctxt.mappingException("Unexpected token (" + token.name()
							+ ") when binding data into LngLatAlt");
			}
		}
	}
}
