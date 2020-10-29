package com.github.pirocraft.challengingtimer;

import com.intellij.openapi.wm.ToolWindow;
import kotlin.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Duration;

public class TimerToolWindow {
    private JPanel timerPanel;
    private JLabel timerLabel;
    private final TimerView timerView;

    public TimerToolWindow(ToolWindow toolWindow) {
        timerView = new TimerView();
        timerLabel.setText(TimerViewKt.display(timerView.getTimeLeft()));
        timerPanel.setBackground(Color.GREEN);
        timerView.subscribe(this::updateTime, this::updateColor);

        timerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1)
                    timerView.click(null);
                else
                    timerView.doubleClick(null);
            }
        });
    }

    public JComponent getContent() {
        return timerPanel;
    }

    private Unit updateTime(Duration timeLeft) {
        timerLabel.setText(TimerViewKt.display(timeLeft));
        return Unit.INSTANCE;
    }

    private Unit updateColor(Color color) {
        timerPanel.setBackground(color);
        return Unit.INSTANCE;
    }
}
