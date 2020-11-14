package encoders;

public class EncoderFactory {
    public Encoder create(String name) {
        Encoder encoder = null;
        if ("caesar".equals(name.toLowerCase())) {
            encoder = new CaesarEncoder();
        }

        if ("vigenere".equals(name.toLowerCase())) {
            encoder = new VigenereEncoder();
        }

        return encoder;
    }
}
