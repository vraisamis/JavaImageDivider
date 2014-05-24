package vraisamis.jviewer;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.LayoutManager;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    public ImagePanel() {
        super();
        init();
    }

    public ImagePanel(boolean b) {
        super(b);
    }

    public ImagePanel(LayoutManager layoutManager) {
        super(layoutManager);
    }

    public ImagePanel(LayoutManager layoutManager, boolean b) {
        super(layoutManager, b);
    }
    
    private ImageIcon image;
    
    private void init() {
        image = null;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                resize();
            }
            @Override
            public void componentMoved(ComponentEvent componentEvent) {
            }
            @Override
            public void componentShown(ComponentEvent componentEvent) {
            }
            @Override
            public void componentHidden(ComponentEvent componentEvent) {
            }
        });
    }
    
    public void setImage(ImageIcon img) {
        if (img == null) return;
        this.image = img;
        resize();
    }
    
    private int resize() {
        if (image == null) return 1000;
        Dimension comp = this.getSize();
        Dimension imgsize = new Dimension(image.getIconWidth(),image.getIconHeight());
        double x = Math.min(1.0, Math.min((double)comp.width / imgsize.width, (double)comp.height / imgsize.height));
        removeAll();
        repaint();
        add(new JLabel(new ImageIcon(image.getImage().getScaledInstance((int)(image.getIconWidth() * x), -1, Image.SCALE_SMOOTH))));
        revalidate();
        return (int)(x * 1000);
    }
}
