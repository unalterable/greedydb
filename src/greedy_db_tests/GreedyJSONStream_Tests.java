package greedy_db_tests;

import static org.junit.Assert.*;
import org.junit.Test;

import greedy_db.GreedyJSONStream;

public class GreedyJSONStream_Tests {
	
	String 	JSON_STRING1 = "{ "
				+ "\"timestamp\": 1460463641, "
				+ "\"id\": 88837264, "
				+ "\"name\": \"Davide\", "
				+ "\"surname\": \"Palmisano\" }", 
			JSON_STRING2 = "{ "
				+ "\"timestamp\": 1460465692, "
				+ "\"id\": 667732, "
				+ "\"name\": \"Jonh\", "
				+ "\"surname\": \"Foobar\" }",
			JSON_STRING3 = "{ "
				+ "\"timestamp\": 1460462644, "
				+ "\"id\": 1180023, "
				+ "\"name\": \"Davide\", "
				+ "\"surname\": \"Palmisano\" }", 
			JSON_STRING4 = "{ "
				+ "\"timestamp\": 1460442540, "
				+ "\"page\": \"/login.html\", "
				+ "\"origin\":  { "
					+ "\"brand\": \"hcom\", "
					+ "\"pos\": \"UK\" "
					+ "} "
				+ "}";
	
	
	// #constructor()
	@Test
	public void constructor() {
		
		GreedyJSONStream stream = new GreedyJSONStream();
		
		assertEquals("The stream is empty", 0, stream.size());
		
	}
	
	// #importJSON()
	@Test
	public void importMultipleJSONs() throws Exception {
		
		GreedyJSONStream stream = new GreedyJSONStream();

		stream.importJSON(JSON_STRING1);
		stream.importJSON(JSON_STRING2);
		stream.importJSON(JSON_STRING3);

		assertEquals("There are three objects stored in the stream", 3 , stream.size());
		
	}
	
	// #getLatest()
	@Test
	public void getLatestObjInStream() throws Exception {
		
		GreedyJSONStream stream = new GreedyJSONStream();

		stream.importJSON(JSON_STRING1);
		stream.importJSON(JSON_STRING2);

		assertEquals("The getLatest() method works correctly", "Foobar" , stream.getLatest("surname"));
		assertNotEquals("The getLatest() method works correctly", "Palmisano" , stream.getLatest("surname"));
		
	}
	@Test
	public void getLatestObjInStreamAndCheckIsJson() throws Exception {
		
		GreedyJSONStream stream = new GreedyJSONStream();

		stream.importJSON(JSON_STRING4);

		assertEquals("The getLatest() method works correctly", "hcom" , stream.getLatest("origin.brand"));
		
	}
	@Test
	public void getLatestJsonInStreamWhenNoneExists() throws Exception {
		
		GreedyJSONStream stream = new GreedyJSONStream();

		assertEquals("The getLatest() returns null if there is no stream", null , stream.getLatest(""));
		
	}
	
	// #getOldest()
	@Test
	public void getOldestObjInStream() throws Exception {
		
		GreedyJSONStream stream = new GreedyJSONStream();

		stream.importJSON(JSON_STRING1);
		stream.importJSON(JSON_STRING2);

		assertEquals("The getOldest() method works correctly", "Palmisano" , stream.getOldest("surname"));
		assertNotEquals("The getOldest() method works correctly", "Foobar" , stream.getOldest("surname"));
		
	}
	@Test
	public void getOldestObjInStreamAndCheckIsJson() throws Exception {
		
		GreedyJSONStream stream = new GreedyJSONStream();

		stream.importJSON(JSON_STRING4);

		assertEquals("The getOldest() method works correctly", "hcom" , stream.getOldest("origin.brand"));
		
	}
	@Test
	public void getOldestJsonInStreamWhenNoneExists() throws Exception {
		
		GreedyJSONStream stream = new GreedyJSONStream();

		assertEquals("The getLatest() returns null if there is no stream", null , stream.getLatest(""));
		
	}
	
	// #searchTimestamp()
	@Test
	public void searchTimestamp() throws Exception {
		
		GreedyJSONStream stream = new GreedyJSONStream();

		stream.importJSON(JSON_STRING1);
		stream.importJSON(JSON_STRING2);
		stream.importJSON(JSON_STRING3);

		assertEquals("The searchTimestamp() method works correctly", 88837264 , stream.searchTimestamp(1460463641, "id") );
		assertEquals("The searchTimestamp() method works correctly", "Foobar" , stream.searchTimestamp(1460465692, "surname") );
		assertEquals("The searchTimestamp() method works correctly", 1180023 , stream.searchTimestamp(1460462644, "id") );
		
	}
	
	@Test
	public void searchTimestampInStreamWhenNoneExists() throws Exception {
		
		GreedyJSONStream stream = new GreedyJSONStream();

		assertEquals("The searchTimestamp() returns null if there is no stream", null , stream.searchTimestamp(1460463641, ""));
		
	}
}
