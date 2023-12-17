package mini1217.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import mini1217.dto.Mini1217Dto;

@Mapper
public interface Mini1217Mapper {
	List<Mini1217Dto> selectWorkerList() throws Exception;
	int insertWorker(Mini1217Dto mini1217Dto)throws Exception;
	Mini1217Dto selectWorkerDetail(int workerIdx)throws Exception;
	int updateWorker(Mini1217Dto mini1217Dto)throws Exception;
	int deleteWorker(int workerIdx)throws Exception;
}
