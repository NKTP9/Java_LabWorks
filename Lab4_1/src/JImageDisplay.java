import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;

public class JImageDisplay extends JComponent {

    private BufferedImage img;

    public JImageDisplay(int width, int height)
    {
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // инициализировать объект BufferedImage новым изображением с шириной и высотой, и типом изображения TYPE_INT_RGB
        Dimension dim = new Dimension(width, height); // Создаём объект Dimension
        super.setPreferredSize(dim); // super - текущий экземпляр родительского класса
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g); // вызов метода суперкласса
        g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null); // Рисование изображения в компоненте
    }

    public void clearImage()
    {
        for(int i = 0 ; i < img.getHeight() ; i++)
        {
            for(int j = 0 ; j < img.getWidth() ; j++)
            {
                this.drawPixel(i, j, 0); // Устанавливаем чёрный цвет
            }
        }
    }

    public void drawPixel(int x, int y, int rgbColor)
    {
        img.setRGB(x, y, rgbColor); // Перекрашиваем в определённый цвет
    }
}

