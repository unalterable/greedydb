package greedy_db_tests;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.junit.Test;

import greedy_db.GreedyJSON;

public class GreedyJSON_Tests {
	
	String JSON_STRING = "{ "
			+ "\"timestamp\": 1460442540, "
			+ "\"page\": \"/login.html\", "
			+ "\"origin\":  { "
				+ "\"brand\": \"hcom\", "
				+ "\"pos\": \"UK\" "
				+ "} "
			+ "}";

	
	// #timestamp
	@Test
	public void timestamp_Test() throws JSONException {
		
		GreedyJSON json = new GreedyJSON(JSON_STRING);

		assertEquals(1460442540, json.timestamp());
		
	}
	
	// #get
	@Test
	public void get_with_JSON_path() throws JSONException {
		
		GreedyJSON json = new GreedyJSON(JSON_STRING);

		assertEquals("/login.html", json.get("page"));
		assertEquals("hcom", json.get("origin.brand"));
		
	}
	
}
