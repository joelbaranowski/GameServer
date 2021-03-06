package test;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.http.*;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.gson.Gson;

import request.JoinGame;
import request.MethodWrapper;
import request.TakeTurn;

@SuppressWarnings("serial")
public class TestServlet extends HttpServlet {
	
	private Gson g = new Gson();
	private MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String method = req.getParameter("method");
		String data = req.getParameter("data");
		if(method == null || data == null)
			return;
		this.execute(req.getParameter("method"), URLDecoder.decode(req.getParameter("data"), "UTF-8"), req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException{
		resp.setContentType("text/plain");
		resp.getWriter().println("from server");
		MethodWrapper mw = g.fromJson(req.getReader(), MethodWrapper.class);
		this.execute(mw.getMethod(), mw.getData(), req, resp);
	}

	private void execute(String method, String data, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		switch(method){
			case "takeTurn":{
				TakeTurn tt = g.fromJson(data, TakeTurn.class);
				Long playerID = tt.getPlayerID();
				Long currScore = tt.getCurrentScore();
				TakeTurn tf = new TakeTurn(playerID, currScore + 1);
				TurnFinishedPost tfp = new TurnFinishedPost();
				String result = tfp.run(tf);
				resp.getWriter().println(result);
				break;
			}
		}
	}
}
