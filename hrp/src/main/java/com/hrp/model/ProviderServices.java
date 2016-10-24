package com.hrp.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="provider_services")
public class ProviderServices {

/*	Table: provider_services
	----------------------------

	Column Information
	----------------------

	Field               Type        Collation  Null    Key     Default              Extra           Privileges                       Comment  
	------------------  ----------  ---------  ------  ------  -------------------  --------------  -------------------------------  ---------
	id                  int(10)     (NULL)     NO      PRI     (NULL)               auto_increment  select,insert,update,references           
	service_request_id  int(10)     (NULL)     NO      MUL     (NULL)                               select,insert,update,references           
	user_id             int(10)     (NULL)     NO      MUL     (NULL)                               select,insert,update,references           
	response_status     tinyint(1)  (NULL)     NO              0                                    select,insert,update,references           
	created_date        timestamp   (NULL)     NO              0000-00-00 00:00:00                  select,insert,update,references           
	updated_date        timestamp   (NULL)     NO              CURRENT_TIMESTAMP                    select,insert,update,references           
	deleted_yn          tinyint(1)  (NULL)*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "service_request_id" ,insertable=false,updatable=false)
	@JsonBackReference
	private ServiceRequest serviceRequest;
	
	@Column(name="service_request_id")
	private Long serviceRequestId;
	
	@Column(name="user_id")
	private Long userId;

	@Column(name = "response_status")
	private Boolean responseStatus;
	
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "deleted_yn")
	private Boolean deletedYn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}

	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	public Long getServiceRequestId() {
		return serviceRequestId;
	}

	public void setServiceRequestId(Long serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(Boolean responseStatus) {
		this.responseStatus = responseStatus;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Boolean getDeletedYn() {
		return deletedYn;
	}

	public void setDeletedYn(Boolean deletedYn) {
		this.deletedYn = deletedYn;
	}

	
	
	
}
