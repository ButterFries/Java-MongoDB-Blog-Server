package ca.utoronto.utm.mcs.post;

import java.io.IOException;
import java.io.OutputStream;

import org.json.*;

import com.mongodb.client.MongoClient;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class Post implements HttpHandler
{
    private static MongoClient db;

    public Post(MongoClient db) {
    	Post.db = db;
    }

    public void handle(HttpExchange r) throws IOException {
    	
        try {
            if (r.getRequestMethod().equals("PUT")) {
                handlePut(r);
            }
            else if (r.getRequestMethod().equals("GET")) {
                handleGet(r);
            }
            else if(r.getRequestMethod().equals("DELETE")) {
                handleDelete(r);
            }
            else {
            	//405 METHOD NOT ALLOWED - called with something other than GET, PUT, DELETE
            	String response = "";
            	r.sendResponseHeaders(405, response.length());
            	OutputStream os = r.getResponseBody();
            	os.write(response.getBytes());
            	os.close();
            }
        } catch (JSONException e) {
        	//400 BAD REQUEST - Improperly formatted JSON
        	String response = "";
        	r.sendResponseHeaders(400, response.length());
        	OutputStream os = r.getResponseBody();
        	os.write(response.getBytes());
        	os.close();
		} catch (Exception e) {
			//400 INTERNAL SERVER ERROR
        	String response = "";
        	r.sendResponseHeaders(500, response.length());
        	OutputStream os = r.getResponseBody();
        	os.write(response.getBytes());
        	os.close();
		}
    }

    private void handlePut(HttpExchange r) throws IOException, JSONException, Exception {
    	Handle.handlePut(r, db.getDatabase("csc301a2").getCollection("posts"));
    }
    private void handleGet(HttpExchange r) throws IOException, JSONException, Exception {
    	Handle.handleGet(r, db.getDatabase("csc301a2").getCollection("posts"));
    }
    private void handleDelete(HttpExchange r) throws IOException, JSONException, Exception {
		Handle.handleDelete(r, db.getDatabase("csc301a2").getCollection("posts"));
	}
    
}
