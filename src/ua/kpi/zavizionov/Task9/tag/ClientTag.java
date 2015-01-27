package ua.kpi.zavizionov.Task9.tag;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;

import ua.kpi.zavizionov.Task9.db.entity.Client;

import java.io.*;

public class ClientTag extends SimpleTagSupport {

	private Client client;

	StringWriter sw = new StringWriter();

	public void doTag() throws JspException, IOException {
		if (client != null) {
			/* Use message from attribute */
			JspWriter out = getJspContext().getOut();
			out.println(client.getName());
		}
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
