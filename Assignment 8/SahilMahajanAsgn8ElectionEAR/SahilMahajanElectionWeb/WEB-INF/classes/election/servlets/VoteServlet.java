package election.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import election.entities.Candidate;
import election.entities.Voter;
import election.exceptions.DataInputException;
import election.exceptions.EntityNotFoundException;
import your.name.election.ejbs.ElectionManagerLocal;

/**
 * Servlet implementation class VoteServlet
 */
@WebServlet("/vote")
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String successPage = "done.jsp";
	private static final String failurePage = "errorPage.jsp";
	@EJB
	private ElectionManagerLocal electionManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VoteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher( successPage );
		try {
			Voter voter = (Voter) session.getAttribute("voter");
		if (voter == null) {
			throw new EntityNotFoundException( "You must log in before voting");
		}
		List<Candidate> lc = (List<Candidate>) getServletContext().getAttribute("candidates");
		for ( Candidate candidate : lc) {
			if ( request.getParameter( candidate.getCid() ) != null ) {
				electionManager.castBallot( voter, candidate );
				session.invalidate();
				new ElectionHelper().printVoteCount(System.out);
				rd.forward( request, response);
				return;
			} 
		}
		// no candidate selected from ballot form
		throw new DataInputException ("Invalid ballot from voter: " + voter );		
		} catch ( EntityNotFoundException | DataInputException e) {
			request.setAttribute("message", e.getMessage());
			rd = request.getRequestDispatcher( failurePage);
			rd.forward(request,  response);
		}
		return;
	}
}
