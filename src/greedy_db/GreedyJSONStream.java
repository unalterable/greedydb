//knows how to store GreedyJSONs and return their fields

package greedy_db;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;


public class GreedyJSONStream {

List<GreedyJSON> stream;
	
	public GreedyJSONStream(){
		stream = new ArrayList<GreedyJSON>();
	}
	
	public int size(){
		return stream.size();
	}

	public void importJSON(String json) throws JSONException {
		stream.add(newJSON(json));
		return;
	}
	
	public Object getOldest(String path) throws JSONException {
		return  size() > 0 ? stream.get(0).get(path) : null;
	}
	
	public Object getLatest(String path) throws JSONException {
		return  size() > 0 ? stream.get( size()-1 ).get(path) : null;
	}

	public Object searchTimestamp(int timestamp, String path) throws JSONException {
		for(GreedyJSON json : stream){
			if(json.timestamp() == timestamp)
				return json.get(path); 
		}
		return null;
	}

	private GreedyJSON newJSON(String json) throws JSONException {
		return new GreedyJSON(json);
	}
	
}
