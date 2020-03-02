package ca.utoronto.utm.mcs;

import com.mongodb.client.MongoClient;
import com.sun.net.httpserver.HttpServer;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class Dagger_Factory implements Factory<Dagger> {
  private final Provider<MongoClient> dbProvider;

  private final Provider<HttpServer> serverProvider;

  public Dagger_Factory(Provider<MongoClient> dbProvider, Provider<HttpServer> serverProvider) {
    this.dbProvider = dbProvider;
    this.serverProvider = serverProvider;
  }

  @Override
  public Dagger get() {
    return provideInstance(dbProvider, serverProvider);
  }

  public static Dagger provideInstance(
      Provider<MongoClient> dbProvider, Provider<HttpServer> serverProvider) {
    return new Dagger(dbProvider.get(), serverProvider.get());
  }

  public static Dagger_Factory create(
      Provider<MongoClient> dbProvider, Provider<HttpServer> serverProvider) {
    return new Dagger_Factory(dbProvider, serverProvider);
  }

  public static Dagger newDagger(MongoClient db, HttpServer server) {
    return new Dagger(db, server);
  }
}
