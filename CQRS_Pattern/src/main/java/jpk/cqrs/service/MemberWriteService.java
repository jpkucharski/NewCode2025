package jpk.cqrs.service;

import jpk.cqrs.model.Member;
import jpk.cqrs.repository.write.MemberWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides services for saving {Member} entities to the database.
 */
@Service
public class MemberWriteService {

    @Autowired
    private MemberWriteRepository userWriteRepository;

    /**
     * Saves a provided {Member} object to master database.
     *
     * @param member {Member} object to save.
     * @return saved {Member} object.
     */
    public Member saveMember(Member member) {
        return userWriteRepository.save(member);
    }
}
