package com.github.pirocraft.challengingtimer;

import com.intellij.openapi.wm.ToolWindow;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TimerToolWindow {
    private JPanel timerPanel;
    private JLabel timerLabel;
    private final TimerView timerView;

    public TimerToolWindow(ToolWindow toolWindow) {
        timerView = new TimerView();
//        timerView.
    }

    public JComponent getContent() {
        return timerPanel;
    }
}
