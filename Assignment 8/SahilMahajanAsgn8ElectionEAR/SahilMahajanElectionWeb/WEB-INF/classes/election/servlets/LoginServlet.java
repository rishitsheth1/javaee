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
import your.name.election.ejbs.CandidateManagerLocal;
import your.name.election.ejbs.ElectionManagerLocal;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/signin")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String successPage = "ballot.jsp";
	private static final String failurePage = "signin.jsp";
	@EJB
	private CandidateManagerLocal cm;
	@EJB
	private ElectionManagerLocal electionManager;

	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	// assume list of candidates does not change during period of voting
	public void init() {
		List<Candidate> candidates = null;
			candidates = cm.getCandidates();
			getServletContext().setAttribute("candidates", candidates); 
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		RequestDispatcher rd = null;
		try {
			if (session.getAttribute("voter") != null) {
				throw new EntityNotFoundException(
						" Session from previous user has been terminated");
			}
			String voterId = request.getParameter("voterId").trim();
			request.setAttribute("voterId", voterId);
			String password = request.getParameter("password").trim();
			if (voterId.isEmpty() || password.isEmpty() ) {
				throw new EntityNotFoundException("Id and password are both required to login");
			}
			int vid = Integer.parseInt(voterId);
			Voter voter = electionManager.getAuthenticatedVoter(vid, password);
			session.setAttribute( "voter", voter );
			rd = request.getRequestDispatcher(successPage);
		} catch (NumberFormatException e) {
			session.invalidate();
			request.setAttribute("message", "Voter ID must be a number");
			rd = request.getRequestDispatcher(failurePage);
		}catch (EntityNotFoundException | DataInputException e) {
			session.invalidate();
			request.setAttribute("message", e.getMessage());
			rd = request.getRequestDispatcher(failurePage);
		} finally {
			rd.forward( request,  response);
		}
		return;
	}
}
