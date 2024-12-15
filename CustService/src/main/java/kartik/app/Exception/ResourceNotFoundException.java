package kartik.app.Exception;

public class ResourceNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String s)
	{
		super(s);
	}
	
}
