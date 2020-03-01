package ca.utoronto.utm.mcs.post;

import com.mongodb.client.MongoClient;
import com.sun.net.httpserver.HttpServer;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerPost_Factory implements Factory<DaggerPost> {
  private final Provider<HttpServer> serverProvider;

  private final Provider<MongoClient> dbProvider;

  public DaggerPost_Factory(Provider<HttpServer> serverProvider, Provider<MongoClient> dbProvider) {
    this.serverProvider = serverProvider;
    this.dbProvider = dbProvider;
  }

  @Override
  public DaggerPost get() {
    return provideInstance(serverProvider, dbProvider);
  }

  public static DaggerPost provideInstance(
      Provider<HttpServer> serverProvider, Provider<MongoClient> dbProvider) {
    return new DaggerPost(serverProvider.get(), dbProvider.get());
  }

  public static DaggerPost_Factory create(
      Provider<HttpServer> serverProvider, Provider<MongoClient> dbProvider) {
    return new DaggerPost_Factory(serverProvider, dbProvider);
  }

  public static DaggerPost newDaggerPost(HttpServer server, MongoClient db) {
    return new DaggerPost(server, db);
  }
}
