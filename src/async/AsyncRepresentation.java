package async;

import org.restlet.Response;
import org.restlet.representation.StringRepresentation;

public class AsyncRepresentation extends StringRepresentation implements Runnable
{
	public AsyncRepresentation( String text, Response response )
	{
		super( text );
		this.response = response;
	}

	public void run()
	{
		System.out.println( "Committing" );
		response.setEntity( this );
		response.commit();
	}

	private final Response response;
}
