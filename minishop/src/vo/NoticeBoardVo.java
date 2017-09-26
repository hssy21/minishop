package vo;

public class NoticeBoardVo {
	
	int noticeIdx;
	String noticeTitle;
	String noticeContent;
	String noticeWriter;
	String noticeDate;
	int noticeHit;
	int noticeAvailable;
	
	public int getNoticeIdx() {
		return noticeIdx;
	}
	public void setNoticeIdx(int noticeIdx) {
		this.noticeIdx = noticeIdx;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public String getNoticeWriter() {
		return noticeWriter;
	}
	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}
	public String getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}
	public int getNoticeHit() {
		return noticeHit;
	}
	public void setNoticeHit(int noticeHit) {
		this.noticeHit = noticeHit;
	}
	public int getNoticeAvailable() {
		return noticeAvailable;
	}
	public void setNoticeAvailable(int noticeAvailable) {
		this.noticeAvailable = noticeAvailable;
	}
	
	@Override
	public String toString() {
		return "NoticeBoardVo [noticeIdx=" + noticeIdx + ", noticeTitle=" + noticeTitle + ", noticeContent="
				+ noticeContent + ", noticeWriter=" + noticeWriter + ", noticeDate=" + noticeDate + ", noticeHit="
				+ noticeHit + ", noticeAvailable=" + noticeAvailable + "]";
	}
	
}

/*CREATE TABLE NOTICE_BOARD(
		 NOTICE_IDX NUMBER(10),
		 NOTICE_TITLE VARCHAR2(200),
		 NOTICE_CONTENT VARCHAR(3000),
		 NOTICE_WRITER VARCHAR2(20),
		 NOTICE_DATE DATE,
		 NOTICE_HIT NUMBER(10),
		 NOTICE_AVAILABLE NUMBER(2),
		 CONSTRAINT NOTICE_NOTICE_ID_PK PRIMARY KEY (NOTICE_IDX)
		)*/