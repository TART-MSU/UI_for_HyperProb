package UI_for_hyperprob;

import java.awt.Color;
import java.awt.*;
import java.awt.Font;
import javax.swing.text.Style;
public class style {
        public Color c;
        public int style;

        public style(Color c, int style)
        {
            this.c = c;
            this.style = style;
        }

       /* public static Style defaultStyle()
        {
            new Style s;
            s = new Style(Color.black, Font.BOLD);
            return s;
        }

       /* public Style copy()
        {
            int r = c.getRed(), g = c.getGreen(), b = c.getBlue();
            Color cc = new Color(r,g,b);
            return new Style(cc, style);
        } */
    }

