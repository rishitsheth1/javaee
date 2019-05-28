package your.name.election.ejbs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import election.entities.Voter;
import election.exceptions.DataInputException;
import election.exceptions.DuplicateEntityException;
import election.exceptions.EntityNotFoundException;

/**
 * Session Bean implementation class VoterManager
 */
@Stateless
public class VoterManager implements VoterManagerLocal {
	@PersistenceContext (name = "YourNameELectionEntites")
	private EntityManager em;

	public VoterManager() {
		super();
	}
	
    public Voter getVoter(int vid) throws EntityNotFoundException {
		Voter voter = em.find( Voter.class, vid );
		if ( voter == null ) {
			throw new EntityNotFoundException("No voter has id " + vid );
		}
		return voter;
    }
 
	    public Voter addVoter(Voter v) throws DuplicateEntityException {
	    	if ( v == null ) {
	    		throw new DuplicateEntityException( "Attempt to add null voter");
	    	}
	    	int vid = v.getVid();
			Voter voter = em.find( Voter.class, vid );
			if ( voter != null ) {
				throw new DuplicateEntityException( 
			              "Attempt to create second voter with id:" + vid );
			}
			em.persist(v);
			// return entity stored in data base to get derived fields
			try {
				return getVoter(vid);				
			} catch (EntityNotFoundException e) {
				throw new DuplicateEntityException( "Database inconsistency inserting voter: " + vid);
			}
	    }
	    
	    public Voter deleteVoter(String vid) throws EntityNotFoundException {
			Voter voter = em.find( Voter.class, vid );
			if ( voter == null ) {
				throw new EntityNotFoundException( 
			              "Attempt to delete unknown voter:" + vid );
			}
			em.remove(voter);
			return voter;
	    }
	    
	    public Voter updateVoter(Voter v) throws EntityNotFoundException, DataInputException {
	    	if ( v == null ) {
	    		throw new EntityNotFoundException( "Attempt to update null voter");
	    	}
	    	Voter voter = em.find( Voter.class, v.getVid() );
	    	if ( voter == null ) {
	    		throw new EntityNotFoundException( "No voter found with ID " + v.getVid() );
	    	}
	    	voter.setVoterName( v.getVoterName() );
	    	voter.setPassword( v.getPassword() );
	    	voter.setVoted( v.isVoted() );
	    	em.merge( voter );
	    	return voter;
	    }

}
