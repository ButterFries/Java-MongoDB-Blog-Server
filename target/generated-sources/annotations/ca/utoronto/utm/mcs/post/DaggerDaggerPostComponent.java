package ca.utoronto.utm.mcs.post;

import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerDaggerPostComponent implements DaggerPostComponent {
  private DaggerPostModule daggerPostModule;

  private DaggerDaggerPostComponent(Builder builder) {
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
    this.daggerPostModule = builder.daggerPostModule;
  }

  @Override
  public DaggerPost buildMongoHttp() {
    return new DaggerPost(
        DaggerPostModule_ProvideHttpServerFactory.proxyProvideHttpServer(daggerPostModule),
        DaggerPostModule_ProvideMongoClientFactory.proxyProvideMongoClient(daggerPostModule));
  }

  public static final class Builder {
    private DaggerPostModule daggerPostModule;

    private Builder() {}

    public DaggerPostComponent build() {
      if (daggerPostModule == null) {
        this.daggerPostModule = new DaggerPostModule();
      }
      return new DaggerDaggerPostComponent(this);
    }

    public Builder daggerPostModule(DaggerPostModule daggerPostModule) {
      this.daggerPostModule = Preconditions.checkNotNull(daggerPostModule);
      return this;
    }
  }
}
