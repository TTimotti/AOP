package boot.demo.aop.application.rms.common.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {

	private int pageSize = 10;

	private int blockSize = 10;

	private int firstPageNo;

	private int prevPageNo;

	private int startPageNo;

	private int pageNo;

	private int endPageNo;

	private int nextPageNo;

	private int finalPageNo;

	private int totalCount;

	private int startRowNum;

	private int endRowNum;

	private int rnum;

	private String orderBy = "DESC";

	private String contentPage;

	public int getPageNo() {
		if (pageNo == 0) pageNo = 1;
		return pageNo;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.makePaging();
	}

	private void makePaging() {

		if (this.totalCount == 0) {
			this.setPageNo(1);
			return;
		}

		if (this.pageNo == 0)
			this.setPageNo(1);

		if (this.pageSize == 0)
			this.setPageSize(10);

		int finalPage = (totalCount + (pageSize - 1)) / pageSize;

		if (this.pageNo > finalPage)
			this.setPageNo(finalPage);

		if (this.pageNo < 0 || this.pageNo > finalPage)
			this.pageNo = 1;

		boolean isNowFirst = pageNo == 1;

		boolean isNowFinal = pageNo == finalPage;

		int startPage = ((pageNo - 1) / blockSize) * blockSize + 1;

		int endPage = startPage + blockSize - 1;

		if (endPage > finalPage) {
			endPage = finalPage;
		}

		this.setFirstPageNo(1);

		if (isNowFirst) {
			this.setPrevPageNo(1);
		} else {
			this.setPrevPageNo((Math.max((pageNo - 1), 1)));
		}

		this.setStartPageNo(startPage);
		this.setEndPageNo(endPage);

		if (isNowFinal) {
			this.setNextPageNo(finalPage);
		} else {
			this.setNextPageNo((Math.min((pageNo + 1), finalPage)));
		}
		this.setEndRowNum(pageNo * pageSize);
		this.setStartRowNum(endRowNum - (pageSize - 1));

		this.setFinalPageNo(finalPage);

	}

	@Override
	public String toString() {
		return "Pagination [firstPageNo=" + firstPageNo + ", prevPageNo=" + prevPageNo + ", startPageNo=" + startPageNo
				+ ", pageNo=" + pageNo + ", endPageNo=" + endPageNo + ", nextPageNo=" + nextPageNo + ", finalPageNo="
				+ finalPageNo + ", totalCount=" + totalCount + "]";
	}
	
}