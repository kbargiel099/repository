package com.auctions.system.module.file_converter.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.auctions.system.module.file_converter.dao.ConverterDAO;

@Repository("converterDAO")
public class ConverterDAOImpl implements ConverterDAO {
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public boolean createVideoReference(long auctionId, String videoName){
        
        try {
    		PreparedStatement pst = dataSource.getConnection().prepareStatement("UPDATE sys.auction SET video=? WHERE id=?",
    				PreparedStatement.RETURN_GENERATED_KEYS);
	        pst.setString(1, videoName);
    		pst.setLong(2, auctionId);
	        pst.executeUpdate();
            pst.close();
            
            return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return false;
	}

}
