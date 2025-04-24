package jpk.cqrs.controller;

import jpk.cqrs.dto.MemberDTO;
import jpk.cqrs.model.Member;
import jpk.cqrs.service.MemberReadService;
import jpk.cqrs.service.MemberWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The {API_Controller} class handles HTTP requests.
 * Provides endpoints for: application status, inserting a new member
 * and getting a list of all members.
 */
@RestController
public class API_Controller {

    @Value("${app.version}")
    private String appVersion;

    @Autowired
    private MemberWriteService memberWriteService;

    @Autowired
    private MemberReadService memberReadService;

    /**
     * Handles HTTP GET requests to the /status endpoint.
     * @return A {ResponseEntity} containing the application version.
     */
    @GetMapping(path = "/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Version: " + appVersion);
    }

    /**
     * Handles HTTP POST requests to the /insert endpoint.
     * @param member The {Member} object to be inserted.
     * @return A {ResponseEntity} containing the inserted {Member} object.
     */
    @PostMapping(path = "/insert")
    public ResponseEntity<Member> insertMember(@RequestBody Member member) {
            memberWriteService.saveMember(member);
            return ResponseEntity.ok(member);
    }

    /**
     * Handles HTTP GET requests to the /members endpoint.
     * @return A {ResponseEntity} containing a list of {MemberDTO} objects.
     */
    @GetMapping(path = "/members")
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        List<MemberDTO> members = memberReadService.getAllMembers();
        return ResponseEntity.ok(members);
    }
}
