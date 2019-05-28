package binary.search.game.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import binary.search.game.exceptions.GameLostException;
import binary.search.game.model.GameConstants;
import binary.search.game.model.NumberGuesser;

/**
 * Servlet implementation class NumberGuessingServlet
 */
@WebServlet("/GuessingGame")
public class NumberGuessingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String GUESS_AGAIN_PAGE = "nextGuess.jsp";
	private static final String GAME_OVER_ERROR_PAGE = "badGuess.jsp";
	private static final String GAME_OVER_PAGE = "goodGuess.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NumberGuessingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String nextPage = GUESS_AGAIN_PAGE;
		// unusual to store model-layer class in session but necessary here
		//     because a NumberGuesser is the game played by each user
		NumberGuesser ng = (NumberGuesser) session.getAttribute( "Guesser");
		String result = request.getParameter("submit");
		try {
			if (result != null && result.equals("too low")) {
				ng.guessHigher();
			} else if (result != null && result.equals("too high")) {
				ng.guessLower();
			} else if (result != null && result.equals("correct")){
					nextPage = GAME_OVER_PAGE;
			} else {
				request.setAttribute("maxGuesses", GameConstants.MAXGUESSES);
				throw new GameLostException("Other user response than: high | too loww | correct");
			}
			// cannot remove warning on generics except by @SuppressWarnings
		    List<Integer> guessTrail = ( List<Integer>) session.getAttribute("guessTrail");
		    guessTrail.add( ng.getLastGuess() );
			session.setAttribute("Guesser", ng);
			request.setAttribute("guessCount", ng.getGuessCount() );
			request.setAttribute("lastGuess", ng.getLastGuess() );
			session.setAttribute("guessTrail", guessTrail );
		} catch (GameLostException e) {
			request.setAttribute("message", e.getMessage() );
			nextPage = GAME_OVER_ERROR_PAGE;
		} finally {
			request.getRequestDispatcher( nextPage ).forward( request, response );
		}
		return;
	}

}