package com.github.pirocraft.challengingtimer.ui;

import com.github.pirocraft.challengingtimer.application.DurationParserKt;
import com.github.pirocraft.challengingtimer.application.TimerView;
import com.intellij.openapi.wm.ToolWindow;
import kotlin.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Duration;

public class TimerToolWindow {
    public static final String BACKGROUND_COLOR_GREEN = "#02E100";
    public static final String BACKGROUND_COLOR_YELLOW = "#E1E100";
    public static final String BACKGROUND_COLOR_RED = "#E10000";
    private JPanel timerPanel;
    private JLabel timerLabel;
    private final TimerView timerView;

    public TimerToolWindow(ToolWindow toolWindow) {
        timerView = new TimerView();
        timerLabel.setText(DurationParserKt.format(timerView.getTimeLeft()));
        timerPanel.setBackground(mapColor(Color.GREEN));
        timerView.subscribe(this::updateTime, this::updateColor);

        timerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Delay simple click event to avoid YELLOW -> GREEN flash
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
        timerLabel.setText(DurationParserKt.format(timeLeft));
        return Unit.INSTANCE;
    }

    private Unit updateColor(Color color) {
        timerPanel.setBackground(mapColor(color));
        return Unit.INSTANCE;
    }

    private Color mapColor(Color color) {
        if (color == Color.YELLOW)
            return Color.decode(BACKGROUND_COLOR_YELLOW);
        else if (color == Color.RED)
            return Color.decode(BACKGROUND_COLOR_RED);
        return Color.decode(BACKGROUND_COLOR_GREEN);
    }
}
