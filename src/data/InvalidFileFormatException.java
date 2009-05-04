package cymple.data;

public class InvalidFileFormatException extends RuntimeException {
	public InvalidFileFormatException() {
		super("This is not a valid cymple.bin file.");
	}

	public InvalidFileFormatException(Throwable cause) {
		super("This is not a valid cymple.bin file.", cause);
	}
}
