package jpk.cqrs.service;

import jpk.cqrs.dto.MemberDTO;
import jpk.cqrs.model.Member;
import jpk.cqrs.repository.read.MemberReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for retrieving {Member} entity's.
 */
@Service
public class MemberReadService {
    @Autowired
    private MemberReadRepository memberReadRepository;

    /**
     * Handles  request to database to retrieve all members {Member} records and converts them to list of {MemberDTO}.
     *
     * @return {List<MemberDTO>} object
     */
    public List<MemberDTO> getAllMembers() {
        List<Member> members = memberReadRepository.findAll();
        return members.stream()
                .map(member -> new MemberDTO(member.getId(), member.getFirstname()))
                .collect(Collectors.toList());
    }
}
