package test;

import javax.servlet.http.HttpServletResponse;

import request.JoinGame;
import request.MakePost;
import request.MethodWrapper;
import request.RegisterGame;
import request.TurnFinished;

import com.google.gson.Gson;

/**
 * @author Joel Baranowski
 * 
 */
public class TurnFinishedPost {

	Gson g = new Gson();
	
	public String run(TurnFinished tf){
		MakePost mp = new MakePost("http://1-dot-utopian-hearth-532.appspot.com/test2");
		
		String gts = g.toJson(tf);
		MethodWrapper mw = new MethodWrapper("turnFinished", gts);
		try {
			return mp.execute(mw);
	    } 
		catch (Exception exception) {
			exception.printStackTrace();
	    }
		return "no return";
	}
}
