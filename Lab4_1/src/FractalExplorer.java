import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FractalExplorer {

    private int dispSize; // Квадратное отображение фрактала
    private JImageDisplay img; // Ссылка для обновления отображения в разных методах в процессе вычисления фрактала
    private FractalGenerator gen; // Для отображения других видов фракталов в будущем
    Rectangle2D.Double r; // Указывает диапазон комплексной плоскости, которая выводится на экран.

    /*
    Конструктор, который принимает значение размера отображения в качестве аргумента, затем сохраняет это значение в
    соответствующем поле, а также инициализирует объекты диапазона и фрактального генератора.
    */
    public FractalExplorer(int dSize)
    {
        dispSize = dSize;
        gen = new Mandelbrot();
        r = new Rectangle2D.Double();
        gen.getInitialRange(r);
    }

    /*
    Метод, инициализирующий графический интерфейс Swing
     */
    public void createAndShowGUI()
    {
        JFrame frame = new JFrame("Рисование фракталов"); // Заголовок
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Операция закрытия окна по умолчанию
        Container contentPane = frame.getContentPane();

        contentPane.setLayout(new BorderLayout()); // Для содержимого окна

        img = new JImageDisplay(dispSize, dispSize); // Объект отображения изображения
        contentPane.add(img, BorderLayout.CENTER); // Изображение по центру

        JButton resetButton = new JButton("Очистить поле"); // Кнопка
        resetButton.addActionListener(new Reset());
        contentPane.add(resetButton, BorderLayout.SOUTH); // Расположение внизу

        contentPane.addMouseListener(new MouseListener());

        frame.pack(); // Разметка окна
        frame.setVisible(true); // Видимость окна
        frame.setResizable(false); // Запрет изменения размеров окна
    }

    /*
    Метод, который циклически проходит через каждый пиксель в отображении
     */
    public void drawFractal()
    {
        int rgbColor = 0;
        for(int x = 0 ; x < dispSize ; ++x) // Цикл, проходящий через каждый пиксель
        {
            // х - пиксельная координата; xCoord - координата в пространстве фрактала
            double xCoord = FractalGenerator.getCoord(r.x, r.x + r.width, dispSize, x);
            for(int y = 0 ; y < dispSize ; ++y)
            {
                // y - пиксельная координата; yCoord - координата в пространстве фрактала
                double yCoord = FractalGenerator.getCoord(r.y, r.y + r.height, dispSize, y);
                float numIters = gen.numIterations(xCoord, yCoord); // Определение количества итераций
                if(numIters == -1)
                {
                    rgbColor = 0; // Если точка не выходит за границы, то покрасить в чёрный
                }
                else
                {
                    float hue = 0.7f + numIters / 200f; // Иначе красим в тот цвет, основанный на количестве итераций
                    rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                }
                img.drawPixel(x, y, rgbColor);
            }
        }
        img.repaint(); // Обновление JImageDisplay
    }

    /*
    Метод, сбрасывающий диапазон к начальному, определенному генератором, а затем перерисовывающий фрактал.
     */
    private class Reset implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            r = new Rectangle2D.Double();
            gen.getInitialRange(r);
            drawFractal();
        }
    }

    /*
    При получении события о щелчке мышью, данный класс отображает пиксельные кооринаты щелчка в область фрактала, а затем вызывает
    метод генератора recenterAndZoomRange() с координатами, по которым щелкнули, и масштабом 0.5, т.е. приближает изображение
     */
    private class MouseListener extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            double xCoord = FractalGenerator.getCoord(r.x, r.x + r.width, dispSize, e.getX());
            double yCoord = FractalGenerator.getCoord(r.y, r.y + r.height, dispSize, e.getY());
            gen.recenterAndZoomRange(r, xCoord, yCoord, 0.5);
            drawFractal();
        }
    }

    /*
    Статический класс main, который запускает программу
     */
    public static void main(String[] args)
    {
        FractalExplorer explorer = new FractalExplorer (800);
        explorer.createAndShowGUI();
        explorer.drawFractal();
    }
}