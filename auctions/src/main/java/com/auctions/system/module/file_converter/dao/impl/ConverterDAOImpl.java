package com.auctions.system.module.file_converter.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.auctions.system.module.file_converter.dao.ConverterDAO;

@Repository("converterDAO")
public class ConverterDAOImpl implements ConverterDAO {
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	@Override
	public boolean createVideoReference(long auctionId, String videoName){
        
        try {
    		PreparedStatement pst = dataSource.getConnection().prepareStatement("INSERT INTO auction_video(auctionid,name) VALUES(?,?)",
    				PreparedStatement.RETURN_GENERATED_KEYS);
	        pst.setLong(1, auctionId);
	        pst.setString(2, videoName);
	        pst.executeUpdate();
            pst.close();
            
            return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return false;
	}

}
