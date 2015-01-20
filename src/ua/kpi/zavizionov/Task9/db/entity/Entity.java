package ua.kpi.zavizionov.Task9.db.entity;


import java.io.Serializable;

/**
 * Root of all entities which have identifier field.
 * 
 * @author D.Kolesnikov
 * 
 */
public abstract class Entity implements Serializable {

	private static final long serialVersionUID = 8466257860808346236L;

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
