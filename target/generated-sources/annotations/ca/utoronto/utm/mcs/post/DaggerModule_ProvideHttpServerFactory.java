package ca.utoronto.utm.mcs.post;

import com.sun.net.httpserver.HttpServer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerModule_ProvideHttpServerFactory implements Factory<HttpServer> {
  private final DaggerPostModule module;

  public DaggerModule_ProvideHttpServerFactory(DaggerPostModule module) {
    this.module = module;
  }

  @Override
  public HttpServer get() {
    return provideInstance(module);
  }

  public static HttpServer provideInstance(DaggerPostModule module) {
    return proxyProvideHttpServer(module);
  }

  public static DaggerModule_ProvideHttpServerFactory create(DaggerPostModule module) {
    return new DaggerModule_ProvideHttpServerFactory(module);
  }

  public static HttpServer proxyProvideHttpServer(DaggerPostModule instance) {
    return Preconditions.checkNotNull(
        instance.provideHttpServer(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
