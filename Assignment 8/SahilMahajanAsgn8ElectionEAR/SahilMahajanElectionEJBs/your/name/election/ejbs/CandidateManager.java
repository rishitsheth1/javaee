package your.name.election.ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import election.entities.Candidate;
import election.exceptions.DataInputException;
import election.exceptions.DuplicateEntityException;
import election.exceptions.EntityNotFoundException;

/**
 * Session Bean implementation class CandidateManager
 */
@Stateless
public class CandidateManager implements CandidateManagerLocal {
	@PersistenceContext (name = "YourNameElectionEntities")
	private EntityManager em;

	public CandidateManager() {
		super();
	}

	public Candidate getCandidate(String cid) throws EntityNotFoundException {
		Candidate candidate = em.find(Candidate.class, cid);
		if (candidate == null) {
			throw new EntityNotFoundException("No candidate has id " + cid);
		}
		return candidate;
	}

	public Candidate addCandidate(Candidate c) throws DuplicateEntityException {
		if ( c == null ) {
			throw new DuplicateEntityException("Attempt to addd null candiddate");
		}
		String cid = c.getCid();
		Candidate candidate = em.find(Candidate.class, cid);
		if (candidate != null) {
			throw new DuplicateEntityException("Attempt to create second candidate with id:" + cid);
		}
		em.persist(c);
		// return entity stored in data base to get derived fields
		try {
			return getCandidate(cid);
		} catch (EntityNotFoundException e) {
			throw new DuplicateEntityException("Database inconsistency inserting candidate: " + cid);
		}
	}

	public Candidate deleteCandidate(String cid) throws EntityNotFoundException {
		Candidate candidate = em.find( Candidate.class, cid );
		if ( candidate == null ) {
			throw new EntityNotFoundException("No candidate has id " + cid );
		}
		em.remove( candidate );
		return candidate;
    }

	public Candidate updateCandidate(Candidate c) throws EntityNotFoundException, DataInputException {
		if ( c == null ) {
			throw new EntityNotFoundException("Attempt to update null candidate");
		}
		Candidate candidate = em.find(Candidate.class, c.getCid());
		if (candidate == null) {
			throw new EntityNotFoundException("No candidate found with ID " + c.getCid());
		}
		candidate.setName(c.getName());
		candidate.setVotes(c.getVotes());
		em.merge(candidate);
		return candidate;
	}

	// returns candidate information used to generate buttons on the ballot form

	public List<Candidate> getCandidates() {
		TypedQuery<Candidate> tq = em.createNamedQuery("Candidate.findAll", Candidate.class);
		List<Candidate> candidates = tq.getResultList();
		return candidates;
	}

}
