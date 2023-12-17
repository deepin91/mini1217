package mini1217.service;

import java.util.List;

import mini1217.dto.Mini1217Dto;

public interface Mini1217Service {

	public List<Mini1217Dto> selectWorkerList() throws Exception;
	public int insertWorker(Mini1217Dto mini1217Dto)throws Exception;
	//작성하는 페이지니까 void로 해서 return값이 없는 게 맞음
	//workerDto에 있는 형식으로 입력한다는 뜻 이려나. 아니면 가져오겠다? 저 내용을? 아니면 저기에 저렇게 넣겠다?
	public Mini1217Dto selectWorkerDetail(int workerIdx)throws Exception;
	public int updateWorker(Mini1217Dto mini1217Dto)throws Exception;
	public int deleteWorker(int workerIdx)throws Exception;
	
}
