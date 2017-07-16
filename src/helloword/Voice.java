package helloword;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.result.WordResult;

import java.io.IOException;

public class Voice {
    private LiveSpeechRecognizer recognizer;

    public Voice() {
        try {
            Configuration configuration = new Configuration();

            configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
            configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/game.dict");
            configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
            recognizer = new LiveSpeechRecognizer(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String recognize() {
        recognizer.startRecognition(true);
        SpeechResult result = recognizer.getResult();
        recognizer.stopRecognition();
        String word = result.getWords().get(0).getWord().getSpelling();
        System.out.println(word);
        return word;
    }
}
