package test;

import javax.servlet.http.HttpServletResponse;

import request.JoinGame;
import request.MakePost;
import request.MethodWrapper;

import com.google.gson.Gson;

/**
 * @author Joel Baranowski
 * 
 */
public class TestPost {

	Gson g = new Gson();
	
	public String run(){
		MakePost mp = new MakePost("http://1-dot-utopian-hearth-532.appspot.com/test2");
		JoinGame jg = new JoinGame(0, "test");
		String jgs = g.toJson(jg);
		MethodWrapper mw = new MethodWrapper("joinGame", jgs);
		try {
			return mp.execute(mw);
	    } 
		catch (Exception exception) {
			exception.printStackTrace();
	    }
		return "no return";
	}
}
