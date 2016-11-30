package greedy_db_tests;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.junit.Test;

import greedy_db.Stream;

public class Stream_Tests {
	
	@Test
	public void items() {
		Stream stream = new Stream();
		
		
		assertEquals("The stream is empty", 0, stream.all().size());
	}
	
	@Test
	public void importJSON() throws JSONException {
		Stream employers = new Stream();
		String json = "{ “timestamp”: 1460463641, “id”: 88837264, “name”: “Davide”, “surname”: “Palmisano“ }";
		
		employers.importJSON(json);
	
		assertEquals("There is one object stored in the stream", 1, employers.all().size());
	}

	@Test
	public void importMultipleJSONs() throws JSONException {
		Stream employers = new Stream();

		employers.importJSON("{ “timestamp”: 1460463641, “id”: 88837264, “name”: “Davide”, “surname”: “Palmisano“ }");
		employers.importJSON("{ “timestamp”: 1460465692, “id”: 667732, “name”: “Jonh”, “surname”: “Foobar“ }");
		employers.importJSON("{ “timestamp”: 1460462644, “id”: 1180023, “name”: “Davide”, “surname”: “Palmisano“ }");

		assertEquals("There are three objects stored in the stream", 3 , employers.all().size());
	}
	
	@Test
	public void getLatestObject() throws JSONException {
		Stream employers = new Stream();

		employers.importJSON("{ “timestamp”: 1460463641, “id”: 88837264, “name”: “Davide”, “surname”: “Palmisano“ }");
		employers.importJSON("{ “timestamp”: 1460465692, “id”: 667732, “name”: “Jonh”, “surname”: “Foobar“ }");

		assertEquals("There are three objects stored in the stream", null , employers.latest());
	}
}
