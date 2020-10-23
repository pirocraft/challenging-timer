package com.github.pirocraft.challengingtimer;

import com.intellij.openapi.wm.ToolWindow;
import kotlin.Unit;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TimerToolWindow {
    private JPanel timerPanel;
    private JLabel timerLabel;
    private final TimerView timerView;

    public TimerToolWindow(ToolWindow toolWindow) {
        timerView = new TimerView();
        timerLabel.setText(TimerViewKt.display(timerView.getTimeLeft()));
        timerView.subscribe((timeLeft) -> {
            timerLabel.setText(TimerViewKt.display(timeLeft));
            return Unit.INSTANCE;
        });
        timerView.click(null);

        timerPanel.setBackground(Color.GREEN);

        timerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Configuration.INSTANCE.reset();
            }
        });
    }

    public JComponent getContent() {
        return timerPanel;
    }
}
