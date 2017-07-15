import java.io.File;

/**
 * Created by haihuan on 7/15/17.
 */
public class test {
    public static void main(String[] args) {
        Recording recording = new Recording();
        try {
            recording.start();
            File file = new File("/Users/haihuan/Desktop/test2.wav");
            recording.stop();
            recording.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
