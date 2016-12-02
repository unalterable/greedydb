//Knows how to interact with streams

package greedy_db;

import java.util.Hashtable;

import org.json.JSONException;

public class GreedyDB {
		
	private Hashtable<String, GreedyJSONStream> streamList;
	
	public GreedyDB(){
		streamList = new Hashtable<String, GreedyJSONStream>();
	}
	
	public void importStream(String streamName, String json_string) throws JSONException {
		findOrCreateStream(streamName).importJSON(json_string);
		return;
	}

	public Object queryStreamLatest(String stream, String path) throws JSONException {
		return findStream(stream).getLatest(path);
	}

	public Object queryStreamOldest(String stream, String path) throws JSONException {
		return findStream(stream).getOldest(path);
	}

	public Object queryStreamTimestamp(String stream, int timestamp, String path) throws JSONException {
		return findStream(stream).searchTimestamp(timestamp, path);
	}

	private GreedyJSONStream findOrCreateStream(String streamName) {
		if (!streamList.containsKey(streamName))
			addStream(streamName);
		return findStream(streamName);
	}
	
	private GreedyJSONStream findStream(String streamName) {
		if (!streamList.containsKey(streamName))
			throw new IllegalArgumentException();
		return streamList.get(streamName);
	}
	
	private void addStream(String streamName) {
		streamList.put(streamName, new GreedyJSONStream());	
	}
}
