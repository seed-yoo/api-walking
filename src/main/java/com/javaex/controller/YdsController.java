package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.YdsService;
import com.javaex.util.JsonResult;
import com.javaex.vo.YdsVo;

@RestController
@RequestMapping("/api")
public class YdsController {

	@Autowired
	private YdsService ydsService;

	// 전체 갤러리 리스트 조회
	@GetMapping("/gallery")
	public JsonResult list() {
		System.out.println("YdsController.list()");
		List<YdsVo> gList = ydsService.exeAllList();
		System.out.println(gList);
		return JsonResult.success(gList);
	}
	
	// 로그인한 회원의 코스 목록 조회
    @GetMapping("/gallery/user/{userNo}/courses")
    public JsonResult listUserCourses(@PathVariable(value ="userNo") int userNo) {
        System.out.println("YdsController.listUserCourses()");
        List<YdsVo> userCourses = ydsService.exefindCoursesByUserNo(userNo);
        System.out.println(userCourses);
        return JsonResult.success(userCourses);
    }
    
    // 로그인한 회원의 특정 코스 소개 등록
    @PostMapping("/gallery/{userNo}/course/{courseNo}")
    public JsonResult introduceCourse(@PathVariable int userNo, @PathVariable int courseNo, @RequestBody YdsVo ydsVo) {
        System.out.println("YdsController.introduceCourse()");
        ydsVo.setUsers_no(userNo); // 사용자 번호 설정
        ydsVo.setCourse_no(courseNo); // 코스 번호 설정
        ydsService.saveCourseIntroduction(ydsVo);
        System.out.println(ydsVo);
        return JsonResult.success(ydsVo);
    }

    // 선택한 코스의 상세 정보 조회
    @GetMapping("/gallery/course/{courseName}")
    public JsonResult getCourseDetails(@PathVariable(value ="courseName") String courseName) {
        System.out.println("YdsController.getCourseDetails()");
        YdsVo courseInfo = ydsService.exefindCourseInfo(courseName);
        System.out.println("courseInfo      "+courseInfo);
        return JsonResult.success(courseInfo);
    }
	

	// 이미지 첨부

	/*@PostMapping("/galleryupload")
	public JsonResult galleryUpload(@RequestParam MultipartFile galleryfile) {
		System.out.println("YdsController.galleryUpload()");

		System.out.println(galleryfile.getOriginalFilename());
		String gallerySaveName = ydsService.exeSaveGalleryFile(galleryfile);

		return JsonResult.success(gallerySaveName);
		//return JsonResult.success(null);
	}*/



}
// 다솜이꺼
