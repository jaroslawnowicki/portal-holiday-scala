package models;

import javax.persistence.GeneratedValue;
import javax.persistence.*;

@Entity
@Table(name="pages")
public class Pages implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6196896587452960036L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pageId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="url")
	private String url;
	
	@Column(name="access_for_role")
	private String accessForRole;

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer page_id) {
		this.pageId = page_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAccessForRole() {
		return accessForRole;
	}

	public void setAccessForRole(String accessForRole) {
		this.accessForRole = accessForRole;
	}
	
	
}
