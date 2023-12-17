package mini1217.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mini1217.dto.Mini1217Dto;
import mini1217.mapper.Mini1217Mapper;

@Service
//@Transactional
public class Mini1217ServiceImpl implements Mini1217Service {
	
	@Autowired
	private Mini1217Mapper mini1217Mapper;
	
	@Override
	public List<Mini1217Dto> selectWorkerList() throws Exception {
		return mini1217Mapper.selectWorkerList();
	}

	@Override
	public int insertWorker(Mini1217Dto mini1217Dto) throws Exception {
		return mini1217Mapper.insertWorker(mini1217Dto);
		
		
	}

	@Override
	public Mini1217Dto selectWorkerDetail(int workerIdx) throws Exception {
		return mini1217Mapper.selectWorkerDetail(workerIdx); //// 게시판 상세 내용을 조회
	}

	@Override
	public int updateWorker(Mini1217Dto mini1217Dto) throws Exception {
		return mini1217Mapper.updateWorker(mini1217Dto);
		
	}

	@Override
	public int deleteWorker(int workerIdx) throws Exception {
		return mini1217Mapper.deleteWorker(workerIdx);
		
	}
	
	

}
