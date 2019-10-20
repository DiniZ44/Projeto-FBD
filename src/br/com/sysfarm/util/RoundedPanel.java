/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarm.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author eltho
 */
public class RoundedPanel extends JPanel
{
    protected int _strokeSize = 1;
    protected Color _shadowColor;
    protected Color _shadowColor2=Color.BLACK;
    protected boolean _shadowed;
    protected boolean _highQuality = true;
    protected Dimension _arcs = new Dimension(60, 60);
    protected int _shadowGap = 5;
    protected int _shadowOffset = 4;
    protected int _shadowAlpha = 150;

    protected Color _backgroundColor = Color.YELLOW;

    public RoundedPanel(Color color, boolean shadow)
    {
        super();
        this._shadowColor=color;
        this._shadowed=shadow;
        setOpaque(false);
    }

    @Override
    public void setBackground(Color c)
    {
        _backgroundColor = c;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        int shadowGap = this._shadowGap;
        Color shadowColorA = new Color(_shadowColor.getRed(), _shadowColor.getGreen(), _shadowColor.getBlue(), _shadowAlpha);
        Color shadowColorB = new Color(_shadowColor2.getRed(), _shadowColor2.getGreen(), _shadowColor2.getBlue(), _shadowAlpha);
        Graphics2D graphics = (Graphics2D) g;

        if(_highQuality)
        {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        if(_shadowed)
        {
            graphics.setColor(shadowColorB);
            graphics.fillRoundRect(_shadowOffset, _shadowOffset, width - _strokeSize - _shadowOffset,
                    height - _strokeSize - _shadowOffset, _arcs.width, _arcs.height);
        }
        else
        {
            _shadowGap = 1;
        }

        graphics.setColor(_backgroundColor);
        graphics.fillRoundRect(0,  0, width - shadowGap, height - shadowGap, _arcs.width, _arcs.height);
        graphics.setStroke(new BasicStroke(_strokeSize));
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0,  0, width - shadowGap, height - shadowGap, _arcs.width, _arcs.height);
        graphics.setStroke(new BasicStroke());
    }
}