package greedy_db_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import greedy_db.GreedyDB;


public class GreedyDB_Tests {
	
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
	
	// #queryStreamLatest
	@Test
	public void queryStreamLatest_Test() throws Exception {
		
		GreedyDB db = new GreedyDB();
		db.importStream("employers", JSON_STRING1);
		db.importStream("employers", JSON_STRING2);
		db.importStream("employers", JSON_STRING3);
		db.importStream("clicks", JSON_STRING4);

		assertEquals(1180023, db.queryStreamLatest("employers", "id"));
		assertEquals("hcom", db.queryStreamLatest("clicks", "origin.brand"));
		
	}

	// #queryStreamOldest
	@Test
	public void queryStreamOldest_Test() throws Exception {
		
		GreedyDB db = new GreedyDB();
		db.importStream("employers", JSON_STRING1);
		db.importStream("employers", JSON_STRING2);
		db.importStream("employers", JSON_STRING3);
		db.importStream("clicks", JSON_STRING4);

		assertEquals(88837264, db.queryStreamOldest("employers", "id"));
		assertEquals("UK", db.queryStreamOldest("clicks", "origin.pos"));
		
	}
	
	// #queryStreamTimestamp
	@Test
	public void queryStreamTimestamp_Test() throws Exception {
		
		GreedyDB db = new GreedyDB();
		db.importStream("employers", JSON_STRING1);
		db.importStream("employers", JSON_STRING2);
		db.importStream("employers", JSON_STRING3);
		db.importStream("clicks", JSON_STRING4);

		assertEquals(667732, db.queryStreamTimestamp("employers", 1460465692, "id"));
		assertEquals("UK", db.queryStreamTimestamp("clicks", 1460442540, "origin.pos"));
		
	}
	
	//Errors
	@Test
	public void badQueryJSONFieldName_Test() throws Exception {
		
		GreedyDB db = new GreedyDB();
		db.importStream("employers", JSON_STRING1);
		db.importStream("clicks", JSON_STRING4);

		assertEquals(null, db.queryStreamTimestamp("clicks", 1460465692, "BAD_FIELD_NAME"));
		
	}
	@Test
	public void badQueryTimeStamp_Test() throws Exception {
		
		GreedyDB db = new GreedyDB();
		db.importStream("employers", JSON_STRING1);
		db.importStream("clicks", JSON_STRING4);

		assertEquals(null, db.queryStreamTimestamp("clicks", 0, "id"));
		
	}
	@Test(expected=IllegalArgumentException.class)
	public void badQueryStreamName_Test() throws Exception {
		
		GreedyDB db = new GreedyDB();
		db.importStream("employers", JSON_STRING1);
		db.importStream("clicks", JSON_STRING4);

		assertEquals(667732, db.queryStreamTimestamp("bad_stream_name", 1460465692, "id"));
		
	}
}
