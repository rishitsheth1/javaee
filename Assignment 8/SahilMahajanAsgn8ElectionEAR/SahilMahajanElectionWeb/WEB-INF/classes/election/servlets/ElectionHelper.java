package election.servlets;

import java.io.PrintStream;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import election.entities.Candidate;
import your.name.election.ejbs.CandidateManagerLocal;

public class ElectionHelper { 
	private static CandidateManagerLocal cm;
	
	static {
		try {
		cm = (CandidateManagerLocal )  new InitialContext().lookup (
				"java:app/YourNameElectionEJBs/CandidateManager");
		} catch (NamingException ne )	 {	
			cm = null;
		}
	}
	
	
    public ElectionHelper() {
       super();
    }
    
// for testing and debugging only: print current vote count for each candidate
	
	public void printVoteCount(PrintStream out) {
		if ( cm == null ) {
			System.out.println("Cannot access CandidateManager to print vote count");
			return;
		}
		List<Candidate> candidates = cm.getCandidates();
		out.print("Vote count so far: ");
		for (Candidate c : candidates) {
			out.printf("[%-12s%4d] ", c.getName(), c.getVotes());
		}
		out.println();
	}
}
