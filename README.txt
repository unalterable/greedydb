Greedy DB is a set of Java Units designed to store, search and retrieve JSON.

The data did not need to be held for long, and therefore it is stored in state.

Problems setting up Eclipse Java Servlets were problematic on the only machine available, therefore only the units have been provided.

Units have been tested with JUnit - laid out verbosely in 4 phases according to the ThoughtBot guidance.


Documentation:


GreedyDB
This class creates, stores and searches GreedyJSONStream objects

#constructor
To use greedydb you must instantiate the GreedyDB class
e.g. GreedyDB gdb = new GreedyDB();

#importStream(String streamName, String json_string)
	To import a JSON into a stream you pass this method the streamName of the stream you wish to use and the JSON as a string.

#queryStreamLatest(String stream, String path)
	To retrieve a field from the latest JSON to enter a stream you pass this method the stream name and the JSON path (dot notation can be  used e.g. "model.id")
	Returns: requested JSON field (as Object) or Null if path is invalid.

#queryStreamOldest(String stream, String path)
	To retrieve a field from the oldest JSON in a stream you pass this method the stream name and the JSON path (dot notation can be used e.g. "model.id")
	Returns: requested JSON field (as Object) or Null if path is invalid.

#queryStreamTimestamp(String stream, int timestamp, String path)
	To retrieve a field from a specific JSON in a stream you pass this method the stream name, the timestamp of the requested JSON as an integer, and the JSON path (dot notation can be used e.g. "model.id")
	Returns: requested JSON field (as Object) or Null if path is invalid.
	

GreedyJSONStream
This class creates, stores and queries GreedyJSON objects

#size()
	Returns: the amount of JSONs stored in the stream as an Integer. 

#importJSON(String json)
	To import a new JSON pass it as a string to this method.
	
#getOldest(String path)
	Returns: the requested JSON field from the oldest JSON in this stream, or Null if none found.
	
#getLatest(String path)
	Returns: the requested JSON field from the latest JSON in this stream, or Null if none found.

#searchTimestamp(int timestamp, String path) 
	Returns: the requested JSON field from JSON with the passed timestamp, or Null if none found.


GreedyJSON
This class wraps the external JSON library. It can parse strings into JSON and query JSON with dot notation.

#constructor
	To create a GreedyJSON pass the constructor a string representation of the JSON you wish to store.

#get(String path)
	Returns: the JSON field found at the path, or Null if none found.
	
#timestamp()
	Returns: the timestamp on the GreedyJSON, or Null if none found.


