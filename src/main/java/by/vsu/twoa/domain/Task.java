package by.vsu.twoa.domain;

import by.vsu.twoa.geometry.Quadrilateral;
import by.vsu.twoa.geometry.QuadrilateralType;

import java.util.Date;

public class Task extends Entity {
	private User owner;
	private String name;
	private Date created;
	private Quadrilateral quadrilateral;
	private QuadrilateralType quadrilateralType;

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Quadrilateral getQuadrilateral() {
		return quadrilateral;
	}

	public void setQuadrilateral(Quadrilateral quadrilateral) {
		this.quadrilateral = quadrilateral;
	}

	public QuadrilateralType getQuadrilateralType() {
		return quadrilateralType;
	}

	public void setQuadrilateralType(QuadrilateralType quadrilateralType) {
		this.quadrilateralType = quadrilateralType;
	}
}
