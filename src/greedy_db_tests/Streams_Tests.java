package greedy_db_tests;

import org.junit.Test;

import greedy_db.Streams;

public class Streams_Tests {

	@Test
	public void test_import_stream() {
		Streams streams = new Streams();
		String json = "{ “timestamp”: 1460463641, “id”: 88837264, “name”: “Davide”, “surname”: “Palmisano\" }";
				
		streams.newItem("employers", json);
		
		//assertEqual(streams.get("employers"), json);
	}

}
