package binary.search.game.servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import binary.search.game.model.NumberGuesser;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet("/SignIn")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		HttpSession session = request.getSession();
		session.invalidate();
		if( user.isEmpty() ) {
			request.setAttribute("message",  "Please enter a name");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		session = request.getSession();
		session.setAttribute("user", user);
		NumberGuesser ng = new NumberGuesser();
		String nextPage = "nextGuess.jsp";
		List<Integer> guessTrail = new LinkedList<Integer>();		
		try {
			ng.firstGuess();
			guessTrail.add( ng.getLastGuess() );
		} catch (Exception e) {
			request.setAttribute("Exception", e);
			nextPage ="badGuess.jsp";
		} finally {
			session.setAttribute("Guesser", ng);
			request.setAttribute("guessCount", ng.getGuessCount() );
			request.setAttribute("lastGuess", ng.getLastGuess() );
			session.setAttribute("guessTrail", guessTrail );
			request.getRequestDispatcher(nextPage).forward(request, response);
		}
		return;	
	}
}
