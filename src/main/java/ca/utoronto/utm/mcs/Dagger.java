package ca.utoronto.utm.mcs;

import javax.inject.Inject;

import com.mongodb.client.MongoClient;
import com.sun.net.httpserver.HttpServer;

public class Dagger {
	
	private MongoClient db;
	private HttpServer server;
	
	@Inject
	public Dagger(MongoClient db, HttpServer server) {
		this.db = db;
		this.server = server;
	}

	public HttpServer getServer() {
		return this.server;
	}

	public void setServer(HttpServer server) {
		this.server = server;
	}

	public MongoClient getDb() {
		return this.db;
	}

	public void setDb(MongoClient db) {
		this.db = db;
	}

}
