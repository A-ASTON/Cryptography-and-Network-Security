package encoders;

public interface Encoder {
    String encode(String plaintext, String key);
    String getName();
}
