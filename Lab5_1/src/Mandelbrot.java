import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator {

    public static final int MAX_ITERATIONS = 2000; // Константа с максимальным количеством итераций

    /**
     * Метод позволяет генератору фракталов определить
     * наиболее «интересную» область комплексной плоскости
     * для конкретного фрактала
     */
    public void getInitialRange(Rectangle2D.Double r)
    {
        r.x = -2;
        r.y = -1.5;
        r.width = 3;
        r.height = 3;
    }

    /**
     * Функция для фрактала Мандельброта имеет вид: z_n = z_n-1^2 + c, где все значения
     * — это комплексные числа, z0 = 0, и с - определенная точка фрактала, которую
     * мы отображаем на экране. Вычисления повторяются до тех пор, пока |z| > 2 (в
     * данной ситуации точка находится не во множестве Мандельброта), или пока
     * число итераций не достигнет максимального значения, например, 2000
     */
    public int numIterations(double x, double y)
    {
        int count = 0;
        double re = x;
        double im = y;
        double re2 = re * re;
        double im2 = im * im;
        double z_n2 = 0;

        while(count < MAX_ITERATIONS && z_n2 < 4)
        {
            im = (2 * re * im) + y;
            re = (re2 - im2) + x;

            re2  = re * re;
            im2  = im * im;

            z_n2 = re2 + im2;
            count++;
        }
        if (count < MAX_ITERATIONS) return count;
        return -1;
    }

    public static String String1() {
        return "Множество Мандельброта";
    }
}
