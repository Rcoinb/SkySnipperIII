package com.greatdevs.Menu;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import javax.swing.JPanel;

class TextContainer extends JPanel
{
    public int m_width;
    public int m_height;
    private String m_text;
    private AttributedCharacterIterator m_iterator;
    private int m_start;
    private int m_end;

    public TextContainer(String text, int width, int height)
    {
        m_text = text;
        m_width = width;
        m_height = height;

        AttributedString styledText = new AttributedString(text);
        m_iterator = styledText.getIterator();
        m_start = m_iterator.getBeginIndex();
        m_end = m_iterator.getEndIndex();
    }

    public String getText()
    {
        return m_text;
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(m_width, m_height);
    }

    public void paint(Graphics g, float x, float y)
    {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        FontRenderContext frc = g2.getFontRenderContext();

        LineBreakMeasurer measurer = new LineBreakMeasurer(m_iterator, frc);
        measurer.setPosition(m_start);

        while (measurer.getPosition() < m_end)
        {
            TextLayout layout = measurer.nextLayout(m_width);

            y += layout.getAscent();
            float dx = layout.isLeftToRight() ?
                    0 : m_width - layout.getAdvance();
            
            layout.draw(g2, x + dx, y);
            y += layout.getDescent() + layout.getLeading();
        }
    }
}
