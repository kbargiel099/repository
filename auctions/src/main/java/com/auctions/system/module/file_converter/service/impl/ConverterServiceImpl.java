package com.auctions.system.module.file_converter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctions.system.module.file_converter.dao.ConverterDAO;
import com.auctions.system.module.file_converter.service.ConverterService;

@Service("converterService")
public class ConverterServiceImpl implements ConverterService {
	
	@Autowired
	ConverterDAO dataSource;

	@Override
	public boolean createVideoReference(long auctionId, String videoName) {
		return dataSource.createVideoReference(auctionId, videoName);
	}
	
}
