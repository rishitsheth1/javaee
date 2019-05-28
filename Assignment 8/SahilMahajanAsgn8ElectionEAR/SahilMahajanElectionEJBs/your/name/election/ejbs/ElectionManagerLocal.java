package your.name.election.ejbs;

import javax.ejb.Local;

import election.entities.Candidate;
import election.entities.Voter;
import election.exceptions.DataInputException;
import election.exceptions.EntityNotFoundException;

@Local
public interface ElectionManagerLocal {

	public Voter getAuthenticatedVoter (int vid, String password ) 
			           throws EntityNotFoundException, DataInputException;	
	
	public void castBallot(Voter voter, Candidate candidate ) 
			   throws EntityNotFoundException, DataInputException;
}
