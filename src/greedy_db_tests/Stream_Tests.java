package greedy_db_tests;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.junit.Test;

import greedy_db.Stream;

public class Stream_Tests {

	// #constructor()
	@Test
	public void items() {
		Stream stream = new Stream();
		
		
		assertEquals("The stream is empty", 0, stream.all().size());
	}
	
	// #importJSON()
	@Test
	public void importMultipleJSONs() throws JSONException {
		Stream employers = new Stream();

		employers.importJSON("{ \"timestamp\": 1460463641, \"id\": 88837264, \"name\": \"Davide\", \"surname\": \"Palmisano\" }");
		employers.importJSON("{ \"timestamp\": 1460465692, \"id\": 667732, \"name\": \"Jonh\", \"surname\": \"Foobar\" }");
		employers.importJSON("{ \"timestamp\": 1460462644, \"id\": 1180023, \"name\": \"Davide\", \"surname\": \"Palmisano\" }");

		assertEquals("There are three objects stored in the stream", 3 , employers.all().size());
		assertEquals("They parsed as JSON", 1460463641 , employers.all().get(0).get("timestamp") );
	}
	
	// #getLatest()
	@Test
	public void getLatestJsonInStream() throws JSONException {
		Stream employers = new Stream();

		employers.importJSON("{ \"timestamp\": 1460463641, \"id\": 88837264, \"name\": \"Davide\", \"surname\": \"Palmisano\" }");
		employers.importJSON("{ \"timestamp\": 1460465692, \"id\": 667732, \"name\": \"Jonh\", \"surname\": \"Foobar\" }");


		assertEquals("The getLatest() method works correctly", "Foobar" , employers.getLatest().get("surname"));
	}
	
	@Test
	public void getLatestJsonInStreamWhenNoneExists() throws JSONException {
		Stream employers = new Stream();

		assertEquals("The getLatest() works even if there is no stream", null , employers.getLatest());
	}
	
	// #getTimestamp()
	@Test
	public void getTimestamp() throws JSONException {
		Stream employers = new Stream();

		employers.importJSON("{ \"timestamp\": 1460463641, \"id\": 88837264, \"name\": \"Davide\", \"surname\": \"Palmisano\" }");
		employers.importJSON("{ \"timestamp\": 1460465692, \"id\": 667732, \"name\": \"Jonh\", \"surname\": \"Foobar\" }");
		employers.importJSON("{ \"timestamp\": 1460462644, \"id\": 1180023, \"name\": \"Davide\", \"surname\": \"Palmisano\" }");

		assertEquals("The getTimestamp() method works correctly", 88837264 , employers.getTimestamp(1460463641).get("id") );
		assertEquals("The getTimestamp() method works correctly", "Foobar" , employers.getTimestamp(1460465692).get("surname") );
		assertEquals("The getTimestamp() method works correctly", 1180023 , employers.getTimestamp(1460462644).get("id") );
	
	
}
