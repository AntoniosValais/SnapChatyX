package gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage;

/*
*
* @Author AntoniosValais
*
*/

public enum EnumMessageType
{
	SnapTextMessage("SnapTextMessage"), UserConnectedMessage("UserConnectedMessage");

	private String type;

	private EnumMessageType( String type )
	{
		this.type = type;
	}

	@Override
	public String toString()
	{
		return this.type;
	}

	public String getType()
	{
		return this.type;
	}
}
