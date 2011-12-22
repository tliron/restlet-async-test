package async;

import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class AsyncResource extends ServerResource
{
	public static volatile boolean async = true;

	public static volatile int delay = 1;

	@Override
	protected void doInit() throws ResourceException
	{
		super.doInit();
		setAnnotated( false );
	}

	@Override
	public Representation get() throws ResourceException
	{
		return get( null );
	}

	@Override
	public Representation get( Variant variant ) throws ResourceException
	{
		AsyncRepresentation representation = new AsyncRepresentation( "Hello from the async resource! " + System.currentTimeMillis(), getResponse() );
		if( async )
		{
			setAutoCommitting( false );
			getApplication().getTaskService().submit( representation );
			if( delay > 0 )
			{
				try
				{
					Thread.sleep( delay );
				}
				catch( InterruptedException x )
				{
					x.printStackTrace();
				}
			}
			return null;
		}
		else
			return representation;
	}
}
