package com.hrp.service;

import java.util.List;

import com.hrp.model.Qualification;

public interface QualificationService {

	List<Qualification> getAllActiveQualifications();

	List<Qualification> getAllQualifications();
}
