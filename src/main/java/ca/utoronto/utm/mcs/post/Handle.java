package ca.utoronto.utm.mcs.post;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.sun.net.httpserver.HttpExchange;

import ca.utoronto.utm.mcs.Utils;

public class Handle {
	
	public static void handleGet(HttpExchange r, MongoCollection<Document> col) throws JSONException, IOException {
		
		String body = Utils.convert(r.getRequestBody());
        JSONObject deserialized = new JSONObject(body);
        
        boolean idGiven = false, nameGiven = false;
        
        String id = "", name = "";
        
        if (deserialized.has("_id")) {
            id = deserialized.getString("_id");
            idGiven = true;
        }
        if (deserialized.has("name")) {
        	name = deserialized.getString("name");
        	nameGiven = true;
        }
        if (!idGiven && !nameGiven)
        	throw new JSONException("JSON does not contain _id and/or name");
        
        
        // If id and name given, query only for id, if no ID found respond with 400 and DON'T check names
        if (idGiven && nameGiven) {
        	
        	// id is not valid type so no results will return
        	if (!ObjectId.isValid(id)) {
        		String response = "";
	            r.sendResponseHeaders(400, response.length());
	            OutputStream os = r.getResponseBody();
	            os.write(response.getBytes());
	            os.close();
	            return;
        	}
        	
        	FindIterable<Document> rtn = col.find(Filters.eq("_id", new ObjectId(id)));
        	
        	JSONArray output = new JSONArray();
        	
        	for(Document d:rtn)
        		output.put(new JSONObject(d.toJson()));
        	
        	//id and name given, id found
        	if (output.length() > 0) {
	        	String response = ""+output.toString();
	            r.sendResponseHeaders(200, response.length());
	            OutputStream os = r.getResponseBody();
	            os.write(response.getBytes());
	            os.close();
	            return;
        	}
        	//id and name given, id does not exist
        	else {
        		String response = "";
	            r.sendResponseHeaders(400, response.length());
	            OutputStream os = r.getResponseBody();
	            os.write(response.getBytes());
	            os.close();
	            return;
        	}
        	
        }
        
        
        // If ONLY id given, respond with 404 if no posts found
        else if (idGiven) {
        	
        	// id is not valid type so no results will return
        	if (!ObjectId.isValid(id)) {
        		String response = "";
	            r.sendResponseHeaders(404, response.length());
	            OutputStream os = r.getResponseBody();
	            os.write(response.getBytes());
	            os.close();
	            return;
        	}
        	
        	
        	FindIterable<Document> rtn = col.find(Filters.eq("_id", new ObjectId(id)));
        		
        	JSONArray output = new JSONArray();

        	for(Document d:rtn)
        		output.put(new JSONObject(d.toJson()));
        	
        	
        	//only id given, id found
        	if (output.length() > 0) {
        		String response = ""+output.toString();
            	r.sendResponseHeaders(200, response.length());
            	OutputStream os = r.getResponseBody();
            	os.write(response.getBytes());
            	os.close();
            	return;
        	}
        	
        	else {
        		//id and name given, id does not exist
        		String response = "";
            	r.sendResponseHeaders(400, response.length());
            	OutputStream os = r.getResponseBody();
            	os.write(response.getBytes());
            	os.close();
        	}
        }
        // If ONLY name given, respond with 404 if no posts found
        else {
        	
        	FindIterable<Document> rtn = col.find(Filters.regex("title", name));
    		
    		JSONArray output = new JSONArray();

    		for(Document d:rtn) {
    			output.put(new JSONObject(d.toJson()));
    		}
    		
    		//Name matches at least 1 post
    		if (output.length() > 0) {
	    		String response = ""+output.toString();
	        	r.sendResponseHeaders(200, response.length());
	        	OutputStream os = r.getResponseBody();
	        	os.write(response.getBytes());
	        	os.close();
	        	return;
    		}
    		//Name matches 0 posts
    		else {
    			String response = "";
	        	r.sendResponseHeaders(404, response.length());
	        	OutputStream os = r.getResponseBody();
	        	os.write(response.getBytes());
	        	os.close();
	        	return;
    		}
        }
        
        
        
	}
	
	
	

	public static void handlePut(HttpExchange r, MongoCollection<Document> col) throws JSONException, IOException {

		String body = Utils.convert(r.getRequestBody());
        JSONObject deserialized = new JSONObject(body);
        
        String title, author, content;
        List<String> tags = new ArrayList<String>();
        
        if (!deserialized.has("title") ||
        	!deserialized.has("author") ||
        	!deserialized.has("content") ||
        	!deserialized.has("tags"))
        	throw new JSONException("JSON does not contain title, author, content, and/or tags");
        
        
        title = deserialized.getString("title");
        author = deserialized.getString("author");
        content = deserialized.getString("content");
        
        JSONArray arr = deserialized.getJSONArray("tags");
        
        for(int i = 0; i < arr.length(); i++){
            tags.add(arr.getString(i));
        }
        
        
        Document send = new Document("title", title)
        				.append("author", author)
        				.append("content", content)
        				.append("tags", tags);
        
        col.insertOne(send);
        
        String id = send.get("_id").toString();
        
        JSONObject output = new JSONObject();
        output.put("_id", id);
        
        String response = ""+output.toString();
    	r.sendResponseHeaders(200, response.length());
    	OutputStream os = r.getResponseBody();
    	os.write(response.getBytes());
    	os.close();
    	return;
        
	}
	
	public static void handleDelete(HttpExchange r, MongoCollection<Document> col) throws JSONException, IOException, Exception {
		
		String body = Utils.convert(r.getRequestBody());
        JSONObject deserialized = new JSONObject(body);
        
        String id;
        
        
        if (!deserialized.has("_id"))
        	throw new JSONException("JSON does not contain _id");
        
        id = deserialized.getString("_id");
       	
        if (ObjectId.isValid(id) && col.countDocuments(Filters.eq("_id", new ObjectId(id)))>0) {
       		
       		col.findOneAndDelete(Filters.eq("_id", new ObjectId(id)));
       		
       		String response = "";
            r.sendResponseHeaders(200, response.length());
           	OutputStream os = r.getResponseBody();
            os.write(response.getBytes());
            os.close();
            return;
       		
        }
        
        else {
        	
        	String response = "";
            r.sendResponseHeaders(404, response.length());
           	OutputStream os = r.getResponseBody();
            os.write(response.getBytes());
            os.close();
            return;
        
        	
        }
	}
	
}
