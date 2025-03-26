package com.example.kintai.syain;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class SyainForm {
	@Size(max=6,min=6, message="社員番号は6桁で入力してください")
	private String callerSyainBango;
	@NotEmpty(message="姓は必須入力です")
	@Size(max=50)
	private String callerSei;
	@NotEmpty(message="名は必須入力です")
	@Size(max=50)
	private String callerMei;
	@NotNull(message="入社年月日は必須入力です")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date callerNyusyaNengappi;
	// dbではnull許容なし、実装では空文字列を入力しなければならない?
	@NotNull
	private String callerSyozokuBumonCode;
	@NotNull
	private int callerUserAccountId;

	
	public String getCallerSyozokuBumonCode() {
		return callerSyozokuBumonCode;
	}

	public void setCallerSyozokuBumonCode(String callerSyozokuBumonCode) {
		this.callerSyozokuBumonCode = callerSyozokuBumonCode;
	}

	public String getCallerSyainBango() {
		return callerSyainBango;
	}

	public void setCallerSyainBango(String callerSyainBango) {
		this.callerSyainBango = callerSyainBango;
	}

	public String getCallerSei() {
		return callerSei;
	}

	public void setCallerSei(String callerSei) {
		this.callerSei = callerSei;
	}

	public String getCallerMei() {
		return callerMei;
	}

	public void setCallerMei(String callerMei) {
		this.callerMei = callerMei;
	}

	public Date getCallerNyusyaNengappi() {
		return callerNyusyaNengappi;
	}

	public void setCallerNyusyaNengappi(Date callerNyusyaNengappi) {
		this.callerNyusyaNengappi = callerNyusyaNengappi;
	}

	public int getCallerUserAccountId() {
		return callerUserAccountId;
	}

	public void setCallerUserAccountId(int callerUserAccountId) {
		this.callerUserAccountId = callerUserAccountId;
	}
}
