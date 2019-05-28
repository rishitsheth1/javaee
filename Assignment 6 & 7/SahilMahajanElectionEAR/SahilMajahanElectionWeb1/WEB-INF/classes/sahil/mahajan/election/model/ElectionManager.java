package sahil.mahajan.election.model;

import java.io.PrintStream;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import sahil.mahajan.election.DataInputException.*;
import sahil.mahajan.election.entities.Candidate;
import sahil.mahajan.election.entities.Voter;
import sahil.mahajan.election.exceptions.ElectionException;

public class ElectionManager { 
	
    public ElectionManager() {
       super();
    }
    
    // user login: returns a Voter if user authenticated or null otherwise  
	public Voter authenticateVoter(int voterId, String password) throws ElectionException  {
		// complete this method to return either an authenticated Voter
		// or null
		EntityManagerFactory emf = EMFSupplier.getInstance().getEMF();
		EntityManager em = emf.createEntityManager();
		Voter voter = em.find(Voter.class, voterId);
		if(voter == null) {
			return null;
		}
		if(password.equals(voter.getPassword())) {
			return null;
		}
		//if voter has already voted voter cant login
		if(voter.hasVoted()) {
			throw new ElectionException("Voter [" + voterId + "] cannot vote twice");
		}
		return voter;
	}

	// user votes: set Voter.hasVoted to true or 1
	//  and 1 to count of votes for the candidate	
	public void castBallot(Voter v, Candidate c ) throws ElectionException, DataInputException {
	// complete this method process one ballot form
    // throw exceptions as appropriate
		EntityManagerFactory emf = EMFSupplier.getInstance().getEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			Voter voter = em.find(Voter.class, v.getVid());
			if(voter == null) {
				throw new ElectionException("No voter with id: " + v.getVid());
			}
			Candidate candidate = em.find(Candidate.class, c.getCid());
			if(candidate == null) {
				throw new ElectionException("No candidate with id: " + c.getCid());
			}
			int votes = candidate.getVotes();
			et.begin();
			voter.setVoted(true);
			
			candidate.setVotes(++votes);
			
			em.merge(voter);
			em.merge(candidate);
			et.commit();
		} catch(ElectionException el) {
			et.rollback();
			//throw new ElectionException(el.getMessage());
			throw new ElectionException("Unexpected Error");
		} finally {
			em.close();
		}
		return;
	}
	
	// for testing and debugging only: 
	// print current vote count for each candidate to the server console 	
	public void printVoteCount(PrintStream out) throws ElectionException {
	// complete this method
		CandidateManager cm = new CandidateManager();
		List<Candidate> list = cm.getCandidates();
		for(Candidate c : list) {
			out.println("Candidate " + c.getName() + " has " + c.getVotes() + "votes.");
		}
	}
}
