package decoders;

public interface Decoder {
    String decode(String ciphertext, String key);
    String getName();
}
