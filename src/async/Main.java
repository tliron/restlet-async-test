package async;

import org.restlet.Component;
import org.restlet.data.Protocol;

public class Main
{
	public static void main( String[] args ) throws Exception
	{
		Component component = new Component();
		component.getServers().add( Protocol.HTTP, 8080 );
		component.getDefaultHost().attach( "/", AsyncResource.class );
		component.start();
	}
}
