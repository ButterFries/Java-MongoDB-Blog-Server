package ca.utoronto.utm.mcs.post;

import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerDaggerComponent implements DaggerPostComponent {
  private DaggerPostModule daggerModule;

  private DaggerDaggerComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static DaggerPostComponent create() {
    return new Builder().build();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.daggerModule = builder.daggerModule;
  }

  @Override
  public DaggerPost buildMongoHttp() {
    return new DaggerPost(
        DaggerModule_ProvideHttpServerFactory.proxyProvideHttpServer(daggerModule),
        DaggerModule_ProvideMongoClientFactory.proxyProvideMongoClient(daggerModule));
  }

  public static final class Builder {
    private DaggerPostModule daggerModule;

    private Builder() {}

    public DaggerPostComponent build() {
      if (daggerModule == null) {
        this.daggerModule = new DaggerPostModule();
      }
      return new DaggerDaggerComponent(this);
    }

    public Builder daggerModule(DaggerPostModule daggerModule) {
      this.daggerModule = Preconditions.checkNotNull(daggerModule);
      return this;
    }
  }
}
