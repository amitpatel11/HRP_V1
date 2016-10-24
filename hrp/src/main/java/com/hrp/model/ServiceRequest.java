package com.hrp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="service_request")
public class ServiceRequest {
	
	
/*	Table: service_request
	--------------------------

	Column Information
	----------------------

	Field         Type          Collation          Null    Key     Default              Extra           Privileges                       Comment  
	------------  ------------  -----------------  ------  ------  -------------------  --------------  -------------------------------  ---------
	id            int(10)       (NULL)             NO      PRI     (NULL)               auto_increment  select,insert,update,references           
	user_id       int(10)       (NULL)             NO      MUL     (NULL)                               select,insert,update,references           
	service_id    int(10)       (NULL)             NO      MUL     (NULL)                               select,insert,update,references           
	description   varchar(500)  latin1_swedish_ci  YES             (NULL)                               select,insert,update,references           
	is_processed  tinyint(1)    (NULL)             NO              0                                    select,insert,update,references           
	created_date  timestamp     (NULL)             NO              0000-00-00 00:00:00                  select,insert,update,references           
	updated_date  timestamp     (NULL)             NO              CURRENT_TIMESTAMP                    select,insert,update,references           
	deleted_yn    tinyint(1)    (NULL)    */
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="service_id")
	private Long serviceId;
	
	@Column(name="description")
	private Long description;
	
	@Column(name="is_processed")
	private Long isProcessed;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "service_request_id")
	@JsonManagedReference
	private List<ProviderServices> providerServices;
	
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Long getDescription() {
		return description;
	}

	public void setDescription(Long description) {
		this.description = description;
	}

	public Long getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(Long isProcessed) {
		this.isProcessed = isProcessed;
	}

	public List<ProviderServices> getProviderServices() {
		return providerServices;
	}

	public void setProviderServices(List<ProviderServices> providerServices) {
		this.providerServices = providerServices;
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
