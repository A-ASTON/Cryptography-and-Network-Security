package decoders;

public class DecoderFactory {
    public Decoder create(String name) {
        Decoder decoder = null;
        if ("caesar".equals(name.toLowerCase())) {
            decoder = new CaesarDecoder();
        }
        if ("vigenere".equals(name.toLowerCase())) {
            decoder = new VigenereDecoder();
        }
        return decoder;
    }
}
