package com.example.kintai.syain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BumonMapper implements RowMapper<Bumon> {
	public Bumon mapRow(ResultSet rs, int rowNum) throws SQLException {
		var msg = new Bumon();
		msg.setCode(rs.getString("bumoncode"));
		msg.setMeisyo(rs.getString("bumonmeisyo"));
		msg.setRyakusyo(rs.getString("bumonryakusyo"));

		return msg;
	}
}
