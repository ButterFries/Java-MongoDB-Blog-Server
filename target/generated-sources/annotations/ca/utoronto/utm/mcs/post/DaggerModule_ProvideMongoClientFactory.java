package ca.utoronto.utm.mcs.post;

import com.mongodb.client.MongoClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerModule_ProvideMongoClientFactory implements Factory<MongoClient> {
  private final DaggerPostModule module;

  public DaggerModule_ProvideMongoClientFactory(DaggerPostModule module) {
    this.module = module;
  }

  @Override
  public MongoClient get() {
    return provideInstance(module);
  }

  public static MongoClient provideInstance(DaggerPostModule module) {
    return proxyProvideMongoClient(module);
  }

  public static DaggerModule_ProvideMongoClientFactory create(DaggerPostModule module) {
    return new DaggerModule_ProvideMongoClientFactory(module);
  }

  public static MongoClient proxyProvideMongoClient(DaggerPostModule instance) {
    return Preconditions.checkNotNull(
        instance.provideMongoClient(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
