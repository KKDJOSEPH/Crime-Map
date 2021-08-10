package crimeMap.model;

import java.util.Date;

public class Comments {
  protected int commentId;
  protected Date created;
  protected String content;
  protected Reports reportId;
  protected Users userName;
  
  public Comments(int commentId, Date created, String content, Reports reportId, Users userName) {
    this.commentId = commentId;
    this.created = created;
    this.content = content;
    this.reportId = reportId;
    this.userName = userName;
  }
  
  public Comments(Date created, String content) {
	  this.created = created;
	  this.content = content;
  }

  public int getCommentId() {
    return commentId;
  }

  public void setCommentId(int commentId) {
    this.commentId = commentId;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Reports getReportId() {
    return reportId;
  }

  public void setReportId(Reports reportId) {
    this.reportId = reportId;
  }

  public Users getUserName() {
    return userName;
  }

  public void setUserName(Users userName) {
    this.userName = userName;
  }
}
