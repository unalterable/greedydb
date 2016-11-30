package greedy_db;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class Stream {

List<JSONObject> stream;
	
	public Stream(){
		stream = new ArrayList<JSONObject>();
	}
	
	public List<JSONObject> all(){
		return stream;
	}

	public void importJSON(String json) throws JSONException {
		stream.add(newJSON(json));
		return;
	}

	private JSONObject newJSON(String json) throws JSONException {
		return new JSONObject(json);
	}
	
	

}
