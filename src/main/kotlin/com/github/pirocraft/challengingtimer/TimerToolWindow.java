package com.github.pirocraft.challengingtimer;

import com.intellij.openapi.wm.ToolWindow;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class TimerToolWindow {
    private JPanel timerPanel;
    private JLabel timerLabel;

    public TimerToolWindow(ToolWindow toolWindow) {
    }

    @Nullable
    public JComponent getContent() {
        return timerPanel;
    }

    private void createUIComponents() {

    }
}
