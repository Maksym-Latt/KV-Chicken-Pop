package com.manacode.chickenpop.audio;

import com.manacode.chickenpop.data.settings.SettingsRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class DefaultAudioController_Factory implements Factory<DefaultAudioController> {
  private final Provider<SettingsRepository> settingsRepositoryProvider;

  public DefaultAudioController_Factory(Provider<SettingsRepository> settingsRepositoryProvider) {
    this.settingsRepositoryProvider = settingsRepositoryProvider;
  }

  @Override
  public DefaultAudioController get() {
    return newInstance(settingsRepositoryProvider.get());
  }

  public static DefaultAudioController_Factory create(
      Provider<SettingsRepository> settingsRepositoryProvider) {
    return new DefaultAudioController_Factory(settingsRepositoryProvider);
  }

  public static DefaultAudioController newInstance(SettingsRepository settingsRepository) {
    return new DefaultAudioController(settingsRepository);
  }
}
