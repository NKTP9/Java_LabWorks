import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FractalExplorer {

    private int dispSize; // Квадратное отображение фрактала
    private JImageDisplay img; // Ссылка для обновления отображения в разных методах в процессе вычисления фрактала
    private FractalGenerator gen; // Для отображения других видов фракталов в будущем
    Rectangle2D.Double r; // Указывает диапазон комплексной плоскости, которая выводится на экран.
    JComboBox fractalChooser = new JComboBox();
    public int rowsRemaining;
    private JButton saveButton;
    private JButton resetButton;

    /*
    Конструктор, который принимает значение размера отображения в качестве аргумента, затем сохраняет это значение в
    соответствующем поле, а также инициализирует объекты диапазона и фрактального генератора.
    */
    public FractalExplorer(int dSize) {
        dispSize = dSize;
        gen = new Mandelbrot();
        r = new Rectangle2D.Double();
        gen.getInitialRange(r);
    }

    // Метод, инициализирующий графический интерфейс Swing
    public void createAndShowGUI() {
        JFrame frame = new JFrame("Рисование нескольких фракталов"); // Заголовок
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Операция закрытия окна по умолчанию
        Container contentPane = frame.getContentPane();

        contentPane.setLayout(new BorderLayout()); // Для содержимого окна
        contentPane.addMouseListener(new MouseListener());
        Chooser handler = new Chooser();

        img = new JImageDisplay(dispSize, dispSize); // Объект отображения изображения
        contentPane.add(img, BorderLayout.CENTER); // Изображение по центру

        // Блок с кнопками
        JPanel buttonsPanel = new JPanel();
        saveButton = new JButton("Сохранить фрактал"); // Кнопка
        saveButton.addActionListener(new Save());
        buttonsPanel.add(saveButton);

        resetButton = new JButton("Очистить поле"); // Кнопка
        resetButton.addActionListener(new Reset());
        buttonsPanel.add(resetButton);
        contentPane.add(buttonsPanel, BorderLayout.SOUTH);

        // Блок выбора фракталов
        JPanel fractalPanel = new JPanel();
        JLabel panelLabel = new JLabel("Выберите фрактал: ");
        fractalPanel.add(panelLabel);
        fractalChooser = new JComboBox();
        fractalChooser.addItem(Mandelbrot.String1());
        fractalChooser.addItem(Tricorn.String1());
        fractalChooser.addItem(BurningShip.String1());
        fractalChooser.addActionListener(handler);
        fractalPanel.add(fractalChooser);
        contentPane.add(fractalPanel, BorderLayout.NORTH);

        frame.pack(); // Разметка окна
        frame.setVisible(true); // Видимость окна
        frame.setResizable(false); // Запрет изменения размеров окна
    }

    // Класс ComboBox, который при выборе фрактала прорисовывает его изображение
    private class Chooser implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String selectedItem = Objects.requireNonNull(fractalChooser.getSelectedItem()).toString();
            if (selectedItem.equals(Mandelbrot.String1())) {
                gen = new Mandelbrot();
            } else if (selectedItem.equals(Tricorn.String1())) {
                gen = new Tricorn();
            } else if (selectedItem.equals(BurningShip.String1())) {
                gen = new BurningShip();
            } else {
                JOptionPane.showMessageDialog(null, "Ошибка: непонятный выбор (класс Chooser)");
                return;
            }
            r = new Rectangle2D.Double();
            gen.getInitialRange(r);
            drawFractal();
        }
    }

    /*
    При получении события о щелчке мышью, данный класс отображает пиксельные кооринаты щелчка в область фрактала, а затем вызывает
    метод генератора recenterAndZoomRange() с координатами, по которым щелкнули, и масштабом 0.5, т.е. приближает изображение
    */
    private class MouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            if (rowsRemaining != 0) {
                return;
            }
            double xCoord = FractalGenerator.getCoord(r.x, r.x + r.width, dispSize, e.getX());
            double yCoord = FractalGenerator.getCoord(r.y, r.y + r.height, dispSize, e.getY());
            gen.recenterAndZoomRange(r, xCoord, yCoord, 0.5);
            drawFractal();
        }
    }

/*    //Метод, который циклически проходит через каждый пиксель в отображении
    public void drawFractal() {
        int rgbColor = 0;
        for (int i = 0; i < dispSize; i++) { // Цикл, проходящий через каждый пиксель
            // х - пиксельная координата; xCoord - координата в пространстве фрактала
            double xCoord = FractalGenerator.getCoord(r.x, r.x + r.width, dispSize, i);
            for (int j = 0; j < dispSize; j++) {
                // y - пиксельная координата; yCoord - координата в пространстве фрактала
                double yCoord = FractalGenerator.getCoord(r.y, r.y + r.height, dispSize, j);
                float numIters = gen.numIterations(xCoord, yCoord); // Определение количества итераций
                if (numIters == -1) {
                    rgbColor = 0; // Если точка не выходит за границы, то покрасить в чёрный
                } else {
                    float hue = 0.5f + numIters / 200f; // Иначе красим в тот цвет, основанный на количестве итераций
                    rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                }
                img.drawPixel(i, j, rgbColor);
            }
        }
        img.repaint(); // Обновление JImageDisplay
    }*/

    // Класс кнопки, который очищает поле и перерисовывает фрактал заново
    private class Reset implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            r = new Rectangle2D.Double();
            gen.getInitialRange(r);
            drawFractal();
        }
    }

    // Класс кнопки, который сохраняет изображение
    private class Save implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
            chooser.setFileFilter(filter);
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    File fd = chooser.getSelectedFile();
                    String filePath = fd.getPath();
                    if (!filePath.toLowerCase().endsWith(".png")) {
                        fd = new File(filePath + ".png");
                    }
                    ImageIO.write(img.getImage(), "png", fd);
                } catch (IOException exc) {
                    JOptionPane.showMessageDialog(null, "Ошибка: невозможно сохранить файл. Подробнее - " + exc.getMessage() + "");
                    exc.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ошибка: неизвестное действие (класс Save)");
            }
        }
    }
    
    private void drawFractal()
    {
        enableUI(false);
        rowsRemaining = dispSize;
        for (int i = 0; i < dispSize; i++){
            FractalWorker drawRow = new FractalWorker(i);
            drawRow.execute();
        }
    }

    private void enableUI(boolean val) {
        fractalChooser.setEnabled(val);
        resetButton.setEnabled(val);
        saveButton.setEnabled(val);
    }

    // Вычисление значений цвета для одной строки фрактала
    private class FractalWorker extends SwingWorker<Object, Object>
    {
        int yCoord1;
        int[] RGBarray;

        private FractalWorker(int row) {
            yCoord1 = row;
        }

        protected Object doInBackground() {
            RGBarray = new int[dispSize];
            for (int i = 0; i < RGBarray.length; i++) {  // Проход через все пиксели в строке
                double xCoord = FractalGenerator.getCoord(r.x, r.x + r.width, dispSize, i);
                double yCoord = FractalGenerator.getCoord(r.y, r.y + r.height, dispSize, yCoord1);
                float numIters = gen.numIterations(xCoord, yCoord); // Определение количества итераций
                if (numIters == -1){
                    RGBarray[i] = 0;
                } else {
                    float hue = 0.5f + numIters / 200f; // Иначе красим в тот цвет, основанный на количестве итераций
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    RGBarray[i] = rgbColor;
                }
            }
            return null;
        }

        protected void done() {
            for (int i = 0; i < RGBarray.length; i++) {
                img.drawPixel(i, yCoord1, RGBarray[i]);
            }
            img.repaint(0, 0, yCoord1, dispSize, 1);

            rowsRemaining--;
            if (rowsRemaining == 0) {
                enableUI(true);
            }
        }
    }

    /*
    Статический класс main, который запускает программу
    */
    public static void main(String[] args) {
        FractalExplorer explorer = new FractalExplorer(800);
        explorer.createAndShowGUI();
        explorer.drawFractal();
    }
}
