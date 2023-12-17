package mini1217.dto;

import lombok.Data;

@Data
public class Mini1217Dto {
	private int workerIdx; //직원 인덱스
	private int workerNumber; //사번
	private String workerName;      //이름
	private String workerPhonenumber; //전화번호
	private String workerPosition; //직급
	private String workerEmail; //이메일
	private boolean deleted_yn ; //삭제 여부
}
