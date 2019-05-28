package your.name.election.ejbs;

import java.util.List;

import javax.ejb.Local;

import election.entities.Candidate;
import election.exceptions.DataInputException;
import election.exceptions.EntityNotFoundException;

@Local
public interface CandidateManagerLocal {
	
	public Candidate getCandidate(String cid) throws EntityNotFoundException;

	public Candidate updateCandidate(Candidate c) throws EntityNotFoundException, DataInputException;

	public List<Candidate> getCandidates();
}
