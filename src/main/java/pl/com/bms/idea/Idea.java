package pl.com.bms.idea;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Idea {

    @Id
    @GeneratedValue
    private String id;
    private String author;
    private String title;
    private String description;
    private boolean approved;
    private int upVoteCount;
    private int downVoteCount;

    public Idea(String author, String title, String description) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.approved = false;
        this.upVoteCount = 0;
        this.downVoteCount = 0;
    }

    public Idea() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void approve() {
        this.approved = true;
    }

	public boolean isApproved() {
		return approved;
	}

    public int getUpVoteCount() {
        return upVoteCount;
    }

    public void setUpVoteCount(int upVoteCount) {
        this.upVoteCount = upVoteCount;
    }

    public int getDownVoteCount() {
        return downVoteCount;
    }

    public void setDownVoteCount(int downVoteCount) {
        this.downVoteCount = downVoteCount;
    }
}
