package your.name.election.ejbs;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import election.entities.Candidate;
import election.entities.Voter;
import election.exceptions.DataInputException;
import election.exceptions.EntityNotFoundException;

/**
 * Session Bean implementation class ElectionManager
 */
@Stateless
public class ElectionManager implements ElectionManagerLocal {
	@EJB
	CandidateManagerLocal cm;
	@EJB
	VoterManagerLocal vm;
	@Resource
	private SessionContext context;
	
    public ElectionManager() {
    	super();
    }

    public Voter getAuthenticatedVoter (int vid, String password ) throws EntityNotFoundException, DataInputException {
    Voter voter = vm.getVoter( vid );
	if( ! voter.getPassword().equals( password) ) { 
		throw new EntityNotFoundException( "Voter " + vid + " password not authenticated");
	} 
	if ( voter.isVoted() ) {
		throw new DataInputException( "Voter " + vid + " has already voted");
	} 
	return voter; 
	}

	public void castBallot(Voter voter, Candidate candidate ) throws EntityNotFoundException, DataInputException {
		if ( voter == null || candidate == null ) {
			throw new DataInputException( "spoiled ballot - voter or candidate is null");
		}
		if ( voter.isVoted() ) {
			throw new DataInputException("Voter " + voter.getVid() + " attempt to voting again");
		}
		try {
			voter.setVoted(true);
			vm.updateVoter(voter);
			candidate.setVotes( candidate.getVotes() +1 );
			cm.updateCandidate(candidate);
		} catch ( Exception e) {
			context.setRollbackOnly();
			throw new DataInputException("Problem casting ballot: " + 
					e.getClass().getName() + ": " + e.getMessage() );
		}
	}
}
