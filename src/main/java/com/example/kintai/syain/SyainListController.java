package com.example.kintai.syain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SyainListController {

    @Autowired
    JdbcTemplate jdbcTemplate;

	// メンバーリスト表示
    @GetMapping("/kintai/syain/list")
	public String defaultRoute(Model model, Object form) {
//    	List<Syain> syainList = jdbcTemplate.query(
//    			"SELECT * FROM syain LEFT JOIN bumon ON syain.syozokubumoncode = bumon.bumoncode;",
//    			new SyainMapper()
//    			);
//    	syainList.sort((a,b)-> a.getSyainBango().compareTo(b.getSyainBango()));
//    	model.addAttribute("syainList" ,syainList);
		return "kintai/syain/list";
	}

}