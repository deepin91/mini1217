package mini1217.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import mini1217.dto.Mini1217Dto;
import mini1217.service.Mini1217Service;

@Slf4j
@RestController
@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Mini1217Controller {

	@Autowired
	private Mini1217Service mini1217Service;

//	@GetMapping("/worker")
//	@CrossOrigin(origins="*", allowedHeaders="*")
//	public String openWorkerList() throws Exception {
////		ModelAndView mv = new ModelAndView("/worker/workerList");
//
//		List<WorkerDto> list = workerService.selectWorkerList();
////		mv.addObject("list", list);
//
////		return mv;
//		return "/worker";
//	}

	@GetMapping("/worker")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<List<Mini1217Dto>> selectWorkerList() throws Exception { //
		List<Mini1217Dto> list = mini1217Service.selectWorkerList();
		if (list != null && list.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

//	 글쓰기 페이지에 대한 요청을 처리
//	@GetMapping("/worker/write")
//	public String openWorkerWrite() throws Exception {
//		return "/worker/workerWrite";
//	}

	// 글쓰기 페이지

	@ApiOperation(value = "게시물 등록", notes = "요청 파라미터로 전달된 제목과 내용을 저장합니다.")
	@PostMapping("/worker/write")
	public ResponseEntity<Map<String, Object>> insertWorker(
//			@Parameter(description="게시물 정보를 담고 있는 객체", required=true)
			@RequestBody Mini1217Dto mini1217Dto) throws Exception {
		int insertedCount = 0;
		try {
			insertedCount = mini1217Service.insertWorker(mini1217Dto);
			if (insertedCount > 0) {
				Map<String, Object> result = new HashMap<>();
				result.put("message", "정상적으로 등록되었습니다.");
				result.put("count", insertedCount);
				result.put("workerIdx", mini1217Dto.getWorkerIdx());
				return ResponseEntity.status(HttpStatus.OK).body(result);
			} else {
				Map<String, Object> result = new HashMap<>();
				result.put("message", "등록된 내용이 없습니다.");
				result.put("count", insertedCount);
				return ResponseEntity.status(HttpStatus.OK).body(result);
			}
		} catch (Exception e) {
			Map<String, Object> result = new HashMap<>();
			result.put("message", "등록 중 오류가 발생했습니다.");
			result.put("count", insertedCount);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
		}
	}
 
//	@ApiOperation(value="게시물 등록", notes="요청 파라미터로 전달된 제목과 내용을 저장합니다.")
//	@PostMapping("/worker/write")
//	public ResponseEntity<Map<String,Object>> insertWorker(
//			@Parameter(description="게시물 정보를 담고 있는 객체", required=true)
//			@RequestBody WorkerDto workerDto) throws Exception {
//		int insertedCount = 0;
//		try {
//			insertedCount = workerService.insertWorker(workerDto);
//			System.out.println(">>>>>>>>>>> " + insertedCount);
//		}catch(Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("등록오류");
//		}
//		
//		return ResponseEntity.status(HttpStatus.OK).body("정상처리");
////		return "redirect:/worker/openWorkerList.do";
//	}

	/* 글쓰기 페이지 - 글쓰기 페이지만 할건데 리턴이 필요한가? */
	// ㄴ그냥 작성한 다음 작성한 내용이 그대로 보이도록 하기위해 return문 쓰는 듯?
//	@PostMapping("/worker/write")
//	public String insertWorker(WorkerDto workerDto) throws Exception{
//		workerService.insertWorker(workerDto);
//		return "redirect:/worker";
//	}

	@GetMapping("/worker/detail/{workerIdx}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<Mini1217Dto> openWorkerDetail(@PathVariable("workerIdx") int workerIdx) throws Exception {

		Mini1217Dto mini1217Dto = mini1217Dto = mini1217Service.selectWorkerDetail(workerIdx);


		if (mini1217Dto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(mini1217Dto);
		}
	}

//	/* 공지 상세 */
//	@GetMapping("/api/notice/detail/{noticeIdx}")
//	public ResponseEntity<NoticeDto> noticeDetail(@PathVariable("noticeIdx") int noticeIdx) throws Exception {
//		NoticeDto noticeDto = jpaService.noticeDetail(noticeIdx);
//		if (noticeDto != null) {
//			return ResponseEntity.status(HttpStatus.OK).body(noticeDto);
//		} else {
//			return ResponseEntity.status(HttpStatus.OK).body(null);
//		}
//	}

//	@GetMapping("/worker/{workerIdx}")
//	public String selectWorkerDetail(@PathVariable("workerIdx")int workerIdx) throws Exception{
////		ModelAndView mv =  new ModelAndView("/worker/workerDetail");
//		
//		WorkerDto workerDto = workerService.selectWorkerDetail(workerIdx);
////		mv.addObject("worker", workerDto);
//		
//		return "/worker/{workerIdx}";
//	}
	// @PathVariable 과 @RequestParam의 차이
	// @PathVariable은 URI 경로에서 직접 값을 추출하는데 사용 , 일반적으로 값이 URI자체 내에 포함된 템플릿 기반에 사용
	// URL의 변수를 컨트롤러의 메서드 매개변수에 매핑 가능 ex) openWorkerDetail

	// @RequestParam URL에서 쿼리 매개변수 또는 요청 매개변수를 추출하는데 사용
	// 쿼리 매개변수는 일반적으로 URL의 물음표( ?) 뒤에 추가됩니다.
	// "/search?query=term"일반적으로 는 요청 매개변수와 같이 쿼리 문자열의 일부로 전송된 query요청 매개변수를 처리하는 데
	// 사용됩니다.

//	@RequestMapping("/worker/updateWorker.do")
//	public String updateWorker(WorkerDto workerDto) throws Exception {
//		workerService.updateWorker(workerDto);
//		return "redirect:/worker/openWorkerList.do";
//	}

	/* 글 수정 기능 - 위와 비교 */
	@PutMapping("/worker/{workerIdx}")
	public ResponseEntity<Integer> updateWorker(@PathVariable("workerIdx") int workerIdx,
			@RequestBody Mini1217Dto mini1217Dto) throws Exception {
//		System.out.println("호출----");
//		WorkerDto detail = workerService.selectWorkerDetail(workerIdx);
//		workerDto.getWorkerIdx();
		mini1217Dto.setWorkerIdx(workerIdx);
		int updatedCount = mini1217Service.updateWorker(mini1217Dto);
		if (updatedCount != 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updatedCount);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(updatedCount);
		}
//		return workerService.updateWorker(workerDto);
//		return "redirect:/worker";
	}

	/*
	 * 안되던 이유.. 1. 컨트롤러에 idx 값 set 해주는게 맞았고 2. 쿼리에서 set idx = {idx} 이 부분 빼주고 3.
	 * postman에 idx값을 또 입력 넣어줘서 뺐어요
	 */

//	@RequestMapping("/worker/deleteWorker.do")
//	public String deleteWorker(WorkerDto workerDto) throws Exception{
//		workerService.deleteService(workerDto.getWorkerIdx());
//		return "redirect:/worker/openWorkerList.do";
//	}

	/* 글 삭제 기능 */
	@DeleteMapping("/worker/{workerIdx}")
	public ResponseEntity<Integer> deleteWorker(@PathVariable("workerIdx") int workerIdx) throws Exception {
		int deletedCount = mini1217Service.deleteWorker(workerIdx);
		if (deletedCount != 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(deletedCount);
//			workerService.deleteWorker(workerIdx);	
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(deletedCount);
		}
//		workerService.deleteService(workerIdx);
	}
	// 맨 첫 줄은 /worker/{workerIdx} 경로에서 해당 값을 직접 추출하여
	// workerService에서 deleteService 메서드의 workerDto형식으로 된 workerIdx값을 가져옴?
	// workerService에서 workerDto의 workerIdx값을 가져와 (혹은 workerDto 타입의 workerIdx 값을 가져와
	// deleteService 메서드를 실행시킴 = 즉, 삭제하는 것
	// 그런 후 worker페이지를 리다이렉트 시킴
	// return 과 redirect의 차이는 return은 서버 내부에서 해당 뷰 값을 찾아 반환
	// redirect:는 /로 다시 돌아가는 것
	// 아마 더 효율적인 것은 redirect인 듯

	// ----------------------------------DB 저장, 조회, 수정까지는 가능 but, 삭제는 ㄴㄴ임 why?
	// ------------------------------------
}
