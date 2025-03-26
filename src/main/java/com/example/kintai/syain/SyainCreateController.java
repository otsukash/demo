package com.example.kintai.syain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.auth.UserEntity;
import com.example.auth.UserEntityMapper;


@Controller
public class SyainCreateController {
    @Autowired
    JdbcTemplate jdbcTemplate;

	@GetMapping("/kintai/syain/create")
	public String showCreateSyain(Model model) {
		SyainForm form = new SyainForm();
		model.addAttribute("syainForm",form);

		// 所属部門一覧取得
		List<Bumon> bumonList = jdbcTemplate.query(
				"select * from bumon",
				new BumonMapper()
				);
		model.addAttribute("bumonList",bumonList);
		// ユーザーアカウント一覧取得
		List<UserEntity> userList = jdbcTemplate.query(
				"select * from user_account",
				new UserEntityMapper()
				);
		model.addAttribute("userList",userList);

		return "kintai/syain/create";
	}

	@RequestMapping(value="/kintai/syain/create" ,method=RequestMethod.POST)
	public String createSyain(Model model,@Validated SyainForm form,BindingResult error) {
		if(error.hasErrors()){
			model.addAttribute("syainForm",form);
			// 所属部門一覧取得
			List<Bumon> bumonList = jdbcTemplate.query(
					"select * from bumon",
					new BumonMapper()
					);
			model.addAttribute("bumonList",bumonList);
			// ユーザーアカウント一覧取得
			List<UserEntity> userList = jdbcTemplate.query(
					"select * from user_account",
					new UserEntityMapper()
					);
			model.addAttribute("userList",userList);
            return "kintai/syain/create";
        }
		// 社員番号が数字かチェックする
		String syainBango = form.getCallerSyainBango();
		boolean isPass = true;
		for(int i = 0; i< syainBango.length();i++) {
			if(Character.isDigit(syainBango.charAt(i))) {
				continue;
			}else {
				isPass = false;
			}
		}
		if(isPass == false) {
			model.addAttribute("syainForm",form);
			// 所属部門一覧取得
			List<Bumon> bumonList = jdbcTemplate.query(
					"select * from bumon",
					new BumonMapper()
					);
			model.addAttribute("bumonList",bumonList);
			// ユーザーアカウント一覧取得
			List<UserEntity> userList = jdbcTemplate.query(
					"select * from user_account",
					new UserEntityMapper()
					);
			model.addAttribute("userList",userList);
			error.reject("validation.custom.invalid-syain-bango");
			return "kintai/syain/create";
		}
		// 重複データ登録関連チェック
		// 社員番号が同じ人がいるなら登録拒否
		try {
	    	jdbcTemplate.queryForObject(
					"SELECT * FROM syain JOIN bumon ON syain.syozokubumoncode = bumon.bumoncode where syainbango = ?"
	        		, new SyainMapper()
	        		, new Object[] { form.getCallerSyainBango() }
	            );
			model.addAttribute("syainForm",form);
			// 所属部門一覧取得
			List<Bumon> bumonList = jdbcTemplate.query(
					"select * from bumon",
					new BumonMapper()
					);
			model.addAttribute("bumonList",bumonList);
			// ユーザーアカウント一覧取得
			List<UserEntity> userList = jdbcTemplate.query(
					"select * from user_account",
					new UserEntityMapper()
					);
			model.addAttribute("userList",userList);
			error.reject("validation.custom.same-syain-bango");
            return "kintai/syain/create";
		}
		catch(Exception ex){
			// NOP
			ex.printStackTrace();
		}
		// ユーザーアカウントの関連チェック
		try {
	    	jdbcTemplate.queryForObject(
					"SELECT * FROM syain JOIN bumon ON syain.syozokubumoncode = bumon.bumoncode where useraccountid = ?"
	        		, new SyainMapper()
	        		, new Object[] { form.getCallerUserAccountId() }
	            );
			model.addAttribute("syainForm",form);
			// 所属部門一覧取得
			List<Bumon> bumonList = jdbcTemplate.query(
					"select * from bumon",
					new BumonMapper()
					);
			model.addAttribute("bumonList",bumonList);
			// ユーザーアカウント一覧取得
			List<UserEntity> userList = jdbcTemplate.query(
					"select * from user_account",
					new UserEntityMapper()
					);
			model.addAttribute("userList",userList);
			error.reject("validation.custom.same-useraccount-id");
            return "kintai/syain/create";
		}
		catch(Exception ex){
			// NOP
			ex.printStackTrace();
		}

    	jdbcTemplate.update(
    			"insert into syain(syainbango,sei,mei,nyusyanengappi,syozokubumoncode,useraccountid) values(?,?,?,?,?,?)",
    			form.getCallerSyainBango(),form.getCallerSei(),form.getCallerMei(),
    			form.getCallerNyusyaNengappi(),
    			form.getCallerSyozokuBumonCode(),
    			form.getCallerUserAccountId()
    			);
		return "redirect:/kintai/syain/list";
	}
}