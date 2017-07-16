package helloword.txt;

import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class VKMapping {
    static Map<String, Integer> KEY_MAPPING;
    static {
        KEY_MAPPING = new HashMap<>();
        KEY_MAPPING.put("SHIFT", KeyEvent.VK_SHIFT);
        KEY_MAPPING.put("ALT", KeyEvent.VK_ALT);
        KEY_MAPPING.put("CTRL", KeyEvent.VK_CONTROL);
        KEY_MAPPING.put("A", KeyEvent.VK_A);
        KEY_MAPPING.put("B", KeyEvent.VK_B);
        KEY_MAPPING.put("C", KeyEvent.VK_C);
        KEY_MAPPING.put("D", KeyEvent.VK_D);
        KEY_MAPPING.put("E", KeyEvent.VK_E);
        KEY_MAPPING.put("F", KeyEvent.VK_F);
        KEY_MAPPING.put("G", KeyEvent.VK_G);
        KEY_MAPPING.put("H", KeyEvent.VK_H);
        KEY_MAPPING.put("I", KeyEvent.VK_I);
        KEY_MAPPING.put("J", KeyEvent.VK_J);
        KEY_MAPPING.put("K", KeyEvent.VK_K);
        KEY_MAPPING.put("L", KeyEvent.VK_L);
        KEY_MAPPING = Collections.unmodifiableMap(KEY_MAPPING);
    }
}
