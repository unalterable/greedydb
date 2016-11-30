//Knows how to handle stream interactions.

package greedy_db;

import java.util.Hashtable;

public class Streams {
	Hashtable<String, Stream> streamList;
	
	public Streams(){
		streamList = new Hashtable<String, Stream>();
	}

	public void newItem(String streamName, String json) {
//		Stream stream;
//		if(streamList.containsKey(streamName)){
//			stream = streamList.get(streamName).addJSON(json);
//		} else {
//			stream = (new Stream()).addJSON(json);
//		}
	}

	public Stream get(String streamName) {
		return streamList.get(streamName);
	}

}
