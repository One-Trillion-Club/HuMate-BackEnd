package com.otclub.humate.domain.member.controller;

import com.otclub.humate.common.annotation.MemberId;
import com.otclub.humate.common.dto.CommonResponseDTO;
import com.otclub.humate.domain.member.dto.MateDetailResponseDTO;
import com.otclub.humate.domain.member.dto.ModifyProfileRequestDTO;
import com.otclub.humate.domain.member.dto.ProfileResponseDTO;
import com.otclub.humate.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 회원 컨트롤러
 * @author 조영욱
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	조영욱        최초 생성
 * 2024.08.04   조영욱        마이페이지 메소드 추가
 * </pre>
 */
@RestController
@RequestMapping("/members")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    /**
     * 닉네임 중복 체크
     *
     * @author 조영욱
     * @param nickname 중복 확인 할 닉네임
     */
    @GetMapping("/check-nickname")
    public ResponseEntity<CommonResponseDTO> checkDuplicatedNickname(
            @RequestParam("nickname") String nickname) {
        return service.checkAvailableNickname(nickname) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "사용 가능한 닉네임입니다.")) :
                ResponseEntity.ok(new CommonResponseDTO(false, "이미 사용중인 닉네임입니다."));

    }

    /**
     * 내 정보 조회
     *
     * @author 조영욱
     */
    @GetMapping("/profile")
    public ResponseEntity<ProfileResponseDTO> getMyProfile(
            @MemberId String memberId) {
        return ResponseEntity.ok(service.getMemberProfile(memberId));
    }

    /**
     * 내 정보 수정
     *
     * @author 조영욱
     */
    @PutMapping(value = "/profile", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<CommonResponseDTO> modifyMyProfile(
            @RequestPart("modifyProfileRequestDTO") ModifyProfileRequestDTO dto,
            @RequestPart(value="image", required=false) MultipartFile image,
            @MemberId String memberId) {
        return service.modifyMyProfile(dto, image, memberId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "수정에 성공하였습니다.")) :
                ResponseEntity.ok(new CommonResponseDTO(false, "수정에 실패하였습니다."));
    }

    /**
     * 내 메이트 목록 조회
     *
     * @author 조영욱
     */
    @GetMapping("/my-mates")
    public ResponseEntity<List<MateDetailResponseDTO>> getMyMates(
            @MemberId String memberId) {
        return ResponseEntity.ok(service.getMyMates(memberId));
    }

    /**
     * 상대방 프로필 조회
     *
     * @author 조영욱
     * @param memberId 회원 ID
     */
    @GetMapping("/{memberId}")
    public ResponseEntity<ProfileResponseDTO> getSpecificMemberProfile(
            @PathVariable("memberId") String memberId){
        return ResponseEntity.ok(service.getMemberProfile(memberId));
    }
}
