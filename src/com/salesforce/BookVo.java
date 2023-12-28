//package com.salesforce;
//
//public class BookVO {
//	
//	private int book_id;
//	private String book_title;
//	private String pubs;
//	private String pub_date;
//	private int author_id;
//	
//	public int getBook_id() {
//		return book_id;
//	}
//	public void setBook_id(int book_id) {
//		this.book_id = book_id;
//	}
//	public String getBook_title() {
//		return book_title;
//	}
//	public void setBook_title(String book_title) {
//		this.book_title = book_title;
//	}
//	public String getPubs() {
//		return pubs;
//	}
//	public void setPubs(String pubs) {
//		this.pubs = pubs;
//	}
//	public String getPub_date() {
//		return pub_date;
//	}
//	public void setPub_date(String pub_date) {
//		this.pub_date = pub_date;
//	}
//	public int getAuthor_id() {
//		return author_id;
//	}
//	public void setAuthor_id(int author_id) {
//		this.author_id = author_id;
//	}
//
//	@Override
//	public String toString() {
//		return "BookVO [book_id=" + book_id + ", book_title=" + book_title + ", pubs=" + pubs + ", pub_date=" + pub_date
//				+ ", author_id=" + author_id + "]";
//	}
//	
//	public BookVO(String book_title, String pubs, String pub_date, int author_id) {
//		super();
//		this.book_id = book_id;
//		this.book_title = book_title;
//		this.pubs = pubs;
//		this.pub_date = pub_date;
//		this.author_id = author_id;
//	}	
//
//}

package com.salesforce;

public class BookVo {
  private int book_id;
  private String title;
  private String pubs;
  private String pub_date;
  private int author_id;
  
  private String author_name;
  
  public String getAuthor_name() {
    return author_name;
  }

  public void setAuthor_name(String author_name) {
    this.author_name = author_name;
  }

  public int getBook_id() {
    return book_id;
  }

  public void setBook_id(int book_id) {
    this.book_id = book_id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPubs() {
    return pubs;
  }

  public void setPubs(String pubs) {
    this.pubs = pubs;
  }

  public String getPub_date() {
    return pub_date;
  }

  public void setPub_date(String pub_date) {
    this.pub_date = pub_date;
  }

  public int getAuthor_id() {
    return author_id;
  }

  public void setAuthor_id(int author_id) {
    this.author_id = author_id;
  }

  @Override
  public String toString() {
    return "BookVo [book_id=" + book_id + ", title=" + title + ", pubs=" + pubs + ", pub_date=" + pub_date
        + ", author_id=" + author_id + ", author_name=" + author_name + "]";
  }

  /**
   * @param bookId
   * @param title
   * @param pubs
   * @param pubDate
   */
  public BookVo(int bookId, String title, String pubs, String pubDate) {
    super();
    this.book_id = bookId;
    this.title = title;
    this.pubs = pubs;
    this.pub_date = pubDate;
  }

  /**
   * 
   */
  public BookVo() {
    super();
  }

  /**
   * @param book_id
   * @param title
   * @param pubs
   * @param pub_date
   * @param author_id
   */
  public BookVo(int book_id, String title, String pubs, String pub_date, int author_id) {
    super();
    this.book_id = book_id;
    this.title = title;
    this.pubs = pubs;
    this.pub_date = pub_date;
    this.author_id = author_id;
  }
  
  /**
   * @param book_id
   * @param title
   * @param pubs
   * @param pub_date
   * @param author_name
   */
  public BookVo(int book_id, String title, String pubs, String pub_date, String author_name) {
    super();
    this.book_id = book_id;
    this.title = title;
    this.pubs = pubs;
    this.pub_date = pub_date;
    this.author_name = author_name;
  }

 
}