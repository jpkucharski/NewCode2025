package jpk.cqrs.repository.write;

import jpk.cqrs.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for write operations on {Member} entities in a master database
 */
public interface MemberWriteRepository extends JpaRepository<Member, Long> {
}
