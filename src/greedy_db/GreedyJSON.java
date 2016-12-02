// knows how to parse and Query a JSON object

package greedy_db;

import org.json.JSONException;
import org.json.JSONObject;

public class GreedyJSON {
	JSONObject json;
	
	public GreedyJSON(String jsonString) throws JSONException {
		json = new JSONObject(jsonString); 
	}
	
	public int timestamp() throws JSONException {
		return json.getInt("timestamp");
	}

	public Object get(String path) throws JSONException {
		return QueryJsonPath(path);
	}

	private Object QueryJsonPath(String path) throws JSONException {
		String[] pathArr = path.split("\\.");
		JSONObject subJson = json;
		int last = pathArr.length-1;
		for(int i = 0; i < last; i++){
			subJson = subJson.getJSONObject(pathArr[i]);
		}
		return subJson.get(pathArr[last]);
	}

}
