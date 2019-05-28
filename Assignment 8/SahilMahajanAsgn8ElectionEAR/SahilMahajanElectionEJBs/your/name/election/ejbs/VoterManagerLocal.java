package your.name.election.ejbs;

import javax.ejb.Local;

import election.entities.Voter;
import election.exceptions.DataInputException;
import election.exceptions.EntityNotFoundException;

@Local
public interface VoterManagerLocal {
	
	public Voter getVoter(int vid) throws EntityNotFoundException;

	public Voter updateVoter(Voter v) throws EntityNotFoundException, DataInputException;

}
