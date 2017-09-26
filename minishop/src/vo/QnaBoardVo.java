package vo;

public class QnaBoardVo {

	int qnaIdx;
	String qnaTitle;
	String qnaContent;
	String qnaWriter;
	String qnaDate;
	int qnaHit;
	int qnaAvailable;
	int qnaGroup;
	int qnaStep;
	int qnaIndent;
	
	public int getQnaIdx() {
		return qnaIdx;
	}
	public void setQnaIdx(int qnaIdx) {
		this.qnaIdx = qnaIdx;
	}
	public String getQnaTitle() {
		return qnaTitle;
	}
	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}
	public String getQnaContent() {
		return qnaContent;
	}
	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}
	public String getQnaWriter() {
		return qnaWriter;
	}
	public void setQnaWriter(String qnaWriter) {
		this.qnaWriter = qnaWriter;
	}
	public String getQnaDate() {
		return qnaDate;
	}
	public void setQnaDate(String qnaDate) {
		this.qnaDate = qnaDate;
	}
	public int getQnaHit() {
		return qnaHit;
	}
	public void setQnaHit(int qnaHit) {
		this.qnaHit = qnaHit;
	}
	public int getQnaAvailable() {
		return qnaAvailable;
	}
	public void setQnaAvailable(int qnaAvailable) {
		this.qnaAvailable = qnaAvailable;
	}
	public int getQnaGroup() {
		return qnaGroup;
	}
	public void setQnaGroup(int qnaGroup) {
		this.qnaGroup = qnaGroup;
	}
	public int getQnaStep() {
		return qnaStep;
	}
	public void setQnaStep(int qnaStep) {
		this.qnaStep = qnaStep;
	}
	public int getQnaIndent() {
		return qnaIndent;
	}
	public void setQnaIndent(int qnaIndent) {
		this.qnaIndent = qnaIndent;
	}
	
	@Override
	public String toString() {
		return "QnaBoardVo [qnaIdx=" + qnaIdx + ", qnaTitle=" + qnaTitle + ", qnaContent=" + qnaContent + ", qnaWriter="
				+ qnaWriter + ", qnaDate=" + qnaDate + ", qnaHit=" + qnaHit + ", qnaAvailable=" + qnaAvailable
				+ ", qnaGroup=" + qnaGroup + ", qnaStep=" + qnaStep + ", qnaIndent=" + qnaIndent + "]";
	}

}

/*CREATE TABLE QNA_BOARD(
		 QNA_IDX NUMBER(10),
		 QNA_TITLE VARCHAR2(200),
		 QNA_CONTENT VARCHAR2(3000),
		 QNA_WRITER VARCHAR2(20),
		 QNA_DATE DATE,
		 QNA_HIT NUMBER(10),
		 QNA_AVAILABLE NUMBER(2),
		 QNA_GROUP NUMBER(10),
		 QNA_STEP NUMBER(10),
		 QNA_INDENT NUMBER(10),
		 CONSTRAINT QNA_QNA_ID_PK PRIMARY KEY (QNA_IDX)
		)*/