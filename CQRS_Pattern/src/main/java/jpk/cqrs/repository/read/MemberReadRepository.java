package jpk.cqrs.repository.read;

import jpk.cqrs.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repository interface for read operations on {Member} entities in a slave database
 */
public interface MemberReadRepository extends JpaRepository<Member, Long> {
}
