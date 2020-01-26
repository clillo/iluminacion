package com.clillo.dmx.ui.jpanels.utiles;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class CircularProgressBar extends JPanel implements ActionListener {

	private static final long serialVersionUID = -3946987923965673138L;

	private boolean mIsRunning;
    private boolean mIsFadingOut;
    private Timer mTimer;
    private int mAngle;
    private int mFadeCount;
    private int mFadeLimit = 15;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int w = this.getWidth();
        int h = this.getHeight();
        if (!mIsRunning)
            return;
        
        Graphics2D g2 = (Graphics2D) g.create();
        float fade = (float) mFadeCount / (float) mFadeLimit;
        Composite urComposite = g2.getComposite();
        g2.setColor(this.getBackground());
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f * fade));
        g2.fillRect(0, 0, w, h);
        g2.setComposite(urComposite);
        
        int s = Math.min(w, h) / 4;
        int cx = w / 2;
        int cy = h / 2;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(s / 4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setPaint(Color.blue);
        g2.rotate(Math.PI * mAngle / 180, cx, cy);
        
        for (int i = 0; i < 12; i++){
            float scale = (11.0f - (float) i) / 11.0f;
            g2.drawLine(cx + s, cy, cx + s * 2, cy);
            g2.rotate(-Math.PI / 6, cx, cy);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, scale * fade));
        }
        
        g2.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (mIsRunning) {
            this.repaint();
            mAngle += 3;
            if (mAngle >= 360) {
                mAngle = 0;
            }
            if (mIsFadingOut) {
                if (--mFadeCount == 0) {
                    mIsRunning = false;
                    mTimer.stop();
                }
            } else if (mFadeCount < mFadeLimit) {
                mFadeCount++;
            }
        }
    }

    public void start() {
        if (mIsRunning) {
            return;
        }
        mIsRunning = true;// Run a thread for animation.
        mIsFadingOut = false;
        mFadeCount = 0;
        int fps = 24;
        int tick = 1000 / fps;
        mTimer = new Timer(tick, this);
        mTimer.start();
    }

    public void stop() {
        mIsFadingOut = true;
    }
}