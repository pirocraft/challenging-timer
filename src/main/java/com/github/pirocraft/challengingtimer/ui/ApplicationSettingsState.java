package com.github.pirocraft.challengingtimer.ui;

import com.github.pirocraft.challengingtimer.application.ConfigurationKt;
import com.github.pirocraft.challengingtimer.application.TimerViewKt;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "com.github.pirocraft.challengingtimer.ui.ApplicationSettingsState",
        storages = {@Storage("SdkSettingsPlugin.xml")})
public class ApplicationSettingsState implements PersistentStateComponent<ApplicationSettingsState> {
    public String duration = TimerViewKt.display(ConfigurationKt.defaultDuration());

    public static ApplicationSettingsState getInstance() {
        return ServiceManager.getService(ApplicationSettingsState.class);
    }

    @Override
    public @Nullable ApplicationSettingsState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull ApplicationSettingsState state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}
